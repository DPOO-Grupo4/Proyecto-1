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

    public void editarActividad(Actividad actividad, String nuevoNombre, String nuevaDescripcion, int nuevaDuracion, String nuevoContenido) {
        if (actividadesCreadas.contains(actividad)) {
            actividad.editar(nuevoNombre, nuevaDescripcion, nuevaDuracion, nuevoContenido);
        } else {
            System.out.println("No tiene permiso para editar esta actividad.");
        }
    }

    public void editarLearningPath(LearningPath learningPath, String nuevoNombre, String nuevaDescripcion, String nuevoContenido, int nuevaDuracion) {
        if (learningPathsCreados.contains(learningPath)) {
            learningPath.editar(nuevoNombre, nuevaDescripcion, nuevoContenido, nuevaDuracion);
        } else {
            System.out.println("No tiene permiso para editar este Learning Path.");
        }
    }

    public Actividad copiarActividad(Actividad actividad) {
        if (actividadesCreadas.contains(actividad)) {
            Actividad copia = new Actividad(actividad); 
            actividadesCreadas.add(copia);
            return copia;
        } else {
            System.out.println("No puede copiar una actividad que no ha creado.");
            return null;
        }
    }

    public void calificarActividad(Actividad actividad, Estudiante estudiante, int calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            actividad.calificarEstudiante(estudiante, calificacion);
            actividad.cambiarEstado(estudiante);
        } else {
            System.out.println("La calificación debe estar entre 0 y 5.");
        }
    }

    
    public void calificarExamen(Examen examen, Estudiante estudiante, int calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            examen.setCalificacion(estudiante, calificacion);
            Actividad actividad = examen.getActividad(); 
            if (actividad != null) {
                actividad.cambiarEstado(estudiante);
            }

        }
    }


    public void publicarReseña(Actividad actividad, String reseña) {
        if (actividadesCreadas.contains(actividad)) {
            actividad.agregarReseña(reseña); 
        } else {
            System.out.println("No puede publicar una reseña para esta actividad.");
        }
    }

   
  
    public ArrayList<LearningPath> getLearningPathsCreados() {
        return learningPathsCreados;
    }

    public ArrayList<Actividad> getActividadesCreadas() {
        return actividadesCreadas;
    }
    
    public Actividad buscarActividadPorTitulo(String titulo) {
        for (Actividad actividad : actividadesCreadas) {
            if (actividad.getNombre().equalsIgnoreCase(titulo)) {
                return actividad;
            }
        }
        System.out.println("Actividad con el título " + titulo + " no encontrada.");
        return null;
    }
}
	

