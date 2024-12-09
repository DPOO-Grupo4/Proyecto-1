package Consola;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;

public class CrearEncuesta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumPreguntas;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearEncuesta frame = new CrearEncuesta();
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
	public CrearEncuesta(Sistema sistema, LearningPath lpEscogido, String tipo, String descripcion, String dificultad, boolean mandatory, String documentPath, int duracion, int calificacionMin, LocalDateTime dateLimit) {
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
				new CrearEncuesta2(sistema, lpEscogido, tipo, descripcion,  dificultad, mandatory, documentPath,  duracion, calificacionMin, dateLimit,numPreguntas).setVisible(true);
				dispose();
			}
			
		});		
		
	}
}
