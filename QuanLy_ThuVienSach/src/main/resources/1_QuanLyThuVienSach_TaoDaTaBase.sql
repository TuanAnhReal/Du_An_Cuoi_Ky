------------------------------------------------------------
-- 1. Tạo Database
------------------------------------------------------------
CREATE DATABASE QuanLyThuVienSach;
GO
USE QuanLyThuVienSach;
GO

------------------------------------------------------------
-- 2. Bảng Độc giả (thêm ảnh chân dung)
------------------------------------------------------------
CREATE TABLE DocGia (
    MaDocGia INT IDENTITY(1,1) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    DiaChi NVARCHAR(200),
    SoDienThoai VARCHAR(15),
    Email VARCHAR(100),
    AnhChanDung VARBINARY(MAX) NULL -- có thể để trống
);

------------------------------------------------------------
-- 3. Bảng Tác giả
------------------------------------------------------------
CREATE TABLE TacGia (
    MaTacGia INT IDENTITY(1,1) PRIMARY KEY,
    TenTacGia NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    AnhChanDung VARBINARY(MAX) NULL
);

------------------------------------------------------------
-- 4. Bảng Sách (liên kết với Tác giả)
------------------------------------------------------------
CREATE TABLE Sach (
    MaSach INT IDENTITY(1,1) PRIMARY KEY,
    TenSach NVARCHAR(200) NOT NULL,
    NamXuatBan INT,
    TheLoai NVARCHAR(100),
    SoLuong INT DEFAULT 1 CHECK (SoLuong >= 0),
    AnhBia VARBINARY(MAX) NULL,
    MaTacGia INT NOT NULL,
    CONSTRAINT FK_Sach_TacGia FOREIGN KEY (MaTacGia) REFERENCES TacGia(MaTacGia)
);

------------------------------------------------------------
-- 5. Bảng Phiếu mượn
------------------------------------------------------------
CREATE TABLE PhieuMuon (
    MaPhieu INT IDENTITY(1,1) PRIMARY KEY,
    MaDocGia INT NOT NULL,
    NgayMuon DATE DEFAULT GETDATE(),
    NgayHenTra DATE NOT NULL,
    CONSTRAINT FK_PhieuMuon_DocGia FOREIGN KEY (MaDocGia) REFERENCES DocGia(MaDocGia)
);

------------------------------------------------------------
-- 6. Bảng Chi tiết mượn
------------------------------------------------------------
CREATE TABLE ChiTietMuon (
    MaPhieu INT NOT NULL,
    MaSach INT NOT NULL,
    SoLuong INT DEFAULT 1 CHECK (SoLuong > 0),
    TinhTrang NVARCHAR(50) CHECK (TinhTrang IN (N'Đang mượn', N'Đã trả', N'Mất sách', N'Hỏng')),
    NgayTraThucTe DATE NULL,
    PRIMARY KEY (MaPhieu, MaSach),
    FOREIGN KEY (MaPhieu) REFERENCES PhieuMuon(MaPhieu),
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach)
);

------------------------------------------------------------
-- 7. Bảng Người dùng (phân quyền)
------------------------------------------------------------
CREATE TABLE NguoiDung (
    MaNguoiDung INT IDENTITY(1,1) PRIMARY KEY,
    TenDangNhap VARCHAR(50) NOT NULL UNIQUE,
    MatKhau VARCHAR(255) NOT NULL,
    HoTen NVARCHAR(100),
    Email VARCHAR(100),
    VaiTro NVARCHAR(20) CHECK (VaiTro IN (N'ThuThu', N'NguoiDung')) NOT NULL,
    TrangThai BIT DEFAULT 1,
    NgayTao DATETIME DEFAULT GETDATE(),
    MaDocGia INT NULL,
    CONSTRAINT FK_NguoiDung_DocGia FOREIGN KEY (MaDocGia) REFERENCES DocGia(MaDocGia)
);

------------------------------------------------------------
-- 8. Thêm dữ liệu mẫu
------------------------------------------------------------

-- Độc giả (ảnh để NULL)
INSERT INTO DocGia(HoTen, NgaySinh, DiaChi, SoDienThoai, Email, AnhChanDung)
VALUES 
(N'Nguyễn Văn A', '2000-05-10', N'Hà Nội', '0912345678', 'vana@gmail.com', NULL),
(N'Trần Thị B', '2001-08-20', N'Hồ Chí Minh', '0987654321', 'thib@gmail.com', NULL),
(N'Lê Văn C', '1999-03-15', N'Đà Nẵng', '0905123456', 'levanc@gmail.com', NULL);

-- Tác giả
INSERT INTO TacGia(TenTacGia, NgaySinh)
VALUES 
(N'Nguyễn Văn Cường', '1980-02-15'),
(N'Phạm Thị Hoa', '1975-07-22'),
(N'Ngô Bảo Châu', '1972-06-28'),
(N'Trần Quốc Tuấn', '1965-10-10');

-- Sách
INSERT INTO Sach(TenSach, NamXuatBan, TheLoai, SoLuong, MaTacGia)
VALUES
(N'Lập trình Java cơ bản', 2022, N'Công nghệ thông tin', 5, 1),
(N'Cơ sở dữ liệu SQL', 2021, N'Công nghệ thông tin', 3, 2),
(N'Giải tích 1', 2020, N'Toán học', 2, 3),
(N'Lịch sử Việt Nam', 2018, N'Lịch sử', 4, 4);

-- Phiếu mượn
INSERT INTO PhieuMuon(MaDocGia, NgayMuon, NgayHenTra)
VALUES
(1, '2025-08-18', '2025-08-25'),
(2, '2025-08-10', '2025-08-17'),
(3, '2025-08-05', '2025-08-12');

-- Chi tiết mượn
INSERT INTO ChiTietMuon(MaPhieu, MaSach, SoLuong, TinhTrang)
VALUES
(1, 1, 1, N'Đang mượn'),
(1, 2, 1, N'Đang mượn'),
(2, 3, 1, N'Đã trả'),
(3, 4, 1, N'Đang mượn');

-- Người dùng
INSERT INTO NguoiDung(TenDangNhap, MatKhau, HoTen, Email, VaiTro)
VALUES
('admin', '123456', N'Nguyễn Thủ Thư', 'admin@thuviensach.com', N'ThuThu');

-- Liên kết người dùng với độc giả
INSERT INTO NguoiDung(TenDangNhap, MatKhau, HoTen, Email, VaiTro, MaDocGia)
VALUES
('user01', '123456', N'Lê Văn User', 'user01@thuviensach.com', N'NguoiDung', 1);
