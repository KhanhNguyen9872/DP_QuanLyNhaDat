package domain.model;

import java.io.Serializable;

public class House extends GD implements Serializable {

    public House(int maGiaoDich, String ngayGiaoDich, int donGia, String loaiNha, String diaChi, double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.donGia = donGia;
        this.loaiNha = loaiNha;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
    }

    @Override
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
