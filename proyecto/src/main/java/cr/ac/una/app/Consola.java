package cr.ac.una.app;

import java.util.Scanner;

/**
 * Utilidades mínimas para leer datos desde la consola.
 *
 * Solo resuelve la lectura de texto y números. Las validaciones propias
 * del negocio (rangos, duplicados, estados, fechas, etc.) NO van aquí:
 * pertenecen a la capa service.
 */
public final class Consola {

    private static final Scanner ENTRADA = new Scanner(System.in);

    private Consola() {
    }

    /** Lee una línea de texto (puede ser vacía). */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return ENTRADA.nextLine().trim();
    }

    /** Lee un número entero; repite la solicitud mientras el valor no sea válido. */
    public static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("  Valor no válido. Digite un número entero.");
            }
        }
    }

    public static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
