package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.model.House;
import domain.model.Land;
import domain.model.Transaction;

public class TransactionPersistenceServiceImpl implements TransactionPersistenceService {
    private String url;
    private String user;
    private String pass;
    private Connection connection = null;

    public TransactionPersistenceServiceImpl(String host, String port, String db, String user, String pass) {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        this.user = user;
        this.pass = pass;
    }

    private void connect() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, user, pass);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    private void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.connection = null;
        }
    }

    @Override
    public void add(Transaction transaction) {
        this.connect();
        String sql = "INSERT INTO transaction (maGiaoDich, ngayGiaoDich, donGia, loaiDat, loaiNha, diaChi, dienTich) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, transaction.getMaGiaoDich());
            preparedStatement.setString(2, transaction.getNgayGiaoDich());
            preparedStatement.setInt(3, transaction.getDonGia());

            if (transaction.getClass().equals(House.class)) {
                preparedStatement.setString(4, "");
                preparedStatement.setString(5, transaction.getLoai());
                preparedStatement.setString(6, transaction.getDiaChi());
            } else if (transaction.getClass().equals(Land.class)) {
                preparedStatement.setString(4, transaction.getLoai());
                preparedStatement.setString(5, "");
                preparedStatement.setString(6, "");
            }

            preparedStatement.setDouble(7, transaction.getDienTich());
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.close();
    }

    @Override
    public void update(Transaction transaction) {
        this.connect();
        String sql = "UPDATE transaction SET ngayGiaoDich = ?, donGia = ?, loaiDat = ?, loaiNha = ?, diaChi = ?, dienTich = ? WHERE (maGiaoDich = ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, transaction.getNgayGiaoDich());
            preparedStatement.setInt(2, transaction.getDonGia());

            if (transaction.getClass().equals(House.class)) {
                preparedStatement.setString(3, "");
                preparedStatement.setString(4, transaction.getLoai());
                preparedStatement.setString(5, transaction.getDiaChi());
            } else if (transaction.getClass().equals(Land.class)) {
                preparedStatement.setString(3, transaction.getLoai());
                preparedStatement.setString(4, "");
                preparedStatement.setString(5, "");
            }
            
            preparedStatement.setDouble(6, transaction.getDienTich());
            preparedStatement.setInt(7, transaction.getMaGiaoDich());
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.close();
    }

    @Override
    public void delete(int id) {
        this.connect();
        String sql = "DELETE FROM transaction where (maGiaoDich = ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.close();
    }

    @Override
    public Transaction getTransactionByID(int id) {
        List<Transaction> transactions = this.getAllTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getMaGiaoDich() == id) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        this.connect();
        List<Transaction> transactions = new ArrayList<>();
        
        String sql = "select * from transaction;";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Transaction transaction;

            while(resultSet.next()) {
                if (!resultSet.getString("loaiDat").isEmpty()) {
                    transaction = new Land(
                        resultSet.getInt("maGiaoDich"), 
                        resultSet.getString("ngayGiaoDich"), 
                        resultSet.getInt("donGia"), 
                        resultSet.getString("loaiDat"), 
                        resultSet.getDouble("dienTich")
                    );
                } else {
                    transaction = new House(
                        resultSet.getInt("maGiaoDich"), 
                        resultSet.getString("ngayGiaoDich"), 
                        resultSet.getInt("donGia"), 
                        resultSet.getString("loaiNha"), 
                        resultSet.getString("diaChi"), 
                        resultSet.getDouble("dienTich")
                    );
                }
                
                transactions.add(transaction);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.close();
        return transactions;
    }
}
