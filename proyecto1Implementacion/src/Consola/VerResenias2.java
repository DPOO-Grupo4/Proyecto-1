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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;

public class VerResenias2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VerResenias2(Sistema sistema, LearningPath LP) {
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

        JLabel lbltitulo = new JLabel("¿De cuál Actividad quieres ver las reseñas?");
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

        ArrayList<Actividad> creadas = LP.getActivities();
		for (Actividad actividad : creadas) {
		    String titulo = actividad.getID() + "-" + actividad.getDescripcion();

		    JButton botonAct = new JButton(titulo);
		    botonesPanel.add(botonAct);

		    botonAct.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JDialog infoDialog = new JDialog(VerResenias2.this, "Reseñas de: " + titulo, true);
		            infoDialog.setSize(400, 300);
		            infoDialog.getContentPane().setLayout(new BorderLayout());

		            JTextArea textArea = new JTextArea();
		            textArea.setEditable(false);

		            ArrayList<String> resenias;
					try {
						resenias = sistema.getReseñasActividad(actividad.getID());
						for (String resenia : resenias) {
		                textArea.append(resenia + "\n");
		            }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		            

		            infoDialog.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

		            JButton btnCerrar = new JButton("Cerrar");
		            btnCerrar.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    infoDialog.dispose();
		                }
		            });
		            infoDialog.getContentPane().add(btnCerrar, BorderLayout.SOUTH);

		            infoDialog.setLocationRelativeTo(VerResenias2.this);
		            infoDialog.setVisible(true);
		        }
		    });
		}
    }
}

