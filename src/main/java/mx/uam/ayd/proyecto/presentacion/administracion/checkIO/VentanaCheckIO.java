package mx.uam.ayd.proyecto.presentacion.administracion.checkIO;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Puesto;
import mx.uam.ayd.proyecto.negocio.modelo.Registro;
import mx.uam.ayd.proyecto.presentacion.administracion.actualizarEmpleado.ControlActualizarEmpleado;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
// modificacion
@Component
public class VentanaCheckIO extends JFrame {
	private JPanel contentPane;
	private ControlCheckIO controlCheckIO;
	private JTextField textFieldBuscar;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidoP;
	private JTextField textFieldApellidoM;
	private JTextField textFieldPuesto;
	private JTextField textFieldTel;
	private JTextField textFieldEmail;
	private JComboBox <String> comboBoxRegistro;

	private Empleado empleado;
	private Puesto puestoInicial;
	
	public VentanaCheckIO() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEncabezado = new JLabel("Check Entrada/Salida Laboral");
		lblEncabezado.setBounds(150, 10, 200, 16);
		contentPane.add(lblEncabezado);
		
		JLabel lblBuscar = new JLabel("Ingresa tu e-mail");
		lblBuscar.setBounds(28, 40, 200, 16);
		contentPane.add(lblBuscar);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(28, 60, 200, 26);
		contentPane.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Ingresar");	
		btnBuscar.setBounds(240, 60, 100, 26);
		contentPane.add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre (s):");
		lblNombre.setBounds(28, 100, 80, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellidoP = new JLabel("Apellido Paterno:");
		lblApellidoP.setBounds(130, 100, 100, 16);
		contentPane.add(lblApellidoP);
		
		JLabel lblApellidoM = new JLabel("Apellido Materno:");
		lblApellidoM.setBounds(232, 100, 100, 16);
		contentPane.add(lblApellidoM);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(350, 100, 100, 16);
		contentPane.add(lblPuesto);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(28, 125, 90, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidoP = new JTextField();
		textFieldApellidoP.setBounds(130, 125, 90, 26);
		contentPane.add(textFieldApellidoP);
		textFieldApellidoP.setColumns(10);
		
		textFieldApellidoM = new JTextField();
		textFieldApellidoM.setBounds(232, 125, 90, 26);
		contentPane.add(textFieldApellidoM);
		textFieldApellidoM.setColumns(10);
		
		textFieldPuesto = new JTextField();
		textFieldPuesto.setBounds(334, 125, 90, 26);
		contentPane.add(textFieldPuesto);
		textFieldPuesto.setColumns(10);
		
		comboBoxRegistro = new JComboBox<>();
		comboBoxRegistro.setBounds(20,170, 80, 26);		
		contentPane.add(comboBoxRegistro);
		
		
		
		JButton btnChecar = new JButton("Checar");	
		btnChecar.setBounds(115, 170, 100, 29);
		contentPane.add(btnChecar);
		

		JButton btnHistorial = new JButton("Historial");	
		btnHistorial.setBounds(360, 170, 100, 29);
		contentPane.add(btnHistorial);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlActualizarEmpleado.termina();
			}
		});
		btnRegresar.setBounds(240, 170, 100, 29);
		contentPane.add(btnRegresar);
/*		
		/*JButton btnLimpiar = new JButton("Limpiar");	
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
		contentPane.add(btnLimpiar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlActualizarEmpleado.termina();
			}
		});
		btnCancelar.setBounds(260, 465, 100, 29);
		contentPane.add(btnCancelar);
		*/
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscar.getText().equals("") 
					) {
					muestraDialogoConMensaje("Los Campos no deben de estar Vacios");
				} else {
					//convoca a recuperar el empleado y su puesto 
					empleado=controlCheckIO.recuperarEmpleado(textFieldBuscar.getText());
					puestoInicial=controlCheckIO.recuperaPuestoEmpleado(empleado);

				}
			}
		});
		
		btnChecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					controlCheckIO.addRegistro(empleado,
							  (String) comboBoxRegistro.getSelectedItem());
				
			}
		});
	}
	
	public void muestra(ControlCheckIO controlCheckIO,List <Registro> registros) {
		this.controlCheckIO=controlCheckIO;
		textFieldBuscar.setText("");
		textFieldNombre.setText("");
		textFieldApellidoP.setText("");
		textFieldApellidoM.setText("");
		textFieldPuesto.setText("");
		DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
		for(Registro registro:registros) {
			comboBoxModel.addElement(registro.getNombre());
		}
		comboBoxRegistro.setModel(comboBoxModel);
		setVisible(true);
	}
	
	public void muestraEmpleadoRecuperado(Empleado empleado) {
		puestoInicial=controlCheckIO.recuperaPuestoEmpleado(empleado);
		textFieldNombre.setText(empleado.getNombre());
		textFieldApellidoP.setText(empleado.getApellidoP());
		textFieldApellidoM.setText(empleado.getApellidoM());
		textFieldPuesto.setText(puestoInicial.getNombre());
	}
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}

}
