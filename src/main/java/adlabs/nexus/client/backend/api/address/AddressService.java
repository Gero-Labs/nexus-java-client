package adlabs.nexus.client.backend.api.address;

import adlabs.nexus.client.backend.api.address.model.*;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano address endpoints. */
public interface AddressService {

    Result<AddressInfo> getAddressInformation(Network network, String address) throws ApiException;

    Result<List<AddressTransaction>> getAddressTransactions(Network network, String address,
                                                            int page, int pageSize) throws ApiException;

    /** As {@link #getAddressTransactions(Network, String, int, int)} but only returns transactions
     *  at or after {@code fromBlockHeight}. A null {@code fromBlockHeight} applies no cutoff. */
    Result<List<AddressTransaction>> getAddressTransactions(Network network, String address,
                                                            int page, int pageSize,
                                                            Integer fromBlockHeight) throws ApiException;

    /** Transactions in the [fromBlockHeight, toBlockHeight] block range (null = no cutoff),
     *  ordered by {@code order} ("asc"/"desc"; default server-side is asc). */
    Result<List<AddressTransaction>> getAddressTransactions(Network network, String address,
                                                            int page, int pageSize,
                                                            Integer fromBlockHeight, Integer toBlockHeight,
                                                            String order) throws ApiException;

    Result<List<AddressUtxo>> getAddressUtxos(Network network, String address,
                                              int page, int pageSize) throws ApiException;

    Result<List<AddressUtxo>> getAddressUtxosByAsset(Network network, String address, String asset,
                                                     int page, int pageSize) throws ApiException;

    Result<TransactionHistoryResponse> getAddressTransactionHistory(Network network, String address,
                                                                    int page, int pageSize) throws ApiException;
}
