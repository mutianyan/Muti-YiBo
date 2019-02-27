package com.chenyinbo.java;

public class TransXY {
	
	public static boolean isNull(String m) {
		//String m = null;
		
		if(m == null||m.isEmpty()||m.trim().equals("")) {
			return true;
		} 
		return false;
	}
	
	
	
	
	public int[] move(byte[] arr) {
		
		String data = new String(arr);
		int[] mousedata = new int[10];
		String[] aa;
		
		if(isNull(data)) {
			mousedata = new int[10];
		}else {
			
		 aa = data.split("m");
		
		mousedata = new int[aa.length];
		for(int i = 0; i< aa.length;i++) {
			mousedata[i] = Integer.parseInt(aa[i]);
			 int x = mousedata[0];
			 int y = mousedata[1];
			 int z = mousedata[2];
			System.out.println("x"+x+"y"+y+"z"+z);
			
		}}
		aa = null;
		return mousedata;
	}

}
