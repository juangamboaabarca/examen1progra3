/**
 * Capa de acceso a datos (Repository o DAO).
 * - Defina una interface por entidad (por ejemplo EquipoRepository) y su
 *   implementación JDBC (por ejemplo EquipoRepositoryJdbc).
 * - Aquí, y solo aquí, se escribe SQL: PreparedStatement + try-with-resources.
 * - Convierte filas (ResultSet) en objetos del modelo.
 * Regla: NO contiene reglas de negocio.
 */
package cr.ac.una.repository;
