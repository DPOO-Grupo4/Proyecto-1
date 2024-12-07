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
import System.PreguntaOpcionMultiple;
import System.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class AnadirOpcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnunciado;
	private JTextField txtExplicacion;

	
	public AnadirOpcion(Sistema sistema, Actividad actividad, LearningPath LP, Pregunta pregunta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(104, 23);
		btnRegresar.setLocation(227, 201);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        });
        
        JLabel lblTitulo = new JLabel("Crear nueva opción");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(105, 21, 209, 28);
        contentPane.add(lblTitulo);
        
        txtEnunciado = new JTextField();
        txtEnunciado.setBounds(33, 81, 380, 20);
        contentPane.add(txtEnunciado);
        txtEnunciado.setColumns(10);
        
        JCheckBox chckbxCorrecta = new JCheckBox("Esta es la opción correcta");
        chckbxCorrecta.setBounds(33, 145, 168, 23);
        contentPane.add(chckbxCorrecta);
        
        JCheckBox chckbxBorrarCorrecta = new JCheckBox("Borrar la Opcion correcta");
        chckbxBorrarCorrecta.setBounds(33, 173, 168, 23);
        contentPane.add(chckbxBorrarCorrecta);
        
        txtExplicacion = new JTextField();
        txtExplicacion.setBounds(33, 118, 380, 20);
        contentPane.add(txtExplicacion);
        txtExplicacion.setColumns(10);
        
        JLabel lblEnunciado = new JLabel("Enunciado: ");
        lblEnunciado.setBounds(31, 67, 98, 14);
        contentPane.add(lblEnunciado);
        
        JLabel lblExplicacion = new JLabel("Explicación: ");
        lblExplicacion.setBounds(31, 104, 84, 14);
        contentPane.add(lblExplicacion);
        
        JButton btnCambiarEnun = new JButton("Cambiar enunciado");
        btnCambiarEnun.setBounds(53, 201, 134, 23);
        contentPane.add(btnCambiarEnun);
        btnCambiarEnun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String enunciado= txtEnunciado.getText();
				String explicacion= txtExplicacion.getText();
				boolean correcta= chckbxCorrecta.isSelected();
				boolean borrarCorrecta= chckbxBorrarCorrecta.isSelected();
				
				try {
					sistema.añadirOpcion(((PreguntaOpcionMultiple) pregunta), enunciado, correcta, explicacion, borrarCorrecta, actividad, LP);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
        	
        });
        
		
		
	}
}
