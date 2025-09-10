package model;

import java.sql.Date;

public class ChiTietMuon {

    private int maPhieu;      // FK
    private PhieuMuon phieuMuon;
    private int maSach;       // FK
    private int soLuong;
    private String tinhTrang; // "Đang mượn", "Đã trả", "Mất sách"...
    private Date ngayTraThucTe;



    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public ChiTietMuon() {
    }

    public ChiTietMuon(PhieuMuon phieuMuon, int maSach, int soLuong, String tinhTrang, Date ngayTraThucTe) {
        this.phieuMuon = phieuMuon;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public ChiTietMuon(int maSach, int soLuong, String tinhTrang, Date ngayTraThucTe) {
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
        this.ngayTraThucTe = ngayTraThucTe;
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

    public Date getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public void setNgayTraThucTe(Date ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

}
