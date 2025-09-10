package dao;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.DBConnection;
import model.*;

public class ExportDAO {

    public static boolean exportDocGia(String folderPath) {
        String sql = "SELECT * FROM DocGia";
        String filePath = folderPath + "\\DocGia.xlsx";
        return exportToExcel(sql, filePath);
    }

    public static boolean exportTacGia(String folderPath) {
        String sql = "SELECT * FROM TacGia";
        String filePath = folderPath + "\\TacGia.xlsx";
        return exportToExcel(sql, filePath);
    }

    public static boolean exportSach(String folderPath) {
        String sql = "SELECT * FROM Sach";
        String filePath = folderPath + "\\Sach.xlsx";
        return exportToExcel(sql, filePath);
    }

    public static boolean exportPhieuMuon(String folderPath) {
        String sql = "SELECT * FROM PhieuMuon";
        String filePath = folderPath + "\\PhieuMuon.xlsx";
        return exportToExcel(sql, filePath);
    }

    public static boolean exportChiTietMuon(String folderPath) {
        String sql = "SELECT * FROM ChiTietMuon";
        String filePath = folderPath + "\\ChiTietMuon.xlsx";
        return exportToExcel(sql, filePath);
    }

    // Hàm dùng chung để xuất 1 bảng SQL ra file Excel
    private static boolean exportToExcel(String sql, String filePath) {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Sheet1");
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Header
            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(meta.getColumnName(i));
            }

            // Dữ liệu
            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    Object value = rs.getObject(i);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
