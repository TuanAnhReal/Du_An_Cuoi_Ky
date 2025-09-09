package model;

import java.util.Date;

public class PhieuMuon {

    private int maPhieu;
    private int maDocGia;
    private String tenDocGia;
    private Date ngayMuon;
    private Date ngayHenTra;

    public PhieuMuon(int maPhieu, int maDocGia, String tenDocGia, Date ngayMuon, Date ngayHenTra) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
    }

    public PhieuMuon(int maDocGia, String tenDocGia, Date ngayMuon, Date ngayHenTra) {
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(int maPhieu, int maDocGia, Date ngayMuon, Date ngayHenTra) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayHenTra() {
        return ngayHenTra;
    }

    public void setNgayHenTra(Date ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }

}
