package System;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PreguntaAbiertaTest {

    private PreguntaAbierta preguntaAbierta;

    @BeforeEach
    public void setUp() {
        // Configuración inicial para cada prueba
        preguntaAbierta = new PreguntaAbierta("¿Cuál es tu color favorito?");
    }

    @Test
    public void testConstructor() {
        // Verifica que la pregunta se haya inicializado correctamente
        assertEquals("¿Cuál es tu color favorito?", preguntaAbierta.getPregunta());
        assertEquals("", preguntaAbierta.getRespuestaUsuario()); // La respuesta debe estar vacía por defecto
    }

    @Test
    public void testSetAndGetPregunta() {
        // Establece una nueva pregunta y verifica si se ha actualizado correctamente
        preguntaAbierta.setPregunta("¿Qué edad tienes?");
        assertEquals("¿Qué edad tienes?", preguntaAbierta.getPregunta());
    }

    @Test
    public void testSetAndGetRespuestaUsuario() {
        // Establece una respuesta y verifica si se ha actualizado correctamente
        preguntaAbierta.setRespuestaUsuario("25");
        assertEquals("25", preguntaAbierta.getRespuestaUsuario());
    }


}
