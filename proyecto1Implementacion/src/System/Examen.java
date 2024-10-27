package System;

import java.util.ArrayList;
import java.util.HashMap;

import Usuarios.Estudiante;

public class Examen extends Actividad {
	ArrayList<Pregunta> preguntas;
	HashMap<Integer, HashMap<String, Object>> respuestas;
	public Examen(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, boolean started, String dateLimit, HashMap<String, String[]> states, ArrayList<Pregunta> preguntas, HashMap<Integer, HashMap<String, Object>> respuestas) {
		super(creator, id, mandatory, descripcion, difficulty, duration, started, dateLimit, states);
		this.preguntas = preguntas;
		this.respuestas = respuestas;
	}
public ArrayList<Pregunta> getPreguntas(){
	return this.preguntas;
}
public void setCalificacion(Estudiante estudiante, int calificacion) {
    if (calificacion < 0 || calificacion > 5) {
        System.out.println("Error: La calificación debe estar entre 0 y 5.");
        return;
    }

}}
