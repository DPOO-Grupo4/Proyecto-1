package Consola;

import javax.swing.*;

import System.LearningPath;
import System.Sistema;
import Usuarios.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import System.*;
import javax.swing.GroupLayout.Alignment;

public class ProfesorCalificarActividad1 extends JFrame{
    private Sistema sistema;

    public ProfesorCalificarActividad1(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Calificar Learning Paths");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();

        JLabel lblBienvenida = new JLabel("¡Bienvenido! Seleccione un Learning Path para calificar actividades:");
        lblBienvenida.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        ArrayList<LearningPath> LPs = ((Profesor) sistema.getSession()).getLPs();
        DefaultListModel<String> listLPSinterfaz = new DefaultListModel<>();
        for (LearningPath lp : LPs) {
            listLPSinterfaz.addElement(lp.getTitulo());
        }
        listLPSinterfaz.addElement("Volver");

        JList<String> lpList = new JList<>(listLPSinterfaz);
        lpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lpList);

        JButton selectButton = new JButton("Seleccionar");

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lpList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(ProfesorCalificarActividad1.this, "Por favor seleccione una opción.");
                } else if (selectedIndex == LPs.size()) { 
                    dispose();
                } else {
                    LearningPath lpSeleccionado = LPs.get(selectedIndex);
                    ProfesorCalificarActividad2 PCA2 = new ProfesorCalificarActividad2(sistema, lpSeleccionado);
                    PCA2.setVisible(true);
    				dispose();
                    //JOptionPane.showMessageDialog(ProfesorCalificarActividad1.this, "Seleccionaste: " + lpSeleccionado.getTitulo());
                }
            }
        });

        getContentPane().add(mainPanel);
        GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
        gl_mainPanel.setHorizontalGroup(
            gl_mainPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(lblBienvenida, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );
        gl_mainPanel.setVerticalGroup(
            gl_mainPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_mainPanel.createSequentialGroup()
                    .addComponent(lblBienvenida)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectButton))
        );
        mainPanel.setLayout(gl_mainPanel);
    }
}

     
/*
    public static void main(String[] args) {

        Sistema sistema = new Sistema(); 
        SwingUtilities.invokeLater(() -> {
            ProfesorCalificarActividad1 ventana = new ProfesorCalificarActividad1(sistema);
            LearningPath lpSeleccionado = LPs.get(selectedIndex);
            ProfesorCalificarActividad2 PCA2 = new ProfesorCalificarActividad2(sistema, lpSeleccionado);
            ventana.setVisible(true);
        });
    }
}
*/

