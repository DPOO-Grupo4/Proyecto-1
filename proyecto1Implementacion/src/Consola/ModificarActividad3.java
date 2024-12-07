package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Pregunta;
import System.Sistema;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class ModificarActividad3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarActividad3 frame = new ModificarActividad3();
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
	public ModificarActividad3(Sistema sistema, Actividad actividad, LearningPath LP) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Que pregunta quieres modificar? ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(69, 11, 284, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(129, 21);
		btnRegresar.setLocation(154, 210);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
		
		if (actividad.getClass().getSimpleName().equals("Tarea")||actividad.getClass().getSimpleName().equals("ActividadRecurso")) {
			JOptionPane.showMessageDialog(this, "Este tipo de actividad no se le pueden modificar preguntas:", "", JOptionPane.ERROR_MESSAGE);
			new ModificarActividad2(sistema, actividad,LP).setVisible(true);
			dispose();
		}
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.setSize(414, 125);
		botonesPanel.setLocation(10, 56);
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(botonesPanel, BorderLayout.SOUTH);
		
		try {
			ArrayList<Pregunta> preguntas= sistema.getPreguntasActividad(actividad.getID());
			for (Pregunta pregunta:preguntas) {
				String IDString= String.valueOf(pregunta.getEnunciado());
				JButton btnActividad= new JButton(IDString); 
				botonesPanel.add(btnActividad);
				btnActividad.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new ModificarActividad4(sistema, actividad, LP, pregunta).setVisible(true);
						dispose();
						
					}
					
				});
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Hubo un error cargando las preguntas, intente mas tarde", "", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
}
