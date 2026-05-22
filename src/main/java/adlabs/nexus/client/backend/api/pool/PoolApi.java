package adlabs.nexus.client.backend.api.pool;

import adlabs.nexus.client.backend.api.pool.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface PoolApi {

    @GET("api/pools")
    Call<List<Pool>> getPools(@Query("network") String network);

    @GET("api/pools/{id}")
    Call<Pool> getPool(@Path("id") String id, @Query("network") String network);

    @GET("api/pools/retirements")
    Call<List<PoolRetirement>> getRetirements(@Query("network") String network,
                                              @Query("page") Integer page,
                                              @Query("pageSize") Integer pageSize);

    @GET("api/pools/registrations")
    Call<List<PoolRegistration>> getRegistrations(@Query("network") String network,
                                                  @Query("page") Integer page,
                                                  @Query("pageSize") Integer pageSize);

    @GET("api/pools/{poolId}/epochs/{epoch}")
    Call<PoolDetails> getPoolEpochHistory(@Path("poolId") String poolId,
                                          @Path("epoch") int epoch,
                                          @Query("network") String network);
}
