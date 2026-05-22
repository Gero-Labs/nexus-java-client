package adlabs.nexus.client.backend.api.account;

import adlabs.nexus.client.backend.api.account.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AccountApi {

    @GET("api/account/{stakeAddress}/info")
    Call<AccountInformation> getAccountInfo(@Path("stakeAddress") String stakeAddress,
                                            @Query("network") String network);

    @GET("api/account/{stakeAddress}/addresses")
    Call<List<AccountAddress>> getAccountAddresses(@Path("stakeAddress") String stakeAddress,
                                                   @Query("network") String network);

    @GET("api/account/{stakeAddress}/rewards")
    Call<List<AccountRewardsHistory>> getAccountRewards(@Path("stakeAddress") String stakeAddress,
                                                        @Query("network") String network);

    @GET("api/account/{stakeAddress}/utxos")
    Call<List<AccountUtxo>> getAccountUtxos(@Path("stakeAddress") String stakeAddress,
                                            @Query("network") String network);

    @GET("api/account/{stakeAddress}/txs")
    Call<List<AccountTransaction>> getAccountTransactions(@Path("stakeAddress") String stakeAddress,
                                                          @Query("network") String network,
                                                          @Query("from") Integer fromBlockHeight);
}
