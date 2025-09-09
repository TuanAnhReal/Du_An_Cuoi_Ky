package model;

import java.util.Date;
import java.util.List;

public class PhieuMuon {
    private int maPhieu;           
    private int maDocGia;          
    private String tenDocGia;      
    private Date ngayMuon;        
    private Date ngayTra;   
    private Date hanTra;
    private String trangThai;      
    
    // Danh sách chi tiết mượn
    private List<ChiTietMuon> danhSachSach;

    // Constructor rỗng
    public PhieuMuon() {}

    public PhieuMuon(int maPhieu, String tenDocGia, Date ngayMuon, Date ngayTra, Date hanTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public PhieuMuon(int maPhieu, int maDocGia, Date ngayMuon, Date ngayTra, Date hanTra) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.hanTra = hanTra;
    }

    public PhieuMuon(int maPhieu, int maDocGia, Date ngayMuon, Date ngayTra) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public PhieuMuon(int maPhieu, int maDocGia, String tenDocGia, Date ngayMuon, Date ngayTra, Date hanTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public PhieuMuon(int maPhieu, int maDocGia, Date ngayMuon, Date ngayTra, Date hanTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
    }

    public Date getHanTra() {
        return hanTra;
    }

    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
    }

    public PhieuMuon(int maPhieu, int maDocGia, String tenDocGia, Date ngayMuon, Date ngayTra, String trangThai, List<ChiTietMuon> danhSachSach) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.danhSachSach = danhSachSach;
    }

    // Constructor đầy đủ
    public PhieuMuon(int maPhieu, int maDocGia, String tenDocGia, Date ngayMuon, Date ngayTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    // Getter & Setter
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

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<ChiTietMuon> getDanhSachSach() {
        return danhSachSach;
    }

    public void setDanhSachSach(List<ChiTietMuon> danhSachSach) {
        this.danhSachSach = danhSachSach;
    }
}
