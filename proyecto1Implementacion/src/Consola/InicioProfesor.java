package Consola;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioProfesor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton CrearLearningPath, EditarLearningPath, CalificaActividad, VerActividadesCreadas, VerLearningPathsCreados, VerResenas, CerrarSesion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioProfesor frame = new InicioProfesor();
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
	public InicioProfesor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		CrearLearningPath = new JButton("Crear Learning Path");
		CrearLearningPath.addActionListener(this);
		CrearLearningPath.setBounds(94, 75, 165, 23);
		
		EditarLearningPath = new JButton("Editar Learning Path");
		EditarLearningPath.addActionListener(this);
		EditarLearningPath.setBounds(322, 75, 149, 23);
		
		CalificaActividad = new JButton("Calificar Actividad");
		CalificaActividad.addActionListener(this);
		CalificaActividad.setBounds(94, 116, 165, 23);
		
		VerActividadesCreadas = new JButton("Ver Actividades Creadas");
		VerActividadesCreadas.addActionListener(this);
		VerActividadesCreadas.setBounds(322, 116, 149, 23);
		
		VerLearningPathsCreados = new JButton("Ver Learning Paths Creados");
		VerLearningPathsCreados.addActionListener(this);
		VerLearningPathsCreados.setBounds(94, 157, 165, 23);
		
		VerResenas = new JButton("Ver Reseñas");
		VerResenas.addActionListener(this); 
		VerResenas.setBounds(322, 157, 149, 23);
		
		CerrarSesion = new JButton("Cerrar Sesión");
		CerrarSesion.setBounds(217, 210, 143, 23);
		
		JLabel LabelBienvedida = new JLabel("BIENVENID@");
		LabelBienvedida.setFont(new Font("Arial", Font.BOLD, 30));
		LabelBienvedida.setBounds(186, 32, 210, 31);
		contentPane.setLayout(null);
		contentPane.add(CrearLearningPath);
		contentPane.add(EditarLearningPath);
		contentPane.add(CalificaActividad);
		contentPane.add(VerActividadesCreadas);
		contentPane.add(VerLearningPathsCreados);
		contentPane.add(VerResenas);
		contentPane.add(CerrarSesion); 
		contentPane.add(LabelBienvedida);
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (CrearLearningPath == e.getSource()) {
			int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				new CrearLearningPathProfesor().setVisible(true);
				JOptionPane.showMessageDialog(CalificaActividad, e);
				dispose();
				
			}
		}
		
	}
}
