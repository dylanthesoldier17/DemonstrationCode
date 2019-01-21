package com.dylanthesoldier.blender_accelerator;

import com.dylanthesoldier.SceneCtrl;
import com.dylanthesoldier.fxml.Dialogs;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.File;

/**
 * Created by DylanTheSoldier on 4/28/2016.
 */
public class SettingsViewController {

    @FXML
    private JFXToggleButton _tbRelocateProcesses;

    @FXML
    private JFXTextField _tF_Affinity_exe;

    @FXML
    private JFXTextField _tF_Blender_exe;

    @FXML
    private JFXTextField _tF_FFMPEG_exe;

    @FXML
    private JFXButton btn_save;

    @FXML
    void handle_btn_affinity_browse(MouseEvent event) {
        File f = Dialogs.openFileChooser("Choose Affinity Ctrl.exe", "Affinity Ctrl.exe" ,".exe");
        if (f != null)
            _tF_Affinity_exe.setText(Main.getRelativePath(f).toString() + File.separator + f.getName());
    }

    @FXML
    void handle_btn_blender_browse(MouseEvent event) {
        File f = Dialogs.openFileChooser("Choose Blender.exe", "blender.exe" ,".exe");
        if (f != null)
            _tF_Blender_exe.setText(Main.getRelativePath(f).toString()+ File.separator  + f.getName());
    }

    @FXML
    void handle_btn_ffmpeg_browse(MouseEvent event) {
        File f = Dialogs.openFileChooser("Choose FFMPEG.exe", "ffmpeg.exe" ,".exe");
        if (f != null)
            _tF_FFMPEG_exe.setText(Main.getRelativePath(f).toString()+ File.separator  + f.getName());
    }

    @FXML
    void handle_btn_settings_clicked(MouseEvent event) {
        SceneCtrl.getDefaultSceneCtrl().previousScene("Blender Render Accelerator");
    }

    private void load(){
        /*TODO: Save and load settings to a preferences file*/
    }

    @FXML
    void initialize() {
        assert _tbRelocateProcesses != null : "fx:id=\"_tbRelocateProcesses\" was not injected: check your FXML file 'view_settings.com.dylanthesoldier.fxml'.";
        assert _tF_Affinity_exe != null : "fx:id=\"_tF_Affinity_exe\" was not injected: check your FXML file 'view_settings.com.dylanthesoldier.fxml'.";
        assert _tF_Blender_exe != null : "fx:id=\"_tF_Blender_exe\" was not injected: check your FXML file 'view_settings.com.dylanthesoldier.fxml'.";
        assert _tF_FFMPEG_exe != null : "fx:id=\"_tF_FFMPEG_exe\" was not injected: check your FXML file 'view_settings.com.dylanthesoldier.fxml'.";
        assert btn_save != null : "fx:id=\"btn_save\" was not injected: check your FXML file 'view_settings.com.dylanthesoldier.fxml'.";
        load();
    }

}
