package prueba.java.personajes;

import prueba.java.utilidades.Constantes;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) del wumpus y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Wumpus extends Personaje {
	
	/**
	 * Constructor para inicializar la posición (fila y columna) que ocupa el wumpus
	 * 
	 * @param x Número que determina la posición de la fila del wumpus
	 * @param y Número que determina la posición de la columna del wumpus
	 */
	public Wumpus(int x, int y) {
		// Dirección por defecto del wumpus hacia arriba
		super(x, y, Constantes.direccion.ARRIBA.toString(), true);
	}
	
}