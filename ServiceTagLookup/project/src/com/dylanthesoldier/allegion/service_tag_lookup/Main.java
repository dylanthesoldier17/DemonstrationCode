package com.dylanthesoldier.allegion.service_tag_lookup;

import com.dylanthesoldier.allegion.service_tag_lookup.model.Initializer;
import com.dylanthesoldier.allegion.service_tag_lookup.views.main.MainViewController;
import com.dylanthesoldier.allegion.service_tag_lookup.views.settings.SettingsViewController;
import com.dylanthesoldier.assistlibfx.Dialogs;
import com.dylanthesoldier.assistlibfx.TaskPoolManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Initializer.init(primaryStage);
        }catch (Exception e){
            Dialogs.createErrorDialog(e, true, false);
        }
        SettingsViewController.createInstance();
        primaryStage.setTitle("ServiceTagLookupFX");
        primaryStage.setScene(new Scene(MainViewController.createInstance()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        TaskPoolManager.createInstance().shutdownAllPools();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
