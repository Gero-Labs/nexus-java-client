package adlabs.nexus.client.backend.api.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataLabel {
    private String label;
    private String cip10;
    private Long count;
}
