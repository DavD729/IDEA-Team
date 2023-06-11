package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

@Component
@SuppressWarnings("serial")
public class VentanaHistorialVenta extends JFrame {
	private ControlHistorialVenta controlHistorialVenta;
	private JPanel panelContenido;
	private JTable tablaInventario;
	private JScrollPane scrollTable;
	private JDateChooser seleccionadorFecha;
	private YearMonth fechaPrevia;
	private List<String> encabezados = List.of("Producto", "Vendidos", "ID");
	
	public VentanaHistorialVenta() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(1045, 130, 700, 500);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Historial de Venta");
		Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);
		lblTitulo.setBounds(30, 15, 440, 16);
		lblTitulo.setFont(fuenteTitulo);
		panelContenido.add(lblTitulo);
		
		seleccionadorFecha = new JDateChooser();
		seleccionadorFecha.addPropertyChangeListener(eventoCambio -> {
			if("date".equals(eventoCambio.getPropertyName())) {
				actualizaTabla();
            }
		});
		
		seleccionadorFecha.setBounds(30, 45, 130, 25);
		panelContenido.add(seleccionadorFecha);
		
		tablaInventario = new JTable() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		scrollTable = new JScrollPane(tablaInventario);
		scrollTable.setBounds(30, 80, 625, 330);
		panelContenido.add(scrollTable);
		
		JButton btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(actionEvent -> {
			controlHistorialVenta.finalizaControlHistorial();
		});
		
		btnRegresa.setBounds(550, 420, 120, 29);
		panelContenido.add(btnRegresa);
		
		JButton btnExportaPDF = new JButton("Exportar a PDF");
		btnExportaPDF.addActionListener(actionEvent -> {
			exportaTablaPDF(fechaPrevia);
		});
		
		btnExportaPDF.setBounds(15, 420, 120, 29);
		panelContenido.add(btnExportaPDF);
	}
	
	public void muestraVentana(ControlHistorialVenta controlHVenta) {
		this.controlHistorialVenta = controlHVenta;
		this.poblarInformacion();
		this.setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void muestraErrorConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private boolean exportaTablaPDF(YearMonth fecha) {
		Document pdf = new Document();
		JFileChooser seleccionadorArchivo = new JFileChooser();
		int userSelection = seleccionadorArchivo.showSaveDialog(null);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String filePath = seleccionadorArchivo.getSelectedFile().getAbsolutePath();
			String[][] datos = controlHistorialVenta.recuperaTabla(fecha);
			
			try {
				PdfWriter.getInstance(pdf, new FileOutputStream(filePath));
				pdf.open();
				PdfPTable tabla = new PdfPTable(datos[0].length);
				
				for (String encabezado : datos[0]) {
					PdfPCell celda = new PdfPCell();
					celda.setPhrase(new com.itextpdf.text.Phrase(encabezado));
					tabla.addCell(celda);
				}
				
				for (int i = 1; i < datos.length; i++) {
					for (String celdaDatos : datos[i]) {
						tabla.addCell(celdaDatos);
					}
				}
				
				pdf.add(tabla);
				pdf.close();
				
			} catch (DocumentException e) {
				muestraErrorConMensaje("La información de la tabla no es valida");
				return false;
				
			} catch (ArrayIndexOutOfBoundsException e) {
				muestraErrorConMensaje("No se puede exportar una Tabla vacia");
				return false;
				
			} catch (FileNotFoundException e) {
				muestraErrorConMensaje("La ubicación no existe o no puede ser accedida");
				return false;
				
			}
			
			muestraDialogoConMensaje(String.format("El documento %s, ha sido guardado exitosamente", seleccionadorArchivo.getSelectedFile().getName()));
			return true;
		} 
		return false;
	}
	
	private void poblarInformacion() {
		this.fechaPrevia = YearMonth.now();
		this.tablaInventario.setModel(new DefaultTableModel(controlHistorialVenta.recuperaTabla(fechaPrevia), encabezados.toArray()));
		this.seleccionadorFecha.setDate(Date.valueOf(LocalDate.now()));
	}
	
	private void actualizaTabla() {
		LocalDate fecha = seleccionadorFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		YearMonth cambio = YearMonth.of(fecha.getYear(), fecha.getMonth());
		
		if(cambio.compareTo(fechaPrevia) != 0) {
			tablaInventario.setModel(new DefaultTableModel(controlHistorialVenta.recuperaTabla(cambio),encabezados.toArray()));
			fechaPrevia = cambio;
		}
	}
}
