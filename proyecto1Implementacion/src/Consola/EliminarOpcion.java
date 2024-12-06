package Consola;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Opcion;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class EliminarOpcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarOpcion frame = new EliminarOpcion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param pregunta 
	 * @param LP 
	 * @param actividad 
	 * @param sistema 
	 */
	public EliminarOpcion(Sistema sistema, Actividad actividad, LearningPath LP, Pregunta pregunta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("¿Qué opción quieres eliminar? ");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(76, 24, 264, 19);
		contentPane.add(lblTitulo);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(90, 23);
		btnRegresar.setLocation(168, 212);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        });
			
		JPanel OpcionesPanel = new JPanel();
		OpcionesPanel.setBounds(23, 71, 387, 118);
		contentPane.add(OpcionesPanel);
		
		ArrayList<Opcion> opciones= ((PreguntaOpcionMultiple) pregunta).getOpciones();
		for (Opcion opcion:opciones) {
			JButton btnOpcion=new JButton(opcion.getEnunciado());
			OpcionesPanel.add(btnOpcion);
			btnOpcion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sistema.eliminarOpcion(opcion, ((PreguntaOpcionMultiple) pregunta), actividad, LP);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar la opcion", "", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}  
					
				}
				
			});
		}
		
		
	}
}
