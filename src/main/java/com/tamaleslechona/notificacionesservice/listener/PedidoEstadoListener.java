package com.tamaleslechona.notificacionesservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tamaleslechona.notificacionesservice.dto.PedidoEstadoEvent;

// Consumidor de Kafka. Sustituye (o complementa) al endpoint REST: en vez de
// que el API core espere la respuesta, el evento se queda en el topico y este
// servicio lo procesa cuando puede.
@Component
public class PedidoEstadoListener {

    private static final Logger log = LoggerFactory.getLogger(PedidoEstadoListener.class);

    @KafkaListener(topics = "${notificaciones.kafka.topico:pedidos.estado-cambiado}")
    public void consumir(PedidoEstadoEvent evento) {
        // Aqui iria el envio real (correo/push). Por ahora se registra en el log,
        // igual que hacia el controlador REST.
        log.info("[NOTIFICACION] Pedido #{} -> {}. Se notificaria a: {} (evento del {})",
                evento.idPedido(),
                evento.estadoNuevo(),
                evento.correoCliente(),
                evento.ocurridoEn());
    }
}
