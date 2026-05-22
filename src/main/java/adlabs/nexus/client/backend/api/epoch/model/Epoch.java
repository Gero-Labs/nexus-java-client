package adlabs.nexus.client.backend.api.epoch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Epoch {
    private Integer epoch;
    private Long startTime;
    private Long endTime;
    private Long firstBlockTime;
    private Long lastBlockTime;
    private Integer blockCount;
    private Integer txCount;
    private String output;
    private String fees;
    private String activeStake;
}
