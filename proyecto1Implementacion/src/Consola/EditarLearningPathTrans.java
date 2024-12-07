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

import javax.swing.JButton;

public class EditarLearningPathTrans extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarLearningPathTrans frame = new EditarLearningPathTrans();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditarLearningPathTrans(Sistema sistema, LearningPath LP) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditarTitulo = new JLabel("Editar " + LP.getTitulo());
		lblEditarTitulo.setBounds(148, 35, 131, 19);
		lblEditarTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblEditarTitulo);
		
		JButton btnRemAct = new JButton("Remover Actividad");
		btnRemAct.setBounds(22, 76, 359, 31);
		contentPane.add(btnRemAct);
		btnRemAct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					new RemoverActividad(sistema, LP).setVisible(true);
					dispose();
				
			}}
			
		});
		
		JButton btnAnaAct = new JButton("Añadir Activdidad");
		btnAnaAct.setBounds(22, 118, 359, 31);
		contentPane.add(btnAnaAct);
		btnAnaAct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					//new AnadirActividad(sistema, LP).setVisible(true);
					dispose();
				
			}}
			
		});
		
		JButton btnModAct = new JButton("Modificar Actividad Existente");
		btnModAct.setBounds(22, 160, 359, 31);
		contentPane.add(btnModAct);
		btnModAct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					new ModificarActividad(sistema, LP).setVisible(true);
					dispose();
				
			}}
			
		});
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(162, 213, 89, 23);
		contentPane.add(btnRegresar);
		btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					new EditarLearningPath(sistema).setVisible(true);
					dispose();
				
			}}
			
		});
	}

}
