package prueba.java.personajes;

import prueba.java.utilidades.Constantes;
import prueba.java.utilidades.Posicion;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) de los personajes dentro del tablero y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Personaje extends Posicion {
	
	// Variable para almacenar/determinar la dirección (arriba, derecha, abajo o izquierda) hacia la que está oriendata un personaje
	private String direccion;
	
	// Variable para almacenar/determinar si el personaje está vivo
	private boolean estaVivo;
	
	/**
	 * Constructor para inicializar la posición (fila y columna) que ocupa el personaje
	 * 
	 * @param x Número que determina la posición de la fila del personaje
	 * @param y Número que determina la posición de la columna del personaje
	 * @param direccion Cadena que determina la dirección (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param estaVivo Valor (verdadero o falso) que determina si el personaje está vivo
	 */
	public Personaje(int x, int y, String direccion, boolean estaVivo) {
		super(x, y);
		
		this.direccion = direccion;
		this.estaVivo = estaVivo;
	}
	
	/**
	 * Método para recoger las posibles acciones (mover) a realizar por los personajes y llevarlas a cabo
	 * 
	 * @param accion Cadena que determina la acción (mover) a realizar
	 * @param direccionActual Cadena que determina la dirección (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la dirección (arriba, derecha, abajo o izquierda) nueva de la acción
	 * @param numeroFilas Número que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas Número que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 */
	public void acciones(String accion, String direccionActual, String direccionNueva, int numeroFilas, int numeroColumnas) {
		switch (Constantes.acciones.valueOf(accion)) {
			case MOVER: mover(direccionActual, direccionNueva, numeroFilas, numeroColumnas);
			
				break;
			default:
				
				break;
		}
	}
	
	/**
	 * Método para que el personaje se mueva de casilla o cambie la dirección (arriba, derecha, abajo o izquierda) de orientación dentro de la casilla
	 * 
	 * @param direccionActual Cadena que determina la dirección (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la dirección (arriba, derecha, abajo o izquierda) nueva de la acción
	 * @param numeroFilas Número que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas Número que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 */
	private void mover(String direccionActual, String direccionNueva, int numeroFilas, int numeroColumnas) {
		if (direccionActual != direccionNueva) {
			direccion = direccionNueva;
		} else {
			int numeroFila = super.getX();
			int numeroCol = super.getY();
			
			// Realizar el movimiento o avisar al cazador de que no puede porque hay un muro
			switch (Constantes.direccion.valueOf(direccionNueva)) {
				case ARRIBA:
					if (numeroFila > 0 && numeroFila <= (numeroFilas - 1)) {
						super.setX(super.getX() - 1);
					} else {
						System.out.println("*** El cazador se ha chocado con un muro ***");
					}
					
					break;
				case DERECHA:
					if (numeroCol >= 0 && numeroCol < (numeroColumnas - 1)) {
						super.setY(super.getY() + 1);
					} else {
						System.out.println("*** El cazador se ha chocado con un muro ***");
					}
					
					break;
				case ABAJO:
					if (numeroFila >= 0 && numeroFila < (numeroFilas - 1)) {
						super.setX(super.getX() + 1);
					} else {
						System.out.println("*** El cazador se ha chocado con un muro ***");
					}
					
					break;
				case IZQUIERDA:
					if (numeroCol > 0 && numeroCol <= (numeroColumnas - 1)) {
						super.setY(super.getY() - 1);
					} else {
						System.out.println("*** El cazador se ha chocado con un muro ***");
					}
					
					break;
				default:
					
					break;
			}
		}
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener la dirección (arriba, derecha, abajo o izquierda) hacia la que está oriendata un personaje
	 * 
	 * @param dirrecion Devuelve la dirección (arriba, derecha, abajo o izquierda) hacia la que está oriendata un personaje
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Asignar la dirección (arriba, derecha, abajo o izquierda) hacia la que está oriendata un personaje
	 * 
	 * @param dirrecion Cadena que determina la dirección (arriba, derecha, abajo o izquierda) hacia la que está oriendata un personaje
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtener si el personaje está vivo
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si el personaje está vivo
	 */
	public boolean isEstaVivo() {
		return estaVivo;
	}
	
	/**
	 * Asignar si el personaje está vivo
	 * 
	 * @param estaVivo Valor (verdadero o falso) que determina si el personaje está vivo
	 */
	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}
	
}