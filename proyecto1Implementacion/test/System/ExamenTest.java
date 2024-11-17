package System;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import Usuarios.Estudiante;
import System.LearningPath;

public class ExamenTest {

    @Test
    public void testConstructorAndGetters() {
        // Datos de prueba
        String creator = "Profesor1";
        int id = 1;
        boolean mandatory = true;
        String descripcion = "Examen de prueba";
        String difficulty = "Media";
        int duration = 60;  // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Crear preguntas de prueba
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("Pregunta 1", 1));
        preguntas.add(new Pregunta("Pregunta 2", 2));

        // Crear respuestas vacías para este test
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();
        
        // Crear un objeto Examen
        Examen examen = new Examen(creator, id, mandatory, descripcion, difficulty, duration, dateLimit, states, preguntas, respuestas);
        
        // Verificar que los datos del examen estén correctos
        assertEquals(descripcion, examen.getDescripcion());
        assertEquals(difficulty, examen.getDifficulty());
        assertEquals(duration, examen.getDuration());
        assertEquals(dateLimit, examen.getDateLimit());
        assertEquals(preguntas, examen.getPreguntas());
    }

    @Test
    public void testSetCalificacionInvalida() {
        // Datos de prueba para el examen
        String creator = "Profesor1";
        int id = 1;
        boolean mandatory = true;
        String descripcion = "Examen de prueba";
        String difficulty = "Media";
        int duration = 60;  // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Crear una lista de preguntas
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("Pregunta 1", 1));
        
        // Crear respuestas vacías
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();
        
        // Crear un Examen
        Examen examen = new Examen(creator, id, mandatory, descripcion, difficulty, duration, dateLimit, states, preguntas, respuestas);
        
        // Crear un Estudiante con los 4 valores necesarios
        ArrayList<LearningPath> learningPathsInscritos = new ArrayList<>();  // Lista vacía de LearningPaths
        Estudiante estudiante = new Estudiante("estudiante1", "password", "correo@dominio.com", learningPathsInscritos);
        
        // Probar la calificación inválida (fuera de rango)
        examen.setCalificacion(estudiante, -1); // La calificación es inválida (-1)
        examen.setCalificacion(estudiante, 6);  // La calificación es inválida (mayor a 5)
    }

    @Test
    public void testSetPreguntas() {
        // Datos de prueba para el examen
        String creator = "Profesor1";
        int id = 1;
        boolean mandatory = true;
        String descripcion = "Examen de prueba";
        String difficulty = "Media";
        int duration = 60;  // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        
        // Crear una lista inicial de preguntas
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("Pregunta 1", 1));
        
        // Crear respuestas vacías
        HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();
        
        // Crear un Examen
        Examen examen = new Examen(creator, id, mandatory, descripcion, difficulty, duration, dateLimit, states, preguntas, respuestas);
        
        // Cambiar las preguntas
        ArrayList<Pregunta> nuevasPreguntas = new ArrayList<>();
        nuevasPreguntas.add(new Pregunta("Pregunta 2", 2));
        examen.setPreguntas(nuevasPreguntas);
        
        // Verificar que las preguntas fueron actualizadas correctamente
        assertEquals(nuevasPreguntas, examen.getPreguntas());
    }

}

