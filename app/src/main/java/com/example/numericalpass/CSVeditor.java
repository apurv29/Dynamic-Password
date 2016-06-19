package com.example.numericalpass;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deepaksood619 on 18/6/16.
 */
public class CSVeditor {

    private static String FILE_PATH;

    private static CSVeditor sharedInstance;
    public static CSVeditor shared() {
        if(sharedInstance == null)
            sharedInstance = new CSVeditor();
        return sharedInstance;
    }

    private int currentRow = 1;
    private int currentColumn = 0;

    private Workbook workbook;
    private Sheet sheet;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");

    public void init(Context context) {

        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath()+"/UserStudyFramework/");
        boolean fileMade = dir.mkdir();
        if(fileMade) {
            Log.v("tag","dir created");
        } else {
            Log.v("tag","dir already present");
        }

        String fileName = "NumericalPassword.xls";
        File newFile = new File(dir, fileName);

        FileInputStream fileInputStream = null;

        if(!newFile.exists()) {
            Log.v("dks","file not found");
            try {
                boolean createdFile = newFile.createNewFile();
                if(createdFile) {
                    Log.v("dks","file created");

                    if(workbook == null)
                        workbook = new HSSFWorkbook();

                } else {
                    Log.v("dks","file not created");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
               try {
                   fileInputStream = new FileInputStream(newFile);
                   if(workbook == null)
                        workbook = new HSSFWorkbook(fileInputStream);
               } catch (IOException e) {
                   e.printStackTrace();
               }
        }

        FILE_PATH = newFile.getAbsolutePath();

        if(workbook != null) {
            sheet = workbook.getSheet("NumSheet");
            if(sheet == null) {
                Log.v("dks", "sheet not present");
                sheet = workbook.createSheet("NumSheet");

                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("username");
                cell = row.createCell(1);
                cell.setCellValue("sign_up");
                cell = row.createCell(2);
                cell.setCellValue("video_name");
                cell = row.createCell(3);
                cell.setCellValue("timestamp");
                cell = row.createCell(4);
                cell.setCellValue("success_login");
                cell = row.createCell(5);
                cell.setCellValue("time_on_username_activity");
                cell = row.createCell(6);
                cell.setCellValue("time_on_activity_test");
                cell = row.createCell(7);
                cell.setCellValue("time_on_registration_activity");
                cell = row.createCell(8);
                cell.setCellValue("time_on_login_activity");
                cell = row.createCell(9);
                cell.setCellValue("total_time_spent");

            } else {
                Log.v("dks","sheet present");
            }

            writeToWorkbook();
        }

        currentRow = sheet.getLastRowNum();
        Log.v("dks","currentRow: "+currentRow);
    }

    public void insertNewUser(String userName, String videoName, long timeSpent) {
        Log.v("dks","inserting new user "+userName);
        currentRow = sheet.getLastRowNum();
        Row row = sheet.createRow(currentRow+1);
        Cell cell = row.createCell(0);
        cell.setCellValue(userName);
        cell = row.createCell(1);
        cell.setCellValue(true);
        cell = row.createCell(2);
        cell.setCellValue(videoName);
        cell = row.createCell(3);
        cell.setCellValue(getTimeStamp());
        cell = row.createCell(4);
        cell.setCellValue("-");
        cell = row.createCell(5);
        cell.setCellValue(timeSpent);
        cell = row.createCell(6);
        cell.setCellValue("-");
        cell = row.createCell(7);
        cell.setCellValue("-");
        cell = row.createCell(8);
        cell.setCellValue("-");
        cell = row.createCell(9);
        cell.setCellValue("-");
        writeToWorkbook();
    }


    public void insertSignInLog(String userName, String videoName, long timeSpent) {
        Log.v("dks","inserting signin log");
        currentRow = sheet.getLastRowNum();
        Row currentRowHolder = sheet.createRow(currentRow+1);
        Cell cell = currentRowHolder.createCell(0);
        cell.setCellValue(userName);
        cell = currentRowHolder.createCell(1);
        cell.setCellValue(false);
        cell = currentRowHolder.createCell(2);
        cell.setCellValue(videoName);
        cell = currentRowHolder.createCell(3);
        cell.setCellValue(getTimeStamp());
        cell = currentRowHolder.createCell(4);
        cell.setCellValue("-");
        cell = currentRowHolder.createCell(5);
        cell.setCellValue(timeSpent);
        cell = currentRowHolder.createCell(6);
        cell.setCellValue("-");
        cell = currentRowHolder.createCell(7);
        cell.setCellValue("-");
        cell = currentRowHolder.createCell(8);
        cell.setCellValue("-");
        cell = currentRowHolder.createCell(9);
        cell.setCellValue("-");
        writeToWorkbook();
    }

    public void recordTimeStamp(long timeSpent, int columnIndex) {

        Cell cell;
        cell = sheet.getRow(currentRow+1).getCell(columnIndex);
        cell.setCellValue(timeSpent);
        writeToWorkbook();
    }

    public void setSuccessLogin(boolean bool) {
        Cell cell = sheet.getRow(currentRow+1).getCell(4);
        cell.setCellValue(bool);
        writeToWorkbook();
    }

    public void writeToWorkbook() {
        Log.v("dks","writing to workbook");
        File file = new File(FILE_PATH);

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getTimeStamp() {
        return simpleDateFormat.format(new Date());
    }

}
