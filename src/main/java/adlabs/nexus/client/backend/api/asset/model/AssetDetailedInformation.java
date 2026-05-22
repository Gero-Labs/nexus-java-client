package adlabs.nexus.client.backend.api.asset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDetailedInformation {
    private String asset;
    private String policyId;
    private String assetName;
    private String assetNameAscii;
    private String fingerprint;
    private String quantity;
    private String initialMintTxHash;
    private Integer mintCnt;
    private Integer burnCnt;
    private Integer mintOrBurnCount;
    private Long creationTime;
    private String onchainMetadata;
    private String cip68Metadata;
    private AssetMetadata metadata;
    private String latestMintTxHash;
    private Long latestMintTime;
    private Long holdersByAddress;
    private Long holdersByAccount;
}
