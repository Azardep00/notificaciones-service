package com.tamaleslechona.notificacionesservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Campos que se pueden corregir en una notificacion ya guardada (PUT).
public record NotificacionUpdateRequest(
        @NotBlank @Email String correoCliente,
        @NotBlank String estadoNuevo) {}
