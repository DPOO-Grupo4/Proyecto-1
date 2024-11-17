package System;

public class PreguntaAbierta {
    
    private String pregunta;
    private String respuestaUsuario;
    
    public PreguntaAbierta(String pregunta) {
        this.pregunta = pregunta;
        this.respuestaUsuario = ""; // Inicializa la respuesta del usuario como vacía
    }
    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    public String getRespuestaUsuario() {
        return respuestaUsuario;
    }
    public void setRespuestaUsuario(String respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }
    public void mostrarPreguntaYRespuesta() {
        System.out.println("Pregunta: " + pregunta);
        System.out.println("Respuesta del usuario: " + respuestaUsuario);
    }
}
