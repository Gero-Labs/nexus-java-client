package adlabs.nexus.client.backend.api.account;

import adlabs.nexus.client.backend.api.account.model.*;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano account (stake address) endpoints. */
public interface AccountService {

    Result<AccountInformation> getAccountInformation(Network network, String stakeAddress) throws ApiException;

    Result<List<AccountAddress>> getAccountAddresses(Network network, String stakeAddress) throws ApiException;

    Result<List<AccountRewardsHistory>> getAccountRewards(Network network, String stakeAddress) throws ApiException;

    Result<List<AccountUtxo>> getAccountUtxos(Network network, String stakeAddress) throws ApiException;

    /** {@code fromBlockHeight} is required by Nexus; pass {@code 1} for the full history. */
    /**
     * Native-asset balances held under a stake account, aggregated server-side across
     * all unspent UTxOs. Prefer this to summing {@link #getAccountUtxos} asset lists:
     * that costs wire bytes proportional to the number of UTxOs for a figure the
     * server already computes.
     */
    Result<List<AccountAsset>> getAccountAssets(Network network, String stakeAddress,
                                                int page, int pageSize) throws ApiException;

    /** As above, restricted to a single policy id. */
    Result<List<AccountAsset>> getAccountAssets(Network network, String stakeAddress, String policyId,
                                                int page, int pageSize) throws ApiException;

    Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                            int fromBlockHeight) throws ApiException;

    /**
     * As above, with an upper block-height bound and an explicit ordering.
     *
     * <p>Without these a caller wanting one end of an account's history has to download
     * all of it. Pass {@code order} "asc" (oldest first) or "desc"; null leaves the
     * server default, which is ascending.
     */
    Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                            int fromBlockHeight, Integer toBlockHeight,
                                                            String order) throws ApiException;
}
