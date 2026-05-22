package adlabs.nexus.client.backend.api.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInformation {
    private Boolean active;
    private Integer activeEpoch;
    private String controlledAmount;
    private String rewardsSum;
    private String reservesSum;
    private String withdrawalsSum;
    private String treasurySum;
    private String withdrawableAmount;
    private String poolId;
    private String drepId;
    private String stakeAddress;
    private String utxoBalance;
    private String deposit;
    private String proposalRefund;
}
