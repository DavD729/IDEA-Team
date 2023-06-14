package mx.uam.ayd.proyecto.presentacion.administracion.checkIO;

import java.util.Date;
import java.text.SimpleDateFormat;
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
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

	//Variables de Tiempo
	Calendar fecha;
	String anio;
	String mes;
	String dia;
	String hora;
	String min;
	String segundos;
	 
	public VentanaCheckIO() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 480, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font nuevaFuente = new Font("Arial", Font.CENTER_BASELINE, 11);
		Font nuevaFuenteTitulos = new Font("Arial", Font.BOLD, 18);
		
		
		JLabel lblEncabezado = new JLabel("Check Entrada/Salida Laboral");
		lblEncabezado.setBounds(100, 10, 300, 16);
		lblEncabezado.setFont(nuevaFuenteTitulos);
		contentPane.add(lblEncabezado);
		
		JLabel lblBuscar = new JLabel("Ingresa tu e-mail");
		lblBuscar.setBounds(22, 30, 200, 16);
		lblBuscar.setFont(nuevaFuente);
		contentPane.add(lblBuscar);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(22, 50, 200, 29);
		textFieldBuscar.setFont(nuevaFuente);
		textFieldBuscar.setColumns(10);
		contentPane.add(textFieldBuscar);
		
		
		JButton btnBuscar = new JButton("Ingresar");	
		btnBuscar.setBounds(233, 50, 100, 29);
	    btnBuscar.setFont(nuevaFuente);
	    contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");	
		btnLimpiar.setBounds(346, 50, 100, 29);
		btnLimpiar.setFont(nuevaFuente);
		contentPane.add(btnLimpiar);
		
		JLabel lblNombre = new JLabel("Nombre (s)");
		lblNombre.setBounds(22, 90, 80, 16);
		lblNombre.setFont(nuevaFuente);
		contentPane.add(lblNombre);
		
		JLabel lblApellidoP = new JLabel("Apellido Paterno");
		lblApellidoP.setBounds(130, 90, 100, 16);
		lblApellidoP.setFont(nuevaFuente);
		contentPane.add(lblApellidoP);
		
		JLabel lblApellidoM = new JLabel("Apellido Materno");
		lblApellidoM.setBounds(238, 90, 100, 16);
		lblApellidoM.setFont(nuevaFuente);
		contentPane.add(lblApellidoM);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(346, 90, 100, 16);
		lblPuesto.setFont(nuevaFuente);
		contentPane.add(lblPuesto);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(22, 110, 90, 29);
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setFont(nuevaFuente);
		contentPane.add(textFieldNombre);
			
		textFieldApellidoP = new JTextField();
		textFieldApellidoP.setBounds(130, 110, 90, 29);
		textFieldApellidoP.setEditable(false);
		textFieldApellidoP.setFont(nuevaFuente);
		textFieldApellidoP.setColumns(10);
		contentPane.add(textFieldApellidoP);
				
		textFieldApellidoM = new JTextField();
		textFieldApellidoM.setBounds(238, 110, 90, 29);
		textFieldApellidoM.setEditable(false);
		textFieldApellidoM.setFont(nuevaFuente);
		textFieldApellidoM.setColumns(10);
		contentPane.add(textFieldApellidoM);
			
		textFieldPuesto = new JTextField();
		textFieldPuesto.setBounds(346, 110, 90, 29);
		textFieldPuesto.setEditable(false);
		textFieldPuesto.setFont(nuevaFuente);
		textFieldPuesto.setColumns(10);
		contentPane.add(textFieldPuesto);
		
		
		comboBoxRegistro = new JComboBox<>();
		comboBoxRegistro.setBounds(60,155, 90, 29);		
		comboBoxRegistro.setFont(nuevaFuente);
		contentPane.add(comboBoxRegistro);
		
		
		
		JButton btnChecar = new JButton("Checar");	
		btnChecar.setBounds(170, 155, 100, 29);
		btnChecar.setFont(nuevaFuente);
		contentPane.add(btnChecar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(290, 155, 100, 29);
		btnRegresar.setFont(nuevaFuente);
		contentPane.add(btnRegresar);
		
		/*Esuchadores de Eventos de los Botones*/
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlCheckIO.termina();
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText("");
				textFieldApellidoP.setText("");
				textFieldApellidoM.setText("");
				textFieldBuscar.setText("");	
				textFieldPuesto.setText("");
				DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
				comboBoxModel.addElement("");			
				comboBoxRegistro.setModel(comboBoxModel);
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldBuscar.getText().equals("") 
					) {
					muestraDialogoConMensaje("Los Campos no deben de estar Vacios");
				} else {			  
					/*convoca a recuperar el empleado y su puesto*/ 
					empleado=controlCheckIO.recuperarEmpleado(textFieldBuscar.getText());
					puestoInicial=controlCheckIO.recuperaPuestoEmpleado(empleado);					
				}
			}
		});
		
		btnChecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					/*Llama al metodo tiempo para inicializar  variables de tiempo*/
					Tiempo();
					/*Anida un Cero para darle el formato correspondiente a las horas minutos y segundos*/
					if(Integer.parseInt(hora)<10)
						hora="0"+hora;
					if(Integer.parseInt(min)<10)
						min="0"+hora;
					if(Integer.parseInt(segundos)<10)
						segundos="0"+segundos;

					controlCheckIO.addRegistroTiempo(empleado,
							  (String) comboBoxRegistro.getSelectedItem(),anio,mes,dia,hora,min,segundos);				  
			}
		});
	}
	
	/*Procedimiento para iniciarlizar variables 
	 * caputaras del Tiempo en que se hace el 
	 * chequeo del Empleado*/
	public void Tiempo() {
		fecha=new GregorianCalendar();
		 anio=Integer.toString(fecha.get(Calendar.YEAR));
		 mes=Integer.toString(fecha.get(Calendar.MONTH)+1);
		 dia=Integer.toString(fecha.get(Calendar.DATE));
		
		 hora=Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
		 min=Integer.toString(fecha.get(Calendar.MINUTE));
		 segundos=Integer.toString(fecha.get(Calendar.SECOND));
	}
	/*metodo para Muestrar la ventana CheckIO*/
	public void muestra(ControlCheckIO controlCheckIO) {
		this.controlCheckIO=controlCheckIO;
		textFieldBuscar.setText("");
		textFieldNombre.setText("");
		textFieldApellidoP.setText("");
		textFieldApellidoM.setText("");
		textFieldPuesto.setText("");
		setVisible(true);
	}
	/*Metodo para mostrar los datos del empleado en la ventana checkIO*/
	public void muestraEmpleadoRecuperado(Empleado empleado,List <Registro> registros) {
		puestoInicial=controlCheckIO.recuperaPuestoEmpleado(empleado);
		textFieldNombre.setText(empleado.getNombre());
		textFieldApellidoP.setText(empleado.getApellidoP());
		textFieldApellidoM.setText(empleado.getApellidoM());
		textFieldPuesto.setText(puestoInicial.getNombre());
		DefaultComboBoxModel <String> comboBoxModel =new DefaultComboBoxModel <>();
		for(Registro registro:registros) {
			comboBoxModel.addElement(registro.getNombre());
		}
		comboBoxRegistro.setModel(comboBoxModel);
	}
	
	/*Metodo para mostrar mensajes en la ventana checkIO*/
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}

}
