package com.dylanthesoldier.apprepofx.model.global;

import com.dylanthesoldier.apprepofx.controllers.main.MainViewController;
import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.controllers.settings.SettingsViewController;
import com.dylanthesoldier.apprepofx.model.pkg.PackageLoader;
import com.dylanthesoldier.assistlibfx.Dialogs;
import com.dylanthesoldier.assistlibfx.PathsChecker;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by c-dmoore on 10/24/2016.
 */
public class Initializer {

    public static void init(Stage stage) throws IOException {
        // --- Load all the Singleton Classes ---
        R.stage = stage;
        R.packageLoader = PackageLoader.createInstance();

        AppSettings.getInstance().loadDefaultSettingsFile();

        checkPropertiesFile();
        checkForFolders();

        // --- Create instance to preload settings & has to be called after loading the properties file above. ---
        SettingsViewController.createInstance().setPreviousNode(MainViewController.createInstance()).setStage(R.stage);
    }


    private static void checkPropertiesFile(){
        AppSettings.properties.put(AppSettings.LAST_USED_DIRECTORY, AppSettings.PACKAGES_DIRECTORY);
    }

    private static void checkForFolders() {
        PathsChecker.getPathChecksList().add(new PathsChecker.Checks("cache", true));
        PathsChecker.getPathChecksList().add(new PathsChecker.Checks("pkgs", true));
        PathsChecker.performPathChecks(e ->
            Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled())
        );
    }
}
