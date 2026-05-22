package adlabs.nexus.client.backend.api.asset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetMetadata {
    private String name;
    private String description;
    private String ticker;
    private String url;
    private String logo;
    private Integer decimals;
}
