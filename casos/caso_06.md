# Caso 6. Administración de parqueos

## Contexto

Un parqueo institucional con espacios para automóviles y motocicletas registra el ingreso y la salida de vehículos. Necesita respetar la capacidad de cada tipo de espacio y evitar que un mismo vehículo quede registrado dos veces dentro del parqueo.

## Usuario del sistema

Oficial de seguridad en la caseta de ingreso.

## Entidades sugeridas

| Clase | Idea | Atributos principales |
|---|---|---|
| `Vehiculo` | Vehículo autorizado | placa, tipo, marca, color, propietario |
| `Zona` | Sección del parqueo por tipo de vehículo | codigo, tipoVehiculo, capacidad |
| `Ingreso` | Estadía de un vehículo | id, vehiculo, zona, fechaHoraEntrada, fechaHoraSalida |

## Enumeraciones sugeridas

```java
enum TipoVehiculo { AUTOMOVIL, MOTOCICLETA }
```

## Modelo relacional sugerido

| Tabla | Columnas |
|---|---|
| `vehiculo` | placa PK, tipo, marca, color, propietario |
| `zona` | codigo PK, tipo_vehiculo, capacidad CHECK > 0 |
| `ingreso` | id PK AUTO_INCREMENT, vehiculo_placa FK, zona_codigo FK, fecha_hora_entrada, fecha_hora_salida NULL |

## Operaciones mínimas (registro, consulta y actualización)

- Registrar, consultar, listar y actualizar vehículos autorizados.
- Registrar, consultar y actualizar zonas (capacidad).
- Consultar los vehículos que están dentro del parqueo y los espacios libres por zona (JOIN / COUNT).

## Operación de negocio principal: Registrar ingreso de vehículo

1. Verificar que el vehículo esté registrado.
2. Verificar que el vehículo **no tenga un ingreso abierto** (sin salida).
3. Seleccionar una zona del tipo del vehículo.
4. Verificar que la cantidad de ingresos abiertos en la zona sea **menor que su capacidad**.
5. Insertar el ingreso con la fecha y hora actual.

## Reglas de negocio

- **RN-01.** No se puede superar la capacidad máxima de una zona.
- **RN-02.** Un vehículo no puede ingresar si ya está dentro (duplicidad).
- **RN-03.** Un vehículo solo puede ocupar una zona de su mismo tipo.
- **RN-04.** Solo se puede registrar la salida de un vehículo que esté dentro.

## Excepciones sugeridas

`ParqueoLlenoException`, `VehiculoYaIngresadoException`, `ValidacionException`

## Funcionalidad adicional sugerida

Registrar salida y calcular el tiempo de permanencia.

---

> Las entidades, tablas y nombres son orientativos. La pareja puede ajustarlos siempre que justifique su diseño en `docs/etapa_1/entrega.md` y cumpla los requisitos comunes (sección 7 de la guía).
