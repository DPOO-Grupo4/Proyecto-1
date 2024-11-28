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
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

public class PMostrarLP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PMostrarLP(Sistema sistema, LearningPath LPEscogido) {
		
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
		
		JLabel lblNewLabel = new JLabel("Título : "+LPEscogido.getTitulo());
		lblNewLabel.setBounds(251, 52, 60, 31);
		layeredPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Descripción : ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(245, 78, 147, 52);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(LPEscogido.getDescripcion());
		lblNewLabel_1.setBounds(6, 15, 52, 13);
		panel.add(lblNewLabel_1);
		
		
		
	}
}