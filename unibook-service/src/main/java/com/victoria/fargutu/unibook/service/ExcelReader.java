package com.victoria.fargutu.unibook.service;

import org.springframework.stereotype.Component;

@Component
public class ExcelReader {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    public static final String SAMPLE_XLSX_FILE_PATH = "/Users/fargutuvictoria/Downloads/orar_semII_2017-2018_V.xlsx";
//
//
//    public void readFromExcel() {
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter("excel222.txt", false));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        Workbook workbook = null;
//        try {
//            workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//        Sheet sheet = workbook.getSheetAt(0);
//
//        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
//        log.info("\n\nIterating over Rows and Columns using Iterator\n");
//        Iterator<Row> rowIterator = sheet.rowIterator();
//        try {
//            bufferedWriter.append("AAAAAA");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int count = 10;
//        while (count > 0) {
//            count--;
//            Row row = rowIterator.next();
//
//            // Now let's iterate over the columns of the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            DataFormatter dataFormatter = new DataFormatter();
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                String cellValue = dataFormatter.formatCellValue(cell);
//
//                log.info(cellValue + "\t");
//                try {
//                    bufferedWriter.append(cellValue + "\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//        }
//        try {
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        // 2. Or you can use a for-each loop to iterate over the rows and columns
////        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
////        for (Row row: sheet) {
////            for(Cell cell: row) {
////                String cellValue = dataFormatter.formatCellValue(cell);
////                System.out.print(cellValue + "\t");
////            }
////            System.out.println();
////        }
//
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
