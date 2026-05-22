package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfo {
    private String address;
    private String stakeAddress;
    private Boolean scriptAddress;
    private String addressType;
    private String balance;
    private List<AssetBalance> assets;
    private List<AddressUtxo> utxos;
}
