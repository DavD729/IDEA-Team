package mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

import mx.uam.ayd.proyecto.presentacion.administracion.agregarEmpleados.ControlAddEmpleado;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")

@Component


public class VentanaActualizarEmpleado extends JFrame {
	
	
	private JPanel contentPane;
	private ControlActualizarEmpleado controlActualizarEmpleado;
	private JTextField textFieldBuscar;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidoP;
	private JTextField textFieldApellidoM;
	private JTextField textFieldDireccion;
	private JTextField textFieldTel;
	private JTextField textFieldEmail;
	private JTextField textFieldTarea;
	private JComboBox <String> comboBoxPuesto;
	private Empleado empleado;
	private Puesto puestoInicial;
	
	/*Estructura de la ventana Actualizar Empleado*/
	public VentanaActualizarEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 130, 400, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font nuevaFuente = new Font("Arial", Font.CENTER_BASELINE, 11);
		Font nuevaFuenteTitulos = new Font("Arial", Font.BOLD, 18);
		
		JLabel lblEncabezado = new JLabel("Actualizar Empleado");
		lblEncabezado.setBounds(100, 10, 200, 16);
		lblEncabezado.setFont(nuevaFuenteTitulos);
		contentPane.add(lblEncabezado);
		
		JLabel lblBuscar = new JLabel("Buscar Empleado por e-mail");
		lblBuscar.setFont(nuevaFuente);
		lblBuscar.setBounds(28, 40, 200, 16);
		contentPane.add(lblBuscar);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(28, 60, 200, 26);
		textFieldBuscar.setFont(nuevaFuente);
		textFieldBuscar.setColumns(10);
		contentPane.add(textFieldBuscar);
		
		
		JButton btnBuscar = new JButton("Buscar");	
		btnBuscar.setBounds(240, 60, 100, 26);
		btnBuscar.setFont(nuevaFuente);
		contentPane.add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre (s):");
		lblNombre.setBounds(28, 105, 80, 16);
		lblNombre.setFont(nuevaFuente);
		contentPane.add(lblNombre);
		
		JLabel lblApellidoP = new JLabel("Apellido Paterno:");
		lblApellidoP.setBounds(28, 150, 100, 16);
		lblApellidoP.setFont(nuevaFuente);
		contentPane.add(lblApellidoP);
		
