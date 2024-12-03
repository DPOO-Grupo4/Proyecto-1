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
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.UIManager;

public class PMostrarPreguntaOM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PMostrarPreguntaOM(Sistema sistema, Quiz ACEscogida, LearningPath LPEscogido, int indiceP , ArrayList<Pregunta> preguntas, int respuestasBuenas) {
		
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
		
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(149, 31, 300, 150); // Fixed size box (300x150)
        layeredPane.add(scrollPane);
        
        
        
        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setText(preguntas.get(indiceP).getEnunciado());
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false); 
        
        Label label = new Label("Pregunta : " + String.valueOf(indiceP));
        label.setBackground(UIManager.getColor("CheckBox.background"));
        label.setBounds(267, 0, 59, 21);
        layeredPane.add(label);
        
        List list = new List();
        list.setBounds(149, 187, 300, 150);
        layeredPane.add(list);
        ArrayList<Opcion> opciones =  ((PreguntaOpcionMultiple) preguntas.get(indiceP)).getOpciones();
        HashMap<Integer, String> letras = new HashMap<Integer, String>();
        letras.put(0,"a");
        letras.put(1, "b");
        letras.put(2, "c");
        letras.put(3, "d");
        for (int i = 0 ; i < opciones.size();i++) 
        {
        	list.add(letras.get(i) + ") "+ opciones.get(i).getEnunciado());
        	
        }
        
        list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HashMap<String, String[]> state = ACEscogida.getState();
					
					if (!state.containsKey(sistema.getSession().getLogin())) {
						state.put(sistema.getSession().getLogin(), new String[3]);
						sistema.actualizarEstado(sistema.getSession(), ACEscogida, LocalDateTime.now().toString() , "", false, false);
					}
					
					sistema.actualizarRespuestaUsuario(sistema.getSession(), preguntas.get(indiceP).getID(), String.valueOf(opciones.get(list.getSelectedIndex()).getID()), "NULL", false);
					if (opciones.get(list.getSelectedIndex()).getCorrect()) {
						int RB = respuestasBuenas;
						RB += 1;
						JOptionPane.showMessageDialog(null, "¡Wow! Tu respuesta fue correcta porque ... \n"
								+ opciones.get(list.getSelectedIndex()).getExplicacion());
						int indicePr = indiceP;
						indicePr += 1;
						if (indicePr == preguntas.size()) {
							if (RB >= ACEscogida.getCalificacionMinima()) {
								//System.out.println("Ha aprobado la actividad");
								JOptionPane.showMessageDialog(null, "Ha aprobado la actividad");
								sistema.actualizarEstado(sistema.getSession(), ACEscogida,state.get(sistema.getSession().getLogin())[0],LocalDateTime.now().toString(), true, true );
								
							}else {
								JOptionPane.showMessageDialog(null, "¡Oops! Vuelve a intentarlo");
							}
							
							PMostrarAC volver = new PMostrarAC(sistema, ACEscogida, LPEscogido);
							volver.setVisible(true);
							dispose();
						}else {
							PMostrarPreguntaOM seguir = new PMostrarPreguntaOM(sistema, ACEscogida, LPEscogido, indicePr, preguntas, RB);
							seguir.setVisible(true);
							dispose();
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "¡oops! Tu respuesta fue incorrecta porque ... \n"
								+ opciones.get(list.getSelectedIndex()).getExplicacion());
						
						int RB = respuestasBuenas;
						int indicePr = indiceP;
						indicePr += 1;
						if (indicePr == preguntas.size()) {
							if (RB >= ACEscogida.getCalificacionMinima()) {
								//System.out.println("Ha aprobado la actividad");
								JOptionPane.showMessageDialog(null, "Ha aprobado la actividad");
								sistema.actualizarEstado(sistema.getSession(), ACEscogida,state.get(sistema.getSession().getLogin())[0],LocalDateTime.now().toString(), true, true );
								
							}else {
								JOptionPane.showMessageDialog(null, "¡Oops! Vuelve a intentarlo");
							}
							PMostrarAC volver = new PMostrarAC(sistema, ACEscogida, LPEscogido);
							volver.setVisible(true);
							dispose();
						}else {
							PMostrarPreguntaOM seguir = new PMostrarPreguntaOM(sistema, ACEscogida, LPEscogido, indicePr, preguntas, RB);
							seguir.setVisible(true);
							dispose();
						}
					}
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Hubo algún error aceptando su respuesta ");
				}
				
				
			}
		});
        
        
        
	}
}