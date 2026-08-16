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
    Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                            int fromBlockHeight) throws ApiException;

    /** Transactions in the [fromBlockHeight, toBlockHeight] block range (null to = no cutoff),
     *  ordered by {@code order} ("asc"/"desc"). */
    Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                            int fromBlockHeight, Integer toBlockHeight,
                                                            String order) throws ApiException;
}
