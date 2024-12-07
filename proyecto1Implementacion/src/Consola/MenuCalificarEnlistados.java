package Consola;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import System.*;
import Usuarios.*;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class MenuCalificarEnlistados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuCalificarEnlistados(Sistema sistema, Actividad ACEscogida, LearningPath lpSeleccionado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Escoger el estudiante que desea calificar:");
		
		JPanel scrollPane = new JPanel();
		List enlistados = new List();
		scrollPane.add(enlistados);
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		for (Iterator<String> iterator = ACEscogida.getState().keySet().iterator(); iterator.hasNext();) {
			String name = iterator.next();
			estudiantes.add(sistema.getEstudiante(name));
			enlistados.add(sistema.getEstudiante(name).getLogin());
			
		}
		enlistados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int opcion = enlistados.getSelectedIndex();
			Estudiante estudiante = estudiantes.get(opcion);
			MenuCalificarEnlistado MCE = new MenuCalificarEnlistado(sistema, ACEscogida, lpSeleccionado, estudiante);
			MCE.setVisible(true);
			dispose();
			
		}
	});
		
		
		
		JButton btnRegresar = new JButton("Regresar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(94)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(170)
							.addComponent(btnRegresar)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel)
					.addGap(37)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRegresar)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorCalificarActividad2 PCA2 = new ProfesorCalificarActividad2(sistema, lpSeleccionado);
				PCA2.setVisible(true);
				dispose();
			}
	});
		contentPane.setLayout(gl_contentPane);
		
		
	
		
		
	}

}