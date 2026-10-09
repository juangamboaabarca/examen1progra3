# Examen Práctico Integrador — Programación III

**EIF206 – Programación III · Universidad Nacional de Costa Rica**

> Este `README.md` contiene la **guía oficial del examen**. No lo modifique.
> El README de su proyecto se escribe en [`proyecto/README.md`](proyecto/README.md) (ver sección 30).

| Dato | Detalle |
|---|---|
| Modalidad | Parejas |
| Entrega final | Sábado 17 de octubre de 2026, 23:59 (ver sección 0.6) |
| Tecnologías | Java 21+, Maven, JDBC, MySQL, NetBeans (u otro IDE con Maven) |
| Interfaz | Consola (se permite una interfaz gráfica, pero no suma puntos adicionales) |
| Entrega | Repositorio GitHub con tres etiquetas: `etapa-1`, `etapa-2`, `etapa-3` |
| Puntaje | 100 puntos |

---

## 0. Inicio rápido

### 0.1 Crear la copia de la pareja

1. Ingrese a este repositorio y use **Use this template → Create a new repository** (o *Fork*, si el docente así lo indica).
2. Nombre del repositorio: `examen1progra3-apellido1-apellido2` (primer apellido de cada integrante).
3. Si el repositorio es **privado**, agregue al docente como colaborador: *Settings → Collaborators → Add people*.
4. El integrante que creó el repositorio agrega a su compañero(a) como colaborador con permiso de escritura.
5. Registre la URL del repositorio en el medio que indique el docente.

### 0.2 Clonar y configurar Git (cada integrante, en su propio equipo)

```bash
git clone https://github.com/<usuario>/examen1progra3-apellido1-apellido2.git
cd examen1progra3-apellido1-apellido2

git config user.name  "Nombre Apellido"
git config user.email "correo-asociado-a-su-cuenta@ejemplo.com"
```

> El correo configurado debe coincidir con el de su cuenta de GitHub; de lo contrario sus commits no aparecerán asociados a usted.

### 0.3 Requisitos técnicos

| Herramienta | Versión recomendada |
|---|---|
| JDK | 21 (LTS) o superior |
| Maven | 3.9 o superior (o el que integra el IDE) |
| MySQL Server | 8.0 o superior |
| Cliente MySQL | MySQL Workbench, DBeaver o la consola `mysql` |
| IDE | NetBeans, IntelliJ IDEA o VS Code con soporte Maven |
| Git | Cualquier versión reciente |

El controlador JDBC (`mysql-connector-j`) y JUnit 5 se descargan automáticamente mediante Maven.

### 0.4 Preparar la base de datos

1. Cree la base de datos y las tablas ejecutando el script de su pareja:

   ```bash
   mysql -u root -p < database/schema.sql
   ```

   (o ábralo y ejecútelo desde MySQL Workbench). El script del repositorio base es solo una plantilla; cada pareja lo completa según su caso.

2. Configure la conexión **en su equipo**:

   ```bash
   cd proyecto/src/main/resources
   cp db.properties.example db.properties
   ```

   Edite `db.properties` con su usuario y contraseña de MySQL.

> `db.properties` está excluido en `.gitignore`. **Nunca publique credenciales reales en GitHub.** Solo se versiona `db.properties.example`.

### 0.5 Ejecutar el proyecto base

```bash
cd proyecto
mvn clean compile exec:java
```

Desde el IDE: abra la carpeta `proyecto/` como proyecto Maven y ejecute la clase `cr.ac.una.app.App`.

El proyecto base incluye:

- `config/ConexionBD.java`: entrega conexiones JDBC leyendo `db.properties` (ya implementada);
- `app/App.java`: menú de consola con la opción *Probar conexión*;
- `app/Consola.java`: lectura básica de texto y números;
- los paquetes `model`, `repository`, `service`, `controller` y `exception`, vacíos y documentados;
- una prueba JUnit de ejemplo en `src/test/java`.

Ejecute la opción **1. Probar conexión** para verificar que MySQL y `db.properties` están bien configurados antes de comenzar.

### 0.6 Fechas de entrega

