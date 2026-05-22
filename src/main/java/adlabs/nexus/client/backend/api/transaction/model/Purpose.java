package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Plutus redeemer purpose. Serialized lowercase by Nexus. */
public enum Purpose {
    @JsonProperty("spend") SPEND,
    @JsonProperty("mint") MINT,
    @JsonProperty("cert") CERT,
    @JsonProperty("reward") REWARD
}
