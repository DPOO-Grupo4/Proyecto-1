package Consola;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import System.Sistema;

public class CrearLearningPathProfesor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtDescripcion;
	private JTextField txtDificultad;
	private JTextField txtDuracion;
	JLabel lblTitulo,lblDificultad, lblDescripcion, lblDuracion; 
	JButton btnSalir, btnCrearLearningPath;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearLearningPathProfesor frame = new CrearLearningPathProfesor();
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
	public CrearLearningPathProfesor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtTitulo = new JTextField();
		txtTitulo.addActionListener(this);
		txtTitulo.setColumns(10);
		
		JLabel lblCrearLP = new JLabel("Crear Learning Path");
		
		txtDescripcion = new JTextField();
		txtDescripcion.addActionListener(this);
		txtDescripcion.setColumns(10);
		
		txtDificultad = new JTextField();
		txtDificultad.addActionListener(this);
		txtDificultad.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.addActionListener(this);
		txtDuracion.setColumns(10);
		
		lblTitulo = new JLabel("Titulo:");
		
		lblDificultad= new JLabel("Dificultad (Basico, Intermedio, Avanzado) :");
		
		lblDescripcion = new JLabel("Descripcion general del learning Path");
		
		lblDuracion = new JLabel("Duración en minutos:");
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		
		btnCrearLearningPath = new JButton("Crear Learning Path");
		btnCrearLearningPath.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(153)
							.addComponent(lblCrearLP))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTitulo, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addComponent(txtDescripcion)
								.addComponent(txtDificultad, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addComponent(txtDuracion, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addComponent(lblTitulo)
								.addComponent(lblDificultad, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDuracion)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnCrearLearningPath)
									.addGap(35)
									.addComponent(btnSalir)))))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCrearLP)
					.addGap(10)
					.addComponent(lblTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblDificultad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblDescripcion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDificultad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblDuracion)
					.addGap(3)
					.addComponent(txtDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrearLearningPath)
						.addComponent(btnSalir))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		    if (btnCrearLearningPath == e.getSource()) { 
		        try {
		          
		            String titulo = txtTitulo.getText();
		            String dificultad = txtDificultad.getText();
		            String descripcion = txtDescripcion.getText();
		            int duracion = Integer.parseInt(txtDuracion.getText());

		            
		            int respuesta = JOptionPane.showConfirmDialog(
		                this, 
		                "¿Desea continuar con la creación del Learning Path?", 
		                "Confirmar", 
		                JOptionPane.YES_NO_OPTION
		            );

		            if (respuesta == JOptionPane.YES_OPTION) {
		            	
		            	int rating = 5;
		        		LocalDateTime fechaCreacion = LocalDateTime.now();
		        		LocalDateTime fechaModificacion = LocalDateTime.now();
		        		boolean nuevo = true;
		                Sistema sistema = new Sistema();
						sistema.crearLearningPath("abcd", titulo, dificultad, descripcion, duracion, rating, fechaCreacion, fechaModificacion,
								nuevo);
		                JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la duración.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, "Error al crear el Learning Path: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    } else if (btnSalir == e.getSource()) { 
		        dispose(); 
		        
		    }
		}

		
		
	}

