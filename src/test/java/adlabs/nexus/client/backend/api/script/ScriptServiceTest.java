package adlabs.nexus.client.backend.api.script;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.script.impl.ScriptServiceImpl;
import adlabs.nexus.client.backend.api.script.model.Datum;
import adlabs.nexus.client.backend.api.script.model.ScriptDetail;
import adlabs.nexus.client.http.RetrofitClient;
import adlabs.nexus.client.util.Network;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ScriptServiceTest {

    private MockWebServer server;
    private ScriptService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new ScriptServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getScriptByHash_hitsPath_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"hash\":\"h1\",\"type\":\"plutusV2\",\"cbor\":\"49...\",\"size\":12}"));
        Result<ScriptDetail> r = service.getScriptByHash(Network.MAINNET, "h1");
        assertTrue(r.isSuccessful());
        assertEquals("plutusV2", r.getValue().getType());
        assertEquals(Integer.valueOf(12), r.getValue().getSize());
        assertEquals("/api/scripts/h1?network=cardano-mainnet", server.takeRequest().getPath());
    }

    @Test
    void getDatumByHash_hitsPath_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"hash\":\"d1\",\"cbor\":\"a1...\",\"json\":{\"int\":5}}"));
        Result<Datum> r = service.getDatumByHash(Network.MAINNET, "d1");
        assertTrue(r.isSuccessful());
        assertEquals("a1...", r.getValue().getCbor());
        assertEquals(5, r.getValue().getJson().get("int").asInt());
        assertEquals("/api/scripts/datum/d1?network=cardano-mainnet", server.takeRequest().getPath());
    }

    @Test
    void getScriptByHash_notFound_returnsError() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(404).setBody("no"));
        Result<ScriptDetail> r = service.getScriptByHash(Network.MAINNET, "h1");
        assertFalse(r.isSuccessful());
        assertEquals(404, r.getCode());
    }
}
