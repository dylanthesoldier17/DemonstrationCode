package com.dylanthesoldier.blender_accelerator;

import com.dylanthesoldier.Async;
import com.dylanthesoldier.SceneCtrl;
import com.dylanthesoldier.fxml.Dialogs;
import com.dylanthesoldier.interfaces.AffinityCtrl;
import com.dylanthesoldier.interfaces.Blender;
import com.dylanthesoldier.interfaces.FFMPEG;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class MainViewController {

    @FXML
    private JFXButton btnSettings, btnBrowse, btnStart, btnConcat;

    @FXML
    private JFXTextField tf_blend_file, tf_frame_start, tf_frame_end;

    @FXML
    void handle_btn_browse_clicked(MouseEvent event) {
        File f = Dialogs.openFileChooser("Choose .blend file", "Blend File", ".blend");
        if (f == null)
            return;
        tf_blend_file.setText(f.getAbsolutePath());
    }

    @FXML
    void handle_btn_play_clicked(MouseEvent event) {
        R.end_frame = tf_frame_end.getText();
        R.blend_file = tf_blend_file.getText();

        if (R.frame_folder == null)
            R.frame_folder = new File(new File(R.blend_file).getParentFile().toString() + "/frames");

        Async.exec(()->{
            try{
                int process_count = Runtime.getRuntime().availableProcessors();
                if (!tf_frame_end.getText().isEmpty() && !tf_blend_file.getText().isEmpty()) {
                    init_affinity_ctrl(process_count);
                    init_blender(process_count);
                    SceneCtrl.getDefaultSceneCtrl().nextScene(getClass().getResource("view_progress.fxml"), "Render Progress");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }

    private void init_blender(int process_count) throws InterruptedException {
        Blender.getDefaultBlender().blend_file = tf_blend_file.getText();
        Blender.getDefaultBlender().save_location = "//frames/";
        Blender.getDefaultBlender().end_frame = (Integer.parseInt(tf_frame_end.getText()));
        Blender.getDefaultBlender().generate_queue();
        Blender.getDefaultBlender().exec_parallel(process_count-1);
    }

    private void init_affinity_ctrl(int process_count) throws IOException, InterruptedException {
        int[] cpu_cores = new int[process_count-1];
        for (int i=1; i<process_count; i++)
            cpu_cores[i-1] = i;

        R.affinityTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    AffinityCtrl.getDefaultAffinityCtrl().setAffinityAll(1, new String[]{"blender", "java"});
                    AffinityCtrl.getDefaultAffinityCtrl().setProcessAffinity(
                            new String[]{"blender", "java"},
                            new int[]{AffinityCtrl.cores_to_cpuID(cpu_cores), AffinityCtrl.cores_to_cpuID(cpu_cores)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3500);
    }

    @FXML
    void handle_btn_settings_clicked(MouseEvent event) throws IOException{
        SceneCtrl.getDefaultSceneCtrl().nextScene(getClass().getResource("view_settings.fxml"), "Settings");
    }

    @FXML
    void handle_btn_concat_clicked(MouseEvent event) throws IOException {
        R.end_frame = tf_frame_end.getText();
        R.blend_file = tf_blend_file.getText();

        if (R.frame_folder == null)
            R.frame_folder = new File(new File(R.blend_file).getParentFile().toString() + "/frames");

        System.out.println(System.getenv("user.dir"));

        Async.exec(() -> {
            if (!R.blend_file.isEmpty()) {
                FFMPEG ffmpeg = new FFMPEG();
                ffmpeg.setSave_location(R.frame_folder.toString());
                try {
                    if (ffmpeg.concat(R.frame_folder.listFiles()) == false)
                        Platform.runLater(() -> Dialogs.alertAndWait(Alert.AlertType.INFORMATION, "FFMPEG Concat Failed!", ButtonType.OK));
                    else
                        Platform.runLater(() -> Dialogs.alert(Alert.AlertType.INFORMATION, "Success!", ButtonType.OK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void initialize() {
        assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        assert btnBrowse != null : "fx:id=\"btnBrowse\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        assert tf_blend_file != null : "fx:id=\"tf_blend_file\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        assert tf_frame_start != null : "fx:id=\"tf_frame_start\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        assert tf_frame_end != null : "fx:id=\"tf_frame_end\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'view_main.com.dylanthesoldier.fxml'.";
        }
}
