package Consola;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import System.PreguntaAbierta;

public class CrearPreguntaAbierta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtPregunta;
    private JTextArea txtRespuestaUsuario;

    public CrearPreguntaAbierta() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear Pregunta Abierta");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(180, 10, 240, 30);
        contentPane.add(lblTitulo);

        JLabel lblPregunta = new JLabel("Pregunta:");
        lblPregunta.setBounds(50, 60, 100, 20);
        contentPane.add(lblPregunta);

        txtPregunta = new JTextField();
        txtPregunta.setBounds(150, 60, 400, 20);
        contentPane.add(txtPregunta);
        txtPregunta.setColumns(10);

        JLabel lblRespuestaUsuario = new JLabel("Respuesta (opcional):");
        lblRespuestaUsuario.setBounds(50, 100, 150, 20);
        contentPane.add(lblRespuestaUsuario);

        txtRespuestaUsuario = new JTextArea();
        txtRespuestaUsuario.setLineWrap(true);
        txtRespuestaUsuario.setWrapStyleWord(true);
        txtRespuestaUsuario.setBounds(150, 130, 400, 100);
        contentPane.add(txtRespuestaUsuario);

        JButton btnCrear = new JButton("Crear Pregunta");
        btnCrear.setBounds(100, 300, 150, 30);
        contentPane.add(btnCrear);
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String pregunta = txtPregunta.getText();
                    if (pregunta.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La pregunta no puede estar vacía.");
                        return;
                    }
                    String respuestaUsuario = txtRespuestaUsuario.getText();

                    // Crear el objeto PreguntaAbierta
                    PreguntaAbierta preguntaAbierta = new PreguntaAbierta(pregunta);
                    preguntaAbierta.setRespuestaUsuario(respuestaUsuario);

                    JOptionPane.showMessageDialog(null, "Pregunta abierta creada exitosamente.");
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
