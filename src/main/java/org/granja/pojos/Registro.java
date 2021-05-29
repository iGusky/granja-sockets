/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.granja.pojos;

import java.sql.Timestamp;

/**
 *
 * @author gusky
 */
public class Registro {
    private Long id_remitente;
    private Long id_destinatario;
    private String fecha;
    private String mensaje;

    public Registro() {
    }

    public Registro(Long id_remitente, Long id_destinatario, String fecha, String mensaje) {
        this.id_remitente = id_remitente;
        this.id_destinatario = id_destinatario;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public Long getId_remitente() {
        return id_remitente;
    }

    public void setId_remitente(Long id_remitente) {
        this.id_remitente = id_remitente;
    }

    public Long getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(Long id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
