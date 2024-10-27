package Consola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	public static void main(String[] args) {
		String url = "jdbc:derby:DataBase;create=true";
		try {
			Connection connection = DriverManager.getConnection(url);
			System.out.println("La base de datos ha sido creada con exito");
			Statement statement = connection.createStatement();
			
			
			
			
			//INICIALIZAR BASE DE DATOS:
			
			
			//CREAR LA TABLA DE USUARIOS
			//__________________________________________________________
			//String creationUsers = "CREATE TABLE USERS (login varchar(40) PRIMARY KEY NOT NULL, password varchar(30), correo varchar(50), tipo varchar(10))";
			
			
			
			//CREAR LA TABLA DE LOS LEARNING PATHS CREADOS
			//__________________________________________________________
			//String creationCreatedLPs = "CREATE TABLE CreatedLearningPaths (id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, login varchar(40), nameLP varchar(100),"
			//		+ " FOREIGN KEY (login) REFERENCES USERS(login), FOREIGN KEY (nameLP) REFERENCES learningPaths(nameLP)	";
			
			
			
			//CREAR LA TABLA DE LOS LEARNINGPATHS
			//__________________________________________________________
			/*
			String creationLearningPaths = "CREATE TABLE learningPaths (nameLP varchar(100) PRIMARY KEY, creator varchar(40), description varchar(400), "
					+ "difficulty varchar(10), duration int, rating int, creationdate varchar(10), moddate varchar(10), FOREIGN KEY (creator) REFERENCES USERS(login)";
			
			*/
			//__________________________________________________________
			/*
			String creationActivitesAssociatedToLPs = "CREATE TABLE createdActivities (id int PRIMARY KEY, login varchar(40),  "
					+ "nombreLPAsociado varChar,FOREIGN KEY (id) REFERENCES activities(id) ,FOREIGN KEY (login) REFERENCES USERS(login), FOREIGN KEY (nombreLPAsociado) REFERENCES learningPaths(nombreLP)";	
			*/
			//__________________________________________________________
			/*
			String creationActivities = "CREATE TABLE activities (id int GENERATES ALWAYS AS IDENTITY PRIMARY KEY, creator varchar(40), mandatory boolean,"
					+ "description varchar(400), difficulty varchar(10), duration int, started boolean, dateLimit varchar(19), state varchar(500)), FOREIGN KEY (creator) REFERENCES USERS(login),"
					+ "type varchar(8)";
			*/
			//__________________________________________________________
			/*
			String creationPeopleInscribedOnActivities = "CREATE TABLE inscribedActivities (num int GENARATES ALWAYS AS IDENTITY PRIMARY KEY, login varchar(40), idActivity int,"
					+ "FOREIGN KEY (login) REFERENCES USERS(login), FOREIGN KEY (idActivity) REFERENCES activities(id))";
			*/
			//__________________________________________________________
			/*
			String creationResourcesActivities = "CREATE TABLE resourcesActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id), "
					+ "documentPath varchar(600))";
			*/
			//__________________________________________________________
			/*
			String creationHomeworkActivities = "CREATE TABLE homeworkActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id),"
					+ "comment varchar(1000))";
			*/	
			
			//__________________________________________________________
			/*
			String creationQuizActivities = "CREATE TABLE questionariesActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id),"
					+ "calificacionMinima int)";
			*/
			//__________________________________________________________
			/*
			String creationQuestionsAssociatedToQuices = "CREATE TABLE questionsAsToQuestionaries(idPregunta int GENERATES ALWAYS AS IDENTITY PRIMARY KEY, idActividad int , "
					+ "FOREIGN KEY (idActividad) REFERENCES activities(id))";
			*/
			//__________________________________________________________
			/*
			String creationQuestions = "CREATE TABLE questions (idPregunta int PRIMARY KEY, FOREIGN KEY (idPregunta) REFERENCES questionsAsToQuizes(idpregunta)), "
					+ "typeQ varchar(8), statement varchar(1000)";
			*/
			//__________________________________________________________
			/*
			String creationAssociatedOptionsToQuestions = "CREATE TABLE optionsAsToQuestions(idOpcion int GENERATES ALWAYS AS IDENTITY PRIMARY KEY, idPregunta int,"
					+ "FOREIGN KEY (idpregunta) REFERENCES questions(idPregunta), explicacion varchar(1000), correct boolean)";
			*/
			//__________________________________________________________
			/*
			String creationsAnswersAssociatedToUserAndToActivity = "CREATE TABLE answersActivity (num int GENERATES ALWAYS AS IDENTITY PRIMARY KEY, idPregunta int, "
					+ "FOREIGN KEY (idOpcion) REFERENCES optionsAsToQuestions(idOpcion), usuario varchar(40), FOREIGN KEY (usuario) REFERENCES USERS(usuario),"
					+ "respuesta varchar(1000), FOREIGN KEY (idPregunta) REFERENCES questions(idPregunta))";
			*/
			//__________________________________________________________
			
			

		
			//EXECUTION SPACE:
			//__________________________________________________________
			//INICIALIZACIÓN 
			/*
			
			statement.executeUpdate("CREATE TABLE USERS (login varchar(40) PRIMARY KEY NOT NULL, password varchar(30), correo varchar(50), tipo varchar(10))");
			
			statement.executeUpdate("CREATE TABLE learningPaths (nameLP varchar(100) PRIMARY KEY NOT NULL, creator varchar(40), description varchar(400), difficulty varchar(10), duration int, rating int, creationdate varchar(10), moddate varchar(10), FOREIGN KEY (creator) REFERENCES USERS(login))");
			
			statement.executeUpdate("CREATE TABLE CreatedLearningPaths (id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, login varchar(40), nameLP varchar(100), FOREIGN KEY (login) REFERENCES USERS(login), FOREIGN KEY (nameLP) REFERENCES learningPaths(nameLP))");
			
			statement.executeUpdate("CREATE TABLE activities (id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, creator varchar(40), mandatory boolean,description varchar(400), difficulty varchar(10), duration int, started boolean, "
					+ "dateLimit varchar(19),type varchar(8),documentPath varchar(600), comment varchar(1000), calificacionMinima int, FOREIGN KEY (creator) REFERENCES USERS(login))");
			
			statement.executeUpdate("CREATE TABLE createdActivities (id int PRIMARY KEY, nameLPAssociated varchar(100),FOREIGN KEY (id) REFERENCES activities(id) , FOREIGN KEY (nameLPAssociated) REFERENCES learningPaths(nameLP))");
			
			statement.executeUpdate("CREATE TABLE inscribedActivities (num int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, login varchar(40), idActivity int, state varchar(500),FOREIGN KEY (login) REFERENCES USERS(login), FOREIGN KEY (idActivity) REFERENCES activities(id))");
		
			//El siguiente no es necesario (NO LO BORRO POR SI ALGO)
			//statement.executeUpdate("CREATE TABLE resourcesActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id), documentPath varchar(600))");
			//El siguiente no es necesario (NO LO BORRO POR SI ALGO)
			//statement.executeUpdate("CREATE TABLE homeworkActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id),comment varchar(1000))"); 
			// siguiente no es necesario (NO LO BORRO POR SI ALGO)
			//statement.executeUpdate("CREATE TABLE questionariesActivities (id int PRIMARY KEY, FOREIGN KEY (id) REFERENCES activities(id),calificacionMinima int)");
			
			statement.executeUpdate("CREATE TABLE questionsAsToQuestionaries(idPregunta int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, idActividad int , FOREIGN KEY (idActividad) REFERENCES activities(id))");
			
			
			statement.executeUpdate("CREATE TABLE questions (idPregunta int PRIMARY KEY, FOREIGN KEY (idPregunta) REFERENCES questionsAsToQuestionaries(idpregunta), typeQ varchar(14), \"statement\" varchar(1000))");
			
			statement.executeUpdate("CREATE TABLE optionsAsToQuestions(idOpcion int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, idPregunta int,FOREIGN KEY (idpregunta) REFERENCES questions(idPregunta), explicacion varchar(1000), enunciado varchar(1000), correct boolean)");
			
			//ACÁ SE PODRIA PONER TAMBIEN RESEÑA, RATING, ETC.
			statement.executeUpdate("CREATE TABLE answersActivity (num int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, idPregunta int, idOpcion int, FOREIGN KEY (idOpcion) REFERENCES optionsAsToQuestions(idOpcion), usuario varchar(40), FOREIGN KEY (usuario) REFERENCES USERS(usuario),respuesta varchar(1000), FOREIGN KEY (idPregunta) REFERENCES questions(idPregunta))");
			
			*/
			//REINICIO DATABASE
			/*
			statement.executeUpdate("DROP TABLE answersActivity");
			statement.executeUpdate("DROP TABLE optionsAsToQuestions");
			statement.executeUpdate("DROP TABLE questions");
			statement.executeUpdate("DROP TABLE QuestionsAsToQuestionaries");
			statement.executeUpdate("DROP TABLE questionariesActivities");
			statement.executeUpdate("DROP TABLE homeworkActivities");
			statement.executeUpdate("DROP TABLE resourcesActivities");
			statement.executeUpdate("DROP TABLE inscribedActivities");
			statement.executeUpdate("DROP TABLE createdActivities");
			statement.executeUpdate("DROP TABLE activities");
			statement.executeUpdate("DROP TABLE CreatedLearningPaths");
			statement.executeUpdate("DROP TABLE learningPaths");
			statement.executeUpdate("DROP TABLE USERS");
			*/
			//EXECUTION SPACE:
			//__________________________________________________________
			
			//statement.executeUpdate("INSERT INTO learningPaths (nameLP, creator, description, difficulty, duration, rating, creationdate, moddate) VALUES ('prueba',"
			//		+ "'pedro', 'hola', 'facil', 5, 5, 'hoy', 'mañana')");
			//statement.executeUpdate("INSERT INTO createdLearningPaths (login, nameLP) VALUES ('juan', 'prueba')");
			//statement.executeUpdate("");
			//statement.executeUpdate("INSERT INTO Activities (creator, mandatory, description, difficulty, duration, datelimit, type, documentpath, comment, calificacionMinima) VALUES ('pedro', true, 'una actividad chevere','facil' , 6, '10/21/2024','quiz', 'www.google.com', 'comentario', 2)");

			//ACÁ SE PODRIA PONER TAMBIEN RESEÑA, RATING, ETC.
		
			
			ResultSet resultset = statement.executeQuery("SELECT * FROM Activities A WHERE A.description = 'una actividad chevere'");
			int id=0;
			if (resultset.next()) {
			id = resultset.getInt("id");
				
			}
			
			//statement.executeUpdate("INSERT INTO createdActivities (id, nameLPAssociated) VALUES ("+String.valueOf(id)+", 'prueba')");
			//statement.executeUpdate("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(id)+")");
			//statement.executeUpdate("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(id)+")");
			//statement.executeUpdate("INSERT INTO questionsAsToQuestionaries (idActividad) VALUES ("+String.valueOf(id)+")");
			Statement statement1 = connection.createStatement();
			ResultSet resultset1 = statement1.executeQuery("SELECT * FROM questionsAsToQuestionaries QA WHERE QA.idActividad = "+String.valueOf(id) );
			int conteo = 0;
			while (resultset1.next()) {
				
				int idPregunta = resultset1.getInt("idPregunta");
				System.out.println(idPregunta);
				
				statement.executeUpdate("INSERT INTO questions (idPregunta, typeQ, \"statement\") VALUES ("+String.valueOf(idPregunta)+", 'opcionMultiple','¿entonces 1?')");
				
				statement.executeUpdate("INSERT INTO optionsAsToQuestions (idPregunta, explicacion, enunciado, correct) VALUES ("+String.valueOf(idPregunta)+", 'porque sí','el agua no tiene color', true)");
				statement.executeUpdate("INSERT INTO optionsAsToQuestions (idPregunta, explicacion, enunciado, correct) VALUES ("+String.valueOf(idPregunta)+", 'porque sí','el agua tiene color', false)");
				statement.executeUpdate("INSERT INTO optionsAsToQuestions (idPregunta, explicacion, enunciado, correct) VALUES ("+String.valueOf(idPregunta)+", 'porque sí','el agua es verde', false)");
				statement.executeUpdate("INSERT INTO optionsAsToQuestions (idPregunta, explicacion, enunciado, correct) VALUES ("+String.valueOf(idPregunta)+", 'porque sí','hay agua en la luna', false)");
				conteo = conteo+ 1;
			}
		
			//ACÁ SE PODRIA PONER TAMBIEN RESEÑA, RATING, ETC.
			//statement.executeUpdate("CREATE TABLE answersActivity (num int GENERATED ALWAYS AS IDENTITY PRIMARY KEY, idPregunta int, idOpcion int, FOREIGN KEY (idOpcion) REFERENCES optionsAsToQuestions(idOpcion), usuario varchar(40), FOREIGN KEY (usuario) REFERENCES USERS(login),respuesta varchar(1000), FOREIGN KEY (idPregunta) REFERENCES questions(idPregunta))");
			


			//__________________________________________________________
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		

	}

}