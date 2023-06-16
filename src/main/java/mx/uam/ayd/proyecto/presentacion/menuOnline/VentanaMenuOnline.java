package mx.uam.ayd.proyecto.presentacion.menuOnline;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto1;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * VentanaMenuOnline
 *
 *
 */
@SuppressWarnings("unused")
@Component
public class VentanaMenuOnline extends JFrame {
	
	private ControlMenuOnline controlMenuOnline; //Control asociado a esta ventana
	private LinkedList<PanelButtonSpinner> panelesProductos; //Contiene un panel por cada platillo que encuentre en la BD
	private LinkedList<Producto1> productosElegidos; //Contiene los platillos que ha elegido el cliente para añadirlos al carrito
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; //Panel principal de esta ventana
	private JPanel panelContenedorProductos; //Panel central que muestra todo el menu
	private JPanel panelTipo1; // Panel donde se colocan los platillos que son "despensa"
	private JPanel panelTipo2; // Panel donde se colocan los platillos que son "galletas"
	private JPanel panelTipo3; // Panel donde se colocan los platillos que son "lacteos"
	private JPanel panelTipo4; // Panel donde se colocan los platillos que son "bebidas"
	
	/**
	 * Create the frame.
	 */
	public VentanaMenuOnline() {
		setTitle("Menu Online");
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 300, 512, 512);
		//setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
	
	
		JLabel instrucciones = new JLabel("Seleccionar los platillos de su orden y presionar 'Añadir al carrito' ");
		panelNorte.add(instrucciones, BorderLayout.WEST);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout());
		JButton btnBorrarSeleccion = new JButton("Borrar seleccion");
		btnBorrarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productosElegidos = new LinkedList<Producto1>();
				reiniciaSpinners();
			}

			public void reiniciaSpinners() {
				for(PanelButtonSpinner p: panelesProductos) {
					p.reiniciaSpinner();
				}
				
			}
		});
		panelSur.add(btnBorrarSeleccion);
		
		JButton btnCarrito = new JButton("     Añadir al carrito");
		btnCarrito.setIcon(new ImageIcon("MenuOnline_iconos/carrito.png"));
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaProductosElegidos();
			}

			public void actualizaProductosElegidos() {
				int spinnerValue;
				boolean flagSeleccion = false;
				productosElegidos = new LinkedList<Producto1>();
				for(PanelButtonSpinner p:panelesProductos) {
					spinnerValue = p.getSpinnerValue();
					if(spinnerValue!=0) {
						flagSeleccion = true;
						for(int i=0; i<spinnerValue; i++) {
							productosElegidos.add(p.getProducto());
						}
					}
				}
				if(flagSeleccion) {
					controlMenuOnline.goToCarrito(productosElegidos);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el numero de productos para añadir al carrito");
				}
				
			}

		});
		panelSur.add(btnCarrito);
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentro.add(scrollPane);
		
		this.panelContenedorProductos = new JPanel();
		scrollPane.setViewportView(panelContenedorProductos);
		GridBagLayout gbl_panelContenedorProductos = new GridBagLayout();
		gbl_panelContenedorProductos.columnWidths = new int[]{1287, 0};
		gbl_panelContenedorProductos.rowHeights = new int[]{157, 157, 157, 157, 0};
		gbl_panelContenedorProductos.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelContenedorProductos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelContenedorProductos.setLayout(gbl_panelContenedorProductos);
		
		JPanel panel1 = new JPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.insets = new Insets(0, 0, 5, 0);
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 0;
		panelContenedorProductos.add(panel1, gbc_panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTipo1 = new JLabel("");
		lblTipo1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblTipo1, BorderLayout.NORTH);
		ImageIcon icon1 = new ImageIcon("MenuOnline_iconos/despensa.png");
		Image img1 = icon1.getImage();
		Image img1Scaled = img1.getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH);
		lblTipo1.setIcon(new ImageIcon(img1Scaled));
		
		this.panelTipo1 = new JPanel();
		panel1.add(panelTipo1, BorderLayout.CENTER);
		panelTipo1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel2 = new JPanel();
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		panelContenedorProductos.add(panel2, gbc_panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTipo2 = new JLabel("");
		lblTipo2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon2 = new ImageIcon("MenuOnline_iconos/galles.png");
		Image img2 = icon2.getImage();
		Image img2Scaled = img2.getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH);
		lblTipo2.setIcon(new ImageIcon(img2Scaled));
		panel2.add(lblTipo2, BorderLayout.NORTH);
		
		this.panelTipo2 = new JPanel();
		panel2.add(panelTipo2, BorderLayout.CENTER);
		panelTipo2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel3 = new JPanel();
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.insets = new Insets(0, 0, 5, 0);
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 2;
		panelContenedorProductos.add(panel3, gbc_panel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTipo3 = new JLabel("");
		lblTipo3.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon3 = new ImageIcon("MenuOnline_iconos/lacteos1.png");
		Image img3 = icon3.getImage();
		Image img3Scaled = img3.getScaledInstance(250, 100, java.awt.Image.SCALE_SMOOTH);
		lblTipo3.setIcon(new ImageIcon(img3Scaled));
		panel3.add(lblTipo3, BorderLayout.NORTH);
		
		this.panelTipo3 = new JPanel();
		panel3.add(panelTipo3);
		panelTipo3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel4 = new JPanel();
		GridBagConstraints gbc_panel4 = new GridBagConstraints();
		gbc_panel4.fill = GridBagConstraints.BOTH;
		gbc_panel4.gridx = 0;
		gbc_panel4.gridy = 3;
		panelContenedorProductos.add(panel4, gbc_panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTipo4 = new JLabel("");
		lblTipo4.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon4 = new ImageIcon("MenuOnline_iconos/bebidas.png");
		Image img4 = icon4.getImage();
		Image img4Scaled = img4.getScaledInstance(300, 100, java.awt.Image.SCALE_SMOOTH);
		lblTipo4.setIcon(new ImageIcon(img4Scaled));
		panel4.add(lblTipo4, BorderLayout.NORTH);
		
		this.panelTipo4 = new JPanel();
		panel4.add(panelTipo4);
		panelTipo4.setLayout(new GridLayout(0, 3, 0, 0));
		
		
	}

	/**
	 * Muestra esta ventana
	 * @param controlMenuOnline Controlador de esta ventana
	 * @param productos La lista completa de productos habilitados para su venta online
	 */
	public void muestra (ControlMenuOnline controlMenuOnline, LinkedList<Producto1> producto) {
		this.controlMenuOnline = controlMenuOnline;
		this.panelesProductos = new LinkedList<PanelButtonSpinner>();
		
		if(!producto.isEmpty()) {
			llenaPanelesProducto(producto);
			setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Imposible mostrar menu\n No se tiene registro de productos");
		}
	
	}
	
	/**
	 * Agrega tantos objetos de tipo PanelButonSpinner a la ventana como platillos existan
	 * @param platillos La lista completa de platillos a mostrar en la ventana
	 */
	public void llenaPanelesProducto(LinkedList<Producto1> producto) {
		
		for(Producto1 p: producto) {
			panelesProductos.add(new PanelButtonSpinner(p));
		}
		
		for(PanelButtonSpinner p: panelesProductos) {
			if(p.getProducto().getTipo() == 1)
				panelTipo1.add(p);
			if(p.getProducto().getTipo() == 2)
				panelTipo2.add(p);
			if(p.getProducto().getTipo() == 3)
				panelTipo3.add(p);
			if(p.getProducto().getTipo() == 4)
				panelTipo4.add(p);
		}
		
		
	}

	/**
	 * Clase auxiliar que encapsula la forma de representar cada productos con su imagen, descripción y precio en un solo objeto abstracto
	 *
	 */
	class PanelButtonSpinner extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JPanel panelAux;
		JPanel panelNorte;
		JPanel panelCentro;
		JPanel panelSur;
		JPanel panelCentroAux;
		JLabel foto;
		Producto1 producto;
		JLabel lblProducto;
		JLabel lblePrecio;
		LinkedList<JLabel> lblsComentarios;
		JSpinner spinner;

		/**
		 * Constructor de esta clase
		 * @param producto El producto cuyos datos serán mostrados en este PanelButtonSpinner
		 */
		public PanelButtonSpinner(Producto1 producto) {
			this.setLayout(new GridLayout(0, 1, 0, 0));
			this.producto = producto;
			iniciaEtiquetas();
			iniciaFoto();
			iniciaSpinner();
			iniciaPanelAux();
			iniciaComentarios();
			this.add(foto);
			this.add(panelAux);
			
		}
		
		/**
		 * Inicializa las JLabel de esta clase
		 */
		void iniciaEtiquetas() {
			this.lblProducto = new JLabel(producto.getNombre());
			this.lblProducto.setFont(new Font("Dialog", Font.BOLD, 20));
			this.lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
			this.lblePrecio = new JLabel("  $"+producto.getEPrecio()+"  ");
			this.lblePrecio.setFont(new Font("Dialog", Font.BOLD, 15));
			this.lblePrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		
		/**
		 * Configura la foto del producto para ser mostrada en la ventana adecuadamente
		 */
		void iniciaFoto() {
			this.foto = new JLabel();
			ImageIcon icono = new ImageIcon(producto.getImagen());
			ImageIcon aux = new ImageIcon();
			double alto=icono.getIconHeight(), ancho=icono.getIconWidth();
			/**
			 * La siguiente condicion sirve para redimensionar la imagen del botón en caso de que esté muy grande
			 */
			if(alto>200|ancho>200) {
				while(alto>200|ancho>200) {
					//Reduce el alto y el ancho al 90% del tamaño original
					alto = alto*0.9; 
					ancho= ancho*0.9;	
				}
			}
			aux = new ImageIcon(icono.getImage().getScaledInstance((int)ancho, (int)alto,java.awt.Image.SCALE_DEFAULT));
			icono = aux;
			this.foto.setIcon(icono);
			foto.setHorizontalAlignment(SwingConstants.CENTER);

		}

		/**
		 * Devuelve el JSpinner de esta clase a sus valores iniciales
		 */
		void iniciaSpinner() {
			this.spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
			this.spinner.setValue(0);
		}
		
		/**
		 * Pone el valor del JSpinner de esta clase en 0
		 */
		void reiniciaSpinner() {
			this.spinner.setValue(0);
		}
		
		/**
		 * Añade todos los componentes de esta clase al panel en el que serán mostrados
		 */
		void iniciaPanelAux() {
			this.panelAux = new JPanel();
			this.panelNorte = new JPanel();
			this.panelCentro = new JPanel();
			this.panelSur = new JPanel();
			this.panelCentroAux = new JPanel();
			this.panelAux.setLayout(new BorderLayout());
			this.panelCentro.setLayout(new BorderLayout());
			this.panelNorte.setLayout(new GridLayout(0, 3, 0, 0));
			this.panelCentroAux.setLayout(new GridLayout(0, 1, 0, 0));
			this.panelCentroAux.add(lblProducto);
			this.panelCentro.add(panelCentroAux, BorderLayout.NORTH);
			this.panelNorte.add(lblePrecio);
			this.panelNorte.add(spinner);
			this.panelNorte.add(new JLabel(""));
			this.panelNorte.add(new JLabel(""));
			this.panelAux.add(panelNorte, BorderLayout.NORTH);
			this.panelAux.add(panelCentro, BorderLayout.CENTER);
			this.panelAux.add(panelSur, BorderLayout.SOUTH);
			
			
		}
		/**
		 * Coloca los comentarios en JLabels agregando saltos de linea 
		 */
		void iniciaComentarios() {
			char[] comentariosArray = producto.getComentario().toCharArray();
			String aux = "";
			JLabel lblAux;
			boolean flag = false;
			char c;
			for(int i=0; i<comentariosArray.length;i++) {
				c = comentariosArray[i];
				aux = aux+c;
				if(i!=0) {
					/*Esta condicion permite determinar el numero de caracteres 
					 * que pueden representarse en una linea de texto al visualizar la ventana
					*/
					if(i%23==0) {
						while(c!=' ' && i<comentariosArray.length-1) {
							i++;
							c = comentariosArray[i];
							aux = aux+c;
							if(i==comentariosArray.length-1)
								flag=true;
						}
						lblAux = new JLabel(aux);
						lblAux.setFont(new Font("Dialog", Font.BOLD, 15));
						lblAux.setHorizontalAlignment(SwingConstants.CENTER);
						this.panelCentroAux.add(lblAux);
						aux = "";
					}
				}
			}
			if(!flag) {
				lblAux = new JLabel(aux);
				lblAux.setFont(new Font("Dialog", Font.BOLD, 15));
				lblAux.setHorizontalAlignment(SwingConstants.CENTER);
				this.panelCentroAux.add(lblAux);
				aux = "";
			}
			
		}

		/**
		 * 
		 * @return El platillo de este JButtonSpinner
		 */
		public Producto1 getProducto() {
			return producto;
		}

		/**
		 * Modifica el platillo de este JButtonSpinner
		 * @param producto El nuevo producto de este JButtonSpinner
		 */
		public void setPlatillo(Producto1 producto) {
			this.producto = producto;
			this.foto.setText(producto.getNombre() + "\n"+ producto.getEPrecio());
		}

		/**
		 * 
		 * @return El ePrecio (precio online) del producto de este JButtonSpinner
		 */
		public float getePrecio() {
			return producto.getEPrecio();
		}

		/**
		 * Modifica el precio del productoo de este JButtonSpinner
		 * @param ePrecio El nuevo ePrecio
		 */
		public void setePrecio(float ePrecio) {
			this.producto.setEPrecio(ePrecio);
			this.foto.setText(producto + "\n"+ ePrecio);
		}

		/**
		 * 
		 * @return La ruta de la imagen del platillo de este JButtonSpinner
		 */
		public String getRutaImagen() {
			return producto.getImagen();
		}

		/**
		 * 
		 * @param rutaImagen La nueva ruta de la imagen de este JButtonSpinner
		 */
		public void setRutaImagen(String rutaImagen) {
			producto.setImagen(rutaImagen);
			this.foto.setIcon(new ImageIcon(rutaImagen));
		}
		
		/**
		 * 
		 * @return El valor del JSpinner de este JButtonSpinner
		 */
		public int getSpinnerValue() {
			int valor = (Integer)this.spinner.getValue();
			return valor;
		}
		
	}
}
