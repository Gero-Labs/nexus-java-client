package adlabs.nexus.client.backend.api.epoch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtocolParams {
    private Integer minFeeA;
    private Integer minFeeB;
    private Integer maxBlockSize;
    private Integer maxTxSize;
    private Integer maxBlockHeaderSize;
    private String keyDeposit;
    private String poolDeposit;
    private Integer eMax;
    private Integer nOpt;
    private BigDecimal a0;
    private BigDecimal rho;
    private BigDecimal tau;
    private BigDecimal decentralisationParam;
    private String extraEntropy;
    private Integer protocolMajorVer;
    private Integer protocolMinorVer;
    private String minUtxo;
    private String minPoolCost;
    private String nonce;
    private LinkedHashMap<String, LinkedHashMap<String, Long>> costModels;
    private BigDecimal priceMem;
    private BigDecimal priceStep;
    private String maxTxExMem;
    private String maxTxExSteps;
    private String maxBlockExMem;
    private String maxBlockExSteps;
    private String maxValSize;
    private BigDecimal collateralPercent;
    private Integer maxCollateralInputs;
    private String coinsPerUtxoSize;
    private BigDecimal pvtMotionNoConfidence;
    private BigDecimal pvtCommitteeNormal;
    private BigDecimal pvtCommitteeNoConfidence;
    private BigDecimal pvtHardForkInitiation;
    private BigDecimal pvtPPSecurityGroup;
    private BigDecimal dvtMotionNoConfidence;
    private BigDecimal dvtCommitteeNormal;
    private BigDecimal dvtCommitteeNoConfidence;
    private BigDecimal dvtUpdateToConstitution;
    private BigDecimal dvtHardForkInitiation;
    private BigDecimal dvtPPNetworkGroup;
    private BigDecimal dvtPPEconomicGroup;
    private BigDecimal dvtPPTechnicalGroup;
    private BigDecimal dvtPPGovGroup;
    private BigDecimal dvtTreasuryWithdrawal;
    private Integer committeeMinSize;
    private Integer committeeMaxTermLength;
    private Integer govActionLifetime;
    private BigInteger govActionDeposit;
    private BigInteger drepDeposit;
    private Integer drepActivity;
    private BigDecimal minFeeRefScriptCostPerByte;
}
