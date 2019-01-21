package com.dylanthesoldier.apprepofx;

import com.dylanthesoldier.apprepofx.controllers.main.MainViewController;
import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.model.global.Initializer;
import com.dylanthesoldier.assistlibfx.components.download_manager.DownloadManagerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Initializer.init(primaryStage);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(AppSettings.APPICON_PNG)));
        primaryStage.setTitle(AppSettings.APP_TITLE);
        primaryStage.setScene(new Scene(MainViewController.createInstance()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        MainViewController.createInstance().shutdown();
        DownloadManagerController.createInstance().shutdown();
        super.stop();
    }
}
