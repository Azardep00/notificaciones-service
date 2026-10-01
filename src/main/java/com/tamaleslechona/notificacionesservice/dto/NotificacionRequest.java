package com.tamaleslechona.notificacionesservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Lo que el backend principal (Distribuidoras-Tamales-y-Lechona) envia
// cada vez que un pedido cambia de estado.
public record NotificacionRequest(
        @NotNull Integer idPedido,
        @NotBlank String correoCliente,
        @NotBlank String estadoNuevo) {}
