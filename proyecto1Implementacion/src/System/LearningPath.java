package System;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import Usuarios.Usuario;

public class LearningPath {
	private String creator;
	private String titulo;
	private String descripcionGeneral;
	public String difficulty;
	private int duration;
	public int rating;
	protected LocalDateTime fechaCreacion;
	protected LocalDateTime fechaModificacion;
	public ArrayList<Actividad> activities;
	public ArrayList<Usuario> estudiantesEnlistados;
	//public ArrayList<String> reseñas;
	public LearningPath(String creator, String titulo, String descripcionGeneral, String difficulty, int duration, int rating, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, Sistema sistema) throws SQLException {
		this.creator = creator;
		this.titulo = titulo;
		this.descripcionGeneral = descripcionGeneral;
		this.difficulty = difficulty;
		this.duration = duration;
		this.rating = rating;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.activities = sistema.cargarActividadesLP(titulo);
		this.estudiantesEnlistados = sistema.cargarEstudiantesEnlistados(titulo);
	}
	public String getTitulo() {
		return this.titulo;
	}
	public String getDescripcion() {
		return this.descripcionGeneral;
	}
	public int getDuration() {
		return this.duration;
	}
	public String getDifficulty() {
		return this.difficulty;
	}
	public ArrayList<Actividad> getActivities(){
		return this.activities;
	}
	public ArrayList<Usuario> getEstudiantesEnlistados(){
		return this.estudiantesEnlistados;
	}
	public void setActivities(ArrayList<Actividad> newActivities) {
		this.activities = newActivities;
	}
}
