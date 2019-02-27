package com.chenyinbo.java;



import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
 

public class BluetoothJavaServer {
	private StreamConnectionNotifier mStreamConnectionNotifier = null;
	private StreamConnection mStreamConnection = null;

 
	public static void main(String[] args) {
		new BluetoothJavaServer();
	}
 
	public BluetoothJavaServer() {
		try {
			mStreamConnectionNotifier = (StreamConnectionNotifier) Connector
					.open("btspp://localhost:0000110100001000800000805F9B34FB");
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Function 'Run' is ready! Waiting...");
					while (true) {
						mStreamConnection = mStreamConnectionNotifier.acceptAndOpen();
						
						System.out.println("Have connected!");
 
						InputStream is = mStreamConnection.openInputStream();
 
						byte[] buffer = new byte[1024];
 
						System.out.println("Received buffer!");
					
						int size =0;
						while((size = is.read(buffer)) >0) {	
							
							String s = new String(buffer,0,size,"UTF-8");
							RobotKey androidRobot = new RobotKey();
							androidRobot.keyBoard(s);
							
							System.out.println(s);
							buffer = null;
							buffer = new byte[1024];
						}
 
						is.close();
						mStreamConnection.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
 
		}).start();
		
	}
	
}