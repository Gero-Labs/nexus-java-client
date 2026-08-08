package adlabs.nexus.client.backend.api.metadata;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.metadata.model.*;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano transaction metadata endpoints. */
public interface MetadataService {

    Result<List<TxMetadataJson>> getTxMetadata(Network network, String txHash) throws ApiException;

    Result<List<TxMetadataCbor>> getTxMetadataCbor(Network network, String txHash) throws ApiException;

    /**
     * Lists known metadata labels. {@code cip10}/{@code count} are best-effort and may be null
     * depending on the underlying provider.
     */
    Result<List<MetadataLabel>> getMetadataLabels(Network network, int page, int pageSize) throws ApiException;

    /**
     * Looks up metadata by label. May return {@link Result#error} (5xx) on networks whose
     * provider doesn't support label indexing.
     */
    Result<List<LabelMetadataJson>> getMetadataByLabel(Network network, String label, int page, int pageSize)
            throws ApiException;

    Result<List<LabelMetadataCbor>> getMetadataCborByLabel(Network network, String label, int page, int pageSize)
            throws ApiException;
}
