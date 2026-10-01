# notificaciones-service

Microservicio independiente del proyecto **Tamales y Lechona**.

Recibe eventos de cambio de estado de pedidos desde el backend principal
(`Distribuidoras-Tamales-y-Lechona`) por **REST sincrono** y simula el envio
de una notificacion al cliente. No comparte base de datos ni proceso con el
backend principal: es una aplicacion Spring Boot separada, con su propio
`pom.xml`, su propio repositorio y su propio despliegue.

## Endpoint

`POST /notificaciones/pedido`

```json
{
  "idPedido": 42,
  "correoCliente": "cliente@correo.com",
  "estadoNuevo": "CONFIRMADO"
}
```

Respuesta:
```json
{
  "enviado": true,
  "mensaje": "Se notifico a cliente@correo.com que su pedido #42 ahora esta: CONFIRMADO"
}
```

## Correr localmente

```
mvn spring-boot:run
```

Corre en el puerto `8081`. Documentacion interactiva en
`http://localhost:8081/swagger-ui.html`.

El contrato OpenAPI del diseño (hecho antes de programar, enfoque API First)
esta en `src/main/resources/openapi-contrato.yaml`.

## Docker

```
docker build -t notificaciones-service .
docker run -p 8081:8081 notificaciones-service
```
