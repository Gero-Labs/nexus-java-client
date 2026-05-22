package adlabs.nexus.client.backend.api.asset;

import adlabs.nexus.client.backend.api.asset.model.AssetDetailedInformation;
import adlabs.nexus.client.backend.api.asset.model.PaymentAddress;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;

import java.util.List;
import java.util.Set;

/** Cardano asset endpoints. */
public interface AssetService {

    /**
     * @param assetPolicy 56-hex-char policy id
     * @param assetName   hex-encoded asset name (may be empty)
     */
    Result<AssetDetailedInformation> getAssetDetailedInformation(Network network, String assetPolicy,
                                                                 String assetName) throws ApiException;

    /** Returns the policy-id blacklist. Not network-scoped. */
    Result<Set<String>> getBlacklist() throws ApiException;

    Result<List<PaymentAddress>> getNftAddress(Network network, String assetPolicy,
                                               String assetName) throws ApiException;
}
