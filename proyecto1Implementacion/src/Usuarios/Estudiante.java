package Usuarios;

import java.util.ArrayList;

import System.LearningPath;

public class Estudiante extends Usuario{
	
	ArrayList<LearningPath> learningPathsInscritos;
	
	
	public Estudiante(String login, String password,String correo, ArrayList<LearningPath> lpsInscritos) {
		super(login, password, correo);
		this.learningPathsInscritos = lpsInscritos;
	
	}
	public ArrayList<LearningPath> getLPsInscritos(){
		return this.learningPathsInscritos;
	}
	public void setLPsInscritos(ArrayList<LearningPath> newLPsInscritos) {
		this.learningPathsInscritos = newLPsInscritos;
	}
}
