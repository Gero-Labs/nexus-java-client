package adlabs.nexus.client.backend.api.block.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {
    private Long time;
    private Long height;
    private Long number;
    private String hash;
    private Long slot;
    private Integer epoch;
    private String era;
    @JsonProperty("epoch_slot")
    private Long epochSlot;
    @JsonProperty("slot_leader")
    private String slotLeader;
    private Integer size;
    @JsonProperty("tx_count")
    private Integer txCount;
    private String output;
    private String fees;
    @JsonProperty("block_vrf")
    private String blockVrf;
    @JsonProperty("op_cert")
    private String opCert;
    @JsonProperty("op_cert_counter")
    private Long opCertCounter;
    @JsonProperty("op_cert_kes_period")
    private Long opCertKesPeriod;
    @JsonProperty("op_cert_sigma")
    private String opCertSigma;
    @JsonProperty("previous_block")
    private String previousBlock;
    @JsonProperty("next_block")
    private String nextBlock;
    private Integer confirmations;
    @JsonProperty("issuer_vkey")
    private String issuerVkey;
    @JsonProperty("nonce_vrf")
    private Vrf nonceVrf;
    @JsonProperty("leader_vrf")
    private Vrf leaderVrf;
    @JsonProperty("vrf_result")
    private Vrf vrfResult;
    @JsonProperty("block_body_hash")
    private String blockBodyHash;
    @JsonProperty("protocol_version")
    private String protocolVersion;
}
