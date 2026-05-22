package adlabs.nexus.client.util;

/**
 * Cardano network. The {@link #queryValue()} is the string Nexus accepts
 * for the {@code ?network=} query parameter.
 */
public enum Network {
    MAINNET("cardano-mainnet"),
    PREPROD("cardano-preprod"),
    PREVIEW("cardano-preview"),
    APEX_PRIME_MAINNET("apex-prime-mainnet"),
    APEX_VECTOR_MAINNET("apex-vector-mainnet"),
    APEX_VECTOR_TESTNET("apex-vector-testnet");

    private final String queryValue;

    Network(String queryValue) {
        this.queryValue = queryValue;
    }

    public String queryValue() {
        return queryValue;
    }
}
