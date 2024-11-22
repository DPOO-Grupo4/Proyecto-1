package SystemTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import Usuarios.*;
import System.*;


public class SistemaTest {
	
	private Sistema sistema;
	private Estudiante estudiante1;
	private Profesor profesor1;


	@BeforeEach 
	 void setUp( ) throws Exception {
		
		this.sistema = new Sistema("Prueba");
		Statement statement = this.sistema.getConnection().createStatement();
		statement.executeUpdate("INSERT INTO USERS (login, password, correo, tipo) VALUES ('vale','12345','vale@gmail.com', 'estudiante')");
		statement.executeUpdate("INSERT INTO USERS (login, password, correo, tipo) VALUES ('juan','12345','juan@gmail.com', 'profesor')");
		this.sistema.cargarSistema(); 
		
    }
	/*
	@AfterEach
	void tearDown() throws Exception {
		//Statement statement = this.sistema.getConnection().createStatement();
		
		
		
		this.sistema = null;
		this.estudiante1 = null;
		this.profesor1 = null;
		
	}
	*/
	
	@AfterEach
	void tearDown() throws Exception {
		
		Statement statement = this.sistema.getConnection().createStatement();
		statement.executeUpdate("DELETE FROM USERS WHERE login = 'vale'");	
		statement.executeUpdate("DELETE FROM USERS WHERE login = 'juan'");
		this.sistema = null;
		this.estudiante1 = null;
		this.profesor1 = null;
		
		
	}
	
	

	/*
	@Test
	void testCrearUsuario() {
		
		try {
			
			//Given
			String login = "annie";
			String password = "1234";
			String correo = "annie@gmail.com";
			String tipo = "estudiante";
			boolean bool= true;
			
			//When
			Usuario usuario1=sistema.crearUsuario(login, password, correo, tipo, bool);
			Estudiante estudiante = (Estudiante) usuario1;

			//Then
			
			assertEquals(estudiante, sistema.getEstudiante(login), "El estudiante creado no es el esperado."); //debería ser usuario1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*	
	
	//intento 2
	@Test
	void testCrearUsuario2() {
		
		try {
			Usuario usuario1=sistema.crearUsuario("vale", "12345", "vale@gmail.com", "estudiante", true); //debería ser estudiante1
			assertEquals("vale", usuario1.getLogin(), "El login no es el esperado.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	*/

	
	//GETLPSINSCRITOS ESTA EN TESTS DE ESTUDIANTE
	
	//Tmb sirve como Test Para Crear Pregunta Opcion Multiple
	
/*
@Test
	
	void testGetRespuestasActividad() {
		
		String creador = "juan";
		int id = 24;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		int idPregunta1 = 3;
		String commentPregunta1 = "default";
		int idPregunta2 = 4;
		String commentPregunta2 = "default2";
		
		ArrayList<Pregunta> listaTest = new ArrayList<Pregunta>(); 
		
		try {
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			Pregunta pregunta1=sistema.crearPreguntaOpcionMultiple(idPregunta1, commentPregunta1);	
			Pregunta pregunta2=sistema.crearPreguntaOpcionMultiple(idPregunta2, commentPregunta2);
			sistema.insertarQuestions(idPregunta1, "cerrada", commentPregunta1);
			sistema.insertarQuestions(idPregunta2, "cerrada", commentPregunta2);
			listaTest.add(pregunta1);
			listaTest.add(pregunta2);
			ArrayList<Pregunta> listaF=sistema.getPreguntasActividad(id);
			
			assertEquals(listaTest,listaF);
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
*/
	
	//@Test

