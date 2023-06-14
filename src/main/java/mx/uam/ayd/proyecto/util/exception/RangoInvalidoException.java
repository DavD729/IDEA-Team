package mx.uam.ayd.proyecto.util.exception;

public class RangoInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int valor;

    public RangoInvalidoException(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    @Override
    public String getMessage() {
        return "El valor " + valor + " está fuera del rango permitido [0-100]";
    }
}