package Consola;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoverActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverActividad frame = new RemoverActividad();
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
	public RemoverActividad(Sistema sistema, LearningPath LP ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel botonesPanel = new JPanel();
		botonesPanel.setBounds(49, 60, 328, 128);
		botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		contentPane.add(botonesPanel);
		
		JLabel lblTitulo = new JLabel("¿Qué actividad desea remover? ");
		lblTitulo.setBounds(91, 21, 240, 19);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo);
        
        JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setLocation(154, 199);
		btnRegresar.setSize(95, 31);
		contentPane.add(btnRegresar, BorderLayout.SOUTH);
        lblTitulo.setBounds(62, 11, 311, 31);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
		
		ArrayList<Actividad> actividades= LP.getActivities(); 
		for (Actividad actividad: actividades) {
			String IDString= String.valueOf(actividad.getID());
			String DescripAct= actividad.getDescripcion();
			JButton btnActividad= new JButton(IDString +" - "+ DescripAct);
			botonesPanel.add(btnActividad);
			btnActividad.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(RemoverActividad.this,
                            "¿Está seguro de que desea remover la actividad " + IDString + "?","",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
							sistema.borrarActividadCreatedActivities(actividad.getID(), LP.getTitulo());
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(RemoverActividad.this,
						            "Ocurrió un error al intentar eliminar la actividad. Por favor, inténtelo nuevamente.\n"
						            + "Detalle del error: " + e1.getMessage(),
						            "Error",
						            JOptionPane.ERROR_MESSAGE);
						};
                        JOptionPane.showMessageDialog(RemoverActividad.this,
                                "La actividad " + IDString + " ha sido removida.");
					
				}}
				
			});
			
		}
	}

}
