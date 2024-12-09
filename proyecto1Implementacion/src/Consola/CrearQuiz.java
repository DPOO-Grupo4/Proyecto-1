 package Consola;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import System.Sistema;
import System.LearningPath;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Quiz;

public class CrearQuiz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumPreguntas;
	private JTextField txtCalifMinima;
	private JPanel preguntasPanel; 
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearQuiz frame = new CrearQuiz();
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
	public CrearQuiz(Sistema sistema, LearningPath lpEscogido, String tipo, String descripcion, String dificultad, boolean mandatory, String documentPath, int duracion, int calificacionMin, LocalDateTime dateLimit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		getContentPane().setLayout(null); 
		
		JLabel lblnumPreguntas = new JLabel("Digite el numero de preguntas que quiere agregar:");
		lblnumPreguntas.setBounds(10, 44, 355, 14);
		getContentPane().add(lblnumPreguntas);
		
		txtNumPreguntas = new JTextField();
		txtNumPreguntas.setBounds(10, 60, 273, 20);
		getContentPane().add(txtNumPreguntas);
		txtNumPreguntas.setColumns(10);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(309, 59, 89, 23);
		getContentPane().add(btnContinuar);
		btnContinuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int numPreguntas= Integer.parseInt(txtNumPreguntas.getText());
				new CrearQuiz2(sistema, lpEscogido, tipo, descripcion,  dificultad, mandatory, documentPath,  duracion, calificacionMin, dateLimit,numPreguntas).setVisible(true);
				dispose();
			}
			
		});		
		
	}
}

 