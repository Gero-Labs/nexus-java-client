package adlabs.nexus.client.backend.api.epoch;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.epoch.model.Epoch;
import adlabs.nexus.client.backend.api.epoch.model.ProtocolParams;
import adlabs.nexus.client.util.Network;

/** Cardano epoch endpoints. */
public interface EpochService {

    Result<ProtocolParams> getEpochParams(Network network, int epochNo) throws ApiException;

    Result<Epoch> getLatestEpoch(Network network) throws ApiException;

    Result<ProtocolParams> getLatestEpochParameters(Network network) throws ApiException;
}
