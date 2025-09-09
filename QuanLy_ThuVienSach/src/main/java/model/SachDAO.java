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
        String sql = "SELECT s.MaSach, s.TenSach, s.MaTacGia, t.TenTacGia, s.NamXuatBan, s.TheLoai, s.SoLuong, s.AnhBia "
                + "FROM Sach s "
                + "JOIN TacGia t ON s.MaTacGia = t.MaTacGia";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sach s = new Sach(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getInt("MaTacGia"),
                        rs.getString("TenTacGia"),
                        rs.getInt("NamXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getInt("SoLuong")
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

    public boolean insertSach(Sach s) {
        String sql = "INSERT INTO Sach (TenSach, NamXuatBan, TheLoai, SoLuong, AnhBia, MaTacGia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getTenSach());
            ps.setInt(2, s.getNamxuatban());
            ps.setString(3, s.getTheLoai());
            ps.setInt(4, s.getSoLuong());

            if (s.getAnhBia() != null) {
                ps.setBytes(5, s.getAnhBia());
            } else {
                ps.setNull(5, java.sql.Types.VARBINARY);
            }

            ps.setInt(6, s.getMaTacGia()); // Phải có mã tác giả

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        s.setMaSach(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sách:");
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSach(Sach s) {
        String sql = "UPDATE Sach SET TenSach = ?, NamXuatBan = ?, TheLoai = ?, SoLuong = ?, AnhBia = ?, MaTacGia = ? WHERE MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getTenSach());
            ps.setInt(2, s.getNamxuatban());
            ps.setString(3, s.getTheLoai());
            ps.setInt(4, s.getSoLuong());

            if (s.getAnhBia() != null) {
                ps.setBytes(5, s.getAnhBia());
            } else {
                ps.setNull(5, java.sql.Types.VARBINARY);
            }

            ps.setInt(6, s.getMaTacGia());
            ps.setInt(7, s.getMaSach());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật sách:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSach(int maSach) {
        String sql = "DELETE FROM Sach WHERE MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maSach);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sách:");
            e.printStackTrace();
            return false;
        }
    }

    public List<Sach> searchSach(String keyword) {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT s.MaSach, s.TenSach, s.NamXuatBan, s.TheLoai, s.SoLuong, s.AnhBia, t.TenTacGia "
                + "FROM Sach s JOIN TacGia t ON s.MaTacGia = t.MaTacGia "
                + "WHERE s.TenSach COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sach s = new Sach(
                            rs.getString("TenSach"),
                            rs.getString("TenTacGia"),
                            rs.getInt("NamXuatBan"),
                            rs.getString("TheLoai"),
                            rs.getInt("SoLuong")
                    );

                    byte[] anh = rs.getBytes("AnhBia");
                    s.setAnhBia(anh);

                    list.add(s);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sách:");
            e.printStackTrace();
        }

        return list;
    }

    //Tìm theo Id
    public Sach getSachById(int id) {
        String sql = "SELECT s.TenSach, t.TenTacGia, s.NamXuatBan, s.TheLoai, s.SoLuong, s.AnhBia\n"
                + "FROM Sach s\n"
                + "JOIN TacGia t ON s.MaTacGia = t.MaTacGia\n"
                + "WHERE s.MaSach = ?;";
        Sach s = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Sach(
                            rs.getString("tensach"),
                            rs.getString("tentacgia"),
                            rs.getInt("namxuatban"),
                            rs.getString("theloai"),
                            rs.getInt("soluong")
                    );

                    byte[] anh = rs.getBytes("anhbia");
                    s.setAnhBia(anh);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy độc giả theo ID:");
            e.printStackTrace();
        }

        return s; // trả về null nếu không tìm thấy
    }

}
