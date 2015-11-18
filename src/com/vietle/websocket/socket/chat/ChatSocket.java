package com.vietle.websocket.socket.chat;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.vietle.websocket.socket.VietLeSocket;
import com.vietle.websocket.socket.WebSocketIfc;

@ServerEndpoint("/chatsocket")
public class ChatSocket implements WebSocketIfc{
	private static Session session = null;
	
	public ChatSocket() {}

	@OnOpen
	@Override
	public void onOpen(Session session) {
		if(ChatSocket.session == null){
			setSession(session);
		} else {
			sessionNotAvailable(session);
		}
		System.out.println("--------Chat socket open--------");
		System.out.println("***chat session id: " + session.getId());
	}
	
	@OnMessage
	@Override
	public void onMessage(String data, Session session) {
		System.out.println("--------Message from client--------");
		System.out.println("***Message from client is: " + data);
		sendMessage(new Date().toString() + " Hello there client!");
	} 

	@OnClose
	@Override
	public void onClose(Session session) {
		setSession(null);
		if(VietLeSocket.getSession() != null){
			try {
				VietLeSocket.getSession().close();
			} catch (IOException e) {
				System.out.println("Error while closing socket:\n" + e.getMessage());
			}
		}
		System.out.println("--------Chat socket closed--------");
	}

	/**
	 * Send a message back to the client.
	 * @param message
	 */
	public static void sendMessage(String message){
		try {
			String messageToClient = message + " | id: " + getSession().getId();
			getSession().getBasicRemote().sendText(messageToClient);
		} catch (IOException e) {
			System.out.println("Error when sending message to client:\n" + e.getMessage());
		}
	}
	
	@Override
	public void onError(Throwable t) {

	}

	@OnError
	@Override
	public void onError(Throwable t, Session session) {
		try {
			System.out.println("------Error in VietLeSocket-------");
			String errorMsg = "Error:\n" + t.getMessage();
			sendMessage(errorMsg);
			getSession().close();
		} catch (IOException e) {
			System.out.println("Error while closing socket:\n" + e.getMessage());
		}
	}
	/**
	 * A session already established.
	 * @param session
	 */
	public void sessionNotAvailable(Session session) {
		try {
			session.getBasicRemote().sendText("A connection is already established. " + "The current connection must be released before a new connection can be made");
		} catch (IOException e) {
			System.out.println("Error sending text:\n" + e.getMessage());
		}
	}
	

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		ChatSocket.session = session;
	}
}
