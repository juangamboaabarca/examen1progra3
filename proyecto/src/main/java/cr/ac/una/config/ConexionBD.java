package cr.ac.una.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Proporciona conexiones JDBC a MySQL.
 *
 * Lee los datos de conexión del archivo {@code db.properties}, ubicado en
 * {@code src/main/resources/}. Ese archivo NO se publica en GitHub: copie
 * {@code db.properties.example} como {@code db.properties} y complete sus
 * propios datos.
 *
 * Uso esperado (siempre con try-with-resources):
 *
 * <pre>
 * try (Connection con = ConexionBD.obtenerConexion();
 *      PreparedStatement ps = con.prepareStatement(sql)) {
 *     ...
 * }
 * </pre>
 *
 * Esta clase ya está completa; no forma parte de lo evaluado.
 */
public final class ConexionBD {

    private static final String ARCHIVO = "db.properties";

    private static String url;
    private static String usuario;
    private static String clave;

    private ConexionBD() {
    }

    /**
     * Abre una nueva conexión. Quien la solicita es responsable de cerrarla
     * (use try-with-resources).
     */
    public static Connection obtenerConexion() throws SQLException {
        if (url == null) {
            cargarConfiguracion();
        }
        return DriverManager.getConnection(url, usuario, clave);
    }

    private static synchronized void cargarConfiguracion() throws SQLException {
        if (url != null) {
            return;
        }
        Properties props = new Properties();
        try (InputStream in = ConexionBD.class.getClassLoader().getResourceAsStream(ARCHIVO)) {
            if (in == null) {
                throw new SQLException("No se encontró " + ARCHIVO + " en src/main/resources. "
                        + "Copie db.properties.example como db.properties y complete sus datos.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new SQLException("No se pudo leer " + ARCHIVO, e);
        }
        String u = props.getProperty("db.url");
        if (u == null || u.isBlank()) {
            throw new SQLException("La propiedad db.url no está definida en " + ARCHIVO);
        }
        usuario = props.getProperty("db.usuario", "");
        clave = props.getProperty("db.clave", "");
        url = u.trim();
    }
}
