package com.tamaleslechona.notificacionesservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tamaleslechona.notificacionesservice.dto.NotificacionUpdateRequest;
import com.tamaleslechona.notificacionesservice.exception.NotificacionNoEncontradaException;
import com.tamaleslechona.notificacionesservice.model.Notificacion;
import com.tamaleslechona.notificacionesservice.repository.NotificacionRepository;

// Logica de las notificaciones: guardar las que llegan (REST o Kafka) y el CRUD.
@Service
public class NotificacionService {

    private final NotificacionRepository repo;

    public NotificacionService(NotificacionRepository repo) {
        this.repo = repo;
    }

    public Notificacion registrar(Integer idPedido, String correoCliente, String estadoNuevo, String canal) {
        return repo.save(new Notificacion(idPedido, correoCliente, estadoNuevo, canal));
    }

    public List<Notificacion> listar(Integer idPedido, String correoCliente) {
        if (idPedido != null && correoCliente != null) {
            return repo.findByIdPedidoAndCorreoCliente(idPedido, correoCliente);
        }
        if (idPedido != null) {
            return repo.findByIdPedido(idPedido);
        }
        if (correoCliente != null) {
            return repo.findByCorreoCliente(correoCliente);
        }
        return repo.findAll();
    }

    public Notificacion obtener(String id) {
        return repo.findById(id).orElseThrow(() -> new NotificacionNoEncontradaException(id));
    }

    public Notificacion actualizar(String id, NotificacionUpdateRequest datos) {
        Notificacion n = obtener(id);
        n.setCorreoCliente(datos.correoCliente());
        n.setEstadoNuevo(datos.estadoNuevo());
        return repo.save(n);
    }

    public void eliminar(String id) {
        Notificacion n = obtener(id); // 404 si no existe
        repo.delete(n);
    }
}
