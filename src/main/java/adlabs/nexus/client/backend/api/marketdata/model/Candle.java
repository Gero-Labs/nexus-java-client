package adlabs.nexus.client.backend.api.marketdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/** A single OHLCV candle from the market-data {@code /api/prices/historical/candles} endpoint. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candle {
    private Long time;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
}
