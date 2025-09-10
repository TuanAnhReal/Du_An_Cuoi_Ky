
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
    @TinhTrangMoi NVARCHAR(50),
    @NgayTraThucTe DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SoLuongCu INT, @TinhTrangCu NVARCHAR(50);

    -- Lấy thông tin cũ
    SELECT @SoLuongCu = SoLuong, @TinhTrangCu = TinhTrang
    FROM ChiTietMuon
    WHERE MaPhieu = @MaPhieu AND MaSach = @MaSach;

    -- Xử lý tồn kho theo thay đổi tình trạng
    IF (@TinhTrangCu = N'Đang mượn' AND @TinhTrangMoi = N'Đã trả')
    BEGIN
        UPDATE Sach SET SoLuong = SoLuong + @SoLuongCu WHERE MaSach = @MaSach;
    END
    ELSE IF (@TinhTrangCu = N'Đã trả' AND @TinhTrangMoi = N'Đang mượn')
    BEGIN
        IF EXISTS (SELECT 1 FROM Sach WHERE MaSach = @MaSach AND SoLuong >= @SoLuongMoi)
        BEGIN
            UPDATE Sach SET SoLuong = SoLuong - @SoLuongMoi WHERE MaSach = @MaSach;
        END
        ELSE
        BEGIN
            RAISERROR(N'Sách không đủ để mượn lại', 16, 1);
            RETURN;
        END
    END
    -- Các case khác (Đang mượn -> Mất/Hỏng) thì không cộng lại tồn kho

    -- Update chi tiết mượn
    UPDATE ChiTietMuon
    SET SoLuong = @SoLuongMoi,
        TinhTrang = @TinhTrangMoi,
        NgayTraThucTe = @NgayTraThucTe
    WHERE MaPhieu = @MaPhieu AND MaSach = @MaSach;
END;
GO
