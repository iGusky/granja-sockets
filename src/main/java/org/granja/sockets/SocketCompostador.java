/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.granja.sockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.granja.pojos.Registro;
import org.granja.rest.RestClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author gusky
 */
@ServerEndpoint("/compostador")
public class SocketCompostador {

     RestClient rest = new RestClient();
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    Registro nuevoRegistro = new Registro();

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {
        
        Object messageRecived = JSONValue.parse(message);
        JSONObject messageJson = (JSONObject)messageRecived;
        System.out.println(messageJson);
        
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(message);
            }
        }
        
        switch(messageJson.get("action").toString()){
            case "guardar":
                nuevoRegistro.setId_remitente(Long.parseLong(messageJson.get("id_remitente").toString()));
                nuevoRegistro.setId_destinatario(Long.parseLong(messageJson.get("id_destinatario").toString()));
                nuevoRegistro.setMensaje(messageJson.get("mensaje").toString());
                nuevoRegistro.setFecha(messageJson.get("fecha").toString());
//                rest.postJson(nuevoRegistro);
                break;
        }
         
    }
    
    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
        
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
}
