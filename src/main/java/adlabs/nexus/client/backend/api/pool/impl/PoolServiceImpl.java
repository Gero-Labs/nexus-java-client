package adlabs.nexus.client.backend.api.pool.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.pool.PoolApi;
import adlabs.nexus.client.backend.api.pool.PoolService;
import adlabs.nexus.client.backend.api.pool.model.*;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;

public class PoolServiceImpl implements PoolService {

    private final PoolApi api;

    public PoolServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(PoolApi.class);
    }

    @Override
    public Result<List<Pool>> getPools(Network network) throws ApiException {
        return ApiUtil.process(api.getPools(network.queryValue()));
    }

    @Override
    public Result<Pool> getPool(Network network, String poolId) throws ApiException {
        return ApiUtil.process(api.getPool(poolId, network.queryValue()));
    }

    @Override
    public Result<List<PoolRetirement>> getRetirements(Network network, int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getRetirements(network.queryValue(), page, pageSize));
    }

    @Override
    public Result<List<PoolRegistration>> getRegistrations(Network network, int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getRegistrations(network.queryValue(), page, pageSize));
    }

    @Override
    public Result<PoolDetails> getPoolEpochHistory(Network network, String poolId, int epoch) throws ApiException {
        return ApiUtil.process(api.getPoolEpochHistory(poolId, epoch, network.queryValue()));
    }

    @Override
    public Result<List<PoolHistory>> getPoolHistory(Network network, String poolId) throws ApiException {
        return ApiUtil.process(api.getPoolHistory(poolId, network.queryValue()));
    }
}
