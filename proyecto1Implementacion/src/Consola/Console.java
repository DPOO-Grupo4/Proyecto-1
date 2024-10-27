package Consola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import System.Actividad;
import System.Examen;
import System.LearningPath;
import System.Opcion;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Quiz;
import System.Sistema;
import Usuarios.Estudiante;
import Usuarios.Profesor;

public class Console {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;
		Sistema sistema = new Sistema();
		sistema.cargarSistema();
		Scanner scanner = new Scanner(System.in);
		while (run) {
			run = menu(sistema, scanner);
			
		}
		scanner.close();
	}
	public static boolean menu(Sistema sistema, Scanner scanner) {
		try {
			
			int option = menuInicio(scanner);
			if (option==3) {
				return false;
			}else if (option == 2){
				menuCreacionUsuario(sistema, scanner);
				
				return true;
			}else {
				menuInicioSesion(sistema, scanner);
				return true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\n"+e.getMessage()+"\n");
			return true;
		}
	}
	public static int menuInicio(Scanner scanner) throws IOException {
		
		System.out.println("_____________________________________");
		System.out.println("BIENVENIDO:");
		System.out.println("1.) Iniciar sesión");
		System.out.println("2.) ¿Eres nuevo? ¡Registrate!");
		System.out.println("3.) Salir");
		System.out.println("_____________________________________");
		System.out.println("Digite la opcion que desea escoger:");
		int respuesta = scanner.nextInt();
		if (respuesta!= 1 & respuesta != 2 & respuesta != 3) {
			throw new IOException("Digito mal la opción");
		}
		
		return respuesta;
		
	}
	public static void menuCreacionUsuario(Sistema sistema, Scanner scanner) {
		System.out.println("\nPor favor digite los siguientes datos necesarios para la formalización de su perfil de usuario\n");
		
		System.out.println("\n Nombre de usuario: \n");
		String login = scanner.next();
		System.out.println("\n Contraseña: \n");
		String password = scanner.next();
		System.out.println("\n Correo: \n");
		String correo = scanner.next();
		System.out.println("\n ¿Usted es un estudiante o un profesor?");
		String tipo = scanner.next();
		
		try {
			sistema.crearUsuario(login, password, correo, tipo, true );
			System.out.println("SU USUARIO FUE CREADO EXITOSAMENTE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	public static void menuInicioSesion(Sistema sistema, Scanner scanner) {
		
		System.out.println("\n Por favor digite los siguientes datos para iniciar su sesión \n");
		System.out.println("\n Nombre de usuario: \n");
		String login = scanner.next();
		System.out.println("\n Contraseña: \n");
		String password = scanner.next();
		try {
			String message = sistema.iniciarSesion(login, password);
			System.out.println(message);
			menuAplicacion(sistema, scanner);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void menuAplicacion(Sistema sistema, Scanner scanner) throws SQLException {
		if (sistema.getSession().getClass().getSimpleName().equals("Estudiante")) {
			boolean runSession = true;
			while (runSession) {
				runSession = menuAplicacionEstudiante(sistema, scanner);
			}
		}else {
			menuAplicacionProfesor(sistema,scanner); 
		}
	
	}
	public static boolean menuAplicacionEstudiante(Sistema sistema, Scanner scanner) throws SQLException {
		System.out.println("\n[1] Consultar mis learning paths inscritos \n");
		System.out.println("[2] Cerrar sesión\n");
		System.out.println("¿Qué desea hacer?");
		int opcion = scanner.nextInt();
		while (opcion != 1 && opcion != 2) {
			System.out.println("Digito una opcion que no existe");
			opcion = scanner.nextInt();
		}
		if (opcion == 1) {
			boolean runMenuLPsInscritos = true;
			while (runMenuLPsInscritos) {
				runMenuLPsInscritos = menuLearningPathsInscritos(sistema, scanner);
			}
		}
		if (opcion == 2) {
			return false;
		}else {
			return true;
		}
	}
	public static boolean menuLearningPathsInscritos(Sistema sistema, Scanner scanner) throws SQLException {
		ArrayList<LearningPath> LPsInscritos = sistema.getLPsInscritos(sistema.getSession().getLogin());
		
		for (int i=0; i < LPsInscritos.size(); i++) {
			System.out.println("["+String.valueOf(i)+"]" + LPsInscritos.get(i).getTitulo());
		}
		System.out.println("["+LPsInscritos.size() + "] Salir");
		System.out.println("Cual learning path desea consultar: ");
		int opcion1 = scanner.nextInt();
		while (opcion1>LPsInscritos.size() & opcion1<0) {
			System.out.println("Por favor digite una opcion valida");
			opcion1 = scanner.nextInt();
		}
		if (opcion1 == LPsInscritos.size()) {
			return false;
		}else {
			boolean runLearningPath = true;
			while (runLearningPath) {
				runLearningPath = mostrarLearningPath(sistema, scanner, LPsInscritos ,opcion1);
			}
			return true;
		}
	}
	public static boolean mostrarLearningPath(Sistema sistema, Scanner scanner, ArrayList<LearningPath> LPsInscritos, int opcion) throws SQLException {
		LearningPath LPEscogido = LPsInscritos.get(opcion);
		System.out.println(LPEscogido.getTitulo());
		System.out.println(LPEscogido.getDescripcion());
		System.out.println("DIFICULTAD: " +LPEscogido.getDifficulty());
		System.out.println("DURACION: "+ String.valueOf(LPEscogido.getDuration()));
		System.out.println("ACTIVIDADES:");
		ArrayList<Actividad> actividades = LPEscogido.getActivities();
		for (int i = 0; i < actividades.size();i++) {
			System.out.println("["+String.valueOf(i)+"] "+actividades.get(i).getID());
		}
		System.out.println("["+String.valueOf(actividades.size())+"] Salir");
		System.out.println("Opcion: ");
		int opcion2= scanner.nextInt();
		if (opcion2 == actividades.size()) {
			return false;
		}else {
			boolean runActividad  = true;
			while (runActividad) {
				runActividad = mostrarActividad(sistema, scanner, actividades, opcion2);
			}
			return true;
		}
		
		
	}
	
	public static boolean mostrarActividad(Sistema sistema, Scanner scanner, ArrayList<Actividad> actividades, int opcion) throws SQLException {
		Actividad actividadEscogida = actividades.get(opcion);
		System.out.println("Actividad : "+ String.valueOf(actividadEscogida.getID()));
		System.out.println(actividadEscogida.getDescripcion());
		System.out.println(actividadEscogida.getDifficulty());
		System.out.println(String.valueOf(actividadEscogida.getDuration()));
		System.out.println(actividadEscogida.getDateLimit());
		HashMap<String, String[]>states = actividadEscogida.getState();
		boolean yaAprobada = false;
		if (states.containsKey(sistema.getSession().getLogin())) {
			if (states.get(sistema.getSession().getLogin())[2].equals("false")) {
				System.out.println("[0] Iniciar actividad ");
			}else {
				yaAprobada = true;
			}
		}else {
			System.out.println("[0] Iniciar actividad ");
		}
		System.out.println("[1] salir");
		int opcion3 = scanner.nextInt();
		while (opcion3!=0 & opcion3!=1) {
			opcion3 = scanner.nextInt();
		}
		
		if (actividadEscogida.getClass().getSimpleName().equals("Quiz") & opcion3==0 & !yaAprobada) {
			
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			Quiz actividad = (Quiz) actividadEscogida;
			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
			int respuestasBuenas = 0;
			for (int i =1 ; i <= preguntas.size(); i++) {
				System.out.println("Pregunta "+String.valueOf(i)+":");
				PreguntaOpcionMultiple pregunta= (PreguntaOpcionMultiple) preguntas.get(i-1);
				ArrayList<Opcion> opciones = pregunta.getOpciones();
				for (int j = 1; j<=opciones.size(); j++) {
					System.out.println("Opcion " + String.valueOf(j));
					Opcion opcioni = opciones.get(j-1);
					System.out.println(opcioni.getEnunciado());
				}
				System.out.println("Seleccione la opcion que considere correcta :");
				int respuestaUsuario = scanner.nextInt() -1;
				if (opciones.get(respuestaUsuario).getCorrect()) {
					respuestasBuenas+=1;
				}
				
			}
			if (respuestasBuenas >= actividad.getCalificacionMinima()) {
				System.out.println("Ha aprobado la actividad");
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida,state.get(sistema.getSession().getLogin())[0],LocalDate.now().toString(), true, true );
				
			}
			return true;
		}if (actividadEscogida.getClass().getSimpleName().equals("Examen") & opcion3==0 & !yaAprobada) {
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			Examen actividad = (Examen) actividadEscogida;
			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
		}
		else {
			return false;
		}
 		
		
	}
	
	public static boolean menuAplicacionProfesor(Sistema sistema, Scanner scanner) throws SQLException {
	    Profesor profesor = (Profesor) sistema.getSession();

	    while (true) {
	        System.out.println("\n--- Menú del Profesor ---");
	        System.out.println("[1] Crear Learning Path");
	        System.out.println("[2] Editar Learning Path");
	        System.out.println("[3] Calificar Actividad");
	        System.out.println("[4] Calificar Examen");
	        System.out.println("[5] Publicar reseña");
	        System.out.println("[6] Ver Actividades Creadas");
	        System.out.println("[7] Ver Learning Paths Creados");
	        System.out.println("[8] Cerrar sesión");
	        System.out.print("¿Qué desea hacer? ");

	        int opcion = scanner.nextInt();
	        //scanner.nextLine(); 

	        switch (opcion) {
		        case 1:
	                
	                System.out.print("Ingrese el título del Learning Path: ");
	                String tituloLP = scanner.next();
	
	                System.out.print("Ingrese la descripción general: ");
	                String descripcionGeneral = scanner.next();
	
	                System.out.print("Ingrese la dificultad (Basico, Intermedio, Avanzado): ");
	                String dificultad = scanner.next();
	
	                System.out.print("Ingrese la duración en minutos (Valores Enteros): ");
	                int duracion = scanner.nextInt();
	
	                //System.out.print("Ingrese la calificación inicial de 0 a 5 (Valores Enteros): ");
	                //int calificacion = scanner.nextInt();
	                //scanner.nextLine(); 
	
	                String fechaCreacion = LocalDate.now().toString(); 
	                LearningPath nuevoLP = sistema.crearLearningPath(profesor.getLogin(), tituloLP, descripcionGeneral, dificultad, duracion, 0, fechaCreacion, fechaCreacion, true);
	                profesor.añadirLearningPath(nuevoLP);
	                
	                System.out.println("Learning Path creado con éxito.");
	                break;
	                
		        case 2:
		        	System.out.println("__________________________________________");
		        	System.out.println("Que Learning Path desea editar ... ");
		        	ArrayList<LearningPath> LPs =  ((Profesor) sistema.getSession()).getLPs();
		        	for (int i = 1 ; i <= LPs.size(); i++) {
		        		System.out.println("["+String.valueOf(i)+"]"+LPs.get(i-1).getTitulo());
		        	}
		        	System.out.println("["+String.valueOf(LPs.size()+1)+"] Volver");
		        	System.out.println("__________________________________________");
		        	int opcioni = scanner.nextInt();
		        	while (opcioni != LPs.size()+1) {
		        		opcioni = scanner.nextInt();
		        		if (opcioni >=1 & opcioni<=LPs.size()) {
			        		menuEdicion(sistema, scanner, LPs.get(opcioni));
			        	}
		        	}
		        	
		            break;
		            
		        case 3:
		            
		            System.out.print("Ingrese el título de la Actividad que desea calificar: ");
		            String tituloActividadCalificar = scanner.nextLine();
		            
		            
		            Actividad actividadACalificar = profesor.buscarActividadPorTitulo(tituloActividadCalificar);
		            if (actividadACalificar != null) {
		                System.out.print("Ingrese el login del estudiante: ");
		                String loginEstudiante = scanner.nextLine();
		                
		                Estudiante estudiante = sistema.buscarEstudiantePorLogin(loginEstudiante);
		                if (estudiante != null) {
		                    System.out.print("Ingrese la calificación (0 a 100): ");
		                    int calificacionActividad = scanner.nextInt();
		                    scanner.nextLine(); 
		                    
		                    profesor.calificarActividad(actividadACalificar, estudiante, calificacionActividad);
		                    System.out.println("Actividad calificada con éxito.");
		                } else {
		                    System.out.println("Estudiante no encontrado.");
		                }
		            } else {
		                System.out.println("Actividad no encontrada.");
		            }
		            break;
		            
		        case 4:
		            
		            System.out.print("Ingrese el ID del examen que desea calificar: ");
		            int idExamen = scanner.nextInt();
		            scanner.nextLine(); 

		            Examen examen = sistema.getExamenById(idExamen); 
		            if (examen == null) {
		                System.out.println("Examen no encontrado.");
		                break;
		            }

		            System.out.print("Ingrese el login del estudiante: ");
		            String loginEstudiante = scanner.nextLine(); 

		            Estudiante estudiante = sistema.buscarEstudiantePorLogin(loginEstudiante); 
		            if (estudiante == null) {
		                System.out.println("Estudiante no encontrado.");
		                break;
		            }

		            
		            System.out.print("Ingrese la calificación (0-5): ");
		            int calificacionInput = scanner.nextInt(); 
		            scanner.nextLine(); 

		            if (calificacionInput < 0 || calificacionInput > 5) {
		                System.out.println("Calificación inválida. Debe estar entre 0 y 5.");
		            } else {
		                
		                examen.setCalificacion(estudiante, calificacionInput);
		                System.out.println("Calificación registrada correctamente.");
		            }
		            break;



		        case 5:
		            
		            System.out.print("Ingrese el título de la Actividad para la reseña: ");
		            String tituloActividadReseña = scanner.nextLine();
		            
		            Actividad actividadAReseñar = profesor.buscarActividadPorTitulo(tituloActividadReseña);
		            if (actividadAReseñar != null) {
		                System.out.print("Escriba la reseña para la actividad: ");
		                String reseña = scanner.nextLine();
		                
		                profesor.publicarReseña(actividadAReseñar, reseña);
		                System.out.println("Reseña publicada con éxito.");
		            } else {
		                System.out.println("Actividad no encontrada.");
		            }
		            break;

		            
		        case 6:
	                
	                System.out.println("--- Actividades Creadas ---");
	                for (Actividad actividad : profesor.getActividadesCreadas()) {
	                    //System.out.println("Título: " + actividad.getNombre());
	                    System.out.println("Descripción: " + actividad.getDescripcion());
	                    System.out.println("Dificultad: " + actividad.getDifficulty());
	                    System.out.println("Duración: " + actividad.getDuration() + " minutos");
	                    System.out.println("Fecha Límite: " + actividad.getDateLimit());
	                    System.out.println("----------------------------");
	                }
	                break;

	            case 7:
	                
	                System.out.println("--- Learning Paths Creados ---");
	                for (LearningPath lp : profesor.getLearningPathsCreados()) {
	                    System.out.println("Título: " + lp.getTitulo());
	                    System.out.println("Descripción: " + lp.getDescripcion());
	                    System.out.println("Dificultad: " + lp.getDifficulty());
	                    System.out.println("Duración: " + lp.getDuration() + " horas");
	                    System.out.println("Calificación: " + lp.getActivities());
	                    System.out.println("----------------------------");
	                }
	                break;
	                
	            case 8:
	                
	                System.out.println("Cerrando sesión...");
	                return false;                 
	                
		           
	            default:
	                System.out.println("Opción no válida. Intente de nuevo.");
	        }
	    }
	}
	public static boolean menuEdicion(Sistema sistema, Scanner scanner, LearningPath LP) {
		System.out.println("___________________________________");
		System.out.println("Bienvenido al menu de edicion ");
		System.out.println("Ahora mismo esta editando el Learning Path : "+ LP.getTitulo());
		System.out.println("[0] Añadir actividad");
		System.out.println("[1] Remover alguna actividad");
		System.out.println("[2] Salir");
		int opcion = scanner.nextInt();
		while (opcion != 0 & opcion != 1 & opcion != 2) {
			opcion = scanner.nextInt();
		}
		
		if (opcion == 2) {
			return false;
		}else if (opcion == 0) {
			boolean runMenuCA = true;
			while (runMenuCA) {
				runMenuCA = menuCreacionActividad(sistema, scanner, LP);
			}
		}else {
			
		}
	}
	public static boolean menuCreacionActividad(Sistema sistema, Scanner scanner, LearningPath LP) throws SQLException {
		System.out.println("______________________________________");
		System.out.println("A continuación se le va a pedir que digite una información necesaria para la creacion de la actividad");
		System.out.println("DESCRIPCION : ");
		String descripcion = scanner.next();
		System.out.println("DIFICULTAD : ");
		String dificultad = scanner.next();
		System.out.println("OBLIGATORIA (true, false) : ");
		boolean mandatory = scanner.nextBoolean();
		System.out.println("DURACION (en minutos) : ");
		int duration = scanner.nextInt();
		System.out.println("FECHA LÍMITE : ");
		String dateLimit = scanner.next();
		String documentPath = "";
		System.out.println("TIPO (Quiz, Examen, Encuesta, Tarea, actividadRecurso");
		String tipo = scanner.next();
		
		Actividad newActividad = sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentpath, 0, new HashMap<String, String[]>(), true);
		
		System.out.println("______________________________________");
	}
}




