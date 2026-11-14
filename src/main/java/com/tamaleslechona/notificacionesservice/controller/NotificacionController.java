package com.tamaleslechona.notificacionesservice.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Notificaciones", description = "Notificaciones de cambio de estado de pedidos. CRUD completo guardado en MongoDB (el DELETE borra el documento de verdad).")
@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar una notificación de cambio de estado (la llama el backend principal)")
    @ApiResponse(responseCode = "200", description = "Notificación guardada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping("/pedido")
    public NotificacionResponse notificarCambioEstado(@Valid @RequestBody NotificacionRequest req) {
        service.registrar(req.idPedido(), req.correoCliente(), req.estadoNuevo(), "REST");

        String mensaje = "Se notifico a " + req.correoCliente()
                + " que su pedido #" + req.idPedido() + " ahora esta: " + req.estadoNuevo();
        return new NotificacionResponse(true, mensaje);
    }

    @Operation(summary = "Listar notificaciones (filtros opcionales: idPedido, correoCliente)")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<NotificacionDetalle> listar(
            @RequestParam(name = "idPedido", required = false) String idPedido,
            @RequestParam(name = "correoCliente", required = false) String correoCliente) {
        return service.listar(idPedido, correoCliente).stream()
                .map(NotificacionDetalle::desde)
                .toList();
    }

    @Operation(summary = "Buscar una notificación por su id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "No encontrada")
    @GetMapping("/{id}")
    public NotificacionDetalle obtener(@PathVariable("id") String id) {
        return NotificacionDetalle.desde(service.obtener(id));
    }

    @Operation(summary = "Corregir el correo o el estado de una notificación")
    @ApiResponse(responseCode = "200", description = "Actualizada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "No encontrada")
    @PutMapping("/{id}")
    public NotificacionDetalle actualizar(
            @PathVariable("id") String id,
            @Valid @RequestBody NotificacionUpdateRequest datos) {
        return NotificacionDetalle.desde(service.actualizar(id, datos));
    }

    @Operation(summary = "Eliminar una notificación (borrado físico de MongoDB)")
    @ApiResponse(responseCode = "204", description = "Eliminada")
    @ApiResponse(responseCode = "404", description = "No encontrada")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") String id) {
        service.eliminar(id);
    }
}