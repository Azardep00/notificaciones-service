package com.tamaleslechona.notificacionesservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tamaleslechona.notificacionesservice.model.Notificacion;

public interface NotificacionRepository extends MongoRepository<Notificacion, String> {

    List<Notificacion> findByIdPedido(Integer idPedido);

    List<Notificacion> findByCorreoCliente(String correoCliente);

    List<Notificacion> findByIdPedidoAndCorreoCliente(Integer idPedido, String correoCliente);
}