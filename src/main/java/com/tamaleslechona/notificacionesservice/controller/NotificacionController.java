package com.tamaleslechona.notificacionesservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamaleslechona.notificacionesservice.dto.NotificacionRequest;
import com.tamaleslechona.notificacionesservice.dto.NotificacionResponse;
import com.tamaleslechona.notificacionesservice.service.NotificacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping("/pedido")
    public NotificacionResponse notificarCambioEstado(@Valid @RequestBody NotificacionRequest req) {
        service.registrar(req.idPedido(), req.correoCliente(), req.estadoNuevo(), "REST");

        String mensaje = "Se notifico a " + req.correoCliente()
                + " que su pedido #" + req.idPedido() + " ahora esta: " + req.estadoNuevo();
        return new NotificacionResponse(true, mensaje);
    }
}