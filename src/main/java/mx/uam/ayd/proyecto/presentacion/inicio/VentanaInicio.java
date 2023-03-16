package mx.uam.ayd.proyecto.presentacion.inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private ControlInicio controlI;
	
	public VentanaInicio() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(130, 130, 225, 285);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel etiqueta = new JLabel("Administración de Tienda");
		etiqueta.setBounds(30, 5, 440, 16);
		panelContenido.add(etiqueta);
		
		// Sección inventario
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Inventario");
				controlI.muestraContenidoInventario();
			}
		});
		
		btnInventario.setBounds(15, 40, 178, 29);
		panelContenido.add(btnInventario);
		
		// Sección abastecimiento
		JButton btnAbastecimiento = new JButton("Abastecimiento");
		btnAbastecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Abastecimiento");
			}
		});
		btnAbastecimiento.setBounds(15, 80, 178, 29);
		panelContenido.add(btnAbastecimiento);
		
		// Sección ventas
		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Ventas");
			}
		});
		btnVentas.setBounds(15, 120, 178, 29);
		panelContenido.add(btnVentas);
		
		// Sección corte de caja
		JButton btnCorteCaja = new JButton("Corte de Caja");
		btnCorteCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Corte de Caja");
			}
		});
		btnCorteCaja.setBounds(15, 160, 178, 29);
		panelContenido.add(btnCorteCaja);
		
		// Sección corte de caja
		JButton btnAdministracion = new JButton("Administración");
		btnAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Administración");
			}
		});
		btnAdministracion.setBounds(15, 200, 178, 29);
		panelContenido.add(btnAdministracion);
	}
	
	public void muestraVentana(ControlInicio control) {
		this.controlI = control;
		this.setVisible(true);
	}
}
