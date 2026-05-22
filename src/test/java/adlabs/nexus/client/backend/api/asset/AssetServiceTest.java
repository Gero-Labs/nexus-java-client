package adlabs.nexus.client.backend.api.asset;

import adlabs.nexus.client.backend.api.asset.impl.AssetServiceImpl;
import adlabs.nexus.client.backend.api.asset.model.AssetDetailedInformation;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AssetServiceTest {

    private MockWebServer server;
    private AssetService service;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        service = new AssetServiceImpl(RetrofitClient.build(
                server.url("/").toString(), "key", Duration.ofSeconds(5), Duration.ofSeconds(5)));
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getAssetDetailedInformation_sendsPolicyAndName() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(
                "{\"policyId\":\"abc\",\"assetName\":\"546f6b656e\",\"quantity\":\"1\"," +
                "\"metadata\":{\"name\":\"Token\",\"decimals\":6}}"));

        Result<AssetDetailedInformation> result =
                service.getAssetDetailedInformation(Network.MAINNET, "abc", "546f6b656e");

        assertTrue(result.isSuccessful());
        assertEquals("Token", result.getValue().getMetadata().getName());

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/assets/detailedInfo?network=cardano-mainnet&assetPolicy=abc&assetName=546f6b656e",
                req.getPath());
    }

    @Test
    void getBlacklist_returnsStringSet_withoutNetworkParam() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[\"policy1\",\"policy2\"]"));
        Result<Set<String>> result = service.getBlacklist();
        assertTrue(result.isSuccessful());
        assertEquals(2, result.getValue().size());
        assertTrue(result.getValue().contains("policy1"));

        RecordedRequest req = server.takeRequest();
        assertEquals("/api/assets/blacklist", req.getPath());
    }
}
