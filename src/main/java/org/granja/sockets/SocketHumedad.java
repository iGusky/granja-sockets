/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.granja.sockets;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import org.granja.pojos.Registro;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.granja.rest.RestClient;
import org.json.simple.JSONArray;


/**
 *
 * @author gusky
 */
@ServerEndpoint("/humedad")
public class SocketHumedad{
    RestClient rest = new RestClient();
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    Registro nuevoRegistro = new Registro();
  
    @OnMessage
    public void onMessage(String message, Session session) throws URISyntaxException, IOException, EncodeException{
  
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
                int index = messageJson.get("zona").hashCode();
                System.out.println(index);
                    
        }
        

    }
    
    @OnOpen
    public void onOpen (Session peer) {
        System.out.println("Conectado: "+peer.getId());
        System.out.println("Dispositivos conectados "+peers.size());
        peers.add(peer);
        
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
    
}
