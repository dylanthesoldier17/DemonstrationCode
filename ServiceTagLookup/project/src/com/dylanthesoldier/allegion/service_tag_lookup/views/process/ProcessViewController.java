package com.dylanthesoldier.allegion.service_tag_lookup.views.process;

import com.dylanthesoldier.allegion.service_tag_lookup.model.Drivers;
import com.dylanthesoldier.assistlibfx.BaseSingletonController;
import com.dylanthesoldier.assistlibfx.TaskPool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;


public class ProcessViewController extends BaseSingletonController{

    @FXML private TextArea mTA_Output;
    private static ProcessViewController pvc;
    private TaskPool taskPool = new TaskPool(TaskPool.PoolType.SINGLE_THREAD_POOL, 1, createShutdownHandler());
    private static final String PROCESS_VIEW_FXML = "ProcessViewController.fxml";

    public ProcessViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PROCESS_VIEW_FXML));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public static final ProcessViewController createInstance() throws IOException {
        if(pvc == null)
            pvc = new ProcessViewController();

        return pvc;
    }

    private TaskPool.ShutdownHandler createShutdownHandler(){
        return sh -> {
            sh.cancel(true);
        };
    }

    private void doThis(){
        try(InputStream is = Drivers.process.getInputStream()){
            int read = 0;
            while (read != -1){
                read = is.read();
                mTA_Output.appendText(String.valueOf(read));
            }
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            loadPreviousNode();
        }
    }
}
