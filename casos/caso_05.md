# Caso 5. Gestión de biblioteca

## Contexto

Una biblioteca comunitaria presta libros a sus usuarios registrados. Cada libro puede tener varios ejemplares. Necesita saber qué ejemplares están disponibles, quién tiene cada uno y qué devoluciones están atrasadas.

## Usuario del sistema

Bibliotecario(a).

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Usuario` | Lector registrado | identificacion, nombre, correo, activo |
| `Libro` | Título del catálogo | isbn, titulo, autor, anio |
| `Ejemplar` | Copia física de un libro | codigo, libro, estado |
| `Prestamo` | Préstamo de un ejemplar | id, usuario, ejemplar, fechaPrestamo, fechaLimite, fechaDevolucion, estado |

## Enumeraciones sugeridas

```java
enum EstadoEjemplar { DISPONIBLE, PRESTADO, DANADO }
enum EstadoPrestamo { ACTIVO, DEVUELTO }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `usuario` | identificacion PK, nombre, correo, activo |
| `libro` | isbn PK, titulo, autor, anio |
| `ejemplar` | codigo PK, libro_isbn FK, estado |
| `prestamo` | id PK AUTO_INCREMENT, usuario_id FK, ejemplar_codigo FK, fecha_prestamo, fecha_limite, fecha_devolucion NULL, estado |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar usuarios.
- Registrar libros y sus ejemplares; consultar disponibilidad de un libro.
- Consultar los préstamos activos de un usuario o los préstamos atrasados (JOIN con libro y usuario).

## Operación de negocio principal: Prestar y devolver un ejemplar

1. **Préstamo:** verificar que el usuario esté activo, que no tenga préstamos atrasados y que el ejemplar esté `DISPONIBLE`; registrar el préstamo con fecha límite (hoy + 8 días) y cambiar el ejemplar a `PRESTADO` (transacción).
2. **Devolución:** verificar que el préstamo esté `ACTIVO`; registrar la fecha de devolución, cambiar el préstamo a `DEVUELTO` y el ejemplar a `DISPONIBLE` (transacción); informar los días de atraso, si los hay.

## Reglas de negocio

- **RN-01.** No se puede prestar un ejemplar que no esté `DISPONIBLE`.
- **RN-02.** Un usuario con préstamos atrasados no puede solicitar nuevos préstamos.
- **RN-03.** Un usuario no puede tener más de 3 préstamos activos.
- **RN-04.** Solo se puede devolver un préstamo `ACTIVO`.

## Excepciones sugeridas

`EjemplarNoDisponibleException`, `UsuarioConAtrasoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Reporte de préstamos atrasados.

## Nota técnica

Este caso tiene cuatro tablas, pero las operaciones de `Libro` y `Ejemplar` pueden resolverse con un solo repositorio si se justifica.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
