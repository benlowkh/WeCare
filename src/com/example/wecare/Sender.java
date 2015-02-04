package com.example.wecare;

import android.telephony.SmsManager;

public class Sender {
	
	protected void sendMsg(String theNumber, String myMsg){
		
		
	SmsManager sms = SmsManager.getDefault();
	sms.sendTextMessage(theNumber, null, myMsg, null, null);
			
	}}