| Corte | Etiqueta | Fecha y hora límite |
|---|---|---|
| Etapa 1 | `etapa-1` | Viernes 9 de octubre de 2026, 23:59 |
| Etapa 2 | `etapa-2` | Domingo 11 de octubre de 2026, 23:59 |
| Etapa 3 | `etapa-3` | Sábado 17 de octubre de 2026, 23:59 |
| Defensa | — | No hay proceso de defensa presencial |

Se evalúa el commit al que apunta cada etiqueta **publicada en GitHub** antes de la fecha límite.

---

## 1. Descripción general

Cada pareja desarrollará una aplicación Java a partir de un **caso asignado**. Los casos son diferentes, pero de complejidad equivalente.

El examen se realizará en **tres etapas acumulativas** sobre el mismo sistema y el mismo repositorio. Cada etapa deberá quedar registrada mediante commits claros y una etiqueta (`tag`) específica.

La evolución esperada será:

```text
ETAPA 1
Análisis, diseño y arquitectura por capas
      ↓
ETAPA 2
Implementación funcional con JDBC y MySQL
      ↓
ETAPA 3
Integración, pruebas y mejora
```

**Competencia central evaluada:** desarrollar software Java funcional que pueda extenderse, modificarse y mantenerse sin rehacer innecesariamente sus componentes.

---

## 2. Modalidad

- Trabajo en parejas.
- Un único repositorio por pareja.
- Ambos integrantes deben realizar aportes identificables mediante commits hechos desde su propia cuenta.
- Lenguaje: Java 21 o superior.
- Gestión del proyecto: Maven (se entrega el `pom.xml` configurado).
- Persistencia: **JDBC y MySQL** (obligatoria).
- Arquitectura por capas: `model`, `repository`, `service`, `controller`.
- Interfaz: consola (suficiente para obtener el puntaje completo).
- No se permite usar frameworks de persistencia (JPA, Hibernate, Spring Data, MyBatis, etc.): el acceso a datos debe hacerse con JDBC.

---

## 3. Objetivos

### Objetivo general

Desarrollar una aplicación Java funcional, organizada y mantenible que resuelva una problemática específica mediante arquitectura por capas y persistencia con JDBC y MySQL.

### Objetivos específicos

1. Analizar un problema y traducirlo a clases, relaciones y reglas de negocio.
2. Separar responsabilidades entre modelo, repositorio, servicio y controlador.
3. Implementar persistencia con JDBC y MySQL.
4. Incorporar validaciones y manejo de excepciones.
5. Verificar el funcionamiento mediante pruebas documentadas.
6. Evidenciar el desarrollo incremental mediante GitHub.

---

## 4. Temas evaluados

- análisis de requerimientos y reglas de negocio;
- diagrama de clases UML;
- modelo relacional (tablas, llaves primarias y foráneas, restricciones);
- arquitectura por capas y separación de responsabilidades;
- interfaces y programación contra abstracciones;
- patrón Repository / DAO;
- JDBC: `Connection`, `PreparedStatement`, `ResultSet`;
- `try-with-resources`;
- transacciones (`commit` / `rollback`) cuando una operación modifica varias tablas;
- validaciones y excepciones personalizadas;
- pruebas funcionales y de error;
- refactorización y reutilización;
- control de versiones con Git y GitHub.

---

## 5. Estructura del repositorio base

```text
examen1progra3/
│
├── README.md                  ← esta guía (no modificar)
├── .gitignore
│
├── casos/
│   ├── caso_01.md             ← Préstamo de equipos tecnológicos
│   ├── caso_02.md             ← Gestión de citas médicas
│   ├── caso_03.md             ← Reservación de aulas universitarias
│   ├── caso_04.md             ← Control de inventario de suministros
│   ├── caso_05.md             ← Gestión de biblioteca
│   ├── caso_06.md             ← Administración de parqueos
│   ├── caso_07.md             ← Gestión de órdenes de reparación
│   ├── caso_08.md             ← Reservación de espacios deportivos
│   ├── caso_09.md             ← Registro de solicitudes de soporte
│   └── caso_10.md             ← Control de herramientas de laboratorio
│
├── database/
│   └── schema.sql             ← script SQL (preliminar en Etapa 1, definitivo en Etapa 2)
│
├── docs/
│   ├── etapa_1/
│   │   ├── entrega.md         ← análisis del caso
│   │   └── diagramas/         ← UML y modelo relacional (los agrega la pareja)
│   ├── etapa_2/
│   │   ├── entrega.md         ← evidencias de implementación
│   │   └── img/               ← capturas de ejecución
│   └── etapa_3/
│       ├── entrega.md         ← integración y cambio de requerimiento
│       ├── pruebas.md         ← pruebas documentadas
│       ├── mejoras.md         ← informe de mejoras
│       └── img/
│
└── proyecto/
    ├── README.md              ← README final de la pareja
    ├── pom.xml
    └── src/...                ← ver sección 19
```

