package com.tamaleslechona.notificacionesservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tamaleslechona.notificacionesservice.dto.PedidoEstadoEvent;
import com.tamaleslechona.notificacionesservice.service.NotificacionService;

// Consumidor de Kafka. Sustituye (o complementa) al endpoint REST: en vez de
// que el API core espere la respuesta, el evento se queda en el topico y este
// servicio lo procesa cuando puede.
@Component
public class PedidoEstadoListener {

    private static final Logger log = LoggerFactory.getLogger(PedidoEstadoListener.class);

    private final NotificacionService service;

    public PedidoEstadoListener(NotificacionService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${notificaciones.kafka.topico:pedidos.estado-cambiado}")
    public void consumir(PedidoEstadoEvent evento) {
        service.registrar(evento.idPedido(), evento.correoCliente(), evento.estadoNuevo(), "KAFKA");
        log.info("[NOTIFICACION] Pedido #{} -> {} guardada", evento.idPedido(), evento.estadoNuevo());
    }
}