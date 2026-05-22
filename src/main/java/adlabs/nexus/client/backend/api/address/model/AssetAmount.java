package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetAmount {
    @JsonProperty("policy_id")
    private String policyId;
    @JsonProperty("asset_name")
    private String assetName;
    private String fingerprint;
    private String quantity;
}
