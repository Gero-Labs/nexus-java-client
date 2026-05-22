package adlabs.nexus.client.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/** Adds the {@code X-Api-Key} header to every outgoing request. */
public class ApiKeyInterceptor implements Interceptor {

    private static final String HEADER = "X-Api-Key";
    private final String apiKey;

    public ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (apiKey != null && !apiKey.isBlank()) {
            request = request.newBuilder().header(HEADER, apiKey).build();
        }
        return chain.proceed(request);
    }
}
