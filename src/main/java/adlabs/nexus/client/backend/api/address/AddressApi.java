package adlabs.nexus.client.backend.api.address;

import adlabs.nexus.client.backend.api.address.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AddressApi {

    @GET("api/addresses/{address}")
    Call<AddressInfo> getAddressInfo(@Path("address") String address,
                                     @Query("network") String network);

    @GET("api/addresses/transactions/{address}")
    Call<List<AddressTransaction>> getAddressTransactions(@Path("address") String address,
                                                          @Query("network") String network,
                                                          @Query("page") Integer page,
                                                          @Query("pageSize") Integer pageSize,
                                                          @Query("fromBlockHeight") Integer fromBlockHeight,
                                                          @Query("toBlockHeight") Integer toBlockHeight,
                                                          @Query("order") String order);

    @GET("api/addresses/{address}/utxos")
    Call<List<AddressUtxo>> getAddressUtxos(@Path("address") String address,
                                            @Query("network") String network,
                                            @Query("page") Integer page,
                                            @Query("pageSize") Integer pageSize);

    @GET("api/addresses/{address}/utxos/{asset}")
    Call<List<AddressUtxo>> getAddressUtxosByAsset(@Path("address") String address,
                                                   @Path("asset") String asset,
                                                   @Query("network") String network,
                                                   @Query("page") Integer page,
                                                   @Query("pageSize") Integer pageSize);

    @GET("api/addresses/{address}/transactions/history")
    Call<TransactionHistoryResponse> getAddressTransactionHistory(@Path("address") String address,
                                                                  @Query("network") String network,
                                                                  @Query("page") Integer page,
                                                                  @Query("pageSize") Integer pageSize);
}
