package cn.kcs.common.fileutil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtil {
    // 标题行数
    private final static int TITLE_NUMBER = 1;

    // excel文件路径
    private final static String FILE_PATH = "D:\\DownloadFolder\\xxxx.xlsx";

    public static void main(String[] args) {
        List<SheetInfo> sheetInfos = new ArrayList<>();

        // 更新的sheet信息以下表示：以第一个sheet的第三列作为条件，在第26列的位置插入处理后的数据。
        sheetInfos.add(new SheetInfo(0, 22, 25));
        readAndWriteExcel(sheetInfos);
    }

    private static void readAndWriteExcel(List<SheetInfo> sheetInfos) {
        InputStream inp = null;
        Workbook wb = null;
        try {
            inp = new FileInputStream(FILE_PATH);
            wb = WorkbookFactory.create(inp);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        if (wb == null) {
            return;
        }

        // 更新excel文件
        for (SheetInfo sheetInfo : sheetInfos) {
            updateExcel(wb, sheetInfo.getIndex(), sheetInfo.getColIndex(), sheetInfo.getAddColIndex());
        }

        // 将修改后的 Workbook 对象写入文件
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(FILE_PATH);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateExcel(Workbook wb, int sheetIndex, int colIndex, int resultColIndex) {
        // 获取指定Sheet页index列内容
        List<String> values = getSheetColumnsByCellIndex(wb.getSheetAt(sheetIndex), colIndex);

        // 拼接查询sql
        buildSelectSql(values);

        // 向指定Sheet页index列添加内容
        addSheetColumnsByCellIndex(wb.getSheetAt(sheetIndex), resultColIndex, values);
    }

    private static void buildSelectSql(List<String> values) {
        // 根据实际情况调整
        System.out.println("sql:");
        String str = "select * form xxxx where id in ('" +
                String.join("','", values) +
                "');";
        System.out.println(str);
    }

    private static List<String> getSheetColumnsByCellIndex(Sheet sheet, int index) {
        List<String> cellValues = new ArrayList<>();

        // 这里从第二行开始遍历，默认第一行为标题
        for (int i = TITLE_NUMBER; i <= sheet.getLastRowNum(); i++) {
            Cell cell = sheet.getRow(i).getCell(index);
            String stringCellValue = cell.getStringCellValue();
            cellValues.add(stringCellValue);
        }
        System.out.println("values size is: " + cellValues.size());
        System.out.println(cellValues.toString());
        return cellValues;
    }

    private static void addSheetColumnsByCellIndex(Sheet sheet, int index, List<String> values) {
        // 这里从第二行开始遍历设值，默认第一行为标题
        for (int i = TITLE_NUMBER; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i).getCell(index) == null) {
                sheet.getRow(i).createCell(index);
            }
            Cell cell = sheet.getRow(i).getCell(index);

            //进行设置值
            cell.setCellValue(i + values.get(i - 1));
        }
        System.out.println("set cell value fulfillment");
    }

    static class SheetInfo {
        public SheetInfo(int index, int colIndex, int addColIndex) {
            this.index = index;
            this.colIndex = colIndex;
            this.addColIndex = addColIndex;
        }

        int index;
        int colIndex;
        int addColIndex;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }

        public int getAddColIndex() {
            return addColIndex;
        }

        public void setAddColIndex(int addColIndex) {
            this.addColIndex = addColIndex;
        }
    }
}
