package model;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietMuonDAO {

    // Thêm chi tiết mượn
    public boolean insertChiTietPhieuMuon(int maPhieu, int maSach, int soLuong, String tinhTrang) {
        String sql = "INSERT INTO ChiTietMuon(MaPhieu, MaSach, SoLuong, TinhTrang) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieu);
            ps.setInt(2, maSach);
            ps.setInt(3, soLuong);
            ps.setString(4, tinhTrang);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chi tiết phiếu mượn:");
            e.printStackTrace();
        }
        return false;
    }

    // Sửa chi tiết phiếu mượn: số lượng và tình trạng
    public boolean updateChiTietPhieuMuon(int maPhieu, int maSach, int soLuong, String tinhTrang) {
        String sql = "UPDATE ChiTietMuon SET SoLuong = ?, TinhTrang = ? WHERE MaPhieu = ? AND MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuong);  // Cập nhật số lượng sách
            ps.setString(2, tinhTrang);  // Cập nhật tình trạng sách
            ps.setInt(3, maPhieu);  // Mã phiếu mượn
            ps.setInt(4, maSach);  // Mã sách

            int rows = ps.executeUpdate();
            return rows > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa chi tiết phiếu mượn:");
            e.printStackTrace();
        }

        return false;  // Trả về false nếu có lỗi
    }

    public boolean updateTinhTrangChiTietMuon(int maPhieu, int maSach, String tinhTrang) {
    String sql = "UPDATE ChiTietMuon SET TinhTrang = ? WHERE MaPhieu = ? AND MaSach = ?";

    try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, tinhTrang);  // Cập nhật trạng thái sách
        ps.setInt(2, maPhieu);  // Mã phiếu mượn
        ps.setInt(3, maSach);  // Mã sách

        int rows = ps.executeUpdate();
        return rows > 0;  // Trả về true nếu cập nhật thành công
    } catch (SQLException e) {
        System.err.println("Lỗi khi sửa trạng thái chi tiết phiếu mượn:");
        e.printStackTrace();
    }

    return false;  // Trả về false nếu có lỗi
}

    // Xóa chi tiết phiếu mượn
    public boolean deleteChiTietPhieuMuon(int maPhieu, int maSach) {
        String sql = "DELETE FROM ChiTietMuon WHERE MaPhieu = ? AND MaSach = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieu);
            ps.setInt(2, maSach);

            int rows = ps.executeUpdate();
            return rows > 0;  // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa chi tiết phiếu mượn:");
            e.printStackTrace();
        }

        return false;  // Trả về false nếu có lỗi
    }

    // Lấy chi tiết phieu mượn trong một phiếu mượn
    public ChiTietMuon getChiTietByMaPhieu(int maPhieu) {
        String sql = "SELECT MaSach, SoLuong FROM ChiTietMuon WHERE MaPhieu = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiTietMuon ct = new ChiTietMuon();
                ct.setMaSach(rs.getInt("MaSach"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                return ct;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
