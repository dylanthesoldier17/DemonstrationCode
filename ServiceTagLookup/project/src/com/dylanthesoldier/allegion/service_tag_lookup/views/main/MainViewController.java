package com.dylanthesoldier.allegion.service_tag_lookup.views.main;

import com.dylanthesoldier.allegion.service_tag_lookup.model.Drivers;
import com.dylanthesoldier.allegion.service_tag_lookup.model.EOLs;
import com.dylanthesoldier.allegion.service_tag_lookup.model.R;
import com.dylanthesoldier.allegion.service_tag_lookup.views.process.ProcessViewController;
import com.dylanthesoldier.allegion.service_tag_lookup.views.settings.Settings;
import com.dylanthesoldier.allegion.service_tag_lookup.views.settings.SettingsViewController;
import com.dylanthesoldier.assistlibfx.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

public class MainViewController extends BaseSingletonController{

    // ------------
    // --- FXML ---
    // ------------
    @FXML
    private Label mL_ComputerDesc;

    @FXML
    private Hyperlink mHP_ServiceTag;

    @FXML
    private Label mL_Model;

    @FXML
    private Label mL_User;

    @FXML
    private Label mL_EOL_Date;

    @FXML
    private MenuItem mMI_EOL_ShowAllInfo;

    @FXML
    private Button btnDrivers;

    @FXML
    private MenuItem mMI_BtnDrivers_OpenFolder;

    @FXML
    private Button btnDellDetect;

    @FXML
    private MenuItem mMI_BtnDetectTool_OpenFolder;

    @FXML
    private MenuItem mMI_Settings;

    @FXML
    private MenuItem mMI_Close;

    @FXML
    private MenuItem mMI_Find_ServiceTag;

    @FXML
    private MenuItem mMI_Find_IP_Resolve;

    private static MainViewController mvc;
    private final TaskPool taskPool = TaskPoolManager.createInstance().createPool(getClass().getName(), TaskPool.PoolType.FIXED_THREAD_POOL, Runtime.getRuntime().availableProcessors(), null);
    private static final String MAIN_VIEW_FXML = "main_view.fxml";

