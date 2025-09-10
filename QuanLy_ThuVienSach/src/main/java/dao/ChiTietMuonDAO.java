package dao;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietMuon;
import model.PhieuMuon;

public class ChiTietMuonDAO {

    // Thêm phiếu mượn, chi tiết mượn
    public static boolean themPhieuMuonVaChiTiet(ChiTietMuon ct) {
        String sql = "{call sp_ThemPhieuMuonVaChiTiet(?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, ct.getPhieuMuon().getMaDocGia());
            cs.setInt(2, ct.getMaSach());
            cs.setInt(3, ct.getSoLuong());
            cs.setDate(4, new java.sql.Date(ct.getPhieuMuon().getNgayMuon().getTime()));
            cs.setDate(5, new java.sql.Date(ct.getPhieuMuon().getNgayHenTra().getTime()));

            cs.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Trả sách
    public static boolean traSach(int maPhieu, int maSach, java.util.Date ngayTra) {
        String sql = "{call sp_TraSach(?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, maPhieu);
            cs.setInt(2, maSach);

            if (ngayTra == null) {
                cs.setNull(3, java.sql.Types.DATE); // để SQL tự gán GETDATE()
            } else {
                cs.setDate(3, new java.sql.Date(ngayTra.getTime()));
            }

            cs.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //update phieu muon va chi tiet
    public static boolean suaChiTietMuon(ChiTietMuon ct) {
        String sql = "{CALL sp_SuaChiTietMuon(?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, ct.getPhieuMuon().getMaPhieu());
            cs.setInt(2, ct.getMaSach());
            cs.setInt(3, ct.getSoLuong());
            cs.setString(4, ct.getTinhTrang());

            // Xử lý NgayTraThucTe
            if (ct.getNgayTraThucTe() != null) {
                cs.setDate(5, new java.sql.Date(ct.getNgayTraThucTe().getTime()));
            } else {
                cs.setNull(5, java.sql.Types.DATE);
            }

            int rows = cs.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public static List<ChiTietMuon> getByTinhTrang(String tinhTrang) {
        List<ChiTietMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietMuon ct JOIN PhieuMuon pm ON ct.MaPhieu = pm.MaPhieu WHERE ct.TinhTrang = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tinhTrang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // map dữ liệu vào đối tượng ChiTietMuon + PhieuMuon
                ChiTietMuon ct = new ChiTietMuon();
                PhieuMuon pm = new PhieuMuon();

                pm.setMaPhieu(rs.getInt("MaPhieu"));
                pm.setMaDocGia(rs.getInt("MaDocGia"));
                pm.setNgayMuon(rs.getDate("NgayMuon"));
                pm.setNgayHenTra(rs.getDate("NgayHenTra"));

                ct.setPhieuMuon(pm);
                ct.setMaSach(rs.getInt("MaSach"));
                ct.setSoLuong(rs.getInt("SoLuong"));
                ct.setNgayTraThucTe(rs.getDate("NgayTraThucTe"));
                ct.setTinhTrang(rs.getString("TinhTrang"));

                list.add(ct);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
