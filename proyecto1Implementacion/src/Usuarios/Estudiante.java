package Usuarios;

import java.util.ArrayList;

import System.LearningPath;

public class Estudiante extends Usuario{
	
	ArrayList<LearningPath> learningPathsInscritos;
	
	
	public Estudiante(String login, String password,String correo, ArrayList<LearningPath> lpsInscritos) {
		super(login, password, correo);
		this.learningPathsInscritos = lpsInscritos;
		
	}
	
}
