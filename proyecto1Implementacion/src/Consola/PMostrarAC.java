package Consola;

import java.awt.EventQueue;
import java.awt.FontMetrics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.ActividadRecurso;
import System.Encuesta;
import System.Examen;
import System.LearningPath;
import System.Opcion;
import System.Pregunta;
import System.PreguntaOpcionMultiple;
import System.Quiz;
import System.Sistema;
import System.Tarea;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.List;

public class PMostrarAC extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PMostrarAC(Sistema sistema, Actividad ACEscogida, LearningPath LPEscogido) {
		
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
				PMostrarLP volver = new PMostrarLP(sistema, LPEscogido);
				volver.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(229, 296, 132, 21);
		layeredPane.add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(202, 125, 41, 31);
		textArea.setText("Descripción : "+ ACEscogida.getDescripcion());
		textArea.setLineWrap(true); 
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false); 
		
		/*
		FontMetrics metrics = textArea.getFontMetrics(textArea.getFont());
        int textWidth = textArea.getWidth();
        int lineHeight = metrics.getHeight();

        
        int lines = textArea.getLineCount();
        int requiredHeight = lineHeight * lines;
        int requiredWidth = metrics.stringWidth("Descripción : "+ LPEscogido.getDescripcion()) / lines + 10;
        textArea.setBounds(20, 20, requiredWidth, requiredHeight);
       
        JPanel panel = new JPanel();
        panel.setBounds(0, 115, 607, 84);
        layeredPane.add(panel);
        panel.add(textArea);
        */
		JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 300, 150); // Fixed size box (300x150)
        layeredPane.add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("ID Actividad : "+ACEscogida.getID());
        scrollPane.setColumnHeaderView(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Dificultad : "+ACEscogida.getDifficulty());
        lblNewLabel_2.setBounds(20, 174, 300, 29);
        layeredPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1 = new JLabel("Duración : " + ACEscogida.getDuration() + " minutos ");
        lblNewLabel_1.setBounds(20, 206, 300, 13);
        layeredPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_3 = new JLabel("Fecha límite : " + ACEscogida.getDateLimit());
        lblNewLabel_3.setBounds(20, 227, 300, 13);
        layeredPane.add(lblNewLabel_3);
        
        JButton btnNewButton_2 = new JButton("Publicar reseña");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String reseña = JOptionPane.showInputDialog(null, "Por favor, ingresa la reseña que deseas publicar : ", "Reseña", JOptionPane.QUESTION_MESSAGE);
        		 if (reseña != null) {
        			 try {
						sistema.insertarReseñas(ACEscogida.getID(), sistema.getSession().getLogin(), reseña);
						JOptionPane.showMessageDialog(null, "Su reseña fue enviada al profesor con exito");
        			 } catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error");
					}
                 } else {
                     JOptionPane.showMessageDialog(null, "No aceptamos reseñas vacías.");
                 }
        	}
        });
        btnNewButton_2.setBounds(229, 327, 132, 40);
        layeredPane.add(btnNewButton_2);
        
        JButton btnNewButton = new JButton("Iniciar actividad");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
        			Quiz actividad = (Quiz) ACEscogida;
        			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
        			PMostrarPreguntaOM empezar = new PMostrarPreguntaOM(sistema, actividad, LPEscogido, 0, preguntas, 0);
        			empezar.setVisible(true);
        			dispose();
        		
        		}else if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
        			Examen actividad = (Examen) ACEscogida;
        			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
        			PMostrarPreguntaAB empezar = new PMostrarPreguntaAB(sistema, actividad, LPEscogido, 0, preguntas);
        			empezar.setVisible(true);
        			dispose();
        		
        		}else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
        			Encuesta actividad = (Encuesta) ACEscogida;
        			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
        			PMostrarPreguntaAB empezar = new PMostrarPreguntaAB(sistema, actividad, LPEscogido, 0, preguntas);
        			empezar.setVisible(true);
        			dispose();
        		
        		}else if (ACEscogida.getClass().getSimpleName().equals("Tarea")) {
        			Tarea tarea  = (Tarea) ACEscogida;
        			HashMap<String, String[]> state = tarea.getState();
        			if (!state.containsKey(sistema.getSession().getLogin())) {
        				state.put(sistema.getSession().getLogin(), new String[3]);
        				try {
							sistema.actualizarEstado(sistema.getSession(), tarea, LocalDateTime.now().toString() , "NULL", false, false);
        				} catch (SQLException e1) {
        					JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algun problema inicializando la actividad");
							
						}
        				
        			}
        			int answer = JOptionPane.showConfirmDialog(null, "Tarea : "+ tarea.getComentario() + "\n"
        					+ "¿Desea dar por completada esta actividad?\n Recuerde que si no la completa antes"
        					+ "de la fecha límite es contada como reprobada");
        			System.out.println(String.valueOf(answer));
        			if (answer == 0) {
        				try {
							sistema.actualizarEstado(sistema.getSession(), tarea, state.get(sistema.getSession().getLogin())[0],LocalDateTime.now().toString() ,true, false);
							PMostrarAC PMAC = new PMostrarAC(sistema, ACEscogida, LPEscogido);
							PMAC.setVisible(true);
							dispose();
        				} catch (SQLException e1) {
						
							JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error");
						}
        			}
        		
        		}else if (ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
        			HashMap<String, String[]> state = ACEscogida.getState();
        			if (!state.containsKey(sistema.getSession().getLogin())) {
        				state.put(sistema.getSession().getLogin(), new String[3]);
        				try {
							sistema.actualizarEstado(sistema.getSession(), ACEscogida, LocalDateTime.now().toString() , "NULL", false, false);
						
        				} catch (SQLException e1) {
							
							JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error");
						}
        			}
        			try {
						sistema.actualizarEstado(sistema.getSession(), ACEscogida, LocalDateTime.now().toString(),"NULL" ,false, false);
						ActividadRecurso actividadRecurso = (ActividadRecurso) ACEscogida;
						
						JTextArea textArea = new JTextArea("Recurso : " + actividadRecurso.getDocumentPath());
			            textArea.setEditable(false); 
			            textArea.setLineWrap(true);
			            textArea.setWrapStyleWord(true); 

			           
			            JScrollPane scrollPane = new JScrollPane(textArea);
			            scrollPane.setPreferredSize(new java.awt.Dimension(300, 150));

			            
			            JOptionPane.showMessageDialog(null, scrollPane, "Texto no editable", JOptionPane.INFORMATION_MESSAGE);
						
						
						sistema.actualizarEstado(sistema.getSession(), ACEscogida, state.get(sistema.getSession().getLogin())[0],LocalDateTime.now().toString() ,true, false);
						PMostrarAC PMAC = new PMostrarAC(sistema, ACEscogida, LPEscogido);
						PMAC.setVisible(true);
						dispose();
        			
        			} catch (SQLException e1) {
        				JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algún error");
					}
        			ActividadRecurso actividadRecurso = (ActividadRecurso) ACEscogida;
        		}
        	}
        });
        HashMap<String, String[]>states = ACEscogida.getState();
		boolean yaAprobada = false;
        if (states.containsKey(sistema.getSession().getLogin())) {
			if (states.get(sistema.getSession().getLogin())[2].equals("false")) {
				btnNewButton.setBounds(229, 250, 132, 36);
				layeredPane.add(btnNewButton);
				
			}else {
				yaAprobada = true;
			}
		}else {
			
			btnNewButton.setBounds(229, 250, 132, 36);
	        layeredPane.add(btnNewButton);
		}
        
        
        
        
        
	}
}