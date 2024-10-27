package System;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Usuarios.Estudiante;
import Usuarios.Profesor;
import Usuarios.Usuario;

public class Sistema {
	private HashMap<String, Estudiante> estudiantes;
	private HashMap<String, Profesor> profesores;
	private HashMap<String,LearningPath> learningPathsCreados;
	private HashMap<Integer, Actividad> actividades;
	private Connection connection;
	private Usuario Session;
	
	public Sistema() {
		this.estudiantes = new HashMap<String, Estudiante>();
		this.profesores = new HashMap<String, Profesor>();
		this.learningPathsCreados = new HashMap<String, LearningPath>();
		this.actividades = new HashMap<Integer, Actividad>();
		String url = "jdbc:derby:DataBase;create=true";
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			this.connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void cargarSistema() {
		try {
			
			System.out.println("La base de datos ha sido creada con exito");
			Statement statement = this.connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM USERS");
			//executeQuery con SELECT
			//executeUpdate con insert
			while (resultset.next()) {
				
				String login = resultset.getString("login");
				String password = resultset.getString("password");
				String correo = resultset.getString("correo");
				String tipo = resultset.getString("tipo");
				
				crearUsuario(login, password, correo, tipo, false);
				

			}
			
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		
		}
	}
	public Usuario crearUsuario(String login, String password, String correo, String tipo, boolean nuevo) throws SQLException {
		Statement statement = this.connection.createStatement();
		if (validezLogin(login)) {
			if (tipo.equals("estudiante") ) {
				Estudiante estudiante = new Estudiante(login, password, correo,  getLPsInscritos(login));
				this.estudiantes.put(login, estudiante);
				if (nuevo) {
					statement.executeUpdate("INSERT INTO USERS (login, password, correo, tipo) VALUES "
							+ "('"+login+"','"+password+"','"+correo+"', 'estudiante'"+")");
				}
				return estudiante;
			}else {
				Profesor profesor = new Profesor(login, password, correo, getActividadesCreadas(login), getLPsCreados(login));
				this.profesores.put(login, profesor);
				if (nuevo) {
					statement.executeUpdate("INSERT INTO USERS (login, password, correo, tipo) VALUES "
							+ "('"+login+"','"+password+"','"+correo+"', 'profesor'"+")");
				}
				return profesor;
			}	
			
			
		}else {
			return new Usuario("NULL", "NULL", "NULL");
		}
	}
	
	public LearningPath crearLearningPath(String creador, String titulo, String descripcionGeneral, String difficulty, int duration, int rating, String fechaCreacion, String fechaModificacion, boolean nuevo) throws SQLException {
		LearningPath newLP = new LearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, this);
		return newLP;
	}
	
	public boolean validezLogin(String login){
		boolean result = true;
		for (Iterator<String> iterator = this.estudiantes.keySet().iterator(); iterator.hasNext();) {
			String loginEstudiante = iterator.next();
			if (loginEstudiante.equals(login)) {
				return false;
			}
		}
		
		for (Iterator<String> iterator = this.profesores.keySet().iterator(); iterator.hasNext();) {
			String loginProfesor = iterator.next();
			if (loginProfesor.equals(login)) {
				return false;
			}
		}
		return result;
	}
	
