package com.dylanthesoldier.apprepofx.controllers.settings;

import com.dylanthesoldier.assistlibfx.BaseSingletonController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.dylanthesoldier.assistlibfx.Dialogs.createErrorDialog;

/**
 * Created by c-dmoore on 10/19/2016.
 */
public class SettingsViewController extends BaseSingletonController {
    private static final String SETTINGS_VIEW_FXML = "settings_view.fxml";
    private static SettingsViewController controller = null;
    @FXML private Button mBtnCancel, mBtnOkay;
    @FXML private CheckBox mCB_DebugMode, mCB_AutoDownload, mCB_defaultEmailer;
    @FXML private TextField mTF_username;
    @FXML private PasswordField mPF_password;

    protected SettingsViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(SETTINGS_VIEW_FXML));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        // --- Setup Button Handlers ---
        mBtnCancel.setOnAction(this::handleCancel);
        mBtnOkay.setOnAction(this::handleOkay);
        mTF_username.setText(AppSettings.getInstance().getSetting(AppSettings.GMAIL_USERNAME, ""));
        mPF_password.setText(AppSettings.getInstance().getSetting(AppSettings.GMAIL_PASSWORD, ""));

        // --- Setup - Checkboxs ---
        //TODO: look into bindings with doing a manual listener
        mCB_DebugMode.setSelected(AppSettings.getInstance().isDebugModeEnabled());
        mCB_AutoDownload.setSelected(AppSettings.getInstance().isAutoDownloadEnabled());
        mCB_defaultEmailer.setSelected(AppSettings.getInstance().useSystemDefaultEmailProgram());
    }

    public static SettingsViewController createInstance() throws IOException {
        if (controller == null)
            controller = new SettingsViewController();

        return controller;
    }

    private void handleOkay(ActionEvent ae) {
        saveSettings();
        loadPreviousNode();
    }

    private void saveSettings() {
        AppSettings.getInstance().setSetting(AppSettings.GMAIL_USERNAME, mTF_username.getText());
        AppSettings.getInstance().setSetting(AppSettings.GMAIL_PASSWORD, mPF_password.getText());

        AppSettings.getInstance().setSetting(AppSettings.DEBUG_MODE, String.valueOf(mCB_DebugMode.isSelected()));
        AppSettings.getInstance().setSetting(AppSettings.AUTOMATICALLY_DOWNLOAD, String.valueOf(mCB_AutoDownload.isSelected()));
        AppSettings.getInstance().setSetting(AppSettings.DEFAULT_EMAIL_PROGRAM_BOOLEAN, String.valueOf(mCB_defaultEmailer.isSelected()));

        try {
            AppSettings.getInstance().saveDefaultSettingsFile();
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }

    private void handleCancel(ActionEvent ae) {
        loadPreviousNode();
    }

    public SettingsViewController setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
        return this;
    }

    public SettingsViewController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }
}
