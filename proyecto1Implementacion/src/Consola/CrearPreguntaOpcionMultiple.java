package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Opcion;
import System.PreguntaOpcionMultiple;

public class CrearPreguntaOpcionMultiple extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEnunciado, txtNuevaOpcion;
    private JComboBox<String> comboOpciones;
    private ArrayList<Opcion> opciones = new ArrayList<>();
    private Opcion respuestaCorrecta;

    public CrearPreguntaOpcionMultiple() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Pregunta de Opción Múltiple");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(150, 10, 300, 30);
        contentPane.add(lblTitulo);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        lblEnunciado.setBounds(50, 60, 100, 20);
        contentPane.add(lblEnunciado);

        txtEnunciado = new JTextField();
        txtEnunciado.setBounds(150, 60, 400, 20);
        contentPane.add(txtEnunciado);
        txtEnunciado.setColumns(10);

        JLabel lblOpciones = new JLabel("Opciones:");
        lblOpciones.setBounds(50, 100, 100, 20);
        contentPane.add(lblOpciones);

        comboOpciones = new JComboBox<>();
        comboOpciones.setBounds(150, 100, 300, 20);
        contentPane.add(comboOpciones);

        txtNuevaOpcion = new JTextField();
        txtNuevaOpcion.setBounds(150, 140, 300, 20);
        contentPane.add(txtNuevaOpcion);

        JButton btnAgregarOpcion = new JButton("Agregar Opción");
        btnAgregarOpcion.setBounds(460, 140, 120, 20);
        contentPane.add(btnAgregarOpcion);
        btnAgregarOpcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcionTexto = txtNuevaOpcion.getText();
                if (opcionTexto != null && !opcionTexto.isEmpty()) {
                    Opcion nuevaOpcion = new Opcion(opcionTexto);
                    opciones.add(nuevaOpcion);
                    comboOpciones.addItem(opcionTexto);
                    txtNuevaOpcion.setText("");
                    JOptionPane.showMessageDialog(null, "Opción añadida.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un texto para la opción.");
                }
            }
        });

        JButton btnSeleccionarCorrecta = new JButton("Seleccionar Correcta");
        btnSeleccionarCorrecta.setBounds(150, 180, 200, 20);
        contentPane.add(btnSeleccionarCorrecta);
        btnSeleccionarCorrecta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = comboOpciones.getSelectedIndex();
                if (index >= 0) {
                    respuestaCorrecta = opciones.get(index);
                    JOptionPane.showMessageDialog(null, "Opción correcta seleccionada: " + respuestaCorrecta.getTexto());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una opción de la lista.");
                }
            }
        });

        JButton btnCrear = new JButton("Crear Pregunta");
        btnCrear.setBounds(100, 300, 150, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String enunciado = txtEnunciado.getText();
                    if (enunciado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El enunciado no puede estar vacío.");
                        return;
                    }
                    if (respuestaCorrecta == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción correcta.");
                        return;
                    }
                    if (opciones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe agregar al menos una opción.");
                        return;
                    }

                    PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(
                        respuestaCorrecta, enunciado, 0, opciones // ID ficticio
                    );

                    JOptionPane.showMessageDialog(null, "Pregunta creada exitosamente.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la pregunta: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 300, 150, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
