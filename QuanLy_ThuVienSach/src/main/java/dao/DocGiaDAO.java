/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DocGia;
import util.DBConnection;

public class DocGiaDAO {

    public static List<DocGia> getAll() {
        List<DocGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DocGia";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); 
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DocGia dg = new DocGia(
                        rs.getInt("MaDocGia"),
                        rs.getString("HoTen"),
                        rs.getDate("NgaySinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SoDienThoai"),
                        rs.getString("Email")
                );

                // Lấy ảnh dạng byte[]
                byte[] anh = rs.getBytes("AnhChanDung");
                dg.setAnhChanDung(anh);

                list.add(dg);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    // Thêm độc giả mới
    public boolean insertDocGia(DocGia dg) {
        String sql = "INSERT INTO DocGia(HoTen, NgaySinh, DiaChi, SoDienThoai, Email, AnhChanDung) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dg.getHoTen());
            ps.setDate(2, new java.sql.Date(dg.getNgaySinh().getTime()));
            ps.setString(3, dg.getDiaChi());
            ps.setString(4, dg.getSoDienThoai());
            ps.setString(5, dg.getEmail());

            if (dg.getAnhChanDung() != null) {
                ps.setBytes(6, dg.getAnhChanDung());
            } else {
                ps.setNull(6, java.sql.Types.VARBINARY);
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dg.setMaDocGia(generatedKeys.getInt(1)); // Gán mã được sinh tự động
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

    // Cập nhật thông tin độc giả
    public boolean updateDocGia(DocGia dg) {
        String sql = "UPDATE DocGia SET HoTen = ?, NgaySinh = ?, DiaChi = ?, SoDienThoai = ?, Email = ?, AnhChanDung = ? WHERE maDocGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dg.getHoTen());
            ps.setDate(2, new java.sql.Date(dg.getNgaySinh().getTime()));
            ps.setString(3, dg.getDiaChi());
            ps.setString(4, dg.getSoDienThoai());
            ps.setString(5, dg.getEmail());
            // Xử lý ảnh
            if (dg.getAnhChanDung() != null) {
                ps.setBytes(6, dg.getAnhChanDung());
            } else {
                ps.setNull(6, java.sql.Types.BLOB);
            }
            ps.setInt(7, dg.getMaDocGia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật độc giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa độc giả theo mã
    public boolean deleteDocGia(int maDocGia) {
        String sql = "DELETE FROM DocGia WHERE maDocGia = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDocGia);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa độc giả:");
            e.printStackTrace();
            return false;
        }
    }

    // Tìm độc giả theo tên
    public List<DocGia> searchDocGia(String keyword) {
        List<DocGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DocGia WHERE HoTen LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DocGia dg = new DocGia(
                            rs.getInt("MaDocGia"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getString("DiaChi"),
                            rs.getString("SoDienThoai"),
                            rs.getString("Email")
                    );
                    list.add(dg);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm độc giả:");
            e.printStackTrace();
        }

        return list;
    }

    //Tìm theo Id
    public DocGia getDocGiaById(int id) {
        String sql = "SELECT * FROM DocGia WHERE MaDocGia = ?";
        DocGia dg = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dg = new DocGia(
                            rs.getInt("MaDocGia"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getString("DiaChi"),
                            rs.getString("SoDienThoai"),
                            rs.getString("Email")
                    );

                    byte[] anh = rs.getBytes("AnhChanDung");
                    dg.setAnhChanDung(anh);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy độc giả theo ID:");
            e.printStackTrace();
        }

        return dg; // trả về null nếu không tìm thấy
    }
    //gọi đến thông tin độc giả trong piếu mượn
    public DocGia getDocGiaBasicById(int maDocGia) {
        String sql = "SELECT MaDocGia, HoTen, SoDienThoai, Email FROM DocGia WHERE MaDocGia = ?";
        DocGia dg = null;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDocGia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dg = new DocGia();
                    dg.setMaDocGia(rs.getInt("MaDocGia"));
                    dg.setHoTen(rs.getString("HoTen"));
                    dg.setSoDienThoai(rs.getString("sodienthoai"));
                    dg.setEmail(rs.getString("Email"));
                    return dg;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy thông tin độc giả (basic):");
            e.printStackTrace();
        }
        return null;
    }

}
