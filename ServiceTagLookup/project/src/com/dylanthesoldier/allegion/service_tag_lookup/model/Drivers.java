package com.dylanthesoldier.allegion.service_tag_lookup.model;

import com.dylanthesoldier.assistlibfx.ExceptionHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by c-dmoore on 10/25/2016.
 */
public class Drivers {

    // --- Prevent Instantiation ---
    private Drivers(){}

    private static Thread theThread = new Thread();
    public static int exit_code = 0;
    public static Process process;

    public static void installDrivers(Runnable callback) throws IOException, InterruptedException {
        String filePath = R.ComputerModel + "/run.bat";
            if (Files.exists(Paths.get(filePath))) {
                process = Runtime.getRuntime().exec(filePath);
                exit_code = process.waitFor();
                if (callback != null) callback.run();
            } else
                throw new IOException("The run batch file is missing: " + Paths.get(filePath).toAbsolutePath().toString());
    }
    public static void installDetectTool(Runnable callback) throws IOException, InterruptedException {
        String filePath = "DellSystemDetectLauncher.bat";
        if (Files.exists(Paths.get(filePath))) {
            process = Runtime.getRuntime().exec(filePath);
            exit_code = process.waitFor();
            if (callback != null) callback.run();
        } else
            throw new IOException("Dell Detect Tool is missing: " + Paths.get(filePath).toAbsolutePath().toString());
    }

    public static void asyncInstallDrivers(Runnable callback, ExceptionHandler eh){
        if (theThread != null)
            if (theThread.isAlive())
                eh.handleException(new Exception("Already Running"));

        theThread = new Thread(() -> {
            try {
                installDrivers(callback);
            } catch (Exception e) {
                eh.handleException(e);
            }
        });
        theThread.start();
    }
    public static void asyncInstallDetectTool(Runnable callback, ExceptionHandler eh) {
        if (theThread != null)
            if (theThread.isAlive())
                eh.handleException(new Exception("Already Running"));

        theThread = new Thread(() -> {
            try {
                installDetectTool(callback);
            } catch (Exception e) {
                eh.handleException(e);
            }
        });
        theThread.start();
    }

    public static boolean isRunning(){
        if (theThread != null && theThread.isAlive())
            return true;
        else
            return false;
    }
}
