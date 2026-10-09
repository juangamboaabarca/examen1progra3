# Caso 10. Control de herramientas de laboratorio

## Contexto

Un laboratorio de electrónica asigna herramientas (multímetros, osciloscopios, cautines, kits) a estudiantes durante las prácticas. Algunas herramientas no se devuelven o se devuelven dañadas y no queda registro de quién las tenía.

## Usuario del sistema

Asistente del laboratorio.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Estudiante` | Usuario del laboratorio | carne, nombre, carrera, habilitado |
| `Herramienta` | Herramienta inventariada | codigo, nombre, tipo, estado |
| `Asignacion` | Entrega de una herramienta | id, estudiante, herramienta, fechaHoraAsignacion, fechaHoraDevolucion, condicionDevolucion, observacion |

## Enumeraciones sugeridas

```java
enum EstadoHerramienta { DISPONIBLE, ASIGNADA, DANADA, EN_MANTENIMIENTO }
enum CondicionDevolucion { BUENA, DANADA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `estudiante` | carne PK, nombre, carrera, habilitado |
| `herramienta` | codigo PK, nombre, tipo, estado |
| `asignacion` | id PK AUTO_INCREMENT, estudiante_carne FK, herramienta_codigo FK, fecha_hora_asignacion, fecha_hora_devolucion NULL, condicion_devolucion NULL, observacion |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar estudiantes (habilitar/inhabilitar).
- Registrar, consultar, listar y actualizar herramientas.
- Consultar las herramientas que tiene asignadas un estudiante y el historial de una herramienta (JOIN).

## Operación de negocio principal: Asignar y devolver herramienta

1. **Asignación:** verificar que el estudiante esté habilitado, que la herramienta esté `DISPONIBLE` y que el estudiante no tenga más de 3 herramientas asignadas; registrar la asignación y cambiar la herramienta a `ASIGNADA` (transacción).
2. **Devolución:** verificar que exista una asignación abierta de esa herramienta; registrar fecha y condición; si la condición es `DANADA`, la herramienta pasa a `DANADA` y el estudiante queda inhabilitado; si es `BUENA`, pasa a `DISPONIBLE` (transacción).

## Reglas de negocio

- **RN-01.** Solo se asignan herramientas `DISPONIBLE` a estudiantes habilitados.
- **RN-02.** Un estudiante no puede tener más de 3 herramientas asignadas al mismo tiempo.
- **RN-03.** Solo se puede devolver una herramienta con una asignación abierta.
- **RN-04.** Una devolución en condición `DANADA` inhabilita al estudiante hasta que el asistente lo habilite de nuevo.

## Excepciones sugeridas

`HerramientaNoDisponibleException`, `EstudianteInhabilitadoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Reporte de herramientas asignadas actualmente.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
