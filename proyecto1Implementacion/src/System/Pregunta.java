package System;

public class Pregunta {
	protected String enunciado;
	protected int idPregunta;
	public Pregunta(String enunciado, int id) {
		this.enunciado = enunciado;
		this.idPregunta = id;
	}
	public String getEnunciado() {
		return this.enunciado;
	}
	public int getID() {
		return this.idPregunta;
	}
}
