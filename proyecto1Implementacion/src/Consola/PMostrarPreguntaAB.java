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

public class PMostrarPreguntaAB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PMostrarPreguntaAB(Sistema sistema, Actividad ACEscogida, LearningPath LPEscogido, int indiceP , ArrayList<Pregunta> preguntas) {
		
		
		
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
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(149, 213, 300, 150);
        layeredPane.add(scrollPane_1);
        scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setLineWrap(true); 
        textArea_1.setWrapStyleWord(true);
        scrollPane_1.setViewportView(textArea_1);
        
        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HashMap<String, String[]> state = ACEscogida.getState();
    			if (!state.containsKey(sistema.getSession().getLogin())) {
    				state.put(sistema.getSession().getLogin(), new String[3]);
    			}
    				try {
						sistema.actualizarEstado(sistema.getSession(), ACEscogida, LocalDateTime.now().toString() , "NULL", false, false);
						sistema.actualizarRespuestaUsuario(sistema.getSession(), preguntas.get(indiceP).getID(), "NULL", textArea_1.getText(), false);
						JOptionPane.showMessageDialog(null,"Su respuesta fue enviada con exito");
						int indicePr = indiceP;
						indicePr += 1;
						if (indicePr == preguntas.size()) {
							sistema.actualizarEstado(sistema.getSession(), ACEscogida, state.get(sistema.getSession().getLogin())[0], LocalDateTime.now().toString(), true, false);
							PMostrarAC volver = new PMostrarAC(sistema,ACEscogida, LPEscogido);
							volver.setVisible(true);
							dispose();
						}else {
							PMostrarPreguntaAB MPAB = new PMostrarPreguntaAB(sistema, ACEscogida, LPEscogido, indicePr, preguntas);
							MPAB.setVisible(true);
							dispose();
						}
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "¡Oops! Ocurrio algun error enviando tu respuesta");
					}
    			
        	}
        });
        btnNewButton.setBounds(251, 381, 85, 21);
        layeredPane.add(btnNewButton);
        
	}
}