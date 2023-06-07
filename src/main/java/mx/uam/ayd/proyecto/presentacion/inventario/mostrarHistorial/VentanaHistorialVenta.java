package mx.uam.ayd.proyecto.presentacion.inventario.mostrarHistorial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
public class VentanaHistorialVenta extends JFrame {
	private ControlHistorialVenta controlHistorialVenta;
	private JPanel panelContenido;
	
	public VentanaHistorialVenta() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(380, 160, 750, 550);
		panelContenido = new JPanel();
		panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		panelContenido.setLayout(null);
	}
	
	public void muestraVentana(ControlHistorialVenta controlHVenta) {
		this.controlHistorialVenta = controlHVenta;
	}
}