Cada pareja trabaja sobre una copia propia de este repositorio. Las plantillas de `docs/` ya contienen los encabezados que deben completarse.

---

## 6. Casos disponibles

El detalle de cada caso (entidades sugeridas, modelo relacional, reglas de negocio y operación principal) está en la carpeta [`casos/`](casos/). Las sugerencias son orientativas: la pareja puede proponer otras clases o tablas siempre que justifique su diseño y cumpla los requisitos comunes (sección 7).

| Caso | Sistema | Regla de negocio principal |
|---|---|---|
| [1](casos/caso_01.md) | Préstamo de equipos tecnológicos | Impedir préstamos de equipos no disponibles |
| [2](casos/caso_02.md) | Gestión de citas médicas | Evitar conflictos de horarios para un profesional |
| [3](casos/caso_03.md) | Reservación de aulas universitarias | Impedir reservas simultáneas del mismo espacio |
| [4](casos/caso_04.md) | Control de inventario de suministros | No permitir salidas superiores a las existencias |
| [5](casos/caso_05.md) | Gestión de biblioteca | Controlar disponibilidad y devolución de libros |
| [6](casos/caso_06.md) | Administración de parqueos | Respetar la capacidad máxima y evitar duplicidades |
| [7](casos/caso_07.md) | Gestión de órdenes de reparación | Controlar transiciones válidas entre estados |
| [8](casos/caso_08.md) | Reservación de espacios deportivos | Evitar reservas superpuestas |
| [9](casos/caso_09.md) | Registro de solicitudes de soporte | Gestionar prioridades y estados de atención |
| [10](casos/caso_10.md) | Control de herramientas de laboratorio | Registrar asignaciones y devoluciones válidas |

### 6.1 Asignación del caso

La asignación de casos se realiza por aparte, según lo indique el docente. Cada pareja debe registrar el caso asignado en `docs/etapa_1/entrega.md` (sección 9.1). No se aceptará el cambio de caso después de la Etapa 1 sin autorización del docente.

---

## 7. Requisitos comunes a todos los casos

Independientemente del caso, el sistema debe incluir como mínimo:

1. **Al menos tres tablas** en MySQL, de las cuales al menos dos estén relacionadas mediante llave foránea (normalmente: dos entidades principales y una entidad que registra la operación entre ellas).
2. **Registro** (INSERT) de las entidades principales.
3. **Consulta** (SELECT) por identificador y listado general.
4. **Actualización** (UPDATE) de al menos una entidad.
5. **Una consulta con filtro o JOIN** (por ejemplo: préstamos activos de una persona, citas de un profesional en una fecha).
6. **Una operación de negocio significativa** que aplique la regla principal del caso y modifique el estado del sistema.
7. **Al menos una enumeración** (`enum`) para representar estados, tipos o prioridades.
8. **Al menos dos excepciones personalizadas** (por ejemplo, una de validación y una de regla de negocio).

---

## ETAPA 1 — Análisis, diseño y arquitectura (25 puntos)

### 8. Objetivo

A partir del caso asignado, analizar la problemática y construir la estructura inicial de la aplicación. La solución debe evidenciar separación de responsabilidades, un modelo de datos coherente y las operaciones necesarias para resolver el problema.

### 9. Entregables

Completar `docs/etapa_1/entrega.md` con:

#### 9.1 Identificación

```text
Caso asignado:
Integrante 1:
Integrante 2:
URL del repositorio:
```

#### 9.2 Descripción del problema

Explicar:

- qué problema se desea resolver;
- quién utilizaría el sistema;
- cuáles son las principales operaciones.

