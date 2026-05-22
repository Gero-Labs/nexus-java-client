package adlabs.nexus.client.backend.api.block;

import adlabs.nexus.client.backend.api.block.model.Block;
import adlabs.nexus.client.backend.api.block.model.BlockSummary;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface BlockApi {

    @GET("api/blocks/latest")
    Call<Block> getLatestBlock(@Query("network") String network);

    @GET("api/blocks/{hash}")
    Call<Block> getBlock(@Path("hash") String hash, @Query("network") String network);

    @GET("api/blocks")
    Call<List<BlockSummary>> getBlocks(@Query("network") String network,
                                       @Query("page") Integer page,
                                       @Query("pageSize") Integer pageSize);
}
