package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
		seleccionadorFecha.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if("date".equals(evt.getPropertyName())) {
					actualizaTabla();
                }
			}
		});
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
			LocalDate fecha = seleccionadorFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			exportaTablaPDF(YearMonth.of(fecha.getYear(), fecha.getMonth()));
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
		Document document = new Document();
		JFileChooser fileChooser = new JFileChooser();
		String nombreArchivo = "";
		int userSelection = fileChooser.showSaveDialog(null);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			nombreArchivo = fileChooser.getSelectedFile().getName();
			String[][] datos = controlHistorialVenta.recuperaTabla(fecha);
			try {
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				document.open();
				PdfPTable table = new PdfPTable(datos[0].length);
				for (String header : datos[0]) {
					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new com.itextpdf.text.Phrase(header));
					table.addCell(cell);
				}
				for (int i = 1; i < datos.length; i++) {
					for (String cellData : datos[i]) {
						table.addCell(cellData);
					}
				}
				document.add(table);
				document.close();
				
			} catch (DocumentException e) {
				muestraErrorConMensaje("La información de la tabla no es valida");
				//e.printStackTrace();
				return false;
			} catch (ArrayIndexOutOfBoundsException e) {
				muestraErrorConMensaje("No se puede exportar una Tabla vacia");
				//e.printStackTrace();
				return false;
			} catch (FileNotFoundException e) {
				muestraErrorConMensaje("La ubicación no existe o no puede ser accedida");
				//e.printStackTrace();
				return false;
			}
		}
		muestraErrorConMensaje(String.format("El documento %s, ha sido guardado en %s", nombreArchivo));
		return true;
	}
	
	private void poblarInformacion() {
		this.tablaInventario.setModel(new DefaultTableModel(controlHistorialVenta.recuperaTabla(YearMonth.now()), encabezados.toArray()));
		this.seleccionadorFecha.setDate(Date.valueOf(LocalDate.now()));
	}
	
	private void actualizaTabla() {
		LocalDate fecha = seleccionadorFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		tablaInventario.setModel(new DefaultTableModel(controlHistorialVenta.recuperaTabla(YearMonth.of(fecha.getYear(), fecha.getMonth())),encabezados.toArray()));
	}
}
