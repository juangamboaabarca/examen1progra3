# Caso 7. Gestión de órdenes de reparación

## Contexto

Un taller de reparación de equipos electrónicos recibe artículos de clientes, los diagnostica, los repara y los entrega. Necesita conocer el estado de cada orden y evitar saltos indebidos, como entregar un equipo que no ha sido reparado.

## Usuario del sistema

Recepcionista y técnicos del taller.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Cliente` | Dueño del artículo | identificacion, nombre, telefono, correo |
| `Tecnico` | Responsable de la reparación | codigo, nombre, especialidad |
| `OrdenReparacion` | Orden de trabajo | numero, cliente, tecnico, articulo, descripcionFalla, costoEstimado, estado, fechaIngreso, fechaEntrega |
| `HistorialEstado` | Bitácora de cambios de estado | id, orden, estadoAnterior, estadoNuevo, fechaHora, comentario |

## Enumeraciones sugeridas

```java
enum EstadoOrden { RECIBIDA, EN_DIAGNOSTICO, EN_REPARACION, LISTA, ENTREGADA, CANCELADA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `cliente` | identificacion PK, nombre, telefono, correo |
| `tecnico` | codigo PK, nombre, especialidad |
| `orden_reparacion` | numero PK AUTO_INCREMENT, cliente_id FK, tecnico_codigo FK NULL, articulo, descripcion_falla, costo_estimado, estado, fecha_ingreso, fecha_entrega NULL |
| `historial_estado` | id PK AUTO_INCREMENT, orden_numero FK, estado_anterior, estado_nuevo, fecha_hora, comentario |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar clientes y técnicos.
- Registrar órdenes (estado inicial `RECIBIDA`) y asignar técnico.
- Consultar las órdenes de un técnico o de un estado, y el historial de una orden (JOIN).

## Operación de negocio principal: Cambiar el estado de una orden

1. Verificar que la orden exista.
2. Verificar que la **transición sea válida** según el diagrama de estados.
3. Verificar las condiciones propias de la transición (por ejemplo, para pasar a `EN_REPARACION` debe tener técnico asignado y costo estimado).
4. Actualizar el estado de la orden e insertar el registro en `historial_estado` (transacción).

## Reglas de negocio

- **RN-01.** Transiciones válidas: `RECIBIDA → EN_DIAGNOSTICO → EN_REPARACION → LISTA → ENTREGADA`; se puede pasar a `CANCELADA` desde cualquier estado anterior a `LISTA`.
- **RN-02.** `ENTREGADA` y `CANCELADA` son estados finales.
- **RN-03.** No se puede pasar a `EN_REPARACION` sin técnico asignado ni costo estimado.
- **RN-04.** Todo cambio de estado queda registrado en el historial.

## Excepciones sugeridas

`TransicionInvalidaException`, `ValidacionException`

## Funcionalidad adicional sugerida

Consultar el historial de estados de una orden.

## Nota técnica

Se recomienda que la tabla de transiciones permitidas se represente en el `enum` (por ejemplo, un método `puedeCambiarA(EstadoOrden nuevo)`).

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
