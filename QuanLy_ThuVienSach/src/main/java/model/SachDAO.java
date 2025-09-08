/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author PC
 */
public class SachDAO {
    
    //khởi tạo ds
    public static List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
            String sql = "SELECT s.MaSach, s.TenSach, s.MaTacGia, t.TenTacGia, s.NamXuatBan, s.TheLoai, s.SoLuong, s.AnhBia " +
                 "FROM Sach s " +
                 "JOIN TacGia t ON s.MaTacGia = t.MaTacGia";

        try (Connection conn = DBConnection.getConnection(); 
                Statement st = conn.createStatement(); 
                    ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getInt("MaTacGia"),
                        rs.getString("TenTacGia"),
                        rs.getInt("NamXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getString("SoLuong")
                        
                );

                // Lấy ảnh dạng byte[]
                byte[] anh = rs.getBytes("AnhBia");
                s.setAnhBia(anh);

                list.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách sách:");
            e.printStackTrace();
        }

        return list;
    }
    
    //thêm sách
        // Thêm sách mới
    public boolean insertSach(Sach s) {
        String sql = "INSERT INTO DocGia(HoTen, NgaySinh, DiaChi, SoDienThoai, Email, AnhChanDung) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getTenSach());


            if (s.getAnhBia()!= null) {
                ps.setBytes(6, s.getAnhBia());
            } else {
                ps.setNull(6, java.sql.Types.VARBINARY);
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        s.setMaSach(generatedKeys.getInt(1)); // Gán mã được sinh tự động
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm độc giả:");
            e.printStackTrace();
        }

        return false;
    }

}
