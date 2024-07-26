package persistence;

import java.util.List;

import domain.model.Transaction;

public interface TransactionPersistenceService {
    public void add(Transaction transaction);
    public void update(Transaction transaction);
    public void delete(int id);

    public Transaction getTransactionByID(int id);
    public List<Transaction> getAllTransactions();
}
