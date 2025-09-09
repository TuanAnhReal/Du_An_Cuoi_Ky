/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Sach {

    private int maSach;
    private String tenSach;
    private int maTacGia;
    private String tenTacGia;
    private int namxuatban;
    private String theLoai;
    private int soLuong;
    private byte[] anhBia;

    public Sach(int maSach, String tenSach, int maTacGia, int namxuatban, String theLoai, int soLuong, byte[] anhBia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(String tenSach, int maTacGia, int namxuatban, String theLoai, int soLuong, byte[] anhBia) {
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(String tenSach, String tenTacGia, int namxuatban, String theLoai, int soLuong) {
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public Sach(String tenSach, String tenTacGia, int namxuatban, String theLoai, int soLuong, byte[] anhBia) {
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(int maSach, String tenSach, int maTacGia, String tenTacGia, int namxuatban, String theLoai, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public Sach(int maSach, String tenSach, int maTacGia, String tenTacGia, int namxuatban, String theLoai, int soLuong, byte[] anhBia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(String tenSach, int maTacGia, String tenTacGia, int namxuatban, String theLoai, int soLuong, byte[] anhBia) {
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(String tenSach, int maTacGia, String tenTacGia, int namxuatban, String theLoai, int soLuong) {
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
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

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public byte[] getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(byte[] anhBia) {
        this.anhBia = anhBia;
    }

    
}
