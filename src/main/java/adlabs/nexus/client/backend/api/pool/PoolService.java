package adlabs.nexus.client.backend.api.pool;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.pool.model.*;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano stake-pool endpoints. */
public interface PoolService {

    Result<List<Pool>> getPools(Network network) throws ApiException;

    Result<Pool> getPool(Network network, String poolId) throws ApiException;

    Result<List<PoolRetirement>> getRetirements(Network network, int page, int pageSize) throws ApiException;

    Result<List<PoolRegistration>> getRegistrations(Network network, int page, int pageSize) throws ApiException;

    /** Per-epoch history for a single pool (replaces the legacy Koios pool-history call). */
    Result<PoolDetails> getPoolEpochHistory(Network network, String poolId, int epoch) throws ApiException;

    /** Full per-epoch history for a single pool. */
    Result<List<PoolHistory>> getPoolHistory(Network network, String poolId) throws ApiException;
}
