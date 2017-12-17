package prueba.java.utilidades;

/**
 * Clase para almacenar/determinar la posici�n (fila y columna) de las casillas y personajes dentro del tablero
 * 
 * @author Josep Puertas
 */
public class Posicion {
	
	// Variable para almacenar/determinar la posici�n en el eje X (fila)
	private int x;
	
	// Variable para almacenar/determinar la posici�n en el eje Y (columna)
	private int y;
	
	/**
	 * Constructor para inicilizar la clase indicando la posici�n del eje X (fila) e Y (columna)
	 * 
	 * @param x N�mero que determina la posici�n en el eje X (fila)
	 * @param y N�mero que determina la posici�n en el eje Y (columna)
	 */
	public Posicion(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener la posici�n en el eje X (fila)
	 * 
	 * @return Devuelve la posici�n en el eje X (fila)
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Asingar la posici�n en el eje X (fila)
	 * 
	 * @param x N�mero que determina la posici�n del eje X (fila)
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Obtener la posici�n en el eje Y (columna)
	 * 
	 * @return Devuelve la posici�n en el eje Y (columna)
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Asingar la posici�n en el eje Y (columna)
	 * 
	 * @param y N�mero que determina la posici�n del eje Y (columna)
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}