#### 9.3 Requerimientos funcionales

Definir al menos **ocho requerimientos funcionales**, incluyendo registro, consulta, actualización y la operación de negocio principal.

```text
RF-01. El sistema permitirá registrar equipos.
RF-02. El sistema permitirá consultar un equipo por su código.
RF-03. El sistema permitirá registrar el préstamo de un equipo disponible.
```

#### 9.4 Reglas de negocio

Identificar las reglas de negocio del caso. Cada regla debe ser verificable y tener un identificador.

| ID | Regla | Operación donde se aplica | Qué ocurre si se viola |
|---|---|---|---|
| RN-01 | No se puede prestar un equipo que no esté disponible | Registrar préstamo | Se rechaza con `ReglaNegocioException` |

#### 9.5 Entidades, atributos y relaciones

| Clase | Responsabilidad | Atributos principales |
|---|---|---|
| | | |

| Clases relacionadas | Tipo de relación | Multiplicidad | Explicación |
|---|---|---|---|
| | | | |

#### 9.6 Diagrama de clases UML

Incluir en `docs/etapa_1/diagramas/` un archivo `uml.png` o `uml.pdf` (y el archivo fuente si usa draw.io, PlantUML, StarUML, etc.).

Debe mostrar las clases de **todas las capas**:

- entidades del modelo con sus atributos;
- enumeraciones;
- interfaces `Repository`/`DAO` y sus implementaciones;
- clases `Service` y `Controller`;
- relaciones y multiplicidades;
- dependencias entre capas.

#### 9.7 Modelo relacional

Incluir en `docs/etapa_1/diagramas/` un archivo `modelo_relacional.png` o `.pdf` con tablas, columnas, tipos, llaves primarias, llaves foráneas y restricciones (`NOT NULL`, `UNIQUE`, `CHECK`).

Además, completar `database/schema.sql` en su **versión preliminar** (creación de la base de datos y de las tablas).

#### 9.8 Arquitectura propuesta

Describir qué clases tendrá cada capa y qué responsabilidad cumplirá:

| Capa | Clases | Responsabilidad |
|---|---|---|
| `model` | | |
| `repository` | | |
| `service` | | |
| `controller` | | |

### 10. Código mínimo de la Etapa 1

En la carpeta `proyecto/` (partiendo del proyecto base):

1. clases del modelo con atributos privados, constructores, getters/setters y `toString()`;
2. enumeraciones;
3. una **interface** `Repository` o `DAO` por entidad principal, con las operaciones necesarias (sin implementar todavía);
4. la **estructura** de las clases `Service` y `Controller` (atributos, constructor y firma de los métodos);
5. excepciones personalizadas declaradas en `exception`.

Ejemplo de interface:

```java
public interface EstudianteRepository {
    void insertar(Estudiante estudiante);
    Optional<Estudiante> buscarPorId(String id);
    List<Estudiante> listar();
    void actualizar(Estudiante estudiante);
}
```

**Alcance:** no se exige que las operaciones JDBC estén completas. El proyecto **debe compilar**.

### 11. Evidencia

Debe existir la etiqueta `etapa-1` publicada en GitHub (ver sección 36.1).

---

## ETAPA 2 — Implementación funcional y persistencia (45 puntos)

### 12. Objetivo

Continuar la arquitectura de la Etapa 1 e implementar las funcionalidades principales del sistema, incorporando reglas de negocio y persistencia con JDBC y MySQL.

### 13. Operaciones mínimas

El sistema debe permitir, desde la consola y con datos guardados en MySQL:

- registrar las entidades principales;
- consultar por identificador y listar;
- actualizar datos o estados;
- ejecutar la consulta con filtro o JOIN (sección 7);
- ejecutar la **operación de negocio principal** del caso.

### 14. Operación de negocio

La operación de negocio debe:

1. validar los datos recibidos;
2. consultar el estado actual en la base de datos;
3. aplicar la regla principal del caso en la capa `Service`;
4. registrar el resultado y actualizar el estado correspondiente;
5. lanzar una excepción personalizada si la regla se viola.

