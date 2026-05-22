package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolHistory {
    private Integer epochNo;
    private BigInteger activeStake;
    private BigDecimal activeStakePct;
    private BigDecimal saturationPct;
    private Integer blockCnt;
    private Integer delegatorCnt;
    private BigDecimal margin;
    private BigInteger fixedCost;
    private BigInteger poolFees;
    private BigInteger delegRewards;
    private BigDecimal epochRos;
}
