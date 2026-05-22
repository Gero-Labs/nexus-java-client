package adlabs.nexus.client.backend.api.network;

import adlabs.nexus.client.backend.api.network.model.NetworkInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("api/network/info")
    Call<NetworkInfo> getNetworkInfo(@Query("network") String network);
}
