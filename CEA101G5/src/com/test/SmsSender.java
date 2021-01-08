package com.test;
//Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SmsSender {

	public static final String ACCOUNT_SID = "AC7d57641ebe9e035722647a8aebec3ab3";
    public static final String AUTH_TOKEN = "c25fd56d29afebf3ab4dbc81e240278a";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+886985845289"), // to
                        new PhoneNumber("+14074567528"), // from
                        "Hello 新訂單")
                .create();

        System.out.println(message.getSid());
    }
    

}
