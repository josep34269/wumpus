package prueba.java.utilidades;

/**
 * Clase para almacenar/determinar la posición (fila y columna) de las casillas y personajes dentro del tablero
 * 
 * @author Josep Puertas
 */
public class Posicion {
	
	// Variable para almacenar/determinar la posición en el eje X (fila)
	private int x;
	
	// Variable para almacenar/determinar la posición en el eje Y (columna)
	private int y;
	
	/**
	 * Constructor para inicilizar la clase indicando la posición del eje X (fila) e Y (columna)
	 * 
	 * @param x Número que determina la posición en el eje X (fila)
	 * @param y Número que determina la posición en el eje Y (columna)
	 */
	public Posicion(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener la posición en el eje X (fila)
	 * 
	 * @return Devuelve la posición en el eje X (fila)
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Asingar la posición en el eje X (fila)
	 * 
	 * @param x Número que determina la posición del eje X (fila)
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Obtener la posición en el eje Y (columna)
	 * 
	 * @return Devuelve la posición en el eje Y (columna)
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Asingar la posición en el eje Y (columna)
	 * 
	 * @param y Número que determina la posición del eje Y (columna)
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}