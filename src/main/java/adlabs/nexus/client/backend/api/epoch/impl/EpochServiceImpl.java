package adlabs.nexus.client.backend.api.epoch.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.epoch.EpochApi;
import adlabs.nexus.client.backend.api.epoch.EpochService;
import adlabs.nexus.client.backend.api.epoch.model.Epoch;
import adlabs.nexus.client.backend.api.epoch.model.ProtocolParams;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

public class EpochServiceImpl implements EpochService {

    private final EpochApi api;

    public EpochServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(EpochApi.class);
    }

    @Override
    public Result<ProtocolParams> getEpochParams(Network network, int epochNo) throws ApiException {
        return ApiUtil.process(api.getEpochParams(network.queryValue(), epochNo));
    }

    @Override
    public Result<Epoch> getLatestEpoch(Network network) throws ApiException {
        return ApiUtil.process(api.getLatestEpoch(network.queryValue()));
    }

    @Override
    public Result<ProtocolParams> getLatestEpochParameters(Network network) throws ApiException {
        return ApiUtil.process(api.getLatestEpochParameters(network.queryValue()));
    }
}
