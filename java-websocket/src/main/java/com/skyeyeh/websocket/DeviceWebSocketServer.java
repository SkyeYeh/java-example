package com.skyeyeh.websocket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Websocket Example.
 */
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {
    @OnOpen
    public void open(Session session) {
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            basicRemote.sendText("open");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session " + session.getId() + " has ended");
    }

    @OnError
    public void error(Session session) {

    }

    @OnMessage
    public void message(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
