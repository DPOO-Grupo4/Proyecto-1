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
		btnNewButton_1.setBounds(251, 296, 85, 21);
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
        			
        			/*
        			HashMap<String, String[]> state = ACEscogida.getState();
        			if (!state.containsKey(sistema.getSession().getLogin())) {
        				state.put(sistema.getSession().getLogin(), new String[3]);
        				sistema.actualizarEstado(sistema.getSession(), ACEscogida, LocalDateTime.now().toString() , "", false, false);
        			}
        			Quiz actividad = (Quiz) ACEscogida;
        			ArrayList<Pregunta> preguntas = actividad.getPreguntas();
        			int respuestasBuenas = 0;
        			
        			for (int i =1 ; i <= preguntas.size(); i++) {
        				System.out.println("Pregunta "+String.valueOf(i)+":");
        				PreguntaOpcionMultiple pregunta= (PreguntaOpcionMultiple) preguntas.get(i-1);
        				System.out.println(pregunta.getEnunciado());
        				ArrayList<Opcion> opciones = pregunta.getOpciones();
        				for (int j = 1; j<=opciones.size(); j++) {
        					System.out.println("Opcion " + String.valueOf(j));
        					Opcion opcioni = opciones.get(j-1);
        					System.out.println(opcioni.getEnunciado());
        				}
        				System.out.println("Seleccione la opcion que considere correcta :");
        				int respuestaUsuario = scanner.nextInt() -1;
        				while (respuestaUsuario<0 || respuestaUsuario>opciones.size()-1)
        				{
        					System.out.println("La opción que selecciono no existe");
        					System.out.println("Por favor digite una opción que sí sea valida");
        					respuestaUsuario = scanner.nextInt()-1;
        				}
        				//sistema.insertarAnswersActiyity();
        				sistema.actualizarRespuestaUsuario(sistema.getSession(), pregunta.getID(), String.valueOf(opciones.get(respuestaUsuario).getID()), "NULL", false);
        				if (opciones.get(respuestaUsuario).getCorrect()) {
        					respuestasBuenas+=1;
        					System.out.println("-----------------------------------------------");
        					System.out.println("¡Wow! Tu respuesta fue correcta porque ...");
        					System.out.println(opciones.get(respuestaUsuario).getExplicacion());
        					System.out.println("-----------------------------------------------");
        				}else {
        					System.out.println("-----------------------------------------------");
        					System.out.println("¡oops! Tu respuesta fue incorrecta porque ...");
        					System.out.println(opciones.get(respuestaUsuario).getExplicacion());
        					System.out.println("-----------------------------------------------");
        				}
        				
        			}
        			if (respuestasBuenas >= actividad.getCalificacionMinima()) {
        				System.out.println("Ha aprobado la actividad");
        				sistema.actualizarEstado(sistema.getSession(), ACEscogida,state.get(sistema.getSession().getLogin())[0],LocalDateTime.now().toString(), true, true );
        				
        			}*/
        			
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