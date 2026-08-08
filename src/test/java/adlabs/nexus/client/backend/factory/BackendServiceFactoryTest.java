package adlabs.nexus.client.backend.factory;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.block.model.Block;
import adlabs.nexus.client.util.Network;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackendServiceFactoryTest {

    private MockWebServer server;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void factory_buildsWorkingBackendService() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("{\"hash\":\"blk1\"}"));

        BackendService backend = BackendServiceFactory.getNexusBackendService(
                server.url("/").toString(), "key");

        assertNotNull(backend.getAccountService());
        assertNotNull(backend.getAddressService());
        assertNotNull(backend.getAssetService());
        assertNotNull(backend.getBlockService());
        assertNotNull(backend.getEpochService());
        assertNotNull(backend.getNetworkService());
        assertNotNull(backend.getPoolService());
        assertNotNull(backend.getTransactionService());

        Result<Block> result = backend.getBlockService().getLatestBlock(Network.MAINNET);
        assertTrue(result.isSuccessful());
        assertEquals("blk1", result.getValue().getHash());
    }

    @Test
    void factory_wiresMetadataAndScriptServices() {
        BackendService svc = BackendServiceFactory.getNexusBackendService(
                server.url("/").toString(), "key");
        assertNotNull(svc.getMetadataService());
        assertNotNull(svc.getScriptService());
    }
}
