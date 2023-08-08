package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceMonitorController {
    @FXML
    private ListView<Device> deviceListView;

    @FXML
    private TextField deviceNameTextField;

    @FXML
    private Button addDeviceButton;

    @FXML
    private RadioButton software_radio_button;
    @FXML
    private RadioButton hardware_radio_button;

    @FXML
    private TextArea deviceInformation;

    @FXML
    private Button hideInformationButton;
    private DeviceMonitor deviceMonitor;


    public void initialize() {
        deviceMonitor = new DeviceMonitor();

        deviceMonitor.addDevice(new GeneralDevice("Device 1"));
        deviceMonitor.addDevice(new GeneralDevice("Device 2"));
        deviceMonitor.addDevice(new GeneralDevice("Device 3"));

        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());

        // Add context menu to ListView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");

        removeItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceMonitor.removeDevice(selectedDevice);
            }
        });
        MenuItem infoItem = new MenuItem("Information");
        infoItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if(selectedDevice instanceof SoftwareDevice){
                show_software_device_info((SoftwareDevice)selectedDevice);
            }
            else if(selectedDevice instanceof HardwareDevice){
                show_hardware_device_info((HardwareDevice) selectedDevice);
            }
        });

        contextMenu.getItems().addAll(removeItem);
        contextMenu.getItems().addAll(infoItem);
        deviceListView.setContextMenu(contextMenu);

    }

    private class DataUpdateTask extends TimerTask {

        private Random random = new Random();
        @Override
        public void run() {
            refreshListView();
        }

    }
    @FXML
    private void addDevice() {
        String deviceName = deviceNameTextField.getText();
        if(software_radio_button.isSelected()){
            Device newDevice = new SoftwareDevice(deviceName);
            deviceMonitor.addDevice(newDevice);
            deviceNameTextField.clear();
        }
        else if(hardware_radio_button.isSelected()){
            Device newDevice = new HardwareDevice(deviceName);
            deviceMonitor.addDevice(newDevice);
            deviceNameTextField.clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You must choose device type");
            alert.showAndWait();
        }
    }

    public void refreshListView() {
        deviceListView.refresh();
    }

    private class DeviceListCell extends ListCell<Device> {

        @Override
        protected void updateItem(Device device, boolean empty) {
            super.updateItem(device, empty);

            if (device == null || empty) {
                setText(null);
                setGraphic(null);
                setStyle(""); // Reset the cell style
            } else {
                setText(device.getName() + " - " + device.getStatus());

                // Set the cell style based on the device status
                if (device.getStatus().equals("Online")) {
                    setStyle("-fx-text-fill: green;");
                } else if (device.getStatus().equals("Offline")) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle(""); // Reset the cell style
                }
            }
        }
    }
    private void show_hardware_device_info(HardwareDevice selectedDevice) {
        deviceInformation.setVisible(true);
        hideInformationButton.setVisible(true);
        if(selectedDevice.getManufacturer()!=null) deviceInformation.appendText(selectedDevice.getManufacturer()+"\n");
        if(selectedDevice.getDeviceType()!=null) deviceInformation.appendText(selectedDevice.getDeviceType()+"\n");
        if(selectedDevice.getLocation()!=null) deviceInformation.appendText(selectedDevice.getLocation()+"\n");
        if(selectedDevice.getVersion()!=null) deviceInformation.appendText(selectedDevice.getVersion()+"\n");
        if(selectedDevice.getMAC_Address()!=null) deviceInformation.appendText(selectedDevice.getMAC_Address()+"\n");
    }

    private void show_software_device_info(SoftwareDevice selectedDevice) {
        deviceInformation.setVisible(true);
        hideInformationButton.setVisible(true);
        if(selectedDevice.getManufacturer()!=null) deviceInformation.appendText(selectedDevice.getManufacturer()+"\n");
        if(selectedDevice.getDeviceType()!=null) deviceInformation.appendText(selectedDevice.getDeviceType()+"\n");
        if(selectedDevice.getVersion()!=null) deviceInformation.appendText(selectedDevice.getVersion()+"\n");
        if(selectedDevice.getInstallation_data_and_time()!=null) deviceInformation.appendText(selectedDevice.getInstallation_data_and_time()+"\n");

    }
    public void HideInformation(){
        deviceInformation.clear();
        deviceInformation.setVisible(false);
        hideInformationButton.setVisible(false);
    }
}
