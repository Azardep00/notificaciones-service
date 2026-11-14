package com.tamaleslechona.notificacionesservice.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// Datos generales de la documentación Swagger de este microservicio.
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Microservicio de Notificaciones - Tamales y Lechona",
        version = "1.0.0",
        description = "Servicio independiente que guarda en MongoDB las notificaciones de cambio de estado "
                + "de los pedidos. Las recibe del backend principal por REST (POST /notificaciones/pedido) "
                + "o por Kafka, y expone el CRUD completo."))
public class OpenApiConfig {
}
