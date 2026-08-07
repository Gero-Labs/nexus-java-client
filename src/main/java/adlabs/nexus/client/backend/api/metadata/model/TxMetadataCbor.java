package adlabs.nexus.client.backend.api.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxMetadataCbor {
    private String label;
    private String cbor;
}
