package System;

public class Opcion {
	String explicacion;
	boolean correct ;
	String enunciado;
	
	public Opcion(String explicacion, boolean correct, String enunciado) {
		this.explicacion = explicacion;
		this.correct = correct;
		this.enunciado = enunciado;
	}
	public String getEnunciado() {
		return this.enunciado;
	}
	public boolean getCorrect() {
		return this.correct;
	}
	public String getExplicacion() {
		return this.explicacion;
	}
}
