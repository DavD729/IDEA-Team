package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
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

import com.toedter.calendar.JDateChooser;

@Component
@SuppressWarnings("serial")
public class VentanaHistorialVenta extends JFrame {
	private ControlHistorialVenta controlHistorialVenta;
	private JPanel panelContenido;
	private JTable tablaInventario;
	private JScrollPane scrollTable;
	private JDateChooser seleccionadorFecha;
	private List<String> encabezados = List.of("Producto", "Vendidos", "ID");
	
	public VentanaHistorialVenta() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(1045, 130, 700, 500);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel titulo = new JLabel("Historial de Venta");
		titulo.setBounds(15, 5, 440, 16);
		panelContenido.add(titulo);
		
		seleccionadorFecha = new JDateChooser();
		seleccionadorFecha.setBounds(30, 25, 130, 25);
		panelContenido.add(seleccionadorFecha);
		
		tablaInventario = new JTable() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollTable = new JScrollPane(tablaInventario);
		scrollTable.setBounds(30, 60, 625, 350);
		panelContenido.add(scrollTable);
		
		JButton btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(actionEvent -> {
			controlHistorialVenta.finalizaControlHistorial();
		});
		btnRegresa.setBounds(550, 420, 120, 29);
		panelContenido.add(btnRegresa);
		
		JButton btnExportaPDF = new JButton("Exportar a PDF");
		btnExportaPDF.addActionListener(actionEvent -> {
			controlHistorialVenta.exportaTablaPDF();
		});
		btnExportaPDF.setBounds(15, 420, 120, 29);
		panelContenido.add(btnExportaPDF);
	}
	
	public void muestraVentana(ControlHistorialVenta controlHVenta) {
		this.controlHistorialVenta = controlHVenta;
		this.poblarInformacion();
		this.setVisible(true);
	}
	
	private void poblarInformacion() {
		this.tablaInventario.setModel(new DefaultTableModel(controlHistorialVenta.recuperaTabla(YearMonth.now()), encabezados.toArray()));
		this.seleccionadorFecha.setDate(Date.valueOf(LocalDate.now()));
	}
}