    protected MainViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_VIEW_FXML));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        setupLabels();
        setupHandlers();
        loadSpreadsheetAsync();
    }

    private void loadSpreadsheetAsync() {
        if(Settings.getSpreadsheetLocation().isEmpty())
            return;

        Task<Row> loadSpreadsheetTask = EOLs.getSpreadsheetTask_CellFilter(theCell -> {
            if(theCell == null) return false;
            if(theCell.toString().toLowerCase().contains(R.ComputerServiceTag.toLowerCase())) return true;
            return false;
        });
        loadSpreadsheetTask.setOnFailed(e ->
                Dialogs.createErrorDialog(new Exception(e.getSource().getException()), true, false)
        );
        loadSpreadsheetTask.setOnSucceeded(e -> {

            if(e.getSource().getValue() == null)
                Dialogs.createInformationDialog("Couldn't get a result from the spreadsheet!");

            Row theRow;
            if (e.getSource().getValue() instanceof  Row)
                theRow = (Row) e.getSource().getValue();
            else
                return;

            // --- Create Mouse Click Listener that'll calculate dates & compare ----
            mL_EOL_Date.setOnMouseClicked(me -> {
                if (me.getButton() != MouseButton.PRIMARY) return;
                dateCheck_showDialogResults(theRow);
            });


            int columnDateIndex = Integer.parseInt(Settings.getColumnDateIndex())-1;
            mL_EOL_Date.setText("EOL: " + theRow.getCell(columnDateIndex).toString());
        });

        taskPool.submit(loadSpreadsheetTask);
    }
    private void setupLabels() {
        mL_ComputerDesc.setText(mL_ComputerDesc.getText() + " " + R.ComputerDescription);
        mL_Model.setText(mL_Model.getText() + " " +R.ComputerModel);
        mL_User.setText(mL_User.getText() + " " +R.ComputerUser);
        mHP_ServiceTag.setText(mHP_ServiceTag.getText() + " " +R.ComputerServiceTag);
        mL_EOL_Date.setOnMouseClicked(me -> {
            if(me.getButton() == MouseButton.PRIMARY)
                Dialogs.createInformationDialog("Can't get the EOL right now!");
        });
    }
    private void setupHandlers() {
        mMI_EOL_ShowAllInfo.setOnAction(this::handle_ShowAllEOL_info);
        mMI_Close.setOnAction(e -> Platform.exit());
        mHP_ServiceTag.setOnMouseClicked(this::handle_open_website);
        btnDrivers.setOnMouseClicked(this::handle_install_drivers);
        btnDellDetect.setOnMouseClicked(this::handle_install_detect_tool);
        mMI_Settings.setOnAction(this::handle_settings_click);
        mMI_BtnDetectTool_OpenFolder.setOnAction(ae -> handle_open_Folder(System.getProperty("user.dir")));
        mMI_BtnDrivers_OpenFolder.setOnAction(ae -> handle_open_Folder(R.ComputerModel));
        mMI_Find_ServiceTag.setOnAction(this::handle_findByServiceTag);
        mMI_Find_IP_Resolve.setOnAction(this::handle_findByIPAddress);
    }

    private void handle_ShowAllEOL_info(ActionEvent actionEvent) {

    }
    private String formatDate(Date theDate){
        String theDateStr = theDate.toString();
        String monthDay = theDateStr.substring(4, 10);
        String year = theDateStr.substring(theDateStr.length() - 5);
        return monthDay + "," + year;
    }
    private void dateCheck_showDialogResults(Row theRow){
        if(theRow == null) {
            Dialogs.createInformationDialog("Can't find anything in the spreadsheet!");
            return;
        }

        int columnDateIndex = Integer.parseInt(Settings.getColumnDateIndex())-1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt(Settings.getDateRange()));
        String theText = "";
        if (theRow.getCell(columnDateIndex).getDateCellValue().before(calendar.getTime()))
            theText = "The device's lease has expired! (" + Settings.getDateRange() + ") Days" +"\n\n";
        else
            theText ="The device's lease is good! (" + Settings.getDateRange() + ") Days" +"\n\n";

        Dialogs.createInformationDialog(theText +
                "Current Date: " + formatDate(new Date()) + "\n\n" + // Current Date
                "EOL Date: " + formatDate(theRow.getCell(columnDateIndex).getDateCellValue()));// Date from Spreadsheet
    }
    private void handle_findByIPAddress(ActionEvent actionEvent) {
        String ipaddr = Dialogs.createTextPromptDialog("Enter Ip Address");
        try {
            InetAddress addr = Inet4Address.getByName(ipaddr);
            Task<Void> theTask = createIP_ResolveCallable(addr);
            theTask.setOnFailed(e -> Dialogs.createErrorDialog(new Exception(e.getSource().getException()), true,false));
            taskPool.submit(theTask);
        } catch (Exception e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }
    private void handle_findByServiceTag(ActionEvent actionEvent) {
        if(Settings.getSpreadsheetLocation().isEmpty()) return;

        String svcTag = Dialogs.createTextPromptDialog("Enter the service tag to find!");
        if (svcTag.isEmpty()) return;
        Task<Row> theTask = EOLs.getSpreadsheetTask_CellFilter(theCell -> {
            if(theCell == null) return false;
            if(theCell.toString().toLowerCase().contains(svcTag.toLowerCase()))
                return true;
            return false;
        });
        theTask.setOnFailed(e -> Dialogs.createErrorDialog(new Exception(e.getSource().getException()), true, false));
        theTask.setOnSucceeded(e ->
            dateCheck_showDialogResults((Row) e.getSource().getValue())
        );
        taskPool.submit(theTask);
    }
    private Task<Void> createIP_ResolveCallable(InetAddress addr){
        return new Task<Void>() {
            String theName_lowerCase = "";
            int index = 0;

            public int incrementIndex(){
                return index++;
            }

            @Override
            public Void call() throws Exception {
                // --- Parse Name to Service Tag ---
                theName_lowerCase = addr.getCanonicalHostName().toLowerCase();
                if(theName_lowerCase.contains("."))
                    theName_lowerCase = theName_lowerCase.split(Pattern.quote("."))[0];
                if(theName_lowerCase.contains("-"))
                    theName_lowerCase = theName_lowerCase.split(Pattern.quote("-"))[2];

                // --- get the specified first row for the information column ---
                index = 0;
                Task<Row> headerRowTask = EOLs.getSpreadsheetTask_GetRow(
                        Integer.parseInt(Settings.getSheetIndex()),
                        Integer.parseInt(Settings.getColumnHeaderIndex())
                );
                taskPool.submit(headerRowTask);
                Row headerRow = headerRowTask.get();
                Task<Row> task_FindTheName = EOLs.getSpreadsheetTask_RowFilter(this::getRowFilter_FindTheName);
                taskPool.submit(task_FindTheName);
                Row theRow = task_FindTheName.get();

                // --- If the name is not in the spread sheet then tell the user ---
                if(theRow == null){
                    Dialogs.createInformationDialog("Can't find the service tag [" + theName_lowerCase + "] in the spreadsheet!");
                    return null;
                }


                // --- Show the row information of the row ---
                String theResult = "";
                int limit = theRow.getPhysicalNumberOfCells();
                int counter = 0;
                while (counter <= limit){
                    if(headerRow == null) {
                       if (theRow.getCell(counter) != null)
                           theResult += theRow.getCell(counter).toString() + "\n";
                    } else {
                        if (theRow.getCell(counter) != null && headerRow.getCell(counter) != null)
                            theResult += headerRow.getCell(counter) + ": \t" + theRow.getCell(counter) + "\n";
                    }
                    counter++;
                }
                Dialogs.createInformationDialog(theResult);
                return null;
            }

            private boolean getRowFilter_FindTheName(Row row) {
                Iterator<Cell> iter = row.iterator();
                while (iter.hasNext()){
                    Cell cell = iter.next();
                    if(cell == null) continue;
                    if (cell.toString().toLowerCase().contains(theName_lowerCase))
                        return true;
                }
                return false;
            }
        };
    }
    private void handle_open_Folder(String folderPath) {
        if(Files.notExists(Paths.get(folderPath))){
            Dialogs.createInformationDialog("Can't find folder: " + folderPath);
            return;
        }

        try {
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }
    private void handle_settings_click(ActionEvent mouseEvent) {
        try {
            R.stage.getScene().setRoot(SettingsViewController.createInstance().setPreviousNode(this).setStage(R.stage));
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }
    private void handle_install_detect_tool(MouseEvent event) {
        if(event.getButton() != MouseButton.PRIMARY)
            return;

        Runnable callback = ()->
                Platform.runLater(
                        ()-> Dialogs.createInformationDialog("Process completed with Exit Code: " + Drivers.exit_code)
                );
        ExceptionHandler exceptionHandler = ex ->
            Platform.runLater(()->{
                Dialogs.createErrorDialog(ex, true, false);
                ex.printStackTrace();
            });
        Drivers.asyncInstallDetectTool(callback, exceptionHandler);
    }
    private void handle_install_drivers(MouseEvent event) {
        if(event.getButton() != MouseButton.PRIMARY || Drivers.isRunning())
            return;

        ExceptionHandler exceptionHandler = ex ->
            Platform.runLater(()->{
                Dialogs.createErrorDialog(ex, true, false);
                ex.printStackTrace();
            });

        Runnable callback = ()->
            Platform.runLater(()->
                    Dialogs.createInformationDialog("Process completed with Exit Code: " + Drivers.exit_code));

        Drivers.asyncInstallDrivers(callback, exceptionHandler);

        try {
            R.stage.getScene().setRoot(ProcessViewController.createInstance());
        } catch (IOException e) {
            Dialogs.createErrorDialog(e, true, false);
        }
    }
    private void handle_open_website(MouseEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("http://www.dell.com/support/home/us/en/04/product-support/servicetag/" + R.ComputerServiceTag + "/warranty?ref=captchasuccess"));
        } catch (Exception ex) {
            Dialogs.createErrorDialog(ex, true, false);
        }
    }

    public static final MainViewController createInstance() throws IOException {
        if(mvc == null) mvc = new MainViewController();
        return mvc;
    }

}
