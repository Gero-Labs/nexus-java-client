package adlabs.nexus.client.backend.api.asset;

import adlabs.nexus.client.backend.api.asset.model.AssetDetailedInformation;
import adlabs.nexus.client.backend.api.asset.model.AssetHolder;
import adlabs.nexus.client.backend.api.asset.model.PaymentAddress;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

public interface AssetApi {

    @GET("api/assets/{unit}/holders")
    Call<List<AssetHolder>> getAssetHolders(@Path("unit") String unit, @Query("network") String network,
                                            @Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/assets/detailedInfo")
    Call<AssetDetailedInformation> getDetailedInfo(@Query("network") String network,
                                                   @Query("assetPolicy") String assetPolicy,
                                                   @Query("assetName") String assetName);

    @GET("api/assets/blacklist")
    Call<Set<String>> getBlacklist();

    @GET("api/assets/nft-address")
    Call<List<PaymentAddress>> getNftAddress(@Query("network") String network,
                                             @Query("assetPolicy") String assetPolicy,
                                             @Query("assetName") String assetName);
}
