package adlabs.nexus.client.backend.api.block.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockSummary {
    private String hash;
    private Long time;
    private Long number;
    private Long slot;
    private Long epoch;
    private Long era;
    private Long output;
    private Long fees;
    private String slotLeader;
    private Long size;
    private Long txCount;
    private String issuerKey;
}
