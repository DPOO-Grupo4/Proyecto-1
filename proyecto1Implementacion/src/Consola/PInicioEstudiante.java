package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Sistema;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

public class PInicioEstudiante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PInicioEstudiante(Sistema sistema) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(128, 255, 0));
		contentPane.add(layeredPane);
		
		JButton LPsInscritos = new JButton("LearningPaths inscritos");
		LPsInscritos.setBounds(227, 85, 146, 59);
		layeredPane.add(LPsInscritos);
		LPsInscritos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PLearningPathsInscritos PLPsInscritos = new PLearningPathsInscritos(sistema);
				PLPsInscritos.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Inscribir LearningPath");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioSesion pantalla1 = new InicioSesion(sistema);
				pantalla1.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(227, 168, 146, 59);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicioSesion IS = new InicioSesion(sistema);
				IS.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(251, 296, 85, 21);
		layeredPane.add(btnNewButton_1);
		
		
		
	}
}
