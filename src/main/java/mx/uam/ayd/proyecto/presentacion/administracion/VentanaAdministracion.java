package mx.uam.ayd.proyecto.presentacion.administracion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaAdministracion extends JFrame {

	private JPanel contentPane;
	
	private ControlAdministración control;
	//Estructura de la Venatan Principal
	
	public VentanaAdministracion() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Botones de acuerdo a la funcion que se desee ejecutar
		JLabel lblMiAplicacin = new JLabel("Control Administrativo");
		lblMiAplicacin.setBounds(80, 20,300, 10);
		contentPane.add(lblMiAplicacin);
		
		JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.AddEmpleado();
			}
		});
		btnAgregarEmpleado.setBounds(15, 40, 260, 50);
		contentPane.add(btnAgregarEmpleado);
		
		JButton btnActualizar = new JButton("Actualizar Datos de Empleado");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//control.listarUsuarios();
			}
		});
		btnActualizar.setBounds(15, 100, 260, 50);
		contentPane.add(btnActualizar);
		
		JButton btnChek = new JButton("Checador de Horario I/O");
		btnChek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	control.listarUsuarios();*/
			}
		});
		btnChek.setBounds(15, 160, 260, 50);
		contentPane.add(btnChek);
	}
	
	// Inicia la sencuencia del control principal
	public void muestra(ControlAdministración control) {
		
		this.control = control;
		
		setVisible(true);
	}
}
