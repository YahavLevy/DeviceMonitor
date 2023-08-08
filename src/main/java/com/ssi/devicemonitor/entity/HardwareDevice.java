package com.ssi.devicemonitor.entity;

public class HardwareDevice  extends Device{

    private String manufacturer = null;
    private String deviceType = null;
    private String location = null;
    private String version = null;
    private String MAC_Address = null;

    public HardwareDevice(String name) {
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
    public void setLocation(String location){
        this.location = location;
    }
    public void setMAC_Address(String MAC_Address){
        this.MAC_Address = MAC_Address;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public String getDeviceType(){
        return deviceType;
    }
    public String getLocation(){
        return location;
    }
    public String getVersion(){
        return version;
    }
    public String getMAC_Address(){
        return MAC_Address;
    }

}
