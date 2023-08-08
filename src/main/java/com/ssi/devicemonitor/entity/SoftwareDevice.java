package com.ssi.devicemonitor.entity;

public class SoftwareDevice extends Device {

    public String manufacturer;
    public String deviceType;
    public String version;
    public String installation_data_and_time;
    public SoftwareDevice(String name) {
        super(name);
    }
}
