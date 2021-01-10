package com.member.model;

//Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MemSMSSender {

	public static final String ACCOUNT_SID = "ACd807bf62b6cf033835cc5d6e7f0c8a52";
	public static final String AUTH_TOKEN = "5a5437b229b95f39202dec5be94ca6f6";

		
		public void sendSMS(String memPhone,String messageText) {
			
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			String sendPhone = memPhone.substring(1);
			Message message = Message.creator
					(new PhoneNumber("+886"+sendPhone), // to
					new PhoneNumber("+12055574165"), // from
					messageText).create();

			System.out.println(message.getSid());
		}
		
//		以下為測試
//		public static void main(String[] args) {
//			String phone ="0921842859";
//			String message ="0921842859";
//			
//			MemSMSSender.sendSMS(phone,message);
//		}
		
		
//		public void sendSMS(String memPhone,String messageText) {
//
//
//			  try {
//			      String server  = "202.39.54.130"; //SMS Gateway IP
//			      int port	     = 8000;            //SMS Gateway Port
//
//			      String user    = "0921842859";//帳號
//			      String passwd  = ""; //密碼
//			      String tel     = memPhone; //手機號碼
//			      String message = new String(messageText.getBytes(),"big5"); //簡訊內容
//
//			      //----建立連線 and 檢查帳號密碼是否錯誤
//			      sms2 mysms = new sms2();
//			      int ret_code = mysms.create_conn(server,port,user,passwd) ;
//			      if( ret_code == 0 ) {
//			           System.out.println("帳號密碼Login OK!");
//			      } else {
//			      	   System.out.println("帳號密碼Login Fail!");
//			           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
//			           //結束連線
//			           mysms.close_conn();
//			           return ;
//			      }
//
//			      //傳送文字簡訊
//			      //如需同時傳送多筆簡訊，請多次呼叫send_message()即可。
//			      ret_code=mysms.send_text_message(tel,message);
//			      if( ret_code == 0 ) {
//			           System.out.println("簡訊已送到簡訊中心!");
//			           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
//			      } else {
//			           System.out.println("簡訊傳送發生錯誤!");
//			           System.out.print("ret_code="+ret_code+",");
//			           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
//			           //結束連線
//			           mysms.close_conn();
//			           return ;
//			      }
//
//			      //結束連線
//			      mysms.close_conn();
//
//			  }catch (Exception e)  {
//
//			      System.out.println("I/O Exception : " + e);
//			   }
//			 }

}
