package mx.uam.ayd.proyecto.presentacion.menuOnline;

import java.awt.BorderLayout;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.OrdenProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto1;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;

/**
 * VentanaCarrito

 *
 */
@SuppressWarnings({ "serial", "unused" })
@Component
public class VentanaCarrito extends JFrame {

	private ControlMenuOnline controlMenuOnline; //Control asociado a esta ventana
	private LinkedList<Producto1> pedidos; // Lista de  pedidos por el empleado
	private JPanel contentPane; // Panel principal de esta ventana
	private float total; // Total a pagar por el Cliente
	private DefaultTableModel modelo = new DefaultTableModel(
			new Object[][] {
		},
		new String[] {
			"Productos", "Cantidad", "Precio"
		}); // Modelo de la JTable de esta ventana
	private JTable tablaSeleccion = new JTable(); //Muestra al empleado el pedido que ha realizado a través de una tabla

	/**
	 * Create the frame.
	 */
	
	
	public VentanaCarrito() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 300, 512, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
	
		
		JLabel lblSuOrden = new JLabel("Su orden:");
		panelNorte.add(lblSuOrden, BorderLayout.SOUTH);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(tablaSeleccion);
		panelCentro.add(scrollPane);
		
		JPanel panelEdit = new JPanel();
		panelCentro.add(panelEdit, BorderLayout.SOUTH);
		panelEdit.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		JButton btnRegresar = new JButton("Regresar a seleccion");
		btnRegresar.setFont(new Font("Dialog", Font.BOLD, 20));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlMenuOnline.ventanaMenuEnabled();
				setVisible(false);
			}
		});
		
	
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelSur.add(btnRegresar);
	
		JButton btnOrdenar = new JButton("Cobrar");
		btnOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(creaOrden()) {
					JOptionPane.showMessageDialog(null, "Orden creada con exito.");
				}
			}
		});
		btnOrdenar.setFont(new Font("Dialog", Font.BOLD, 20));
		panelSur.add(btnOrdenar);
	}
		
	
		
	/**
	 * Crea una nueva OrdenOnline en la capa de datos
	 * @return true si la orden se creó, false en caso contrario
	 */
	private boolean creaOrden() {
		return controlMenuOnline.creaOrden(pedidos, total);
	}
		
		



	/**
	 * Muestra esta ventana
	 * @param controlMenuOnline Control asociado a la ventana
	 * @param productoElegidos Lista de productos elegidos por el empleado
	 */
	public void muestra(ControlMenuOnline controlMenuOnline, LinkedList<Producto1> productoElegidos) {
		this.controlMenuOnline = controlMenuOnline;
		this.pedidos = productoElegidos;
		
		
		
	
		
		if(!productoElegidos.isEmpty()) {
			setTitle("Compra Online");
			llenaJModel(productoElegidos);
			this.tablaSeleccion.setModel(this.modelo);
			setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "No se han seleccionado productos. ¿Como fue que llegó aqui?");
		}
	}


	




	/**
	 * Llena el JModel de la tabla de esta ventana con la informacion de compra de los productos elegidos por el empleado
	 * @param productosElegidos Los productos elegidos por el empleado
	 */
	private void llenaJModel(LinkedList<Producto1> productoElegidos) {
		this.modelo.setColumnCount(3);
		
		LinkedList<Producto1> productoFiltro = new LinkedList<Producto1>();
		for(Producto1 p: productoElegidos) {
			if(!productoFiltro.contains(p)){
				productoFiltro.add(p);
			}
		}
		this.modelo.setRowCount(productoFiltro.size()+2);
		
		int incidencias, index=0;
		float subtotal;
		total=0; 
		
		for(Producto1 p: productoFiltro) {
			incidencias = cuentaIncidencias(p,productoElegidos);
			subtotal = productoFiltro.get(index).getEPrecio()*incidencias;
			total += subtotal;
			this.modelo.setValueAt(productoFiltro.get(index).getNombre(), index, 0);
			this.modelo.setValueAt(incidencias, index, 1);
			this.modelo.setValueAt(subtotal, index, 2);
			index++;
		}
		this.modelo.setValueAt("-----", index, 0);
		this.modelo.setValueAt("-----", index, 1);
		this.modelo.setValueAt("-----", index, 2);
		this.modelo.setValueAt("TOTAL", index+1, 0);
		this.modelo.setValueAt("  -  ", index+1, 1);
		this.modelo.setValueAt(total, index+1, 2);
		
	}
	


	
	/**
	 * Cuenta el número de incidencias de un producto en una lista de producto
	 * @param p El producto cuyas incidencias se requiere contar
	 * @param l La lista de producto
	 * @return El número de incidencias del producto en la lista de producto
	 */
	public int cuentaIncidencias(Producto1 p, LinkedList<Producto1> l) {
		int incidencias=0;
		for(Producto1 producto: l) {
			if(producto.equals(p)) {
				incidencias++;
			}
		}
		return incidencias;
	}

	

}
