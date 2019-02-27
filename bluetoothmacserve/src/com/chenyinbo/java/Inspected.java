package com.chenyinbo.java;

public class Inspected {
	
	public void test(String tyu){
		
		char ch;
		int character = 0, blank = 0, number = 0;
		
		for(int i = 0; i< tyu.length();i++) {
			
			ch = tyu.charAt(i);
			if((ch>='a'&&ch<='z')||(ch>='A'&&ch>='Z')) {
				character++;
				
			}else if(ch==' ') {
				blank++;
			}else if((ch>='0'&&ch<='9')) {
				number++;
			}
		}
		System.out.println("character="+character+" blank="+blank+" number="+number);
	}

}
