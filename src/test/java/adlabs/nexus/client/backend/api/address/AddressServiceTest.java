package adlabs.nexus.client.backend.api.address;

import adlabs.nexus.client.backend.api.address.impl.AddressServiceImpl;
import adlabs.nexus.client.backend.api.address.model.AddressInfo;
import adlabs.nexus.client.backend.api.address.model.TransactionHistoryResponse;
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

class AddressServiceTest {

    private MockWebServer server;
    private AddressService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new AddressServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getAddressInformation_deserializesAssets() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"address\":\"addr1xyz\",\"balance\":\"5000000\"," +
                "\"assets\":[{\"unit\":\"lovelace\",\"quantity\":\"5000000\"}]}"));

        Result<AddressInfo> result = service.getAddressInformation(Network.MAINNET, "addr1xyz");

        assertTrue(result.isSuccessful());
        assertEquals("5000000", result.getValue().getBalance());
        assertEquals(1, result.getValue().getAssets().size());
        assertEquals("lovelace", result.getValue().getAssets().get(0).getUnit());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/addresses/addr1xyz?network=cardano-mainnet", req.getPath());
    }

    @Test
    void getAddressTransactionHistory_mapsSnakeCaseAndPagination() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"transactions\":[{\"txHash\":\"abc\",\"block_height\":100,\"total_output\":\"42\"}]," +
                "\"pagination\":{\"page\":1,\"pageSize\":20,\"totalItems\":1}}"));

        Result<TransactionHistoryResponse> result =
                service.getAddressTransactionHistory(Network.PREPROD, "addr1xyz", 1, 20);

        assertTrue(result.isSuccessful());
        assertEquals(Long.valueOf(100), result.getValue().getTransactions().get(0).getBlockHeight());
        assertEquals("42", result.getValue().getTransactions().get(0).getTotalOutput());
        assertEquals(Integer.valueOf(20), result.getValue().getPagination().getPageSize());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/addresses/addr1xyz/transactions/history?network=cardano-preprod&page=1&pageSize=20",
                req.getPath());
    }
}
