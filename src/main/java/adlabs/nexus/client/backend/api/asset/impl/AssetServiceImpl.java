package adlabs.nexus.client.backend.api.asset.impl;

import adlabs.nexus.client.backend.api.asset.AssetApi;
import adlabs.nexus.client.backend.api.asset.AssetService;
import adlabs.nexus.client.backend.api.asset.model.AssetDetailedInformation;
import adlabs.nexus.client.backend.api.asset.model.AssetHolder;
import adlabs.nexus.client.backend.api.asset.model.PaymentAddress;
import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;
import java.util.Set;

public class AssetServiceImpl implements AssetService {

    private final AssetApi api;

    public AssetServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(AssetApi.class);
    }

    @Override
    public Result<AssetDetailedInformation> getAssetDetailedInformation(Network network, String assetPolicy,
                                                                        String assetName) throws ApiException {
        return ApiUtil.process(api.getDetailedInfo(network.queryValue(), assetPolicy, assetName));
    }

    @Override
    public Result<Set<String>> getBlacklist() throws ApiException {
        return ApiUtil.process(api.getBlacklist());
    }

    @Override
    public Result<List<PaymentAddress>> getNftAddress(Network network, String assetPolicy,
                                                      String assetName) throws ApiException {
        return ApiUtil.process(api.getNftAddress(network.queryValue(), assetPolicy, assetName));
    }

    @Override
    public Result<List<AssetHolder>> getAssetHolders(Network network, String unit, int page,
                                                     int pageSize) throws ApiException {
        return ApiUtil.process(api.getAssetHolders(unit, network.queryValue(), page, pageSize));
    }
}
