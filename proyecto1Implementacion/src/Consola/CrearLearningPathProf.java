package Consola;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import System.Sistema;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class CrearLearningPathProf extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public CrearLearningPathProf(Sistema sistema) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 601, 424);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCrearLearningPath = new JLabel("Crear Learning Path");
        lblCrearLearningPath.setHorizontalAlignment(SwingConstants.CENTER);
        lblCrearLearningPath.setBounds(143, 8, 150, 19);
        lblCrearLearningPath.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(lblCrearLearningPath);

        JLabel lblTitulo = new JLabel("Titulo");
        lblTitulo.setBounds(43, 38, 157, 14);
        contentPane.add(lblTitulo);

        JLabel lblDificultad = new JLabel("Dificultad (basico, intermedio, avanzado)");
        lblDificultad.setBounds(43, 163, 220, 14);
        contentPane.add(lblDificultad);

        JLabel lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setBounds(43, 100, 250, 14);
        contentPane.add(lblDescripcion);

        JLabel lblDuracion = new JLabel("Duración");
        lblDuracion.setBounds(43, 226, 220, 14);
        contentPane.add(lblDuracion);

        JCheckBox chckbxObligatorio = new JCheckBox("Obligatorio");
        chckbxObligatorio.setBounds(43, 287, 142, 23);
        contentPane.add(chckbxObligatorio);

        JTextField txtTitulo = new JTextField();
        txtTitulo.setBounds(43, 63, 383, 20);
        contentPane.add(txtTitulo);
        txtTitulo.setColumns(10);

        JTextField txtDificultad = new JTextField();
        txtDificultad.setBounds(43, 195, 383, 20);
        contentPane.add(txtDificultad);
        txtDificultad.setColumns(10);

        JTextField txtDescripcion = new JTextField();
        txtDescripcion.setBounds(46, 132, 380, 20);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JTextField txtDuracion = new JTextField();
        txtDuracion.setBounds(43, 247, 383, 20);
        contentPane.add(txtDuracion);
        txtDuracion.setColumns(10);

        JButton btnCrearlearningPath = new JButton("Crear Learning Path");
        btnCrearlearningPath.setBounds(43, 320, 150, 23);
        contentPane.add(btnCrearlearningPath);

        btnCrearlearningPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = txtTitulo.getText();
                    String dificultad = txtDificultad.getText();
                    String descripcion = txtDescripcion.getText();
                    int duracion = Integer.parseInt(txtDuracion.getText());

                    int respuesta = JOptionPane.showConfirmDialog(
                        CrearLearningPathProf.this,
                        "¿Desea continuar?",
                        "",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (respuesta == JOptionPane.YES_OPTION) {
                        int rating = 0;
                        LocalDateTime fechaCreacion = LocalDateTime.now();
                        LocalDateTime fechaModificacion = LocalDateTime.now();
                        boolean obligatorio = chckbxObligatorio.isSelected();

                        sistema.crearLearningPath(sistema.getSession().getLogin(), titulo, descripcion, dificultad, duracion, rating, fechaCreacion, fechaModificacion, obligatorio);

                        JOptionPane.showMessageDialog(
                            CrearLearningPathProf.this,
                            "Learning Path creado exitosamente."
                        );
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        CrearLearningPathProf.this,
                        "Por favor, ingrese un número válido para la duración.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        CrearLearningPathProf.this,
                        "Error al crear el Learning Path: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(219, 320, 89, 23);
        contentPane.add(btnRegresar);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                    CrearLearningPathProf.this,
                    "¿Desea cerrar esta ventana?",
                    "",
                    JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    InicioProfesor inicioProfesor = new InicioProfesor(sistema);
                    inicioProfesor.setVisible(true);
                    dispose();
                }
            }
        });
    }
}
