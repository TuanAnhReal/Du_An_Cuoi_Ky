/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class TacGia {
    private int maTacGia;
    private String tenTacGia;
    private Date ngaySinh;
    private byte[] anhChanDung;

    public TacGia(int maTacGia, String tenTacGia, Date ngaySinh) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.ngaySinh = ngaySinh;
    }

    public TacGia(int maTacGia, String tenTacGia, Date ngaySinh, byte[] anhChanDung) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.ngaySinh = ngaySinh;
        this.anhChanDung = anhChanDung;
    }

    public TacGia(String tenTacGia, Date ngaySinh, byte[] anhChanDung) {
        this.tenTacGia = tenTacGia;
        this.ngaySinh = ngaySinh;
        this.anhChanDung = anhChanDung;
    }

    public TacGia(String tenTacGia, Date ngaySinh) {
        this.tenTacGia = tenTacGia;
        this.ngaySinh = ngaySinh;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public byte[] getAnhChanDung() {
        return anhChanDung;
    }

    public void setAnhChanDung(byte[] anhChanDung) {
        this.anhChanDung = anhChanDung;
    }

    @Override
    public String toString() {
        return "TacGia{" + "maTacGia=" + maTacGia + ", tenTacGia=" + tenTacGia + ", ngaySinh=" + ngaySinh + ", anhChanDung=" + anhChanDung + '}';
    }
    
}
