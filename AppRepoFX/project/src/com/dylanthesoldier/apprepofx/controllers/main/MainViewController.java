package com.dylanthesoldier.apprepofx.controllers.main;

import com.dylanthesoldier.apprepofx.controllers.package_editor.PackageEditorController;
import com.dylanthesoldier.apprepofx.controllers.settings.AppSettings;
import com.dylanthesoldier.apprepofx.controllers.settings.SettingsViewController;
import com.dylanthesoldier.apprepofx.model.global.R;
import com.dylanthesoldier.apprepofx.model.installer.InstallTask;
import com.dylanthesoldier.apprepofx.model.pkg.Package;
import com.dylanthesoldier.apprepofx.model.pkg.PackageLoader;
import com.dylanthesoldier.assistlibfx.BaseSingletonController;
import com.dylanthesoldier.assistlibfx.Dialogs;
import com.dylanthesoldier.assistlibfx.TaskPool;
import com.dylanthesoldier.assistlibfx.components.download_manager.DownloadManagerController;
import com.dylanthesoldier.assistlibfx.components.download_manager.DownloadTask;
import com.dylanthesoldier.assistlibfx.components.emailer.EmailViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.dylanthesoldier.assistlibfx.Dialogs.createErrorDialog;

public class MainViewController extends BaseSingletonController {

    // --- Constants ---
    private static final String MAIN_VIEW_FXML = "main_view.fxml";

    private static MainViewController mainViewController = null;

    @FXML protected MenuItem mMI_Settings, mMI_Close, mMI_About,
                            mMI_Add, mMI_Edit, mMI_Remove, mMI_Website,
                            mMI_Install, mMI_Uninstall, mMI_OpenDir,
                            mMI_PackageFolder, mMI_InstallFolder,
                            mMI_InstallSilent, mMI_Download, mMI_Reload, mMI_Tutorial,
                            mMI_ChangeLog,mMI_Export_Email, mMI_Export_File, mMI_openDownloads;
    @FXML protected ListView<Package> lvItems;
    private TaskPool<InstallTask> installTaskPool = new TaskPool<>(TaskPool.PoolType.FIXED_THREAD_POOL, 1);
    private MultipleSelectionModel<Package> selectedPackage;

