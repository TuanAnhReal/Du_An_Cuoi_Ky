package model;

public class ChiTietMuon {
    private int maPhieu;      // FK
    private int maSach;       // FK
    private int soLuong;
    private String tinhTrang; // "Đang mượn", "Đã trả", "Mất sách"...

    public ChiTietMuon() {}
private PhieuMuon phieuMuon;

public ChiTietMuon(PhieuMuon phieuMuon, int maSach, int soLuong, String tinhTrang) {
    this.phieuMuon = phieuMuon;
    this.maSach = maSach;
    this.soLuong = soLuong;
    this.tinhTrang = tinhTrang;
}

    public ChiTietMuon(int maPhieu, int maSach, int soLuong, String tinhTrang) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
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
