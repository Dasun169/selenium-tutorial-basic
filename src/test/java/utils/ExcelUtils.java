package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/TEST_DATA/loginData.xlsx";

    public static Object[][] getSheetData(String sheetName) {
        List<Object[]> rows = new ArrayList<>();
        File file = new File(TEST_DATA_PATH);
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in " + TEST_DATA_PATH);
            }

            // Assume first row is header; start from row index 1
            int firstDataRow = sheet.getFirstRowNum() + 1;
            int lastRow = sheet.getLastRowNum();

            for (int i = firstDataRow; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String username = getCellString(row.getCell(0));
                String password = getCellString(row.getCell(1));
                rows.add(new Object[]{username, password});
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel test data: " + e.getMessage(), e);
        }

        // Convert list to Object[][] for TestNG DataProvider
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) data[i] = rows.get(i);
        return data;
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception ex) {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK:
            default:
                return "";
        }
    }
}
