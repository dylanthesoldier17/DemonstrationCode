package com.dylanthesoldier.apprepofx.controllers.settings;

import com.dylanthesoldier.assistlibfx.settings.Settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by c-dmoore on 11/14/2016.
 */
public class AppSettings extends Settings {

    // Contains all the stored settings
    public static final Properties properties = new Properties();
    public static final String SETTINGS_FILE = "settings.txt";
    public static final String LAST_USED_DIRECTORY = "LastUsedDirectory";
    public static final String AUTOMATICALLY_DOWNLOAD = "AUTOMATICALLY_DOWNLOAD";
    public static final String PACKAGES_DIRECTORY = System.getProperty("user.dir") + "/pkgs/";
    public static final String CACHE_DIRECTORY = System.getProperty("user.dir") + "/cache/";
    public static final String APP_TITLE = "AppRepoFX";
    public static final String YOUTUBE_TUTORIAL = "https://www.youtube.com/playlist?list=PLhWQqQ5pGw9AwkjE_KPsQst8lERBlzlP-";
    public static final String GMAIL_USERNAME = "GMAIL_USERNAME";
    public static final String GMAIL_PASSWORD = "GMAIL_PASSWORD";
    public static final String CHANGE_LOG_WEBSITE = "http://news.apprepofx.com/";
    public static final String DEFAULT_EMAIL_PROGRAM_BOOLEAN = "DEFAULT_EMAIL_PROGRAM_BOOLEAN";
    public static final String ERROR_FILE = "errors.txt";
    public static final String APPICON_PNG = "appicon.png";
    public static final String DEBUG_MODE = "DebugMode";
    public static final String THE_WEBSITE = "http://www.apprepofx.com";
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final String LAST_USED_DIRECTORY_ENABLED = "LastUsedDirectoryEnabled";
    protected static AppSettings instance;

    public static AppSettings getInstance(){
        if(instance == null) instance = new AppSettings();
        return instance;
    }

    public void loadDefaultSettingsFile() throws IOException {
        if (Files.exists(Paths.get(SETTINGS_FILE)))
            try(FileInputStream fis = new FileInputStream(SETTINGS_FILE)){
                loadSettings(fis);
            } catch (IOException ex) {
                throw ex;
            }

    }

    public String getLastUsedDirectory(String secondary){
        return getSetting(LAST_USED_DIRECTORY, secondary);
    }

    public void saveDefaultSettingsFile() throws IOException {
        try(FileOutputStream fos = new FileOutputStream(SETTINGS_FILE)){
            saveSettings(fos, null);
        }catch (IOException e){
            throw new IOException();
        }
    }

    public Boolean useSystemDefaultEmailProgram(){
        return Boolean.parseBoolean(
                getSetting(DEFAULT_EMAIL_PROGRAM_BOOLEAN, Boolean.FALSE.toString())
        );
    }

    public Boolean isDebugModeEnabled(){
        return Boolean.parseBoolean(
                getSetting(DEBUG_MODE, Boolean.FALSE.toString())
        );
    }

    public Boolean isAutoDownloadEnabled(){
        return Boolean.parseBoolean(
                getSetting(AUTOMATICALLY_DOWNLOAD, Boolean.FALSE.toString())
        );
    }

    public boolean isLastUsedDirectoryEnabled() {
        return Boolean.parseBoolean(
                getSetting(LAST_USED_DIRECTORY_ENABLED, Boolean.FALSE.toString())
        );
    }
}