	public ArrayList<LearningPath> getLPsInscritos(String login) throws SQLException{
		Statement statement = this.connection.createStatement();
		String sql = "SELECT CLP.id, CLP.nameLP FROM CreatedLearningPaths CLP WHERE CLP.login = "+"'"+login+"'";
		ArrayList<LearningPath> LPsInscritos = new ArrayList<LearningPath>();
		try {
			
			ResultSet resultset = statement.executeQuery(sql);
			Statement statement1 = this.connection.createStatement();
			while (resultset.next()) {
				String nombre = resultset.getString("nameLP");
				ResultSet LP = statement1.executeQuery("SELECT * FROM learningPaths LP WHERE LP.nameLP = '"+nombre+"'");
				if (LP.next()) {
					String creator = LP.getString("creator");
					String nameLP = LP.getString("namelp");
					String description = LP.getString("description");
					String difficulty = LP.getString("difficulty");
					int duration = LP.getInt("duration");
					int rating = LP.getInt("rating");
					String creationdate = LP.getString("creationdate");
					String moddate = LP.getString("moddate");
						
					
				
					if (!this.learningPathsCreados.containsKey(nameLP)) {
						this.learningPathsCreados.put(nameLP, null);
						LearningPath newLP = crearLearningPath(creator, nameLP, description, difficulty, duration, rating, creationdate, moddate, false);
						this.learningPathsCreados.put(nameLP, newLP);
						LPsInscritos.add(newLP);
					}else {
						LearningPath newLP = this.learningPathsCreados.get(nombre);
						LPsInscritos.add(newLP);
					}
					
				}
			}
			return LPsInscritos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LPsInscritos;
		}
		
	
	}
	
	
	public ArrayList<Actividad> getActividadesCreadas(String login) throws SQLException{
		Statement statement = this.connection.createStatement();
		ArrayList<Actividad> actividadesCreadas = new ArrayList<Actividad>();
		try {
			ResultSet resultset = statement.executeQuery("SELECT * FROM Activities A WHERE A.creator='"+login+"'");
			while (resultset.next()) {
				String creator = resultset.getString("creator");
				int id = resultset.getInt("id");
				boolean mandatory = resultset.getBoolean("mandatory");
				String description = resultset.getString("description");
				String difficulty = resultset.getString("difficulty");
				int duration = resultset.getInt("duration");
				boolean started = resultset.getBoolean("started");
				String datelimit = resultset.getString("datelimit");
				int calificacionMinima = resultset.getInt("calificacionMinima");
				String type = resultset.getString("type");
				String documentPath = resultset.getString("documentPath");
				//Actividad actividad = new Actividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, getEstadosActividad(id));
				Actividad actividad = crearActividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, type, documentPath,calificacionMinima, getEstadosActividad(id), false);
				if (!this.actividades.containsKey(id)) {
					this.actividades.put(id, actividad);
				}
				actividadesCreadas.add(actividad);
				
			}
			return actividadesCreadas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return actividadesCreadas;
		}
	}
	public ArrayList<LearningPath> getLPsCreados(String login) throws SQLException{
		Statement statement = this.connection.createStatement();
		ArrayList<LearningPath> LPsCreados = new ArrayList<LearningPath>();
		try {
			ResultSet resultset = statement.executeQuery("SELECT * FROM learningpaths LP WHERE LP.creator ='"+login+"'");
			while (resultset.next()) {
				
				String creator = resultset.getString("creator");
				String nameLP = resultset.getString("namelp");
				String description = resultset.getString("description");
				String difficulty = resultset.getString("difficulty");
				int duration = resultset.getInt("duration");
				int rating = resultset.getInt("rating");
				String creationdate = resultset.getString("creationdate");
				String moddate = resultset.getString("moddate");
						
				LearningPath newLP = crearLearningPath(creator, nameLP, description, difficulty, duration, rating, creationdate, moddate, false);
				if (!this.learningPathsCreados.containsKey(nameLP)) {
					this.learningPathsCreados.put(nameLP, newLP);
				}
				LPsCreados.add(newLP);
			}
			return LPsCreados;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LPsCreados;
		}
	}
	
	public HashMap<String, String[]> getEstadosActividad(int id) throws SQLException{
		Statement statement = this.connection.createStatement();
		
		HashMap<String, String[]> states = new HashMap<String, String[]>();
		try {
			ResultSet resultset = statement.executeQuery("SELECT * FROM inscribedactivities IA WHERE IA.idactivity = "+String.valueOf(id)+"");
			while (resultset.next()) {
				String login = resultset.getString("login");
				String[] state = (resultset.getString("state")).split(",");
				states.put(login, state);
			}
			return states;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return states;
		}
	}
	
	public Actividad crearActividad(String creator, int id, boolean mandatory, String description, String difficulty, int duration, boolean started, String datelimit, String type, String documentPath, int calificacionMinima,HashMap<String,String[]> states, boolean nuevo) throws SQLException {
		Statement globalStatement = this.connection.createStatement();
		if (type.equals("actividadRecurso")) {
			ActividadRecurso actividadRecurso =  new ActividadRecurso(creator, id, mandatory, description, difficulty, duration, started, datelimit,states, documentPath);
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, actividadRecurso);
			}
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(id)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+"," + String.valueOf(started)+",'"+datelimit+"','"+type+"','"+documentPath+"',"+String.valueOf(calificacionMinima) +")");
			}
			return actividadRecurso;
			
		}else if (type.equals("quiz")) {
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Quiz quiz = new Quiz(creator, id, mandatory, description,difficulty, duration, started, datelimit, states, preguntas,  calificacionMinima, getRespuestasActividad(IDsPreguntas));
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, quiz);
			}
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(id)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+"," + String.valueOf(started)+",'"+datelimit+"','"+type+"','"+documentPath+"',"+String.valueOf(calificacionMinima) +")");
				
			}
			return quiz;
			
			
		}else if (type.equals("examen")) {
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Examen examen = new Examen(creator, id, mandatory, description,difficulty, duration, started, datelimit, states, preguntas, getRespuestasActividad(IDsPreguntas));
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, examen);
			}
			
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(id)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+"," + String.valueOf(started)+",'"+datelimit+"','"+type+"','"+documentPath+"',"+String.valueOf(calificacionMinima) +")");
			}
			return examen;
		
		}else if (type.equals("encuesta")) {
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Encuesta encuesta = new Encuesta(creator, id, mandatory, description,difficulty, duration, started, datelimit, states, preguntas, getRespuestasActividad(IDsPreguntas));	
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, encuesta);
			}
			
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(id)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+"," + String.valueOf(started)+",'"+datelimit+"','"+type+"','"+documentPath+"',"+String.valueOf(calificacionMinima) +")");
			}
			return encuesta;
		
		
		}else if (type.equals("tarea")) {
			Statement statement = this.connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM activities A WHERE A.id = "+ id+"");
			String comment = "";
			if (resultset.next()) {
				comment = resultset.getString("comment");
			}
			Tarea tarea = new Tarea(creator, id, mandatory, description, difficulty, duration, started, datelimit, states, comment);
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, tarea);
			}
			
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"','"+String.valueOf(id)+"','"+description +"','"+ difficulty + "','"+String.valueOf(duration)+"','" + String.valueOf(started)+"','"+datelimit+"','"+type+"','"+documentPath+"','"+String.valueOf(calificacionMinima) +"')");
				
			}
			return tarea;
			
		}else {
			return null;
		}
	}
	
	public ArrayList<Pregunta> getPreguntasActividad(int idActividad) throws SQLException{
		ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM questionsAsToQuestionaries QA WHERE QA.idActividad = "+String.valueOf(idActividad)+"");
		while (resultset.next()) {
			Statement statement1 = this.connection.createStatement();
			int idPregunta = resultset.getInt("idPregunta");
			ResultSet pregunta = statement1.executeQuery("SELECT * FROM questions Q WHERE Q.idPregunta = "+String.valueOf(idPregunta)+"");
			if (pregunta.next()) {
				String tipoPregunta = pregunta.getString("typeQ");
				String Qstatement = pregunta.getString("statement");
				if (tipoPregunta.equals("opcionMultiple")) {
					PreguntaOpcionMultiple preguntaOpcionMultiple = crearPreguntaOpcionMultiple(idPregunta, Qstatement);
					preguntas.add(preguntaOpcionMultiple);
				}if (tipoPregunta.equals("abierta")) {
					Pregunta preguntaAbierta = new Pregunta(Qstatement, idPregunta);
					preguntas.add(preguntaAbierta);
				}
			}
			
			
		}
		return preguntas;
	}
	
	public PreguntaOpcionMultiple crearPreguntaOpcionMultiple(int idPregunta, String Qstatement ) throws SQLException {
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT OA.explicacion, OA.correct, OA.enunciado FROM optionsAsToQuestions OA WHERE OA.idPregunta = "+idPregunta+"");
		ArrayList<Opcion> opciones = new ArrayList<Opcion>();
		Opcion respuestaCorrecta = null;
		while (resultset.next()) {
			String explicacion = resultset.getString("explicacion");
			String enunciado = resultset.getString("enunciado");
			boolean correct = resultset.getBoolean("correct");
			
			Opcion opcion = new Opcion(explicacion, correct, enunciado);
			if (correct) {
				respuestaCorrecta = opcion;
				
			}
			opciones.add(opcion);
		}
		return new PreguntaOpcionMultiple(respuestaCorrecta, Qstatement,idPregunta, opciones);
	}
	

	public HashMap<Integer, HashMap<String, Object>> getRespuestasActividad(ArrayList<Integer> IDsPreguntas) throws SQLException{
		HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<Integer, HashMap<String, Object>>();
		Statement statement = this.connection.createStatement();
		for (int i : IDsPreguntas) {
			ResultSet resultset = statement.executeQuery("SELECT * FROM answersActivity AA WHERE AA.idPregunta = "+String.valueOf(i)+"");
			while (resultset.next()) {
				String login = resultset.getString("usuario");
				int idOpcion = resultset.getInt("idOpcion");
				String respuesta = resultset.getString("respuesta");
				if (idOpcion != 0) {
					Opcion opcion = crearOpcionEscogidaPorUsuario(idOpcion);
					if (!respuestas.containsKey(i)) {
						respuestas.put(i, new HashMap<String, Object>());
					}
					
					respuestas.get(i).put(login, opcion);
				}else {
					if (!respuestas.containsKey(i)) {
						respuestas.put(i, new HashMap<String, Object>());
					}
					respuestas.get(i).put(login, respuesta );
				}
				
				
			}
		}
		return respuestas;
		
	}
	
	public Opcion crearOpcionEscogidaPorUsuario(int idOpcion) throws SQLException {
		
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM optionsAsToQuestions OA WHERE OA.idOpcion = '"+String.valueOf(idOpcion)+"'");
		if (resultset.next()) {
			String explicacion = resultset.getString("explicacion");
			String enunciado = resultset.getString("enunciado");
			boolean correct = resultset.getBoolean("correct");
			
			return new Opcion(explicacion, correct, enunciado);
		}
		else {
			return null;
		}
	}

	public String iniciarSesion(String login, String password) throws SQLException {
		Statement statement = this.connection.createStatement();
		
		if (!validezLogin(login)) {
			ResultSet resultset = statement.executeQuery("SELECT * FROM USERS U WHERE U.login = '"+login+"'");
			if (resultset.next()) {
				String passwordDatabase = resultset.getString("password");
				
			
			if (password.equals(passwordDatabase)) {
				this.Session = this.estudiantes.get(login);
				return "¡Inicio sesión correctamente '" + login +"'!";
				}
			}
		}
			
		return "Su usuario o contraseña son erróneos";
	}
	
	private Connection getConnection() {
		return this.connection;
	}
	public Usuario getSession() {
		return this.Session;
	}
	public ArrayList<Actividad> cargarActividadesLP(String nameLP) throws SQLException {
		ArrayList<Actividad> associatedActivities = new ArrayList<Actividad>();
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM CreatedActivities CA JOIN learningPaths LP ON CA.nameLPAssociated = LP.nameLP WHERE LP.nameLP = '"+nameLP+"'"); 
		while (resultset.next()) {
			//Acá hay que buscar el id de las actividades ahora, eso no tiene esas columnas
			int idActividad = resultset.getInt("id");
			Statement statement1 = this.connection.createStatement();
			ResultSet activity = statement1.executeQuery("SELECT * FROM Activities A WHERE A.id = "+String.valueOf(idActividad));
			if (activity.next()) {
				String creator = activity.getString("creator");
				int id = activity.getInt("id");
				boolean mandatory = activity.getBoolean("mandatory");
				String description = activity.getString("description");
				String difficulty = activity.getString("difficulty");
				int duration = activity.getInt("duration");
				boolean started = activity.getBoolean("started");
				String datelimit = activity.getString("datelimit");
				String type = activity.getString("type");
				int calificacionMinima = activity.getInt("calificacionMinima");
				String documentPath = activity.getString("documentPath");
				//Actividad actividad = new Actividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, getEstadosActividad(id));
				Actividad actividad = crearActividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, type, documentPath, calificacionMinima, getEstadosActividad(id), false);
				
				//ACAAAAAAAAAAAAAAAAA
				if (!this.actividades.containsKey(id)) {
					this.actividades.put(id, actividad);
				}
				associatedActivities.add(actividad);
			}
		}
		return associatedActivities;
	}
	
	public ArrayList<Usuario> cargarEstudiantesEnlistados(String nameLP) throws SQLException {
		ArrayList<Usuario> estudiantesEnlistados = new ArrayList<Usuario>();
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM CreatedLearningPaths CLP JOIN USERS U ON CLP.login = U.login WHERE CLP.nameLP = '"+nameLP+"'" );
		while (resultset.next()) {
			String login = resultset.getString("login");
			String password = resultset.getString("password");
			String correo = resultset.getString("correo");
			String tipo = resultset.getString("tipo");
			
			Usuario usuario = crearUsuario(login, password, correo, tipo, false);
			estudiantesEnlistados.add(usuario);
			
		}
		return estudiantesEnlistados;
	
	}
	public void actualizarEstado(Usuario usuario, Actividad actividad, String fechaInicio, String fechaFin, boolean aprobado, boolean yaEstabaCreado ) throws SQLException {
		HashMap<String, String[]> states = actividad.getState();
		String[] state = states.get(usuario.getLogin());
		state[0] = fechaInicio;
		state[1] = fechaFin;
		state[2] = String.valueOf(aprobado);
		states.put(usuario.getLogin(), state);
		Statement statement = this.connection.createStatement();
		if (!yaEstabaCreado) {
			statement.executeUpdate("INSERT INTO inscribedActivities (login, idActivity, state) VALUES ('"+usuario.getLogin()+"',"+String.valueOf(actividad.getID())+",'"+fechaInicio+","+fechaFin+","+String.valueOf(aprobado)+"')");
		}else {
			statement.executeUpdate("UPDATE inscribedActivities SET state = '"+fechaInicio+","+fechaFin+","+String.valueOf(aprobado)+"' WHERE idActivity = "+ String.valueOf(actividad.getID())+ "AND login = '"+usuario.getLogin()+"'");
		}
	}
}

	public Estudiante buscarEstudiantePorLogin(String login) {
		return estudiantes.get(login);
}
	public Examen getExamenById(int idExamen) {
        Actividad actividad = actividades.get(idExamen); 
        
        if (actividad instanceof Examen) { 
            return (Examen) actividad; 
        } else {
            System.out.println("Examen no encontrado con ID: " + idExamen);
            return null; 
        }
    }
	
	public Actividad buscarActividadPorTitulo(String titulo) throws SQLException {
	    Statement statement = this.connection.createStatement();
	    Actividad actividadEncontrada = null;
	    try {
	        ResultSet resultset = statement.executeQuery("SELECT * FROM Activities WHERE title='" + titulo + "'");
	        if (resultset.next()) {
	            String creator = resultset.getString("creator");
	            int id = resultset.getInt("id");
	            boolean mandatory = resultset.getBoolean("mandatory");
	            String description = resultset.getString("description");
	            String difficulty = resultset.getString("difficulty");
	            int duration = resultset.getInt("duration");
	            boolean started = resultset.getBoolean("started");
	            String datelimit = resultset.getString("datelimit");
	            int calificacionMinima = resultset.getInt("calificacionMinima");
	            String type = resultset.getString("type");
	            String documentPath = resultset.getString("documentPath");

	            actividadEncontrada = crearActividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, type, documentPath, calificacionMinima, getEstadosActividad(id), false);

	            
	            if (!this.actividades.containsKey(id)) {
	                this.actividades.put(id, actividadEncontrada);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return actividadEncontrada; 
	}

	public LearningPath buscarLearningPathPorTitulo(String titulo) {
	    for (LearningPath lp : this.learningPathsCreados.values()) {
	        if (lp.getTitulo().equalsIgnoreCase(titulo)) {
	            return lp;
	        }
	    }
	    return null; 
	}
