package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Encuesta;
import System.Pregunta;

public class CrearEncuesta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtID;
    private JTextField txtDuracion;
    private JTextField txtFechaLimite;
    private JTextArea txtDescripcion;
    private JComboBox<String> comboDificultad;
    private ArrayList<Pregunta> preguntas;

    public CrearEncuesta() {
        preguntas = new ArrayList<>(); // Lista de preguntas de la encuesta

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Encuesta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(220, 10, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblID = new JLabel("ID Encuesta:");
        lblID.setBounds(50, 60, 100, 20);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(150, 60, 400, 20);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 100, 100, 20);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(150, 100, 400, 60);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        contentPane.add(txtDescripcion);

        JLabel lblDificultad = new JLabel("Dificultad:");
        lblDificultad.setBounds(50, 180, 100, 20);
        contentPane.add(lblDificultad);

        comboDificultad = new JComboBox<>(new String[]{"Fácil", "Media", "Difícil"});
        comboDificultad.setBounds(150, 180, 150, 20);
        contentPane.add(comboDificultad);

        JLabel lblDuracion = new JLabel("Duración (min):");
        lblDuracion.setBounds(50, 220, 100, 20);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setBounds(150, 220, 400, 20);
        contentPane.add(txtDuracion);
        txtDuracion.setColumns(10);

        JLabel lblFechaLimite = new JLabel("Fecha Límite (AAAA-MM-DD):");
        lblFechaLimite.setBounds(50, 260, 200, 20);
        contentPane.add(lblFechaLimite);

        txtFechaLimite = new JTextField();
        txtFechaLimite.setBounds(250, 260, 300, 20);
        contentPane.add(txtFechaLimite);
        txtFechaLimite.setColumns(10);

        JButton btnAgregarPregunta = new JButton("Agregar Pregunta");
        btnAgregarPregunta.setBounds(150, 300, 150, 30);
        contentPane.add(btnAgregarPregunta);
        btnAgregarPregunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una ventana para agregar preguntas
                CrearPregunta ventanaPregunta = new CrearPregunta(pregunta -> {
                    preguntas.add(pregunta);
                    JOptionPane.showMessageDialog(null, "Pregunta añadida con éxito.");
                });
                ventanaPregunta.setVisible(true);
            }
        });

        JButton btnCrear = new JButton("Crear Encuesta");
        btnCrear.setBounds(100, 400, 150, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String descripcion = txtDescripcion.getText();
                    if (descripcion.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.");
                        return;
                    }
                    String idTexto = txtID.getText();
                    if (idTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El ID no puede estar vacío.");
                        return;
                    }
                    int idEncuesta;
                    try {
                        idEncuesta = Integer.parseInt(idTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                        return;
                    }
                    String duracionTexto = txtDuracion.getText();
                    int duracion;
                    try {
                        duracion = Integer.parseInt(duracionTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La duración debe ser un número entero.");
                        return;
                    }
                    String fechaTexto = txtFechaLimite.getText();
                    LocalDateTime fechaLimite;
                    try {
                        fechaLimite = LocalDateTime.parse(fechaTexto + "T00:00:00");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "La fecha límite debe tener el formato AAAA-MM-DD.");
                        return;
                    }
                    String dificultad = comboDificultad.getSelectedItem().toString();

                    HashMap<String, String[]> estados = new HashMap<>();
                    HashMap<Integer, HashMap<String, Object>> respuestas = new HashMap<>();

                    // Crear el objeto Encuesta
                    Encuesta encuesta = new Encuesta("Creador", idEncuesta, true, descripcion, dificultad, duracion, fechaLimite, estados, preguntas, respuestas);

                    JOptionPane.showMessageDialog(null, "Encuesta creada exitosamente:\n" +
                            "ID: " + idEncuesta +
                            "\nDescripción: " + descripcion +
                            "\nDificultad: " + dificultad +
                            "\nDuración: " + duracion + " min" +
                            "\nPreguntas: " + preguntas.size());
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la encuesta: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(350, 400, 150, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
