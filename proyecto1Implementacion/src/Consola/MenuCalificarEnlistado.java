package Consola;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import System.*;
import Usuarios.*;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class MenuCalificarEnlistado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuCalificarEnlistado(Sistema sistema, Actividad ACEscogida, LearningPath lpSeleccionado, Estudiante estudiante) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Calificar" + estudiante.getLogin());
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuCalificarEnlistados MCE = new MenuCalificarEnlistados(sistema, ACEscogida, lpSeleccionado);
				MCE.setVisible(true);
				dispose();
			}
	});
		
		JButton btnAprobar = new JButton("Aprobar");
		btnAprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (ACEscogida.getClass().getSimpleName().equals("Tarea") | ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
					JOptionPane.showMessageDialog(null,"Esta actividad no es calificable por medio de la aplicación.");
				}
				else {
					HashMap<String, String[]> state = ACEscogida.getState();
					if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
						Quiz quiz = (Quiz) ACEscogida;
						try {
							sistema.actualizarEstado(estudiante, quiz, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
						}
						JOptionPane.showMessageDialog(null,"La actividad fue aprobada con éxito.");
						}
					else if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
						Examen examen = (Examen) ACEscogida;
						try {
							sistema.actualizarEstado(estudiante, examen, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
						}
						JOptionPane.showMessageDialog(null,"La actividad fue aprobada con éxito.");
					}
				
					else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
						Encuesta encuesta = (Encuesta) ACEscogida;
						try {
							sistema.actualizarEstado(estudiante, encuesta, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], true, true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
						}
						JOptionPane.showMessageDialog(null,"La actividad fue aprobada con éxito.");
						}
			
				}
				}
			});
		
		JButton btnReprobar = new JButton("Reprobar");
		btnReprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (ACEscogida.getClass().getSimpleName().equals("Tarea") | ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
				JOptionPane.showMessageDialog(null,"Esta actividad no es calificable por medio de la aplicación.");
			}
			else {
				HashMap<String, String[]> state = ACEscogida.getState();
				if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
					Quiz quiz = (Quiz) ACEscogida;
					try {
						sistema.actualizarEstado(estudiante, quiz, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], false, true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
					}
					JOptionPane.showMessageDialog(null,"La actividad fue reprobada con éxito.");
					}
				else if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
					Examen examen = (Examen) ACEscogida;
					try {
						sistema.actualizarEstado(estudiante, examen, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], false, true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
					}
					JOptionPane.showMessageDialog(null,"La actividad fue reprobada con éxito.");
				}
			
				else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
					Encuesta encuesta = (Encuesta) ACEscogida;
					try {
						sistema.actualizarEstado(estudiante, encuesta, state.get(estudiante.getLogin())[0], state.get(estudiante.getLogin())[1], false, true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error calificando esta actividad.");
					}
					}
		
			}
			}
		});
		
		List pregs = new List();
		JList listaPreguntas = new JList();
		listaPreguntas.add(pregs);
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(160, Short.MAX_VALUE)
					.addComponent(lblTitulo)
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(167)
					.addComponent(btnRegresar)
					.addContainerGap(175, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(109)
					.addComponent(btnAprobar)
					.addGap(18)
					.addComponent(btnReprobar)
					.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(listaPreguntas, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitulo)
					.addGap(18)
					.addComponent(listaPreguntas, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReprobar)
						.addComponent(btnAprobar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegresar)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
