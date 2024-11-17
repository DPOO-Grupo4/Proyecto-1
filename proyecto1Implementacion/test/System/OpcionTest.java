package System;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpcionTest {

    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;

    @BeforeEach
    void setUp() {
        // Inicializar las opciones con los valores del constructor
        opcionCorrecta = new Opcion("Explicación de la respuesta correcta", true, "Rojo", 1);  // Respuesta correcta
        opcionIncorrecta = new Opcion("Explicación de la respuesta incorrecta", false, "Azul", 2);  // Respuesta incorrecta
    }

    @Test
    void testConstructor() {
        // Verificar que el constructor inicializa correctamente los valores
        assertNotNull(opcionCorrecta);
        assertNotNull(opcionIncorrecta);
        
        // Verificar los valores de la opción correcta
        assertEquals("Explicación de la respuesta correcta", opcionCorrecta.getExplicacion());
        assertTrue(opcionCorrecta.getCorrect());
        assertEquals("Rojo", opcionCorrecta.getEnunciado());
        assertEquals(1, opcionCorrecta.getID());

        // Verificar los valores de la opción incorrecta
        assertEquals("Explicación de la respuesta incorrecta", opcionIncorrecta.getExplicacion());
        assertFalse(opcionIncorrecta.getCorrect());
        assertEquals("Azul", opcionIncorrecta.getEnunciado());
        assertEquals(2, opcionIncorrecta.getID());
    }

    @Test
    void testGetEnunciado() {
        // Verificar que el método getEnunciado devuelve el valor correcto
        assertEquals("Rojo", opcionCorrecta.getEnunciado());
        assertEquals("Azul", opcionIncorrecta.getEnunciado());
    }

    @Test
    void testGetCorrect() {
        // Verificar que el método getCorrect devuelve el valor correcto
        assertTrue(opcionCorrecta.getCorrect());
        assertFalse(opcionIncorrecta.getCorrect());
    }

    @Test
    void testGetExplicacion() {
        // Verificar que el método getExplicacion devuelve el valor correcto
        assertEquals("Explicación de la respuesta correcta", opcionCorrecta.getExplicacion());
        assertEquals("Explicación de la respuesta incorrecta", opcionIncorrecta.getExplicacion());
    }

    @Test
    void testGetID() {
        // Verificar que el método getID devuelve el valor correcto
        assertEquals(1, opcionCorrecta.getID());
        assertEquals(2, opcionIncorrecta.getID());
    }

    @Test
    void testConstructorValoresNull() {
        // Probar el caso con valores nulos para el constructor
        Opcion opcionNula = new Opcion(null, false, null, 0);
        
        assertNull(opcionNula.getExplicacion());
        assertFalse(opcionNula.getCorrect());
        assertNull(opcionNula.getEnunciado());
        assertEquals(0, opcionNula.getID());
    }
}
