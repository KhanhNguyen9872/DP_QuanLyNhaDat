package domain.model;

public abstract class GD {
    public int maGiaoDich;
    public String ngayGiaoDich;
    public int donGia;
    public double dienTich;
    public String loaiNha;
    public String loaiDat;
    public String diaChi;
    
    public String getLoaiNha() {
        return loaiNha;
    }


    public void setLoaiNha(String loaiNha) {
        this.loaiNha = loaiNha;
    }


    public String getLoaiDat() {
        return loaiDat;
    }


    public void setLoaiDat(String loaiDat) {
        this.loaiDat = loaiDat;
    }


    public String getDiaChi() {
        return diaChi;
    }


    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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


    public double getDienTich() {
        return dienTich;
    }


    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }


    public abstract double getThanhTien();
}
