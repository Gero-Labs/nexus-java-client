package adlabs.nexus.client.backend.api.transaction;

import adlabs.nexus.client.backend.api.transaction.model.*;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface TransactionApi {

    @GET("v1/transactions/{txHash}")
    Call<Transaction> getTransaction(@Path("txHash") String txHash,
                                     @Query("network") String network);

    @GET("v1/transactions/{txHash}/cbor")
    Call<TransactionCbor> getTransactionCbor(@Path("txHash") String txHash,
                                             @Query("network") String network);

    @POST("v1/transactions/cbor")
    Call<List<TransactionCbor>> getTransactionsCbor(@Query("network") String network,
                                                    @Body List<String> txHashes);

    @POST("v1/transactions/submit")
    Call<String> submitTransaction(@Query("network") String network,
                                   @Body RequestBody cborHex);

    @GET("v1/transactions/{txHash}/utxos")
    Call<TransactionUtxos> getTransactionUtxos(@Path("txHash") String txHash,
                                               @Query("network") String network);

    @POST("v1/transactions/utxos")
    Call<List<Utxo>> getTransactionsUtxos(@Query("network") String network,
                                          @Body List<UtxoRequestItem> items);
}
