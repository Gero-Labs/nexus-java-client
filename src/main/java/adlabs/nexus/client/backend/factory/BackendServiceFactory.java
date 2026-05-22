package adlabs.nexus.client.backend.factory;

import adlabs.nexus.client.backend.factory.impl.BackendServiceImpl;
import adlabs.nexus.client.http.RetrofitClient;

import java.time.Duration;

/** Builds a {@link BackendService} bound to a Nexus base URL and API key. */
public final class BackendServiceFactory {

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(30);

    private BackendServiceFactory() {
    }

    /**
     * @param baseUrl Nexus base URL, e.g. {@code https://nexus.gerowallet.io}
     * @param apiKey  Nexus API key, sent as the {@code X-Api-Key} header
     */
    public static BackendService getNexusBackendService(String baseUrl, String apiKey) {
        return getNexusBackendService(baseUrl, apiKey, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public static BackendService getNexusBackendService(String baseUrl, String apiKey,
                                                        Duration connectTimeout, Duration readTimeout) {
        return new BackendServiceImpl(
                RetrofitClient.build(baseUrl, apiKey, connectTimeout, readTimeout));
    }
}
