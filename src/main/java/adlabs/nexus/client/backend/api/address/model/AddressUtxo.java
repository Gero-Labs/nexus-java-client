package adlabs.nexus.client.backend.api.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressUtxo {
    private String txHash;
    private Integer txIndex;
    private String address;
    private String stakeAddress;
    private String paymentCred;
    private Integer epoch;
    private Integer blockHeight;
    private Integer blockTime;
    private Long slot;
    private String value;
    private String datumHash;
    private InlineDatumValue inlineDatum;
    private ReferenceScriptValue referenceScript;
    private List<AssetBalance> assets;
    private Boolean spent;
    private String blockHash;
}
