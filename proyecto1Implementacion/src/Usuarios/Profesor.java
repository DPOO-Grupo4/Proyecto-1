package Usuarios;

import java.util.ArrayList;

import System.Actividad;
import System.LearningPath;

public class Profesor extends Usuario {
	public ArrayList<LearningPath> learningPathsCreados;
	public ArrayList<Actividad> actividadesCreadas;
	
	
	public Profesor(String login, String password, String correo, ArrayList<Actividad> actividadesCreadas, ArrayList<LearningPath> learningPathsCreados) {
        super(login, password, correo);
        this.actividadesCreadas = (actividadesCreadas != null) ? actividadesCreadas : new ArrayList<>();
        this.learningPathsCreados = (learningPathsCreados != null) ? learningPathsCreados : new ArrayList<>();
    }

    
    public void añadirLearningPath(LearningPath learningPath) {
        this.learningPathsCreados.add(learningPath);
    }

    public void crearActividad(Actividad actividad) {
        actividadesCreadas.add(actividad);
    }
    
    public ArrayList<LearningPath> getLPs(){
    	return this.learningPathsCreados;
    }

  
  
    public ArrayList<LearningPath> getLearningPathsCreados() {
        return learningPathsCreados;
    }

    public ArrayList<Actividad> getActividadesCreadas() {
        return actividadesCreadas;
    }
    public void setActividadesCreadas(ArrayList<Actividad> actividades) {
    	this.actividadesCreadas = actividades;
    }
    public void setLPsCreados(ArrayList<LearningPath> LPs) {
    	this.learningPathsCreados =LPs;
    }
    
    
}
	

