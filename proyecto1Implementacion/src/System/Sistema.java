package System;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	public Sistema(String prueba) {
		this.estudiantes = new HashMap<String, Estudiante>();
		this.profesores = new HashMap<String, Profesor>();
		this.learningPathsCreados = new HashMap<String, LearningPath>();
		this.actividades = new HashMap<Integer, Actividad>();
		String url = "jdbc:derby:DataBase" +prueba+ ";create=true";
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
			this.estudiantes = new HashMap<String, Estudiante>();
			this.profesores = new HashMap<String, Profesor>();
			this.learningPathsCreados = new HashMap<String, LearningPath>();
			this.actividades = new HashMap<Integer, Actividad>();
			//System.out.println("La base de datos ha sido creada con exito");
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
	
	public LearningPath crearLearningPath(String creador, String titulo, String descripcionGeneral, String difficulty, int duration, int rating, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, boolean nuevo) throws SQLException {
		LearningPath newLP = new LearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, this);
		if (nuevo) {
			Statement statement = this.connection.createStatement();
			//statement.executeUpdate("INSERT INTO createdLearningPaths (nameLP, login) VALUES ('"+titulo+"','"+creador+"')");
			String creationDate = LocalDate.now().toString();
			String creationTime = LocalTime.now().toString();
			String modificationDate = LocalDate.now().toString();
			String modificationTime = LocalTime.now().toString();
			String cd = creationDate + " " + creationTime;
			String md = modificationDate + " " + modificationTime;
			statement.executeUpdate("INSERT INTO learningPaths (nameLP,creator, description, difficulty, duration, rating, creationDate, moddate) VALUES ('"+titulo+"','"+creador+"','"+descripcionGeneral+"','"+difficulty+"',"+String.valueOf(duration)+","
					+ "0,'"+cd+"','"+md+"')");
		}
		
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
					//String creationdate = LP.getString("creationdate");
					//String moddate = LP.getString("moddate");
					LocalDateTime creationdate = LP.getTimestamp("creationdate").toLocalDateTime();
					LocalDateTime moddate =  LP.getTimestamp("moddate").toLocalDateTime();
				
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
				//boolean started = resultset.getBoolean("started");
				LocalDateTime datelimit = resultset.getTimestamp("datelimit").toLocalDateTime();
				int calificacionMinima = resultset.getInt("calificacionMinima");
				String type = resultset.getString("type");
				String documentPath = resultset.getString("documentPath");
				String comment = resultset.getString("comment");
				//Actividad actividad = new Actividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, getEstadosActividad(id));
				Actividad actividad = crearActividad(creator, id, mandatory, description, difficulty, duration, datelimit, type, documentPath,calificacionMinima, getEstadosActividad(id), false, comment);
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
				//String creationdate = resultset.getString("creationdate");
				//String moddate = resultset.getString("moddate");
				LocalDateTime creationdate = resultset.getTimestamp("creationdate").toLocalDateTime();
				LocalDateTime moddate =  resultset.getTimestamp("moddate").toLocalDateTime();
				
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
	
	public Actividad crearActividad(String creator, int id, boolean mandatory, String description, String difficulty, int duration, LocalDateTime datelimit, String type, String documentPath, int calificacionMinima,HashMap<String,String[]> states, boolean nuevo, String comment) throws SQLException {
		Statement globalStatement = this.connection.createStatement();
		LocalDate fecha = datelimit.toLocalDate();
		LocalTime hora = datelimit.toLocalTime();
		String horaFormateada = hora.toString();
		if (horaFormateada.length()<8) {
			horaFormateada= horaFormateada + ":00";
		}
		if (type.equals("recurso")) {
			if (nuevo) {
				
				
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(mandatory)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+",'"+fecha.toString()+" "+horaFormateada+"','"+type+"','"+documentPath+"','"+comment+"',"+String.valueOf(calificacionMinima) +")");
				StringBuilder notin = new StringBuilder();
				notin.append("(");
				int conteo = 0;
				for (int a : this.actividades.keySet()) {
					notin.append(String.valueOf(a));
					if (conteo != this.actividades.keySet().size()-1) {
						notin.append(",");
					}
					conteo ++;
				}
				notin.append(")");
				ResultSet resultset = globalStatement.executeQuery("SELECT * FROM Activities A WHERE A.id NOT IN "+notin.toString());
				if (resultset.next()) {
					id = resultset.getInt("id");
				}
			}
			ActividadRecurso actividadRecurso =  new ActividadRecurso(creator, id, mandatory, description, difficulty, duration, datelimit,states, documentPath);
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, actividadRecurso);
			}
			
			return actividadRecurso;
			
		}else if (type.equals("quiz")) {
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator,mandatory, description, difficulty, duration, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(mandatory)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+",'"+fecha.toString()+" "+horaFormateada+"','"+type+"','"+documentPath+"','"+comment+"',"+String.valueOf(calificacionMinima) +")");
				StringBuilder notin = new StringBuilder();
				notin.append("(");
				int conteo = 0;
				for (int a : this.actividades.keySet()) {
					notin.append(String.valueOf(a));
					if (conteo != this.actividades.keySet().size()-1) {
						notin.append(",");
					}
					conteo ++;
				}
				notin.append(")");
				ResultSet resultset = globalStatement.executeQuery("SELECT * FROM Activities A WHERE A.id NOT IN "+notin.toString());
				if (resultset.next()) {
					id = resultset.getInt("id");
				}
			}
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Quiz quiz = new Quiz(creator, id, mandatory, description,difficulty, duration,  datelimit, states, preguntas,  calificacionMinima, getRespuestasActividad(IDsPreguntas));
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, quiz);
			}

			return quiz;
			
			
		}else if (type.equals("examen")) {
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(mandatory)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+",'"+fecha.toString()+" "+horaFormateada+"','"+type+"','"+documentPath+"','"+comment+"',"+String.valueOf(calificacionMinima) +")");
				StringBuilder notin = new StringBuilder();
				notin.append("(");
				int conteo = 0;
				for (int a : this.actividades.keySet()) {
					notin.append(String.valueOf(a));
					if (conteo != this.actividades.keySet().size()-1) {
						notin.append(",");
					}
					conteo ++;
				}
				notin.append(")");
				
				ResultSet resultset = globalStatement.executeQuery("SELECT * FROM Activities A WHERE A.id NOT IN "+notin.toString());
				if (resultset.next()) {
					id = resultset.getInt("id");
				}
			}
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Examen examen = new Examen(creator, id, mandatory, description,difficulty, duration,datelimit, states, preguntas, getRespuestasActividad(IDsPreguntas));
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, examen);
			}
			
	
			return examen;
		
		}else if (type.equals("encuesta")) {
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(mandatory)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+",'"+fecha.toString()+" "+horaFormateada+"','"+type+"','"+documentPath+"','"+comment+"',"+String.valueOf(calificacionMinima) +")");
				
				StringBuilder notin = new StringBuilder();
				notin.append("(");
				int conteo = 0;
				for (int a : this.actividades.keySet()) {
					notin.append(String.valueOf(a));
					if (conteo != this.actividades.keySet().size()-1) {
						notin.append(",");
					}
					conteo ++;
				}
				notin.append(")");
				
				ResultSet resultset = globalStatement.executeQuery("SELECT * FROM Activities A WHERE A.id NOT IN "+notin.toString());
				if (resultset.next()) {
					id = resultset.getInt("id");
				}
			}
			ArrayList<Pregunta> preguntas = getPreguntasActividad(id);
			ArrayList<Integer> IDsPreguntas = new ArrayList<Integer>();
			for (Pregunta p : preguntas) {
				IDsPreguntas.add(p.idPregunta);
			}
			Encuesta encuesta = new Encuesta(creator, id, mandatory, description,difficulty, duration, datelimit, states, preguntas, getRespuestasActividad(IDsPreguntas));	
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, encuesta);
			}
			

			return encuesta;
		
		
		}else if (type.equals("tarea")) {
			if (nuevo) {
				globalStatement.executeUpdate("INSERT INTO activities (creator, mandatory, description, difficulty, duration, started, datelimit, type, documentpath, comment, calificacionminima)"
						+ " VALUES ('"+creator+"',"+String.valueOf(mandatory)+",'"+description +"','"+ difficulty + "',"+String.valueOf(duration)+",'"+fecha.toString()+" "+horaFormateada+"','"+type+"','"+documentPath+"','"+comment+"',"+String.valueOf(calificacionMinima) +")");
				StringBuilder notin = new StringBuilder();
				notin.append("(");
				int conteo = 0;
				for (int a : this.actividades.keySet()) {
					notin.append(String.valueOf(a));
					if (conteo != this.actividades.keySet().size()-1) {
						notin.append(",");
					}
					conteo ++;
				}
				notin.append(")");
				String prueba = notin.toString();
				ResultSet resultset = globalStatement.executeQuery("SELECT * FROM Activities A WHERE A.id NOT IN "+notin.toString());
				if (resultset.next()) {
					id = resultset.getInt("id");
				}
			}
			Statement statement = this.connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM activities A WHERE A.id = "+ id+"");
			
			if (resultset.next()) {
				comment = resultset.getString("comment");
			}
			Tarea tarea = new Tarea(creator, id, mandatory, description, difficulty, duration, datelimit, states, comment);
			if (!this.actividades.containsKey(id)) {
				this.actividades.put(id, tarea);
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
		ResultSet resultset = statement.executeQuery("SELECT OA.explicacion, OA.correct, OA.enunciado, OA.idOpcion FROM optionsAsToQuestions OA WHERE OA.idPregunta = "+idPregunta+"");
		ArrayList<Opcion> opciones = new ArrayList<Opcion>();
		Opcion respuestaCorrecta = null;
		while (resultset.next()) {
			String explicacion = resultset.getString("explicacion");
			String enunciado = resultset.getString("enunciado");
			boolean correct = resultset.getBoolean("correct");
			int idOpcion = resultset.getInt("idOpcion");
			
			Opcion opcion = new Opcion(explicacion, correct, enunciado, idOpcion);
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
		ResultSet resultset = statement.executeQuery("SELECT * FROM optionsAsToQuestions OA WHERE OA.idOpcion = "+String.valueOf(idOpcion)+"");
		if (resultset.next()) {
			String explicacion = resultset.getString("explicacion");
			String enunciado = resultset.getString("enunciado");
			boolean correct = resultset.getBoolean("correct");
			//int idOpcion = resultset.getInt("idOpcion");
			
			return new Opcion(explicacion, correct, enunciado, idOpcion);
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
				String tipo = resultset.getString("tipo");
			
			if (password.equals(passwordDatabase)) {
				if (tipo.equals("estudiante")) {
					this.Session = this.estudiantes.get(login);
				}else {
					this.Session = this.profesores.get(login);
				}
				return "¡Inicio sesión correctamente '" + login +"'!";
				}
			}
		}
			
		return "Su usuario o contraseña son erróneos";
	}
	
	public Connection getConnection() {
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
				//boolean started = activity.getBoolean("started");
				LocalDateTime datelimit = activity.getTimestamp("datelimit").toLocalDateTime();
				String type = activity.getString("type");
				int calificacionMinima = activity.getInt("calificacionMinima");
				String documentPath = activity.getString("documentPath");
				//Actividad actividad = new Actividad(creator, id, mandatory, description, difficulty, duration, started, datelimit, getEstadosActividad(id));
				Actividad actividad = crearActividad(creator, id, mandatory, description, difficulty, duration, datelimit, type, documentPath, calificacionMinima, getEstadosActividad(id), false, "");
				
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
	            //boolean started = resultset.getBoolean("started");
	            LocalDateTime datelimit = resultset.getTimestamp("datelimit").toLocalDateTime();
	            int calificacionMinima = resultset.getInt("calificacionMinima");
	            String type = resultset.getString("type");
	            String documentPath = resultset.getString("documentPath");

	            actividadEncontrada = crearActividad(creator, id, mandatory, description, difficulty, duration, datelimit, type, documentPath, calificacionMinima, getEstadosActividad(id), false, "");
	            
	            
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
	/*
	public void crearNuevaPregunta(String enunciado, int idActividad, String typeQ) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(idActividad)+")");
		ResultSet resultset = statement.executeQuery("SELECT * FROM questionsAsToQuestionaries QA WHERE QA.idPregunta");
		
	}
	*/
	public int insertarPreguntaQuestionsAsToQuestionaries(int idActividad) throws SQLException {
		Statement statement = this.connection.createStatement();
		//Statement statement = this.connection.prepareStatement("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(idActividad)+")", Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(idActividad)+")", Statement.RETURN_GENERATED_KEYS);
		ResultSet resultset = statement.getGeneratedKeys();
		int idPregunta = 0;
		if (resultset.next()) {
			idPregunta = resultset.getInt(1);
			
		}
		return idPregunta;
	}
	public void insertarQuestions(int idPregunta, String typeQ, String enunciado) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate ("INSERT INTO questions (idPregunta, typeQ, \"statement\") VALUES ("+String.valueOf(idPregunta)+",'"+typeQ+"','"+enunciado+"')");
	}
	
	public int insertarOptionsAsToQuestions(int idPregunta, String enunciado,String explicacion, boolean correct) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("INSERT INTO optionsAsToQuestions (idPregunta, enunciado ,explicacion, correct) VALUES ("+String.valueOf(idPregunta)+",'"+enunciado+"','"+explicacion+"',"+String.valueOf(correct)+")", Statement.RETURN_GENERATED_KEYS);
		ResultSet resultset = statement.getGeneratedKeys();
		int idOpcion = 0;
		if (resultset.next()) {
			idOpcion = resultset.getInt(1);
		}
		return idOpcion;
	}
	public void insertarCreatedActivities(int idActividad, String nameLPAssociated) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("INSERT INTO createdActivities (id, nameLPAssociated) VALUES ("+String.valueOf(idActividad)+",'"+nameLPAssociated+"')");
	}
	public HashMap<String,LearningPath> getLPs(){
		return this.learningPathsCreados;
	}
	
	public void actualizarRespuestaUsuario(Usuario usuario, int idPregunta, String idOpcion, String respuesta, boolean yaEstabaCreado) throws SQLException {
		Statement statement = this.connection.createStatement();
		if (!yaEstabaCreado) {
			statement.executeUpdate("INSERT INTO answersActivity (idPregunta, idOpcion, usuario, respuesta) VALUES ("+String.valueOf(idPregunta)+","+idOpcion+",'"+usuario.getLogin()+"','"+respuesta+"')");
		}else {
			statement.execute("UPDATE answersActivity SET respuesta = '"+respuesta+"' WHERE idPregunta = "+String.valueOf(idPregunta)+"AND usuario = '"+usuario.getLogin()+"'");
		}
	}
	public void inscribirLP(LearningPath LP, Estudiante estudiante) throws SQLException { //Esto esta sujeto a cambios, la aplicacion no contempla por ahora que un profesor pueda inscribir un learningpath
		Statement statement = this.connection.createStatement();
		ArrayList<LearningPath> LPsInscritos = estudiante.getLPsInscritos();
		HashMap<String, Boolean> yaInscritos = new HashMap<String,Boolean>();
		for (LearningPath lp : LPsInscritos) {
			yaInscritos.put(lp.getTitulo(), true);
		}
		if (!yaInscritos.containsKey(LP.getTitulo())) {
			statement.executeUpdate("INSERT INTO CreatedLearningPaths (login, nameLP) VALUES ('"+estudiante.getLogin()+"','"+LP.getTitulo()+"')");
			LPsInscritos.add(LP);
			estudiante.setLPsInscritos(LPsInscritos);
		}
		
		
	}
	public Estudiante getEstudiante(String login) {
		if (this.estudiantes.containsKey(login)) {
			return this.estudiantes.get(login);
		}else {
			return null;
		}
	}
	public Object[] getAnswersActivity(int idPregunta, String login) throws SQLException {
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM answersActivity AA WHERE AA.idPregunta = "+String.valueOf(idPregunta)+ " AND AA.usuario = '"+login+"'");
		Object[] array = new Object[3];
		if (resultset.next()) {
			String respuesta = resultset.getString("respuesta");
			int idOpcion = resultset.getInt("idOpcion");
			array[0] = respuesta;
			array[1] = idOpcion;
			array[2] = this.crearOpcionEscogidaPorUsuario(idOpcion);
		}
		
		return array;
		
	}
	public void insertarAnswersActivity(int idPregunta, int idOpcion, String login, String respuesta) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("INSERT INTO answersActivity (idPregunta, idOpcion, usuario, respuesta) VALUES ("+String.valueOf(idPregunta)+","+String.valueOf(idOpcion)+",'"+login+"','"+respuesta+"')");
		
	}
	public void borrarACEscogida(Actividad actividad, LearningPath LP, boolean borrarDelTodo) throws SQLException {
		boolean found = false;
		int indice = 0;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		for (int i = 0;i <activitiesLP.size();i++)
		{
			if (activitiesLP.get(i).getID() == actividad.getID()) 
			{
				indice = i;
				found = true;
				break;
				
			}
		}
		if (found)
		{
			activitiesLP.remove(indice);
		}
		LP.setActivities(activitiesLP);
		this.learningPathsCreados.put(LP.getTitulo(),LP);
		//Ya se quito la actividad del learning path, no se borro la actividad por si eventualmente se quiere copiar la actividad.
		
		if (borrarDelTodo) {
			borrarActividadCreatedActivities(actividad.getID(), LP.getTitulo());
		}
	}
	
	public void borrarActividadCreatedActivities(int idActividad, String nameLP) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("DELETE FROM createdActivities CA WHERE CA.id = "+String.valueOf(idActividad)+"and CA.nameLPAssociated = '"+nameLP+"'");

	
	}
	public void setLPs(HashMap<String, LearningPath> LPs) {
		this.learningPathsCreados = LPs;
	}
	public void insertarReseñas(int idActividad, String login, String reseña) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("INSERT INTO reseñas (idActividad, login, reseña) VALUES ("+String.valueOf(idActividad)+",'"+login+"','"+reseña+"')");
	}
	
	public ArrayList<String> getReseñasActividad(int idActividad) throws SQLException{
		ArrayList<String> reseñas = new ArrayList<String>();
		Statement statement = this.connection.createStatement();
		ResultSet resultset = statement.executeQuery("SELECT * FROM reseñas WHERE idActividad = "+idActividad);
		while (resultset.next()) {
			String reseñai = resultset.getString("reseña");
			String login = resultset.getString("login");
			int id = resultset.getInt("num");
			reseñas.add(login+" : "+reseñai);
			
		}
		return reseñas;
	}
	public void modificarDescripcionActividad(Actividad ACEscogida, LearningPath LP, String newDescription) throws SQLException {
		borrarACEscogida(ACEscogida, LP, false);
		ACEscogida.setDescrition(newDescription);
		HashMap<String, LearningPath> LPsCreados = this.learningPathsCreados;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		activitiesLP.add(ACEscogida);
		LP.setActivities(activitiesLP);
		LPsCreados.put(LP.getTitulo(), LP);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("UPDATE activities SET description = '"+newDescription+"' WHERE id = "+String.valueOf(ACEscogida.getID()));
		
	}
	public void modificarObligatoriedadActividad(Actividad ACEscogida, LearningPath LP, boolean newMandatory) throws SQLException {
		borrarACEscogida(ACEscogida, LP, false);
		ACEscogida.setMandatory(newMandatory);
		HashMap<String, LearningPath> LPsCreados = this.learningPathsCreados;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		activitiesLP.add(ACEscogida);
		LP.setActivities(activitiesLP);
		LPsCreados.put(LP.getTitulo(), LP);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("UPDATE activities SET mandatory = "+String.valueOf(newMandatory)+" WHERE id = "+String.valueOf(ACEscogida.getID()));
		
	}
	
	public void modificarDificultadActividad(Actividad ACEscogida, LearningPath LP, String newDifficulty) throws SQLException {
		borrarACEscogida(ACEscogida, LP, false);
		ACEscogida.setDifficulty(newDifficulty);
		HashMap<String, LearningPath> LPsCreados = this.learningPathsCreados;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		activitiesLP.add(ACEscogida);
		LP.setActivities(activitiesLP);
		LPsCreados.put(LP.getTitulo(), LP);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("UPDATE activities SET difficulty = '"+String.valueOf(newDifficulty)+"' WHERE id = "+String.valueOf(ACEscogida.getID()));
		
	}
	
	public void modificarDuracionActividad(Actividad ACEscogida, LearningPath LP, int newDuration) throws SQLException {
		borrarACEscogida(ACEscogida, LP, false);
		ACEscogida.setDuration(newDuration);
		HashMap<String, LearningPath> LPsCreados = this.learningPathsCreados;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		activitiesLP.add(ACEscogida);
		LP.setActivities(activitiesLP);
		LPsCreados.put(LP.getTitulo(), LP);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("UPDATE activities SET duration = "+String.valueOf(newDuration)+" WHERE id = "+String.valueOf(ACEscogida.getID()));
		
	}
	
	public void modificarFechaLímiteActividad(Actividad ACEscogida, LearningPath LP, LocalDateTime newDateLimit) throws SQLException {
		borrarACEscogida(ACEscogida, LP, false);
		ACEscogida.setDateLimit(newDateLimit);
		HashMap<String, LearningPath> LPsCreados = this.learningPathsCreados;
		ArrayList<Actividad> activitiesLP = LP.getActivities();
		activitiesLP.add(ACEscogida);
		LP.setActivities(activitiesLP);
		LPsCreados.put(LP.getTitulo(), LP);
		String fecha = newDateLimit.toLocalDate().toString();
		String time = newDateLimit.toLocalTime().toString();
		if (time.length()<8) {
			time = time+":00";
		}
		Statement statement = this.connection.createStatement();
		statement.executeUpdate("UPDATE activities SET dateLimit = '"+fecha+" "+time+"' WHERE id = "+String.valueOf(ACEscogida.getID()));
		
	}
	public boolean eliminarPregunta(LearningPath LP, Actividad actividad, Pregunta pregunta, boolean esQuiz) throws SQLException
	{	
		
		ArrayList<Pregunta> preguntas = getPreguntasActividad(actividad.getID());
		
		int indice = 0;
		boolean found = false;
		for (int i = 0; i <preguntas.size();i++) 
		{
			if (preguntas.get(i).getID() == pregunta.getID()) {
				found = true;
				indice = i;
			}
		}
		preguntas.remove(indice);
		if (actividad.getClass().getSimpleName().equals("Quiz")) 
		{	
			Quiz quiz = (Quiz) actividad;
			if (quiz.getCalificacionMinima()> quiz.getPreguntas().size()-1)
			{
				
				return false;
			}
			borrarACEscogida(actividad, LP, false);
			//Quiz quiz = (Quiz) actividad;
			quiz.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(quiz);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(quiz.getID(), quiz);
			
		}
		else if (actividad.getClass().getSimpleName().equals("Examen")) 
		{	
			Examen examen = (Examen) actividad;
			if (examen.getPreguntas().size()-1 == 0)
			{
				return false;
			}
			borrarACEscogida(actividad, LP, false);
			//Examen examen = (Examen) actividad;
			examen.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(examen);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(examen.getID(), examen);
			
		}
		else if (actividad.getClass().getSimpleName().equals("Encuesta")) 
		{	
			Encuesta encuesta = (Encuesta) actividad;
			if (encuesta.getPreguntas().size()-1 == 0) 
			{
				return false;
			}
			borrarACEscogida(actividad, LP, false);
			//Encuesta encuesta = (Encuesta) actividad;
			encuesta.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(encuesta);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(encuesta.getID(), encuesta);
			
		}
		Statement statement = this.connection.createStatement();
		if (esQuiz)
		{
			statement.executeUpdate("DELETE FROM answersActivity WHERE idPregunta = "+String.valueOf(pregunta.getID()));
			statement.executeUpdate("DELETE FROM optionsAsToQuestions WHERE idPregunta = "+String.valueOf(pregunta.getID()));
			
		}
		statement.executeUpdate("DELETE FROM questions WHERE idPregunta = " + String.valueOf(pregunta.getID()));
		statement.executeUpdate("DELETE FROM questionsAsToQuestionaries WHERE idPregunta = " + String.valueOf(pregunta.getID()));
		return true;
	}
	
	public void modificarEnunciadoPregunta(int idPregunta, String newEnunciado, LearningPath LP, Actividad actividad) throws SQLException {
		Statement statement = this.connection.createStatement();
		
		
		
		statement.executeUpdate("UPDATE questions SET \"statement\" = '"+ newEnunciado +"' WHERE idPregunta = "+String.valueOf(idPregunta));
		ArrayList<Pregunta> preguntas = getPreguntasActividad(actividad.getID());
		if (actividad.getClass().getSimpleName().equals("Quiz"))
		{
			Quiz quiz = (Quiz) actividad;
			
			borrarACEscogida(actividad, LP, false);
			//Quiz quiz = (Quiz) actividad;
			quiz.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(quiz);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(quiz.getID(), quiz);
		}
		else if (actividad.getClass().getSimpleName().equals("Examen")) 
		{	
			Examen examen = (Examen) actividad;
			
			borrarACEscogida(actividad, LP, false);
			//Examen examen = (Examen) actividad;
			examen.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(examen);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(examen.getID(), examen);
			
		}
		else if (actividad.getClass().getSimpleName().equals("Encuesta")) 
		{	
			Encuesta encuesta = (Encuesta) actividad;
		
			borrarACEscogida(actividad, LP, false);
			//Encuesta encuesta = (Encuesta) actividad;
			encuesta.setPreguntas(preguntas);
			ArrayList<Actividad> activitiesLP = LP.getActivities();
			activitiesLP.add(encuesta);
			LP.setActivities(activitiesLP);
			this.learningPathsCreados.put(LP.getTitulo(), LP);
			this.actividades.put(encuesta.getID(), encuesta);
			
		}
	}
		
	
}