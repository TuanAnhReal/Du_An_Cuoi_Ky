package model;

public class ChiTietMuon {
    private int maPhieu;      // FK
    private int maSach;       // FK
    private String tenSach;   // Để hiển thị
    private int soLuong;
    private String tinhTrang; // "Đang mượn", "Đã trả", "Mất sách"...

    public ChiTietMuon() {}

    public ChiTietMuon(int maPhieu, int maSach, String tenSach, int soLuong, String tinhTrang) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
