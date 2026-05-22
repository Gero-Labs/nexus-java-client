package adlabs.nexus.client.backend.api.epoch;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.epoch.impl.EpochServiceImpl;
import adlabs.nexus.client.backend.api.epoch.model.ProtocolParams;
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

class EpochServiceTest {

    private MockWebServer server;
    private EpochService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new EpochServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getEpochParams_sendsEpochNoParam_andParsesDecimals() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"minFeeA\":44,\"minFeeB\":155381,\"a0\":0.3,\"keyDeposit\":\"2000000\"}"));

        Result<ProtocolParams> result = service.getEpochParams(Network.MAINNET, 520);

        assertTrue(result.isSuccessful());
        assertEquals(Integer.valueOf(44), result.getValue().getMinFeeA());
        assertEquals(0, new BigDecimal("0.3").compareTo(result.getValue().getA0()));

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/epoch/params?network=cardano-mainnet&epoch_no=520", req.getPath());
    }
}
