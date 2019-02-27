package com.chenyinbo.java;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotKey {

	private Robot robot = null;
	
	public RobotKey() {
		try {
			robot = new Robot();
			
		}catch(AWTException e) {
			e.printStackTrace();
			
		}
	}
	public void keyBoard(String key) {
		String str1="a";
		String str2="d";
		String str3="w";
		String str4="s";
		String str5="1";
		String str6="2";
		String str7="3";
		
		if(str1.equals(key)) {
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_LEFT);
			System.out.println("a");
		}else if(str2.equals(key)) {
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			System.out.println("d");
		}else if(str3.equals(key)) {
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			System.out.println("w");
		}else if(str4.equals(key)) {
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			System.out.println("s");
		}else if(str5.equals(key)){
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_1);
			System.out.println("1");
		}else if(str6.equals(key)){
			robot.keyPress(KeyEvent.VK_2);
			robot.keyRelease(KeyEvent.VK_2);
			System.out.println("2");
		}else if(str7.equals(key)){
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_3);
			System.out.println("3");
		}else {
			System.out.println("Input Error");
		}
		
		
//		if(key == "a") {
//			robot.keyPress(KeyEvent.VK_LEFT);
//			robot.keyRelease(KeyEvent.VK_LEFT);
//		}else if(key == "d") {
//			robot.keyPress(KeyEvent.VK_RIGHT);
//			robot.keyRelease(KeyEvent.VK_RIGHT);
//		}else if(key == "w") {
//			robot.keyPress(KeyEvent.VK_UP);
//			robot.keyRelease(KeyEvent.VK_UP);
//		}else if(key == "s") {
//			robot.keyPress(KeyEvent.VK_DOWN);
//			robot.keyRelease(KeyEvent.VK_DOWN);
//		}else {
//			System.out.println(key);
//		}
	}
}
