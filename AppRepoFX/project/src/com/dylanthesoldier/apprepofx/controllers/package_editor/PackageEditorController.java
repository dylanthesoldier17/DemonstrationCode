package com.dylanthesoldier.apprepofx.controllers.package_editor;

import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.model.global.R;
import com.dylanthesoldier.apprepofx.model.pkg.Package;
import com.dylanthesoldier.assistlibfx.BaseSingletonController;
import com.dylanthesoldier.assistlibfx.Dialogs;
import com.dylanthesoldier.assistlibfx.Hashs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by c-dmoore on 10/20/2016.
 */
public class PackageEditorController extends BaseSingletonController {

    private static final String PACKAGE_EDITOR_VIEW = "package_editor.fxml";
    public static Package current_package;
    protected static Runnable callback;
    private static PackageEditorController pec = null;
    private static Package tmp_package;
    @FXML TextArea mTA_Description;
    @FXML ProgressIndicator mPI_Progress;
    @FXML private TextField mTxtName, mTxtVersion,
            mTxtPackageName, mTxtPackageURL, mTxtSize,
            mTxtHash, mTxtInstall, mTxtUninstall, mTxtWebsite;
    @FXML private Button mBtnOkay, mBtnCancel;
    @FXML private MenuItem mMI_Cancel;
    private TextField[] textFieldGroup;

    protected PackageEditorController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PACKAGE_EDITOR_VIEW));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        textFieldGroup = new TextField[]{ mTxtName, mTxtVersion,
                mTxtPackageName, mTxtPackageURL, mTxtSize,
                mTxtHash, mTxtInstall, mTxtUninstall, mTxtWebsite };

        // --- Drag-N-Drop Handler ---
        this.setOnDragOver(this::checkDraggedData);
        this.setOnDragDropped(this::handle_onDragDrop);

        // --- Button Click Handlers ---
        mBtnCancel.setOnMouseClicked(me -> handle_Buttons_Click(false));
        mBtnOkay.setOnMouseClicked(me -> handle_Buttons_Click(true));
        mMI_Cancel.setOnAction(this::handle_cancel);
    }

    public static PackageEditorController createInstance(Runnable callback, Package pkg) throws IOException {
        tmp_package = pkg;
        return createInstance(callback);
    }

    public static PackageEditorController createInstance(Runnable callback) throws IOException {
        // --- Set Stage Size ---
        R.stage.setWidth(415);
        R.stage.setHeight(515);

        // --- Create a new package instance if user didn't specify one ---
        if (tmp_package == null)
            tmp_package = new Package();

        // --- Setup Callback ---
        PackageEditorController.callback = callback;

        // --- Check for an instance ---
        if (pec == null)
            pec = new PackageEditorController();

        // --- Enable Bindings ---
        pec.enable_bindings();

        return pec;
    }

    private void handle_Buttons_Click(boolean savePackage) {
        if (savePackage)
            if (!inputValid())
                return;

        inputTweak();

        if (savePackage)
            current_package = tmp_package.clone();

        disable_bindings();

        tmp_package = null;

        if(callback != null && savePackage)
            callback.run();

        loadPreviousNode();
    }

    private void handle_onDragDrop(DragEvent de){
        Dragboard db = de.getDragboard();
        mTxtPackageName.setText(db.getFiles().get(0).getName());
        mTxtSize.setText(String.valueOf(db.getFiles().get(0).length()));
        mPI_Progress.progressProperty().bind(Hashs.ProgressProperty);

        Runnable callback = () -> {
            mTxtHash.setText(Hashs.theHash);
            mPI_Progress.progressProperty().unbind();
        };

        Hashs.AsyncCalculateHash(
                db.getFiles().get(0),
                callback,
                R.getDefaultExceptionHandler());
    }

    private void checkDraggedData(DragEvent de){
        Dragboard db = de.getDragboard();
        if (db.hasFiles() && db.getFiles().get(0).isFile())
            if (!Hashs.isRunning())
                de.acceptTransferModes(TransferMode.ANY);
    }

    private void handle_cancel(ActionEvent ae){
        Hashs.cancel();
        mPI_Progress.progressProperty().unbind();
        mPI_Progress.progressProperty().setValue(0);
    }

    private void disable_bindings(){
        mTxtName.textProperty().unbind();
        mTxtVersion.textProperty().unbind();
        mTxtPackageName.textProperty().unbind();
        mTxtPackageURL.textProperty().unbind();
        mTxtHash.textProperty().unbind();
        mTxtSize.textProperty().unbind();
        mTxtInstall.textProperty().unbind();
        mTxtUninstall.textProperty().unbind();
        mTxtWebsite.textProperty().unbind();
        mPI_Progress.progressProperty().unbind();
        mTA_Description.textProperty().unbind();
    }

    private void enable_bindings(){
        // --- Bindings ---
        mTxtName.textProperty().bindBidirectional(tmp_package.nameProperty());
        mTxtVersion.textProperty().bindBidirectional(tmp_package.versionProperty());
        mTxtPackageName.textProperty().bindBidirectional(tmp_package.packageNameProperty());
        mTxtPackageURL.textProperty().bindBidirectional(tmp_package.packageURLProperty());
        mTxtHash.textProperty().bindBidirectional(tmp_package.SHA_HashProperty());
        mTxtSize.textProperty().bindBidirectional(tmp_package.sizeInBytesProperty());
        mTxtInstall.textProperty().bindBidirectional(tmp_package.silentInstallProperty());
        mTxtUninstall.textProperty().bindBidirectional(tmp_package.silentUninstallProperty());
        mTxtWebsite.textProperty().bindBidirectional(tmp_package.websiteProperty());
        mPI_Progress.progressProperty().bind(Hashs.ProgressProperty);
        mTA_Description.textProperty().bindBidirectional(tmp_package.descriptionProperty());
    }

    private boolean inputValid(){
        if(mTxtName.getText().isEmpty()) {
            Dialogs.createErrorDialog(new Exception("[Name] is empty!"), false, AppSettings.getInstance().isDebugModeEnabled());
            return false;
        }
        if(mTxtPackageName.getText().isEmpty()){
            Dialogs.createErrorDialog(new Exception("[Package Name] is empty!"), false, AppSettings.getInstance().isDebugModeEnabled());
            return false;
        }
        return true;
    }

    private void inputTweak(){
        for(TextField tf: textFieldGroup)
            tf.setText(tf.getText().replace("\\", "/"));
    }

    public PackageEditorController setPreviousNode(Node node){
        this.previousNode = node;
        return this;
    }
    public  PackageEditorController setStage(Stage stage){
        this.stage = stage;
        return this;
    }
}
