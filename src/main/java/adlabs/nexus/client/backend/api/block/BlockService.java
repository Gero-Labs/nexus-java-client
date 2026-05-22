package adlabs.nexus.client.backend.api.block;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.block.model.Block;
import adlabs.nexus.client.backend.api.block.model.BlockSummary;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano block endpoints. */
public interface BlockService {

    Result<Block> getLatestBlock(Network network) throws ApiException;

    Result<Block> getBlock(Network network, String hash) throws ApiException;

    Result<List<BlockSummary>> getBlocks(Network network, int page, int pageSize) throws ApiException;
}
