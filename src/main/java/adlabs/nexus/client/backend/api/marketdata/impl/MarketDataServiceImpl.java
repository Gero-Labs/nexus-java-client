package adlabs.nexus.client.backend.api.marketdata.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.marketdata.MarketDataApi;
import adlabs.nexus.client.backend.api.marketdata.MarketDataService;
import adlabs.nexus.client.backend.api.marketdata.model.Candle;
import adlabs.nexus.client.backend.api.marketdata.model.MarketPrice;
import retrofit2.Retrofit;

import java.util.List;

public class MarketDataServiceImpl implements MarketDataService {

    private final MarketDataApi api;

    public MarketDataServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(MarketDataApi.class);
    }

    @Override
    public Result<List<Candle>> getHistoricalCandles(String assetId, String resolution, Long from) throws ApiException {
        return ApiUtil.process(api.getHistoricalCandles(assetId, resolution, from));
    }

    @Override
    public Result<MarketPrice> getMarketPrice(String assetId) throws ApiException {
        return ApiUtil.process(api.getMarketPrice(assetId));
    }
}
