package adlabs.nexus.client.backend.api.transaction.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtxoRequestItem {
    private String txHash;
    private Integer outputIndex;
}
