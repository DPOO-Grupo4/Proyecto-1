package System;

import java.util.ArrayList;
import java.util.HashMap;

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

}
