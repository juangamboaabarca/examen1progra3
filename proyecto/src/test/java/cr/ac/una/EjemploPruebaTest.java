package cr.ac.una;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Ejemplo de la forma de una prueba JUnit 5 (Etapa 3).
 *
 * Reemplace este archivo por pruebas reales de su caso, por ejemplo:
 * - una prueba exitosa de la operación de negocio;
 * - una prueba que verifique que se lanza la excepción esperada cuando
 *   se viola una regla (assertThrows).
 *
 * Ejecutar: mvn test
 */
class EjemploPruebaTest {

    @Test
    void pruebaExitosa() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void pruebaDeError() {
        assertThrows(NumberFormatException.class, () -> Integer.parseInt("abc"));
    }
}
