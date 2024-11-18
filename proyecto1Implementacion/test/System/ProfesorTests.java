package System;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.Actividad;
import System.LearningPath;
import System.Sistema;
import Usuarios.Estudiante;
import Usuarios.Profesor;

public class ProfesorTests {
	private Profesor profesor;
	private Estudiante estudiante; 
	private Actividad actividad1;
	private Actividad actividad2; 
	private LearningPath LP;
	private LearningPath LP1;
	private LearningPath LP2;
	private ArrayList<LearningPath> LPsCreados;
	private ArrayList<LearningPath> LPsCreadosNuevo;
	private ArrayList<Actividad> actividadesCreadas; 
	private ArrayList<Actividad> actividadesCreadasNuevo;
	private Sistema sistema;
	private HashMap<String, String[]> states; 
	
	@BeforeEach
	void setUp() throws Exception{
		sistema= new Sistema();
		sistema.cargarSistema();
		LPsCreados= new ArrayList<LearningPath>();
		LP= new LearningPath("Camilo", "Programación en Java", "Curso de programacion en Java", "Intermedio", 7, 5, LocalDateTime.now() , LocalDateTime.now(), sistema );
		LP1= new LearningPath("Camilo", "Probabilidad", "Acompañamiento en probabilidad discreta", "Avanzado", 8, 4, LocalDateTime.now() , LocalDateTime.now(), sistema);
		LP2= new LearningPath("Camilo","Economia" , "Econometria", "avanzado", 10, 3, LocalDateTime.now() , LocalDateTime.now(), sistema);
		LPsCreados.add(LP);
		LPsCreados.add(LP1);
		actividadesCreadas= new ArrayList<Actividad>();
		states= new HashMap<String, String[]>();
		actividad1= new Actividad("Camilo", 1234, false, "Taller hamburguesas", "Avanzado", 60, LocalDateTime.now(), states);
		actividadesCreadas.add(actividad1);
		actividad2= new Actividad("Camilo", 1234, false, "Taller hamburguesas", "Avanzado", 60, LocalDateTime.now(), states);
		actividadesCreadasNuevo= new ArrayList<Actividad>();
		actividadesCreadasNuevo.add(actividad1);
		actividadesCreadasNuevo.add(actividad2);
		LPsCreadosNuevo= new ArrayList<LearningPath>();
		LPsCreadosNuevo.add(LP);
		LPsCreadosNuevo.add(LP1);
		LPsCreadosNuevo.add(LP2);
		
		profesor= new Profesor("Camilo", "246810", "c.orca12", actividadesCreadas, LPsCreados);
		
		
	}
	@AfterEach
	void tearDown() throws Exception {
		LP= null; 
		LP1= null;
		LP2= null; 
		LPsCreados= null;
		actividadesCreadas= null; 
		actividadesCreadasNuevo= null; 
		actividad1=null;
		actividad2= null; 
		
	}
	
	@Test
	void testAnadirLP () {
		profesor.añadirLearningPath(LP2);
		assertEquals(LPsCreadosNuevo, LPsCreados);
	}
	@Test
	void testCrearActividad() {
		profesor.crearActividad(actividad2);
		assertEquals(actividadesCreadasNuevo, actividadesCreadas);
	}
	@Test
	void tesgettLPs() {
		assertEquals(profesor.getLearningPathsCreados(),LPsCreados);
	}
	@Test
	void testgetActividadesCreadas() {
		assertEquals(profesor.getActividadesCreadas(),actividadesCreadas);
	}
	@Test
	void testSetActividadesCreadas() {
		profesor.setActividadesCreadas(actividadesCreadasNuevo);
		assertEquals(actividadesCreadasNuevo,profesor.getActividadesCreadas());
	}
	@Test
	void setLPsCreados() {
		profesor.setLPsCreados(LPsCreadosNuevo);
		assertEquals(LPsCreadosNuevo, profesor.getLearningPathsCreados());
	}


}
