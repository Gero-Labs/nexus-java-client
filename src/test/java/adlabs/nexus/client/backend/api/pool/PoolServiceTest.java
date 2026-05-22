package adlabs.nexus.client.backend.api.pool;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.pool.impl.PoolServiceImpl;
import adlabs.nexus.client.backend.api.pool.model.Pool;
import adlabs.nexus.client.backend.api.pool.model.PoolDetails;
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

class PoolServiceTest {

    private MockWebServer server;
    private PoolService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new PoolServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getPool_deserializesRelays() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"poolIdBech32\":\"pool1abc\",\"ticker\":\"GERO\"," +
                "\"relays\":[{\"dns\":\"relay.gero.io\",\"port\":3001}]}"));

        Result<Pool> result = service.getPool(Network.MAINNET, "pool1abc");

        assertTrue(result.isSuccessful());
        assertEquals("GERO", result.getValue().getTicker());
        assertEquals("relay.gero.io", result.getValue().getRelays().get(0).getDns());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/pools/pool1abc?network=cardano-mainnet", req.getPath());
    }

    @Test
    void getPoolEpochHistory_mapsSnakeCase() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"epoch\":520,\"pool_id\":\"pool1abc\",\"reward_account\":\"stake1xyz\"}"));

        Result<PoolDetails> result = service.getPoolEpochHistory(Network.MAINNET, "pool1abc", 520);

        assertTrue(result.isSuccessful());
        assertEquals("pool1abc", result.getValue().getPoolId());
        assertEquals("stake1xyz", result.getValue().getRewardAccount());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/pools/pool1abc/epochs/520?network=cardano-mainnet", req.getPath());
    }
}
