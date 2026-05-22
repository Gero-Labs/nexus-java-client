package adlabs.nexus.client.backend.api.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountTransaction {
    private String txHash;
    private Integer epochNo;
    private Integer blockHeight;
    private Long blockTime;
}
