package mx.uam.ayd.proyecto.presentacion.abastecimiento;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.util.exception.RangoInvalidoException;

@Component
@SuppressWarnings("serial")
public class VentanaAbastecimiento extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private Object[][] matriz;
	private ControlAbastecimiento controlAbastecimiento;
	private DefaultTableModel model;
	private int flujo = 0;

	/**
	 * Create the frame.
	 */
	public VentanaAbastecimiento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(380, 130, 469, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		/*
		 * Esto hace que no se pueda editar la columna de los nombres
		 */
		table = new JTable() {

			public boolean isCellEditable(int row, int column) {
						
				if(column == 0) {
					return false;
				}else {
					return true;
				}
		   	}
        };
		        
		/*
		 * Este metodo impide que las columnas puedan cambiarse de orden
		 */
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(new DefaultTableModel(new Object[0][0] , new String[] { "Productos", "Cantidad", "Precio", "Seleccionado"
				
		}));
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		/*Object[][] matriz = control_vista.obtenMatriz();
		
	    table.setModel(new DefaultTableModel(matriz, new String[] { "Productos", "Cantidad", "Precio" }));
		table.setEnabled(false);
		scrollPane.setViewportView(table);*/
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 11));
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 11));
		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.setFont(new Font("Arial", Font.PLAIN, 11));
		JButton btnCancelar = new JButton("Regresar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		
		/*
		 * Boton de editar, habilita editar las cantidades y precios de los productos
		 */
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);	
				btnConfirmar.setEnabled(true);
				flujo = 1;
			}
		});
		
		/*
		 * Boton de solicitar, habilita las casillas para seleccionar los productos
		 */
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
				btnConfirmar.setEnabled(true);
				flujo = 2;
			}
		});
				
		/*
		 * Boton de confirmar, acepta los cambios realizados
		 */
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(false);
				/*
				 * Trae todos los datos de la tabla
				 */
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				
				/*
				 * Los datos obtenidos de la tabla se almacenan en una matriz provicional
				 */
				for (int fila = 0; fila < controlAbastecimiento.recuperaCantidadActual(); fila++) {
					matriz[fila][1] = model.getValueAt(fila, 1);
					matriz[fila][2]= model.getValueAt(fila, 2);
				}
				
				/*
				 * Se hacen excepciones por algun tipo de error de datos
				 */
				for (int fila = 0; fila < controlAbastecimiento.recuperaCantidadActual(); fila++) {
					
					try {
						Integer.parseInt(matriz[fila][1].toString());
					} catch (NumberFormatException error) { 
						muestraDialogo("Datos erroneos, no se admiten números decimales en cantidad y tampoco letras.");
						controlAbastecimiento.finalizaControlAbastecimiento();
						return;
					}
					
					try {
						Float.parseFloat(matriz[fila][2].toString());
					} catch (NumberFormatException error) { 
						muestraDialogo("Datos erroneos, no se admiten letras en precio.");
						controlAbastecimiento.finalizaControlAbastecimiento();
						return;
					}
					
					try {
					    validarValor(Integer.parseInt(matriz[fila][1].toString()));
					} catch (RangoInvalidoException error) {
						muestraDialogo("No se pueden almacenar más de 1000 productos.");
						controlAbastecimiento.finalizaControlAbastecimiento();
						return;
					}
					
					/*
					 * Devuelve la lista para almacenar los datos
					 */
					List <Producto> lista = new ArrayList<Producto>();
					/*
					 * Llena los datos de la lista de la matriz de objetos provisional
					 */
					for (int fila1 = 0; fila1 < matriz.length; fila1++) {
					    Producto product = new Producto(matriz[fila1][0].toString(),Float.parseFloat(matriz[fila1][2].toString()),Integer.parseInt(matriz[fila1][1].toString()));
					    lista.add(product);
					}
					
					controlAbastecimiento.actualizaProductos(lista);
					
				}
				if(flujo == 1) {
					muestraDialogo("Los cambios se han realizado con éxito.");
				}
				if(flujo == 2) {
					muestraDialogo("La solicitud ha sido enviada al proveedor.");
				}
				flujo = 0;
			}
		});
		
		/*
		 * Boton de cancelar, cierra la ventana
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAbastecimiento.finalizaControlAbastecimiento();
				//muestraDialogo("Bye");
			}
		});
		
		JLabel lblEncabezado = new JLabel("Abastecimiento de productos");
		lblEncabezado.setFont(new Font("Arial", Font.BOLD, 18));
		
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(24)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnEditar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSolicitar)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnConfirmar)
								.addGap(18)
								.addComponent(btnCancelar)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(33, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(94, Short.MAX_VALUE)
						.addComponent(lblEncabezado)
						.addGap(92))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblEncabezado)
						.addGap(14)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSolicitar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGap(17))
			);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	/*
	 * Este metodo ayuda a mostrar dialogos con mensajes especificos
	 */
	public void muestraDialogo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	/*
	 * Metodo de la clase Exception para validar el valor en las cantidades
	 */
	public void validarValor(int valor) throws RangoInvalidoException {
	    if (valor < 0 || valor > 1000) {
	        throw new RangoInvalidoException(valor);
	    }
	}

	/*
	 * Metodo de control para mostrar la ventana de esta clase
	 */
	public void muestra(ControlAbastecimiento controlAbastecimiento) {
		this.controlAbastecimiento = controlAbastecimiento;
		this.poblateData();
		setVisible(true);
    }
	
	/*
	 * Llama un metodo de Servicio para llenar los datos de la tabla
	 */
	private void poblateData() {
		this.matriz = controlAbastecimiento.obtenMatriz();
		
		model = new DefaultTableModel(matriz, new String[] { "Productos", "Cantidad", "Precio", "Seleccionado" }){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // La columna "Seleccionado" utiliza JCheckBox como clase de columna
                if (columnIndex == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
		
	    table.setModel(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 3) {
                    int row = e.getFirstRow();
                    boolean selected = (Boolean) table.getValueAt(row, 3);
                    /*
                     *  Aquí puedes realizar alguna acción basada en el estado del JCheckBox
                     */
                    System.out.println("Fila " + row + ", seleccionado: " + selected);
                }
            }
        });
		
		/*
		 * Ese metodo sirve para ordenar los datos de la tabla
		 */
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
	}
}
