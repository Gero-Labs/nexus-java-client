package adlabs.nexus.client.backend.api.metadata.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.metadata.MetadataApi;
import adlabs.nexus.client.backend.api.metadata.MetadataService;
import adlabs.nexus.client.backend.api.metadata.model.*;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;

public class MetadataServiceImpl implements MetadataService {

    private final MetadataApi api;

    public MetadataServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(MetadataApi.class);
    }

    @Override
    public Result<List<TxMetadataJson>> getTxMetadata(Network network, String txHash) throws ApiException {
        return ApiUtil.process(api.getTxMetadata(txHash, network.queryValue()));
    }

    @Override
    public Result<List<TxMetadataCbor>> getTxMetadataCbor(Network network, String txHash) throws ApiException {
        return ApiUtil.process(api.getTxMetadataCbor(txHash, network.queryValue()));
    }

    @Override
    public Result<List<MetadataLabel>> getMetadataLabels(Network network, int page, int pageSize)
            throws ApiException {
        return ApiUtil.process(api.getMetadataLabels(network.queryValue(), page, pageSize));
    }

    @Override
    public Result<List<LabelMetadataJson>> getMetadataByLabel(Network network, String label, int page, int pageSize)
            throws ApiException {
        return ApiUtil.process(api.getMetadataByLabel(label, network.queryValue(), page, pageSize));
    }

    @Override
    public Result<List<LabelMetadataCbor>> getMetadataCborByLabel(Network network, String label, int page,
                                                                   int pageSize) throws ApiException {
        return ApiUtil.process(api.getMetadataCborByLabel(label, network.queryValue(), page, pageSize));
    }
}
