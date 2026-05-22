package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressTransaction {
    private String txHash;
    private Integer txIndex;
    private Long blockHeight;
    private Long blockTime;
    private Integer epochNo;
    private Long slot;
    private Boolean input;
    private Boolean output;
}
