package Consola;

import java.awt.EventQueue;
import java.awt.FontMetrics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class PMostrarLP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public PMostrarLP(Sistema sistema, LearningPath LPEscogido) {
		
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
				PLearningPathsInscritos IS = new PLearningPathsInscritos(sistema);
				IS.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(232, 296, 120, 21);
		layeredPane.add(btnNewButton_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(202, 125, 41, 31);
		textArea.setText("Descripción : "+ LPEscogido.getDescripcion());
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
        
        JLabel lblNewLabel = new JLabel("Título : "+LPEscogido.getTitulo());
        scrollPane.setColumnHeaderView(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Dificultad : "+LPEscogido.getDifficulty());
        lblNewLabel_2.setBounds(20, 174, 300, 29);
        layeredPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1 = new JLabel("Duración : " + LPEscogido.getDuration() + " minutos ");
        lblNewLabel_1.setBounds(20, 206, 300, 13);
        layeredPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_3 = new JLabel("Actividades : ");
        lblNewLabel_3.setBounds(371, 21, 134, 13);
        layeredPane.add(lblNewLabel_3);
        
        List list = new List();
        list.setBounds(361, 40, 202, 130);
        layeredPane.add(list);
        
        
        ArrayList<Actividad> actividades = LPEscogido.getActivities();
		for (int i=0; i < actividades.size(); i++) {
			list.add("Actividad " + String.valueOf(i), i);
			
		}
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = list.getSelectedIndex();
				Actividad ACEscogida = actividades.get(opcion);
				PMostrarAC PMAC = new PMostrarAC(sistema, ACEscogida, LPEscogido);
				PMAC.setVisible(true);
				dispose();
				
			}
		});
	}
}