
--Có 3 trường hợp: Thêm cả phiếu mượn và chi tiết,
--				   Khi trùng bảng phiếu mượn sẽ chỉ thêm bảng chi tiết
--				   Thêm phần tồn kho

CREATE PROCEDURE sp_ThemPhieuMuonVaChiTiet
    @MaDocGia INT,
    @MaSach INT,
    @SoLuong INT,
    @NgayMuon DATE,
    @NgayHenTra DATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MaPhieu INT;

    -- 1. Check tồn kho
    IF NOT EXISTS (SELECT 1 FROM Sach WHERE MaSach = @MaSach AND SoLuong >= @SoLuong)
    BEGIN
        RAISERROR(N'Sách không đủ số lượng để mượn', 16, 1);
        RETURN;
    END

    -- 2. Tìm phiếu mượn trùng
    SELECT TOP 1 @MaPhieu = MaPhieu
    FROM PhieuMuon
    WHERE MaDocGia = @MaDocGia 
      AND NgayMuon = @NgayMuon
      AND NgayHenTra = @NgayHenTra;

    -- 3. Nếu chưa có thì tạo mới
    IF @MaPhieu IS NULL
    BEGIN
        INSERT INTO PhieuMuon(MaDocGia, NgayMuon, NgayHenTra)
        VALUES (@MaDocGia, @NgayMuon, @NgayHenTra);

        SET @MaPhieu = SCOPE_IDENTITY();
    END

    -- 4. Thêm chi tiết mượn
    INSERT INTO ChiTietMuon(MaPhieu, MaSach, SoLuong, TinhTrang, NgayTraThucTe)
    VALUES (@MaPhieu, @MaSach, @SoLuong, N'Đang mượn', NULL);

    -- 5. Cập nhật số lượng sách
    UPDATE Sach
    SET SoLuong = SoLuong - @SoLuong
    WHERE MaSach = @MaSach;

END;
GO


-- Hàm trả sách
CREATE PROCEDURE sp_TraSach
    @MaPhieu INT,
    @MaSach INT,
    @NgayTra DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @NgayMuon DATE;

    -- Lấy ngày mượn từ phiếu
    SELECT @NgayMuon = NgayMuon 
    FROM PhieuMuon 
    WHERE MaPhieu = @MaPhieu;

    -- Nếu người dùng để trống ngày trả => gán = ngày hiện hành
    IF @NgayTra IS NULL
        SET @NgayTra = GETDATE();

    -- Check ngày trả
    IF @NgayTra < @NgayMuon OR @NgayTra > GETDATE()
    BEGIN
        RAISERROR(N'Ngày trả không hợp lệ!', 16, 1);
        RETURN;
    END

    -- Update ChiTietMuon
    UPDATE ChiTietMuon
    SET NgayTraThucTe = @NgayTra,
        TinhTrang = N'Đã trả'
    WHERE MaPhieu = @MaPhieu AND MaSach = @MaSach;

    -- Cập nhật tồn kho
    UPDATE Sach
    SET SoLuong = SoLuong + (
        SELECT SoLuong 
        FROM ChiTietMuon 
        WHERE MaPhieu = @MaPhieu AND MaSach = @MaSach
    )
    WHERE MaSach = @MaSach;
END;
GO


--sửa phiếu mượn, update tồn kho, nếu tình trạng hỏng, mất sách thì không cộng vào tồn kho
CREATE PROCEDURE sp_SuaChiTietMuon
    @MaPhieu INT,
    @MaSach INT,
    @SoLuongMoi INT,
    @TinhTrang NVARCHAR(50),
    @NgayTraThucTe DATE
AS
BEGIN
    DECLARE @SoLuongCu INT;

    -- Lấy số lượng cũ
    SELECT @SoLuongCu = SoLuong
    FROM ChiTietMuon
    WHERE MaPhieu = @MaPhieu AND MaSach = @MaSach;

    DECLARE @Delta INT = @SoLuongMoi - @SoLuongCu;

    -- Nếu mượn thêm thì check tồn kho
    IF @Delta > 0
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM Sach WHERE MaSach=@MaSach AND SoLuong >= @Delta)
        BEGIN
            RAISERROR(N'Sách không đủ số lượng để cập nhật',16,1);
            RETURN;
        END
    END

    -- Update chi tiết
    UPDATE ChiTietMuon
    SET SoLuong=@SoLuongMoi, TinhTrang=@TinhTrang, NgayTraThucTe=@NgayTraThucTe
    WHERE MaPhieu=@MaPhieu AND MaSach=@MaSach;

    -- Update tồn kho
    UPDATE Sach
    SET SoLuong = SoLuong - @Delta
    WHERE MaSach=@MaSach;
END;
