package com.dylanthesoldier.allegion.service_tag_lookup.views.settings;

import com.dylanthesoldier.allegion.service_tag_lookup.views.main.MainViewController;
import com.dylanthesoldier.assistlibfx.BaseSingletonController;
import com.dylanthesoldier.assistlibfx.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by c-dmoore on 11/17/2016.
 */
public class SettingsViewController extends BaseSingletonController{

    private static final String SETTINGS_FILE = "settings.cfg";
    private static final String SETTINGS_VIEW_FXML = "settings_view.fxml";
    private static SettingsViewController svc;
    private ExecutorService taskPool = Executors.newSingleThreadExecutor();


    @FXML private TextField mTF_WorkbookLoc, mTF_ColumnTermDate, mTF_ColumnHeaderIndex, mTF_SheetNum, mTF_DateRange;
    @FXML private Button mBtn_Okay, mBtn_Cancel;
    @FXML private MenuItem mMI_Browse;

    protected SettingsViewController() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(SETTINGS_VIEW_FXML));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        mBtn_Cancel.setOnMouseClicked(this::handle_btn_cancel_clicked);
        mBtn_Okay.setOnMouseClicked(this::handle_btn_okay_clicked);
        mMI_Browse.setOnAction(this::handle_menu_item_browse_clicked);
        loadSettings();
        setupTextFields();
    }

    private void setupTextFields() {
        try{
            mTF_ColumnHeaderIndex.setText(Settings.getColumnHeaderIndex().toString());
            mTF_ColumnTermDate.setText(Settings.getColumnDateIndex().toString());
            mTF_WorkbookLoc.setText(Settings.getSpreadsheetLocation());
            mTF_SheetNum.setText(Settings.getSheetIndex().toString());
            mTF_DateRange.setText(Settings.getDateRange().toString());
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    private void loadSettings() {
     File settingsFile = new File(SETTINGS_FILE);
        try {
           if(settingsFile.exists())
                Settings.properties.loadFromXML(new FileInputStream(settingsFile));
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }
    private void saveSettings(){
        Settings.setColumnDateIndex(mTF_ColumnTermDate.getText());
        Settings.setWorkbookLocation(mTF_WorkbookLoc.getText());
        Settings.setColumnHeaderIndex(mTF_ColumnHeaderIndex.getText());
        Settings.setSheetIndex(mTF_SheetNum.getText());
        Settings.setDateRange(mTF_DateRange.getText());
        try {
            Settings.properties.storeToXML(new FileOutputStream(SETTINGS_FILE), null);
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }

    private void handle_menu_item_browse_clicked(ActionEvent mouseEvent) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        fc.setTitle("Choose the spreadsheet to use!");
        File theFile = fc.showOpenDialog(null);
        if(theFile != null) mTF_WorkbookLoc.setText(theFile.getPath());
    }
    private void handle_btn_okay_clicked(MouseEvent mouseEvent) {
        saveSettings();
        loadPreviousNode();
    }
    private void handle_btn_cancel_clicked(MouseEvent mouseEvent) {
        loadPreviousNode();
    }

    public static final SettingsViewController createInstance() throws IOException {
        if(svc == null)
            svc = new SettingsViewController();

        return svc;
    }

    public SettingsViewController setPreviousNode(MainViewController previousNode) {
        this.previousNode = previousNode;
        return this;
    }

    public SettingsViewController setStage(Stage theStage){
        this.stage = theStage;
        return this;
    }
}
