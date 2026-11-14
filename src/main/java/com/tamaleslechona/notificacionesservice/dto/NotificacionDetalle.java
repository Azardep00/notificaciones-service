package com.tamaleslechona.notificacionesservice.dto;

import java.time.Instant;

import com.tamaleslechona.notificacionesservice.model.Notificacion;

// Lo que devuelve el API al consultar notificaciones guardadas.
public record NotificacionDetalle(
        String id,
        String idPedido,
        String correoCliente,
        String estadoNuevo,
        String canal,
        Instant enviadaEn) {

    public static NotificacionDetalle desde(Notificacion n) {
        return new NotificacionDetalle(
                n.getId(), n.getIdPedido(), n.getCorreoCliente(),
                n.getEstadoNuevo(), n.getCanal(), n.getEnviadaEn());
    }
}
