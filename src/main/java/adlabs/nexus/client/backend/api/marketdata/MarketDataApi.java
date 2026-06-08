package adlabs.nexus.client.backend.api.marketdata;

import adlabs.nexus.client.backend.api.marketdata.model.Candle;
import adlabs.nexus.client.backend.api.marketdata.model.MarketPrice;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface MarketDataApi {

    @GET("api/prices/historical/candles")
    Call<List<Candle>> getHistoricalCandles(@Query("assetId") String assetId,
                                            @Query("resolution") String resolution,
                                            @Query("from") Long from);

    @GET("api/market/prices/{assetId}")
    Call<MarketPrice> getMarketPrice(@Path("assetId") String assetId);
}
