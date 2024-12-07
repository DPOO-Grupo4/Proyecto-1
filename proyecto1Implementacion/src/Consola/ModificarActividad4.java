package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Sistema;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ModificarActividad4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarActividad4 frame = new ModificarActividad4();
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
	public ModificarActividad4(Sistema sistema, Actividad actividad, LearningPath LP, Pregunta pregunta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(90, 23);
		btnRegresar.setLocation(170, 227);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
        JLabel lbltitulo = new JLabel("¿Qué quieres hacer?");
        lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltitulo.setBounds(116, 30, 193, 19);
        contentPane.add(lbltitulo);
        
        JButton btnModificaEnunciado = new JButton("Modificar Enunciado");
        btnModificaEnunciado.setLocation(29, 160);
        btnModificaEnunciado.setSize(375, 29);
        contentPane.add(btnModificaEnunciado);
        btnModificaEnunciado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ModificarEnunciado(sistema, actividad, LP, pregunta).setVisible(true);
				dispose();
				
			}
        	
        });
        
        JButton btnEliminarOpcion = new JButton("Eliminar Opcion");
        btnEliminarOpcion.setLocation(29, 80);
        btnEliminarOpcion.setSize(375, 29);
        contentPane.add(btnEliminarOpcion);
        btnEliminarOpcion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pregunta instanceof PreguntaOpcionMultiple) {
					new EliminarOpcion(sistema, actividad, LP, pregunta).setVisible(true);
					dispose();
					
			}
				else {
					JOptionPane.showMessageDialog(null, "No puedes agregar opciones a una pregunta abierta");
				}
			}
        	
        });
        
        JButton btnAnadirOpcion = new JButton("AnadirOpcion");
        btnAnadirOpcion.setLocation(29, 120);
        btnAnadirOpcion.setSize(375, 29);
        contentPane.add(btnAnadirOpcion);
        btnAnadirOpcion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pregunta instanceof PreguntaOpcionMultiple) {
					if (((PreguntaOpcionMultiple) pregunta).getOpciones().size()<4) {
						new AnadirOpcion(sistema, actividad, LP, pregunta).setVisible(true);
						dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No puedes agregar opciones a una pregunta abierta o tu pregunta ya tiene 4 opciones");
				}
				
			}
        	
        });
        
        
        
        
	}

}
