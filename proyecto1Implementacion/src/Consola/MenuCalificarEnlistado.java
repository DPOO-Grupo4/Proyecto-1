package Consola;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import System.Actividad;
import System.Encuesta;
import System.Examen;
import System.LearningPath;
import System.Pregunta;
import System.Quiz;
import System.Sistema;
import Usuarios.Estudiante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuCalificarEnlistado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

   
    public MenuCalificarEnlistado(Sistema sistema, Actividad ACEscogida, LearningPath lpSeleccionado, Estudiante estudiante) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Calificando a: " + estudiante.getLogin());

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> {
            MenuCalificarEnlistados MCE = new MenuCalificarEnlistados(sistema, ACEscogida, lpSeleccionado);
            MCE.setVisible(true);
            dispose();
        });

        JButton btnAprobar = new JButton("Aprobar");
        btnAprobar.addActionListener(e -> {
            if (ACEscogida.getClass().getSimpleName().equals("Tarea") || ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
                JOptionPane.showMessageDialog(null, "Esta actividad no es calificable por medio de la aplicación.");
            } else {
                HashMap<String, String[]> state = ACEscogida.getState();
               
            }
        });

        JButton btnReprobar = new JButton("Reprobar");
        btnReprobar.addActionListener(e -> {
            if (ACEscogida.getClass().getSimpleName().equals("Tarea") || ACEscogida.getClass().getSimpleName().equals("ActividadRecurso")) {
                JOptionPane.showMessageDialog(null, "Esta actividad no es calificable por medio de la aplicación.");
            } else {
                HashMap<String, String[]> state = ACEscogida.getState();
                
            }
        });


        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaPreguntas = new JList<>(modeloLista);

 
        if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
            Examen examen = (Examen) ACEscogida;
            ArrayList<Pregunta> preguntas = examen.getPreguntas();
            for (int i = 0; i < preguntas.size(); i++) {
                modeloLista.addElement("Pregunta [" + i + "]");
            }
        } else if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
            Quiz quiz = (Quiz) ACEscogida;
            ArrayList<Pregunta> preguntas = quiz.getPreguntas();
            for (int i = 0; i < preguntas.size(); i++) {
                modeloLista.addElement("Pregunta [" + i + "]");
            }
        } else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
            Encuesta encuesta = (Encuesta) ACEscogida;
            ArrayList<Pregunta> preguntas = encuesta.getPreguntas();
            for (int i = 0; i < preguntas.size(); i++) {
                modeloLista.addElement("Pregunta [" + i + "]");
            }
        }


        JScrollPane scrollPane = new JScrollPane(listaPreguntas);


        listaPreguntas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int opcion = listaPreguntas.getSelectedIndex(); 
                if (opcion != -1) { 
                    try {
                        Pregunta preguntaEscogida = null;

                        if (ACEscogida.getClass().getSimpleName().equals("Examen")) {
                            Examen examen = (Examen) ACEscogida;
                            ArrayList<Pregunta> preguntas = examen.getPreguntas();
                            preguntaEscogida = preguntas.get(opcion);
                        } else if (ACEscogida.getClass().getSimpleName().equals("Quiz")) {
                            Quiz quiz = (Quiz) ACEscogida;
                            ArrayList<Pregunta> preguntas = quiz.getPreguntas();
                            preguntaEscogida = preguntas.get(opcion);
                        } else if (ACEscogida.getClass().getSimpleName().equals("Encuesta")) {
                            Encuesta encuesta = (Encuesta) ACEscogida;
                            ArrayList<Pregunta> preguntas = encuesta.getPreguntas();
                            preguntaEscogida = preguntas.get(opcion);
                        }

                        if (preguntaEscogida != null) {
                            Object[] array = sistema.getAnswersActivity(preguntaEscogida.getID(), estudiante.getLogin());
                            String respuestaEstudiante = (String) array[0]; 
                            JOptionPane.showMessageDialog(null, 
                                "Enunciado: " + preguntaEscogida.getEnunciado() + "\n" +
                                "Respuesta del estudiante: " + respuestaEstudiante);
                        }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "¡Oops! Ocurrió un error al obtener la información.");
                        e1.printStackTrace();
                    }
                }
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(167)
                    .addComponent(btnRegresar))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(109)
                    .addComponent(btnAprobar)
                    .addGap(18)
                    .addComponent(btnReprobar))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(68)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(155)
                    .addComponent(lblTitulo))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(10)
                    .addComponent(lblTitulo)
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnReprobar)
                        .addComponent(btnAprobar))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnRegresar)
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}