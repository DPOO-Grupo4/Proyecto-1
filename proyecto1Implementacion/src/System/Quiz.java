package System;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz extends Actividad {
	public ArrayList<Pregunta> preguntas;
	private int calificacionMinima;
	private HashMap<Integer, HashMap<String, Object>> respuestas;
	
	public Quiz(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, boolean started, String dateLimit, HashMap<String, String[]> states, ArrayList<Pregunta> preguntas, int calificacionMinima, HashMap<Integer, HashMap<String, Object>> respuestas) {
		super(creator, id, mandatory, descripcion, difficulty, duration, started, dateLimit, states);
		this.preguntas = preguntas;
		this.calificacionMinima = calificacionMinima;
		this.respuestas = respuestas;
		
	}
	public ArrayList<Pregunta> getPreguntas(){
		return this.preguntas;
	}
	public HashMap<Integer, HashMap<String, Object>> getRespuestas(){
		return this.respuestas;
	}
	public void setRespuestas(  HashMap<Integer, HashMap<String, Object>> newRespuestas ) {
		this.respuestas = newRespuestas;
	}
	public int getCalificacionMinima() {
		return this.calificacionMinima;
	}
	public void setPreguntas(ArrayList<Pregunta> newPreguntas){
		this.preguntas = newPreguntas;
	}
	
	
}
