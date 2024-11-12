package System;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Tarea extends Actividad{
	protected String comentario; //Este comentario les dira a los estudiantes a donde subir o enviar la tarea
	public Tarea(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, LocalDateTime dateLimit, HashMap<String, String[]> states, String comment) {
		super(creator, id, mandatory, descripcion, difficulty, duration, dateLimit, states);
		this.comentario = comment;
	}
	public String getComentario() {
		return this.comentario;
	}
}
