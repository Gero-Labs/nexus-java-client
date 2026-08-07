package adlabs.nexus.client.backend.api.metadata;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.metadata.impl.MetadataServiceImpl;
import adlabs.nexus.client.backend.api.metadata.model.LabelMetadataCbor;
import adlabs.nexus.client.backend.api.metadata.model.LabelMetadataJson;
import adlabs.nexus.client.backend.api.metadata.model.MetadataLabel;
import adlabs.nexus.client.backend.api.metadata.model.TxMetadataCbor;
import adlabs.nexus.client.backend.api.metadata.model.TxMetadataJson;
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

class MetadataServiceTest {

    private MockWebServer server;
    private MetadataService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new MetadataServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getTxMetadata_hitsPath_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"label\":\"674\",\"json\":{\"msg\":[\"hi\"]}}]"));
        Result<List<TxMetadataJson>> r = service.getTxMetadata(Network.MAINNET, "tx1");
        assertTrue(r.isSuccessful());
        assertEquals("674", r.getValue().get(0).getLabel());
        assertEquals("hi", r.getValue().get(0).getJson().get("msg").get(0).asText());
        assertEquals("/api/transactions/tx1/metadata?network=cardano-mainnet", server.takeRequest().getPath());
    }

    @Test
    void getTxMetadataCbor_hitsPath_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"label\":\"674\",\"cbor\":\"a100\"}]"));
        Result<List<TxMetadataCbor>> r = service.getTxMetadataCbor(Network.MAINNET, "tx1");
        assertTrue(r.isSuccessful());
        assertEquals("674", r.getValue().get(0).getLabel());
        assertEquals("a100", r.getValue().get(0).getCbor());
        RecordedRequest req = server.takeRequest();
        assertEquals("/api/transactions/tx1/metadata/cbor?network=cardano-mainnet", req.getPath());
    }

    @Test
    void getMetadataLabels_hitsPath_withPagination_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"label\":\"674\",\"cip10\":\"msg\",\"count\":42}]"));
        Result<List<MetadataLabel>> r = service.getMetadataLabels(Network.MAINNET, 1, 100);
        assertTrue(r.isSuccessful());
        assertEquals("674", r.getValue().get(0).getLabel());
        assertEquals("msg", r.getValue().get(0).getCip10());
        assertEquals(Long.valueOf(42), r.getValue().get(0).getCount());
        RecordedRequest req = server.takeRequest();
        assertEquals("/api/metadata/txs/labels?network=cardano-mainnet&page=1&pageSize=100", req.getPath());
    }

    @Test
    void getMetadataByLabel_hitsPath_withPagination() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"txHash\":\"abc\",\"json\":{\"k\":\"v\"}}]"));
        Result<List<LabelMetadataJson>> r = service.getMetadataByLabel(Network.MAINNET, "674", 2, 50);
        assertTrue(r.isSuccessful());
        assertEquals("abc", r.getValue().get(0).getTxHash());
        assertEquals("/api/metadata/txs/labels/674?network=cardano-mainnet&page=2&pageSize=50",
                     server.takeRequest().getPath());
    }

    @Test
    void getMetadataCborByLabel_hitsPath_withPagination_andMaps() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "[{\"txHash\":\"abc\",\"cbor\":\"a100\"}]"));
        Result<List<LabelMetadataCbor>> r = service.getMetadataCborByLabel(Network.MAINNET, "674", 1, 100);
        assertTrue(r.isSuccessful());
        assertEquals("abc", r.getValue().get(0).getTxHash());
        assertEquals("a100", r.getValue().get(0).getCbor());
        RecordedRequest req = server.takeRequest();
        assertEquals("/api/metadata/txs/labels/674/cbor?network=cardano-mainnet&page=1&pageSize=100",
                     req.getPath());
    }

    @Test
    void getTxMetadata_nonSuccess_returnsError() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(503).setBody("nope"));
        Result<List<TxMetadataJson>> r = service.getTxMetadata(Network.MAINNET, "tx1");
        assertFalse(r.isSuccessful());
        assertEquals(503, r.getCode());
    }
}
