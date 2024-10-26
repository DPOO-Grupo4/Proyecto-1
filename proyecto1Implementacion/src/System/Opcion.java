package System;

public class Opcion {
	private String explicacion;
	private boolean correct ;
	private String enunciado;
	private int idOpcion;
	
	public Opcion(String explicacion, boolean correct, String enunciado, int idOpcion) {
		this.explicacion = explicacion;
		this.correct = correct;
		this.enunciado = enunciado;
		this.idOpcion = idOpcion;
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
		return this.idOpcion;
	}
}
