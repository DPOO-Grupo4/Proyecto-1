package System;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TareaTest {

    @Test
    public void testConstructorAndGetters() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor1";
        int id = 101;
        boolean mandatory = true;
        String descripcion = "Descripción de la tarea";
        String difficulty = "Media";
        int duration = 90; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 10, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        states.put("Estudiante1", new String[] {"Pendiente", "Comentario1"});
        String comentario = "Subir tarea al sistema de gestión";

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar los atributos heredados de la clase Actividad
        assertEquals(descripcion, tarea.getDescripcion());
        assertEquals(difficulty, tarea.getDifficulty());
        assertEquals(duration, tarea.getDuration());
        assertEquals(dateLimit, tarea.getDateLimit());

        // Verificar el atributo específico de Tarea
        assertEquals(comentario, tarea.getComentario());
    }

    @Test
    public void testGetComentario() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor2";
        int id = 202;
        boolean mandatory = false;
        String descripcion = "Otra tarea";
        String difficulty = "Alta";
        int duration = 120; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 15, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String comentario = "Enviar tarea por correo electrónico";

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar el método getComentario
        assertEquals(comentario, tarea.getComentario());
    }

    @Test
    public void testNullComentario() {
        // Configurar los datos necesarios para la prueba con comentario nulo
        String creator = "Profesor3";
        int id = 303;
        boolean mandatory = true;
        String descripcion = "Tarea con comentario nulo";
        String difficulty = "Baja";
        int duration = 30; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 31, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String comentario = null;  // Comentario nulo

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar que el comentario nulo se maneje correctamente
        assertNull(tarea.getComentario());
    }

    @Test
    public void testEmptyComentario() {
        // Configurar los datos necesarios para la prueba con comentario vacío
        String creator = "Profesor4";
        int id = 404;
        boolean mandatory = true;
        String descripcion = "Tarea con comentario vacío";
        String difficulty = "Media";
        int duration = 45; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 25, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String comentario = "";  // Comentario vacío

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar que el comentario vacío se maneje correctamente
        assertEquals("", tarea.getComentario());
    }

    @Test
    public void testExtremeDateLimit() {
        // Configurar los datos necesarios para la prueba con una fecha límite extrema
        String creator = "Profesor5";
        int id = 505;
        boolean mandatory = false;
        String descripcion = "Tarea con fecha límite extrema";
        String difficulty = "Alta";
        int duration = 150; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(9999, 12, 31, 23, 59);  // Fecha límite extrema
        HashMap<String, String[]> states = new HashMap<>();
        String comentario = "Enviar tarea a través de correo electrónico";

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar que la fecha límite se maneje correctamente
        assertEquals(dateLimit, tarea.getDateLimit());
    }

    @Test
    public void testPastDateLimit() {
        // Configurar los datos necesarios para la prueba con una fecha límite pasada
        String creator = "Profesor6";
        int id = 606;
        boolean mandatory = false;
        String descripcion = "Tarea con fecha límite pasada";
        String difficulty = "Baja";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2020, 1, 1, 0, 0);  // Fecha límite pasada
        HashMap<String, String[]> states = new HashMap<>();
        String comentario = "Tarea entregada tarde";

        // Crear la instancia de Tarea
        Tarea tarea = new Tarea(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            comentario
        );

        // Verificar que la fecha límite pasada se maneje correctamente
        assertEquals(dateLimit, tarea.getDateLimit());
    }
}
