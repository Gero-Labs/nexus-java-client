package adlabs.nexus.client.backend.api.transaction.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.transaction.TransactionApi;
import adlabs.nexus.client.backend.api.transaction.TransactionService;
import adlabs.nexus.client.backend.api.transaction.model.*;
import adlabs.nexus.client.util.Network;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private static final MediaType TEXT_PLAIN = MediaType.parse("text/plain");

    private final TransactionApi api;

    public TransactionServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(TransactionApi.class);
    }

    @Override
    public Result<Transaction> getTransaction(Network network, String txHash) throws ApiException {
        return ApiUtil.process(api.getTransaction(txHash, network.queryValue()));
    }

    @Override
    public Result<TransactionCbor> getTransactionCbor(Network network, String txHash) throws ApiException {
        return ApiUtil.process(api.getTransactionCbor(txHash, network.queryValue()));
    }

    @Override
    public Result<List<TransactionCbor>> getTransactionsCbor(Network network, List<String> txHashes)
            throws ApiException {
        return ApiUtil.process(api.getTransactionsCbor(network.queryValue(), txHashes));
    }

    @Override
    public Result<String> submitTransaction(Network network, String cborHex) throws ApiException {
        RequestBody body = RequestBody.create(cborHex, TEXT_PLAIN);
        return ApiUtil.process(api.submitTransaction(network.queryValue(), body));
    }

    @Override
    public Result<TransactionUtxos> getTransactionUtxos(Network network, String txHash) throws ApiException {
        return ApiUtil.process(api.getTransactionUtxos(txHash, network.queryValue()));
    }

    @Override
    public Result<List<Utxo>> getTransactionsUtxos(Network network, List<UtxoRequestItem> items)
            throws ApiException {
        return ApiUtil.process(api.getTransactionsUtxos(network.queryValue(), items));
    }
}
