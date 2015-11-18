package com.vietle.websocket.socket.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	
	public ChatSocket() {}

	@OnOpen
	@Override
	public void onOpen(Session session) {
		System.out.println("--------Chat socket open--------");
		sendConnectedMessage(session, session.getId() + " connected!");
		try{
			session.getBasicRemote().sendText(session.getId() + " connected!");
		}catch(IOException e){
			System.out.println("Error while opening socket:\n" + e.getMessage());
		}
		sessions.add(session);
	}
	
	@OnMessage
	@Override
	public void onMessage(String data, Session session) {
		System.out.println("--------Message from client--------");
		System.out.println(session.getId() + " say: " + data);
		sendMessage(data); 
	} 

	@OnClose
	@Override
	public void onClose(Session session) {
		sessions.remove(session);
		System.out.println("Session " + session.getId() + " has ended");
		sendMessage(session.getId() + " disconnected!");
	}

	/**
	 * Send a message back to the client.
	 * @param message
	 */
	public static void sendMessage(String message){
		for(Session s: sessions){
			try{
				System.out.println("sending message: " + message);
				s.getBasicRemote().sendText(message);
			}catch(IOException e){
				System.out.println("Error when sending message to client:\n" + e.getMessage());
			}
		}
	}
	
	public static void sendConnectedMessage(Session s, String message){
		try{
			System.out.println("sending message: " + message);
			s.getBasicRemote().sendText(message);
		}catch(IOException e){
			System.out.println("Error when sending message to client:\n" + e.getMessage());
		}
	}
	
	@Override
	public void onError(Throwable t) {}

	@OnError
	@Override
	public void onError(Throwable t, Session session) {
		try {
			System.out.println("------Error in ChatSocket-------");
			String errorMsg = "Error:\n" + t.getMessage();
			sendMessage(errorMsg);
			getSession().close();
		} catch (IOException e) {
			System.out.println("Error while closing socket:\n" + e.getMessage());
		}
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		ChatSocket.session = session;
	}
}
