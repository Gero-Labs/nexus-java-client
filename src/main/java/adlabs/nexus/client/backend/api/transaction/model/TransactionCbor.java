package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCbor {
    private String txHash;
    private String blockHash;
    private Long blockHeight;
    private Integer epochNo;
    private Long absoluteSlot;
    private String txTimestamp;
    private String cbor;
}
