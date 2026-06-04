package adlabs.nexus.client.backend.api.network.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.network.NetworkApi;
import adlabs.nexus.client.backend.api.network.NetworkService;
import adlabs.nexus.client.backend.api.network.model.NetworkInfo;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

public class NetworkServiceImpl implements NetworkService {

    private final NetworkApi api;

    public NetworkServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(NetworkApi.class);
    }

    @Override
    public Result<NetworkInfo> getNetworkInfo(Network network) throws ApiException {
        return ApiUtil.process(api.getNetworkInfo(network.queryValue()));
    }
}