Si la operación modifica **más de una tabla** (por ejemplo, insertar un préstamo y cambiar el estado del equipo), debe ejecutarse como una **transacción**: o se aplican todos los cambios o ninguno.

```java
try (Connection con = ConexionBD.obtenerConexion()) {
    con.setAutoCommit(false);
    try {
        // varias operaciones con la misma conexión
        con.commit();
    } catch (SQLException e) {
        con.rollback();
        throw e;
    }
}
```

### 15. JDBC

Todas las operaciones de base de datos deben:

- usar `PreparedStatement` con parámetros (`?`); **no se permite concatenar valores en el SQL**;
- usar `try-with-resources` para `Connection`, `PreparedStatement` y `ResultSet`;
- convertir cada fila del `ResultSet` en un objeto del modelo (método privado `mapear(ResultSet rs)` recomendado);
- obtener la conexión mediante `ConexionBD.obtenerConexion()`.

Ejemplo:

```java
public Optional<Estudiante> buscarPorId(String id) {
    String sql = "SELECT id, nombre, correo FROM estudiante WHERE id = ?";
    try (Connection con = ConexionBD.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
        }
    } catch (SQLException e) {
        throw new DatosException("Error al buscar el estudiante " + id, e);
    }
}
```

### 16. Separación de capas

```text
App (consola)
   ↓  lee datos y muestra resultados
Controller
   ↓  recibe la solicitud y coordina
Service
   ↓  valida y aplica reglas de negocio
Repository / DAO  (interface)
   ↓  implementación JDBC
MySQL
```

| Capa | Puede | No puede |
|---|---|---|
| `model` | Representar datos y estados | Usar `java.sql.*` ni leer consola |
| `repository` | Escribir SQL y mapear resultados | Contener reglas de negocio |
| `service` | Validar y aplicar reglas; usar interfaces de `repository` | Escribir SQL ni leer/escribir consola |
| `controller` | Invocar servicios y preparar respuestas | Escribir SQL ni decidir reglas de negocio |
| `app` | Mostrar menús y leer datos | Acceder directamente a `repository` o a la base de datos |

El `Service` debe depender de la **interface** del repositorio, recibida por constructor, y no de la clase JDBC concreta:

```java
public class EstudianteService {
    private final EstudianteRepository repositorio;

    public EstudianteService(EstudianteRepository repositorio) {
        this.repositorio = repositorio;
    }
}
```

### 17. Validaciones y excepciones

- Validar campos obligatorios, formatos, rangos, fechas (`java.time`) y duplicados.
- Lanzar excepciones personalizadas desde `service` cuando se viola una regla.
- Envolver `SQLException` en una excepción propia (por ejemplo `DatosException`) en la capa `repository`.
- Capturar las excepciones en `controller` o `app` y mostrar un **mensaje claro** al usuario.
- Una entrada inválida o un error de base de datos **no debe cerrar la aplicación**.
- Se recomienda reforzar las reglas con restricciones en la base de datos (`UNIQUE`, `FOREIGN KEY`, `CHECK`), pero la regla principal debe validarse en `Service`.

### 18. Interfaz de consola

Basta con un menú de consola que permita ejecutar todas las operaciones. Se recomienda cargar algunos **datos de prueba** en el script SQL para agilizar la demostración.

### 19. Organización del proyecto

```text
proyecto/
├── pom.xml
└── src
    ├── main
    │   ├── java/cr/ac/una
    │   │   ├── app/          App.java (menú), Consola.java
    │   │   ├── config/       ConexionBD.java (ya implementada)
    │   │   ├── model/        entidades y enumeraciones
    │   │   ├── repository/   interfaces + implementaciones JDBC
    │   │   ├── service/      reglas de negocio
    │   │   ├── controller/   coordinación entre consola y servicios
    │   │   └── exception/    excepciones personalizadas
    │   └── resources
    │       ├── db.properties.example
    │       └── db.properties        ← local, NO se publica
    └── test/java/cr/ac/una/          pruebas JUnit (Etapa 3)
```

### 20. Script SQL definitivo

`database/schema.sql` debe permitir crear la base de datos **desde cero** en otro equipo:

- `DROP DATABASE IF EXISTS` / `CREATE DATABASE`;
- creación de todas las tablas con llaves y restricciones;
- datos de prueba (`INSERT`) suficientes para demostrar el sistema.

