package adlabs.nexus.client.backend.api.metadata;

import adlabs.nexus.client.backend.api.metadata.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface MetadataApi {

    @GET("api/transactions/{txHash}/metadata")
    Call<List<TxMetadataJson>> getTxMetadata(@Path("txHash") String txHash,
                                             @Query("network") String network);

    @GET("api/transactions/{txHash}/metadata/cbor")
    Call<List<TxMetadataCbor>> getTxMetadataCbor(@Path("txHash") String txHash,
                                                 @Query("network") String network);

    @GET("api/metadata/txs/labels")
    Call<List<MetadataLabel>> getMetadataLabels(@Query("network") String network,
                                                @Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/metadata/txs/labels/{label}")
    Call<List<LabelMetadataJson>> getMetadataByLabel(@Path("label") String label, @Query("network") String network,
                                                     @Query("page") int page, @Query("pageSize") int pageSize);

    @GET("api/metadata/txs/labels/{label}/cbor")
    Call<List<LabelMetadataCbor>> getMetadataCborByLabel(@Path("label") String label, @Query("network") String network,
                                                         @Query("page") int page, @Query("pageSize") int pageSize);
}
