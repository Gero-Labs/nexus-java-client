package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxPlutusContract {
    private String address;
    @JsonProperty("script_hash")
    private String scriptHash;
    private String bytecode;
    private Integer size;
    @JsonProperty("valid_contract")
    private Boolean validContract;
    private PlutusScriptInput input;
}
