package System;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Encuesta extends Actividad {
	ArrayList<Pregunta> preguntas;
	HashMap<Integer, HashMap<String, Object>> respuestas;
	public Encuesta(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, LocalDateTime dateLimit, HashMap<String, String[]> states, ArrayList<Pregunta> preguntas, HashMap<Integer, HashMap<String, Object>> respuestas) {
		super(creator, id, mandatory, descripcion, difficulty, duration, dateLimit, states);
		this.preguntas = preguntas;
		this.respuestas = respuestas;
	}
	public ArrayList<Pregunta> getPreguntas(){
		return this.preguntas;
		
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
}
