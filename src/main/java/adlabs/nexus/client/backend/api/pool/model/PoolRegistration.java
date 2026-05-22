package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolRegistration {
    private Long blockNumber;
    private Long blockTime;
    private String txHash;
    private Integer certIndex;
    private Integer txIndex;
    private String poolId;
    private String vrfKeyHash;
    private Long pledge;
    private Long cost;
    private BigDecimal margin;
    private Long marginNumerator;
    private Long marginDenominator;
    private String rewardAccount;
    private Set<String> poolOwners;
    private List<Relay> relays;
    private String metadataUrl;
    private String metadataHash;
    private Integer epoch;
    private Long slot;
    private String blockHash;
    private String poolIdBech32;
}
