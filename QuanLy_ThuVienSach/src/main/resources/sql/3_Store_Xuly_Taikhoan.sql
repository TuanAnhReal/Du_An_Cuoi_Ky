
--kiểm tra tài khoản
CREATE PROCEDURE sp_CheckLogin
    @TenDangNhap VARCHAR(50),
    @MatKhau VARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;
    SELECT MaNguoiDung, TenDangNhap, VaiTro, TrangThai
    FROM NguoiDung
    WHERE TenDangNhap = @TenDangNhap 
      AND MatKhau = @MatKhau
      AND TrangThai = 1;
END;
