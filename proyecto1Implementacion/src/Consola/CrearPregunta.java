package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Pregunta;

public class CrearPregunta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEnunciado;
    private JTextField txtID;

    public CrearPregunta() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Pregunta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(180, 10, 150, 30);
        contentPane.add(lblTitulo);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        lblEnunciado.setBounds(50, 70, 100, 20);
        contentPane.add(lblEnunciado);

        txtEnunciado = new JTextField();
        txtEnunciado.setBounds(150, 70, 300, 20);
        contentPane.add(txtEnunciado);
        txtEnunciado.setColumns(10);

        JLabel lblID = new JLabel("ID Pregunta:");
        lblID.setBounds(50, 120, 100, 20);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(150, 120, 300, 20);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JButton btnCrear = new JButton("Crear Pregunta");
        btnCrear.setBounds(80, 200, 150, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String enunciado = txtEnunciado.getText();
                    if (enunciado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El enunciado no puede estar vacío.");
                        return;
                    }
                    String idTexto = txtID.getText();
                    if (idTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El ID no puede estar vacío.");
                        return;
                    }
                    int idPregunta;
                    try {
                        idPregunta = Integer.parseInt(idTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                        return;
                    }

                    // Crear el objeto Pregunta
                    Pregunta pregunta = new Pregunta(enunciado, idPregunta);

                    JOptionPane.showMessageDialog(null, "Pregunta creada exitosamente:\n" +
                            "Enunciado: " + pregunta.getEnunciado() + "\nID: " + pregunta.getID());
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la pregunta: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(280, 200, 150, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
