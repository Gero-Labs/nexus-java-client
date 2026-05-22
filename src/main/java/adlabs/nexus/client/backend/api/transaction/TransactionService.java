package adlabs.nexus.client.backend.api.transaction;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.transaction.model.*;
import adlabs.nexus.client.util.Network;

import java.util.List;

/** Cardano transaction endpoints. */
public interface TransactionService {

    Result<Transaction> getTransaction(Network network, String txHash) throws ApiException;

    Result<TransactionCbor> getTransactionCbor(Network network, String txHash) throws ApiException;

    Result<List<TransactionCbor>> getTransactionsCbor(Network network, List<String> txHashes) throws ApiException;

    /**
     * Submits a signed transaction.
     *
     * @param cborHex hex-encoded serialized signed transaction CBOR
     * @return on success, {@code Result.value} is the submitted transaction hash
     */
    Result<String> submitTransaction(Network network, String cborHex) throws ApiException;

    Result<TransactionUtxos> getTransactionUtxos(Network network, String txHash) throws ApiException;

    Result<List<Utxo>> getTransactionsUtxos(Network network, List<UtxoRequestItem> items) throws ApiException;
}
