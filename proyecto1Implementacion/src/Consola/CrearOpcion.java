package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Opcion;

public class CrearOpcion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEnunciado;
    private JTextField txtID;
    private JTextArea txtExplicacion;
    private JCheckBox checkCorrect;

    public CrearOpcion() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Opción");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(220, 10, 150, 30);
        contentPane.add(lblTitulo);

        JLabel lblEnunciado = new JLabel("Enunciado:");
        lblEnunciado.setBounds(50, 60, 100, 20);
        contentPane.add(lblEnunciado);

        txtEnunciado = new JTextField();
        txtEnunciado.setBounds(150, 60, 400, 20);
        contentPane.add(txtEnunciado);
        txtEnunciado.setColumns(10);

        JLabel lblID = new JLabel("ID Opción:");
        lblID.setBounds(50, 100, 100, 20);
        contentPane.add(lblID);

        txtID = new JTextField();
        txtID.setBounds(150, 100, 400, 20);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblExplicacion = new JLabel("Explicación:");
        lblExplicacion.setBounds(50, 140, 100, 20);
        contentPane.add(lblExplicacion);

        txtExplicacion = new JTextArea();
        txtExplicacion.setBounds(150, 140, 400, 80);
        txtExplicacion.setLineWrap(true);
        txtExplicacion.setWrapStyleWord(true);
        contentPane.add(txtExplicacion);

        JLabel lblCorrect = new JLabel("¿Es correcta?");
        lblCorrect.setBounds(50, 240, 100, 20);
        contentPane.add(lblCorrect);

        checkCorrect = new JCheckBox();
        checkCorrect.setBounds(150, 240, 20, 20);
        contentPane.add(checkCorrect);

        JButton btnCrear = new JButton("Crear Opción");
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
                    String explicacion = txtExplicacion.getText();
                    if (explicacion.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La explicación no puede estar vacía.");
                        return;
                    }
                    String idTexto = txtID.getText();
                    if (idTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El ID no puede estar vacío.");
                        return;
                    }
                    int idOpcion;
                    try {
                        idOpcion = Integer.parseInt(idTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                        return;
                    }
                    boolean correcta = checkCorrect.isSelected();

                    // Crear el objeto Opción
                    Opcion opcion = new Opcion(explicacion, correcta, enunciado, idOpcion);

                    JOptionPane.showMessageDialog(null, "Opción creada exitosamente:\n" +
                            "Enunciado: " + opcion.getEnunciado() + "\nID: " + opcion.getID() +
                            "\nExplicación: " + opcion.getExplicacion() +
                            "\n¿Correcta?: " + (opcion.getCorrect() ? "Sí" : "No"));
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la opción: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(350, 300, 150, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
