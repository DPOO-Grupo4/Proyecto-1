package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Sistema;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class PRegistrarse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginUsuario;
	private JTextField correoUsuario;
	private JPasswordField contraseña1Usuario;
	private JPasswordField contraseña2Usuario;

	

	public PRegistrarse(Sistema sistema) {
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
		
		JLabel login = new JLabel("Nombre de usuario");
		login.setBounds(246, 60, 153, 37);
		layeredPane.add(login);
		
		loginUsuario = new JTextField();
		loginUsuario.setBounds(254, 101, 96, 19);
		layeredPane.add(loginUsuario);
		loginUsuario.setColumns(10);
		
		JLabel lblContraseña1 = new JLabel("Confirmar contraseña");
		lblContraseña1.setBounds(246, 263, 133, 19);
		layeredPane.add(lblContraseña1);
		
		JLabel lblContraseña2 = new JLabel("Contraseña");
		lblContraseña2.setBounds(246, 201, 153, 37);
		layeredPane.add(lblContraseña2);
		
		correoUsuario = new JTextField();
		correoUsuario.setColumns(10);
		correoUsuario.setBounds(254, 172, 96, 19);
		layeredPane.add(correoUsuario);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(246, 130, 153, 37);
		layeredPane.add(lblCorreo);
		
		
		String[] options = { "Estudiante", "Profesor" };
		JComboBox<String> comboBox = new JComboBox<>(options);

        
        Object[] message = {
            "Selecciona una opción:", comboBox
        };
        
        
		JButton btnNewButton = new JButton("Registrarme");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				int option = JOptionPane.showConfirmDialog(null,message,"Seleccione su rol según corresponda ", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
	                String selectedOption = (String) comboBox.getSelectedItem();
	                //System.out.println(selectedOption);
	                boolean error = false;
	                String contraseña1 = new String(contraseña1Usuario.getPassword());
	                String contraseña2 = new String(contraseña2Usuario.getPassword());
	                if (!contraseña1.equals(contraseña2)) {
	                	
	                	JOptionPane.showMessageDialog(null, "Los campos de contraseña no coinciden.");
	        			PRegistrarse limpiar = new PRegistrarse(sistema);
	        			limpiar.setVisible(true);
	        			dispose();
	        			error = true;
	                }
	                Pattern pattern = Pattern.compile("[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}");
	        		String algo = correoUsuario.getText();
	        		Matcher matcher = pattern.matcher( correoUsuario.getText());
	        		if (!matcher.find()) {
	        			JOptionPane.showMessageDialog(null, "La dirreción de correo electronico no es válida.");
	        			PRegistrarse limpiar = new PRegistrarse(sistema);
	        			limpiar.setVisible(true);
	        			dispose();
	        			error = true;
	        		}
	        		if (!sistema.validezLogin(loginUsuario.getText()))
	        				{
	        					error = true;
	        					JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe :)");
	        				}
	        		if (!error)
	        		{
	        			try {
		        			sistema.crearUsuario(loginUsuario.getText(), contraseña1, correoUsuario.getText(), selectedOption.toLowerCase(), true );
		        			
		        			JOptionPane.showMessageDialog(null, "SU USUARIO FUE CREADO EXITOSAMENTE");
		        		} catch (SQLException e1) {
		        			// TODO Auto-generated catch block
		        			JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error creando tu usuario.");
		        		}
	        		}else {
	        			
	        		}
	        		
	        		
	            } else {
	                
	            }
			}
		});
		btnNewButton.setBounds(231, 338, 143, 21);
		layeredPane.add(btnNewButton);
		
		contraseña1Usuario = new JPasswordField();
		contraseña1Usuario.setBounds(254, 234, 96, 19);
		layeredPane.add(contraseña1Usuario);
		
		contraseña2Usuario = new JPasswordField();
		contraseña2Usuario.setBounds(254, 292, 96, 19);
		layeredPane.add(contraseña2Usuario);
		
		JButton btnNewButton_1 = new JButton("Regresar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio volver = new Inicio();
				volver.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(229, 369, 145, 21);
		layeredPane.add(btnNewButton_1);
		
	}
}

