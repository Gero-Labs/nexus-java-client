package adlabs.nexus.client.backend.api.metadata;

import adlabs.nexus.client.backend.api.metadata.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetadataModelMappingTest {
    private final ObjectMapper om = new ObjectMapper();

    @Test
    void labelMetadataJson_mapsCamelCase() throws Exception {
        LabelMetadataJson d = om.readValue(
            "{\"txHash\":\"abc\",\"json\":{\"msg\":[\"hi\"]}}", LabelMetadataJson.class);
        assertEquals("abc", d.getTxHash());
        assertEquals("hi", d.getJson().get("msg").get(0).asText());
    }

    @Test
    void metadataLabel_mapsAllFields() throws Exception {
        MetadataLabel d = om.readValue(
            "{\"label\":\"674\",\"cip10\":\"CIP-20\",\"count\":\"5\"}", MetadataLabel.class);
        assertEquals("674", d.getLabel());
        assertEquals("CIP-20", d.getCip10());
        assertEquals(Long.valueOf(5), d.getCount());
    }

    @Test
    void txMetadataJson_mapsCamelCase() throws Exception {
        TxMetadataJson d = om.readValue(
            "{\"label\":\"123\",\"json\":{\"data\":\"test\"}}", TxMetadataJson.class);
        assertEquals("123", d.getLabel());
        assertEquals("test", d.getJson().get("data").asText());
    }

    @Test
    void txMetadataCbor_mapsCamelCase() throws Exception {
        TxMetadataCbor d = om.readValue(
            "{\"label\":\"456\",\"cbor\":\"d8799f\"}", TxMetadataCbor.class);
        assertEquals("456", d.getLabel());
        assertEquals("d8799f", d.getCbor());
    }

    @Test
    void labelMetadataCbor_mapsCamelCase() throws Exception {
        LabelMetadataCbor d = om.readValue(
            "{\"txHash\":\"def\",\"cbor\":\"a1\"}", LabelMetadataCbor.class);
        assertEquals("def", d.getTxHash());
        assertEquals("a1", d.getCbor());
    }
}
