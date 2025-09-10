package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuMuon {

    private int maPhieu;
    private int maDocGia;
    private Date ngayMuon;
    private Date ngayHenTra;

    private List<ChiTietMuon> chiTietList = new ArrayList<>();

    public void addChiTiet(ChiTietMuon chiTiet) {
        chiTiet.setPhieuMuon(this);
        chiTietList.add(chiTiet);
    }
    
    public PhieuMuon(int maDocGia, Date ngayMuon, Date ngayHenTra) {
        this.maDocGia = maDocGia;
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
