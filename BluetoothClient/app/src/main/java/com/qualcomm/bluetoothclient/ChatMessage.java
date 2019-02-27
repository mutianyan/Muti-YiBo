package com.qualcomm.bluetoothclient;

public class ChatMessage {

	private String message;
	private boolean isSiri;
	
	public ChatMessage(String message, boolean siri) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.isSiri = siri;
	}

	public String getMessage() {
		return message;
	}


	public boolean isSiri() {
		return isSiri;
	}

	
	
}
