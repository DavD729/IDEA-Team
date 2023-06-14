package mx.uam.ayd.proyecto.presentacion.administracion.agregarEmpleados;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Puesto;

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
/*Estructura de la ventana Agregar Empleado*/
public class VentanaAddEmpleado  extends JFrame {
	private JPanel contentPane;
	private ControlAddEmpleado controlAddEmpleado;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidoP;
	private JTextField textFieldApellidoM;
	private JTextField textFieldDireccion;
	private JTextField textFieldTel;
	private JTextField textFieldEmail;
	private JTextField textFieldTarea;
	private JComboBox <String> comboBoxPuesto;
	
	
	public VentanaAddEmpleado() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 400, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font nuevaFuente = new Font("Arial", Font.CENTER_BASELINE, 11);
		Font nuevaFuenteTitulos = new Font("Arial", Font.BOLD, 18);
		
		JLabel lblEncabezado = new JLabel("Agregar Empleado");
		lblEncabezado.setBounds(120, 10, 200, 20);
		lblEncabezado.setFont(nuevaFuenteTitulos);
		contentPane.add(lblEncabezado);
		
		JLabel lblNombre = new JLabel("Nombre (s):");
		lblNombre.setBounds(28, 39, 80, 16);
		lblNombre.setFont(nuevaFuente);
		contentPane.add(lblNombre);
		
		JLabel lblApellidoP = new JLabel("Apellido Paterno:");
		lblApellidoP.setBounds(28, 84, 100, 16);
		lblApellidoP.setFont(nuevaFuente);
		contentPane.add(lblApellidoP);
		
		JLabel lblApellidoM = new JLabel("Apellido Materno:");
		lblApellidoM.setBounds(28, 129, 100, 16);
		lblApellidoM.setFont(nuevaFuente);
		contentPane.add(lblApellidoM);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(28, 174, 61, 16);
		lblDireccion.setFont(nuevaFuente);
		contentPane.add(lblDireccion);
		
		JLabel lblTel = new JLabel("Telefono Movil:");
		lblTel.setBounds(28, 219, 100, 16);
		lblTel.setFont(nuevaFuente);
		contentPane.add(lblTel);
		
		JLabel lblmail = new JLabel("E-mail:");
		lblmail.setBounds(28, 264, 61, 16);
		lblmail.setFont(nuevaFuente);
		contentPane.add(lblmail);
		
		JLabel lblTarea = new JLabel("Tarea Asignada:");
		lblTarea.setBounds(28, 309,120, 16);
		lblTarea.setFont(nuevaFuente);
		contentPane.add(lblTarea);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(28, 354, 61, 16);
		lblPuesto.setFont(nuevaFuente);
		contentPane.add(lblPuesto);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(140, 39, 200, 29);
		textFieldNombre.setFont(nuevaFuente);
		textFieldNombre.setColumns(10);
		contentPane.add(textFieldNombre);
		
		
		textFieldApellidoP = new JTextField();
		textFieldApellidoP.setBounds(140, 84, 200, 29);
		textFieldApellidoP.setFont(nuevaFuente);
		textFieldApellidoP.setColumns(10);
		contentPane.add(textFieldApellidoP);
		
		
		textFieldApellidoM = new JTextField();
		textFieldApellidoM.setBounds(140, 129, 200, 29);
		textFieldApellidoM.setFont(nuevaFuente);
		textFieldApellidoM.setColumns(10);
		contentPane.add(textFieldApellidoM);
		
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(140, 174, 200, 29);
		textFieldDireccion.setFont(nuevaFuente);
		textFieldDireccion.setColumns(10);
		contentPane.add(textFieldDireccion);
		
		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(140, 219, 200, 29);
		textFieldTel.setFont(nuevaFuente);
		textFieldTel.setColumns(10);
		contentPane.add(textFieldTel);
		
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(140, 264, 200, 29);
		textFieldEmail.setFont(nuevaFuente);
		textFieldEmail.setColumns(10);
		contentPane.add(textFieldEmail);
		
		
		textFieldTarea = new JTextField();
		textFieldTarea.setBounds(140, 309, 200, 29);
		textFieldTarea.setFont(nuevaFuente);
		textFieldTarea.setColumns(10);
		contentPane.add(textFieldTarea);
		
		
		comboBoxPuesto = new JComboBox<>();
		comboBoxPuesto.setBounds(140,354, 200, 29);	
		comboBoxPuesto.setFont(nuevaFuente);
		contentPane.add(comboBoxPuesto);
		
		JButton btnAgregar = new JButton("Agregar");	
		btnAgregar.setBounds(20, 399, 100, 29);
		btnAgregar.setFont(nuevaFuente);
		contentPane.add(btnAgregar);
		
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
		btnLimpiar.setBounds(140, 399, 100, 29);
		btnLimpiar.setFont(nuevaFuente);
		contentPane.add(btnLimpiar);
		
		JButton btnCancelar = new JButton("Regresar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAddEmpleado.termina();
			}
		});
		btnCancelar.setBounds(260, 399, 100, 29);
		btnCancelar.setFont(nuevaFuente);
		contentPane.add(btnCancelar);
		
		// Listeners
		btnAgregar.addActionListener(new ActionListener() {
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
					controlAddEmpleado.addEmpleado(textFieldNombre.getText(), 
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
	
	public void muestra(ControlAddEmpleado controlAddEmpleado,List <Puesto> puestos) {
		this.controlAddEmpleado= controlAddEmpleado;
		textFieldNombre.setText("");
		textFieldApellidoP.setText("");
		textFieldApellidoM.setText("");
		textFieldDireccion.setText("");
		textFieldTel.setText("");
		textFieldEmail.setText("");
		textFieldTarea.setText("");
		
		
		DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
		for(Puesto puesto:puestos) {
			comboBoxModel.addElement(puesto.getNombre());
		}
		comboBoxPuesto.setModel(comboBoxModel);
		setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	

}
