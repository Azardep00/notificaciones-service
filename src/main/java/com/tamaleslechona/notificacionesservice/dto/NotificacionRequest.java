package com.tamaleslechona.notificacionesservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

// Lo que el backend principal (Distribuidoras-Tamales-y-Lechona) envia
// cada vez que un pedido cambia de estado.
public record NotificacionRequest(
        @Schema(description = "Id del pedido en el backend (texto, ObjectId de Mongo)",
                example = "6740f1c2a9b3d4e5f6a7b8d0") @NotBlank String idPedido,
        @Schema(example = "laura@example.com") @NotBlank String correoCliente,
        @Schema(example = "CONFIRMADO") @NotBlank String estadoNuevo) {}
