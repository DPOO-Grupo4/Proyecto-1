package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.Sistema;
import System.Tarea;

public class CrearTarea extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtDescripcion, txtDificultad, txtDuracion, txtFechaLimite, txtComentario;

    public CrearTarea(Sistema sistema) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Tarea");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(180, 10, 140, 30);
        contentPane.add(lblTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(50, 60, 100, 20);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(160, 60, 250, 20);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JLabel lblDificultad = new JLabel("Dificultad:");
        lblDificultad.setBounds(50, 100, 100, 20);
        contentPane.add(lblDificultad);

        txtDificultad = new JTextField();
        txtDificultad.setBounds(160, 100, 250, 20);
        contentPane.add(txtDificultad);
        txtDificultad.setColumns(10);

        JLabel lblDuracion = new JLabel("Duración:");
        lblDuracion.setBounds(50, 140, 100, 20);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setBounds(160, 140, 250, 20);
        contentPane.add(txtDuracion);
        txtDuracion.setColumns(10);

        JLabel lblFechaLimite = new JLabel("Fecha Límite:");
        lblFechaLimite.setBounds(50, 180, 100, 20);
        contentPane.add(lblFechaLimite);

        txtFechaLimite = new JTextField();
        txtFechaLimite.setBounds(160, 180, 250, 20);
        contentPane.add(txtFechaLimite);
        txtFechaLimite.setColumns(10);

        JLabel lblComentario = new JLabel("Comentario:");
        lblComentario.setBounds(50, 220, 100, 20);
        contentPane.add(lblComentario);

        txtComentario = new JTextField();
        txtComentario.setBounds(160, 220, 250, 20);
        contentPane.add(txtComentario);
        txtComentario.setColumns(10);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(100, 300, 120, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String descripcion = txtDescripcion.getText();
                    String dificultad = txtDificultad.getText();
                    int duracion = Integer.parseInt(txtDuracion.getText());
                    LocalDateTime fechaLimite = LocalDateTime.parse(txtFechaLimite.getText());
                    String comentario = txtComentario.getText();

                    HashMap<String, String[]> estados = new HashMap<>(); // Inicializamos vacío por ahora
                    Tarea nuevaTarea = new Tarea(sistema.getSession().getLogin(), 
                                                  sistema.generarIdActividad(),
                                                  true, // Por defecto obligatorio
                                                  descripcion, 
                                                  dificultad, 
                                                  duracion, 
                                                  fechaLimite, 
                                                  estados, 
                                                  comentario);

                    sistema.agregarActividad(nuevaTarea); // Método ficticio que debes implementar
                    JOptionPane.showMessageDialog(null, "Tarea creada exitosamente.");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear la tarea: " + ex.getMessage());
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 300, 120, 30);
        contentPane.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

