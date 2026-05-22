package adlabs.nexus.client.backend.api.epoch;

import adlabs.nexus.client.backend.api.epoch.model.Epoch;
import adlabs.nexus.client.backend.api.epoch.model.ProtocolParams;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EpochApi {

    @GET("api/epoch/params")
    Call<ProtocolParams> getEpochParams(@Query("network") String network,
                                        @Query("epoch_no") Integer epochNo);

    @GET("api/epoch/latest")
    Call<Epoch> getLatestEpoch(@Query("network") String network);

    @GET("api/epoch/latest/parameters")
    Call<ProtocolParams> getLatestEpochParameters(@Query("network") String network);
}
