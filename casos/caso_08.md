# Caso 8. Reservación de espacios deportivos

## Contexto

Un polideportivo municipal alquila canchas de fútbol 5, básquetbol y tenis por bloques horarios. Las reservas se hacen por teléfono y en ocasiones dos equipos llegan a la misma cancha al mismo tiempo.

## Usuario del sistema

Administrador(a) del polideportivo.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Cliente` | Persona que reserva | identificacion, nombre, telefono |
| `Cancha` | Espacio deportivo | codigo, nombre, deporte, tarifaHora, habilitada |
| `Reserva` | Uso de una cancha | id, cliente, cancha, fecha, horaInicio, horaFin, montoTotal, estado |

## Enumeraciones sugeridas

```java
enum Deporte { FUTBOL_5, BASQUETBOL, TENIS }
enum EstadoReserva { ACTIVA, CANCELADA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `cliente` | identificacion PK, nombre, telefono |
| `cancha` | codigo PK, nombre, deporte, tarifa_hora CHECK > 0, habilitada |
| `reserva` | id PK AUTO_INCREMENT, cliente_id FK, cancha_codigo FK, fecha, hora_inicio, hora_fin, monto_total, estado |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar clientes.
- Registrar, consultar, listar y actualizar canchas (tarifa, habilitada).
- Consultar la ocupación de una cancha en una fecha y las reservas de un cliente (JOIN).

## Operación de negocio principal: Reservar cancha

1. Verificar que el cliente exista y que la cancha esté habilitada.
2. Verificar que el horario esté entre las 06:00 y las 22:00, en horas completas, con duración de 1 a 3 horas.
3. Verificar que **no exista otra reserva `ACTIVA`** de la cancha con horario traslapado.
4. Calcular el monto (horas × tarifa) e insertar la reserva `ACTIVA`.

## Reglas de negocio

- **RN-01.** No se permiten reservas superpuestas de la misma cancha.
- **RN-02.** Las reservas son por horas completas, de 1 a 3 horas, dentro del horario de operación.
- **RN-03.** Un cliente no puede tener más de 2 reservas activas el mismo día.
- **RN-04.** Solo se puede cancelar una reserva `ACTIVA` con al menos 2 horas de anticipación.

## Excepciones sugeridas

`CanchaOcupadaException`, `HorarioInvalidoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Cancelar reserva.

## Nota técnica

Dos intervalos [inicio1, fin1) y [inicio2, fin2) se traslapan si `inicio1 < fin2 AND inicio2 < fin1`.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