### 21. Documento de la Etapa 2

Completar `docs/etapa_2/entrega.md` incluyendo:

- cambios al diseño respecto a la Etapa 1;
- instrucciones de configuración y ejecución;
- operaciones implementadas y la clase/método donde se encuentran;
- reglas de negocio implementadas;
- fragmentos de código de JDBC, del `Service` y de una excepción;
- evidencias de ejecución (salida de consola o capturas en `docs/etapa_2/img/`).

### 22. Evidencia

Debe existir la etiqueta `etapa-2` publicada en GitHub.

---

## ETAPA 3 — Integración, pruebas y mejora (30 puntos)

### 23. Objetivo

Completar y verificar el sistema, identificar problemas de funcionamiento y realizar mejoras que demuestren calidad, robustez y facilidad de mantenimiento.

### 24. Integración

Todas las funcionalidades deben estar disponibles desde el menú principal y funcionar sobre MySQL. Las reglas de negocio deben comprobarse con datos reales.

### 25. Cambio de requerimiento

Al inicio de la Etapa 3 el docente comunicará a cada pareja un **cambio pequeño de requerimiento** relacionado con su caso (por ejemplo: una nueva regla, un nuevo estado o una nueva consulta).

La pareja debe:

1. implementarlo modificando el menor número posible de clases;
2. documentar en `docs/etapa_3/entrega.md` qué clases y capas modificó y por qué el diseño facilitó (o dificultó) el cambio.

Un diseño con capas bien separadas debería permitir resolverlo sin rehacer componentes.

### 26. Pruebas

Documentar en `docs/etapa_3/pruebas.md` al menos **diez casos de prueba**:

- al menos **cuatro pruebas exitosas** (operaciones que deben funcionar);
- al menos **cuatro pruebas de error** (datos inválidos o violación de reglas);
- al menos **una prueba** de la operación de negocio principal en cada sentido (éxito y rechazo).

| ID | Descripción | Datos de entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|---|
| P-01 | Registrar préstamo de equipo disponible | equipo EQ-01, persona 1-1111-1111 | Préstamo registrado; equipo en estado PRESTADO | | ✅ / ❌ |

Las pruebas pueden ejecutarse de forma manual (con evidencia) o automatizarse con **JUnit 5** en `src/test/java` (`mvn test`). Se valora automatizar al menos las pruebas de la capa `Service`.

> Sugerencia: para probar un `Service` sin base de datos, cree en la prueba una implementación en memoria de la interface `Repository`. Esto solo es posible si el `Service` depende de la interface.

### 27. Mejora de calidad

Implementar al menos **una mejora concreta** de reutilización o separación de responsabilidades y documentarla en `docs/etapa_3/mejoras.md` (antes / después). Ejemplos:

- extraer un validador reutilizable usado por varios servicios;
- centralizar el mapeo de `ResultSet` en un método o clase;
- eliminar código duplicado entre repositorios;
- mover una regla que estaba en el `Controller` o en `App` hacia el `Service`;
- reemplazar dependencias de clases concretas por interfaces;
- mejorar los mensajes de error para el usuario.

### 28. Corrección de defectos

Registrar en `docs/etapa_3/mejoras.md` los defectos encontrados durante las pruebas y cómo se corrigieron (con referencia al commit).

### 29. Documentos de la Etapa 3

- `docs/etapa_3/entrega.md`: integración, cambio de requerimiento y decisiones de diseño;
- `docs/etapa_3/pruebas.md`: pruebas y resultados;
- `docs/etapa_3/mejoras.md`: mejoras y defectos corregidos;
- `docs/etapa_1/diagramas/uml_final.png` (o `.pdf`) con el **UML actualizado**, si el diseño cambió.

### 30. README final

El archivo [`proyecto/README.md`](proyecto/README.md) deberá incluir:

- curso e integrantes;
- caso asignado y descripción del sistema;
- funcionalidades implementadas;
- reglas de negocio implementadas;
- arquitectura y estructura del proyecto;
- configuración de la base de datos y pasos para ejecutar;
- cómo ejecutar las pruebas;
- tecnologías utilizadas (con versiones);
- enlace al video de demostración (sección 32).

