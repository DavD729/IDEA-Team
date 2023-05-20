package mx.uam.ayd.proyecto.presentacion.corte;

import javax.swing.JFrame;
import org.springframework.stereotype.Component;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;


import mx.uam.ayd.proyecto.negocio.modelo.Corte;


import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
@Component

public class VentanaGuardaGanancias extends JFrame{	
	private JTable Ingresos;
	private JTable Egresos;
	private JTable Balance;
	private ControlGuardaGanancias control;
	private Date Fecha;
	public VentanaGuardaGanancias() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(380, 160, 550, 450);
		getContentPane().setLayout(null);
		
		Calendar calendario = Calendar.getInstance();
		Fecha = calendario.getTime();
		
		JDateChooser dateChooser = new JDateChooser();
		JTextField textField1 = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        textField1.setEditable(false); 
		dateChooser.setDate(Fecha);
		dateChooser.getCalendarButton().addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent e) {
				Fecha = dateChooser.getDate();
			}
		});
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
		tabbedPane.addTab("Egresos", null, Egresos, null);
		
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

				control.guardarCorte(Date, (double)Ingresos.getValueAt(1, 1), (double)Ingresos.getValueAt(2, 1), (double)Ingresos.getValueAt(3, 1),
						(double)Ingresos.getValueAt(4, 1), (double)Ingresos.getValueAt(5, 1));
				//control.finaliza();
			}
		});
		
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!btnGuardar.isEnabled())
					btnGuardar.setEnabled(true);
				btnCerrarCaja.setEnabled(false);
			}
		});
		
		dateChooser.addAncestorListener(new AncestorListener() {
			public void ancestorMoved(AncestorEvent event) {
				Date FechaCal;
				FechaCal = dateChooser.getDate();
				LocalDate DateCal = FechaCal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate DateAc = LocalDate.now();
				if(DateCal != DateAc) {
					Corte corte = new Corte();
					corte = control.buscaCorte(DateCal);
					muestra(control, corte);
				}
			}

			@Override
			public void ancestorAdded(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
								
			}
		});
	}
	
	public void muestra(ControlGuardaGanancias control, Corte corte ) {
		this.control=control;
		Ingresos.setValueAt(corte.getEfectivo(), 1, 1);
		Ingresos.setValueAt(corte.getTarjeta(), 2, 1);
		Ingresos.setValueAt(corte.getCredito(), 3, 1);
		Ingresos.setValueAt(corte.getVales(), 4, 1);
		Ingresos.setValueAt(corte.getTotal(), 5, 1);
		if(corte.getDate()!=null)
			System.out.println("XD");
		setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
