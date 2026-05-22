package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pool {
    private String poolIdBech32;
    private String poolIdHex;
    private String poolStatus;
    private Long activeEpochNo;
    private Long retiringEpoch;
    private BigDecimal marginPct;
    private BigInteger fixedCost;
    private BigInteger pledgeDeclared;
    private BigInteger deposit;
    private String rewardAddr;
    private List<String> owners;
    private List<Relay> relays;
    private String metaUrl;
    private String metaHash;
    private JsonNode metaJson;
    private String vrfKeyHash;
    private Long opCertCounter;
    private Long blockCount;
    private Long blocksMinted;
    private BigInteger activeStake;
    private BigInteger liveStake;
    private BigInteger livePledge;
    private BigDecimal sigma;
    private BigDecimal liveSaturationPct;
    private BigDecimal liveStakePct;
    private Long liveDelegators;
    private String ticker;
    private String poolGroup;
}