	@Test
	void testCrearActividad() {
		
	}
	@Test
	void testgetActividadesCreadas() {
		Actividad actividad1= new Actividad("perensejo1", 0, false, null, null, 0, null, null);
		Actividad actividad2= new Actividad("perensejo2", 0, false, null, null, 0, null, null);
		
		
		
	}
	@Test
	void testGetEstadosACtividad() {
		LocalDate date= LocalDate.of(2024, 11, 13);
		LocalDate date1= LocalDate.of(2024, 11, 18);
		LocalTime time= LocalTime.of(13, 30, 0);
		LocalTime time1= LocalTime.of(16, 40, 23);
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("sebastian3", 123453, false, "diseño grafico6", "basico", 30, LocalDateTime.now(), states);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String[] states2= new String[3];
		states2[0]= LocalDateTime.of(date, time).format(formatter);
		states2[1]= LocalDateTime.of(date1, time1).format(formatter);
		states2[2]= "aprobado";
		states.put("pepito", states2);
		try {
			sistema.crearActividad("sebastian3", 123453, false, "diseño grafico6", "basico", 30, LocalDateTime.now(),"tarea","comentario prueba",3, states, false, "");
			HashMap<String, String[]> salida= sistema.getEstadosActividad(actividad.getID());
			assertEquals(states,salida);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testCargarActividadesLP() {
		
	}
	@Test
	void testCargarEstudiantesEnlistados() {
		
	}
	@Test
	void testgetExamenbyID() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		LocalDate date1= LocalDate.of(2024, 11, 16);
		LocalTime time= LocalTime.of(12, 30, 0);
		ArrayList<Pregunta> preguntas= new ArrayList<Pregunta>();
		HashMap<Integer, HashMap<String, Object>> respuestas= new  HashMap<Integer, HashMap<String, Object>>();
		Examen examen= new Examen("perensejo", 9876, true, "teoria basica de conjuntos", "basico", 30, LocalDateTime.of(date1, time) , states, preguntas, respuestas);
		try {
			sistema.crearActividad("perensejo", 9876, true, "teoria basica de conjuntos", "basico", 30, LocalDateTime.of(date1, time),"examen","comentario prueba",3, states, false, "");
			Examen salida= sistema.getExamenById(examen.getID());
			assertEquals(salida, examen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	void testBuscarLearningPathPorTitulo () {
		try {
			LearningPath LP3 = new LearningPath("Emilia1","Economia1" , "Econometria1", "avanzado", 10, 3, LocalDateTime.now() , LocalDateTime.now(), sistema);
			sistema.crearLearningPath("Emilia1","Economia1" , "Econometria1", "avanzado", 10, 3, LocalDateTime.now() , LocalDateTime.now(), true);
			LearningPath salida= sistema.buscarLearningPathPorTitulo(LP3.getTitulo());
			assertEquals(LP3.getTitulo(), salida);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test //TERMINARRRRR
	void testInsertarCreatedActivities () {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("sebastian2", 123452, false, "diseño grafico5", "basico", 30, LocalDateTime.now(), states);
		ArrayList<Actividad> actividadesCreadas= new ArrayList<Actividad>();
		actividadesCreadas.add(actividad);
		try {
			LearningPath LP2 = new LearningPath("Emilia","Economia" , "Econometria", "avanzado", 10, 3, LocalDateTime.now() , LocalDateTime.now(), sistema);
			sistema.insertarCreatedActivities(actividad.getID(), LP2.getTitulo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void TestActualizarRespuestayGetAnwersActivityUsuario() {
		ArrayList<LearningPath> LpsInscritos= new ArrayList<LearningPath>();
		estudiante1= new Estudiante("eddie", "diaz", "eddieDiaz", LpsInscritos);
		Opcion opcion1= new Opcion("Explicacion prueba op1", false, "Enunciado prueba op1", 1);
		ArrayList<Opcion> opciones= new ArrayList<Opcion>();
		opciones.add(opcion1);
		PreguntaOpcionMultiple pregunta3= new PreguntaOpcionMultiple(opcion1, "Pregunta de preuba", 543211, opciones);
		
		try {
			sistema.actualizarRespuestaUsuario(estudiante1, pregunta3.getID(), "543211", "NULL", false);
			Object[] esperado= new Object[3];
			esperado[0]= opcion1.getEnunciado();
			esperado[1]= opcion1.getID();
			esperado[2]=sistema.crearOpcionEscogidaPorUsuario(opcion1.getID());
			
			assertEquals(esperado,sistema.getAnswersActivity(pregunta3.getID(), estudiante1.getLogin()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	@Test
	void setLPs() {
		
	}

	@Test
	void TestInsertaryGetReseniasActividad() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("sebastian1", 123451, false, "diseño grafico4", "basico", 30, LocalDateTime.now(), states);
		try {
			sistema.insertarReseñas(actividad.getID(), "amelia1", "reseña de prueba");
			ArrayList<String> esperado= new ArrayList<String>();
			esperado.add("reseña de prueba");
			assertEquals(esperado, sistema.getReseñasActividad(actividad.getID()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void testModificarDescripcionActividad() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("sebastian", 12345, false, "diseño grafico", "basico", 30, LocalDateTime.now(), states); 
		String nuevaDescripcion = "Prueba Descripcion";
		try {
			LearningPath LP=  new LearningPath("Camilo", "Programación en Java", "Curso de programacion en Java", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
			sistema.modificarDescripcionActividad(actividad, LP, nuevaDescripcion);
			assertEquals(nuevaDescripcion, actividad.getDescripcion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testModificarObligatoriedadActividad() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("pepito", 1357, false, "diseño grafico 2", "basico", 30, LocalDateTime.now(), states); 
		boolean obligatoriedadNueva = true;
		try {
			LearningPath LP=  new LearningPath("Camilo1", "Programación en Java1", "Curso de programacion en Java1", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
			sistema.modificarObligatoriedadActividad(actividad, LP, obligatoriedadNueva);
			assertEquals(obligatoriedadNueva, actividad.getMandatory());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void testModificarDificultadActividad() { 
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("pepito1", 13571, false, "diseño grafico 1", "basico", 30, LocalDateTime.now(), states); 
		String DificultadNueva = "Avanzado";
		try {
			LearningPath LP=  new LearningPath("Camilo2", "Programación en Java2", "Curso de programacion en Java2", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
			sistema.modificarDificultadActividad(actividad, LP, DificultadNueva);
			assertEquals(DificultadNueva, actividad.getDifficulty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void testModificarDuracionActividad() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("pepito2", 13572, false, "diseño grafico 2", "basico", 30, LocalDateTime.now(), states); 
		int DuracionNueva = 20;
		try {
			LearningPath LP=  new LearningPath("Camilo3", "Programación en Java3", "Curso de programacion en Java3", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
			sistema.modificarDuracionActividad(actividad, LP, DuracionNueva);
			assertEquals(DuracionNueva, actividad.getDuration());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	void testModificarFechaLimiteActividad() {
		HashMap <String,String[]> states= new HashMap<String, String[]>();
		Actividad actividad= new Actividad("pepito3", 13572, false, "diseño grafico 3", "basico", 30, LocalDateTime.now(), states); 
		LocalDate date= LocalDate.of(2024, 12, 5);
		LocalTime time= LocalTime.of(10, 10, 10);
		LocalDateTime FechaLimiteNueva = LocalDateTime.of(date, time);
		try {
			LearningPath LP=  new LearningPath("Camilo4", "Programación en Java4", "Curso de programacion en Java4", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
			sistema.modificarFechaLímiteActividad(actividad, LP, FechaLimiteNueva);
			assertEquals(FechaLimiteNueva, actividad.getDuration());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testIniciarSesion() throws Exception {
		
		
		String sesion1 = this.sistema.iniciarSesion("vale", "12345");
		String sesion2 = this.sistema.iniciarSesion("juan", "12345");
		
		assertEquals("¡Inicio sesión correctamente '" + "vale" +"'!", sesion1);
		assertEquals("¡Inicio sesión correctamente '" + "juan" +"'!", sesion2);
		
	}
	
	/*
	@Test
	void testCrearUsuario() {
		
		try {
			
			//Given
			String login = "annie";
			String password = "1234";
			String correo = "annie@gmail.com";
			String tipo = "estudiante";
			boolean bool= true;
			
			//When
			Usuario usuario1=sistema.crearUsuario(login, password, correo, tipo, bool);
			Estudiante estudiante = (Estudiante) usuario1;

			//Then
			
			assertEquals(estudiante, sistema.getEstudiante(login), "El estudiante creado no es el esperado."); //debería ser usuario1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*	
	
	//intento 2
	@Test
	void testCrearUsuario2() {
		
		try {
			Usuario usuario1=sistema.crearUsuario("vale", "12345", "vale@gmail.com", "estudiante", true); //debería ser estudiante1
			assertEquals("vale", usuario1.getLogin(), "El login no es el esperado.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	*/
	
	
	@Test
	void testCrearLearningPath() {
		
		//Given
		String creador = "juan";
		String titulo = "Python 101";
		String descripcionGeneral = "Un curso diseñado para principiantes en Python.";
		String difficulty = "facil";
		int duration = 100;
		int rating = 5;
		LocalDateTime fechaCreacion = LocalDateTime.now();
		LocalDateTime fechaModificacion = LocalDateTime.now();
		boolean nuevo = true;
		
		
		//When
		
		try {
			LearningPath newLP = sistema.crearLearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, nuevo);
			//assertTrue(sistema.getLPs().containsKey(titulo),"El Learning Path no fue creado exitosamente.");
			assertEquals(titulo, sistema.buscarLearningPathPorTitulo(titulo),"El Learning Path no fue creado exitosamente.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Then
		
		
	}
	
	@Test
	void testValidezLogin() {
		
		//Given
		String login1 = "cata";
		String password1 = "1234";
		String correo1 = "cata@gmail.com";
		String tipo1 = "estudiante";
		boolean bool1 = true;
		
		String login2 = "majo";
		
		//When
		try {
			sistema.crearUsuario(login1, password1, correo1, tipo1, bool1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Then
		assertFalse(sistema.validezLogin(login1), "No se encontró el login deseado."); //como el login ya existe, entonces debería retornar falso para que no se vuelva a crear el usuario
		assertTrue(sistema.validezLogin(login2), "No se encontró el login deseado."); //como el login no existe, entonces debería retornar verdadero para que se cree el usuario
		Statement statement;
		try {
			statement = this.sistema.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM USERS WHERE login = 'cata'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	//GETLPSINSCRITOS ESTA EN TESTS DE ESTUDIANTE
	
	
	@Test
	
	void testGetLpsCreados() {
		
		//Given
		String creador = "juan";
		String titulo = "Java 101";
		String descripcionGeneral = "Un curso diseñado para principiantes en Java.";
		String difficulty = "facil";
		int duration = 100;
		int rating = 5;
		LocalDateTime fechaCreacion = LocalDateTime.now();
		LocalDateTime fechaModificacion = LocalDateTime.now();
		boolean nuevo = true;
		
		//When
		
		try {
			
			LearningPath newLP = sistema.crearLearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, nuevo);
			ArrayList<LearningPath> lista = sistema.getLPsCreados(creador);
			
			
			assertEquals(newLP,lista.getFirst());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
		void testGetLpsInscritos() {
			
			//Given
			String login1 = "natalia";
			String password1 = "1234";
			String correo1 = "natalia@gmail.com";
			String tipo1 = "estudiante";
			boolean bool1 = true;
			
			
			String creador = "juan";
			String titulo = "SQL 101";
			String descripcionGeneral = "Un curso diseñado para principiantes en SQL.";
			String difficulty = "facil";
			int duration = 100;
			int rating = 5;
			LocalDateTime fechaCreacion = LocalDateTime.now();
			LocalDateTime fechaModificacion = LocalDateTime.now();
			boolean nuevo = true;
			
			//When
			
			try {
				Estudiante estudiante = (Estudiante) sistema.crearUsuario(login1, password1, correo1, tipo1, bool1);
				LearningPath newLP = sistema.crearLearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, nuevo);
				sistema.inscribirLP(newLP, estudiante);
				ArrayList<LearningPath> lista = sistema.getLPsInscritos(estudiante.getLogin());
				
				assertEquals(newLP,lista.getFirst());
				Statement statement = this.sistema.getConnection().createStatement();
				statement.executeUpdate("DELETE FROM USERS WHERE login = 'natalia'");	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	@Test
	
	void testCrearUsuario() {
		String login1 = "juan pablo";
		String password1 = "1234";
		String correo1 = "juanpablo@gmail.com";
		String tipo1 = "estudiante";
		boolean bool1 = true;
		
		
		try {
			Estudiante estudiante = (Estudiante) sistema.crearUsuario(login1, password1, correo1, tipo1, bool1);
			assertEquals(estudiante, sistema.getEstudiante(login1));
			Statement statement;
			statement = this.sistema.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM USERS WHERE login = 'juan pablo'");	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//Tmb sirve como Test Para Crear Pregunta Opcion Multiple
	@Test
	
	void testGetPreguntasActividad() {
		
		String creador = "juan";
		int id = 23;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		int idPregunta1 = 1;
		String commentPregunta1 = "default";
		int idPregunta2 = 2;
		String commentPregunta2 = "default2";
		
		ArrayList<Pregunta> listaTest = new ArrayList<Pregunta>(); 
		
		try {
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			Pregunta pregunta1=sistema.crearPreguntaOpcionMultiple(idPregunta1, commentPregunta1);	
			Pregunta pregunta2=sistema.crearPreguntaOpcionMultiple(idPregunta2, commentPregunta2);
			sistema.insertarQuestions(idPregunta1, "cerrada", commentPregunta1);
			sistema.insertarQuestions(idPregunta2, "cerrada", commentPregunta2);
			listaTest.add(pregunta1);
			listaTest.add(pregunta2);
			ArrayList<Pregunta> listaF=sistema.getPreguntasActividad(id);
			
			assertEquals(listaTest,listaF);
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
/*
@Test
	
	void testGetRespuestasActividad() {
		
		String creador = "juan";
		int id = 24;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		int idPregunta1 = 3;
		String commentPregunta1 = "default";
		int idPregunta2 = 4;
		String commentPregunta2 = "default2";
		
		ArrayList<Pregunta> listaTest = new ArrayList<Pregunta>(); 
		
		try {
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			Pregunta pregunta1=sistema.crearPreguntaOpcionMultiple(idPregunta1, commentPregunta1);	
			Pregunta pregunta2=sistema.crearPreguntaOpcionMultiple(idPregunta2, commentPregunta2);
			sistema.insertarQuestions(idPregunta1, "cerrada", commentPregunta1);
			sistema.insertarQuestions(idPregunta2, "cerrada", commentPregunta2);
			listaTest.add(pregunta1);
			listaTest.add(pregunta2);
			ArrayList<Pregunta> listaF=sistema.getPreguntasActividad(id);
			
			assertEquals(listaTest,listaF);
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
*/
	
	@Test
	
	void testGetConnection() throws SQLException {

		Connection connection;
		connection = this.sistema.getConnection();
		assertEquals(connection, sistema.getConnection());
		
		
	}
	
	@Test
	void testGetSession() throws SQLException {

		Usuario session;
		sistema.iniciarSesion("vale", "12345");
		Usuario esperado = sistema.getSession();
		assertEquals(esperado, sistema.getSession(), "No fue el usuario esperado");
		
		
	}
	/*	
	@Test
	
	void testActualizarEstados() {
		
		sistema.
		
		sistema.actualizarEstado(estudiante1, null, null, null, false, false);
		
		
		
	}
*/
	
	
	@Test
	void testBuscarEstudiantePorLogin(){
		String login = "alejandro";
		String password = "1234";
		String correo = "alejandro@gmail.com";
		String tipo = "estudiante";
		boolean bool= true;
		
		//When
		Usuario usuario1;
		try {
			usuario1 = sistema.crearUsuario(login, password, correo, tipo, bool);
			Estudiante estudiante = (Estudiante) usuario1;
			
			assertEquals(estudiante, sistema.buscarEstudiantePorLogin(login), "No se encontró el login.");
			Statement statement = this.sistema.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM USERS WHERE login = 'alejandro'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	void testInsertarPreguntaQuestionsAsToQuestionnaries() {
		String creador = "juan";
		int id = 26;
		boolean mandatory = true;
		String descripcion = "Encuesta Default Para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "encuesta";
		String documentPath= "";
		int calificacionMinima = 0;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		try {
			sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			int retorno = sistema.insertarPreguntaQuestionsAsToQuestionaries(id);
			assertEquals(1, retorno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	
	void testInsertarOptionsAsToQuestions() {
		
		int idPregunta = 1;
		String typeq="opcion multiple";
		String enunciado = "El cielo es azul.";
		String explicacion = "Está bien porque el cielo es azul.";
		
		try {
			int retorno = sistema.insertarOptionsAsToQuestions(idPregunta, enunciado, explicacion, true);
			assertEquals(1,retorno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	@Test
	void testInsertarQuestions() {
		
		
		
	}
	*/
	
	@Test
	
	void testInscribirLP() {
		String login = "simon";
		String password = "1234";
		String correo = "simon@gmail.com";
		String tipo = "estudiante";
		boolean bool= true;
		
		
		String creador = "juan";
		String titulo = "C++ 101";
		String descripcionGeneral = "Un curso diseñado para principiantes en C++.";
		String difficulty = "facil";
		int duration = 100;
		int rating = 5;
		LocalDateTime fechaCreacion = LocalDateTime.now();
		LocalDateTime fechaModificacion = LocalDateTime.now();
		boolean nuevo = true;
		
		//When
		Usuario usuario1;
		try {
			usuario1 = sistema.crearUsuario(login, password, correo, tipo, bool);
			Estudiante estudiante = (Estudiante) usuario1;
			LearningPath newLP=sistema.crearLearningPath(creador, titulo, descripcionGeneral, difficulty, duration, rating, fechaCreacion, fechaModificacion, nuevo);
			
			sistema.inscribirLP(newLP, estudiante);
			ArrayList<LearningPath>lista=sistema.getLPsInscritos(login);
			assertEquals(newLP,lista.getFirst());
			
			
			Statement statement = this.sistema.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM LEARNINGPATHS WHERE titulo = 'C++ 101'");
			statement.executeUpdate("DELETE FROM CREATEDLEARNINGPATHS WHERE login = 'simon'");
			statement.executeUpdate("DELETE FROM USERS WHERE login = 'simon'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	
	@Test
	
	void testGetEstudiante() {
		String login = "harry";
		String password = "1234";
		String correo = "harry@gmail.com";
		String tipo = "estudiante";
		boolean bool= true;
		Usuario usuario1;
		try {
			usuario1 = sistema.crearUsuario(login, password, correo, tipo, bool);
			Estudiante estudiante = (Estudiante) usuario1;
			
			Estudiante est = sistema.getEstudiante(login);
			assertEquals(estudiante, est);
			Statement statement = this.sistema.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM USERS WHERE login = 'harry'");	
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
		
		
		
	}
	
	
	@Test
	void testBorrarACEscogida() {
		

		String creador = "juan";
		int id = 27;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		
		String creador2 = "juan";
		String titulo2 = "Java Swing 101";
		String descripcionGeneral2 = "Un curso diseñado para principiantes en Java Swing.";
		String difficulty2 = "facil";
		int duration2 = 100;
		int rating2 = 5;
		LocalDateTime fechaCreacion2 = LocalDateTime.now();
		LocalDateTime fechaModificacion2 = LocalDateTime.now();
		boolean nuevo2 = true;
		
		
		try {
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			LearningPath newLP=sistema.crearLearningPath(creador2, titulo2, descripcionGeneral2, difficulty2, duration2, rating2, fechaCreacion2, fechaModificacion2, nuevo2);
			sistema.insertarCreatedActivities(id, newLP.getTitulo());
			sistema.borrarACEscogida(quiz1, newLP, true);
			ArrayList<Actividad> lista=sistema.getActividadesCreadas(creador);
			//assertTrue(lista.isEmpty());
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testEliminarPregunta() {
		
		String creador2 = "juan";
		String titulo2 = "Dibujar 101";
		String descripcionGeneral2 = "Un curso diseñado para principiantes en dibujo.";
		String difficulty2 = "facil";
		int duration2 = 100;
		int rating2 = 5;
		LocalDateTime fechaCreacion2 = LocalDateTime.now();
		LocalDateTime fechaModificacion2 = LocalDateTime.now();
		boolean nuevo2 = true;
		
		
		
		String creador = "juan";
		int id = 30;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		int idPregunta1 = 7;
		String commentPregunta1 = "default";
		int idPregunta2 = 8;
		String commentPregunta2 = "default2";
		
		ArrayList<Pregunta> listaTest = new ArrayList<Pregunta>(); 
		
		try {
			LearningPath newLP=sistema.crearLearningPath(creador2, titulo2, descripcionGeneral2, difficulty2, duration2, rating2, fechaCreacion2, fechaModificacion2, nuevo2);
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			Pregunta pregunta1=sistema.crearPreguntaOpcionMultiple(idPregunta1, commentPregunta1);	
			Pregunta pregunta2=sistema.crearPreguntaOpcionMultiple(idPregunta2, commentPregunta2);
			sistema.insertarQuestions(idPregunta1, "cerrada", commentPregunta1);
			sistema.insertarQuestions(idPregunta2, "cerrada", commentPregunta2);
			listaTest.add(pregunta1);
			listaTest.add(pregunta2);
			sistema.insertarCreatedActivities(id, newLP.getTitulo());
			ArrayList<Pregunta> listaF=sistema.getPreguntasActividad(id);
			//assertTrue(sistema.eliminarPregunta(newLP, quiz1, pregunta1, true));
			//assertTrue(sistema.eliminarPregunta(newLP, quiz1, pregunta2, true));
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	@Test
	void testModificarEnunciadoPregunta() {
		
		String creador2 = "juan";
		String titulo2 = "Cine 101";
		String descripcionGeneral2 = "Un curso diseñado para principiantes en cine.";
		String difficulty2 = "facil";
		int duration2 = 100;
		int rating2 = 5;
		LocalDateTime fechaCreacion2 = LocalDateTime.now();
		LocalDateTime fechaModificacion2 = LocalDateTime.now();
		boolean nuevo2 = true;
		
		
		
		String creador = "juan";
		int id = 31;
		boolean mandatory = true;
		String descripcion = "Descripcion Default para Tests";
		String difficulty = "principiante";
		int duration = 140;
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime datelimit = currentDate.plusHours(2);
		String type = "quiz";
		String documentPath= "";
		int calificacionMinima = 3;
		HashMap<String,String[]> states = new HashMap<String,String[]>();
		boolean nuevo = true;
		String comment = "no comment";
		
		
		int idPregunta1 = 9;
		String commentPregunta1 = "default";
		int idPregunta2 = 10;
		String commentPregunta2 = "default2";
		
		ArrayList<Pregunta> listaTest = new ArrayList<Pregunta>(); 
		String nuevoEnunciado = "¿El cielo es verde?";
		
		try {
			LearningPath newLP=sistema.crearLearningPath(creador2, titulo2, descripcionGeneral2, difficulty2, duration2, rating2, fechaCreacion2, fechaModificacion2, nuevo2);
			Quiz quiz1 = (Quiz) sistema.crearActividad(creador, id, mandatory, descripcion, difficulty, duration, datelimit, type, documentPath, calificacionMinima, states, nuevo, comment);
			Pregunta pregunta1=sistema.crearPreguntaOpcionMultiple(idPregunta1, commentPregunta1);	
			Pregunta pregunta2=sistema.crearPreguntaOpcionMultiple(idPregunta2, commentPregunta2);
			sistema.insertarQuestions(idPregunta1, "cerrada", commentPregunta1);
			sistema.insertarQuestions(idPregunta2, "cerrada", commentPregunta2);
			listaTest.add(pregunta1);
			listaTest.add(pregunta2);
			sistema.insertarCreatedActivities(id, newLP.getTitulo());
			ArrayList<Pregunta> listaF=sistema.getPreguntasActividad(id);
			sistema.modificarEnunciadoPregunta(idPregunta1, nuevoEnunciado, newLP, quiz1);
			
			assertEquals(nuevoEnunciado, pregunta1.getEnunciado(), "No se cambió el enunciado.");
			
			
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
			
		
	



	@Test
	void TestInsertaryGetAnwersActivity() {
		ArrayList<LearningPath> LpsInscritos= new ArrayList<LearningPath>();
		estudiante1= new Estudiante("eddie", "diaz", "eddieDiaz", LpsInscritos);
		Opcion opcion1= new Opcion("Explicacion prueba op1", false, "Enunciado prueba op1", 1);
		ArrayList<Opcion> opciones= new ArrayList<Opcion>();
		opciones.add(opcion1);
		PreguntaOpcionMultiple pregunta3= new PreguntaOpcionMultiple(opcion1, "Pregunta de preuba", 543211, opciones);
		
		try {
			sistema.actualizarRespuestaUsuario(estudiante1, pregunta3.getID(), "543211", "NULL", false);
			Object[] esperado= new Object[3];
			esperado[0]= opcion1.getEnunciado();
			esperado[1]= opcion1.getID();
			esperado[2]=sistema.crearOpcionEscogidaPorUsuario(opcion1.getID());
			
			assertEquals(esperado,sistema.getAnswersActivity(pregunta3.getID(), estudiante1.getLogin()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	

}
		
	}


