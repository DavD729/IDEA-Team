package mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;

@Component
@SuppressWarnings("serial")
public class VentanaAgregarProducto extends JFrame {
	private ControlAgregarProducto controlProd;
	private ControlAgregarCategoria controlCat;
	private JPanel panelContenido;
	
	private JTextField textFieldProducto;
	private JTextField textFieldCantidad;
	private JTextField textFieldPrecio;
	private JComboBox<String> comboBoxCategoria;
	private JTextField textFieldCategoria;
	private JTextArea areaDescripcion;
	
	public VentanaAgregarProducto() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(380, 160, 750, 550);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(15, 18, 60, 15);
		panelContenido.add(lblProducto);
		
		textFieldProducto = new JTextField();
		textFieldProducto.setBounds(85, 15, 220, 25);
		panelContenido.add(textFieldProducto);
		textFieldProducto.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(15, 58, 60, 15);
		panelContenido.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(85, 55, 180, 25);
		panelContenido.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		textFieldCategoria.setEnabled(false);
		
		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setBounds(85, 100, 180, 25);
		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String)comboBoxCategoria.getSelectedItem();
				if(seleccion.toLowerCase().equals("otra")) {
					textFieldCategoria.setText("");
					textFieldCategoria.setEnabled(true);
				} else {
					textFieldCategoria.setText(seleccion);
					textFieldCategoria.setEnabled(false);
				}
			}
		});
		panelContenido.add(comboBoxCategoria);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(350, 15, 80, 15);
		panelContenido.add(lblDescripcion);
		
		areaDescripcion = new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(areaDescripcion);
		scrollPane.setBounds(350, 35, 350, 350);
		panelContenido.add(scrollPane);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(15, 403, 60, 15);
		panelContenido.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(85, 400, 100, 25);
		panelContenido.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(15, 443, 60, 15);
		panelContenido.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(65, 440, 120, 25);
		panelContenido.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JButton btnRegistra = new JButton("Registrar");
		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(areEmpty(textFieldProducto, textFieldCantidad, textFieldPrecio, textFieldCategoria) || areaDescripcion.getText().equals("")) {
					muestraErrorConMensaje("Verifique los campos, no deben estar vacios");
				} else {
					if(textFieldCategoria.getText().toLowerCase().equals("categoria") || textFieldCategoria.getText().toLowerCase().equals("otra") || isTooLong(textFieldProducto, 60) || areNaN(textFieldCantidad, textFieldPrecio) || isNegative(textFieldCantidad) || isNegative(textFieldPrecio) || areaDescripcion.getText().length() >= 350) {
						muestraErrorConMensaje("La información dada no es correcta, verifiquela");
					} else {
						if(muestraDialogoDeConfirmacion(String.format("El producto %s con cantidad: %s, y Precio: %s, está por ser registrado en: %s, ¿Desea continuar?", textFieldProducto.getText(), textFieldCantidad.getText(), textFieldPrecio.getText(), textFieldCategoria.getText())) == 0) {
							boolean flag = true;
							if(((String)comboBoxCategoria.getSelectedItem()).toLowerCase().equals("otra")) {
								flag = controlCat.registraCategoria(textFieldCategoria.getText());
							}
							if(flag) {
								long ID = controlProd.registraProducto(textFieldProducto.getText(), textFieldCategoria.getText(), Integer.parseInt(textFieldCantidad.getText()), Double.parseDouble(textFieldPrecio.getText()), areaDescripcion.getText()).getIdProducto();
								muestraDialogoConMensaje("El producto " + textFieldProducto.getText() + " ha sido registrado con ID: " + ID, "Registro");
								controlProd.finaliza();
							}
						}
					}
				}
			}
		});
		btnRegistra.setBounds(460, 470, 120, 29);
		panelContenido.add(btnRegistra);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProd.finaliza();
			}
		});
		btnCancela.setBounds(600, 470, 120, 29);
		panelContenido.add(btnCancela);
	}
	
	public void muestraVentana(ControlAgregarProducto controlProd, ControlAgregarCategoria controlCat, List<Categoria> categorias) {
		this.controlCat = controlCat;
		this.controlProd = controlProd;
		this.textFieldProducto.setText("");
		this.textFieldCantidad.setText("");
		this.textFieldPrecio.setText("");
		this.textFieldCategoria.setText("");
		this.areaDescripcion.setText("");
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		this.preparaCategorias(comboBoxModel, Set.copyOf(categorias));
		this.comboBoxCategoria.setModel(comboBoxModel);
		
		
		this.setVisible(true);
	}
	
	private void preparaCategorias(DefaultComboBoxModel<String> comboBox, Set<Categoria> categorias) {
		comboBox.addElement("");
		categorias.stream().filter(element -> !element.getNombre().toLowerCase().equals("otra")).forEach(categoria -> {
			comboBox.addElement(categoria.getNombre());
		});
		comboBox.addElement("Otra");
	}
	
	public int muestraDialogoDeConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(this, mensaje);
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void muestraDialogoConMensaje(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void muestraErrorConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private boolean isTooLong(JTextField texto, int limit) {
		return texto.getText().length() >= limit;
	}
	
	private boolean areNaN(JTextField... textos) {
		try {
			for(JTextField texto : textos) {
				Float.parseFloat(texto.getText());
			}
		} catch(NumberFormatException e) {
			return true;
		}
		return false;
	}
	
	private boolean isNegative(JTextField texto) {
		float number = -1;
		try {
			number = Float.parseFloat(texto.getText());
		} catch(NumberFormatException e) {
			return true;
		}
		return number <= 0;
	}
	
	private boolean areEmpty(JTextField... campos) {
		for(JTextField campo : campos) {
			if(campo.getText().equals("")) return true;
		}
		return false;
	}
}
