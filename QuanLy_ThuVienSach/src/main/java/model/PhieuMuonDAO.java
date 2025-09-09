package model;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {

    // Thêm phiếu mượn
    public int insertPhieuMuon(PhieuMuon pm) {
        String sql = "INSERT INTO PhieuMuon(MaDocGia, NgayMuon, NgayHenTra) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pm.getMaDocGia());
            ps.setDate(2, new java.sql.Date(pm.getNgayMuon().getTime()));
            ps.setDate(3, new java.sql.Date(pm.getNgayHenTra().getTime()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về mã phiếu mới
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phiếu mượn:");
            e.printStackTrace();
        }

        return -1; // Thêm thất bại
    }

    // Sửa thông tin phiếu mượn: ngày mượn, ngày hẹn trả
    public boolean updatePhieuMuon(PhieuMuon pm) {
        String sql = "UPDATE PhieuMuon SET NgayMuon = ?, NgayHenTra = ? WHERE MaPhieu = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Cập nhật ngày mượn và ngày hẹn trả
            ps.setDate(1, new java.sql.Date(pm.getNgayMuon().getTime()));
            ps.setDate(2, new java.sql.Date(pm.getNgayHenTra().getTime()));
            ps.setInt(3, pm.getMaPhieu());

            // Thực hiện câu lệnh cập nhật
            int rows = ps.executeUpdate();
            return rows > 0;  // Trả về true nếu cập nhật thành công, ngược lại false
        } catch (SQLException e) {
            System.err.println("Lỗi khi sửa phiếu mượn:");
            e.printStackTrace();
        }

        return false;  // Trả về false nếu có lỗi
    }

    public List<PhieuMuon> getAllPhieuMuon() {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT pm.MaPhieu, pm.MaDocGia, dg.HoTen AS TenDocGia, pm.NgayMuon, pm.NgayHenTra, "
                + "ct.NgayTraThucTe, ct.TinhTrang "
                + "FROM PhieuMuon pm "
                + "JOIN DocGia dg ON pm.MaDocGia = dg.MaDocGia "
                + "LEFT JOIN ChiTietMuon ct ON pm.MaPhieu = ct.MaPhieu";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int maPhieu = rs.getInt("MaPhieu");
                int maDocGia = rs.getInt("MaDocGia");
                String tenDocGia = rs.getString("TenDocGia");
                Date ngayMuon = rs.getDate("NgayMuon");
                Date hanTra = rs.getDate("NgayHenTra");
                Date ngayTra = rs.getDate("NgayTraThucTe");
                String trangThai = rs.getString("TinhTrang");

                PhieuMuon pm = new PhieuMuon(maPhieu, maDocGia, tenDocGia, ngayMuon, ngayTra, hanTra, trangThai);
                list.add(pm);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phiếu mượn:");
            e.printStackTrace();
        }

        return list;
    }

    public List<PhieuMuon> getPhieuMuonByTrangThai(String trangThai) {
        List<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT pm.MaPhieu, pm.MaDocGia, pm.NgayMuon, pm.NgayHenTra, "
                + "MAX(ct.NgayTraThucTe) AS NgayTraThucTe, MAX(ct.TinhTrang) AS TinhTrang "
                + "FROM PhieuMuon pm "
                + "JOIN ChiTietMuon ct ON pm.MaPhieu = ct.MaPhieu "
                + "WHERE ct.TinhTrang = ? "
                + "GROUP BY pm.MaPhieu, pm.MaDocGia, pm.NgayMuon, pm.NgayHenTra";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int maPhieu = rs.getInt("MaPhieu");
                    int maDocGia = rs.getInt("MaDocGia");
                    Date ngayMuon = rs.getDate("NgayMuon");
                    Date hanTra = rs.getDate("NgayHenTra");
                    Date ngayTra = rs.getDate("NgayTraThucTe");
                    String tinhTrang = rs.getString("TinhTrang");

                    PhieuMuon pm = new PhieuMuon(maPhieu, maDocGia, ngayMuon, ngayTra, hanTra, tinhTrang);
                    list.add(pm);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lọc phiếu mượn theo trạng thái:");
            e.printStackTrace();
        }
        return list;
    }

}
