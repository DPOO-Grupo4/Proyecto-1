package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;
import Usuarios.Usuario;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VerLPsCreadosProf extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerLPsCreadosProf frame = new VerLPsCreadosProf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public VerLPsCreadosProf(Sistema sistema) {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    
	    
	    
	    contentPane.setLayout(null);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(163, 221, 106, 29);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InicioProfesor IS= new InicioProfesor(sistema);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
        
        JPanel botonesPanel = new JPanel();
	    botonesPanel.setBounds(10, 78, 414, 120);
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(botonesPanel);
        
        JLabel lbltitulo = new JLabel("¿Que Learning Path quiere consultar?");
        lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltitulo.setBounds(56, 25, 299, 42);
        contentPane.add(lbltitulo);

	    try {
	        ArrayList<LearningPath> creados = sistema.getLPsCreados(sistema.getSession().getLogin());
	        for (LearningPath LP : creados) {
	            String titulo = LP.getTitulo();
	            String descripcion = LP.getDescripcion();
	            String dificultad = LP.getDifficulty();
	            int duracion = LP.getDuration();
	            ArrayList<Usuario> estudiantesEnlistados = LP.getEstudiantesEnlistados();

	            JButton botonLP = new JButton(titulo);
	            botonesPanel.add(botonLP);

	            botonLP.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    JDialog infoDialog = new JDialog(VerLPsCreadosProf.this, "Información de" + titulo, true);
	                    infoDialog.setSize(400, 300);
	                    infoDialog.getContentPane().setLayout(new BorderLayout());

	                    JTextArea textArea = new JTextArea();
	                    textArea.setEditable(false);

	                    StringBuilder estudiantesStr = new StringBuilder();
	                    for (Usuario estudiante : estudiantesEnlistados) {
	                        estudiantesStr.append(estudiante.getLogin()).append("\n");
	                    }

	                    textArea.setText("Título: " + titulo + "\n"
	                                   + "Descripción: " + descripcion + "\n"
	                                   + "Dificultad: " + dificultad + "\n"
	                                   + "Duración: " + duracion + " horas\n"
	                                   + "Estudiantes enlistados:\n" + estudiantesStr);

	                    infoDialog.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

	                    JButton btnCerrar = new JButton("Cerrar");
	                    btnCerrar.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            infoDialog.dispose();
	                        }
	                    });
	                    infoDialog.getContentPane().add(btnCerrar, BorderLayout.SOUTH);

	                    infoDialog.setLocationRelativeTo(VerLPsCreadosProf.this);
	                    infoDialog.setVisible(true);
	                }
	            });
	        }
	        
	        

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar los Learning Paths: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}}


