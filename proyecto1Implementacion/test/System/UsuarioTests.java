 package System;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.Actividad;
import System.LearningPath;
import System.Sistema;
import Usuarios.Estudiante;
import Usuarios.Profesor;
import Usuarios.Usuario;

public class UsuarioTests {
	private Profesor profesor;
	private Estudiante estudiante; 
	private Actividad actividad; 
	private ArrayList<LearningPath> learningPaths;
	private LearningPath LP;
	private LearningPath LP1; 
	private LearningPath LP2;
	private Sistema sistema;
	private Usuario usuario; 
	
	@BeforeEach
	void setUp() throws Exception{
		sistema= new Sistema();
		sistema.cargarSistema();
		learningPaths= new ArrayList<LearningPath>();
		LP= new LearningPath("Camilo", "Programación en Java", "Curso de programacion en Java", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
		LP1= new LearningPath("Tristram", "Probabilidad", "Acompañamiento en probabilidad discreta", "Avanzado", 8, 4, LocalDateTime.now() , LocalDateTime.now(), sistema);
		LP2= new LearningPath("Emilia","Economia" , "Econometria", "avanzado", 10, 3, LocalDateTime.now() , LocalDateTime.now(), sistema);
		learningPaths.add(LP);
		learningPaths.add(LP1);
		learningPaths.add(LP2);
		estudiante= new Estudiante("a.serranoa", "123456", "ameliaserrano", learningPaths);
		usuario= new Usuario("a.serranoa", "123456", "ameliaserrano");
	}
	@AfterEach
	void tearDown() throws Exception {
		LP= null;
		LP1=null;
		LP2= null;
		estudiante= null; 
	}
	
	@Test
	void testConsUsuario() {
		assertEquals("a.serranoa", usuario.getLogin());
		assertEquals("123456", usuario.getPassword());
		assertEquals("ameliaserrano", usuario.getCorreo());
		
	}
	
	@Test
	void testgetLogin () {
		assertEquals("a.serranoa", usuario.getLogin());
	}
	@Test
	void testgetPassword() {
		assertEquals("123456", usuario.getPassword());		
	}
	@Test
	void testgetCorreo() {
		assertEquals("ameliaserrano", usuario.getCorreo());
		
	}

}