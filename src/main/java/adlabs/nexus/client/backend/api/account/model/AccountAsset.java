package adlabs.nexus.client.backend.api.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountAsset {
    private String policyId;
    private String assetName;
    private String fingerprint;
    private Integer decimals;
    private String quantity;
}
