package adlabs.nexus.client.backend.api.account.model;

import adlabs.nexus.client.backend.api.transaction.model.InlineDatum;
import adlabs.nexus.client.backend.api.transaction.model.ReferenceScript;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUtxo {
    private String txHash;
    private Integer txIndex;
    private String address;
    private String value;
    private String stakeAddress;
    private String paymentCred;
    private Integer epochNo;
    private Integer blockHeight;
    private Long blockTime;
    private String datumHash;
    private InlineDatum inlineDatum;
    private ReferenceScript referenceScript;
    private List<AccountAsset> assetList;
    private Boolean isSpent;
}
