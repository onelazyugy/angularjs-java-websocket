package com.vietle.websocket.socket.chat;

import java.io.IOException;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.vietle.websocket.socket.WebSocketIfc;

@ServerEndpoint("/chatsocket")
public class ChatSocket implements WebSocketIfc{
	private static Session session = null;
	
	public ChatSocket() {}

	@Override
	public void onOpen(Session session) {
		System.out.println("session id: " + session.getId());
		if(ChatSocket.session == null){
			setSession(session);
		} else {
			sessionNotAvailable(session);
		}
	}
	
	@Override
	public void onMessage(String data, Session session) {
		
	}

	@Override
	public void onClose(Session session) {

	}

	@Override
	public void onError(Throwable t) {

	}

	@Override
	public void onError(Throwable t, Session session) {

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
	

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
