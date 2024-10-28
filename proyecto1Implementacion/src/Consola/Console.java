package Consola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import System.Actividad;
import System.ActividadRecurso;
import System.Encuesta;
import System.Examen;
import System.LearningPath;
import System.Opcion;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Quiz;
import System.Sistema;
import System.Tarea;
import Usuarios.Estudiante;
import Usuarios.Profesor;
import Usuarios.Usuario;

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
			System.out.println("Digito mal su usuario o contraseña");
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
				System.out.println(pregunta.getEnunciado());
				ArrayList<Opcion> opciones = pregunta.getOpciones();
				for (int j = 1; j<=opciones.size(); j++) {
					System.out.println("Opcion " + String.valueOf(j));
					Opcion opcioni = opciones.get(j-1);
					System.out.println(opcioni.getEnunciado());
				}
				System.out.println("Seleccione la opcion que considere correcta :");
				int respuestaUsuario = scanner.nextInt() -1;
				//sistema.insertarAnswersActiyity();
				sistema.actualizarRespuestaUsuario(sistema.getSession(), pregunta.getID(), String.valueOf(opciones.get(respuestaUsuario).getID()), "NULL", false);
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
			for (int i =1 ; i <= preguntas.size(); i++) {
				System.out.println("Pregunta "+String.valueOf(i)+":");
				Pregunta pregunta= preguntas.get(i-1);
				//System.out.println("Pregunta "+String.valueOf(i));
				System.out.println(pregunta.getEnunciado());
				System.out.println("A continuación escriba su respuesta");
				if (i == 1) {
					scanner.nextLine();
				}
				String respuestaUsuario = scanner.nextLine();
				scanner.nextLine();
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
			Encuesta actividad = (Encuesta) actividadEscogida;
			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
			for (int i =1 ; i <= preguntas.size(); i++) {
				System.out.println("Pregunta "+String.valueOf(i)+":");
				Pregunta pregunta= preguntas.get(i-1);
				//System.out.println("Pregunta "+String.valueOf(i));
				System.out.println(pregunta.getEnunciado());
				System.out.println("A continuación escriba su respuesta");
				if ( i == 1) {
					scanner.nextLine();
				}
				String respuestaUsuario = scanner.nextLine();
				scanner.nextLine();
				sistema.actualizarRespuestaUsuario(sistema.getSession(), pregunta.getID(), "NULL", respuestaUsuario, false);
				
			}
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, state.get(sistema.getSession().getLogin())[0], LocalDate.now().toString(), true, false);
			return true;
		}else if (actividadEscogida.getClass().getSimpleName().equals("ActividadRecurso") & opcion3==0 & !yaAprobada) {
			//sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
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
			//sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
			HashMap<String, String[]> state = actividadEscogida.getState();
			if (!state.containsKey(sistema.getSession().getLogin())) {
				state.put(sistema.getSession().getLogin(), new String[3]);
				sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString() , "", false, false);
			}
			sistema.actualizarEstado(sistema.getSession(), actividadEscogida, LocalDate.now().toString(),"" ,false, false);
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
	
	public static boolean menuAplicacionProfesor(Sistema sistema, Scanner scanner) throws SQLException {
	    Profesor profesor = (Profesor) sistema.getSession();

	    while (true) {
	        System.out.println("\n--- Menú del Profesor ---");
	        System.out.println("[1] Crear Learning Path");
	        System.out.println("[2] Editar Learning Path");
	        System.out.println("[3] Calificar Actividad");
	        //System.out.println("[4] Calificar Examen");
	        //System.out.println("[5] Publicar reseña");
	        System.out.println("[4] Ver Actividades Creadas");
	        System.out.println("[5] Ver Learning Paths Creados");
	        System.out.println("[6] Cerrar sesión");
	        System.out.print("¿Qué desea hacer? ");

	        int opcion = scanner.nextInt();
	        //scanner.nextLine(); 

	        switch (opcion) {
		        case 1:
	                
	                System.out.print("Ingrese el título del Learning Path: ");
	                String tituloLP = scanner.next();
	                if (sistema.getLPs().containsKey(tituloLP)){
	                	System.out.println("Ese nombre ya fue usado para otro learning path");
	                	break;
	                }
	
	                System.out.print("Ingrese la descripción general: ");
	                String descripcionGeneral = scanner.nextLine();
	                scanner.nextLine();
	
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
		        	while (opcioni<1 & opcion >LPs.size()) {
		        		opcioni = scanner.nextInt();
		        		
		        		
		        	}
		        	if (opcioni  == LPs.size()+1) {
		        		break;
		        	}
		        	menuEdicion(sistema, scanner, LPs.get(opcioni-1));
		        	
		        	
		            break;
		            
		        case 3:
		            
		            boolean runMenuCalificar = true;
		            while (runMenuCalificar) {
		            	runMenuCalificar = menuCalificarLP(sistema, scanner);
		            }
		            
		            break;
		            
		        
		            
		        case 4:
	                
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

	            case 5:
	                
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
	                
	            case 6:
	                
	                System.out.println("Cerrando sesión...");
	                return false;                 
	                
		           
	            default:
	                System.out.println("Opción no válida. Intente de nuevo.");
	        }
	    }
	}
	public static boolean menuEdicion(Sistema sistema, Scanner scanner, LearningPath LP) throws SQLException {
		System.out.println("___________________________________");
		System.out.println("Bienvenido al menu de edicion ");
		System.out.println("Ahora mismo esta editando el Learning Path : "+ LP.getTitulo());
		System.out.println("[0] Añadir actividad");
		//System.out.println("[1] Remover alguna actividad");
		System.out.println("[1] Salir");
		int opcion = scanner.nextInt();
		while (opcion != 0 & opcion != 1 & opcion != 2) {
			opcion = scanner.nextInt();
		}
		
		if (opcion == 1) {
			return false;
		}else if (opcion == 0) {
			boolean runMenuCA = true;
			while (runMenuCA) {
				runMenuCA = menuCreacionActividad(sistema, scanner, LP);
			}
			return true;
		}else {
			return true;
		}
	}
	public static boolean menuCreacionActividad(Sistema sistema, Scanner scanner, LearningPath LP) throws SQLException {
		System.out.println("______________________________________");
		System.out.println("A continuación se le va a pedir que digite una información necesaria para la creacion de la actividad");
		System.out.println("DESCRIPCION : ");
		scanner.nextLine();
		String descripcion = scanner.nextLine();
		scanner.nextLine();
		System.out.println("DIFICULTAD : ");
		String dificultad = scanner.next();
		System.out.println("OBLIGATORIA (true, false) : ");
		boolean mandatory = scanner.nextBoolean();
		System.out.println("DURACION (en minutos) : ");
		int duration = scanner.nextInt();
		System.out.println("FECHA LÍMITE : ");
		String dateLimit = scanner.next();
		String documentPath = "";
		int calificacionMinima = 0;
		System.out.println("TIPO (quiz, examen, encuesta, tarea, recurso)");
		String tipo = scanner.next();
		if (tipo.equals("recurso")) {
			System.out.println("Digite el hipervinculo del recurso del que desea hacer display");
			documentPath = scanner.next();
		}
		if (tipo.equals("quiz")) {
			System.out.println("Digite la calificacion mínima necesaria para aprobar esta actividad");
			calificacionMinima = scanner.nextInt();
		}
		
		
		if (tipo.equals("quiz")) {
			
			Quiz newActividad = (Quiz) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentPath, calificacionMinima, new HashMap<String, String[]>(), true,"");
			
			System.out.println("Usted eligio una actividad de tipo quiz");
			System.out.println("Por ello a continuación digite el numero de preguntas que quiere que su actividad contenga");
			int cantidadPreguntas = scanner.nextInt();
			//ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
			newActividad.setPreguntas(new ArrayList<Pregunta>());
			ArrayList<Pregunta> preguntas = newActividad.getPreguntas();
			for (int i = 1 ; i<= cantidadPreguntas; i ++) {
				System.out.println("-------------------");
				System.out.println("Pregunta ("+String.valueOf(i)+")" );
				System.out.println("-------------------");
				System.out.println("Por favor digite el enunciado de su pregunta : ");
				scanner.nextLine();
				String enunciado = scanner.nextLine();
				scanner.nextLine();
				int idPregunta = sistema.insertarPreguntaQuestionsAsToQuestionaries(newActividad.getID());
				
				if (tipo.equals("quiz")) {
					sistema.insertarQuestions(idPregunta, "opcionMultiple", enunciado);
					System.out.println("Cuantas opciones desea añadir a su pregunta (Recuerde son maximo 4 opciones)");
					int cantidadOpciones = scanner.nextInt();
					while (cantidadOpciones > 4 & cantidadOpciones <1) {
						cantidadOpciones = scanner.nextInt();
					}
					ArrayList<Opcion> opciones = new ArrayList<Opcion>();
					for (int j = 1 ; j<=cantidadOpciones; j ++) {
						System.out.println("OPCION ("+String.valueOf(j)+")");
						System.out.println("Enunciado de la OPCION : ");
						if (j== 1) {
							scanner.nextLine();
						}
						String enunciadoOpcion = scanner.nextLine();
						scanner.nextLine();
						System.out.println("¿La opcion es correcta? Digite true o false (true: Sí, false : No)");
						boolean correct = scanner.nextBoolean();
						System.out.println("¿Por qué la opcion es correcta o incorrecta?");
						scanner.nextLine();
						String explicacion = scanner.nextLine();
						scanner.nextLine();
						int idOpcion = sistema.insertarOptionsAsToQuestions(idPregunta, enunciadoOpcion, explicacion, correct);
						Opcion newOpcion	 = sistema.crearOpcionEscogidaPorUsuario(idOpcion);
						opciones.add(newOpcion);
					}
					PreguntaOpcionMultiple newPregunta = sistema.crearPreguntaOpcionMultiple(idPregunta, enunciado);
					preguntas.add(newPregunta);
				}
				
			}
			newActividad.setPreguntas(preguntas);
			sistema.insertarCreatedActivities(newActividad.getID(), LP.getTitulo());
			ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
			actividades.add(newActividad);
			((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
		}
		else if (tipo.equals("examen")) {
			Examen newActividad = (Examen) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentPath, calificacionMinima, new HashMap<String, String[]>(), true, "");
		
			System.out.println("Usted eligio una actividad de tipo examen");
			System.out.println("Por ello a continuación digite el numero de preguntas que quiere que su actividad contenga");
			int cantidadPreguntas = scanner.nextInt();
			//ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
			newActividad.setPreguntas(new ArrayList<Pregunta>());
			ArrayList<Pregunta> preguntas = newActividad.getPreguntas();
			for (int i = 1 ; i<= cantidadPreguntas; i ++) {
				System.out.println("-------------------");
				System.out.println("Pregunta ("+String.valueOf(i)+")" );
				System.out.println("-------------------");
				System.out.println("Por favor digite el enunciado de su pregunta : ");
				if (i == 1) {
					scanner.nextLine();
				}
				String enunciado = scanner.nextLine();
				scanner.nextLine();
				int idPregunta = sistema.insertarPreguntaQuestionsAsToQuestionaries(newActividad.getID());
				sistema.insertarQuestions(idPregunta, "abierta", enunciado);
				Pregunta pregunta = new Pregunta(enunciado, idPregunta);
				preguntas.add(pregunta);
			}
			newActividad.setPreguntas(preguntas);
			sistema.insertarCreatedActivities(newActividad.getID(), LP.getTitulo());
			ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
			actividades.add(newActividad);
			((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
		}
		else if (tipo.equals("encuesta")) {
			Encuesta newActividad = (Encuesta) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentPath, calificacionMinima, new HashMap<String, String[]>(), true, "");
			
			System.out.println("Usted eligio una actividad de tipo encuesta");
			System.out.println("Por ello a continuación digite el numero de preguntas que quiere que su actividad contenga");
			int cantidadPreguntas = scanner.nextInt();
			//ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
			newActividad.setPreguntas(new ArrayList<Pregunta>());
			ArrayList<Pregunta> preguntas = newActividad.getPreguntas();
			for (int i = 1 ; i<= cantidadPreguntas; i ++) {
				System.out.println("-------------------");
				System.out.println("Pregunta ("+String.valueOf(i)+")" );
				System.out.println("-------------------");
				System.out.println("Por favor digite el enunciado de su pregunta : ");
				if (i==1) {
					scanner.nextLine();
				}
				String enunciado = scanner.nextLine();
				scanner.nextLine();
				int idPregunta = sistema.insertarPreguntaQuestionsAsToQuestionaries(newActividad.getID());
				sistema.insertarQuestions(idPregunta, "abierta", enunciado);
				Pregunta pregunta = new Pregunta(enunciado, idPregunta);
				preguntas.add(pregunta);
			}
			newActividad.setPreguntas(preguntas);
			sistema.insertarCreatedActivities(newActividad.getID(), LP.getTitulo());
			ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
			actividades.add(newActividad);
			((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
		}
		else if (tipo.equals("recurso")) {
			System.out.println("Usted eligio una actividad de tipo recurso");
			//System.out.println("Por favor digite a continuación el hipervinculo que desea que los estudiantes visualicen");
			//documentPath = scanner.next();
			ActividadRecurso newActividad = (ActividadRecurso) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentPath, calificacionMinima, new HashMap<String, String[]>(), true, "");
			sistema.insertarCreatedActivities(newActividad.getID(), LP.getTitulo());
			ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
			actividades.add(newActividad);
			((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
		}else if (tipo.equals("tarea")) {
			System.out.println("Usted eligio una actividad de tipo tarea");
			System.out.println("Por favor digite a continuación el comentario que desea añadir a la tarea : ");
			scanner.nextLine();
			String comment = scanner.nextLine();
			scanner.nextLine();
			Tarea newActividad = (Tarea) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duration, false, dateLimit, tipo, documentPath, calificacionMinima, new HashMap<String, String[]>(), true, comment);
			sistema.insertarCreatedActivities(newActividad.getID(), LP.getTitulo());
			ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
			actividades.add(newActividad);
			((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
		}
		
		System.out.println("______________________________________");
		return false;
	}
	
	public static boolean menuCalificarLP(Sistema sistema, Scanner scanner) throws SQLException {
		System.out.println("_______________________________________________");
		System.out.println("¡Bienvenido! Acá podrá calificar actividades (cambiar el estado de los estudiante de aprobado a desaprobado y viceversa");
		System.out.println("Escoja que learning path desea corregir");
		ArrayList<LearningPath> LPs =  ((Profesor) sistema.getSession()).getLPs();
    	for (int i = 1 ; i <= LPs.size(); i++) {
    		System.out.println("["+String.valueOf(i)+"]"+LPs.get(i-1).getTitulo());
    	}
    	System.out.println("["+String.valueOf(LPs.size()+1)+"] Volver");
    	System.out.println("Opcion : ");
    	int opcioni = scanner.nextInt();
    	if (opcioni == LPs.size()+1) {
    		return false;
    	}else {
    		LearningPath LPEscogido = LPs.get(opcioni-1);
    		boolean runMenuCalificarActividad = true;
    		while (runMenuCalificarActividad) {
    			runMenuCalificarActividad = menuCalificarActividad(sistema, scanner, LPEscogido);
    		}
    		return true;
    	}
    	
	}
	public static boolean menuCalificarActividad(Sistema sistema, Scanner scanner, LearningPath LPEscogido) throws SQLException {
		System.out.println("_______________________________________________");
		System.out.println("ACTIVIDADES DEL LEARNING PATH ");
		ArrayList<Actividad> activities = LPEscogido.getActivities();
		for (int i = 0; i < activities.size();i++) {
			System.out.println("["+String.valueOf(i)+"] " + activities.get(i).getID());
		}
		System.out.println("["+String.valueOf(activities.size())+"] Volver");
		System.out.println("Seleccione que actividad de "+LPEscogido.getTitulo()+"desea calificar : ");
		int opcioni = scanner.nextInt();
		if (opcioni == activities.size()) {
			return false;
		}else if  (opcioni>=0 & opcioni<activities.size()){
			Actividad ACEscogida = activities.get(opcioni);
			boolean runMenuCalificarEnlistados = true;
			while (runMenuCalificarEnlistados) {
				runMenuCalificarEnlistados = MenuCalificarEnlistados(sistema, scanner, ACEscogida);
			}
			return true;
		}else {
			
			return true;
		}
		
	}
	
	public static boolean MenuCalificarEnlistados(Sistema sistema, Scanner scanner, Actividad ACEscogida) throws SQLException {
		System.out.println("____________________________________________");
		System.out.println("USUARIOS QUE YA HICIERON LA ACTIVIDAD");
		ArrayList<Estudiante> enlistados = new ArrayList<Estudiante>();
		for (Iterator<String> iterator = ACEscogida.getState().keySet().iterator(); iterator.hasNext();) {
			String name = iterator.next();
			enlistados.add(sistema.getEstudiante(name));
			
		}
		for (int i = 0; i < enlistados.size(); i++) {
			System.out.println("["+String.valueOf(i)+"] " + enlistados.get(i).getLogin());
		}
		System.out.println("["+String.valueOf(enlistados.size())+"] Volver");
		int opcioni = scanner.nextInt();
		if (opcioni == enlistados.size()) {
			return false;
		}else {
			Estudiante estudiante = enlistados.get(opcioni);
			boolean runMenuCalificarEnlistado = true;
			while (runMenuCalificarEnlistado) {
				runMenuCalificarEnlistado = MenuCalificarEnlistado(sistema, scanner, estudiante, ACEscogida);
			}
			
			
			return true;
		}
	}
	public static boolean MenuCalificarEnlistado(Sistema sistema, Scanner scanner, Estudiante estudiante, Actividad ACEscogida) throws SQLException {
		System.out.println("_______________________________");
		HashMap<String, String[]> state = ACEscogida.getState();
		if (ACEscogida.getClass().getSimpleName().equals("Tarea") | ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
			System.out.println("Esta actividad no es calificable por medio de la aplicacion");
			return false;
		}else {
			if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
				Quiz quiz = (Quiz) ACEscogida;
				ArrayList<Pregunta> preguntas = quiz.getPreguntas();
				for (int i = 0; i < preguntas.size(); i++) {
					System.out.println("["+String.valueOf(i)+"] Pregunta : "+ preguntas.get(i).getID());
				}
				System.out.println("["+String.valueOf(preguntas.size()) + "] Aprobar");
				System.out.println("["+String.valueOf(preguntas.size()+1)+"] Volver");
				int opcioni = scanner.nextInt();
				if (opcioni == preguntas.size()+1) {
					return false;
				}
				else if (opcioni>= 0 & opcioni < preguntas.size()) {
					Pregunta preguntaEscogida = preguntas.get(opcioni);
					HashMap<Integer , HashMap<String, Object>> respuestas = quiz.getRespuestas();
					Opcion opcion = (Opcion) respuestas.get(preguntaEscogida.getID()).get(estudiante.getLogin());
					Object[] array = sistema.getAnswersActivity(preguntaEscogida.getID(), estudiante.getLogin());
					System.out.println("Para la pregunta con id "+ preguntaEscogida.getID() +" el usuario "+estudiante.getLogin()+ " contesto : ");
					System.out.println(((Opcion) array[2]).getEnunciado());
					
				}else if(opcioni == preguntas.size()) {
					sistema.actualizarEstado(estudiante, quiz, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
				}
				
				else {
					return true;
				}
			}else if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
				Examen examen = (Examen) ACEscogida;
				ArrayList<Pregunta> preguntas = examen.getPreguntas();
				for (int i = 0; i < preguntas.size(); i++) {
					System.out.println("["+String.valueOf(i)+"] Pregunta : "+ preguntas.get(i).getID());
				}
				System.out.println("["+String.valueOf(preguntas.size()) + "] Aprobar");
				System.out.println("["+String.valueOf(preguntas.size()+1)+"] Volver");
				int opcioni = scanner.nextInt();
				if (opcioni == preguntas.size()+1) {
					return false;
				}
				else if (opcioni>= 0 & opcioni < preguntas.size()) {
					Pregunta preguntaEscogida = preguntas.get(opcioni);
					Object[] array = sistema.getAnswersActivity(preguntaEscogida.getID(), estudiante.getLogin());
					System.out.println("Para la pregunta con id "+ preguntaEscogida.getID() +" el usuario "+estudiante.getLogin()+ " contesto : ");
					System.out.println(preguntaEscogida.getEnunciado());
					System.out.println(((String) array[0]));
					
				}else if(opcioni == preguntas.size()) {
					sistema.actualizarEstado(estudiante, examen, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
				}
				
				else {
					return true;
				}
			}else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
				Encuesta encuesta = (Encuesta) ACEscogida;
				ArrayList<Pregunta> preguntas = encuesta.getPreguntas();
				for (int i = 0; i < preguntas.size(); i++) {
					System.out.println("["+String.valueOf(i)+"] Pregunta : "+ preguntas.get(i).getID());
				}
				System.out.println("["+String.valueOf(preguntas.size()) + "] Aprobar");
				System.out.println("["+String.valueOf(preguntas.size()+1)+"] Volver");
				int opcioni = scanner.nextInt();
				if (opcioni == preguntas.size()+1) {
					return false;
				}
				else if (opcioni>= 0 & opcioni < preguntas.size()) {
					Pregunta preguntaEscogida = preguntas.get(opcioni);
					Object[] array = sistema.getAnswersActivity(preguntaEscogida.getID(), estudiante.getLogin());
					System.out.println("Para la pregunta con id "+ preguntaEscogida.getID() +" el usuario "+estudiante.getLogin()+ " contesto : ");
					System.out.println(preguntaEscogida.getEnunciado());
					System.out.println(((String) array[0]));
					
				}else if(opcioni == preguntas.size()) {
					sistema.actualizarEstado(estudiante, encuesta, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
				}
				
				else {
					return true;
				}
			}
			return true;
		}
	}
}




