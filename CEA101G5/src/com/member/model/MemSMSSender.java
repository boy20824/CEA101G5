package com.member.model;

//Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MemSMSSender {

	public static final String ACCOUNT_SID = "ACd807bf62b6cf033835cc5d6e7f0c8a52";
	public static final String AUTH_TOKEN = "3e527d9f5bb69432299ff88f97fccd6e";

		
		public void sendSMS(String memPhone,String messageText) {
			
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			String sendPhone = memPhone.substring(1);
			Message message = Message.creator
					(new PhoneNumber("+886"+sendPhone), // to
					new PhoneNumber("+12055574165"), // from
					messageText).create();

			System.out.println(message.getSid());
		}

}
