package com.dylanthesoldier.blender_accelerator;

import com.dylanthesoldier.SceneCtrl;
import com.dylanthesoldier.interfaces.AffinityCtrl;
import com.dylanthesoldier.interfaces.Blender;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneCtrl.setDefaultSceneCtrl(new SceneCtrl(primaryStage));
        AffinityCtrl.setDefaultAffinityCtrl(new AffinityCtrl());
        Blender.setDefaultBlender(new Blender());

        primaryStage.setOnCloseRequest(we -> {
            R.affinityTimer.cancel();
            AffinityCtrl.getDefaultAffinityCtrl().restoreProcessAffinity();
            System.exit(0);
        });
        SceneCtrl.getDefaultSceneCtrl().nextScene(getClass().getResource("view_main.fxml"), "Blender Render Accelerator");
        primaryStage.show();
    }

    public static Path getRelativePath(File f){
        return f.toPath().relativize(new File(System.getProperty("user.dir")).toPath());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
