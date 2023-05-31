package mx.uam.ayd.proyecto.presentacion.inventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("serial")
public class VentanaInventario extends JFrame {
	private ControlInventario controlI;
	private JPanel panelContenido;
	private JTable tablaInventario;
	private JScrollPane scrollTable;
	private List<String> encabezados = List.of("ID", "Producto", "En Stock", "Categoria", "Precios");
	
	public VentanaInventario() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(350, 130, 700, 600);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		tablaInventario = new JTable(new DefaultTableModel(getDefaultData(), encabezados.toArray())) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		scrollTable = new JScrollPane(tablaInventario);
		scrollTable.setBounds(30, 30, 625, 480);
		panelContenido.add(scrollTable);
		
		JLabel etiqueta = new JLabel("Inventario");
		etiqueta.setBounds(15, 5, 440, 16);
		panelContenido.add(etiqueta);
		
		JButton btnRegistra = new JButton("Registrar Producto");
		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlI.agregaProducto();
			}
		});
		btnRegistra.setBounds(15, 520, 150, 29);
		panelContenido.add(btnRegistra);
		
		JButton btnHistorial= new JButton("Historial de ventas");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Historial");
				log.info("WIP HU-09");
			}
		});
		btnHistorial.setBounds(180, 520, 150, 29);
		panelContenido.add(btnHistorial);
		
		JButton btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlI.finalizaControlInventario();
			}
		});
		btnRegresa.setBounds(550, 520, 120, 29);
		panelContenido.add(btnRegresa);
	}
	
	public void muestraContenido(ControlInventario control) {
		this.controlI = control;
		this.setVisible(true);
	}
	
	private Object[][] getDefaultData() {
		return new String[][] {
			{"Juan", "Pérez", "35"},
			{"María", "Gómez", "28"},
			{"Pedro", "López", "42"}
		};
	}
}
