package System;

import java.util.HashMap;

public class ActividadRecurso extends Actividad {
	private String documentPath;
	public ActividadRecurso(String creator, int id, boolean mandatory, String descripcion, String difficulty, int duration, boolean started, String dateLimit, HashMap<String, String[]> states, String documentPath) {
		super(creator, id, mandatory, descripcion, difficulty, duration, started, dateLimit, states);
		this.documentPath = documentPath;
	}
	public String getDocumentPath() {
		return this.documentPath;
	}
}
