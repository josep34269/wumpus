package prueba.java.personajes;

import prueba.java.utilidades.Constantes;
import prueba.java.utilidades.Posicion;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) de los personajes dentro del tablero y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Personaje extends Posicion {
	
	// Variable para almacenar/determinar la direcci�n (arriba, derecha, abajo o izquierda) hacia la que est� oriendata un personaje
	private String direccion;
	
	// Variable para almacenar/determinar si el personaje est� vivo
	private boolean estaVivo;
	
	/**
	 * Constructor para inicializar la posici�n (fila y columna) que ocupa el personaje
	 * 
	 * @param x N�mero que determina la posici�n de la fila del personaje
	 * @param y N�mero que determina la posici�n de la columna del personaje
	 * @param direccion Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param estaVivo Valor (verdadero o falso) que determina si el personaje est� vivo
	 */
	public Personaje(int x, int y, String direccion, boolean estaVivo) {
		super(x, y);
		
		this.direccion = direccion;
		this.estaVivo = estaVivo;
	}
	
	/**
	 * M�todo para recoger las posibles acciones (mover) a realizar por los personajes y llevarlas a cabo
	 * 
	 * @param accion Cadena que determina la acci�n (mover) a realizar
	 * @param direccionActual Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) nueva de la acci�n
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
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
	 * M�todo para que el personaje se mueva de casilla o cambie la direcci�n (arriba, derecha, abajo o izquierda) de orientaci�n dentro de la casilla
	 * 
	 * @param direccionActual Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) nueva de la acci�n
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
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
	 * Obtener la direcci�n (arriba, derecha, abajo o izquierda) hacia la que est� oriendata un personaje
	 * 
	 * @param dirrecion Devuelve la direcci�n (arriba, derecha, abajo o izquierda) hacia la que est� oriendata un personaje
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Asignar la direcci�n (arriba, derecha, abajo o izquierda) hacia la que est� oriendata un personaje
	 * 
	 * @param dirrecion Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) hacia la que est� oriendata un personaje
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtener si el personaje est� vivo
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si el personaje est� vivo
	 */
	public boolean isEstaVivo() {
		return estaVivo;
	}
	
	/**
	 * Asignar si el personaje est� vivo
	 * 
	 * @param estaVivo Valor (verdadero o falso) que determina si el personaje est� vivo
	 */
	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}
	
}