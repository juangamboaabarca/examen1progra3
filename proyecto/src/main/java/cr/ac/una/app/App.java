package cr.ac.una.app;

import cr.ac.una.config.ConexionBD;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Punto de entrada de la aplicación (interfaz de consola).
 *
 * Responsabilidad de esta clase: mostrar menús, leer datos y presentar
 * resultados. No debe contener SQL ni reglas de negocio: debe delegar en
 * los Controllers del paquete controller.
 *
 * Flujo esperado:  App (consola) → Controller → Service → Repository → MySQL
 */
public class App {

    public static void main(String[] args) {
        int opcion;
        do {
            Consola.titulo("Examen Práctico Integrador - Programación III");
            System.out.println("1. Probar conexión con la base de datos");
            // TODO (pareja): agregar las opciones de su caso, por ejemplo:
            // 2. Registrar ...
            // 3. Consultar ...
            // 4. Actualizar ...
            // 5. <Operación de negocio del caso>
            System.out.println("0. Salir");
            opcion = Consola.leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> probarConexion();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void probarConexion() {
        try (Connection con = ConexionBD.obtenerConexion()) {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Conexión exitosa: " + meta.getDatabaseProductName()
                    + " " + meta.getDatabaseProductVersion()
                    + " — base de datos: " + con.getCatalog());
        } catch (SQLException e) {
            System.out.println("No fue posible conectar: " + e.getMessage());
        }
    }
}
