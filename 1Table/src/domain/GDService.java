package domain;

import java.util.List;

import domain.model.GD;
import observer.Subscriber;

public interface GDService {
    public void add(GD gd);
    public void update(GD gd);
    public void delete(int id);

    public void findGD(int id);
    public GD findGetGD(int id);

    public void getAllGD();
    public List<GD> getData();

    public void addSub(Subscriber s);

    public GD getGD();
    public List<GD> getGDs();

    // thanhTien
    public void calcMoney(GD gd);
    public double getThanhTien();

    // tinhTongSoLuong
    public void sumCountGD(String loai);
    public int getSoLuong();

    // tinhTrungBinhThanhTien
    public void avgMoney();
    public double getAvgMoney();

    // export
    public void exportData(int month);
}
