package domain;

import java.util.List;

import domain.model.Transaction;
import observer.Subscriber;

public interface TransactionService {
    public void add(Transaction transaction);
    public void update(Transaction transaction);
    public void delete(int id);

    public void find(int id);
    public Transaction findGetTransaction(int id);

    public void getAllTransactions();
    public List<Transaction> getData();

    public void addSub(Subscriber s);

    public Transaction getTransaction();
    public List<Transaction> getTransactions();

    // thanhTien
    public void calcMoney(Transaction transaction);
    public double getThanhTien();

    // tinhTongloai
    public void sumCountTransaction(String loai);
    public int getCountTransaction();

    // tinhTrungBinhThanhTien
    public void avgMoneyHouse();
    public void avgMoneyLand();
    public double getAvgMoney();

    // export
    public void exportData(int month);
}
