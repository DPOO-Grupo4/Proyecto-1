package System;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ActividadRecursoTest {

    @Test
    public void testConstructorAndGetters() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor1";
        int id = 101;
        boolean mandatory = true;
        String descripcion = "Descripción de la actividad";
        String difficulty = "Media";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 30, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        states.put("Estudiante1", new String[] {"Pendiente", "Comentario1"});
        String documentPath = "/resources/document.pdf";

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar los atributos heredados de la clase Actividad
        assertEquals(descripcion, actividadRecurso.getDescripcion());
        assertEquals(difficulty, actividadRecurso.getDifficulty());
        assertEquals(duration, actividadRecurso.getDuration());
        assertEquals(dateLimit, actividadRecurso.getDateLimit());

        // Verificar el atributo específico de ActividadRecurso
        assertEquals(documentPath, actividadRecurso.getDocumentPath());
    }

    @Test
    public void testGetDocumentPath() {
        // Configurar los datos necesarios para la prueba
        String creator = "Profesor2";
        int id = 202;
        boolean mandatory = false;
        String descripcion = "Otra descripción";
        String difficulty = "Alta";
        int duration = 120; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 31, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String documentPath = "/documents/manual.pdf";

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar el método getDocumentPath
        assertEquals(documentPath, actividadRecurso.getDocumentPath());
    }

    @Test
    public void testNullDocumentPath() {
        // Configurar los datos necesarios para la prueba con documentPath nulo
        String creator = "Profesor3";
        int id = 303;
        boolean mandatory = true;
        String descripcion = "Actividad con documentPath nulo";
        String difficulty = "Baja";
        int duration = 45; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 12, 15, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String documentPath = null;  // documentPath nulo

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar que el documentPath nulo se maneje correctamente
        assertNull(actividadRecurso.getDocumentPath());
    }

    @Test
    public void testEmptyDocumentPath() {
        // Configurar los datos necesarios para la prueba con documentPath vacío
        String creator = "Profesor4";
        int id = 404;
        boolean mandatory = true;
        String descripcion = "Actividad con documentPath vacío";
        String difficulty = "Alta";
        int duration = 30; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2024, 11, 25, 23, 59);
        HashMap<String, String[]> states = new HashMap<>();
        String documentPath = "";  // documentPath vacío

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar que el documentPath vacío se maneje correctamente
        assertEquals("", actividadRecurso.getDocumentPath());
    }

    @Test
    public void testExtremeDateLimit() {
        // Configurar los datos necesarios para la prueba con una fecha límite extrema
        String creator = "Profesor5";
        int id = 505;
        boolean mandatory = false;
        String descripcion = "Actividad con fecha límite extrema";
        String difficulty = "Alta";
        int duration = 150; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(9999, 12, 31, 23, 59);  // Fecha límite extrema
        HashMap<String, String[]> states = new HashMap<>();
        String documentPath = "/resources/large_document.pdf";

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar que la fecha límite extrema se maneje correctamente
        assertEquals(dateLimit, actividadRecurso.getDateLimit());
    }

    @Test
    public void testPastDateLimit() {
        // Configurar los datos necesarios para la prueba con una fecha límite pasada
        String creator = "Profesor6";
        int id = 606;
        boolean mandatory = false;
        String descripcion = "Actividad con fecha límite pasada";
        String difficulty = "Baja";
        int duration = 60; // Duración en minutos
        LocalDateTime dateLimit = LocalDateTime.of(2020, 1, 1, 0, 0);  // Fecha límite pasada
        HashMap<String, String[]> states = new HashMap<>();
        String documentPath = "/resources/past_document.pdf";

        // Crear la instancia de ActividadRecurso
        ActividadRecurso actividadRecurso = new ActividadRecurso(
            creator,
            id,
            mandatory,
            descripcion,
            difficulty,
            duration,
            dateLimit,
            states,
            documentPath
        );

        // Verificar que la fecha límite pasada se maneje correctamente
        assertEquals(dateLimit, actividadRecurso.getDateLimit());
    }
}
