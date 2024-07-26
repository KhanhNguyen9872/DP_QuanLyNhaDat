package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import observer.Publisher;
import observer.Subscriber;
import domain.model.GD;

import persistence.GDPersistenceService;

public class GDServiceImpl extends Publisher implements GDService {
    private GDPersistenceService persistenceService = null;
    private GD gd = null;
    private List<GD> gds = null;
    private double thanhTien = -1;
    private int soLuong = -1;
    private double avgMoney = -1;

    public GDServiceImpl(GDPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void changeState() {
        notifySubscribers();
    }

    @Override
    public GD getGD() {
        return this.gd;
    }

    @Override
    public List<GD> getGDs() {
        return this.gds;
    }

    @Override
    public GD findGetGD(int id) {
        return this.persistenceService.getGDByID(id);
    }

    @Override
    public List<GD> getData() {
        return this.persistenceService.getAllGDs();
    }

    @Override
    public void addSub(Subscriber s) {
        subscriber(s);
    }

    @Override
    public void add(GD gd) {
        this.persistenceService.add(gd);
        this.gd = null;
        this.gds = null;
        changeState();
    }

    @Override
    public void update(GD gd) {
        this.persistenceService.update(gd);
        this.gd = null;
        this.gds = null;
        changeState();
    }

    @Override
    public void delete(int id) {
        this.persistenceService.delete(id);
        this.gd = null;
        this.gds = null;
        changeState();
    }

    @Override
    public void findGD(int id) {
        this.gd = this.persistenceService.getGDByID(id);
        changeState();
    }

    @Override
    public void getAllGD() {
        this.gds = this.persistenceService.getAllGDs();
        changeState();
    }

    @Override
    public void calcMoney(GD gd) {
        this.thanhTien = gd.getThanhTien();
        changeState();
    }

    @Override
    public double getThanhTien() {
        double p = this.thanhTien;
        this.thanhTien = -1;
        return p;
    }

    @Override
    public void sumCountGD(String loaiNha) {
        int tong = 0;
        List<GD> listGDs = this.persistenceService.getAllGDs();

        for (GD gd : listGDs) {
            if (loaiNha.toLowerCase().equals(gd.getLoaiNha().toLowerCase())) {
                tong = tong + 1;
            }   
        }

        this.soLuong = tong;
        changeState();
    }

    @Override
    public int getSoLuong() {
        int p = this.soLuong;
        this.soLuong = -1;
        return p;
    }

    @Override
    public void avgMoney() {
        double money = 0;
        int count = 0;
        List<GD> listGDs = this.persistenceService.getAllGDs();
        for (GD gd : listGDs) {
            money = money + gd.getThanhTien();
            count = count + 1;
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
            this.getAllGD();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;

        List<GD> tmp = this.persistenceService.getAllGDs();
        List<GD> result = new ArrayList<GD>();

        for (GD gd : tmp) {
            date = LocalDate.parse(gd.getNgayGiaoDich(), formatter);
            int m = date.getMonthValue();
            if (m == month) {
                result.add(gd);
            }
        }

        this.gds = result;
        changeState();
    }
}
