package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;
import javax.swing.JOptionPane;
import util.DBConnection;
import org.apache.poi.ss.usermodel.*;
import util.DBConnection;

public class ImportDAO {

    public static boolean importDocGia(String path) {
        try (Connection conn = DBConnection.getConnection()) {
            FileInputStream fis = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "INSERT INTO DocGia(HoTen, NgaySinh, DiaChi, SoDienThoai, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // bỏ header
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ps.setString(1, row.getCell(0).getStringCellValue());
                ps.setDate(2, new java.sql.Date(row.getCell(1).getDateCellValue().getTime()));
                ps.setString(3, row.getCell(2).getStringCellValue());
                ps.setString(4, row.getCell(3).getStringCellValue());
                ps.setString(5, row.getCell(4).getStringCellValue());
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi tại DocGia: " + e.getMessage());
                    return false;
                }
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi import DocGia: " + e.getMessage());
            return false;
        }
    }

    public static boolean importTacGia(String path) {
        try (Connection conn = DBConnection.getConnection()) {
            FileInputStream fis = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "INSERT INTO TacGia(TenTacGia, NgaySinh) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // bỏ header
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ps.setString(1, row.getCell(0).getStringCellValue());
                ps.setDate(2, new java.sql.Date(row.getCell(1).getDateCellValue().getTime()));
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi tại TacGia: " + e.getMessage());
                    return false;
                }
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi import TacGia: " + e.getMessage());
            return false;
        }
    }

    public static boolean importSach(String path) {
        try (Connection conn = DBConnection.getConnection()) {
            FileInputStream fis = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "INSERT INTO Sach(TenSach, NamXuatBan, TheLoai, SoLuong, MaTacGia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // bỏ header
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ps.setString(1, row.getCell(0).getStringCellValue());
                ps.setInt(2, (int) row.getCell(1).getNumericCellValue());
                ps.setString(3, row.getCell(2).getStringCellValue());
                ps.setInt(4, (int) row.getCell(3).getNumericCellValue());
                ps.setInt(5, (int) row.getCell(4).getNumericCellValue());
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi tại Sach: " + e.getMessage()
                            + "\nKiểm tra: đã tồn tại sách hoặc tác giả chưa tồn tại!");
                    return false;
                }
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi import Sach: " + e.getMessage());
            return false;
        }
    }

    public static boolean importPhieuMuon(String path) {
        try (Connection conn = DBConnection.getConnection()) {
            FileInputStream fis = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "INSERT INTO PhieuMuon(MaDocGia, NgayMuon, NgayHenTra) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // bỏ header
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ps.setInt(1, (int) row.getCell(0).getNumericCellValue());
                ps.setDate(2, new java.sql.Date(row.getCell(1).getDateCellValue().getTime()));
                ps.setDate(3, new java.sql.Date(row.getCell(2).getDateCellValue().getTime()));
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi tại PhieuMuon: " + e.getMessage()
                            + "\nKiểm tra: đã tồn tại phiếu hoặc độc giả chưa tồn tại!");
                    return false;
                }
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi import PhieuMuon: " + e.getMessage());
            return false;
        }
    }

    public static boolean importChiTietMuon(String path) {
        try (Connection conn = DBConnection.getConnection()) {
            FileInputStream fis = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "INSERT INTO ChiTietMuon(MaPhieu, MaSach, SoLuong, TinhTrang, NgayTraThucTe) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // bỏ header
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ps.setInt(1, (int) row.getCell(0).getNumericCellValue());
                ps.setInt(2, (int) row.getCell(1).getNumericCellValue());
                ps.setInt(3, (int) row.getCell(2).getNumericCellValue());
                ps.setString(4, row.getCell(3).getStringCellValue());
                Cell dateCell = row.getCell(4);
                if (dateCell != null && dateCell.getCellType() == CellType.NUMERIC) {
                    ps.setDate(5, new java.sql.Date(dateCell.getDateCellValue().getTime()));
                } else {
                    ps.setDate(5, null);
                }

                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi tại ChiTietMuon: " + e.getMessage()
                            + "\nKiểm tra: phiếu mượn hoặc sách chưa tồn tại, hoặc trùng khóa chính!");
                    return false;
                }
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi import ChiTietMuon: " + e.getMessage());
            return false;
        }
    }
}
