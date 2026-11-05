package com.tamaleslechona.notificacionesservice.exception;

public class NotificacionNoEncontradaException extends RuntimeException {
    public NotificacionNoEncontradaException(String id) {
        super("No existe una notificacion con id " + id);
    }
}
