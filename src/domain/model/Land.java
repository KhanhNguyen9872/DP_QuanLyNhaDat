package domain.model;

import java.io.Serializable;

public class Land extends Transaction implements Serializable {
    
    public Land(int maGiaoDich, String ngayGiaoDich, int donGia, String loaiDat, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.loai = loaiDat;
        this.dienTich = dienTich;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loaiDat) {
        this.loai = loaiDat;
    }

    @Override
    public double getThanhTien() {
        double thanhTien = 0;

        Double dienTich = getDienTich();
        int donGia = getDonGia();
        String loaiDat = getLoai();
        thanhTien = dienTich * donGia;

        if (loaiDat.toLowerCase().equals("a")) {
            thanhTien = thanhTien * 1.5;
        }

        return thanhTien;
    }
}
