package adlabs.nexus.client.backend.api.transaction;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.transaction.impl.TransactionServiceImpl;
import adlabs.nexus.client.backend.api.transaction.model.Transaction;
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

class TransactionServiceTest {

    private MockWebServer server;
    private TransactionService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new TransactionServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getTransaction_usesV1Prefix_andMapsSnakeCase() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"txHash\":\"tx1\",\"block_hash\":\"blk1\",\"block_height\":1000,\"fee\":\"170000\"," +
                "\"inputs\":[{\"payment_address\":{\"bech32\":\"addr1in\"},\"value\":\"1000000\"}]," +
                "\"outputs\":[{\"payment_address\":{\"bech32\":\"addr1out\"},\"value\":\"830000\"}]}"));

        Result<Transaction> result = service.getTransaction(Network.MAINNET, "tx1");

        assertTrue(result.isSuccessful());
        assertEquals("blk1", result.getValue().getBlockHash());
        assertEquals(Long.valueOf(1000), result.getValue().getBlockHeight());
        assertEquals("addr1in", result.getValue().getInputs().get(0).getPaymentAddr().getBech32());

        RecordedRequest req = server.takeRequest();
        assertEquals("/v1/transactions/tx1?network=cardano-mainnet", req.getPath());
    }

    @Test
    void submitTransaction_postsHexBody_andReturnsTxHash() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("tx-hash-result"));

        Result<String> result = service.submitTransaction(Network.PREPROD, "84a400818258...");

        assertTrue(result.isSuccessful());
        assertEquals("tx-hash-result", result.getValue());

        RecordedRequest req = server.takeRequest();
        assertEquals("POST", req.getMethod());
        assertEquals("/v1/transactions/submit?network=cardano-preprod", req.getPath());
        assertEquals("84a400818258...", req.getBody().readUtf8());
    }

    @Test
    void getTransactionsCbor_postsHashArray() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[]"));
        service.getTransactionsCbor(Network.MAINNET, java.util.List.of("h1", "h2"));
        RecordedRequest req = server.takeRequest();
        assertEquals("POST", req.getMethod());
        assertEquals("/v1/transactions/cbor?network=cardano-mainnet", req.getPath());
        assertEquals("[\"h1\",\"h2\"]", req.getBody().readUtf8());
    }
}
