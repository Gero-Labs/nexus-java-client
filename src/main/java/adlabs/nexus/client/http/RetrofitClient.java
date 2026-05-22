package adlabs.nexus.client.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.time.Duration;

/** Builds the shared {@link Retrofit} instance used by every resource service. */
public final class RetrofitClient {

    private RetrofitClient() {
    }

    public static Retrofit build(String baseUrl, String apiKey, Duration connectTimeout, Duration readTimeout) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .build();

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Retrofit.Builder()
                .baseUrl(baseUrl.endsWith("/") ? baseUrl : baseUrl + "/")
                .client(httpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }
}
