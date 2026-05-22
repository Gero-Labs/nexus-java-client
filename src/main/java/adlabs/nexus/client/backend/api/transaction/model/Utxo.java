package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Utxo {
    @JsonProperty("tx_hash")
    private String txHash;
    @JsonProperty("output_index")
    private Integer outputIndex;
    @JsonProperty("owner_addr")
    private String address;
    @JsonProperty("owner_stake_addr")
    private String stakeAddress;
    @JsonProperty("owner_payment_credential")
    private String ownerPaymentCredential;
    @JsonProperty("owner_stake_credential")
    private String ownerStakeCredential;
    @JsonProperty("amounts")
    private List<Amount> amount;
    @JsonProperty("lovelace_amount")
    private Long lovelaceAmount;
    @JsonProperty("data_hash")
    private String dataHash;
    @JsonProperty("inline_datum")
    private String inlineDatum;
    @JsonProperty("inline_datum_json")
    private Map<String, Object> inlineDatumJson;
    @JsonProperty("reference_script_hash")
    private String referenceScriptHash;
    @JsonProperty("script_ref")
    private String scriptRef;
    @JsonProperty("block_number")
    private Long blockNumber;
    @JsonProperty("block_time")
    private Long blockTime;
    private Long slot;
    @JsonProperty("block_hash")
    private String blockHash;
    private Integer epoch;
    private Boolean collateral;
    private Boolean reference;
    @JsonProperty("consumed_by_tx")
    private String consumedByTx;
    @JsonProperty("is_collateral_return")
    private Boolean collateralReturn;
}
