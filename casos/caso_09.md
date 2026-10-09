# Caso 9. Registro de solicitudes de soporte

## Contexto

El departamento de tecnologías de información recibe solicitudes de soporte (fallas de equipo, accesos, red). Necesita priorizarlas, asignarlas a un técnico y dar seguimiento a su atención hasta el cierre.

## Usuario del sistema

Coordinador(a) de soporte y técnicos.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Usuario` | Funcionario que reporta | identificacion, nombre, departamento, correo |
| `Tecnico` | Responsable de atender | codigo, nombre, activo |
| `Solicitud` | Caso de soporte | numero, usuario, tecnico, titulo, descripcion, categoria, prioridad, estado, fechaCreacion, fechaCierre, solucion |

## Enumeraciones sugeridas

```java
enum Prioridad { BAJA, MEDIA, ALTA, CRITICA }
enum EstadoSolicitud { ABIERTA, ASIGNADA, EN_PROCESO, RESUELTA, CERRADA }
enum Categoria { HARDWARE, SOFTWARE, RED, ACCESOS }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `usuario` | identificacion PK, nombre, departamento, correo |
| `tecnico` | codigo PK, nombre, activo |
| `solicitud` | numero PK AUTO_INCREMENT, usuario_id FK, tecnico_codigo FK NULL, titulo, descripcion, categoria, prioridad, estado, fecha_creacion, fecha_cierre NULL, solucion NULL |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar usuarios y técnicos.
- Registrar solicitudes (estado inicial `ABIERTA`).
- Listar las solicitudes pendientes **ordenadas por prioridad y antigüedad**, y las asignadas a un técnico (JOIN).

## Operación de negocio principal: Asignar y atender solicitudes

1. **Asignar:** verificar que la solicitud esté `ABIERTA` y el técnico activo; verificar que el técnico no tenga más de 5 solicitudes sin resolver; asignar y cambiar a `ASIGNADA`.
2. **Atender:** cambiar a `EN_PROCESO` y luego a `RESUELTA` (con solución obligatoria); el usuario confirma y pasa a `CERRADA` con fecha de cierre.

## Reglas de negocio

- **RN-01.** Las solicitudes pendientes se atienden por prioridad (`CRITICA` primero) y, a igual prioridad, por antigüedad.
- **RN-02.** Una solicitud `CRITICA` no puede quedar sin técnico: se debe asignar al registrarla o el sistema debe advertirlo en el listado.
- **RN-03.** Las transiciones de estado siguen el orden `ABIERTA → ASIGNADA → EN_PROCESO → RESUELTA → CERRADA`.
- **RN-04.** No se puede resolver una solicitud sin registrar la solución.

## Excepciones sugeridas

`TransicionInvalidaException`, `TecnicoSaturadoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Reporte de solicitudes por estado y prioridad.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
