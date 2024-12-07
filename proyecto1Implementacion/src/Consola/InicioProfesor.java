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
import System.Sistema;

public class InicioProfesor extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton CrearLearningPath, EditarLearningPath, CalificaActividad, VerActividadesCreadas, VerLearningPathsCreados, VerResenas, CerrarSesion;
	
	
	public InicioProfesor(Sistema sistema) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		CrearLearningPath = new JButton("Crear Learning Path");
		CrearLearningPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CrearLearningPath == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new CrearLearningPathProf(sistema).setVisible(true);
						dispose();
			}}}
		});
		CrearLearningPath.setBounds(32, 75, 236, 23);
		
		EditarLearningPath = new JButton("Editar Learning Path");
		EditarLearningPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EditarLearningPath == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new EditarLearningPath(sistema).setVisible(true);
						dispose();
			}}}
		});
		EditarLearningPath.setBounds(284, 75, 217, 23);
		
		CalificaActividad = new JButton("Calificar Actividad");
		CalificaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CalificaActividad == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new ProfesorCalificarActividad1(sistema).setVisible(true);
						dispose();
			}}}
		});
		CalificaActividad.setBounds(32, 116, 236, 23);
		
		VerActividadesCreadas = new JButton("Ver Actividades Creadas");
		VerActividadesCreadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VerActividadesCreadas == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new VerActividadesCreadasProf(sistema).setVisible(true);
						dispose();
			}}}
		});
		VerActividadesCreadas.setBounds(284, 116, 217, 23);
		
		VerLearningPathsCreados = new JButton("Ver Learning Paths Creados");
		VerLearningPathsCreados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VerLearningPathsCreados == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new VerLPsCreadosProf(sistema).setVisible(true);
						dispose();
			}}}
		});
		VerLearningPathsCreados.setBounds(32, 157, 236, 23);
		
		VerResenas = new JButton("Ver Reseñas");
		VerResenas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VerResenas == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						new VerReseniasProf(sistema).setVisible(true);
						dispose();
			}}}
		});
		VerResenas.setBounds(284, 157, 217, 23);
		
		CerrarSesion = new JButton("Cerrar Sesión");
		CerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CerrarSesion == e.getSource()) {
					int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "", JOptionPane.YES_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						InicioSesion IS = new InicioSesion(sistema);
						IS.setVisible(true);
						dispose();
			}}}
		});
		CerrarSesion.setBounds(205, 210, 143, 23);
		
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

	
}
