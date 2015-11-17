package com.vietle.websocket.socket.chat;

import javax.websocket.Session;

import com.vietle.websocket.socket.WebSocketIfc;

public class ChatSocket implements WebSocketIfc{

	public ChatSocket() {
		
	}

	@Override
	public void onClose(Session session) {

	}

	@Override
	public void onOpen(Session session) {

	}

	@Override
	public void onMessage(String data, Session session) {
	
	}

	@Override
	public void onError(Throwable t) {

	}

	@Override
	public void onError(Throwable t, Session session) {

	}

}
