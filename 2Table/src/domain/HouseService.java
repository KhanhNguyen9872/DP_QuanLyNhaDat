package domain;

import java.util.List;

import domain.model.House;
import observer.Subscriber;

public interface HouseService {
    public void addHouse(House house);
    public void updateHouse(House house);
    public void deleteHouse(int id);

    public void findHouse(int id);
    public House findGetHouse(int id);

    public void getAllHouses();
    public List<House> getData();

    public void addSub(Subscriber s);

    public House getHouse();
    public List<House> getHouses();

    // thanhTien
    public void calcMoney(House house);
    public double getThanhTien();

    // tinhTongSoLuong
    public void sumCountHouse(String loaiNha);
    public int getSoLuong();

    // tinhTrungBinhThanhTien
    public void avgMoney();
    public double getAvgMoney();

    // export
    public void exportData(int month);
}
