package Consola;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Pregunta;
import System.Sistema;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ModificarEnunciado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNewEnun;

	public ModificarEnunciado(Sistema sistema, Actividad actividad, LearningPath LP, Pregunta pregunta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        txtNewEnun = new JTextField();
        txtNewEnun.setBounds(59, 81, 324, 20);
        contentPane.add(txtNewEnun);
        txtNewEnun.setColumns(10);
        
        JLabel lblNewEnun = new JLabel("Escriba el nuevo enunciado");
        lblNewEnun.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewEnun.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewEnun.setBounds(59, 47, 324, 23);
        contentPane.add(lblNewEnun);
        
        JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(77, 23);
		btnRegresar.setLocation(177, 201);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        });
        
        JButton btnCambiarEnun = new JButton("Cambiar Enunciado");
        btnCambiarEnun.setBounds(59, 113, 175, 23);
        contentPane.add(btnCambiarEnun);
        btnCambiarEnun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevoEnunciado = txtNewEnun.getText(); 
				try {
					sistema.modificarEnunciadoPregunta(pregunta.getID(), nuevoEnunciado, LP, actividad);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se pudo cambiar el enunciado", "", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			} 
        	
        });
	}
}
