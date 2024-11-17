package System;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizTest {

    @Test
    public void testConstructorAndGetters() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor1";
        int id = 101;
        boolean mandatory = true;
        String descripcion = "Descripción del quiz";
        String difficulty = "Media";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Crear las preguntas con el nuevo constructor (String enunciado, int id)
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿Cuál es la capital de Francia?", 1));
        preguntas.add(new Pregunta("¿Cuál es el resultado de 2+2?", 2));
        
        int calificacionMinima = 60; // Calificación mínima para aprobar el quiz
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();
        
        // Crear la instancia de Quiz
        Quiz quiz = new Quiz(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            preguntas,
            calificacionMinima,
            respuestas
        );

        // Verificar los atributos heredados de la clase Actividad
        assertEquals(descripcion, quiz.getDescripcion());
        assertEquals(difficulty, quiz.getDifficulty());
        assertEquals(duration, quiz.getDuration());
        assertEquals(dateLimit, quiz.getDateLimit());

        // Verificar los atributos específicos de Quiz
        assertEquals(preguntas, quiz.getPreguntas());
        assertEquals(calificacionMinima, quiz.getCalificacionMinima());
        assertEquals(respuestas, quiz.getRespuestas());
    }

    @Test
    public void testSetPreguntasAndSetRespuestas() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor2";
        int id = 202;
        boolean mandatory = false;
        String descripcion = "Quiz de práctica";
        String difficulty = "Baja";
        int duration = 90; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 31, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Cambiar el constructor de Pregunta a dos parámetros
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿En qué año llegó Cristóbal Colón a América?", 3));
        preguntas.add(new Pregunta("¿Qué es 10+5?", 4));
        
        int calificacionMinima = 50; // Calificación mínima
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();

        // Crear la instancia de Quiz
        Quiz quiz = new Quiz(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            preguntas,
            calificacionMinima,
            respuestas
        );

        // Cambiar las preguntas y respuestas
        ArrayList<Pregunta> newPreguntas = new ArrayList<>();
        newPreguntas.add(new Pregunta("¿Cuántos continentes hay?", 5));
        newPreguntas.add(new Pregunta("¿Cuál es el idioma oficial de Brasil?", 6));
        quiz.setPreguntas(newPreguntas);
        
        HashMap<Integer, HashMap<String, Object>> newRespuestas = new HashMap<>();
        newRespuestas.put(1, new HashMap<>() {{
            put("respuesta", "B");
        }});
        quiz.setRespuestas(newRespuestas);

        // Verificar que las preguntas y respuestas se han actualizado correctamente
        assertEquals(newPreguntas, quiz.getPreguntas());
        assertEquals(newRespuestas, quiz.getRespuestas());
    }

    @Test
    public void testNullPreguntas() {
        // Configurar los datos necesarios para la prueba con preguntas nulas
        String creator = "Profesor3";
        int id = 303;
        boolean mandatory = true;
        String descripcion = "Quiz con preguntas nulas";
        String difficulty = "Media";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Preguntas nulas
        ArrayList<Pregunta> preguntas = null;
        
        int calificacionMinima = 60; // Calificación mínima para aprobar el quiz
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();

        // Crear la instancia de Quiz
        Quiz quiz = new Quiz(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            preguntas,
            calificacionMinima,
            respuestas
        );

        // Verificar que el getter de preguntas retorne null
        assertNull(quiz.getPreguntas());
    }

    @Test
    public void testEmptyRespuestas() {
        // Configurar los datos necesarios para la prueba con respuestas vacías
        String creator = "Profesor4";
        int id = 404;
        boolean mandatory = false;
        String descripcion = "Quiz sin respuestas";
        String difficulty = "Alta";
        int duration = 45; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 15, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿Cuál es la capital de España?", 7));
        preguntas.add(new Pregunta("¿Cuántos lados tiene un triángulo?", 8));
        
        int calificacionMinima = 70; // Calificación mínima
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();  // Respuestas vacías

        // Crear la instancia de Quiz
        Quiz quiz = new Quiz(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            preguntas,
            calificacionMinima,
            respuestas
        );

        // Verificar que las respuestas estén vacías
        assertTrue(quiz.getRespuestas().isEmpty());
    }

    @Test
    public void testNegativeCalificacionMinima() {
        // Configurar los datos necesarios para la prueba con calificación mínima negativa
        String creator = "Profesor5";
        int id = 505;
        boolean mandatory = true;
        String descripcion = "Quiz con calificación mínima negativa";
        String difficulty = "Baja";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Crear preguntas con el nuevo constructor
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿En qué año terminó la Segunda Guerra Mundial?", 9));
        preguntas.add(new Pregunta("¿Cuántos planetas hay en el sistema solar?", 10));
        
        int calificacionMinima = -10; // Calificación mínima negativa
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();

        // Crear la instancia de Quiz
        Quiz quiz = new Quiz(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            preguntas,
            calificacionMinima,
            respuestas
        );

        assertEquals(-10, quiz.getCalificacionMinima());
    }
}

