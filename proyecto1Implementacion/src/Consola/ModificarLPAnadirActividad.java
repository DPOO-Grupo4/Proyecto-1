package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import System.*;
import Usuarios.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ModificarLPAnadirActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDificultad;
	private JTextField textFieldObligatoria;
	private JTextField textFieldFechaLimite;
	private JTextField textFieldComentario;
	private JTextField textFieldCalificacionMinima;
	private JTextField textFieldDuracion;
	private JTextField textFieldHipervinculo;

	/**
	 * Create the frame.
	 */
	public ModificarLPAnadirActividad(Sistema sistema, LearningPath lpEscogido) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Añadir Actividad a: " + lpEscogido);
		lblTitulo.setBounds(246, 6, 147, 16);
		
		JLabel lblDescripcion = new JLabel("Ingrese la descripción: ");
		
		
		
		
		
		lblDescripcion.setBounds(11, 33, 170, 14);
		lblDescripcion.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(11, 59, 141, 26);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblDificultad = new JLabel("Dificultad (fácil, media, difícil): ");
		lblDificultad.setBounds(19, 115, 205, 14);
		lblDificultad.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		textFieldDificultad = new JTextField();
		textFieldDificultad.setBounds(11, 142, 142, 26);
		textFieldDificultad.setColumns(10);
		
		JLabel lblObligatoria = new JLabel("¿Es obligatoria? (true, false):");
		lblObligatoria.setBounds(319, 33, 215, 14);
		lblObligatoria.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		textFieldObligatoria = new JTextField();
		textFieldObligatoria.setBounds(319, 59, 142, 26);
		textFieldObligatoria.setColumns(10);
		
		JLabel lblFechaLimite = new JLabel("FECHA LÍMITE : (yyyy-MM-dd HH:mm:ss)");
		lblFechaLimite.setBounds(319, 115, 273, 14);
		lblFechaLimite.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		textFieldFechaLimite = new JTextField();
		
		
		JLabel lblDuracion = new JLabel("Duración (minutos):");
		lblDuracion.setBounds(21, 180, 135, 16);
		contentPane.add(lblDuracion);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setBounds(22, 199, 130, 26);
		contentPane.add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JLabel lblHiperVinc = new JLabel("Si va a crear un recurso, digite a continuación el hipervinculo:");
		lblHiperVinc.setBounds(21, 313, 533, 16);
		contentPane.add(lblHiperVinc);
		
		textFieldHipervinculo = new JTextField();
		textFieldHipervinculo.setBounds(21, 341, 534, 26);
		contentPane.add(textFieldHipervinculo);
		textFieldHipervinculo.setColumns(10);
		
		
		textFieldFechaLimite.setBounds(319, 141, 178, 26);
		textFieldFechaLimite.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblTitulo);
		contentPane.add(lblObligatoria);
		contentPane.add(textFieldFechaLimite);
		contentPane.add(textFieldObligatoria);
		contentPane.add(lblFechaLimite);
		contentPane.add(lblDescripcion);
		contentPane.add(textFieldDescripcion);
		contentPane.add(lblDificultad);
		contentPane.add(textFieldDificultad);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						EditarLearningPathTrans ELPT = new EditarLearningPathTrans(sistema, lpEscogido);
			            ELPT.setVisible(true);
						dispose();
						
					
			}
		});
		btnRegresar.setBounds(262, 456, 117, 29);
		contentPane.add(btnRegresar);
		
		JButton btnRecurso = new JButton("Crear Recurso");
		btnRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = "recurso";
				String descripcion  = textFieldDescripcion.getText();
				String dificultad = textFieldDificultad.getText();
				String obligatoriaString = textFieldObligatoria.getText();
				boolean obligatoria = false;
				
				if (obligatoriaString == "true") {
					obligatoria = true;
				}
				else if(obligatoriaString == "false") {
					obligatoria = false;
				} 
				else {
					JOptionPane.showMessageDialog(null, "Digite true o false para identificar si la actividad es obligaoria.");
				}
				String stringFechaLim = textFieldFechaLimite.getText();
				String duracionString = textFieldDuracion.getText();
				int duracion = Integer.parseInt(duracionString);
				String documentPath = textFieldHipervinculo.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateLimit = LocalDateTime.parse(stringFechaLim, formatter);
				if (dateLimit.isBefore(LocalDateTime.now())) {
					JOptionPane.showMessageDialog(null,"La fecha límite no puede ser antes que la fecha actual.");
					
				}
				
				ActividadRecurso newActividad;
				try {
					newActividad = (ActividadRecurso) sistema.crearActividad(sistema.getSession().getLogin(), 0, obligatoria, descripcion, dificultad, duracion, dateLimit, tipo, documentPath, 0, new HashMap<String, String[]>(), true, "");
					sistema.insertarCreatedActivities(newActividad.getID(), lpEscogido.getTitulo());
					ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
					actividades.add(newActividad);
					((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
					ArrayList<Actividad> activitiesLP = lpEscogido.getActivities();
					activitiesLP.add(newActividad);
					lpEscogido.setActivities(activitiesLP);
					HashMap<String, LearningPath> LPsCreados = sistema.getLPs();
					LPsCreados.put(lpEscogido.getTitulo(), lpEscogido);
					JOptionPane.showMessageDialog(null,"Se creó la Actividad Recurso.");
					EditarLearningPathTrans ELPT = new EditarLearningPathTrans(sistema, lpEscogido);
		            ELPT.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"¡Oops! Hubo un error al crear la actividad.");
				}
				
				
			}
		});
		btnRecurso.setBounds(4, 400, 117, 29);
		contentPane.add(btnRecurso);
		
		JButton btnTarea = new JButton("Crear Tarea");
		btnTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = "tarea";
				String descripcion  = textFieldDescripcion.getText();
				String dificultad = textFieldDificultad.getText();
				String obligatoriaString = textFieldObligatoria.getText();
				boolean obligatoria = false;
				
				if (obligatoriaString == "true") {
					obligatoria = true;
				}
				else if(obligatoriaString == "false") {
					obligatoria = false;
				} 
				else {
					JOptionPane.showMessageDialog(null, "Digite true o false para identificar si la actividad es obligaoria.");
				}
				String stringFechaLim = textFieldFechaLimite.getText();
				String duracionString = textFieldDuracion.getText();
				int duracion = Integer.parseInt(duracionString);
				String comentario = textFieldComentario.getText();
				String documentPath = "";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateLimit = LocalDateTime.parse(stringFechaLim, formatter);
				if (dateLimit.isBefore(LocalDateTime.now())) {
					JOptionPane.showMessageDialog(null,"La fecha límite no puede ser antes que la fecha actual.");
					
				}
				
				
				try {
					Tarea newActividad = (Tarea) sistema.crearActividad(sistema.getSession().getLogin(), 0, obligatoria, descripcion, dificultad, duracion, dateLimit, tipo, documentPath, 0, new HashMap<String, String[]>(), true, comentario);
					sistema.insertarCreatedActivities(newActividad.getID(), lpEscogido.getTitulo());
					ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
					actividades.add(newActividad);
					((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
					ArrayList<Actividad> activitiesLP = lpEscogido.getActivities();
					activitiesLP.add(newActividad);
					lpEscogido.setActivities(activitiesLP);
					HashMap<String, LearningPath> LPsCreados = sistema.getLPs();
					LPsCreados.put(lpEscogido.getTitulo(), lpEscogido);
					JOptionPane.showMessageDialog(null,"Se creó la Actividad Tarea.");
					EditarLearningPathTrans ELPT = new EditarLearningPathTrans(sistema, lpEscogido);
		            ELPT.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"¡Oops! Hubo un error al crear la actividad.");
				}
				
				
			}
		});
		
		JLabel lblComentarioTarea = new JLabel("Si va a crear una tarea, ingrese el comentario que desea añadir a la tarea :");
		lblComentarioTarea.setBounds(19, 237, 480, 16);
		contentPane.add(lblComentarioTarea);
		
		textFieldComentario = new JTextField();
		textFieldComentario.setBounds(16, 265, 539, 26);
		contentPane.add(textFieldComentario);
		textFieldComentario.setColumns(10);
		
		btnTarea.setBounds(133, 400, 117, 29);
		contentPane.add(btnTarea);
		
		JButton btnQuiz = new JButton("Crear Quiz");
		btnQuiz.setBounds(262, 400, 117, 29);
		contentPane.add(btnQuiz);
		btnQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = "quiz";
				String documentPath = "";
				String descripcion  = textFieldDescripcion.getText();
				String dificultad = textFieldDificultad.getText();
				String obligatoriaString = textFieldObligatoria.getText();
				String stringFechaLim = textFieldFechaLimite.getText();
				String duracionString = textFieldDuracion.getText();
				int duracion = Integer.parseInt(duracionString);
				int calificacionMin = Integer.parseInt(textFieldCalificacionMinima.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateLimit = LocalDateTime.parse(stringFechaLim, formatter);
				if (dateLimit.isBefore(LocalDateTime.now())) {
					JOptionPane.showMessageDialog(null,"La fecha límite no puede ser antes que la fecha actual.");
				}
				boolean obligatoria = false;
				
				if (obligatoriaString == "true") {
					obligatoria = true;
				}
				else if(obligatoriaString == "false") {
					obligatoria = false;
				} 
				else {
					JOptionPane.showMessageDialog(null, "Digite true o false para identificar si la actividad es obligaoria.");
				}
				
				CrearQuiz CQ = new CrearQuiz(sistema, lpEscogido, tipo, descripcion, dificultad, obligatoria, documentPath, duracion, calificacionMin, dateLimit);
	            CQ.setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnExamen = new JButton("Crear Examen");
		btnExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = "examen";
				String documentPath = "";
				String descripcion  = textFieldDescripcion.getText();
				String dificultad = textFieldDificultad.getText();
				String obligatoriaString = textFieldObligatoria.getText();
				String stringFechaLim = textFieldFechaLimite.getText();
				String duracionString = textFieldDuracion.getText();
				int calificacionMin = Integer.parseInt(textFieldCalificacionMinima.getText());
				int duracion = Integer.parseInt(duracionString);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateLimit = LocalDateTime.parse(stringFechaLim, formatter);
				if (dateLimit.isBefore(LocalDateTime.now())) {
					JOptionPane.showMessageDialog(null,"La fecha límite no puede ser antes que la fecha actual.");
					
				}
				boolean obligatoria = false;
				
				if (obligatoriaString == "true") {
					obligatoria = true;
				}
				else if(obligatoriaString == "false") {
					obligatoria = false;
				} 
				else {
					JOptionPane.showMessageDialog(null, "Digite true o false para identificar si la actividad es obligaoria.");
				}
				CrearExamen CE = new CrearExamen(sistema, lpEscogido, tipo, descripcion, dificultad, obligatoria, documentPath, duracion, calificacionMin, dateLimit);
	            CE.setVisible(true);
				dispose();
				
				
			}
		});
		btnExamen.setBounds(401, 400, 117, 29);
		contentPane.add(btnExamen);
		
		
		JButton btnEncuesta = new JButton("Crear Encuesta");
		btnEncuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = "encuesta";
				String descripcion  = textFieldDescripcion.getText();
				String dificultad = textFieldDificultad.getText();
				String obligatoriaString = textFieldObligatoria.getText();
				String stringFechaLim = textFieldFechaLimite.getText();
				String duracionString = textFieldDuracion.getText();
				String documentPath = "";
				int calificacionMinima = 0;
				int duracion = Integer.parseInt(duracionString);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateLimit = LocalDateTime.parse(stringFechaLim, formatter);
				if (dateLimit.isBefore(LocalDateTime.now())) {
					JOptionPane.showMessageDialog(null,"La fecha límite no puede ser antes que la fecha actual.");
					
				}
				boolean obligatoria = false;
				
				if (obligatoriaString == "true") {
					obligatoria = true;
				}
				else if(obligatoriaString == "false") {
					obligatoria = false;
				} 
				else {
					JOptionPane.showMessageDialog(null, "Digite true o false para identificar si la actividad es obligaoria.");
				}
				
				CrearEncuesta CE2 = new CrearEncuesta(sistema, lpEscogido, tipo, descripcion, dificultad, obligatoria, documentPath, duracion, calificacionMinima, dateLimit);
	            CE2.setVisible(true);
				dispose();
				
			}
		});
		btnEncuesta.setBounds(530, 400, 117, 29);
		contentPane.add(btnEncuesta);
		
		JLabel lblCalificacionMinima = new JLabel("Si va a crear un quiz o examen, digite la calificacion minima: ");
		lblCalificacionMinima.setBounds(309, 180, 496, 16);
		contentPane.add(lblCalificacionMinima);
		
		textFieldCalificacionMinima = new JTextField();
		textFieldCalificacionMinima.setBounds(319, 199, 170, 26);
		contentPane.add(textFieldCalificacionMinima);
		textFieldCalificacionMinima.setColumns(10);
		
		
		
		
		
		
	}
}