    protected MainViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_VIEW_FXML));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        lvItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedPackage = lvItems.getSelectionModel();
        reloadPackages();
        setupMenuItemHandlers();
    }

    public static MainViewController createInstance() throws IOException {
        if (mainViewController == null)
            mainViewController = new MainViewController();

        return mainViewController;
    }

    private void setupMenuItemHandlers(){
        mMI_openDownloads.setOnAction(a -> handle_open_downloads());
        mMI_Settings.setOnAction(a -> handle_settings());
        mMI_Close.setOnAction(a -> handle_close(false));
        mMI_About.setOnAction(a -> handle_about());
        mMI_Remove.setOnAction(a -> handle_RemoveItem());
        mMI_Website.setOnAction(a -> handle_openWebsite());
        mMI_OpenDir.setOnAction(a -> handle_openDir());
        mMI_PackageFolder.setOnAction(a -> handle_openPackageFolder());
        mMI_InstallFolder.setOnAction(a -> handle_openInstallFolder());
        mMI_Download.setOnAction(a -> handle_download());
        mMI_ChangeLog.setOnAction(a -> handle_open_changelog());
        mMI_Export_File.setOnAction(a -> handle_export_toFile());
        mMI_Export_Email.setOnAction(a -> handle_export_toEmail());
        mMI_InstallSilent.setOnAction(a -> handle_silent_install());
        mMI_Add.setOnAction(a -> handle_AddItem());
        mMI_Edit.setOnAction(a -> handle_EditItem());
        mMI_Install.setOnAction(a -> handle_install());
        mMI_Uninstall.setOnAction(a -> handle_uninstall());
        mMI_Reload.setOnAction(a -> reloadPackages());
        mMI_Tutorial.setOnAction(a -> handle_openTutorial());
    }

    private void handle_EditItem() {
        Runnable callback = () -> {
            Package p = lvItems.getSelectionModel().getSelectedItem();
            try {
                // --- delete the old .pkg file if the user edited the package name or version. ---
                if (!p.getName().equals(PackageEditorController.current_package.getName()) || !p.getVersion().equals(PackageEditorController.current_package.getVersion()))
                    Files.deleteIfExists(Paths.get(AppSettings.getInstance().getLastUsedDirectory(AppSettings.WORKING_DIRECTORY)+ "/" + p.getName() + "_" + p.getVersion() + ".pkg"));

                // --- export the newly edited package to a .pkg file. ---
                R.packageLoader.exportXML(PackageEditorController.current_package);
            } catch (IOException e) {
                createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
            }
            reloadPackages();
        };

        handle_EditItem(callback);
    }

    private void handle_AddItem() {
        Runnable callback = () ->{
            // Once finished export the newly created package & reload all packages.
            R.packageLoader.exportXML(PackageEditorController.current_package);
            reloadPackages();
        };

        handle_AddItem(callback);
    }

    public MainViewController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    // --- Menu Item Click Handlers ---
    protected void handle_openInstallFolder() {
        try {
            Desktop.getDesktop().open(new File(AppSettings.CACHE_DIRECTORY));
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_openPackageFolder() {
        try {
            Desktop.getDesktop().open(new File(AppSettings.getInstance().getLastUsedDirectory(AppSettings.PACKAGES_DIRECTORY)));
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_openDir() {
        //TODO: change to a multiselect open file dialog to copy to pkg or load all from that directory
        DirectoryChooser dc = new DirectoryChooser();

        if (AppSettings.getInstance().isLastUsedDirectoryEnabled() && Files.exists(Paths.get(AppSettings.getInstance().getLastUsedDirectory(AppSettings.WORKING_DIRECTORY))))
            dc.setInitialDirectory(new File((String) AppSettings.properties.getOrDefault(AppSettings.LAST_USED_DIRECTORY, AppSettings.WORKING_DIRECTORY)));
        else
            dc.setInitialDirectory(new File(AppSettings.WORKING_DIRECTORY));
        dc.setTitle("Choose Packages Folder");
        File theDir = dc.showDialog(R.stage.getOwner());
        if (theDir != null)
            AppSettings.getInstance().setSetting(AppSettings.LAST_USED_DIRECTORY, theDir.getPath());

        reloadPackages();
    }
    protected void handle_about() {
        try {
            Desktop.getDesktop().browse(new URI(AppSettings.THE_WEBSITE));
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_close(boolean forceShutdown) {
        if (DownloadManagerController.createInstanceNoException().isRunning() || installTaskPool.isRunning())
            if(!forceShutdown)
                if (Dialogs.createPromptDialog("Are you sure you want to cancel?") == Dialogs.PROMPT.NO)
                    return;

        try {
            installTaskPool.cancelAll_ShutdownNow(true);
            //TODO: cancel running downloads in DownloadManagerController
            //downloadTaskPool.cancelAll_ShutdownNow(false);

            // --- Save any settings ---
            AppSettings.getInstance().saveDefaultSettingsFile();
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
        // --- Finally Exit ---
        Platform.exit();
    }
    protected void handle_settings() {
        try {
            R.stage.getScene().setRoot(
                    SettingsViewController
                            .createInstance()
                            .setPreviousNode(MainViewController.createInstance())
                            .setStage(R.stage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void handle_openWebsite() {
        try {
            if (selectedPackage.getSelectedItem().getWebsite() == null || selectedPackage.getSelectedItem().getWebsite().isEmpty())
                throw new Exception("The website url is missing!");
            else
                Desktop.getDesktop().browse(new URI(selectedPackage.getSelectedItem().getWebsite()));
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_RemoveItem() {

        // --- ignore the click if the ListView's selection is empty ---
        if (selectedPackage.getSelectedItems().size() > 0) {

            // --- if the selection isn't empty then ask to verify deletion ---
            String msg = "Are you sure you want to delete: ";
            for(Package pkg: selectedPackage.getSelectedItems()) msg += pkg.getName() + "\n";

            Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.NO, ButtonType.YES);

            if (alert.showAndWait().get() == ButtonType.YES) {

                selectedPackage.getSelectedItems().forEach(pkg -> {
                    // --- if verified for deletion then delete the file ---

                    try {
                        // --- this gets a list of paths from files with a .pkg extension ---
                        Files.list(Paths.get(AppSettings.getInstance().getLastUsedDirectory(AppSettings.WORKING_DIRECTORY)))
                                .filter(f -> f.toString().contains(".pkg"))
                                .collect(Collectors.toList())
                                .forEach(f -> {
                                    // --- for each file that matches load that file ---
                                    Package loaded_pkg = R.packageLoader.loadPackageXML(f.toString());

                                    // --- Check to see if loaded file matches the one removed from the listview.
                                    if (pkg.equals(loaded_pkg))
                                        // --- if it matches then delete that file ---
                                        try {
                                            Files.deleteIfExists(f);
                                            return; // --- delete only the first occurrence. ---
                                        } catch (IOException e) {
                                            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
                                        }

                                });
                    } catch (Exception e) {
                        createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
                    }
                });

                // --- reload all the packages ---
                reloadPackages();
            }
        }
    }
    protected void handle_EditItem(Runnable callback) {
        if (selectedPackage.getSelectedItems().size() <= 0)
            return;

        try {
            R.stage.getScene().setRoot(
                    PackageEditorController
                            .createInstance(callback, selectedPackage.getSelectedItem())
                            .setPreviousNode(MainViewController.createInstance())
                            .setStage(R.stage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void handle_AddItem(Runnable callback) {
        try {
            // Change to PackageEditorView
            R.stage.getScene().setRoot(
                    PackageEditorController
                            .createInstance(callback)
                            .setPreviousNode(MainViewController.createInstance())
                            .setStage(R.stage)
            );
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_uninstall() {
        if (!sanityCheck()) return;
        if (Dialogs.createPromptDialog("Are you sure?") != Dialogs.PROMPT.YES) return;
        InstallTask it = new InstallTask(selectedPackage.getSelectedItem(), InstallTask.Mode.UNINSTALL);
        it.setOnSucceeded(e -> Dialogs.createInformationDialog("Process completed with Exit Code: " + e.getSource().getValue().toString()));
        it.setOnFailed(e ->
                Dialogs.createErrorDialog(
                        new Exception(e.getSource().getException()),
                        true,
                        AppSettings.getInstance().isDebugModeEnabled()));
        installTaskPool.submit(it);
    }
    protected void handle_openTutorial() {
        try {
            Desktop.getDesktop().browse(new URI(AppSettings.YOUTUBE_TUTORIAL));
        } catch (Exception e) {
            Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_install() {
        if (!sanityCheck()) return;
        handle_install_init(false);
    }
    protected void handle_silent_install() {
        handle_install_init(true);
    }
    protected void handle_install_init(boolean silentInstall) {
        if (!sanityCheck()) return;
        selectedPackage.getSelectedItems().forEach(pkg -> {
            InstallTask theInstall;
            try {
                if (silentInstall)
                    theInstall = new InstallTask(pkg, InstallTask.Mode.INSTALL_SILENT);
                else
                    theInstall = new InstallTask(pkg, InstallTask.Mode.INSTALL);
                if (!silentInstall)
                    theInstall.setOnSucceeded(e -> Dialogs.createInformationDialog("Process completed with Exit Code: " + e.getSource().getValue().toString()));
                theInstall.setOnFailed(e ->
                        Dialogs.createErrorDialog(
                                new Exception(e.getSource().getException()),
                                true,
                                AppSettings.getInstance().isDebugModeEnabled()));
                installTaskPool.submit(theInstall);
            } catch (Exception e) {
                Dialogs.createErrorDialog(e, true, false);
            }
        });
    }
    protected void handle_open_downloads() {
        try {
            R.stage.getScene().setRoot(
                    DownloadManagerController.createInstance().setStage(R.stage).setPreviousNode(MainViewController.createInstance())
            );
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_download() {
        if (!sanityCheck()) return;
        if (!AppSettings.getInstance().isAutoDownloadEnabled() && Dialogs.createPromptDialog("Do you want to download the installer?") == Dialogs.PROMPT.NO)
            return; // if the user doesn't want to download the installer and the Auto Download setting isn't enabled then exit

        selectedPackage.getSelectedItems().forEach(pkg -> {
            try{
                DownloadTask theDownload = new DownloadTask(pkg.getPackageURL());
                theDownload.setSaveFile(pkg.getPackageName());
                theDownload.setURL(pkg.getPackageURL(), true);
                theDownload.setTitle(pkg.getName());
                DownloadManagerController.createInstanceNoException().addDownload(theDownload);
            } catch (IOException e) {
                Dialogs.createErrorDialog(e, true, false);
            }
        });
        handle_open_downloads();
    }
    protected void handle_open_changelog() {
        try {
            Desktop.getDesktop().browse(new URI(AppSettings.CHANGE_LOG_WEBSITE));
        } catch (Exception e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_export_toFile() {
        try {
            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("Choose Save Folder!");
            File theDir = dc.showDialog(null);
            if (theDir == null) return;
            String tmpLoc = PackageLoader.createInstance().getLocation();
            PackageLoader.createInstance().setLocation(theDir.toString());
            PackageLoader.createInstance().exportXML(selectedPackage.getSelectedItems().toArray(new Package[0]));
            PackageLoader.createInstance().setLocation(tmpLoc);
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected void handle_export_toEmail() {
        if(AppSettings.getInstance().useSystemDefaultEmailProgram()){
            try {
                //Desktop.getDesktop().mail(new URI("mailto:"));
                Desktop.getDesktop().mail();
            } catch (Exception e) {
                Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
            }
            return;
        }

        try {
            EmailViewController
                    .getInstance(
                            AppSettings.getInstance().getSetting(AppSettings.GMAIL_USERNAME, ""),
                            AppSettings.getInstance().getSetting(AppSettings.GMAIL_PASSWORD, "")
                    )
                    .setPreviousNode(MainViewController.createInstance())
                    .setStage(R.stage);

            String[] pkgList = new String[selectedPackage.getSelectedItems().size()];
            for (int i=0; i<pkgList.length; i++)
                pkgList[i] = AppSettings.PACKAGES_DIRECTORY + "/" + selectedPackage.getSelectedItems().get(i).getPkgFileName();
            EmailViewController.getInstance().setAttachments(pkgList);
            R.stage.getScene().setRoot(EmailViewController.getInstance());
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }
    protected boolean sanityCheck() {
        //if (pkgList.isEmpty())
        //    return false; // There is no package files loaded.

        if (installTaskPool.isRunning())
            return false; // The InstallTask is already running.

        //TODO: valid the commenting of this code
        //if (downloadTaskPool.isRunning())
        //    return false; // The InstallTask is already running.

        //pkgList.retainAll(selectedPackage.getSelectedItems());

        return true;
    }

    public void reloadPackages() { reloadPackages(null); }
    protected void reloadPackages(String theDir) {
        lvItems.getItems().clear();
        try {
            if(theDir != null && !theDir.isEmpty()) {
                R.packageLoader.setLocation(theDir).loadXML(lvItems.getItems());
                return;
            }
            if (AppSettings.getInstance().isLastUsedDirectoryEnabled() && Files.exists(Paths.get(AppSettings.getInstance().getLastUsedDirectory(AppSettings.PACKAGES_DIRECTORY))))
                R.packageLoader.setLocation(AppSettings.getInstance().getLastUsedDirectory(AppSettings.PACKAGES_DIRECTORY)).loadXML(lvItems.getItems());
            else
                R.packageLoader.setLocation(AppSettings.PACKAGES_DIRECTORY).loadXML(lvItems.getItems());
        } catch (IOException e) {
            createErrorDialog(e, true, AppSettings.getInstance().isDebugModeEnabled());
        }
    }

    public void shutdown() {
        handle_close(true);
    }
}
