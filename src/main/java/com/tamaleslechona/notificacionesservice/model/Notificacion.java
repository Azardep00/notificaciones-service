package com.tamaleslechona.notificacionesservice.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notificaciones")
public class Notificacion {

    @Id
    private String id;
    private String idPedido;
    private String correoCliente;
    private String estadoNuevo;
    private String canal;
    private Instant enviadaEn;

    public Notificacion() {}

    public Notificacion(String idPedido, String correoCliente, String estadoNuevo, String canal) {
        this.idPedido = idPedido;
        this.correoCliente = correoCliente;
        this.estadoNuevo = estadoNuevo;
        this.canal = canal;
        this.enviadaEn = Instant.now();
    }

    public String getId() { return id; }
    public String getIdPedido() { return idPedido; }
    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }
    public String getEstadoNuevo() { return estadoNuevo; }
    public void setEstadoNuevo(String estadoNuevo) { this.estadoNuevo = estadoNuevo; }
    public String getCanal() { return canal; }
    public Instant getEnviadaEn() { return enviadaEn; }
}