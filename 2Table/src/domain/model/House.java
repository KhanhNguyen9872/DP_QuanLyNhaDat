package domain.model;

import java.io.Serializable;

public class House extends QuanLy implements Serializable {
    private String diaChi;

    public House(int maGiaoDich, String ngayGiaoDich, int donGia, String loaiNha, String diaChi, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.loai = loaiNha;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public double getThanhTien() {
        double thanhTien = 0;

        Double dienTich = getDienTich();
        int donGia = getDonGia();
        String loaiNha = getLoai();
        thanhTien = dienTich * donGia;

        if (!loaiNha.toLowerCase().equals("nhà cao cấp")) {
            thanhTien = thanhTien * 0.9;
        }
        return thanhTien;
    }
    
}
