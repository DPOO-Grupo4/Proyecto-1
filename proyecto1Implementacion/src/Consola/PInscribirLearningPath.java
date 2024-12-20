package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;
import Usuarios.Estudiante;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.List;
import java.awt.Label;

public class PInscribirLearningPath extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PInscribirLearningPath(Sistema sistema) {
		
		
		
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
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PInicioEstudiante IS = new PInicioEstudiante(sistema);
				IS.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(201, 296, 188, 21);
		layeredPane.add(btnNewButton_1);
		
		List list = new List();
		list.setBounds(201, 38, 188, 225);
		layeredPane.add(list);
		
		ArrayList<LearningPath> LPsInscritos;
		try {
			LPsInscritos = sistema.getLPsInscritos(sistema.getSession().getLogin());
			HashMap<String, LearningPath> learningPaths = sistema.getLPs();
			ArrayList<String> titulosLPs = new ArrayList<>();
			for (int i = 0; i < learningPaths.size(); i ++) {
				list.add("["+String.valueOf(i)+"] " + (new ArrayList<LearningPath> (learningPaths.values())).get(i).getTitulo(),i);
				titulosLPs.add( (new ArrayList<LearningPath> (learningPaths.values())).get(i).getTitulo());
			}
			
			list.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcion = list.getSelectedIndex();
					try {
						boolean success = sistema.inscribirLP(learningPaths.get(titulosLPs.get(opcion)),(Estudiante) sistema.getSession());
						if (success) {
							JOptionPane.showMessageDialog(null, "¡El learning path fue inscrito exitosamente!");
							
						}else
						{
							JOptionPane.showMessageDialog(null, "¡Oops! Ese learning path ya lo inscribiste");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error inscribiendo este Learning Path");
					}
					
					
					
				}
			});
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algun problema cargando tus learning paths inscritos");
		}
		
		
	}
	
}

