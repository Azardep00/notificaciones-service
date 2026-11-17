# notificaciones-service

Microservicio independiente del proyecto **Tamales y Lechona**.

Recibe eventos de cambio de estado de pedidos desde el backend principal
(`Distribuidoras-Tamales-y-Lechona`) por **REST sincrono** (o por Kafka) y simula el envio
de una notificacion al cliente. No comparte base de datos ni proceso con el
backend principal: es una aplicacion Spring Boot separada, con su propio
`pom.xml`, su propio repositorio y su propio despliegue.

## Endpoints

Las notificaciones se guardan en MongoDB (colección `notificaciones`). Los IDs
son `String` (ObjectId de Mongo, 24 caracteres).

| Método | Endpoint                                  | Descripción                                         |
|--------|-------------------------------------------|-----------------------------------------------------|
| POST   | `/notificaciones/pedido`                  | Registra una notificación (la llama el backend)     |
| GET    | `/notificaciones`                         | Lista; filtros opcionales `idPedido`, `correoCliente` |
| GET    | `/notificaciones/{id}`                    | Busca una notificación por su id                    |
| PUT    | `/notificaciones/{id}`                    | Corrige `correoCliente` y `estadoNuevo`             |
| DELETE | `/notificaciones/{id}`                    | Elimina el documento de verdad (204)                |

`POST /notificaciones/pedido`

```json
{
  "idPedido": "6740f1c2a9b3d4e5f6a7b8d0",
  "correoCliente": "cliente@correo.com",
  "estadoNuevo": "CONFIRMADO"
}
```

Respuesta:
```json
{
  "enviado": true,
  "mensaje": "Se notifico a cliente@correo.com que su pedido #6740f1c2a9b3d4e5f6a7b8d0 ahora esta: CONFIRMADO"
}
```

Cada notificación guardada incluye el `canal` por el que llegó (`REST` o `KAFKA`)
y la fecha `enviadaEn`.

## Kafka

Además del endpoint REST, el servicio consume el tópico
`pedidos.estado-cambiado` (configurable con `notificaciones.kafka.topico`).
El evento tiene los mismos campos que `PedidoEstadoEvent` del backend:
`idPedido`, `correoCliente`, `estadoNuevo`, `ocurridoEn`. El backend elige el
transporte con `notificaciones.kafka.habilitado` (por defecto `false` = REST).

Variables de entorno: `KAFKA_BOOTSTRAP`, `KAFKA_SECURITY_PROTOCOL`,
`KAFKA_SASL_MECHANISM`, `KAFKA_USER`, `KAFKA_PASS`, `KAFKA_CA_PATH`.

## MongoDB

Se conecta con la variable `MONGODB_URI` (por defecto
`mongodb://localhost:27017/tamales`).

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
