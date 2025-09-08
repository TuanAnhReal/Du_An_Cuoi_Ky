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
public class Sach {

    private int maSach;
    private String tenSach;
    private String tacGia;
    private Date namXB;
    private String theLoai;
    private String soLuong;
    private byte[] anhBia;
    private int maTacGia;

    public Sach(String tenSach, String tacGia, Date namXB, String theLoai, String soLuong, int maTacGia) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.maTacGia = maTacGia;
    }

    public Sach(int maSach, String tenSach, String tacGia, Date namXB, String theLoai, String soLuong, byte[] anhBia, int maTacGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
        this.maTacGia = maTacGia;
    }

    public Sach(String tenSach, String tacGia, Date namXB, String theLoai, String soLuong, byte[] anhBia, int maTacGia) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
        this.maTacGia = maTacGia;
    }

    public Sach(int maSach, String tenSach, String tacGia, Date namXB, String theLoai, String soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public Sach(String tenSach, String tacGia, Date namXB, String theLoai, String soLuong, byte[] anhBia) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(String tenSach, String tacGia, Date namXB, String theLoai, String soLuong) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXB = namXB;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Date getNamXB() {
        return namXB;
    }

    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public byte[] getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(byte[] anhBia) {
        this.anhBia = anhBia;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    @Override
    public String toString() {
        return "Sach{" + "tenSach=" + tenSach + ", tacGia=" + tacGia + ", namXB=" + namXB + ", theLoai=" + theLoai + ", soLuong=" + soLuong + '}';
    }
    
}
