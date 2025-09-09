package model;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietMuonDAO {

    // Thêm chi tiết mượn
    public boolean insertChiTietMuon(ChiTietMuon ct) {
        String sql = "INSERT INTO ChiTietMuon(MaPhieu, MaSach, SoLuong, TinhTrang) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getMaPhieu());
            ps.setInt(2, ct.getMaSach());
            ps.setInt(3, ct.getSoLuong());
            ps.setString(4, ct.getTinhTrang());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chi tiết mượn:");
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách sách trong một phiếu mượn
    public List<ChiTietMuon> getChiTietByPhieu(int maPhieu) {
        List<ChiTietMuon> list = new ArrayList<>();
        String sql = "SELECT ct.MaPhieu, ct.MaSach, s.TenSach, ct.SoLuong, ct.TinhTrang " +
                     "FROM ChiTietMuon ct JOIN Sach s ON ct.MaSach = s.MaSach WHERE ct.MaPhieu = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhieu);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietMuon ct = new ChiTietMuon(
                            rs.getInt("MaPhieu"),
                            rs.getInt("MaSach"),
                            rs.getString("TenSach"),
                            rs.getInt("SoLuong"),
                            rs.getString("TinhTrang")
                    );
                    list.add(ct);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết mượn:");
            e.printStackTrace();
        }
        return list;
    }
}
