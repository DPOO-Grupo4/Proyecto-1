package System;

import java.util.ArrayList;

public class PreguntaOpcionMultiple extends Pregunta{
	private Opcion respuestaCorrecta;
	private ArrayList<Opcion> opciones;
	
	public PreguntaOpcionMultiple(Opcion respuestaCorrecta, String enunciado,int idPregunta,  ArrayList<Opcion> opciones) {
		super(enunciado,idPregunta);
		this.respuestaCorrecta = respuestaCorrecta;
		this.opciones = opciones;
	}
	public ArrayList<Opcion> getOpciones(){
		return this.opciones;
	}
}
