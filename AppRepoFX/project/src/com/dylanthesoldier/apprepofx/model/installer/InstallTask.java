package com.dylanthesoldier.apprepofx.model.installer;

import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.model.pkg.Package;
import javafx.concurrent.Task;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by c-dmoore on 11/11/2016.
 */
public class InstallTask extends Task<Integer>{
    private static Process proc = null;
    private Boolean isInstall = true;
    private Boolean isSilent = false;
    private Package pkg;

    public InstallTask(Package pkg, Mode mode){

        if (pkg != null && mode != null)
            switch (mode){
                case INSTALL: this.isInstall = true; break;
                case INSTALL_SILENT: this.isInstall = true; isSilent = true; break;
                case UNINSTALL: this.isInstall = false;
            }

        this.pkg = pkg;
    }

    @Override
    protected Integer call() throws Exception {
        try {
            if(isCancelled())
                return -1;

            if (isInstall)
                return install(isSilent, pkg);
            else
                return uninstall(pkg);

        }  catch (InterruptedException e) {
            if (proc != null && proc.isAlive())
                proc.destroy();
        }
        return -1;
    }

    public int install(boolean silentInstall, Package pkg) throws Exception {
        String cmd = "";

        if(pkg.packageNameProperty().get().contains(".msi")){
            cmd += "msiexec /i \"" + Paths.get(AppSettings.CACHE_DIRECTORY + pkg.getPackageName()).toString() + "\"";
            if(silentInstall) cmd += "/qr /norestart";
        } else {
            cmd += Paths.get(AppSettings.CACHE_DIRECTORY + pkg.getPackageName()).toString();
            if (silentInstall) {
                if (pkg.getSilentInstall() == null || pkg.getSilentInstall().isEmpty())
                    throw new Exception("Silent install information is empty!");
                else
                    cmd += " " + pkg.getSilentInstall();
            }
        }
        proc = Runtime.getRuntime().exec(cmd);
        return proc.waitFor();
    }

    public int uninstall(Package pkg) throws Exception {
        // --- Check to see if the package is already cached before starting a download & if yes then just execute ---
        if (Files.exists(Paths.get(AppSettings.CACHE_DIRECTORY + pkg.getPackageName()))) {
            if (pkg.getSilentUninstall() == null || pkg.getSilentUninstall().isEmpty())
                throw new Exception("The uninstall information is empty!");

            String cmd = "";
            if(pkg.getName().endsWith(".msi")){
                cmd = "msiexec /x \"" + Paths.get(AppSettings.CACHE_DIRECTORY + pkg.getPackageName()).toString() + "\"";
            } else {
                cmd = Paths.get(AppSettings.CACHE_DIRECTORY + pkg.getPackageName()).toString() + " " +  pkg.getSilentUninstall();
            }
            proc = Runtime.getRuntime().exec(cmd);
            return proc.waitFor();
        } else {
            throw new Exception("The package is missing!");
        }
    }

    public enum Mode { INSTALL, UNINSTALL, INSTALL_SILENT }
}
