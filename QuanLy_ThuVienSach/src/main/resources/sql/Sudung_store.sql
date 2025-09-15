-- TH1: Chưa có phiếu cho độc giả 1 ngày 2025-09-10 → sẽ tạo phiếu mới
EXEC sp_ThemPhieuMuonVaChiTiet 
    @MaDocGia = 1,
    @MaSach = 2,
    @SoLuong = 1,
    @NgayMuon = '2025-09-10',
    @NgayHenTra = '2025-09-17';

-- TH2: Lại gọi tiếp với cùng @MaDocGia, @NgayMuon, @NgayHenTra → chỉ thêm vào chi tiết
EXEC sp_ThemPhieuMuonVaChiTiet 
    @MaDocGia = 1,
    @MaSach = 3,
    @SoLuong = 2,
    @NgayMuon = '2025-09-10',
    @NgayHenTra = '2025-09-17';


-- Xóa store nếu đã tồn tại
IF OBJECT_ID('sp_SuaChiTietMuon', 'P') IS NOT NULL
    DROP PROCEDURE sp_SuaChiTietMuon;
GO