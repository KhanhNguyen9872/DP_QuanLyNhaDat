package domain.model;

import java.io.Serializable;

public class House extends QuanLy implements Serializable {
    private String loaiNha;
    private String diaChi;

    public House(int maGiaoDich, String ngayGiaoDich, int donGia, String loaiNha, String diaChi, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.loaiNha = loaiNha;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
    }

    public int getMaGiaoDich() {
        return this.maGiaoDich;
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

    public String getLoaiNha() {
        return loaiNha;
    }

    public void setLoaiNha(String loaiNha) {
        this.loaiNha = loaiNha;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
        String loaiNha = getLoaiNha();
        thanhTien = dienTich * donGia;

        if (!loaiNha.toLowerCase().equals("nhà cao cấp")) {
            thanhTien = thanhTien * 0.9;
        }
        return thanhTien;
    }
    
}