### 31. Evidencia

Debe existir la etiqueta `etapa-3` publicada en GitHub.

---

## 32. Demostración (sin defensa presencial)

No hay proceso de defensa presencial. En su lugar, cada pareja grabará un **video de demostración** de máximo **5 minutos** y colocará el enlace (YouTube no listado, Google Drive u otro medio accesible para el docente) en `proyecto/README.md` antes de la fecha límite de la Etapa 3.

En el video **ambos integrantes deben participar** y mostrar:

- la creación de la base de datos con `database/schema.sql` y la ejecución del sistema;
- la operación de negocio principal en un caso exitoso y en un caso rechazado por una regla;
- dónde se aplica una regla de negocio (clase `Service`);
- un método del repositorio con `PreparedStatement` y `try-with-resources`;
- el cambio de requerimiento de la Etapa 3 funcionando.

La comprensión individual forma parte de la evaluación: cada integrante debe explicar al menos una parte del código.

---

## 33. Distribución de la evaluación

| Etapa | Descripción | Puntaje |
|---|---|---:|
| 1 | Análisis, diseño y arquitectura | 25 |
| 2 | Implementación funcional y JDBC | 45 |
| 3 | Integración, pruebas y mejora | 30 |
| **Total** | | **100** |

---

## 34. Rúbricas

### 34.1 Etapa 1 — 25 puntos

| Criterio | Puntos |
|---|---:|
| Identificación de entidades y reglas de negocio | 6 |
| Diagrama UML y modelo relacional | 7 |
| Organización por capas y paquetes | 7 |
| Definición de interfaces y responsabilidades | 5 |
| **Total** | **25** |

### 34.2 Etapa 2 — 45 puntos

| Criterio | Puntos |
|---|---:|
| Operaciones funcionales del sistema | 12 |
| Aplicación correcta de reglas de negocio | 9 |
| Implementación JDBC y MySQL | 12 |
| Validaciones y manejo de excepciones | 7 |
| Separación entre `Service` y `Repository` | 5 |
| **Total** | **45** |

### 34.3 Etapa 3 — 30 puntos

| Criterio | Puntos |
|---|---:|
| Funcionamiento integrado del sistema (incluye el cambio de requerimiento) | 8 |
| Pruebas funcionales y de errores | 8 |
| Mejoras de calidad y reutilización | 6 |
| Documentación técnica y README | 4 |
| Video de demostración y explicación del código | 4 |
| **Total** | **30** |

## 35. Escala por criterio

| Nivel | Porcentaje del criterio |
|---|---:|
| Cumplimiento completo y correcto | 100 % |
| Cumplimiento satisfactorio con detalles menores | 75 % |
| Cumplimiento parcial | 50 % |
| Evidencia mínima o solución considerablemente incompleta | 25 % |
| No evidenciado | 0 % |

Penalizaciones que se aplican dentro de los criterios correspondientes:

- SQL construido por concatenación de valores → no se otorga el nivel completo en «Implementación JDBC y MySQL».
- SQL o reglas de negocio en `App` o `Controller` → no se otorga el nivel completo en «Separación entre `Service` y `Repository`».
- Credenciales reales publicadas en el repositorio → se descuenta en «Documentación técnica y README».
- Proyecto que no compila en la etiqueta evaluada → la etapa se califica sobre lo que pueda verificarse.

---

## 36. Reglas de trabajo con Git

Los commits deben describir cambios reales.

Ejemplos adecuados:

```text
Agrega entidades y enumeraciones del modelo
Define interfaces de repositorio
Crea script SQL preliminar
Implementa EquipoRepositoryJdbc con PreparedStatement
Agrega regla de disponibilidad en PrestamoService
Agrega pruebas de PrestamoService
Extrae validador reutilizable
```

Evite mensajes como:

```text
cambios
avance
prueba
final
```

El historial debe mostrar evolución real y ambos integrantes deben tener aportes identificables. Si trabajan juntos en un mismo equipo (programación en pareja), agreguen al final del mensaje del commit:

```text
Co-authored-by: Nombre Compañero <correo-del-companero@ejemplo.com>
```

Otras reglas:

