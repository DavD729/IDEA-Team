package mx.uam.ayd.proyecto.presentacion.administracion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaAdministracion extends JFrame {

	private JPanel contentPane;
	
	private ControlAdministración control;
	/*Estructura de la Venatan Principal*/
	
	public VentanaAdministracion() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(355, 100, 240, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font nuevaFuente = new Font("Arial", Font.CENTER_BASELINE, 11);
		Font nuevaFuenteTitulos = new Font("Arial", Font.BOLD, 18);
		
		/*Botones de acuerdo a la funcion que se desee ejecutar*/
		JLabel lblControl = new JLabel("Control Administrativo");
		lblControl.setBounds(13, 10,300, 16);
		lblControl.setFont(nuevaFuenteTitulos);
		contentPane.add(lblControl);
		
		JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.AddEmpleado();
			}
		});
		btnAgregarEmpleado.setFont(nuevaFuente);
		btnAgregarEmpleado.setBounds(22, 40,178, 29);
		contentPane.add(btnAgregarEmpleado);
		
		JButton btnActualizar = new JButton("Actualizar Datos de Empleado");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.ActualizarEmpleado();
			}
		});
		btnActualizar.setFont(nuevaFuente);
		btnActualizar.setBounds(22, 80,178, 29);
		contentPane.add(btnActualizar);
		
		JButton btnChek = new JButton("Checador de Horario I/O");
		btnChek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.CheckIO();
			}
		});
		btnChek.setFont(nuevaFuente);
		btnChek.setBounds(22, 120,178, 29);
		contentPane.add(btnChek);
		
		JButton btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		btnRegresa.setFont(nuevaFuente);
		btnRegresa.setBounds(22, 160,178, 29);
		contentPane.add(btnRegresa);
	}
	
	/* Inicia la sencuencia del control principal*/
	public void muestra(ControlAdministración control) {
		
		this.control = control;
		
		setVisible(true);
	}
}
