/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class TacGiaDAO {

    public static List<TacGia> getAll() {
        List<TacGia> list = new ArrayList<>();
        String sql = "SELECT * FROM TACGIA";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                TacGia tg = new TacGia(
                        rs.getInt("MaTacGia"),
                        rs.getString("TenTacGia"),
                        rs.getDate("NgaySinh")
                );
                // Lấy ảnh dạng byte[]
                byte[] anh = rs.getBytes("AnhChanDung");
                tg.setAnhChanDung(anh);

                list.add(tg);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách tác giả:");
            e.printStackTrace();
        }
        return list;
    }

    //them tac gia moi
    public boolean insertTacGia(TacGia tg) {
        //cau lenh sql
        String sql = "INSERT INTO TacGia(TenTacGia, NgaySinh, AnhChanDung) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tg.getTenTacGia());
            ps.setDate(2, new java.sql.Date(tg.getNgaySinh().getTime()));

            if (tg.getAnhChanDung() != null) {
                ps.setBytes(3, tg.getAnhChanDung());
            } else {
                ps.setNull(3, java.sql.Types.VARBINARY);
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tg.setMaTacGia(generatedKeys.getInt(1)); // Gán mã được sinh tự động
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm tác giả:");
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật thông tin độc giả
    public boolean updateTacGia(TacGia tg) {
        String sql = "UPDATE TacGia SET TenTacGia = ?, NgaySinh = ?, AnhChanDung = ? WHERE MaTacGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tg.getTenTacGia());
            ps.setDate(2, new java.sql.Date(tg.getNgaySinh().getTime()));
            
            // Xử lý ảnh
            if (tg.getAnhChanDung() != null) {
                ps.setBytes(3, tg.getAnhChanDung());
            } else {
                ps.setNull(3, java.sql.Types.BLOB); 
            }
            ps.setInt(4, tg.getMaTacGia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật tác giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa tac giả theo mã
    public boolean deleteTacGia(int MaTacGia) {
        String sql = "DELETE FROM TacGia WHERE MaTacGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, MaTacGia);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa tác giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Tìm tác giả theo tên
    public List<TacGia> searchTacGia(String keyword) {
        List<TacGia> list = new ArrayList<>();
        String sql = "SELECT * FROM TacGia WHERE TenTacGia LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TacGia tg = new TacGia(
                            rs.getInt("MaDocGia"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh")
                    );
                    list.add(tg);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    //Tìm theo Id
    public TacGia getTacGiaById(int id) {
        String sql = "SELECT * FROM TacGia WHERE MaTacGia = ?";
        TacGia tg = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tg = new TacGia(
                            rs.getInt("MaTacGia"),
                            rs.getString("TenTacGia"),
                            rs.getDate("NgaySinh")
                    );

                    byte[] anh = rs.getBytes("AnhChanDung");
                    tg.setAnhChanDung(anh);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy tác giả theo ID:");
            e.printStackTrace();
        }

        return tg; // trả về null nếu không tìm thấy
    }

}
