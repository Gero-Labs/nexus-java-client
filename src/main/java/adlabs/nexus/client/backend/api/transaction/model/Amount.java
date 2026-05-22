package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Amount {
    private String unit;
    private String quantity;
    @JsonProperty("policy_id")
    private String policyId;
    @JsonProperty("asset_name")
    private String assetName;
}
