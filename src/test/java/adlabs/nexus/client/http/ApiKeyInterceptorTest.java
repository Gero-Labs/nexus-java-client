package adlabs.nexus.client.http;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiKeyInterceptorTest {

    interface PingApi {
        @GET("ping")
        retrofit2.Call<String> ping();
    }

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
    void addsApiKeyHeader() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("pong"));
        Retrofit retrofit = RetrofitClient.build(
                server.url("/").toString(), "test-key-123",
                Duration.ofSeconds(5), Duration.ofSeconds(5));
        retrofit.create(PingApi.class).ping().execute();

        RecordedRequest recorded = server.takeRequest();
        assertEquals("test-key-123", recorded.getHeader("X-Api-Key"));
    }
}
