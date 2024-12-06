package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;
import Usuarios.Usuario;
import System.Actividad;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VerActividadesCreadasProf extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerActividadesCreadasProf frame = new VerActividadesCreadasProf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 * Create the frame.
	 */
	public VerActividadesCreadasProf(Sistema sistema) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    JPanel botonesPanel = new JPanel();
	    botonesPanel.setBounds(10, 79, 414, 109);
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(botonesPanel);
        
        JLabel lbltitulo = new JLabel("¿De cuál Learning Path quieres ver las actividades?");
		lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo.setBounds(24, 25, 400, 30);
		contentPane.add(lbltitulo);
		
		JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setLocation(164, 199);
        btnRegresar.setSize(106, 30);
		contentPane.add(btnRegresar);
		
		
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InicioProfesor IS= new InicioProfesor(sistema);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });


	    try {
	        ArrayList<LearningPath> creados = sistema.getLPsCreados(sistema.getSession().getLogin());
	        for (LearningPath LP : creados) {
	            String titulo = LP.getTitulo();
	            ArrayList<Actividad> actividades = LP.getActivities();

	            JButton botonLP = new JButton(titulo);
	            botonesPanel.add(botonLP);

	            botonLP.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    JDialog infoDialog = new JDialog(VerActividadesCreadasProf.this, "Actividades de" + titulo, true);
	                    infoDialog.setSize(400, 300);
	                    infoDialog.getContentPane().setLayout(new BorderLayout());

	                    JTextArea textArea = new JTextArea();
	                    textArea.setEditable(false);

	                    for (Actividad actividad : actividades) {
	                        String ActID= String.valueOf(actividad.getID()); 
	                        String ActDescrip= actividad.getDescripcion();
	                        String ActDifi= actividad.getDifficulty();
	                        String ActDuracion= String.valueOf(actividad.getDuration()); 
	                        String ActObli = String.valueOf(actividad.getMandatory()); 
	                        String ActTipo= getActTipo(sistema, actividad);
	                        
	                    
							textArea.setText(
	                        "Descripcion: "+ ActDescrip + "\n" 
	                        + "ID: " + ActID + "\n" 
	                        + "Tipo de Actividad:  " + ActTipo + "\n"
	                        + "Dificultad: " + ActDifi + "\n"
	                        + "Duración: " + ActDuracion + "\n"
	                        + "Obligatorio: " + ActObli + "\n" + "\n"
	                        );
	                    }

	                    infoDialog.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
	                    JButton btnCerrar = new JButton("Cerrar");
	                    btnCerrar.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            infoDialog.dispose();
	                        }
	                    });
	                    infoDialog.getContentPane().add(btnCerrar, BorderLayout.SOUTH);

	                    infoDialog.setLocationRelativeTo(VerActividadesCreadasProf.this);
	                    infoDialog.setVisible(true);
	                }
	            });
	        }
	        	        

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	public String getActTipo(Sistema sistema, Actividad actividad) {
		String ActTipo = "N.A";
		if (actividad.getClass().getSimpleName().equals("Quiz")) {
			ActTipo= "Quiz";
	    }
		else if (actividad.getClass().getSimpleName().equals("Examen")) {
			ActTipo= "Examen";
		}
		else if (actividad.getClass().getSimpleName().equals("Encuesta")) {
			ActTipo= "Encuesta";
		}
		else if (actividad.getClass().getSimpleName().equals("Tarea")) {
			ActTipo= "Tarea";
		}
		else if (actividad.getClass().getSimpleName().equals("ActividadRecurso")) {
			ActTipo= "Recurso";
		}
		
		return ActTipo;
	}

}