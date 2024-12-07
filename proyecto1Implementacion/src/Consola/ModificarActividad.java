package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

public class ModificarActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarActividad frame = new ModificarActividad();
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
	public ModificarActividad(Sistema sistema, LearningPath LP) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("¿Qué actividad desea modificar?");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(62, 11, 311, 31);
		contentPane.add(lblTitulo);
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.setBounds(10, 53, 414, 118);
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(botonesPanel, BorderLayout.CENTER);
		
		ArrayList<Actividad> actividades= LP.getActivities(); 
		for (Actividad actividad: actividades) {
			String IDString= String.valueOf(actividad.getID());
			JButton btnActividad= new JButton(IDString);
			botonesPanel.add(btnActividad);
			btnActividad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new ModificarActividad2(sistema, actividad, LP).setVisible(true);
					dispose();
					
				}
				
			});
		
		
	}
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setLocation(168, 194);
		btnRegresar.setSize(85, 23);
		contentPane.add(btnRegresar, BorderLayout.SOUTH);
        lblTitulo.setBounds(62, 11, 311, 31);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
		

}} 