		JLabel lblApellidoM = new JLabel("Apellido Materno:");
		lblApellidoM.setBounds(28, 195, 100, 16);
		lblApellidoM.setFont(nuevaFuente);
		contentPane.add(lblApellidoM);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 240, 61, 16);
		lblDireccion.setFont(nuevaFuente);
		contentPane.add(lblDireccion);
		
		JLabel lblTel = new JLabel("Telefono Movil:");
		lblTel.setBounds(28, 285, 100, 16);
		lblTel.setFont(nuevaFuente);
		contentPane.add(lblTel);
		
		JLabel lblmail = new JLabel("E-mail:");
		lblmail.setBounds(28, 330, 61, 16);
		lblmail.setFont(nuevaFuente);
		contentPane.add(lblmail);
		
		JLabel lblTarea = new JLabel("Tarea Asignada:");
		lblTarea.setBounds(28, 375,120, 16);
		lblTarea.setFont(nuevaFuente);
		contentPane.add(lblTarea);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(28, 420, 61, 16);
		lblPuesto.setFont(nuevaFuente);
		contentPane.add(lblPuesto);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(140, 105, 200, 29);
		textFieldNombre.setFont(nuevaFuente);
		textFieldNombre.setColumns(10);
		contentPane.add(textFieldNombre);
		
		
		textFieldApellidoP = new JTextField();
		textFieldApellidoP.setBounds(140, 150, 200, 29);
		textFieldApellidoP.setFont(nuevaFuente);
		textFieldApellidoP.setColumns(10);
		contentPane.add(textFieldApellidoP);
		
		
		textFieldApellidoM = new JTextField();
		textFieldApellidoM.setBounds(140, 195, 200, 29);
		textFieldApellidoM.setColumns(10);
		textFieldApellidoM.setFont(nuevaFuente);
		contentPane.add(textFieldApellidoM);
	
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(140, 240, 200, 29);
		textFieldDireccion.setFont(nuevaFuente);
		textFieldDireccion.setColumns(10);
		contentPane.add(textFieldDireccion);
		
		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(140, 285, 200, 29);
		textFieldTel.setFont(nuevaFuente);
		textFieldTel.setColumns(10);
		contentPane.add(textFieldTel);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(140, 330, 200, 29);
		textFieldEmail.setFont(nuevaFuente);
		textFieldEmail.setColumns(10);
		contentPane.add(textFieldEmail);
				
		textFieldTarea = new JTextField();
		textFieldTarea.setBounds(140, 375, 200, 29);
		textFieldTarea.setFont(nuevaFuente);
		textFieldTarea.setColumns(10);
		contentPane.add(textFieldTarea);
		
		
		comboBoxPuesto = new JComboBox<>();
		comboBoxPuesto.setBounds(140,420, 200, 29);	
		comboBoxPuesto.setFont(nuevaFuente);
		contentPane.add(comboBoxPuesto);
		
		
		JButton btnActualizar = new JButton("Actualizar");	
		btnActualizar.setBounds(20, 465, 100, 29);
		btnActualizar.setFont(nuevaFuente);
		contentPane.add(btnActualizar);
		
		JButton btnLimpiar = new JButton("Limpiar");	
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText("");
				textFieldApellidoP.setText("");
				textFieldApellidoM.setText("");
				textFieldDireccion.setText("");
				textFieldTel.setText("");
				textFieldEmail.setText("");
				textFieldTarea.setText("");
				
			}
		});
		btnLimpiar.setBounds(140, 465, 100, 29);
		btnLimpiar.setFont(nuevaFuente);
		contentPane.add(btnLimpiar);
		
		JButton btnCancelar = new JButton("Regresar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlActualizarEmpleado.termina();
			}
		});
		btnCancelar.setBounds(260, 465, 100, 29);
		btnCancelar.setFont(nuevaFuente);
		contentPane.add(btnCancelar);
		
		/* Listeners*/
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscar.getText().equals("") 
					) {
					muestraDialogoConMensaje("Los Campos no deben de estar Vacios");
				} else {
					/*convoca a recuperar el empleado y su puesto*/ 
					empleado=controlActualizarEmpleado.recuperarEmpleado(textFieldBuscar.getText());
					puestoInicial=controlActualizarEmpleado.recuperaPuestoEmpleado(empleado);
				}
			}
		});
		
		comboBoxPuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("esta funcionando el combo box ");
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNombre.getText().equals("") 
					|| textFieldApellidoP.getText().equals("")
					|| textFieldApellidoM.getText().equals("")
					|| textFieldDireccion.getText().equals("")
					|| textFieldTel.getText().equals("")
					|| textFieldEmail.getText().equals("")
					|| textFieldTarea.getText().equals("")) {
					muestraDialogoConMensaje("Los Campos no deben de estar Vacios");
				} else {
					controlActualizarEmpleado.actualizarEmpleado(empleado,puestoInicial,
							textFieldNombre.getText(),
							textFieldApellidoP.getText(), 
							textFieldApellidoM.getText(),
							textFieldDireccion.getText(),
							textFieldTel.getText(),
							textFieldEmail.getText(),
							textFieldTarea.getText(),
							(String) comboBoxPuesto.getSelectedItem());
				}
			}
		});
	  }
	/*Metodo para mostrar la primera instancia de la ventana*/
	public void muestra(ControlActualizarEmpleado controlActualizarEmpleado) {
		this.controlActualizarEmpleado=controlActualizarEmpleado;
		textFieldBuscar.setText("");
		textFieldNombre.setText("");
		textFieldApellidoP.setText("");
		textFieldApellidoM.setText("");
		textFieldDireccion.setText("");
		textFieldTel.setText("");
		textFieldEmail.setText("");
		textFieldTarea.setText("");
		DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
		comboBoxModel.addElement(" ");
		comboBoxPuesto.setModel(comboBoxModel);	
		
		setVisible(true);
	}
	/*Metodo para mostrar el empleado recuperado*/
	public void muestraEmpleadoRecuperado(Empleado empleado,List <Puesto> puestos) {
		textFieldNombre.setText(empleado.getNombre());
		textFieldApellidoP.setText(empleado.getApellidoP());
		textFieldApellidoM.setText(empleado.getApellidoM());
		textFieldDireccion.setText(empleado.getDireccion());
		textFieldTel.setText(empleado.getTel());
		textFieldEmail.setText(empleado.getEmail());
		textFieldTarea.setText(empleado.getTarea());
		puestoInicial=controlActualizarEmpleado.recuperaPuestoEmpleado(empleado);
		DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
		comboBoxModel.addElement(puestoInicial.getNombre());	
		comboBoxPuesto.setModel(comboBoxModel);	
		for(Puesto puesto:puestos) {
			if(!puesto.getNombre().equals(puestoInicial.getNombre())) {
				comboBoxModel.removeElement(puesto.getNombre());
				comboBoxModel.addElement(puesto.getNombre());
			}
		}
		comboBoxPuesto.setModel(comboBoxModel);	
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
}
	


