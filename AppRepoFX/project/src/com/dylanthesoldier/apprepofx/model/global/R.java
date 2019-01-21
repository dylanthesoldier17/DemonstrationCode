package com.dylanthesoldier.apprepofx.model.global;

import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.model.pkg.PackageLoader;
import com.dylanthesoldier.assistlibfx.ExceptionHandler;
import javafx.stage.Stage;

import static com.dylanthesoldier.assistlibfx.Dialogs.createErrorDialog;

/**
 * Created by DylanTheSoldier on 10/19/2016.
 */
public class R {
    // handles the loading of pkg out of a specified directory
    public static PackageLoader packageLoader;

    // use to hold the nodes for changing the scenes.
    //public static NodeQueue nodeSwap;

    // used for changing the nodes in the view.
    public static Stage stage;

    public static ExceptionHandler getDefaultExceptionHandler(){
        return e -> createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
    }
}
