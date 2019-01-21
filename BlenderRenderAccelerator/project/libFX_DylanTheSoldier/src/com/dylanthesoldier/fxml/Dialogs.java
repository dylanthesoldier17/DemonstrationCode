package com.dylanthesoldier.fxml;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class Dialogs {

    public static File openFileChooser(String title, String description, String extenstion){
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(description, extenstion));
        File f = fc.showOpenDialog(null);
        return f;
    }

    public static void alertAndWait(Alert.AlertType aat, String msg, ButtonType btn_type){
        Alert a = new Alert(aat, msg, btn_type);
        a.showAndWait();
    }

    public static void alert(Alert.AlertType aat, String msg, ButtonType btn_type){
        Alert a = new Alert(aat, msg, btn_type);
        a.show();
    }
}
