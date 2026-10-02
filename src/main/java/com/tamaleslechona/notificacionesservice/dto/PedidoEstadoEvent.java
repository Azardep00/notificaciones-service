package com.tamaleslechona.notificacionesservice.dto;

import java.time.Instant;

// Copia local del evento que publica el API core. Debe tener los mismos
// campos (mismos nombres) que com.tamaleslechona.tamaleslechona.event.PedidoEstadoEvent,
// porque viaja como JSON por el topico.
public record PedidoEstadoEvent(
        Integer idPedido,
        String correoCliente,
        String estadoNuevo,
        Instant ocurridoEn) {
}
