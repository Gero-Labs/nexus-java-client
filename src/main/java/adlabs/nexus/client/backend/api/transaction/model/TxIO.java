package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxIO {
    @JsonProperty("payment_address")
    private TxPaymentAddress paymentAddr;
    @JsonProperty("stake_address")
    private String stakeAddr;
    private String txHash;
    private Integer txIndex;
    private String value;
    @JsonProperty("datum_hash")
    private String datumHash;
    @JsonProperty("inline_datum")
    private InlineDatum inlineDatum;
    @JsonProperty("reference_script")
    private ReferenceScript referenceScript;
    @JsonProperty("assets")
    private List<TxAsset> assetList;
}
