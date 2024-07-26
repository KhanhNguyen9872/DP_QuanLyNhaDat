package persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Message;

import domain.model.GD;
import domain.model.House;
import domain.model.Land;

public class GDPersistenceServiceImpl implements GDPersistenceService {
    private String url;
    private String user;
    private String pass;
    private Connection connection = null;

    public GDPersistenceServiceImpl(String host, String port, String db, String user, String pass) {
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
    public void add(GD gd) {
        this.connect();
        String sql = "INSERT INTO gd (maGiaoDich, ngayGiaoDich, donGia, loaiNha, diaChi, dienTich) VALUES " +
            "(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, gd.getMaGiaoDich());
            preparedStatement.setString(2, gd.getNgayGiaoDich());
            preparedStatement.setInt(3, gd.getDonGia());
            preparedStatement.setString(4, gd.getLoaiNha());
            preparedStatement.setString(5, gd.getDiaChi());
            preparedStatement.setDouble(6, gd.getDienTich());
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.close();
    }

    @Override
    public void update(GD gd) {
        this.connect();
        String sql = "UPDATE gd SET ngayGiaoDich = ?, donGia = ?, loaiNha = ?, diaChi = ?, dienTich = ? WHERE (maGiaoDich = ?);";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setString(1, gd.getNgayGiaoDich());
            preparedStatement.setInt(2, gd.getDonGia());
            preparedStatement.setString(3, gd.getLoaiNha());
            preparedStatement.setString(4, gd.getDiaChi());
            preparedStatement.setDouble(5, gd.getDienTich());
            preparedStatement.setInt(6, gd.getMaGiaoDich());
            preparedStatement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.close();
    }

    @Override
    public void delete(int id) {
        this.connect();
        String sql = "DELETE FROM gd where (maGiaoDich = ?)";
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
    public GD getGDByID(int id) {
        List<GD> gds = this.getAllGDs();
        for (GD gd : gds) {
            if (gd.getMaGiaoDich() == id) {
                return gd;
            }
        }
        return null;
    }

    @Override
    public List<GD> getAllGDs() {
        this.connect();
        List<GD> gds = new ArrayList<>();
        
        String sql = "select * from gd;";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            GD gd;

            while(resultSet.next()) {
                if (resultSet.getString("loainha").isEmpty()) {
                    gd = new Land(
                        resultSet.getInt("maGiaoDich"), 
                        resultSet.getString("ngayGiaoDich"), 
                        resultSet.getInt("donGia"), 
                        resultSet.getString("loaiDat"), 
                        resultSet.getDouble("dienTich")
                    );
                } else {
                    gd = new House(
                        resultSet.getInt("maGiaoDich"), 
                        resultSet.getString("ngayGiaoDich"), 
                        resultSet.getInt("donGia"), 
                        resultSet.getString("loaiNha"), 
                        resultSet.getString("diaChi"), 
                        resultSet.getDouble("dienTich")
                    );
                }
                
                gds.add(gd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.close();
        return gds;
    }
}
