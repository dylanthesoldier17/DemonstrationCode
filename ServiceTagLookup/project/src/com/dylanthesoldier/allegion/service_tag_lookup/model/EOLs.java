package com.dylanthesoldier.allegion.service_tag_lookup.model;

import com.dylanthesoldier.allegion.service_tag_lookup.views.settings.Settings;
import javafx.concurrent.Task;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * Created by c-dmoore on 11/18/2016.
 *
 * 81PL5V1
 */
public class EOLs {
    protected EOLs(){}

    public static Task<Row> getSpreadsheetTask_RowFilter(RowFilter rf){
        Task<Row> loadSpreadsheetTask = new Task<Row>() {
            @Override
            protected Row call() throws Exception {
                File theFile = new File(Settings.getSpreadsheetLocation());
                Row theRow;
                FileInputStream fis = new FileInputStream(theFile);

                if (!theFile.exists() && theFile.isFile())
                    throw new FileNotFoundException("Can't find the spreadsheet!");

                Workbook wb = new HSSFWorkbook(fis);
                Iterator<Row> rowIter = wb.getSheetAt(Integer.parseInt(Settings.getSheetIndex())-1).rowIterator();

                while (rowIter.hasNext()){
                    theRow = rowIter.next();
                    try {
                        if(rf.getRow(theRow) == true) {
                            fis.close();
                            return theRow;
                        }
                        else
                            continue;
                    }catch (NullPointerException npe){
                        continue;
                    }
                }
                fis.close();
                return null;
            }
        };
        return loadSpreadsheetTask;
    }
    public static Task<Row> getSpreadsheetTask_GetRow(int sheetIndex, int rowIndex){
        Task<Row> loadSpreadsheetTask = new Task<Row>() {
            @Override
            protected Row call() throws Exception {
                File theFile = new File(Settings.getSpreadsheetLocation());
                Row theRow;
                FileInputStream fis;

                if (!theFile.exists() && theFile.isFile())
                    throw new FileNotFoundException("Can't find the spreadsheet!");

                fis = new FileInputStream(theFile);
                Workbook wb = new HSSFWorkbook(fis);
                theRow = wb.getSheetAt(sheetIndex-1).getRow(rowIndex-1);
                fis.close();
                return theRow;
            }
        };
        return loadSpreadsheetTask;
    }

    public static Task<Row> getSpreadsheetTask_CellFilter(CellFilter cf){
        Task<Row> loadSpreadsheetTask = new Task<Row>() {
            @Override
            protected Row call() throws Exception {
                File theFile = new File(Settings.getSpreadsheetLocation());
                Row theRow;
                FileInputStream fis;

                if (!theFile.exists() && theFile.isFile())
                    throw new FileNotFoundException("Can't find the spreadsheet!");

                Workbook wb =  null;
                try {
                    fis = new FileInputStream(theFile);
                    wb = new HSSFWorkbook(fis);

                }catch (OfficeXmlFileException e){
                    throw e;
                }
                Iterator<Row> rowIter = wb.getSheetAt(Integer.parseInt(Settings.getSheetIndex())-1).rowIterator();
                int t = 1;
                while (rowIter.hasNext()){
                    theRow = rowIter.next();
                    try {
                        Iterator<Cell> iter = theRow.iterator();
                        while (iter.hasNext()){
                            Cell cell = iter.next();
                            if(cell == null) continue;
                            if (cf.getRow(cell) == true) {
                                fis.close();
                                return theRow;
                            }
                        }
                    }catch (NullPointerException npe){
                        continue;
                    }
                }
                fis.close();
                return null;
            }
        };
        return loadSpreadsheetTask;
    }

    public interface RowFilter{
        boolean getRow(Row theRow) throws Exception;
    }

    public interface CellFilter{
        boolean getRow(Cell theCell) throws Exception;
    }
}
