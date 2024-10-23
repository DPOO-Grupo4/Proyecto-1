package Usuarios;

import java.util.ArrayList;

import System.Actividad;
import System.LearningPath;

public class Profesor extends Usuario {
	public ArrayList<LearningPath> learningPathsCreados;
	public ArrayList<Actividad> actividadesCreadas;
	
	
	public Profesor(String login, String password, String correo, ArrayList<Actividad> actividadesCreadas, ArrayList<LearningPath> learningPathsCreados) {
		super(login, password, correo);
		this.actividadesCreadas = actividadesCreadas;
		this.learningPathsCreados = learningPathsCreados;
		
	}
	
}
