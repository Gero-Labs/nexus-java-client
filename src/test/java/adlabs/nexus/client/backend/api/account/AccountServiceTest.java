package adlabs.nexus.client.backend.api.account;

import adlabs.nexus.client.backend.api.account.impl.AccountServiceImpl;
import adlabs.nexus.client.backend.api.account.model.AccountAsset;
import adlabs.nexus.client.backend.api.account.model.AccountInformation;
import adlabs.nexus.client.backend.api.account.model.AccountTransaction;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.http.RetrofitClient;
import adlabs.nexus.client.util.Network;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private MockWebServer server;
    private AccountService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new AccountServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getAccountInformation_deserializesAndSendsNetwork() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"active\":true,\"activeEpoch\":520,\"controlledAmount\":\"1000000\"," +
                "\"stakeAddress\":\"stake1xyz\",\"poolId\":\"pool1abc\"}"));

        Result<AccountInformation> result =
                service.getAccountInformation(Network.MAINNET, "stake1xyz");

        assertTrue(result.isSuccessful());
        assertEquals("1000000", result.getValue().getControlledAmount());
        assertEquals("pool1abc", result.getValue().getPoolId());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/account/stake1xyz/info?network=cardano-mainnet", req.getPath());
        assertEquals("key", req.getHeader("X-Api-Key"));
    }

    @Test
    void getAccountTransactions_includesFromParam() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[]"));
        service.getAccountTransactions(Network.PREPROD, "stake1xyz", 42);
        RecordedRequest req = server.takeRequest();
        assertEquals("/api/account/stake1xyz/txs?network=cardano-preprod&from=42", req.getPath());
    }

    @Test
    void getAccountInformation_returnsError_on404() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404).setBody("{\"message\":\"not found\"}"));
        Result<AccountInformation> result =
                service.getAccountInformation(Network.MAINNET, "stake1missing");
        assertFalse(result.isSuccessful());
        assertEquals(404, result.getCode());
    }

    // ------------------------------------------------------------------
    // GET /api/account/{stake}/assets  (issue #7)
    //
    // Nexus aggregates native-asset balances server-side, but the client never
    // wrapped it, so consumers had to pull every account UTxO and sum the asset
    // lists themselves -- O(number of UTxOs) in wire bytes for a figure the
    // server already knows. cardano-shield-api does exactly that today.
    // ------------------------------------------------------------------

    @Test
    void getAccountAssets_callsTheAssetsEndpointWithPaging() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"policyId\":\"policyA\",\"assetName\":\"TOKEN\",\"quantity\":\"350\"}]"));

        Result<List<AccountAsset>> result =
                service.getAccountAssets(Network.MAINNET, "stake1xyz", 1, 100);

        RecordedRequest request = server.takeRequest();
        assertTrue(request.getPath().startsWith("/api/account/stake1xyz/assets"),
                "unexpected path: " + request.getPath());
        assertTrue(request.getPath().contains("page=1"), request.getPath());
        assertTrue(request.getPath().contains("pageSize=100"), request.getPath());
        assertTrue(result.isSuccessful());
        assertEquals(1, result.getValue().size());
        assertEquals("policyA", result.getValue().get(0).getPolicyId());
        assertEquals("350", result.getValue().get(0).getQuantity());
    }

    @Test
    void getAccountAssets_passesThePolicyFilterWhenGiven() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[]"));

        service.getAccountAssets(Network.MAINNET, "stake1xyz", "policyA", 1, 100);

        RecordedRequest request = server.takeRequest();
        assertTrue(request.getPath().contains("policy=policyA"), request.getPath());
    }

    // ------------------------------------------------------------------
    // GET /api/account/{stake}/txs — order / to  (issue #8)
    //
    // The endpoint accepts both, the client exposed neither, so a caller wanting
    // one end of an account's history had to download all of it. cardano-shield
    // needs only the oldest transaction to decide whether an address is new.
    // ------------------------------------------------------------------

    @Test
    void getAccountTransactions_sendsOrderAndUpperBound() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[]"));

        service.getAccountTransactions(Network.MAINNET, "stake1xyz", 1, 12_000_000, "desc");

        RecordedRequest request = server.takeRequest();
        assertTrue(request.getPath().contains("from=1"), request.getPath());
        assertTrue(request.getPath().contains("to=12000000"), request.getPath());
        assertTrue(request.getPath().contains("order=desc"), request.getPath());
    }

    @Test
    void getAccountTransactions_shortOverloadIsUnchanged() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[]"));

        Result<List<AccountTransaction>> result =
                service.getAccountTransactions(Network.MAINNET, "stake1xyz", 1);

        RecordedRequest request = server.takeRequest();
        assertTrue(request.getPath().contains("from=1"), request.getPath());
        assertFalse(request.getPath().contains("order="),
                "the existing overload must keep relying on the server default");
        assertTrue(result.isSuccessful());
    }
}
