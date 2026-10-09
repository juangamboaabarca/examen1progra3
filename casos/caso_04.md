# Caso 4. Control de inventario de suministros

## Contexto

La proveeduría de una institución entrega suministros de oficina y limpieza a los departamentos. Necesita registrar entradas (compras) y salidas (entregas) para conocer las existencias reales; actualmente se entregan productos que ya no hay.

## Usuario del sistema

Encargado(a) de proveeduría.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Producto` | Suministro inventariado | codigo, nombre, categoria, unidadMedida, existencia, existenciaMinima |
| `Departamento` | Unidad que recibe suministros | codigo, nombre, responsable |
| `Movimiento` | Entrada o salida de producto | id, producto, departamento (solo en salidas), tipo, cantidad, fecha, observacion |

## Enumeraciones sugeridas

```java
enum CategoriaProducto { OFICINA, LIMPIEZA, COMPUTO }
enum TipoMovimiento { ENTRADA, SALIDA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `producto` | codigo PK, nombre, categoria, unidad_medida, existencia CHECK >= 0, existencia_minima |
| `departamento` | codigo PK, nombre, responsable |
| `movimiento` | id PK AUTO_INCREMENT, producto_codigo FK, departamento_codigo FK NULL, tipo, cantidad CHECK > 0, fecha, observacion |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar productos.
- Registrar, consultar, listar y actualizar departamentos.
- Consultar el historial de movimientos de un producto o las salidas a un departamento (JOIN).

## Operación de negocio principal: Registrar salida de producto

1. Verificar que el producto y el departamento existan.
2. Verificar que la cantidad sea mayor que cero.
3. Verificar que la cantidad **no supere la existencia actual**.
4. Insertar el movimiento `SALIDA` y disminuir la existencia del producto (transacción).
5. Informar si el producto quedó por debajo de su existencia mínima.

## Reglas de negocio

- **RN-01.** No se permiten salidas superiores a las existencias.
- **RN-02.** Una entrada aumenta la existencia; una salida la disminuye.
- **RN-03.** Toda salida debe indicar el departamento que recibe.
- **RN-04.** La existencia nunca puede ser negativa.

## Excepciones sugeridas

`ExistenciaInsuficienteException`, `ValidacionException`

## Funcionalidad adicional sugerida

Registrar entrada de producto y reporte de productos bajo la existencia mínima.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
