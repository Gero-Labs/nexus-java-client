package adlabs.nexus.client.backend.api.script.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScriptDetail {
    private String hash;
    private String type;
    private String cbor;
    private Integer size;
    private JsonNode json;
}
