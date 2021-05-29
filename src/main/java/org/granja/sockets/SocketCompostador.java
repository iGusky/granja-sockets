/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.granja.sockets;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author gusky
 */
@ServerEndpoint("/lombricomposta")
public class SocketCompostador {

    @OnMessage
    public String onMessage(String message) {
        return message;
    }
    
}
