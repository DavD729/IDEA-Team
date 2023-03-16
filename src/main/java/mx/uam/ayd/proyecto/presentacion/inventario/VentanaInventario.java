package mx.uam.ayd.proyecto.presentacion.inventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings("serial")
public class VentanaInventario extends JFrame {
	private ControlInventario controlI;
	private JPanel panelContenido;
	
	public VentanaInventario() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(350, 130, 600, 500);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
		
		JLabel etiqueta = new JLabel("Inventario");
		etiqueta.setBounds(15, 5, 440, 16);
		panelContenido.add(etiqueta);
		
		JLabel etiquetaWIP = new JLabel("Contenido en Progreso (HU-07)");
		etiquetaWIP.setBounds(200, 200, 440, 16);
		panelContenido.add(etiquetaWIP);
		
		JButton btnRegistra = new JButton("Registrar Producto");
		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Registra");
				controlI.agregaProducto();
			}
		});
		btnRegistra.setBounds(15, 420, 150, 29);
		panelContenido.add(btnRegistra);
		
		JButton btnHistorial= new JButton("Historial de ventas");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Historial");
				log.info("WIP HU-09");
			}
		});
		btnHistorial.setBounds(180, 420, 150, 29);
		panelContenido.add(btnHistorial);
		
		JButton btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Action Regresa");
				controlI.finalizaControlInventario();
			}
		});
		btnRegresa.setBounds(450, 420, 120, 29);
		panelContenido.add(btnRegresa);
	}
	
	public void muestraContenido(ControlInventario control) {
		this.setVisible(true);
		this.controlI = control;
	}
}
