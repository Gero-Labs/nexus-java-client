package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlutusScriptRedeemer {
    private Purpose purpose;
    private String fee;
    private ExecutionUnit unit;
    private Datum datum;
}
