package dao;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietMuon;
import model.PhieuMuon;

public class PhieuMuonDAO {

    public static List<ChiTietMuon> getAll() {
        List<ChiTietMuon> list = new ArrayList<>();

        String sql = "SELECT pm.MaPhieu, pm.MaDocGia, ctm.MaSach, ctm.SoLuong, ctm.TinhTrang, "
                + "pm.NgayMuon, ctm.NgayTraThucTe, pm.NgayHenTra "
                + "FROM PhieuMuon pm JOIN ChiTietMuon ctm ON pm.MaPhieu = ctm.MaPhieu;";

        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Tạo object PhieuMuon
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieu(rs.getInt(1));       // MaPhieu
                pm.setMaDocGia(rs.getInt(2));      // MaDocGia
                pm.setNgayMuon(rs.getDate(6));     // NgayMuon
                pm.setNgayHenTra(rs.getDate(8));   // NgayHenTra

                // Tạo ChiTietMuon và gán phiếu mượn
                ChiTietMuon ct = new ChiTietMuon();
                ct.setPhieuMuon(pm);
                ct.setMaSach(rs.getInt(3));        // MaSach
                ct.setSoLuong(rs.getInt(4));       // SoLuong
                ct.setTinhTrang(rs.getString(5));  // TinhTrang
                ct.setNgayTraThucTe(rs.getDate(7));// NgayTraThucTe

                // Thêm vào list
                list.add(ct);

            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách chi tiết mượn:");
            e.printStackTrace();
        }

        return list;
    }

    //lấy tất cả mã phiếu trong phiếu mượn
    public static List<Integer> getAllMaPhieu() {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT MaPhieu FROM PhieuMuon";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt("MaPhieu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //lọc theo mã phiếu
    public static List<ChiTietMuon> getByMaPhieu(int maPhieu) {
        List<ChiTietMuon> list = new ArrayList<>();
        String sql = "SELECT pm.MaPhieu, pm.MaDocGia, pm.NgayMuon, pm.NgayHenTra, "
                + "ct.MaSach, ct.SoLuong, ct.TinhTrang, ct.NgayTraThucTe "
                + "FROM PhieuMuon pm "
                + "JOIN ChiTietMuon ct ON pm.MaPhieu = ct.MaPhieu "
                + "WHERE pm.MaPhieu = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieu(rs.getInt("MaPhieu"));
                pm.setMaDocGia(rs.getInt("MaDocGia"));
                pm.setNgayMuon(rs.getDate("NgayMuon"));
                pm.setNgayHenTra(rs.getDate("NgayHenTra"));

                ChiTietMuon ct = new ChiTietMuon();
                ct.setPhieuMuon(pm);
                ct.setMaSach(rs.getInt("MaSach"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setTinhTrang(rs.getString("TinhTrang"));
                ct.setNgayTraThucTe(rs.getDate("NgayTraThucTe"));

                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
