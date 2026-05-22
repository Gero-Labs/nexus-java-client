package adlabs.nexus.client.backend.api.address.model;

import adlabs.nexus.client.backend.api.transaction.model.Utxo;
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
public class TransactionHistoryItem {
    private String txHash;
    private Long txTimestamp;
    @JsonProperty("block_epoch")
    private Long blockEpoch;
    private Integer txSize;
    @JsonProperty("block_hash")
    private String blockHash;
    @JsonProperty("block_height")
    private Long blockHeight;
    private String fee;
    @JsonProperty("total_output")
    private String totalOutput;
    private String deposit;
    @JsonProperty("sent_amount")
    private String sentAmount;
    @JsonProperty("received_amount")
    private String receivedAmount;
    @JsonProperty("sent_assets")
    private List<AssetAmount> sentAssets;
    @JsonProperty("received_assets")
    private List<AssetAmount> receivedAssets;
    private List<Utxo> inputs;
    private List<Utxo> outputs;
    private JsonNode metadata;
}
