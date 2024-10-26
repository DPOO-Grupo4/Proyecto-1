package Consola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import System.Actividad;
import System.ActividadRecurso;
import System.Examen;
import System.LearningPath;
import System.Opcion;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Quiz;
import System.Sistema;
import System.Tarea;
import Usuarios.Estudiante;

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
			menuAplicacionProfesor(sistema);
		}
	
	}
	public static boolean menuAplicacionEstudiante(Sistema sistema, Scanner scanner) throws SQLException {
		System.out.println("\n[1] Consultar mis learning paths inscritos \n");
		System.out.println("\n[2] Inscribir un learning path\n");
		System.out.println("[3] Cerrar sesión\n");
		System.out.println("¿Qué desea hacer?");
		int opcion = scanner.nextInt();
		while (opcion != 1 && opcion != 2 && opcion != 3) {
			System.out.println("Digito una opcion que no existe");
			opcion = scanner.nextInt();
		}
		if (opcion == 1) {
			boolean runMenuLPsInscritos = true;
			while (runMenuLPsInscritos) {
				runMenuLPsInscritos = menuLearningPathsInscritos(sistema, scanner);
			}
		}else if (opcion == 3) {
			return false;
		}else if (opcion == 2){
			ArrayList<LearningPath> LPsInscritos = sistema.getLPsInscritos(sistema.getSession().getLogin());
			HashMap<String, LearningPath> learningPaths = sistema.getLPs();
			ArrayList<String> titulosLPs = new ArrayList<>();
			for (int i = 0; i < learningPaths.size(); i ++) {
				System.out.println("["+String.valueOf(i)+"] " + (new ArrayList<LearningPath> (learningPaths.values())).get(i).getTitulo());
				titulosLPs.add( (new ArrayList<LearningPath> (learningPaths.values())).get(i).getTitulo());
			}
			System.out.println("["+learningPaths.size()+"] Volver");
			System.out.println("De la lista de Learning Paths en el sistema ¿ Cúal desea inscribir ?");
			int opcioni = scanner.nextInt();
			sistema.inscribirLP(learningPaths.get(titulosLPs.get(opcioni)),(Estudiante) sistema.getSession());
			return true;
		}
		else {
			return true;
		}
		return true;
	}
	public static boolean menuLearningPathsInscritos(Sistema sistema, Scanner scanner) throws SQLException {
		ArrayList<LearningPath> LPsInscritos = sistema.getLPsInscritos(sistema.getSession().getLogin());
		System.out.println("__________________________________________");
		System.out.println("MIS LEARNING PATHS INSCRITOS ");
		for (int i=0; i < LPsInscritos.size(); i++) {
			System.out.println("["+String.valueOf(i)+"]" + LPsInscritos.get(i).getTitulo());
		}
		System.out.println("["+LPsInscritos.size() + "] Salir");
		System.out.println("__________________________________________");
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
		System.out.println("__________________________________________");
		System.out.println("LEARNING PATH : "+LPEscogido.getTitulo());
		System.out.println("DESCRIPCION : "+LPEscogido.getDescripcion());
		System.out.println("DIFICULTAD: " +LPEscogido.getDifficulty());
		System.out.println("DURACION: "+ String.valueOf(LPEscogido.getDuration())+" minutos");
		System.out.println("__________________________________________");
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
		System.out.println("__________________________________________");
		System.out.println("Actividad : "+ String.valueOf(actividadEscogida.getID()));
		System.out.println("DESCRIPCION : " +actividadEscogida.getDescripcion());
		System.out.println("DIFICULTAD : " +actividadEscogida.getDifficulty());
		System.out.println("DURACION : "+String.valueOf(actividadEscogida.getDuration())+  " minutos");
		System.out.println("fecha limite : " + actividadEscogida.getDateLimit());
		System.out.println("__________________________________________");
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
				sistema.actualizarRespuestaUsuario(sistema.getSession(),pregunta.getID(), String.valueOf(opciones.get(respuestaUsuario).getID()), null, false );
				if (opciones.get(respuestaUsuario).getCorrect()) {
					respuestasBuenas+=1;
				}
				
			}
			if (respuestasBuenas >= actividad.getCalificacionMinima()) {
				System.out.println("Ha aprobado la actividad");
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida,state.get(sistema.getSession().getLogin())[0],LocalDate.now().toString(), true, true );
				
			}
			return true;
		}else if (actividadEscogida.getClass().getSimpleName().equals("Examen") & opcion3==0 & !yaAprobada) {
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			Examen actividad = (Examen) actividadEscogida;
			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
			for (int i =1 ; i <= preguntas.size(); i++) {
				System.out.println("Pregunta "+String.valueOf(i)+":");
				Pregunta pregunta= preguntas.get(i-1);
				System.out.println("Pregunta "+String.valueOf(i));
				System.out.println(pregunta.getEnunciado());
				System.out.println("A continuación escriba su respuesta");
				String respuestaUsuario = scanner.next();
				sistema.actualizarRespuestaUsuario(sistema.getSession(), pregunta.getID(), "NULL", respuestaUsuario, false);
				System.out.println("Su respuesta fue enviada con exito");
			}
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, state.get(sistema.getSession().getLogin())[0], LocalDate.now().toString(), false, false);
			return true;
		}else if (actividadEscogida.getClass().getSimpleName().equals("Encuesta") & opcion3==0 & !yaAprobada) {
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			Examen actividad = (Examen) actividadEscogida;
			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
			for (int i =1 ; i <= preguntas.size(); i++) {
				System.out.println("Pregunta "+String.valueOf(i)+":");
				Pregunta pregunta= preguntas.get(i-1);
				System.out.println("Pregunta "+String.valueOf(i));
				System.out.println(pregunta.getEnunciado());
				System.out.println("A continuación escriba su respuesta");
				String respuestaUsuario = scanner.next();
				sistema.actualizarRespuestaUsuario(sistema.getSession(), pregunta.getID(), "NULL", respuestaUsuario, false);
				
			}
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, state.get(sistema.getSession().getLogin())[0], LocalDate.now().toString(), true, false);
			return true;
		}else if (actividadEscogida.getClass().getSimpleName().equals("ActividadRecurso") & opcion3==0 & !yaAprobada) {
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
			HashMap<String, String[]> state = actividadEscogida.getState();
			ActividadRecurso actividadRecurso = (ActividadRecurso) actividadEscogida;
			System.out.println("HIPERVINCULO : "+ actividadRecurso.getDocumentPath());
			System.out.println("[0] volver");
			int opcioni = scanner.nextInt();
			while (opcioni != 0) {
				opcioni = scanner.nextInt();
			}if (opcioni == 0) {
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, state.get(sistema.getSession().getLogin())[0],LocalDate.now().toString() ,false, false);
				return false;
			}
			return true;
		}else if (actividadEscogida.getClass().getSimpleName().equals("Tarea") & opcion3==0 & !yaAprobada) {
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
			HashMap<String, String[]> state = actividadEscogida.getState();
			Tarea tarea = (Tarea) actividadEscogida;
			System.out.println("Comentario : " + tarea.getComentario()); //Se supone que el comentario les dice donde enviar la tarea que hicieron
			System.out.println("[0] completar ");
			int opcioni = scanner.nextInt();
			while (opcioni != 0) {
				opcioni = scanner.nextInt();
			}if (opcioni == 0) {
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, state.get(sistema.getSession().getLogin())[0],LocalDate.now().toString() ,false, false);
				return false;
			}
			return true;
		}
		else {
			return false;
		}
		
		
	}
	public static boolean menuAplicacionProfesor(Sistema sistema) {
		
	}
}
