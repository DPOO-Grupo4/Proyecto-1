package Consola;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import System.*;
import Usuarios.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ProfesorCalificarActividad2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */

	public ProfesorCalificarActividad2(Sistema sistema, LearningPath lpSeleccionado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(128, 255, 0));
		
		setContentPane(contentPane);
		
		JPanel scrollPane = new JPanel();
		
		
		List list = new List();
        list.setBounds(361, 40, 202, 130);

        
        
		ArrayList<Actividad> activities = lpSeleccionado.getActivities();
		for (int i=0; i < activities.size(); i++) {
			list.add("Actividad " + String.valueOf(i), i);
			
		}
		scrollPane.add(list);
		list.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				int opcion = list.getSelectedIndex();
				Actividad ACEscogida = activities.get(opcion);
				MenuCalificarEnlistados MCE = new MenuCalificarEnlistados(sistema, ACEscogida, lpSeleccionado);
				MCE.setVisible(true);
				dispose();
				
			}
		});
		
		//DefaultListModel<String> listActividadesinterfaz = new DefaultListModel<>();
        //for (LearningPath lp : LPs) {
        //	listActividadesinterfaz.addElement(lp.getTitulo());
        //}
       // listLPSinterfaz.addElement("Volver");
		
		JButton btnRegresar = new JButton("Regresar");
		
		JLabel lblTitulo = new JLabel("Escoja la actividad que desea calificar:");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(169)
							.addComponent(btnRegresar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(219)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(280, Short.MAX_VALUE)
					.addComponent(lblTitulo)
					.addGap(99))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(lblTitulo)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegresar)
					.addGap(30))
		);
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorCalificarActividad1 PCA1 = new ProfesorCalificarActividad1(sistema);
				PCA1.setVisible(true);
				dispose();
			}
	});
		contentPane.setLayout(gl_contentPane);
		

}
}