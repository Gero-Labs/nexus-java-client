package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolDetails {
    private Integer epoch;
    @JsonProperty("pool_id")
    private String poolId;
    @JsonProperty("pool_hash")
    private String poolHash;
    @JsonProperty("vrf_key_hash")
    private String vrfKeyHash;
    private String pledge;
    private String cost;
    private BigDecimal margin;
    @JsonProperty("reward_account")
    private String rewardAccount;
    @JsonProperty("pool_owners")
    private List<String> poolOwners;
    private List<Relay> relays;
    @JsonProperty("metadata_url")
    private String metadataUrl;
    @JsonProperty("metadata_hash")
    private String metadataHash;
    @JsonProperty("tx_hash")
    private String txHash;
    @JsonProperty("cert_index")
    private Integer certIndex;
    private String status;
    @JsonProperty("retire_epoch")
    private Integer retireEpoch;
}
