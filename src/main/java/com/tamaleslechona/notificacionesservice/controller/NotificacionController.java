package com.tamaleslechona.notificacionesservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tamaleslechona.notificacionesservice.dto.NotificacionDetalle;
import com.tamaleslechona.notificacionesservice.dto.NotificacionRequest;
import com.tamaleslechona.notificacionesservice.dto.NotificacionResponse;
import com.tamaleslechona.notificacionesservice.dto.NotificacionUpdateRequest;
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

    @GetMapping
    public List<NotificacionDetalle> listar(
            @RequestParam(name = "idPedido", required = false) Integer idPedido,
            @RequestParam(name = "correoCliente", required = false) String correoCliente) {
        return service.listar(idPedido, correoCliente).stream()
                .map(NotificacionDetalle::desde)
                .toList();
    }

    @GetMapping("/{id}")
    public NotificacionDetalle obtener(@PathVariable("id") String id) {
        return NotificacionDetalle.desde(service.obtener(id));
    }

    @PutMapping("/{id}")
    public NotificacionDetalle actualizar(
            @PathVariable("id") String id,
            @Valid @RequestBody NotificacionUpdateRequest datos) {
        return NotificacionDetalle.desde(service.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") String id) {
        service.eliminar(id);
    }
}