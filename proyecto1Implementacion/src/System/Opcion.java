package System;

public class Opcion {
	String explicacion;
	boolean correct ;
	String enunciado;
	int id;
	
	public Opcion(String explicacion, boolean correct, String enunciado, int id) {
		this.explicacion = explicacion;
		this.correct = correct;
		this.enunciado = enunciado;
		this.id = id;
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
	public int getID() {
		return this.id;
	}
}
