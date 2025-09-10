package dao;

import model.TaiKhoan;
import util.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TaiKhoanDAO {

    // Hàm check login bằng stored procedure
    public TaiKhoan checkLogin(TaiKhoan tkInput) {
        TaiKhoan tkResult = null;
        String sql = "{call sp_CheckLogin(?, ?)}";

        try (Connection con = DBConnection.getConnection(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, tkInput.getTenDangNhap());
            cstmt.setString(2, tkInput.getMatKhau());

            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    tkResult = new TaiKhoan();
                    tkResult.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                    tkResult.setTenDangNhap(rs.getString("TenDangNhap"));
                    tkResult.setVaiTro(rs.getString("VaiTro"));
                    tkResult.setTrangThai(rs.getBoolean("TrangThai"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tkResult;
    }
}
