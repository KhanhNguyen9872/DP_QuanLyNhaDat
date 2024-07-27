package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import observer.Publisher;
import observer.Subscriber;
import domain.model.House;
import domain.model.Land;
import domain.model.Transaction;

import persistence.TransactionPersistenceService;

public class TransactionServiceImpl extends Publisher implements TransactionService {
    public TransactionPersistenceService persistenceService = null;
    public Transaction transaction = null;
    public List<Transaction> transactions = null;
    public double thanhTien = -1;
    public int soLuong = -1;
    public double avgMoney = -1;
    
    public TransactionServiceImpl(TransactionPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void changeState() {
        notifySubscribers();
    }

    @Override
    public Transaction getTransaction() {
        Transaction trans = this.transaction;
        this.transaction = null;
        return trans;
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> trans = this.transactions;
        this.transactions = null;
        return trans;
    }

    @Override
    public Transaction findGetTransaction(int id) {
        return this.persistenceService.getTransactionByID(id);
    }

    @Override
    public List<Transaction> getData() {
        return this.persistenceService.getAllTransactions();
    }

    @Override
    public void addSub(Subscriber s) {
        subscriber(s);
    }

    @Override
    public void add(Transaction transaction) {
        this.persistenceService.add(transaction);
        changeState();
    }

    @Override
    public void update(Transaction transaction) {
        this.persistenceService.update(transaction);
        changeState();
    }

    @Override
    public void delete(int id) {
        this.persistenceService.delete(id);
        changeState();
    }

    @Override
    public void find(int id) {
        this.transaction = this.persistenceService.getTransactionByID(id);
        changeState();
    }

    @Override
    public void getAllTransactions() {
        this.transactions = this.persistenceService.getAllTransactions();
        changeState();
    }

    @Override
    public void calcMoney(Transaction transaction) {
        this.thanhTien = transaction.getThanhTien();
        changeState();
    }

    @Override
    public double getThanhTien() {
        double p = this.thanhTien;
        this.thanhTien = -1;
        return p;
    }

    @Override
    public void sumCountTransaction(String loai) {
        int tong = 0;
        List<Transaction> listTransactions = this.persistenceService.getAllTransactions();

        for (Transaction transaction : listTransactions) {
            if (loai.toLowerCase().equals(transaction.getLoai().toLowerCase())) {
                tong = tong + 1;
            }   
        }

        this.soLuong = tong;
        changeState();
    }

    @Override
    public int getCountTransaction() {
        int p = this.soLuong;
        this.soLuong = -1;
        return p;
    }

    @Override
    public void avgMoneyHouse(List<Transaction> listTransactions) {
        double money = 0;
        int count = 0;
        for (Transaction transaction : listTransactions) {
            if (transaction.getClass().equals(House.class)) {
                money = money + transaction.getThanhTien();
                count = count + 1;
            }
        }

        this.avgMoney = (double)(money / count);
        changeState();
    }

    @Override
    public void avgMoneyLand(List<Transaction> listTransactions) {
        double money = 0;
        int count = 0;
        for (Transaction transaction : listTransactions) {
            if (transaction.getClass().equals(Land.class)) {
                money = money + transaction.getThanhTien();
                count = count + 1;
            }
        }

        this.avgMoney = (double)(money / count);
        changeState();
    }

    @Override
    public double getAvgMoney() {
        double p = this.avgMoney;
        this.avgMoney = -1;
        return p;
    }

    @Override
    public void exportData(int month) {
        if (month == 0) {
            this.getAllTransactions();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;

        List<Transaction> tmp = this.persistenceService.getAllTransactions();
        List<Transaction> result = new ArrayList<Transaction>();

        for (Transaction transaction : tmp) {
            date = LocalDate.parse(transaction.getNgayGiaoDich(), formatter);
            int m = date.getMonthValue();
            if (m == month) {
                result.add(transaction);
            }
        }

        this.transactions = result;
        changeState();
    }
}
