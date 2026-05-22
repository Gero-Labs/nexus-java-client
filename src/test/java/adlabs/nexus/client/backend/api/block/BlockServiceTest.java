package adlabs.nexus.client.backend.api.block;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.block.impl.BlockServiceImpl;
import adlabs.nexus.client.backend.api.block.model.Block;
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

class BlockServiceTest {

    private MockWebServer server;
    private BlockService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new BlockServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getLatestBlock_mapsSnakeCaseFields() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"hash\":\"blk1\",\"height\":1000,\"slot\":555,\"tx_count\":7," +
                "\"slot_leader\":\"pool1\",\"epoch_slot\":42}"));

        Result<Block> result = service.getLatestBlock(Network.MAINNET);

        assertTrue(result.isSuccessful());
        assertEquals("blk1", result.getValue().getHash());
        assertEquals(Integer.valueOf(7), result.getValue().getTxCount());
        assertEquals("pool1", result.getValue().getSlotLeader());
        assertEquals(Long.valueOf(42), result.getValue().getEpochSlot());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/blocks/latest?network=cardano-mainnet", req.getPath());
    }
}
