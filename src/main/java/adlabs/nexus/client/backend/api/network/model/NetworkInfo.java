package adlabs.nexus.client.backend.api.network.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkInfo {
    private Integer networkMagic;
    private Long systemStart;
    private Integer epochLength;
    private Double slotLength;
    private Integer slotsPerKesPeriod;
    private Integer maxKesEvolutions;
    private Integer securityParam;
    private BigDecimal activeSlotsCoefficient;
    private Integer updateQuorum;
    private String maxLovelaceSupply;
}