- Haga `git pull` antes de empezar a trabajar y `git push` al terminar cada sesión.
- No suba archivos generados (`target/`, `.class`, configuraciones del IDE); el `.gitignore` ya los excluye.
- No suba `db.properties` ni credenciales; el `.gitignore` ya lo excluye.
- No suba el proyecto comprimido (`.zip`, `.rar`).

### 36.1 Cómo crear y publicar la etiqueta de cada etapa

```bash
# 1. Asegúrese de que todo está confirmado y publicado
git status
git push

# 2. Cree la etiqueta sobre el último commit de la etapa
git tag -a etapa-1 -m "Entrega de la Etapa 1"

# 3. Publique la etiqueta (git push NO publica etiquetas por sí solo)
git push origin etapa-1
```

Verifique en GitHub (*Code → Tags*) que la etiqueta aparece. Use `etapa-2` y `etapa-3` en las etapas siguientes.

No elimine ni mueva una etiqueta ya publicada: las modificaciones posteriores no alteran la versión evaluada de una etapa anterior. Si necesita corregir algo después de la fecha límite, consulte al docente.

---

## 37. Condiciones generales

- El docente entrega la estructura Maven, el `pom.xml`, los paquetes base, la clase de conexión y el `.gitignore`, sin implementar las funcionalidades evaluadas.
- Se permite consultar la documentación oficial de Java, JDBC y MySQL.
- El uso de herramientas de inteligencia artificial se ajustará a las reglas que establezca el docente.
- Ambos integrantes deben comprender el código y poder explicar las decisiones tomadas.
- No se deben publicar credenciales reales de base de datos en GitHub.

---

## 38. Lista de comprobación final

```text
[ ] Trabajamos sobre el repositorio de la pareja.
[ ] Ambos integrantes tienen commits identificables.
[ ] Existe la etiqueta etapa-1 (publicada en GitHub).
[ ] Existe la etiqueta etapa-2 (publicada en GitHub).
[ ] Existe la etiqueta etapa-3 (publicada en GitHub).
[ ] docs/etapa_1/entrega.md indica el caso asignado.
[ ] El UML y el modelo relacional corresponden con el código final.
[ ] database/schema.sql crea la base de datos desde cero con datos de prueba.
[ ] Hay al menos tres tablas y llaves foráneas.
[ ] Existen interfaces Repository/DAO y sus implementaciones JDBC.
[ ] Todo el SQL está en la capa repository.
[ ] Todas las consultas usan PreparedStatement con parámetros.
[ ] Las conexiones se cierran con try-with-resources.
[ ] Las reglas de negocio están en la capa service.
[ ] La operación de negocio principal funciona y rechaza los casos inválidos.
[ ] Las operaciones que modifican varias tablas usan transacción.
[ ] Existen excepciones personalizadas y se muestran mensajes claros.
[ ] Una entrada inválida no cierra la aplicación.
[ ] Implementamos el cambio de requerimiento de la Etapa 3.
[ ] docs/etapa_3/pruebas.md tiene al menos diez pruebas (éxito y error).
[ ] docs/etapa_3/mejoras.md documenta la mejora y los defectos corregidos.
[ ] proyecto/README.md está completo e incluye el enlace al video.
[ ] El video dura máximo 5 minutos y participan ambos integrantes.
[ ] db.properties NO está en el repositorio.
[ ] El proyecto compila y ejecuta con: mvn clean compile exec:java
[ ] Ambos integrantes pueden explicar el código.
```

---

## 39. Resultado esperado

El repositorio deberá evidenciar claramente la evolución:

```text
CASO
  ↓
ANÁLISIS Y REGLAS DE NEGOCIO
  ↓
UML + MODELO RELACIONAL
  ↓
ARQUITECTURA POR CAPAS
  ↓
REPOSITORY (JDBC) + MYSQL
  ↓
SERVICE (REGLAS) + EXCEPCIONES
  ↓
INTEGRACIÓN Y CAMBIO DE REQUERIMIENTO
  ↓
PRUEBAS Y MEJORAS
  ↓
SISTEMA MANTENIBLE
```

El repositorio no es únicamente el medio de entrega: también constituye evidencia del proceso de desarrollo realizado durante las tres etapas.
