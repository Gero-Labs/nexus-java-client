package adlabs.nexus.client.backend.api.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRewardsHistory {
    private Integer epoch;
    private String amount;
    private String poolId;
    private String type;
    private Integer spendableEpoch;
    private String stakeAddress;
}
