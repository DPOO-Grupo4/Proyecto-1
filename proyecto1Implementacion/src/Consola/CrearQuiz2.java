package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Pregunta;
import System.Quiz;
import System.Sistema;
import Usuarios.Profesor;

import java.awt.Font;

public class CrearQuiz2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private ArrayList<Pregunta> camposPreguntas;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearQuiz2 frame = new CrearQuiz2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearQuiz2(Sistema sistema, LearningPath lpEscogido, String tipo, String descripcion, String dificultad, boolean mandatory, String documentPath, int duracion, int calificacionMin, LocalDateTime dateLimit, int numPreguntas) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 576);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        lblNewLabel = new JLabel("Vaya a añadir opcion para añadirle las opciones a sus preguntas(Inicio-editar Learning-AñadirOpcion)");
        lblNewLabel.setBounds(10, 43, 604, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTitulo = new JLabel("Escriba los enunciados de sus preguntas en cada espacio en blanco");
		lblTitulo.setBounds(10, 3, 485, 31);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblTitulo);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(520, 39, 85, 23);
		contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,lpEscogido);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
		
		JPanel preguntasPanel = new JPanel();
		preguntasPanel.setBounds(10, 68, 624, 461);
		contentPane.add(preguntasPanel);
		
		Quiz newActividad;
		try {
			newActividad = (Quiz) sistema.crearActividad(sistema.getSession().getLogin(), 0, mandatory, descripcion, dificultad, duracion, dateLimit, tipo, documentPath, calificacionMin, new HashMap<String, String[]>(), true,"");
		
		ArrayList<Pregunta> preguntas= new ArrayList<Pregunta>(); 
		while (preguntas.size()< numPreguntas) {
			JTextField preguntaY = new JTextField();
			getContentPane().add(preguntaY);
			preguntaY.setColumns(10);
			preguntasPanel.add(preguntaY); 
			int idPregunta = sistema.insertarPreguntaQuestionsAsToQuestionaries(newActividad.getID());
			Pregunta pregunta= sistema.crearPreguntaOpcionMultiple(idPregunta, preguntaY.getText());
			sistema.insertarQuestions(idPregunta, "opcionMultiple", preguntaY.getText());
			preguntas.add(pregunta);
		}
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(520, 9, 89, 23);
		contentPane.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newActividad.setPreguntas(preguntas);
				try {
					sistema.insertarCreatedActivities(newActividad.getID(), lpEscogido.getTitulo());
				
				ArrayList<Actividad> actividades = ((Profesor) sistema.getSession()).getActividadesCreadas();
				actividades.add(newActividad);
				((Profesor) sistema.getSession()).setActividadesCreadas(actividades);
				ArrayList<Actividad> activitiesLP = lpEscogido.getActivities();
				activitiesLP.add(newActividad);
				lpEscogido.setActivities(activitiesLP);
				HashMap<String, LearningPath> LPsCreados = sistema.getLPs();
				LPsCreados.put(lpEscogido.getTitulo(), lpEscogido);
				sistema.crearActividad(sistema.getSession().getLogin(), duracion, mandatory, descripcion, dificultad, numPreguntas, dateLimit, dificultad, documentPath, calificacionMin, null, mandatory, documentPath);
				JOptionPane.showMessageDialog(null, "Preguntas agregadas!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
			
		});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
}	
}