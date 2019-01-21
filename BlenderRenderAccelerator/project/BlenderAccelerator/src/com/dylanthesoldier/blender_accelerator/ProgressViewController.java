package com.dylanthesoldier.blender_accelerator;

import com.dylanthesoldier.Async;
import com.dylanthesoldier.SceneCtrl;
import com.dylanthesoldier.fxml.Dialogs;
import com.dylanthesoldier.interfaces.AffinityCtrl;
import com.dylanthesoldier.interfaces.Blender;
import com.dylanthesoldier.interfaces.FFMPEG;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DylanTheSoldier on 4/29/2016.
 */
public class ProgressViewController {

    @FXML
    private JFXProgressBar pbProgress;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label lblProgress;

    int cancel_count = 0;

    @FXML
    void handle_btn_cancel_clicked(MouseEvent event) {
        if (cancel_count >= 2) {
            cancel_count = 0;
            btnCancel.setDisable(true);
            Blender.getDefaultBlender().terminateRender();
            Blender.getDefaultBlender().setOnFinished(()->System.exit(0));
        }
        cancel_count++;

    }
    Timer update_timer = new Timer();

    @FXML
    void initialize() {
        assert pbProgress != null : "fx:id=\"pbProgress\" was not injected: check your FXML file 'view_progress.com.dylanthesoldier.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'view_progress.com.dylanthesoldier.fxml'.";
        assert lblProgress != null : "fx:id=\"lblProgress\" was not injected: check your FXML file 'view_progress.com.dylanthesoldier.fxml'.";

        Blender.getDefaultBlender().setOnFinished(()->{
            R.affinityTimer.cancel();
            R.affinityTimer = new Timer("Affinity Controller");
            update_timer.cancel();
            update_timer = new Timer("Update UI Timer - ProgressView");
            AffinityCtrl.getDefaultAffinityCtrl().restoreProcessAffinity();
            pbProgress.setProgress(JFXProgressBar.INDETERMINATE_PROGRESS);
            SceneCtrl.getDefaultSceneCtrl().previousScene("Blender Render Accelerator");
        });


        update_timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double size = Blender.getDefaultBlender().getCmdQueue().size();
                double num_of_chunks = Blender.getDefaultBlender().getNumberOfChunks();
                double prog = (num_of_chunks - size) / num_of_chunks;
                String strProg = (int)(num_of_chunks - size) + "/" + (int)num_of_chunks + " (" + ((int)(prog*100)) + "%)";

                Platform.runLater(() -> {
                    lblProgress.setText(strProg);
                    pbProgress.setProgress(prog);
                });
            }
        }, 0,1000);
    }
}
