# <Nombre del sistema>

> Plantilla del README final (sección 30 de la guía). Reemplace todo lo que está entre `< >`.

## Curso

EIF206 – Programación III

## Integrantes

| Nombre | Usuario de GitHub |
|---|---|
| | |
| | |

## Caso asignado

<Número y nombre del caso>

## Descripción del sistema

<Descripción breve>

## Funcionalidades implementadas

- [ ] <funcionalidad>

## Reglas de negocio implementadas

| ID | Regla | Dónde se valida |
|---|---|---|
| RN-01 | | |

## Arquitectura

```text
App (consola) → Controller → Service → Repository (interface) → RepositoryJdbc → MySQL
```

<Breve explicación de la responsabilidad de cada capa en su sistema.>

## Estructura del proyecto

```text
proyecto/
├── pom.xml
└── src
    ├── main/java/cr/ac/una
    │   ├── app/
    │   ├── config/
    │   ├── model/
    │   ├── repository/
    │   ├── service/
    │   ├── controller/
    │   └── exception/
    └── test/java/cr/ac/una/
```

## Configuración de la base de datos

```bash
mysql -u root -p < database/schema.sql
cd proyecto/src/main/resources
cp db.properties.example db.properties   # y edite usuario/contraseña
```

## Pasos para ejecutar

```bash
git clone <url-del-repositorio>
cd <repositorio>/proyecto
mvn clean compile exec:java
```

## Ejecutar las pruebas

```bash
cd proyecto
mvn test
```

## Video de demostración

<enlace al video (máximo 5 minutos, participan ambos integrantes)>

## Tecnologías utilizadas

- Java <versión>
- Maven <versión>
- MySQL <versión>
- MySQL Connector/J <versión>
- JUnit <versión> (si se utilizó)
