package adlabs.nexus.client.backend.api.marketdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/** A token's market data from {@code /api/market/prices/{assetId}} (price, market cap, supply, etc.). */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketPrice {
    private String assetId;
    private String assetNameAscii;
    private String name;
    private String ticker;
    private Double priceAda;
    private Double priceUsd;
    private Double priceEur;
    private Double marketCap;
    private Double totalSupply;
    private Double circulatingSupply;
    private Double tvl;
    private Double volume24h;
    private Integer decimals;
    private String fingerprint;
    private Boolean verified;
    private String logo;
    private String updatedAt;
}
