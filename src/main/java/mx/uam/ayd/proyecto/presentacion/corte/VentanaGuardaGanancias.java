package mx.uam.ayd.proyecto.presentacion.corte;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.stereotype.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import mx.uam.ayd.proyecto.negocio.modelo.Corte;
import com.toedter.calendar.JDateChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
@Component

public class VentanaGuardaGanancias extends JFrame{	
	//Declaración de los elementos de la ventana
	private ControlGuardaGanancias control;
	private Date fecha;
	private JDateChooser dateChooser;
	private JTextField textField;
	private JButton btnCerrarCaja;
	private JButton btnGuardar;
	private JButton btnRegresar;
	private JTabbedPane tabbedPane;
	private JTable tablaIngresos;
	private JTable tablaEgresos;
	private JTable tablaBalance;
	private Calendar calendario;
	private JLabel titulo;
	private Font fuenteTitulo;
	private Font fuenteVentana;
	private int tamaboton1;
	private int tamaboton2;
	private int tamaboton3;
	
	/**
	* Constructor de la Ventana
	**/
	public VentanaGuardaGanancias() {
		//Creamos los elementos de la ventana
		dateChooser = new JDateChooser();
		textField = ((JTextField) dateChooser.getDateEditor().getUiComponent());
		btnCerrarCaja = new JButton("Cerrar Caja");
		btnGuardar = new JButton("Guardar");
		btnRegresar = new JButton("Regresar");
		titulo = new JLabel("Corte de Caja");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tablaIngresos = new JTable();
		tablaEgresos = new JTable();
		tablaBalance = new JTable();
		calendario = Calendar.getInstance();
		fuenteTitulo = new Font("Arial",Font.BOLD,18);
		fuenteVentana = new Font("Arial",Font.PLAIN,11);

		//Deshabilitamos la edicion del cuadro de texto del dateChooser 
		textField.setEditable(false); 
		
		//Obtenemos la fecha actual
		fecha = calendario.getTime();
		dateChooser.setDate(fecha);
		
		//Configuramos la Tabla de Ingresos
		tablaIngresos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tablaIngresos.setValueAt("Tipo de Ingreso", 0, 0);
		tablaIngresos.setValueAt("Efectivo", 1, 0);
		tablaIngresos.setValueAt("Tarjeta de Credito/Debito", 2, 0);
		tablaIngresos.setValueAt("Credito en Tienda", 3, 0);
		tablaIngresos.setValueAt("Vales de Despensa", 4, 0);
		tablaIngresos.setValueAt("Total", 5, 0);
		tablaIngresos.setValueAt("Cantidad", 0, 1);
		tablaIngresos.setEnabled(false);
		tablaIngresos.setFont(fuenteVentana);
		tabbedPane.addTab("Ingresos", null, tablaIngresos, null);
	
		//Configuramos la tabla de Egresos
		tablaEgresos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		tablaEgresos.setValueAt("Tipo de Ingreso", 0, 0);
		tablaEgresos.setValueAt("Efectivo", 1, 0);
		tablaEgresos.setValueAt("Tarjeta de Credito/Debito", 2, 0);
		tablaEgresos.setValueAt("Credito en Tienda", 3, 0);
		tablaEgresos.setValueAt("Vales de Despensa", 4, 0);
		tablaEgresos.setValueAt("Total", 5, 0);
		tablaEgresos.setValueAt("Cantidad", 0, 1);
		tablaEgresos.editCellAt(1, 1);
		tablaEgresos.editCellAt(1, 2);
		tablaEgresos.editCellAt(1, 3);
		tablaEgresos.editCellAt(1, 4);
		tablaEgresos.setFont(fuenteVentana);
		tabbedPane.addTab("Egresos", null, tablaEgresos, null);
		
		//Configuramos la tabla de Balance
		tablaBalance.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column"
				}
			));
			tablaBalance.setValueAt("Tipo Operacion", 0, 0);
			tablaBalance.setValueAt("Efectivo", 1, 0);
			tablaBalance.setValueAt("Tarjeta de Credito/Debito", 2, 0);
			tablaBalance.setValueAt("Credito en Tienda", 3, 0);
			tablaBalance.setValueAt("Vales de despensa", 4, 0);
			tablaBalance.setValueAt("Balance del Dia", 5, 0);
			tablaBalance.setValueAt("Ventas del dia", 0, 1);
			tablaBalance.setValueAt("Compras del dia", 0, 2);
			tablaBalance.setValueAt("Total", 0, 3);
			tablaBalance.setEnabled(false);
			tablaBalance.setFont(fuenteVentana);
			tabbedPane.addTab("Balance", null, tablaBalance, null);
			tabbedPane.setFont(fuenteVentana);
		
	//Agregamos los Listeners de los elementos
			
		//Listener de la tabla de Egresos	
		tablaEgresos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablaEgresos.rowAtPoint(e.getPoint());
                int column = tablaEgresos.columnAtPoint(e.getPoint());
                double efe;
                double tar;
            	double cre;
            	double val;
            	double tot;
            	String efectivo;
            	String tarjeta;
            	String credito;
            	String vales;
            	
            	//Se habilita o deshabilita la tabla, dependiendo de la celda que sea clickeada
            	if (((row==0 || row==1 || row==2 || row==3 || row==4 || row==5) && column==0) || ((row==0 || row==5) && column==1))
            		tablaEgresos.setEnabled(false);
            	else {
            		tablaEgresos.setEnabled(true);
                    efe=0;
                    tar=0;
                    cre=0;
                    val=0;
                    //Guardamos el contenido de las celdas
                    efectivo = (String) tablaEgresos.getValueAt(1,1);
    				tarjeta = (String)tablaEgresos.getValueAt(2,1);
    				credito = (String)tablaEgresos.getValueAt(3,1);
    				vales = (String)tablaEgresos.getValueAt(4,1);
    				//Si la celda no esta vacia, se valida que el contenido sea un numero flotante
    				if (!efectivo.isEmpty()) {
    					try {
    				        efe = Double.parseDouble(efectivo);
    					} catch (NumberFormatException e1) {
    					    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    					    tablaEgresos.setValueAt("", 1, 1);
    					}
    				} 
    				//Si la celda esta vacia se toma como un 0 para poder actualizar en todo momento el total
    				else
    					efe = 0;
    				if(!tarjeta.isEmpty()) {
    					try {
    						tar = Double.parseDouble(tarjeta);
     					} catch (NumberFormatException e1) {
     					    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
     					    tablaEgresos.setValueAt("", 2, 1);
     					}
    				}
    				else
    					tar = 0;
    				if(!credito.isEmpty()) {
    					try {
    						cre = Double.parseDouble(credito);
      					} catch (NumberFormatException e1) {
      					    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
      					    tablaEgresos.setValueAt("", 3, 1);
      					}
    				}
    				else
    					cre = 0;
    				if(!vales.isEmpty()) {
    					try {
    						val = Double.parseDouble(vales);
      					} catch (NumberFormatException e1) {
      					    JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
      					    tablaEgresos.setValueAt("", 4, 1);
      					}
    				}
    				else
    					val = 0;
    				//Se muestra el total en la celda correspondiente
    				tot = efe + tar + cre + val;
                    tablaEgresos.setValueAt(tot, 5, 1);
                    setVisible(true);
                }
            }
        });
		
		//Listener del Panel que contiene las 3 tablas
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int pestaña = tabbedPane.getSelectedIndex();
				LocalDate fechaAc;
				Date fechaCal;
				LocalDate dateCal;
				LocalDate dateAc;
				//Condicion para saber que pestaña esta seleccionada (Ingresos = 0, Egresos = 1, Balance = 2)
				switch (pestaña){
					case 0:
						fechaAc = LocalDate.now();
						fechaCal = dateChooser.getDate();
						dateCal = fechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						dateAc = LocalDate.now();
						
						if(btnGuardar.isEnabled())
							btnGuardar.setEnabled(false);
						if(control.buscaCorte(fechaAc, 1)==null && dateCal.equals(dateAc))
							btnCerrarCaja.setEnabled(true);
						control.inicia();
						break;
					case 1:
						fechaCal = dateChooser.getDate();
						dateCal = fechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						dateAc = LocalDate.now();
						
						if(!(btnGuardar.isEnabled())&&dateCal.equals(dateAc))
							btnGuardar.setEnabled(true);
						if(btnCerrarCaja.isEnabled())
							btnCerrarCaja.setEnabled(false);
						if(dateCal != dateAc) {
							Corte corte = new Corte();
							corte = control.buscaCorte(dateCal,2);
							if(corte==null) {
								tablaEgresos.setValueAt("", 1, 1);
								tablaEgresos.setValueAt("", 2, 1);
								tablaEgresos.setValueAt("", 3, 1);
								tablaEgresos.setValueAt("", 4, 1);
								tablaEgresos.setValueAt("", 5, 1);
								setVisible(true);
							}
							else {
								muestra(control, corte,2);
								btnGuardar.setEnabled(false);
							}
						}
						break;
					case 2:
						fechaCal = dateChooser.getDate();
						dateCal = fechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						dateAc = LocalDate.now();
						Corte ingresos = new Corte();
						ingresos = control.buscaCorte(dateCal,1);
						Corte egresos = new Corte();
						egresos = control.buscaCorte(dateCal, 2);
						
						if(btnGuardar.isEnabled())
							btnGuardar.setEnabled(false);
						if(btnCerrarCaja.isEnabled())
							btnCerrarCaja.setEnabled(false);
						if(ingresos==null || egresos==null) {
							if(ingresos==null && egresos == null) {
									muestraDialogoConMensaje("No se encontraron los ingresos y egresos del dia solicitado");
									tablaBalance.setValueAt("", 1, 1);
									tablaBalance.setValueAt("", 2, 1);
									tablaBalance.setValueAt("", 3, 1);
									tablaBalance.setValueAt("", 4, 1);
									tablaBalance.setValueAt("", 5, 1);
									tablaBalance.setValueAt("", 1, 2);
									tablaBalance.setValueAt("", 2, 2);
									tablaBalance.setValueAt("", 3, 2);
									tablaBalance.setValueAt("", 4, 2);
									tablaBalance.setValueAt("", 5, 2);
									tablaBalance.setValueAt("", 1, 3);
									tablaBalance.setValueAt("", 2, 3);
									tablaBalance.setValueAt("", 3, 3);
									tablaBalance.setValueAt("", 4, 3);
									tablaBalance.setValueAt("", 5, 3);
									break;
							}
							else if(ingresos==null) {
								muestraDialogoConMensaje("No se encontraron los ingresos del dia solicitado");
								tablaBalance.setValueAt(egresos.getEfectivo(), 1, 2);
								tablaBalance.setValueAt(egresos.getTarjeta(), 2, 2);
								tablaBalance.setValueAt(egresos.getCredito(), 3, 2);
								tablaBalance.setValueAt(egresos.getVales(), 4, 2);
								tablaBalance.setValueAt(egresos.getTotal(), 5, 2);
								break;
							}
							else
								muestraDialogoConMensaje("No se encontraron los egresos del dia solicitado");
								tablaBalance.setValueAt(ingresos.getEfectivo(), 1, 1);
								tablaBalance.setValueAt(ingresos.getTarjeta(), 2, 1);
								tablaBalance.setValueAt(ingresos.getCredito(), 3, 1);
								tablaBalance.setValueAt(ingresos.getVales(), 4, 1);
								tablaBalance.setValueAt(ingresos.getTotal(), 5, 1);
								break;
						}
						else {
							tablaBalance.setValueAt(ingresos.getEfectivo(), 1, 1);
							tablaBalance.setValueAt(ingresos.getTarjeta(), 2, 1);
							tablaBalance.setValueAt(ingresos.getCredito(), 3, 1);
							tablaBalance.setValueAt(ingresos.getVales(), 4, 1);
							tablaBalance.setValueAt(ingresos.getTotal(), 5, 1);
							tablaBalance.setValueAt(egresos.getEfectivo(), 1, 2);
							tablaBalance.setValueAt(egresos.getTarjeta(), 2, 2);
							tablaBalance.setValueAt(egresos.getCredito(), 3, 2);
							tablaBalance.setValueAt(egresos.getVales(), 4, 2);
							tablaBalance.setValueAt(egresos.getTotal(), 5, 2);
							tablaBalance.setValueAt(ingresos.getEfectivo()-egresos.getEfectivo(), 1, 3);
							tablaBalance.setValueAt(ingresos.getTarjeta()-egresos.getTarjeta(), 2, 3);
							tablaBalance.setValueAt(ingresos.getCredito()-egresos.getCredito(), 3, 3);
							tablaBalance.setValueAt(ingresos.getVales()-egresos.getVales(), 4, 3);
							tablaBalance.setValueAt(ingresos.getTotal()-egresos.getTotal(), 5, 3);
							setVisible(true);
						}
						break;
					default: 
						break;
				}
			}
		});
		
		//Listener del boton Guardar
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fecha;
				fecha = dateChooser.getDate();
				LocalDate date = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(tabbedPane.getSelectedComponent()==tablaIngresos) {
					control.guardarCorte(date, (double)tablaIngresos.getValueAt(1, 1), (double)tablaIngresos.getValueAt(2, 1), 
							(double)tablaIngresos.getValueAt(3, 1), (double)tablaIngresos.getValueAt(4, 1), (double)tablaIngresos.getValueAt(5, 1),
							1);
					btnGuardar.setEnabled(false);
				}
				else if(tabbedPane.getSelectedComponent()==tablaEgresos){
					String Efectivo = (String) tablaEgresos.getValueAt(1,1);
					String TDC = (String)tablaEgresos.getValueAt(2,1);
					String Credito = (String)tablaEgresos.getValueAt(3,1);
					String Vales = (String)tablaEgresos.getValueAt(4,1);
					if((Efectivo.isEmpty()||TDC.isEmpty()||Credito.isEmpty()||Vales.isEmpty())) 	
						JOptionPane.showMessageDialog(null, "No puede haber campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						double efe = Double.parseDouble(Efectivo);
						double tar = Double.parseDouble(TDC);
						double cre = Double.parseDouble(Credito);
						double val = Double.parseDouble(Vales);
						double tot = efe + tar + cre + val;
						control.guardarCorte(date, efe, tar, cre, val, tot, 2);
						tablaEgresos.setEnabled(false);
						btnGuardar.setEnabled(false);
					}
				}
			}
		});
		
		//Listener del boton Cerrar Caja
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnGuardar.isEnabled())
					btnGuardar.setEnabled(true);
				btnCerrarCaja.setEnabled(false);
			}
		});
		
		//Listener del boton Regresar
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//Listener del seleccionador de fecha
		dateChooser.getCalendarButton().addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent e) {
				int sit=0;
				Date fechaCal;
				fechaCal = dateChooser.getDate();
				LocalDate DateCal = fechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate DateAc = LocalDate.now();
				if(!DateCal.equals(DateAc) && tabbedPane.getSelectedComponent()==tablaIngresos)
					sit=1;	
				else {
					if(DateCal.equals(DateAc) && tabbedPane.getSelectedComponent()==tablaIngresos)
						sit=2;
					else {
						if(!DateCal.equals(DateAc) && tabbedPane.getSelectedComponent()==tablaEgresos)
							sit=3;
						else {
							if(DateCal.equals(DateAc) && tabbedPane.getSelectedComponent()==tablaEgresos)
								sit=4;
							else {
								sit=5;
							}
						}
					}
				}
				switch(sit) {
					case 1:
						Corte corte = new Corte();
						corte = control.buscaCorte(DateCal,1);
						if(corte==null) {
							if(btnCerrarCaja.isEnabled())
								btnCerrarCaja.setEnabled(false);
							tablaIngresos.setValueAt("", 1, 1);
							tablaIngresos.setValueAt("", 2, 1);
							tablaIngresos.setValueAt("", 3, 1);
							tablaIngresos.setValueAt("", 4, 1);
							tablaIngresos.setValueAt("", 5, 1);
							setVisible(true);
						}
						else
							muestra(control, corte,1);
						break;
					case 2:
						corte = new Corte();
						corte = control.buscaCorte(DateCal,1);
						if(corte==null) {
							if(!btnCerrarCaja.isEnabled())
								btnCerrarCaja.setEnabled(true);
							control.inicia();
						}
						else
							muestra(control,corte,1);
						break;
					case 3:
						corte = new Corte();
						corte = control.buscaCorte(DateCal,2);
						if(corte==null) {
							tablaEgresos.setValueAt("", 1, 1);
							tablaEgresos.setValueAt("", 2, 1);
							tablaEgresos.setValueAt("", 3, 1);
							tablaEgresos.setValueAt("", 4, 1);
							tablaEgresos.setValueAt("", 5, 1);
							setVisible(true);
							tablaEgresos.setEnabled(false);
							if(btnGuardar.isEnabled())
								btnGuardar.setEnabled(false);
						}
						else
							muestra(control,corte,2);
						break;
					case 4:
						corte = new Corte();
						corte = control.buscaCorte(DateCal,2);
						if(corte==null) {
							tablaEgresos.setValueAt("", 1, 1);
							tablaEgresos.setValueAt("", 2, 1);
							tablaEgresos.setValueAt("", 3, 1);
							tablaEgresos.setValueAt("", 4, 1);
							tablaEgresos.setValueAt("", 5, 1);
							setVisible(true);
							if(!btnGuardar.isEnabled())
								btnGuardar.setEnabled(true);
						}
						else
							muestra(control,corte,2);
						break;
					case 5:
						fechaCal = dateChooser.getDate();
						DateCal = fechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						DateAc = LocalDate.now();
						Corte ingresos = new Corte();
						ingresos = control.buscaCorte(DateCal,1);
						Corte egresos = new Corte();
						egresos = control.buscaCorte(DateCal, 2);
						if(ingresos==null || egresos==null) {
							if(ingresos==null && egresos == null) {
									muestraDialogoConMensaje("No se encontraron los ingresos y egresos del dia solicitado");
									tablaBalance.setValueAt("", 1, 1);
									tablaBalance.setValueAt("", 2, 1);
									tablaBalance.setValueAt("", 3, 1);
									tablaBalance.setValueAt("", 4, 1);
									tablaBalance.setValueAt("", 5, 1);
									tablaBalance.setValueAt("", 1, 2);
									tablaBalance.setValueAt("", 2, 2);
									tablaBalance.setValueAt("", 3, 2);
									tablaBalance.setValueAt("", 4, 2);
									tablaBalance.setValueAt("", 5, 2);
									tablaBalance.setValueAt("", 1, 3);
									tablaBalance.setValueAt("", 2, 3);
									tablaBalance.setValueAt("", 3, 3);
									tablaBalance.setValueAt("", 4, 3);
									tablaBalance.setValueAt("", 5, 3);
									break;
							}
							else if(ingresos==null) {
								muestraDialogoConMensaje("No se encontraron los ingresos del dia solicitado");
								tablaBalance.setValueAt(egresos.getEfectivo(), 1, 2);
								tablaBalance.setValueAt(egresos.getTarjeta(), 2, 2);
								tablaBalance.setValueAt(egresos.getCredito(), 3, 2);
								tablaBalance.setValueAt(egresos.getVales(), 4, 2);
								tablaBalance.setValueAt(egresos.getTotal(), 5, 2);
								break;
							}
							else
								muestraDialogoConMensaje("No se encontraron los egresos del dia solicitado");
								tablaBalance.setValueAt(ingresos.getEfectivo(), 1, 1);
								tablaBalance.setValueAt(ingresos.getTarjeta(), 2, 1);
								tablaBalance.setValueAt(ingresos.getCredito(), 3, 1);
								tablaBalance.setValueAt(ingresos.getVales(), 4, 1);
								tablaBalance.setValueAt(ingresos.getTotal(), 5, 1);
								break;
						}
						else {
							tablaBalance.setValueAt(ingresos.getEfectivo(), 1, 1);
							tablaBalance.setValueAt(ingresos.getTarjeta(), 2, 1);
							tablaBalance.setValueAt(ingresos.getCredito(), 3, 1);
							tablaBalance.setValueAt(ingresos.getVales(), 4, 1);
							tablaBalance.setValueAt(ingresos.getTotal(), 5, 1);
							tablaBalance.setValueAt(egresos.getEfectivo(), 1, 2);
							tablaBalance.setValueAt(egresos.getTarjeta(), 2, 2);
							tablaBalance.setValueAt(egresos.getCredito(), 3, 2);
							tablaBalance.setValueAt(egresos.getVales(), 4, 2);
							tablaBalance.setValueAt(egresos.getTotal(), 5, 2);
							tablaBalance.setValueAt(ingresos.getEfectivo()-egresos.getEfectivo(), 1, 3);
							tablaBalance.setValueAt(ingresos.getTarjeta()-egresos.getTarjeta(), 2, 3);
							tablaBalance.setValueAt(ingresos.getCredito()-egresos.getCredito(), 3, 3);
							tablaBalance.setValueAt(ingresos.getVales()-egresos.getVales(), 4, 3);
							tablaBalance.setValueAt(ingresos.getTotal()-egresos.getTotal(), 5, 3);
							setVisible(true);
						}
						break;
					default:
						break;
				}
			}
		});
		
		//Configuramos las proporciones de los elementos y los agregamos a la ventana
		dateChooser.setBounds(92, 73, 120, 19);
		dateChooser.setFont(fuenteVentana);
		getContentPane().add(dateChooser);
		btnCerrarCaja.setBounds(375, 63, 100, 29);
		btnCerrarCaja.setFont(fuenteVentana);
		getContentPane().add(btnCerrarCaja);
		btnGuardar.setBounds(92, 291, 85, 29);
		btnGuardar.setFont(fuenteVentana);
		btnGuardar.setEnabled(false);
		getContentPane().add(btnGuardar);
		btnRegresar.setBounds(390, 291, 85, 29);
		btnRegresar.setFont(fuenteVentana);
		getContentPane().add(btnRegresar);
		titulo.setFont(fuenteTitulo);
		titulo.setBounds(194, 10, 155, 29);
		getContentPane().add(titulo);
		tabbedPane.setBounds(92, 118, 383, 119);
		getContentPane().add(tabbedPane);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(380, 160, 550, 450);
		getContentPane().setLayout(null);
	}
	
	/**
	*Muestra en la ventana el corte de un dia.
	*
	* @param ControlGuardaGanancias control: Es el controlador que conecta con el servicio y la base de datos.
	* 		Corte corte: Es el corte del dia que se va a mostrar.
	* 		int tipoOperacion: Tipo de Corte (Ingresos = 1, Egresos = 2).
	* 
	* @return Void: Solo realiza la accion de mostrar
	**/
	public void muestra(ControlGuardaGanancias control, Corte corte, int tipoOperacion ) {
		this.control=control;
		switch(tipoOperacion) {
			case 1:tablaIngresos.setValueAt(corte.getEfectivo(), 1, 1);
				tablaIngresos.setValueAt(corte.getTarjeta(), 2, 1);
				tablaIngresos.setValueAt(corte.getCredito(), 3, 1);
				tablaIngresos.setValueAt(corte.getVales(), 4, 1);
				tablaIngresos.setValueAt(corte.getTotal(), 5, 1);
				setVisible(true);
				break;
			case 2:tablaEgresos.setValueAt(corte.getEfectivo(), 1, 1);
			tablaEgresos.setValueAt(corte.getTarjeta(), 2, 1);
			tablaEgresos.setValueAt(corte.getCredito(), 3, 1);
			tablaEgresos.setValueAt(corte.getVales(), 4, 1);
			tablaEgresos.setValueAt(corte.getTotal(), 5, 1);
			setVisible(true);
				break;
			default: 
				break;
		}
	}
	
	/**
	*Muestra un mensaje por medio de una subventana.
	*
	* @param String mensaje: El mensaje que desea mostrarse.
	* 
	* @return void: Solo muestra el mensaje.
	**/
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}