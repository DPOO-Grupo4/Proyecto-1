package Consola;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.LearningPath;
import System.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditarLearningPath extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public EditarLearningPath(Sistema sistema) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditarLearningPath = new JLabel("Editar Learning Path");
		lblEditarLearningPath.setBounds(131, 11, 153, 19);
		lblEditarLearningPath.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblEditarLearningPath);
		
		JComboBox<String> cmbBoxLPs = new JComboBox<String>();
		cmbBoxLPs.setMaximumRowCount(30);
		cmbBoxLPs.setBounds(39, 78, 340, 22);
		contentPane.add(cmbBoxLPs);
		
		HashMap<String,LearningPath> MLPs= new HashMap<String,LearningPath>();
		
		try {
			ArrayList<LearningPath> LPsCreados= sistema.getLPsCreados(sistema.getSession().getLogin());
			for (LearningPath LP: LPsCreados) {
				MLPs.put(LP.getTitulo(), LP);
				cmbBoxLPs.addItem(LP.getTitulo());
				
			}
			cmbBoxLPs.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String seleccionTitulo = (String) cmbBoxLPs.getSelectedItem();
					LearningPath seleccion= MLPs.get(seleccionTitulo);
					if (seleccion != null) {
						new EditarLearningPathTrans(sistema, seleccion).setVisible(true);
						dispose();
					}
				}
			});
			JButton btnRegresar = new JButton("Regresar");
			btnRegresar.setBounds(162, 213, 89, 23);
			contentPane.add(btnRegresar);
			btnRegresar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new InicioProfesor(sistema).setVisible(true);
						dispose();
					
				}}
				
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
