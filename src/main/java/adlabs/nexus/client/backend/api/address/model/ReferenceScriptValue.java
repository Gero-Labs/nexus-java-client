package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceScriptValue {
    private String hash;
    private Integer size;
    private String type;
    private String bytes;
    private JsonNode value;
}
