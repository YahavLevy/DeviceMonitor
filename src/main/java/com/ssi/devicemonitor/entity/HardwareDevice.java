package com.ssi.devicemonitor.entity;

public class HardwareDevice  extends Device{

    public String manufacturer;
     public String deviceType;
     public String location;
    public String version;
    public String MAC_Address;

    public HardwareDevice(String name) {
        super(name);
    }


}
