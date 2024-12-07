package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;

public class VerReseniasProf extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VerReseniasProf(Sistema sistema) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBounds(10, 79, 414, 109);
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(botonesPanel);

        JLabel lbltitulo = new JLabel("¿De cuál Learning Path quieres ver las reseñas?");
        lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltitulo.setBounds(24, 25, 400, 30);
        contentPane.add(lbltitulo);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setLocation(164, 199);
        btnRegresar.setSize(106, 30);
        contentPane.add(btnRegresar);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioProfesor IS = new InicioProfesor(sistema);
                IS.setVisible(true);
                dispose();
            }
        });

        try {
            ArrayList<LearningPath> creados = sistema.getLPsCreados(sistema.getSession().getLogin());
            for (LearningPath LP : creados) {
                String titulo = LP.getTitulo();
                JButton botonLP = new JButton(titulo);
                botonesPanel.add(botonLP);

                botonLP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new VerResenias2(sistema,LP).setVisible(true);
                        dispose();
                    }
                });
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

            
