package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetBalance {
    private String unit;
    private String policyId;
    private String assetName;
    private String fingerprint;
    private String quantity;
    private Integer decimals;
    private Boolean hasOnchainMetadata;
}
