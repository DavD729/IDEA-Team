package mx.uam.ayd.proyecto.presentacion.inventario.agregarProducto;

import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Categoria;
import mx.uam.ayd.proyecto.presentacion.inventario.agregarCategoria.ControlAgregarCategoria;

/**
 * Ventana que permite al usuario registrar productos a travez de esta interfaz grafica,
 * opera con los controles "AgregarProducto" y "AgregarCategoria"
 * 
 * @author David
 */

@Component
@SuppressWarnings("serial")
public class VentanaAgregarProducto extends JFrame {
	private ControlAgregarProducto controlProducto;
	private ControlAgregarCategoria controlCategoria;
	private JPanel panelContenido;
	
	private JTextField textFieldProducto;
	private JTextField textFieldCantidad;
	private JTextField textFieldPrecio;
	private JComboBox<String> comboBoxCategoria;
	private JTextField textFieldCategoria;
	private JTextArea areaDescripcion;
	
	public VentanaAgregarProducto() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(1045, 130, 750, 550);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Producto");
		Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);
		lblTitulo.setBounds(15, 10, 440, 25);
		lblTitulo.setFont(fuenteTitulo);
		panelContenido.add(lblTitulo);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(15, 53, 60, 15);
		panelContenido.add(lblProducto);
		
		textFieldProducto = new JTextField();
		textFieldProducto.setToolTipText("Producto");
		textFieldProducto.setBounds(85, 50, 220, 25);
		panelContenido.add(textFieldProducto);
		textFieldProducto.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(15, 88, 60, 15);
		panelContenido.add(lblCategoria);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setToolTipText("Categoria");
		textFieldCategoria.setBounds(85, 85, 180, 25);
		panelContenido.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		textFieldCategoria.setEnabled(false);
		
		comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setBounds(85, 120, 180, 25);
		comboBoxCategoria.addActionListener(actionEvent -> {
			String seleccion = (String)comboBoxCategoria.getSelectedItem();
			if(seleccion.toLowerCase().equals("otra")) {
				textFieldCategoria.setText("");
				textFieldCategoria.setEnabled(true);
			} else {
				textFieldCategoria.setText(seleccion);
				textFieldCategoria.setEnabled(false);
			}
		});
		panelContenido.add(comboBoxCategoria);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(350, 15, 80, 15);
		panelContenido.add(lblDescripcion);
		
		areaDescripcion = new JTextArea(10, 30);
		areaDescripcion.setToolTipText("Descripción");
		JScrollPane scrollPane = new JScrollPane(areaDescripcion);
		scrollPane.setBounds(350, 35, 350, 350);
		panelContenido.add(scrollPane);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(15, 403, 60, 15);
		panelContenido.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setToolTipText("Cantidad");
		textFieldCantidad.setBounds(85, 400, 100, 25);
		panelContenido.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(15, 443, 60, 15);
		panelContenido.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setToolTipText("Precio");
		textFieldPrecio.setBounds(65, 440, 120, 25);
		panelContenido.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JButton btnRegistra = new JButton("Registrar");
		btnRegistra.addActionListener(actionEvent -> {
			
			if(hayCuadrosVacios(List.of(textFieldProducto, textFieldCategoria, textFieldCantidad, textFieldPrecio, areaDescripcion))) return;
			
			if(hayInformacionInvalida()) return;
			
			if(muestraDialogoDeConfirmacion(String.format("El producto %s con cantidad: %s, y Precio: %s, está por ser registrado en: %s, ¿Desea continuar?", textFieldProducto.getText(), textFieldCantidad.getText(), textFieldPrecio.getText(), textFieldCategoria.getText())) == 0) {
				
				if(revisaPosiblesExistencias(textFieldProducto.getText())) {
					if(muestraAdvertenciaDeConfirmacion(String.format("Existen similitudes de %s en inventario, quiere continuar registro?", textFieldProducto.getText())) == 0) registraDatos();
				} else {
					registraDatos();
				}
			}
		});
		btnRegistra.setBounds(460, 470, 120, 29);
		panelContenido.add(btnRegistra);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProducto.finaliza();
			}
		});
		btnCancela.setBounds(600, 470, 120, 29);
		panelContenido.add(btnCancela);
	}
	
	public void muestraVentana(ControlAgregarProducto controlProd, ControlAgregarCategoria controlCat, List<Categoria> categorias) {
		this.controlCategoria = controlCat;
		this.controlProducto = controlProd;
		this.textFieldProducto.setText("");
		this.textFieldCantidad.setText("");
		this.textFieldPrecio.setText("");
		this.textFieldCategoria.setText("");
		this.areaDescripcion.setText("");
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		this.preparaCategorias(comboBoxModel, Set.copyOf(categorias));
		this.comboBoxCategoria.setModel(comboBoxModel);
		this.comboBoxCategoria.setSelectedIndex(0);
		
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
		return JOptionPane.showConfirmDialog(this, mensaje, UIManager.getString("OptionPane.titleText"), JOptionPane.YES_NO_OPTION);
	}
	
	public int muestraAdvertenciaDeConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(this, mensaje, "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
	
	private boolean hayCuadrosVacios(List<JTextComponent> componentesTexto) {
		for(JTextComponent componente : componentesTexto) {
			if(componente.getText().isEmpty()) {
				muestraErrorConMensaje(String.format("El campo %s se encuentra vacio", componente.getToolTipText()));
				return true;
			}
		}
		return false;
	}
	
	private boolean hayInformacionInvalida() {
		String comprobar;
		
		comprobar = textFieldProducto.getText();
		if(comprobar.length() > 60) {
			muestraErrorConMensaje(String.format("El campo %s contiene demasiados datos", textFieldProducto.getToolTipText()));
			return true;
		}
		
		comprobar = textFieldCategoria.getText().toLowerCase();
		if(comprobar.equals("otra") || comprobar.equals("categoria")) {
			muestraErrorConMensaje(String.format("El campo %s tiene una categoria invalida", textFieldCategoria.getToolTipText()));
			return true;
		}
		
		comprobar = textFieldCantidad.getText();
		try {
			if(Float.parseFloat(comprobar) < 0) throw new NumberFormatException("Negative value");
		} catch (NumberFormatException e) {
			muestraErrorConMensaje(String.format("El campo %s contiene datos no numericos o son negativos", textFieldCantidad.getToolTipText()));
			return true;
		}
		
		comprobar = textFieldPrecio.getText();
		try {
			if(Float.parseFloat(comprobar) < 0) throw new NumberFormatException("Negative value");
		} catch (NumberFormatException e) {
			muestraErrorConMensaje(String.format("El campo %s contiene datos no numericos o son negativos", textFieldPrecio.getToolTipText()));
			return true;
		}
		
		comprobar = areaDescripcion.getText();
		if(comprobar.length() > 400) {
			muestraErrorConMensaje(String.format("El campo %s contiene demasiados datos", areaDescripcion.getToolTipText()));
			return true;
		}
		
		return false;
	}
	
	private boolean revisaPosiblesExistencias(String nombre) {
		return controlProducto.hayExistenciasDeProductos(nombre);
	}
	
	private boolean registraDatos() {
		boolean flag = true;
		if(((String)comboBoxCategoria.getSelectedItem()).toLowerCase().equals("otra")) {
			flag = controlCategoria.registraCategoria(textFieldCategoria.getText());
		}
		
		if(flag) {
			long ID = controlProducto.registraProducto(textFieldProducto.getText(), textFieldCategoria.getText(), Integer.parseInt(textFieldCantidad.getText()), Double.parseDouble(textFieldPrecio.getText()), areaDescripcion.getText()).getIdProducto();
			muestraDialogoConMensaje("El producto " + textFieldProducto.getText() + " ha sido registrado con ID: " + ID, "Registro");
			controlProducto.finaliza();
		}
		return flag;
	}
}
