package com.tamaleslechona.notificacionesservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamaleslechona.notificacionesservice.dto.NotificacionRequest;
import com.tamaleslechona.notificacionesservice.dto.NotificacionResponse;

import jakarta.validation.Valid;

// Microservicio independiente: recibe eventos de cambio de estado de
// pedidos desde el backend principal via REST sincrono (RestClient) y
// simula el envio de la notificacion al cliente (en un escenario real
// seria un correo o push; aqui se registra en el log del servicio).
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @PostMapping("/pedido")
    public NotificacionResponse notificarCambioEstado(@Valid @RequestBody NotificacionRequest req) {
        System.out.printf(
                "[NOTIFICACION] Pedido #%d -> %s. Se notificaria a: %s%n",
                req.idPedido(), req.estadoNuevo(), req.correoCliente());

        String mensaje = "Se notifico a " + req.correoCliente()
                + " que su pedido #" + req.idPedido() + " ahora esta: " + req.estadoNuevo();

        return new NotificacionResponse(true, mensaje);
    }
}
