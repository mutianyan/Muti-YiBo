package com.qualcomm.bluetoothclient;

public class BluetoothMsg {


    public enum ServerOrCilent {
        NONE,  

    };

    public static ServerOrCilent serviceOrCilent = ServerOrCilent.NONE;
    public static String BlueToothAddress = null, lastblueToothAddress = null;
    public static boolean isOpen = false;
    
}
