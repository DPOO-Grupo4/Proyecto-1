package System;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class PreguntaOpcionMultipleTest {

    private PreguntaOpcionMultiple pregunta;
    private Opcion respuestaCorrecta;
    private ArrayList<Opcion> opciones;

    @BeforeEach
    void setUp() {
        // Crear las opciones con la nueva definición
        respuestaCorrecta = new Opcion("Explicación de la respuesta correcta", true, "Rojo", 1);  // Respuesta correcta
        Opcion opcionIncorrecta1 = new Opcion("Explicación de la respuesta incorrecta", false, "Azul", 2);
        Opcion opcionIncorrecta2 = new Opcion("Explicación de la respuesta incorrecta", false, "Verde", 3);

        // Crear lista de opciones
        opciones = new ArrayList<>();
        opciones.add(respuestaCorrecta);
        opciones.add(opcionIncorrecta1);
        opciones.add(opcionIncorrecta2);

        // Crear la instancia de PreguntaOpcionMultiple
        pregunta = new PreguntaOpcionMultiple(respuestaCorrecta, "¿Cuál es tu color favorito?", 1, opciones);
    }

    @Test
    void testConstructor() {
        // Verificar que el constructor funciona correctamente
        assertNotNull(pregunta);
        assertEquals("¿Cuál es tu color favorito?", pregunta.getEnunciado());
        assertEquals(1, pregunta.getID());
        assertEquals(3, pregunta.getOpciones().size());
        assertTrue(pregunta.getOpciones().contains(respuestaCorrecta));
    }

    @Test
    void testGetOpciones() {
        // Verificar que getOpciones devuelve la lista correcta de opciones
        ArrayList<Opcion> opcionesRecuperadas = pregunta.getOpciones();
        assertNotNull(opcionesRecuperadas);
        assertEquals(3, opcionesRecuperadas.size());
        assertTrue(opcionesRecuperadas.contains(respuestaCorrecta));
    }

    @Test
    void testRespuestaCorrecta() {
        // Verificar que la respuesta correcta es la que esperamos
        assertTrue(respuestaCorrecta.getCorrect());
        assertFalse(new Opcion("Explicación de la respuesta incorrecta", false, "Azul", 2).getCorrect());
    }

    @Test
    void testOpcionesNoVacias() {
        // Verificar que las opciones no estén vacías
        assertFalse(pregunta.getOpciones().isEmpty());
    }

    @Test
    void testConstructorConOpcionesVacias() {
        // Probar con una lista de opciones vacía (aunque no es un caso muy común en un cuestionario real)
        ArrayList<Opcion> opcionesVacias = new ArrayList<>();
        PreguntaOpcionMultiple preguntaVacia = new PreguntaOpcionMultiple(null, "Pregunta sin opciones", 2, opcionesVacias);
        assertNotNull(preguntaVacia);
        assertTrue(preguntaVacia.getOpciones().isEmpty());
    }

    @Test
    void testOpcionCorrecta() {
        // Verificar que los atributos de la respuesta correcta se asignen correctamente
        assertEquals("Rojo", respuestaCorrecta.getEnunciado());
        assertEquals(1, respuestaCorrecta.getID());
        assertTrue(respuestaCorrecta.getCorrect());
        assertEquals("Explicación de la respuesta correcta", respuestaCorrecta.getExplicacion());
    }

    @Test
    void testOpcionIncorrecta() {
        // Verificar que los atributos de las opciones incorrectas también se asignen correctamente
        Opcion opcionIncorrecta = new Opcion("Explicación de la respuesta incorrecta", false, "Azul", 2);
        assertEquals("Azul", opcionIncorrecta.getEnunciado());
        assertFalse(opcionIncorrecta.getCorrect());
        assertEquals(2, opcionIncorrecta.getID());
        assertEquals("Explicación de la respuesta incorrecta", opcionIncorrecta.getExplicacion());
    }
}
