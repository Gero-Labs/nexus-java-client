package adlabs.nexus.client.backend.api.marketdata;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.marketdata.model.Candle;
import adlabs.nexus.client.backend.api.marketdata.model.MarketPrice;

import java.util.List;

/**
 * Market data endpoints (token prices, market cap, OHLCV candles). These proxy the upstream
 * cardano-market-data service; the backing Nexus API key must have market-data read access, and
 * data is served for the key's network.
 */
public interface MarketDataService {

    /** OHLCV candles for a token. {@code from} is a unix-seconds lower bound (nullable). */
    Result<List<Candle>> getHistoricalCandles(String assetId, String resolution, Long from) throws ApiException;

    /** Current market data (price, market cap, supply) for a single token. */
    Result<MarketPrice> getMarketPrice(String assetId) throws ApiException;
}
