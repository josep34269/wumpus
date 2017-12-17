package prueba.java.personajes;

import prueba.java.utilidades.Constantes;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) del wumpus y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Wumpus extends Personaje {
	
	/**
	 * Constructor para inicializar la posici�n (fila y columna) que ocupa el wumpus
	 * 
	 * @param x N�mero que determina la posici�n de la fila del wumpus
	 * @param y N�mero que determina la posici�n de la columna del wumpus
	 */
	public Wumpus(int x, int y) {
		// Direcci�n por defecto del wumpus hacia arriba
		super(x, y, Constantes.direccion.ARRIBA.toString(), true);
	}
	
}