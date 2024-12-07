package Consola;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System.Actividad;
import System.LearningPath;
import System.Sistema;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class ModificarActividad2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInfo;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarActividad2 frame = new ModificarActividad2();
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
	public ModificarActividad2(Sistema sistema, Actividad actividad, LearningPath LP) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInfo= new JTextField();
		txtInfo.setBounds(29, 69, 369, 20);
		contentPane.add(txtInfo);
		txtInfo.setColumns(10);
		
		JLabel lblInfo = new JLabel("Escribe la informacion nueva aquí:");
		lblInfo.setBounds(29, 44, 261, 14);
		contentPane.add(lblInfo);
		
		JButton btnModDescripcion = new JButton("Modificar Descripcion");
		btnModDescripcion.setBounds(29, 100, 188, 23);
		contentPane.add(btnModDescripcion);
		btnModDescripcion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String descripcion= txtInfo.getText();
				try {
					sistema.modificarDescripcionActividad(actividad, LP, descripcion);
					JOptionPane.showMessageDialog(
	                           ModificarActividad2.this,
	                            "Modificado exitosamente."
	                        );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
				            "Ocurrió un error al modificar la descripción de la actividad.\nPor favor, inténtelo de nuevo.\nDetalle: " + e1.getMessage(),
				            "Error",
				            JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnModDificultad = new JButton("Modificar Dificultad");
		btnModDificultad.setBounds(227, 100, 171, 23);
		contentPane.add(btnModDificultad);
		btnModDificultad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dificultad= txtInfo.getText();
				try {
					sistema.modificarDificultadActividad(actividad, LP, dificultad);
					JOptionPane.showMessageDialog(
	                           ModificarActividad2.this,
	                            "Modificado exitosamente."
	                        );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
				            "Ocurrió un error al modificar la dificultad de la actividad. Recuerde que debe ser basico, intermedio o avanzado. \nPor favor, inténtelo de nuevo.\nDetalle: " + e1.getMessage(),
				            "Error",
				            JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnModDuracion = new JButton("Modificar Duración");
		btnModDuracion.setBounds(29, 134, 188, 23);
		contentPane.add(btnModDuracion);
		btnModDuracion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String duracion1= txtInfo.getText();
				int duracion= Integer.parseInt(duracion1);
				try {
					sistema.modificarDuracionActividad(actividad, LP, duracion);
					JOptionPane.showMessageDialog(
	                           ModificarActividad2.this,
	                            "Modificado exitosamente."
	                        );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
				            "Ocurrió un error al modificar la dificultad de la actividad. Recuerde que debe ser en minutos. \nPor favor, inténtelo de nuevo.\nDetalle: " + e1.getMessage(),
				            "Error",
				            JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnModificarPregunta = new JButton("Modificar pregunta");
		btnModificarPregunta.setBounds(29, 227, 208, 23);
		contentPane.add(btnModificarPregunta);
		btnModificarPregunta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ModificarActividad3(sistema, actividad, LP).setVisible(true);
				dispose();
				
			}
			
		});
		
		JLabel lblPregunta = new JLabel("Si deseas modificar una pregunta deja el texto en blaco");
		lblPregunta.setBounds(29, 206, 369, 14);
		contentPane.add(lblPregunta);
		
		JButton btnModObli = new JButton("Modificar Obligatoriedad");
		btnModObli.setBounds(227, 134, 171, 23);
		contentPane.add(btnModObli);
		btnModObli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String Obligatoriedad1= txtInfo.getText();
				boolean Obligatoriedad= Boolean.parseBoolean(Obligatoriedad1);
				try {
					sistema.modificarObligatoriedadActividad(actividad, LP, Obligatoriedad);
					JOptionPane.showMessageDialog(
                           ModificarActividad2.this,
                            "Modificado exitosamente."
                        );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
				            "Ocurrió un error al modificar la obligatoriedad de la actividad. Recuerde que debe ser true o false. \nPor favor, inténtelo de nuevo.\nDetalle: " + e1.getMessage(),
				            "Error",
				            JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnModFechaLimite = new JButton("Modificar fecha limite (YYYY-MM-DDTHH:mm:ss)");
        btnModFechaLimite.setBounds(29, 168, 261, 23);
        contentPane.add(btnModFechaLimite);
        btnModFechaLimite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String FechaLimite1= txtInfo.getText();
				try {
					LocalDateTime fechaLimite= LocalDateTime.parse(FechaLimite1);
					sistema.modificarFechaLímiteActividad(actividad, LP, fechaLimite);
					JOptionPane.showMessageDialog(
	                           ModificarActividad2.this,
	                            "Modificado exitosamente."
	                        );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
				            "Ocurrió un error al modificar la dificultad de la actividad. Recuerde que debe ser en minutos. \nPor favor, inténtelo de nuevo.\nDetalle: " + e1.getMessage(),
				            "Error",
				            JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setSize(129, 21);
		btnRegresar.setLocation(161, 261);
        contentPane.add(btnRegresar);
        btnRegresar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditarLearningPathTrans IS= new EditarLearningPathTrans(sistema,LP);
				IS.setVisible(true);
				dispose();
				
			}
        	
        });
	}
}
