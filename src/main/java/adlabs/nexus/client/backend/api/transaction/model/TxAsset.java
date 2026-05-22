package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxAsset {
    @JsonProperty("policy_id")
    private String policyId;
    private String unit;
    private String assetName;
    private String fingerprint;
    private Integer decimals;
    private String quantity;
}
