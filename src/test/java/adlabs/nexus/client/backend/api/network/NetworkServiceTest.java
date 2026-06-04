package adlabs.nexus.client.backend.api.network;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.network.impl.NetworkServiceImpl;
import adlabs.nexus.client.backend.api.network.model.NetworkInfo;
import adlabs.nexus.client.http.RetrofitClient;
import adlabs.nexus.client.util.Network;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class NetworkServiceTest {

    private MockWebServer server;
    private NetworkService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new NetworkServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getNetworkInfo_deserializesFields() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"networkMagic\":764824073,\"systemStart\":1506203091," +
                "\"epochLength\":432000,\"slotLength\":1.0,\"slotsPerKesPeriod\":129600," +
                "\"maxKesEvolutions\":62,\"securityParam\":2160," +
                "\"activeSlotsCoefficient\":0.05,\"updateQuorum\":5," +
                "\"maxLovelaceSupply\":\"45000000000000000\"}"));

        Result<NetworkInfo> result = service.getNetworkInfo(Network.MAINNET);

        assertTrue(result.isSuccessful());
        NetworkInfo info = result.getValue();
        assertEquals(764824073, info.getNetworkMagic());
        assertEquals(1506203091L, info.getSystemStart());
        assertEquals(432000, info.getEpochLength());
        assertEquals(1.0, info.getSlotLength());
        assertEquals(129600, info.getSlotsPerKesPeriod());
        assertEquals(62, info.getMaxKesEvolutions());
        assertEquals(2160, info.getSecurityParam());
        assertEquals(new BigDecimal("0.05"), info.getActiveSlotsCoefficient());
        assertEquals(5, info.getUpdateQuorum());
        assertEquals("45000000000000000", info.getMaxLovelaceSupply());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/network/info?network=cardano-mainnet", req.getPath());
    }

    @Test
    void getNetworkInfo_usesApexNetworkQueryValue() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"networkMagic\":1}"));

        Result<NetworkInfo> result = service.getNetworkInfo(Network.APEX_PRIME_MAINNET);

        assertTrue(result.isSuccessful());
        assertEquals(1, result.getValue().getNetworkMagic());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/network/info?network=apex-prime-mainnet", req.getPath());
    }
}
