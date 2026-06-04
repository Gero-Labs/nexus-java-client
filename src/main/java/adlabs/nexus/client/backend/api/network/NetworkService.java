package adlabs.nexus.client.backend.api.network;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.network.model.NetworkInfo;
import adlabs.nexus.client.util.Network;

/** Cardano network protocol parameter endpoints. */
public interface NetworkService {

    Result<NetworkInfo> getNetworkInfo(Network network) throws ApiException;
}
