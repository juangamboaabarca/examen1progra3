# Caso 2. Gestión de citas médicas

## Contexto

Un consultorio con varios profesionales de salud agenda citas por teléfono. Se han presentado choques de horario: dos pacientes citados con el mismo profesional a la misma hora.

## Usuario del sistema

Recepcionista del consultorio.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Paciente` | Persona atendida | identificacion, nombre, telefono, correo |
| `Profesional` | Profesional que atiende | codigo, nombre, especialidad, duracionCitaMinutos |
| `Cita` | Cita agendada | id, paciente, profesional, fechaHoraInicio, fechaHoraFin, motivo, estado |

## Enumeraciones sugeridas

```java
enum Especialidad { MEDICINA_GENERAL, ODONTOLOGIA, PSICOLOGIA, NUTRICION }
enum EstadoCita { PROGRAMADA, ATENDIDA, CANCELADA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `paciente` | identificacion PK, nombre, telefono, correo |
| `profesional` | codigo PK, nombre, especialidad, duracion_cita_minutos |
| `cita` | id PK AUTO_INCREMENT, paciente_id FK, profesional_codigo FK, fecha_hora_inicio, fecha_hora_fin, motivo, estado |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar pacientes.
- Registrar, consultar, listar y actualizar profesionales.
- Consultar la agenda de un profesional en una fecha (JOIN con paciente).

## Operación de negocio principal: Agendar cita

1. Verificar que el paciente y el profesional existan.
2. Calcular la hora de fin según la duración de cita del profesional.
3. Verificar que la cita sea en el futuro y dentro del horario de atención (07:00 a 17:00).
4. Verificar que **no exista otra cita `PROGRAMADA`** del profesional cuyo intervalo se traslape.
5. Insertar la cita en estado `PROGRAMADA`.

## Reglas de negocio

- **RN-01.** Un profesional no puede tener dos citas `PROGRAMADA` con intervalos traslapados.
- **RN-02.** Un paciente no puede tener dos citas `PROGRAMADA` a la misma hora.
- **RN-03.** Solo se puede cancelar o marcar como atendida una cita `PROGRAMADA`.
- **RN-04.** La duración de la cita debe ser positiva.

## Excepciones sugeridas

`ConflictoHorarioException`, `EstadoInvalidoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Cancelar o marcar como atendida una cita.

## Nota técnica

Dos intervalos [inicio1, fin1) y [inicio2, fin2) se traslapan si `inicio1 < fin2 AND inicio2 < fin1`.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
