package adlabs.nexus.client.backend.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private String txHash;
    @JsonProperty("block_hash")
    private String blockHash;
    @JsonProperty("block_height")
    private Long blockHeight;
    @JsonProperty("absolute_slot")
    private Long absoluteSlot;
    private Long txTimestamp;
    private Integer txSize;
    @JsonProperty("total_output")
    private String totalOutput;
    private String fee;
    private String deposit;
    @JsonProperty("invalid_before")
    private String invalidBefore;
    @JsonProperty("invalid_after")
    private String invalidAfter;
    @JsonProperty("reference_inputs")
    private List<TxIO> referenceInputs;
    private List<TxIO> inputs;
    private List<TxIO> outputs;
    private List<TxWithdrawal> withdrawals;
    @JsonProperty("assets_minted")
    private List<TxAsset> assetsMinted;
    private JsonNode metadata;
    private List<TxCertificate> certificates;
    @JsonProperty("native_scripts")
    private List<TxNativeScript> nativeScripts;
    @JsonProperty("plutus_contracts")
    private List<TxPlutusContract> plutusContracts;
    @JsonProperty("block_epoch")
    private Long blockEpoch;
}
