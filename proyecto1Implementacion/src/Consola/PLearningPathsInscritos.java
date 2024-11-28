package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.List;
import java.awt.Label;

public class PLearningPathsInscritos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PLearningPathsInscritos(Sistema sistema) {
		
		
		
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
				InicioSesion IS = new InicioSesion(sistema);
				IS.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(251, 296, 85, 21);
		layeredPane.add(btnNewButton_1);
		
		List list = new List();
		list.setBounds(201, 38, 188, 225);
		layeredPane.add(list);
		
		ArrayList<LearningPath> LPsInscritos;
		try {
			LPsInscritos = sistema.getLPsInscritos(sistema.getSession().getLogin());
			for (int i=0; i < LPsInscritos.size(); i++) {
				list.add(LPsInscritos.get(i).getTitulo(), i);
			}
			list.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcion = list.getSelectedIndex();
					LearningPath LPEscogido = LPsInscritos.get(opcion);
					boolean aprobado = sistema.verificarEstadoLP(sistema.getSession(), LPEscogido);
					if (aprobado)
					{
						JOptionPane.showMessageDialog(null, "¡Felicidades! completaste el learning path"
								+ "\n Vuelve en un tiempo cuando tengas la certeza de que el profesor evaluó todas las actividades " );
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Este learning path no esta acabado o esta reprobado"
								+ "\n Nota: Si ya completo todas las actividades obligatorias y el profesor calificó, probablemente entregó algo tarde " );
						
					}
					PMostrarLP PMLP = new PMostrarLP(sistema, LPEscogido);
					PMLP.setVisible(true);
					dispose();
					
				}
			});
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algun problema cargando tus learning paths inscritos");
		}
		
		
	}
	
}
