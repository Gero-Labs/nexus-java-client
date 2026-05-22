package adlabs.nexus.client.backend.api.account;

import adlabs.nexus.client.backend.api.account.impl.AccountServiceImpl;
import adlabs.nexus.client.backend.api.account.model.AccountInformation;
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
}
