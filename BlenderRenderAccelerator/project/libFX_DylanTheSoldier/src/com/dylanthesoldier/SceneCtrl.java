package com.dylanthesoldier;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class SceneCtrl {
    private Stage primaryStage;
    private Deque<Scene> sceneQueue = new ArrayDeque<>();

    public SceneCtrl(Stage stage){
        this.primaryStage = stage;
    }

    public Scene previousScene(String title){
        if (sceneQueue.size() == 0)
            return null;
        Scene curScene = primaryStage.getScene();
        Platform.runLater(()->{
            primaryStage.setTitle(title);
            primaryStage.setScene(sceneQueue.pop());
        });
        return curScene;
    }

    public Scene nextScene(final URL sceneFile, final String title){
        try {
            if (primaryStage.getScene() != null)
            sceneQueue.push(primaryStage.getScene());
            Scene curScene = primaryStage.getScene();
            final Parent root = FXMLLoader.load(sceneFile);

            Platform.runLater(()->{
                primaryStage.setTitle(title);
                primaryStage.setScene(new Scene(root));
            });
            return curScene;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    //region Static variables and methods

    public static SceneCtrl sceneCtrl = null;
    public static SceneCtrl setDefaultSceneCtrl(SceneCtrl s){
        SceneCtrl prevSceneCtrl = sceneCtrl;
        sceneCtrl = s;
        return prevSceneCtrl;
    }
    public static final SceneCtrl getDefaultSceneCtrl(){
        return sceneCtrl;
    }

    //endregion
}
