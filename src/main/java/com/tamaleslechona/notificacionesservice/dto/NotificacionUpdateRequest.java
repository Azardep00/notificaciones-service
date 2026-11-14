package com.tamaleslechona.notificacionesservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Campos que se pueden corregir en una notificacion ya guardada (PUT).
public record NotificacionUpdateRequest(
        @Schema(example = "laura@example.com") @NotBlank @Email String correoCliente,
        @Schema(example = "EN_PREPARACION") @NotBlank String estadoNuevo) {}
