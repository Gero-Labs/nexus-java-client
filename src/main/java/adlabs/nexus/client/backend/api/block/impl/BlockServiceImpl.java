package adlabs.nexus.client.backend.api.block.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.block.BlockApi;
import adlabs.nexus.client.backend.api.block.BlockService;
import adlabs.nexus.client.backend.api.block.model.Block;
import adlabs.nexus.client.backend.api.block.model.BlockSummary;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;

public class BlockServiceImpl implements BlockService {

    private final BlockApi api;

    public BlockServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(BlockApi.class);
    }

    @Override
    public Result<Block> getLatestBlock(Network network) throws ApiException {
        return ApiUtil.process(api.getLatestBlock(network.queryValue()));
    }

    @Override
    public Result<Block> getBlock(Network network, String hash) throws ApiException {
        return ApiUtil.process(api.getBlock(hash, network.queryValue()));
    }

    @Override
    public Result<List<BlockSummary>> getBlocks(Network network, int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getBlocks(network.queryValue(), page, pageSize));
    }
}
