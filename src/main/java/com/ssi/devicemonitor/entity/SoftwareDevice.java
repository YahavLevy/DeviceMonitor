package com.ssi.devicemonitor.entity;

public class SoftwareDevice extends Device {

    private String manufacturer;
    private String deviceType;
    private String version;
    private String installation_data_and_time;
    public SoftwareDevice(String name) {
        super(name);
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public void setInstallation_data_and_time(String installation_data_and_time){
        this.installation_data_and_time = installation_data_and_time;
    }

    public String getManufacturer(){
        return manufacturer;
    }
    public String getDeviceType(){
        return deviceType;
    }

    public String getVersion(){
        return version;
    }

    public String getInstallation_data_and_time(){
        return installation_data_and_time;
    }
}
