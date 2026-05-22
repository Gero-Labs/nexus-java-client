package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolRetirement {
    private Long blockNumber;
    private Long blockTime;
    private String txHash;
    private Integer certIndex;
    private Integer txIndex;
    private String poolId;
    private Integer retirementEpoch;
    private Integer epoch;
    private Long slot;
    private String blockHash;
    private String poolIdBech32;
}
