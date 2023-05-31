package mx.uam.ayd.proyecto.presentacion.corte;

import javax.swing.JFrame;
import org.springframework.stereotype.Component;
import javax.swing.JButton;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;


import mx.uam.ayd.proyecto.negocio.modelo.Corte;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
@Component

public class VentanaGuardaGanancias extends JFrame{	
	private JTable Ingresos;
	private JTable Egresos;
	private JTable Balance;
	private ControlGuardaGanancias control;
	private Corte corte;
	private Date Fecha;
	public VentanaGuardaGanancias() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(380, 160, 550, 450);
		getContentPane().setLayout(null);
		
		Calendar calendario = Calendar.getInstance();
		Fecha = calendario.getTime();
		
		JDateChooser dateChooser = new JDateChooser();
		JCalendar calendar = dateChooser.getJCalendar();
		JTextField textField1 = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        textField1.setEditable(false); 
		dateChooser.setDate(Fecha);
		
		dateChooser.setBounds(92, 22, 120, 19);
		getContentPane().add(dateChooser);
		
		JButton btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.setBounds(390, 20, 100, 21);
		getContentPane().add(btnCerrarCaja);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(320, 220, 85, 21);
		btnGuardar.setEnabled(false);
		getContentPane().add(btnGuardar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(92, 72, 313, 123);
		getContentPane().add(tabbedPane);
		
		Ingresos = new JTable();
		Ingresos.setModel(new DefaultTableModel(
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
		
		Ingresos.setValueAt("Tipo de Ingreso", 0, 0);
		Ingresos.setValueAt("Efectivo", 1, 0);
		Ingresos.setValueAt("Tarjeta de Credito/Debito", 2, 0);
		Ingresos.setValueAt("Credito en Tienda", 3, 0);
		Ingresos.setValueAt("Vales de Despensa", 4, 0);
		Ingresos.setValueAt("Total", 5, 0);
		Ingresos.setValueAt("Cantidad", 0, 1);
		Ingresos.setEnabled(false);
		tabbedPane.addTab("Ingresos", null, Ingresos, null);
	
		Egresos = new JTable();
		Egresos.setModel(new DefaultTableModel(
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
		Egresos.setValueAt("Tipo de Ingreso", 0, 0);
		Egresos.setValueAt("Efectivo", 1, 0);
		Egresos.setValueAt("Tarjeta de Credito/Debito", 2, 0);
		Egresos.setValueAt("Credito en Tienda", 3, 0);
		Egresos.setValueAt("Vales de Despensa", 4, 0);
		Egresos.setValueAt("Total", 5, 0);
		Egresos.setValueAt("Cantidad", 0, 1);
		tabbedPane.addTab("Egresos", null, Egresos, null);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int pestaña = tabbedPane.getSelectedIndex();
				System.out.println(pestaña);
				switch (pestaña){
					case 0:
						control.inicia();
						break;
					case 1:
						if(!btnGuardar.isEnabled())
							btnGuardar.setEnabled(true);
						if(btnCerrarCaja.isEnabled())
							btnCerrarCaja.setEnabled(false);
						Date FechaCal;
						FechaCal = dateChooser.getDate();
						LocalDate DateCal = FechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						LocalDate DateAc = LocalDate.now();
						System.out.println(pestaña);
						System.out.println(DateCal);
						System.out.println(DateAc);
						if(DateCal != DateAc) {
							Corte corte = new Corte();
							corte = control.buscaCorte(DateCal,2);
							if(corte==null) {
								Egresos.setValueAt("", 1, 1);
								Egresos.setValueAt("", 2, 1);
								Egresos.setValueAt("", 3, 1);
								Egresos.setValueAt("", 4, 1);
								Egresos.setValueAt("", 5, 1);
								setVisible(true);
							}
							else
								muestra(control, corte,2);
						}
						else {
							/*
			//Creación de la tabla
							Object[][] data = {
					                {"Celda 1", "Celda 2"},
					                {"Celda 3", "Celda 4"},
					                {"Celda 5", "Celda 6"},
					                {"Celda 7", "Celda 8"},
					                {"Celda 9", "Celda 10"},
					                {"Celda 11", "Celda 12"}
					        };
					        // Columnas de ejemplo
					        Object[] columns = {"Columna 1", "Columna 2"};
					        boolean[] editableColumns = { true, false}; // Define qué columnas son editables 
					        Object[] defaultValues = {"Tipo de ingreso", "Efectivo", "Tarjeta de Credito/Debito", "Credito en Tienda"
					        							,"Vales de Despensa", "Total"};
					        							
					        // Crear un DefaultTableModel con los datos y columnas
					        CustomTableModel model = new CustomTableModel(data, columns, editableColumns, defaultValues);
					        Egresos.setModel(model);

					 		
					        // Personalizar el renderizador de celdas para mostrar las celdas deshabilitadas de manera visual
					        Egresos.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
					            @Override
					            public java.awt.Component getTableCellRendererComponent(JTable Egresos, Object value, boolean isSelected,
					                                                           boolean hasFocus, int row, int column) {
					            	java.awt.Component component = super.getTableCellRendererComponent(Egresos, value, isSelected, hasFocus, row, column);
					            	if (!editableColumns[column]) {
					                    component.setBackground(Color.LIGHT_GRAY);
					                    component.setForeground(Color.BLACK);
					                }
					                return component;
					            }
					        });
					        */
						}
						break;
					case 2:
						break;
					default: 
						break;
				}
			}
		});
		
		
		Balance = new JTable();
		Balance.setModel(new DefaultTableModel(
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
		tabbedPane.addTab("Balance", null, Balance, null);
		
		
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date Fecha;
				Fecha = dateChooser.getDate();
				LocalDate Date = Fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(tabbedPane.getSelectedComponent()==Ingresos) {
					control.guardarCorte(Date, (double)Ingresos.getValueAt(1, 1), (double)Ingresos.getValueAt(2, 1), 
							(double)Ingresos.getValueAt(3, 1), (double)Ingresos.getValueAt(4, 1), (double)Ingresos.getValueAt(5, 1),
							1);
				}
				else if(tabbedPane.getSelectedComponent()==Egresos){
					String Efectivo = (String) Egresos.getValueAt(1,1);
					String TDC = (String)Egresos.getValueAt(2,1);
					String Credito = (String)Egresos.getValueAt(3,1);
					String Vales = (String)Egresos.getValueAt(4,1);
					double Efe = Double.parseDouble(Efectivo);
					double Tdc = Double.parseDouble(TDC);
					double Cre = Double.parseDouble(Credito);
					double Val = Double.parseDouble(Vales);
					double Tot = Efe + Tdc + Cre + Val;
					Egresos.setValueAt(Tot, 5, 1);
					control.guardarCorte(Date, Efe, Tdc, Cre, Val, Tot, 2);
					}
					else {
						System.out.println("Balance");
					}
			}
		});
		
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnGuardar.isEnabled())
					btnGuardar.setEnabled(true);
				btnCerrarCaja.setEnabled(false);
			}
		});
		
		/*dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener(){						
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("date")) {
					Date FechaCal;
					FechaCal = dateChooser.getDate();
					LocalDate DateCal = FechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate DateAc = LocalDate.now();
					if(DateCal != DateAc && tabbedPane.getSelectedComponent()==Ingresos) {
						Corte corte = new Corte();
						corte = control.buscaCorte(DateCal,1);
						if(corte==null) {
							Ingresos.setValueAt("", 1, 1);
							Ingresos.setValueAt("", 2, 1);
							Ingresos.setValueAt("", 3, 1);
							Ingresos.setValueAt("", 4, 1);
							Ingresos.setValueAt("", 5, 1);
							setVisible(true);
							
						}
						else
							muestra(control, corte,1);
					}
					else if (DateCal != DateAc && tabbedPane.getSelectedComponent()==Egresos) {
						Corte corte = new Corte();
						corte = control.buscaCorte(DateCal,2);
						if(corte==null) {
							Egresos.setValueAt("", 1, 1);
							Egresos.setValueAt("", 2, 1);
							Egresos.setValueAt("", 3, 1);
							Egresos.setValueAt("", 4, 1);
							Egresos.setValueAt("", 5, 1);
							setVisible(true);
							
						}
						else
							muestra(control, corte,1);
					}
					else {
						System.out.println("Balance");
					}
		        }
				
			}
		});*/
		
		dateChooser.getCalendarButton().addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent e) {
				Date FechaCal;
				FechaCal = dateChooser.getDate();
				LocalDate DateCal = FechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate DateAc = LocalDate.now();
				if(DateCal != DateAc && tabbedPane.getSelectedComponent()==Ingresos) {
					Corte corte = new Corte();
					corte = control.buscaCorte(DateCal,1);
					if(corte==null) {
						Ingresos.setValueAt("", 1, 1);
						Ingresos.setValueAt("", 2, 1);
						Ingresos.setValueAt("", 3, 1);
						Ingresos.setValueAt("", 4, 1);
						Ingresos.setValueAt("", 5, 1);
						setVisible(true);
						
					}
					else
						muestra(control, corte,1);
				}
				else if (DateCal != DateAc && tabbedPane.getSelectedComponent()==Egresos) {
					Corte corte = new Corte();
					corte = control.buscaCorte(DateCal,2);
					if(corte==null) {
						Egresos.setValueAt("", 1, 1);
						Egresos.setValueAt("", 2, 1);
						Egresos.setValueAt("", 3, 1);
						Egresos.setValueAt("", 4, 1);
						Egresos.setValueAt("", 5, 1);
						setVisible(true);
					}
					else
						muestra(control, corte,2);
				}
				else {
					System.out.println("Balance");
				}
			}
		});
	}
	
	public void muestra(ControlGuardaGanancias control, Corte corte, int tipoOperacion ) {
		this.control=control;
		this.corte=corte;
		switch(tipoOperacion) {
			case 1:Ingresos.setValueAt(corte.getEfectivo(), 1, 1);
				Ingresos.setValueAt(corte.getTarjeta(), 2, 1);
				Ingresos.setValueAt(corte.getCredito(), 3, 1);
				Ingresos.setValueAt(corte.getVales(), 4, 1);
				Ingresos.setValueAt(corte.getTotal(), 5, 1);
				setVisible(true);
				break;
			case 2:Egresos.setValueAt(corte.getEfectivo(), 1, 1);
			Egresos.setValueAt(corte.getTarjeta(), 2, 1);
			Egresos.setValueAt(corte.getCredito(), 3, 1);
			Egresos.setValueAt(corte.getVales(), 4, 1);
			Egresos.setValueAt(corte.getTotal(), 5, 1);
			setVisible(true);
				break;
			default: System.out.println("Balance");
				break;
		}
		
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}