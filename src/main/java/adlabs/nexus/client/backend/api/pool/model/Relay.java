package adlabs.nexus.client.backend.api.pool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Relay {
    private String ipv4;
    private String ipv6;
    private String dns;
    private Integer port;
}
