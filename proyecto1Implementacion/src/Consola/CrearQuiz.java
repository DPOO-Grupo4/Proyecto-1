package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Pregunta;
import System.Quiz;
import System.Sistema;

public class CrearQuiz extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDescripcion, txtDificultad, txtDuracion, txtFechaLimite, txtCalificacionMinima;
    private ArrayList<Pregunta> preguntas = new ArrayList<>();

    public CrearQuiz(Sistema sistema) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Quiz");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(220, 10, 140, 30);
        contentPane.add(lblTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 60, 100, 20);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(180, 60, 350, 20);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JLabel lblDificultad = new JLabel("Dificultad:");
        lblDificultad.setBounds(50, 100, 100, 20);
        contentPane.add(lblDificultad);

        txtDificultad = new JTextField();
        txtDificultad.setBounds(180, 100, 350, 20);
        contentPane.add(txtDificultad);
        txtDificultad.setColumns(10);

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setBounds(50, 140, 100, 20);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setBounds(180, 140, 350, 20);
        contentPane.add(txtDuracion);
        txtDuracion.setColumns(10);

        JLabel lblFechaLimite = new JLabel("Fecha Límite:");
        lblFechaLimite.setBounds(50, 180, 100, 20);
        contentPane.add(lblFechaLimite);

        txtFechaLimite = new JTextField();
        txtFechaLimite.setBounds(180, 180, 350, 20);
        contentPane.add(txtFechaLimite);
        txtFechaLimite.setColumns(10);

        JLabel lblCalificacionMinima = new JLabel("Calificación Mínima:");
        lblCalificacionMinima.setBounds(50, 220, 150, 20);
        contentPane.add(lblCalificacionMinima);

        txtCalificacionMinima = new JTextField();
        txtCalificacionMinima.setBounds(180, 220, 350, 20);
        contentPane.add(txtCalificacionMinima);
        txtCalificacionMinima.setColumns(10);

        JButton btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnAgregarPregunta.setBounds(50, 270, 200, 30);
        contentPane.add(btnAgregarPregunta);
        btnAgregarPregunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pregunta = JOptionPane.showInputDialog("Ingrese la pregunta:");
                if (pregunta != null && !pregunta.isEmpty()) {
                    preguntas.add(new Pregunta(pregunta)); // Se asume que `Pregunta` tiene un constructor simple
                    JOptionPane.showMessageDialog(null, "Pregunta añadida.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ingresó ninguna pregunta.");
                }
            }
        });

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(100, 350, 120, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String descripcion = txtDescripcion.getText();
                    String dificultad = txtDificultad.getText();
                    int duracion = Integer.parseInt(txtDuracion.getText());
                    LocalDateTime fechaLimite = LocalDateTime.parse(txtFechaLimite.getText());
                    int calificacionMinima = Integer.parseInt(txtCalificacionMinima.getText());

                    HashMap<String, String[]> estados = new HashMap<>(); // Inicializamos vacío por ahora
                    HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>(); // Vacío inicialmente

                    Quiz nuevoQuiz = new Quiz(sistema.getSession().getLogin(), 
                                              sistema.generarIdActividad(),
                                              true, // Por defecto obligatorio
                                              descripcion, 
                                              dificultad, 
                                              duracion, 
                                              fechaLimite, 
                                              estados, 
                                              preguntas, 
                                              calificacionMinima, 
                                              respuestas);

                    sistema.agregarActividad(nuevoQuiz); // Método ficticio que debes implementar
                    JOptionPane.showMessageDialog(null, "Quiz creado exitosamente.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear el quiz: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 350, 120, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
