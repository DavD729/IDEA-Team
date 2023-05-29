package mx.uam.ayd.proyecto.presentacion.abastecimiento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.exception.RangoInvalidoException;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
@SuppressWarnings("serial")
public class Vista_modifica extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private Object[][] matriz;
	private Control_vista control_vista;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public Vista_modifica() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 568, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		//Esto hace que no se pueda editar la columna de los nombres
		table = new JTable() {

			public boolean isCellEditable(int row, int column) {
						
				if(column == 0) {
					return false;
				}else {
					return true;
				}
		   	}
        };
		        
		//Este metodo impide que las columnas puedan cambiarse de orden
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(new DefaultTableModel(new Object[0][0] , new String[] { "Productos", "Cantidad", "Precio" }));
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		/*Object[][] matriz = control_vista.obtenMatriz();
		
	    table.setModel(new DefaultTableModel(matriz, new String[] { "Productos", "Cantidad", "Precio" }));
		table.setEnabled(false);
		scrollPane.setViewportView(table);*/
		
		//Boton de actualizar
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);	
			}
		});
				
		//Boton de confirmar
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(false);
				//Trae todos los datos de la tabla
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				
				//Los datos obtenidos de la tabla se almacenan en una matriz provicional
				for (int fila = 0; fila < control_vista.recuperaCantidadActual(); fila++) {
					matriz[fila][1] = model.getValueAt(fila, 1);
					matriz[fila][2]= model.getValueAt(fila, 2);
				}
				
				//Se hacen excepciones por algun tipo de error de datos
				for (int fila = 0; fila < control_vista.recuperaCantidadActual(); fila++) {
					
					try {
						Integer.parseInt(matriz[fila][1].toString());
					} catch (NumberFormatException error) { 
						muestraDialogo("Datos erroneos.");
						control_vista.finalizaControlAbastecimiento();
						return;
					}
					
					try {
						Float.parseFloat(matriz[fila][2].toString());
					} catch (NumberFormatException error) { 
						muestraDialogo("Datos erroneos.");
						control_vista.finalizaControlAbastecimiento();
						return;
					}
					
					try {
					    validarValor(Integer.parseInt(matriz[fila][1].toString()));
					} catch (RangoInvalidoException error) {
						muestraDialogo("No se pueden almacenar más de 100 productos.");
						control_vista.finalizaControlAbastecimiento();
						return;
					}
					
					//Devuelve la lista para almacenar los datos
					List <Producto> lista = new ArrayList<Producto>();
					//Llena los datos de la lista de la matriz de objetos provisional
					for (int fila1 = 0; fila1 < matriz.length; fila1++) {
					    Producto product = new Producto(matriz[fila1][0].toString(),Float.parseFloat(matriz[fila1][2].toString()),Integer.parseInt(matriz[fila1][1].toString()));
					    lista.add(product);
					}
					
					control_vista.actualizaProductos(lista);
					
				}
				muestraDialogo("Los cambios se han realizado con éxito.");
			}
		});
		
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(24)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEditar).addComponent(btnConfirmar))
						.addContainerGap(25, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(15, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264,
												GroupLayout.PREFERRED_SIZE)
										.addGap(56))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(btnEditar).addGap(18).addComponent(btnConfirmar).addGap(79)))));
		
		contentPane.setLayout(gl_contentPane);
	}
	
	public void muestraDialogo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void validarValor(int valor) throws RangoInvalidoException {
	    if (valor < 0 || valor > 100) {
	        throw new RangoInvalidoException(valor);
	    }
	}

	public void muestra(Control_vista control_vista) {
		this.control_vista = control_vista;
		this.poblateData();
		setVisible(true);
    }
	
	private void poblateData() {
		this.matriz = control_vista.obtenMatriz();
		
		model = new DefaultTableModel(matriz, new String[] { "Productos", "Cantidad", "Precio" });
		
	    table.setModel(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		//Ese metodo sirve para ordenar los datos de la tabla
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
	}
}
