# Caso 3. Reservación de aulas universitarias

## Contexto

Una sede universitaria administra aulas y laboratorios que docentes y organizaciones estudiantiles reservan para clases de reposición, tutorías y actividades. Hoy se reservan por correo y a veces dos grupos llegan a la misma aula a la misma hora.

## Usuario del sistema

Funcionario(a) de servicios administrativos.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Aula` | Espacio reservable | codigo, nombre, tipo, capacidad, activa |
| `Solicitante` | Docente u organización que reserva | identificacion, nombre, correo, tipo |
| `Reserva` | Uso de un aula en un horario | id, aula, solicitante, fecha, horaInicio, horaFin, cantidadPersonas, proposito, estado |

## Enumeraciones sugeridas

```java
enum TipoAula { AULA, LABORATORIO, AUDITORIO }
enum EstadoReserva { CONFIRMADA, CANCELADA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `aula` | codigo PK, nombre, tipo, capacidad CHECK > 0, activa |
| `solicitante` | identificacion PK, nombre, correo, tipo |
| `reserva` | id PK AUTO_INCREMENT, aula_codigo FK, solicitante_id FK, fecha, hora_inicio, hora_fin, cantidad_personas, proposito, estado |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar aulas (incluido activar/desactivar).
- Registrar, consultar, listar y actualizar solicitantes.
- Consultar las reservas de un aula en una fecha (JOIN con solicitante).

## Operación de negocio principal: Reservar aula

1. Verificar que el aula exista y esté activa.
2. Verificar que la hora de fin sea posterior a la de inicio y que la fecha no esté en el pasado.
3. Verificar que la cantidad de personas no supere la capacidad del aula.
4. Verificar que **no exista otra reserva `CONFIRMADA`** del aula con horario traslapado en la misma fecha.
5. Insertar la reserva en estado `CONFIRMADA`.

## Reglas de negocio

- **RN-01.** No se permiten reservas simultáneas (traslapadas) de la misma aula.
- **RN-02.** La cantidad de personas no puede superar la capacidad.
- **RN-03.** No se puede reservar un aula inactiva.
- **RN-04.** Solo se puede cancelar una reserva `CONFIRMADA` cuya fecha no haya pasado.

## Excepciones sugeridas

`AulaOcupadaException`, `CapacidadExcedidaException`, `ValidacionException`

## Funcionalidad adicional sugerida

Cancelar reserva.

## Nota técnica

Dos intervalos [inicio1, fin1) y [inicio2, fin2) se traslapan si `inicio1 < fin2 AND inicio2 < fin1`.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
