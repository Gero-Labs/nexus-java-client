package adlabs.nexus.client.backend.api.asset.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentAddress {
    private String paymentAddress;
    private String stakeAddress;
}
