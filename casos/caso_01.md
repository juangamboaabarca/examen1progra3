# Caso 1. Préstamo de equipos tecnológicos

## Contexto

Una unidad académica presta computadoras portátiles, proyectores, cámaras y otros equipos a estudiantes y funcionarios. Actualmente lo controla en una hoja de cálculo y con frecuencia se presta un equipo que ya estaba prestado o en reparación.

## Usuario del sistema

Encargado(a) del área de préstamos.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Solicitante` | Persona que solicita equipo | identificacion, nombre, correo, tipo (ESTUDIANTE / FUNCIONARIO) |
| `Equipo` | Equipo prestable | codigo, descripcion, tipo, estado |
| `Prestamo` | Registro de un préstamo | id, solicitante, equipo, fechaPrestamo, fechaDevolucionEsperada, fechaDevolucionReal, estado |

## Enumeraciones sugeridas

```java
enum TipoSolicitante { ESTUDIANTE, FUNCIONARIO }
enum EstadoEquipo { DISPONIBLE, PRESTADO, EN_REPARACION }
enum EstadoPrestamo { ACTIVO, DEVUELTO }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `solicitante` | identificacion PK, nombre, correo UNIQUE, tipo |
| `equipo` | codigo PK, descripcion, tipo, estado |
| `prestamo` | id PK AUTO_INCREMENT, solicitante_id FK, equipo_codigo FK, fecha_prestamo, fecha_devolucion_esperada, fecha_devolucion_real NULL, estado |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar solicitantes.
- Registrar, consultar, listar y actualizar equipos (incluido cambiar a EN_REPARACION).
- Consultar los préstamos activos de un solicitante (JOIN).

## Operación de negocio principal: Registrar préstamo

1. Verificar que el solicitante y el equipo existan.
2. Verificar que el equipo esté `DISPONIBLE`.
3. Verificar que la fecha de devolución esperada sea posterior a la fecha actual.
4. Insertar el préstamo en estado `ACTIVO` y cambiar el equipo a `PRESTADO` (transacción).

## Reglas de negocio

- **RN-01.** No se puede prestar un equipo que no esté `DISPONIBLE`.
- **RN-02.** Un estudiante no puede tener más de 2 préstamos activos; un funcionario, no más de 4.
- **RN-03.** La devolución cambia el préstamo a `DEVUELTO`, registra la fecha real y deja el equipo `DISPONIBLE`.
- **RN-04.** No se permiten códigos de equipo ni identificaciones duplicadas.

## Excepciones sugeridas

`EquipoNoDisponibleException`, `LimitePrestamosException`, `ValidacionException`

## Funcionalidad adicional sugerida

Registrar devolución.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
