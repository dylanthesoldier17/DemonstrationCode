package com.dylanthesoldier.allegion.service_tag_lookup.views.settings;

import java.util.Properties;

/**
 * Created by c-dmoore on 11/17/2016.
 */
public class Settings {
    public static final Properties properties = new Properties();
    public static final String SPREADSHEET_LOCATION = "SpreadsheetLocation";
    public static final String SHEET_INDEX = "SheetIndex";
    public static final String COLUMN_DATE_INDEX = "ColumnDateIndex";
    public static final String HEADER_COLUMN_INDEX = "ColumnHeaderIndex";
    public static final String DATE_RANGE_IN_DAYS = "DateRangeInDays";
    public static final String FIRST_ROW = "FirstRow";

    public static String getSpreadsheetLocation(){
        return properties.getProperty(SPREADSHEET_LOCATION, "");
    }
    public static String getSheetIndex(){
        return properties.getProperty(SHEET_INDEX);
    }
    public static String getColumnDateIndex(){
        return properties.getProperty(COLUMN_DATE_INDEX);
    }
    public static String getColumnHeaderIndex(){
        return properties.getProperty(HEADER_COLUMN_INDEX);
    }
    public static String getDateRange() {
        return properties.getProperty(DATE_RANGE_IN_DAYS);
    }

    public static void setSheetIndex(String value){
        properties.put(SHEET_INDEX, value);
    }
    public static void setColumnDateIndex(String value){
        properties.put(COLUMN_DATE_INDEX, value);
    }
    public static void setColumnHeaderIndex(String value){
        properties.put(HEADER_COLUMN_INDEX, value);
    }
    public static void setDateRange(String value) {
        properties.put(DATE_RANGE_IN_DAYS, value);
    }
    public static void setWorkbookLocation(String value) {
        properties.put(SPREADSHEET_LOCATION, value);
    }
    public static void setFirstRowSetting(String value){
        properties.put(FIRST_ROW, value);
    }
}
