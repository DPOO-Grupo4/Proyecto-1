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
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreUsuario;
	private JTextField contraseña;

	

	public InicioSesion(Sistema sistema) {
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
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setBounds(246, 60, 153, 37);
		layeredPane.add(lblNewLabel);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setBounds(254, 101, 96, 19);
		layeredPane.add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		contraseña = new JTextField();
		contraseña.setBounds(254, 219, 96, 19);
		layeredPane.add(contraseña);
		contraseña.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(246, 196, 133, 19);
		layeredPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login  = nombreUsuario.getText();
				String password = contraseña.getText();
				try {
					String message = sistema.iniciarSesion(login, password);
					JOptionPane.showMessageDialog(null, message);
					if (!message.equals("Su usuario o contraseña son erróneos"))
					{
						if (sistema.getSession().getClass().getSimpleName().equals("Estudiante")) {
							PInicioEstudiante PInicioEstudiante = new PInicioEstudiante(sistema);
							PInicioEstudiante.setVisible(true);
							dispose();
						}else {
							
						}
						
						//menuAplicacion(sistema, scanner);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"¡Oops! ocurrio algun error");
				}
			}
		});
		btnNewButton.setBounds(254, 304, 96, 21);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio volver = new Inicio();
				volver.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(253, 335, 97, 21);
		layeredPane.add(btnNewButton_1);
	}
}

