/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class DocGia {

    private int maDocGia;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private byte[] AnhChanDung;

    public DocGia() {
    }

    public DocGia(int maDocGia, String hoTen, String diaChi, String email) {
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.email = email;
    }

    public DocGia(String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email, byte[] AnhChanDung) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.AnhChanDung = AnhChanDung;
    }

    public DocGia(int maDocGia, String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email, byte[] AnhChanDung) {
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.AnhChanDung = AnhChanDung;
    }

    public DocGia(int maDocGia,String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email) {
        this.maDocGia = maDocGia;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public DocGia(String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String email) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public DocGia(String hoTen, Date ngaySinh, String diaChi, String email) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAnhChanDung() {
        return AnhChanDung;
    }

    public void setAnhChanDung(byte[] AnhChanDung) {
        this.AnhChanDung = AnhChanDung;
    }


    @Override
    public String toString() {
        return "DocGia{" + "maDocGia=" + maDocGia + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", email=" + email + '}';
    }
    
}
