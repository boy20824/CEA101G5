package com.foodorder.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/TogetherWS/{storeId}")
public class FrontStoreEndWebSocket {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
    //該Session為websocket的
	
	@OnOpen  //當連線開啟時(對應到init)  
	public void onOpen(@PathParam("storeId") String storeId, Session userSession) throws IOException {
					//連結時可以搭配變數代入 	
		sessionsMap.put(storeId,userSession);
		System.out.println(userSession.getId());
		System.out.println(storeId);
	}

	@OnMessage //當接收到訊息時(service)
	public void onMessage(Session userSession,String message) {
	
			Session session =sessionsMap.get(message);
			if (session.isOpen())
				session.getAsyncRemote().sendText(message); //送出來自前端的json物件給每個使用者
				
	}

	@OnClose  //當連線關閉時(destory)
	public void onClose(Session userSession, CloseReason reason,@PathParam("storeId") String storeId) {
		sessionsMap.remove(storeId);
	}

	@OnError //當連連線發生例外時
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
}
