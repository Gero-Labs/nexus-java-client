package adlabs.nexus.client.util;

/**
 * Cardano network. The {@link #queryValue()} is the string Nexus accepts
 * for the {@code ?network=} query parameter.
 */
public enum Network {
    MAINNET("cardano-mainnet"),
    PREPROD("cardano-preprod"),
    PREVIEW("cardano-preview");

    private final String queryValue;

    Network(String queryValue) {
        this.queryValue = queryValue;
    }

    public String queryValue() {
        return queryValue;
    }
}
