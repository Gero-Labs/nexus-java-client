package adlabs.nexus.client.backend.api.base;

import adlabs.nexus.client.backend.api.base.exception.ApiException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

import static org.junit.jupiter.api.Assertions.*;

class ApiUtilTest {

    interface PingApi {
        @GET("/ping")
        retrofit2.Call<String> ping();
    }

    private MockWebServer server;
    private PingApi api;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        api = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(PingApi.class);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void process_returnsSuccess_on200() throws ApiException {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("pong"));
        Result<String> result = ApiUtil.process(api.ping());
        assertTrue(result.isSuccessful());
        assertEquals(200, result.getCode());
        assertEquals("pong", result.getValue());
    }

    @Test
    void process_returnsError_on404() throws ApiException {
        server.enqueue(new MockResponse().setResponseCode(404).setBody("not found"));
        Result<String> result = ApiUtil.process(api.ping());
        assertFalse(result.isSuccessful());
        assertEquals(404, result.getCode());
        assertEquals("not found", result.getResponse());
        assertNull(result.getValue());
    }

    @Test
    void process_throwsApiException_onTransportFailure() throws Exception {
        server.shutdown(); // no server listening
        assertThrows(ApiException.class, () -> ApiUtil.process(api.ping()));
    }
}
