package domain.model;

import java.io.Serializable;

public class Land extends QuanLy implements Serializable {
    private String loaiDat;

    public Land(int maGiaoDich, String ngayGiaoDich, int donGia, String loaiDat, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.loaiDat = loaiDat;
        this.dienTich = dienTich;
    }

    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(String ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getLoaiDat() {
        return loaiDat;
    }

    public void setLoaiDat(String loaiDat) {
        this.loaiDat = loaiDat;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getThanhTien() {
        double thanhTien = 0;

        Double dienTich = getDienTich();
        int donGia = getDonGia();
        String loaiDat = getLoaiDat();
        thanhTien = dienTich * donGia;

        if (loaiDat.toLowerCase().equals("a")) {
            thanhTien = thanhTien * 1.5;
        }

        return thanhTien;
    }
}
