package mx.uam.ayd.proyecto.presentacion.inicio;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Esta ventana mostrara lo más basico y general que se mostrará siempre en todo momento.
 * Cada acción deberá ser manejada por su propio control y ventana, el panel de contenido
 * deberá ser mandado como atributo en los metodos subsecuentes para agregar contenido de
 * cada categoria.
 * 
 * @author David
 *
 */

@Slf4j
@Component
@SuppressWarnings("serial")
public class VentanaInicio extends JFrame {
	private JPanel panelContenido;
	private ControlInicio controlInicio;
	
	public VentanaInicio() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(130, 130, 225, 320);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel lblTitulo = new JLabel("<html><div style='text-align: center;'>Administración de<br>Tienda</div></html>");
		Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);
		lblTitulo.setBounds(25, 10, 440, 35);
		lblTitulo.setFont(fuenteTitulo);
		panelContenido.add(lblTitulo);
		
		// Sección Administración
		JButton btnAdministracion = new JButton("Administración");
		btnAdministracion.addActionListener(actionEvent -> {
			controlInicio.muestraContenidoAdministracion();
		});
		btnAdministracion.setBounds(15, 75, 178, 29);
		panelContenido.add(btnAdministracion);
		
		// Sección abastecimiento
		JButton btnAbastecimiento = new JButton("Abastecimiento");
		btnAbastecimiento.addActionListener(actionEvent -> {
			controlInicio.muestraContenidoAbastecimiento();
		});
		btnAbastecimiento.setBounds(15, 115, 178, 29);
		panelContenido.add(btnAbastecimiento);
		
		// Sección inventario
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(actionEvent -> {
			controlInicio.muestraContenidoInventario();
		});
		
		btnInventario.setBounds(15, 155, 178, 29);
		panelContenido.add(btnInventario);
		
		// Sección ventas
		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(actionEvent -> {
			log.info("Action Ventas");
		});
		btnVentas.setBounds(15, 195, 178, 29);
		panelContenido.add(btnVentas);
		
		// Sección corte de caja
		JButton btnCorteCaja = new JButton("Corte de Caja");
		btnCorteCaja.addActionListener(actionEvent -> {
			controlInicio.muestraContenidoCorte();
		});
		btnCorteCaja.setBounds(15, 235, 178, 29);
		panelContenido.add(btnCorteCaja);
	}
	
	public void muestraVentana(ControlInicio controlInicio) {
		this.controlInicio = controlInicio;
		this.setVisible(true);
	}
}
