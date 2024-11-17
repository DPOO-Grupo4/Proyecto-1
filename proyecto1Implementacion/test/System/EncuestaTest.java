package System;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

class EncuestaTest {

    private Encuesta encuesta;
    private ArrayList<Pregunta> preguntas;
    private HashMap<Integer, HashMap<String, Object>> respuestas;
    private LocalDateTime dateLimit;

    @BeforeEach
    void setUp() {
        // Fecha límite
        dateLimit = LocalDateTime.of(2024, 11, 16, 12, 0, 0, 0);

        preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿Cuál es tu color favorito?", 1));
        preguntas.add(new Pregunta("¿Cómo te sientes hoy?", 2));

        // Inicializa las respuestas
        respuestas = new HashMap<>();
        HashMap<String, Object> respuesta1 = new HashMap<>();
        respuesta1.put("usuario", "user1");
        respuesta1.put("respuesta", "Rojo");
        respuestas.put(1, respuesta1);

        // Crea la instancia de Encuesta
        encuesta = new Encuesta("Creator", 1, true, "Encuesta sobre colores", "Facil", 30, dateLimit, new HashMap<>(), preguntas, respuestas);
    }

    @Test
    void testConstructor() {
        // Verificar que el constructor inicializa correctamente los valores.
        assertNotNull(encuesta);
        assertEquals("Encuesta sobre colores", encuesta.getDescripcion());
        assertEquals("Facil", encuesta.getDifficulty());
        assertEquals(30, encuesta.getDuration());
        assertEquals(dateLimit, encuesta.getDateLimit());
        assertEquals(preguntas, encuesta.getPreguntas());
    }

    @Test
    void testGetPreguntas() {
        // Verificar que el getter de preguntas devuelve la lista correcta.
        ArrayList<Pregunta> preguntasRecuperadas = encuesta.getPreguntas();
        assertNotNull(preguntasRecuperadas);
        assertEquals(2, preguntasRecuperadas.size());
        assertEquals("¿Cuál es tu color favorito?", preguntasRecuperadas.get(0).getEnunciado());
        assertEquals(1, preguntasRecuperadas.get(0).getID());
    }

    @Test
    void testSetPreguntas() {
        // Verificar que el setter de preguntas establece correctamente la lista.
        ArrayList<Pregunta> nuevasPreguntas = new ArrayList<>();
        nuevasPreguntas.add(new Pregunta("¿Dónde vives?", 3));
        encuesta.setPreguntas(nuevasPreguntas);

        ArrayList<Pregunta> preguntasRecuperadas = encuesta.getPreguntas();
        assertEquals(1, preguntasRecuperadas.size());
        assertEquals("¿Dónde vives?", preguntasRecuperadas.get(0).getEnunciado());
        assertEquals(3, preguntasRecuperadas.get(0).getID());
    }

    @Test
    void testRespuestas() throws NoSuchFieldException, IllegalAccessException {
        // Usar reflexión para acceder al campo 'respuestas' de la clase Encuesta
        Field respuestasField = Encuesta.class.getDeclaredField("respuestas");
        respuestasField.setAccessible(true);

        // Obtener el valor del campo 'respuestas'
        HashMap<Integer, HashMap<String, Object>> respuestasRecuperadas = 
            (HashMap<Integer, HashMap<String, Object>>) respuestasField.get(encuesta);

        // Verificar que la respuesta está presente
        HashMap<String, Object> respuesta1 = respuestasRecuperadas.get(1);
        assertNotNull(respuesta1);
        assertEquals("user1", respuesta1.get("usuario"));
        assertEquals("Rojo", respuesta1.get("respuesta"));
    }

    @Test
    void testConstructorConValoresNulos() {
        // Usamos valores por defecto para los parámetros que no pueden ser null
        HashMap<String, String[]> states = new HashMap<>();
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();
        LocalDateTime dateLimit = LocalDateTime.now();  // Puede ser cualquier fecha válida

        // Crear la encuesta con algunos parámetros por defecto
        Encuesta encuestaNula = new Encuesta(null, 0, false, null, null, 0, dateLimit, states, preguntas, respuestas);
        
        // Verificar que la instancia se crea correctamente
        assertNotNull(encuestaNula);
        assertNull(encuestaNula.getDescripcion());  // El constructor puede permitir 'null'
        assertEquals(dateLimit, encuestaNula.getDateLimit());  // Fecha no debe ser null
        assertNotNull(encuestaNula.getPreguntas());  // La lista de preguntas debe existir aunque esté vacía
    }
}
