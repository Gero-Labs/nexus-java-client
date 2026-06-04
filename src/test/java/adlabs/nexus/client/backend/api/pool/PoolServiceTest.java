package adlabs.nexus.client.backend.api.pool;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.pool.impl.PoolServiceImpl;
import adlabs.nexus.client.backend.api.pool.model.Pool;
import adlabs.nexus.client.backend.api.pool.model.PoolDetails;
import adlabs.nexus.client.backend.api.pool.model.PoolHistory;
import adlabs.nexus.client.http.RetrofitClient;
import adlabs.nexus.client.util.Network;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.List;

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

    @Test
    void getPoolHistory_deserializesList() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"epochNo\":520,\"activeStake\":\"123456789\",\"activeStakePct\":1.25," +
                "\"saturationPct\":42.7,\"blockCnt\":12,\"delegatorCnt\":345," +
                "\"margin\":0.02,\"fixedCost\":\"340000000\",\"poolFees\":\"500000\"," +
                "\"delegRewards\":\"987654321\",\"epochRos\":3.14}]"));

        Result<List<PoolHistory>> result = service.getPoolHistory(Network.MAINNET, "pool1abc");

        assertTrue(result.isSuccessful());
        assertEquals(1, result.getValue().size());
        PoolHistory h = result.getValue().get(0);
        assertEquals(520, h.getEpochNo());
        assertEquals(new BigInteger("123456789"), h.getActiveStake());
        assertEquals(new BigDecimal("1.25"), h.getActiveStakePct());
        assertEquals(new BigDecimal("42.7"), h.getSaturationPct());
        assertEquals(12, h.getBlockCnt());
        assertEquals(345, h.getDelegatorCnt());
        assertEquals(new BigDecimal("0.02"), h.getMargin());
        assertEquals(new BigInteger("340000000"), h.getFixedCost());
        assertEquals(new BigInteger("500000"), h.getPoolFees());
        assertEquals(new BigInteger("987654321"), h.getDelegRewards());
        assertEquals(new BigDecimal("3.14"), h.getEpochRos());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/pools/pool1abc/history?network=cardano-mainnet", req.getPath());
    }
}
