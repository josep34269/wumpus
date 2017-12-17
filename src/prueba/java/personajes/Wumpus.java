package prueba.java.personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import prueba.java.tablero.Casilla;
import prueba.java.utilidades.Constantes;
import prueba.java.utilidades.Posicion;

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
	
	/**
	 * M�todo para determinar que acci�n va a realizar el wumpus
	 * 
	 * @param cazador Objeto que determina los atributos del cazador dentro del tablero
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	public void accionWumpus(Cazador cazador, int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = super.getX();
		int numeroCol = super.getY();
		
		// Obtener los n�meros de las casillas adyacentes
		Posicion posCasillaArriba = numeroFila > 0 ? new Posicion((numeroFila - 1), numeroCol) : null;
		Posicion posCasillaIzquierda = numeroCol > 0 ? new Posicion(numeroFila, (numeroCol - 1)) : null;
		Posicion posCasillaDerecha = numeroCol < (numeroColumnas - 1) ? new Posicion(numeroFila, (numeroCol + 1)) : null;
		Posicion posCasillaAbajo = numeroFila < (numeroFilas - 1) ? new Posicion((numeroFila + 1), numeroCol) : null;
		
		List<Posicion> listaPosiciones = new ArrayList<Posicion>();
		listaPosiciones.add(posCasillaArriba);
		listaPosiciones.add(posCasillaIzquierda);
		listaPosiciones.add(posCasillaDerecha);
		listaPosiciones.add(posCasillaAbajo);
		
		// Recorrer las posiciones adyacentes a la casilla actual
		for (Posicion posicion : listaPosiciones) {
			if (posicion != null) {
				int filaAdyacen = posicion.getX();
				int colAdyacen = posicion.getY();
				
				int filaCazador = cazador.getX();
				int colCazador = cazador.getY();
				
				// Contador total de casillas del cazador
				int numeroCasillaCazador = filaCazador + colCazador + (filaCazador * (numeroColumnas - 1));
				
				int filaWumpus = super.getX();
				int colWumpus = super.getY();
				
				// Contador total de casillas del wumpus
				int numeroCasillaWumpus = filaWumpus + colWumpus + (filaWumpus * (numeroColumnas - 1));
				
				Casilla casilla = null;
				Posicion numeroPosicion = null;
				
				// Obtener la posici�n y la casilla actual del wumpues
				for (Posicion posicionLista : listaCasillas.keySet()) {
					if (filaWumpus == posicionLista.getX() && colWumpus == posicionLista.getY()) {
						numeroPosicion = posicionLista;
						casilla = listaCasillas.get(numeroPosicion);
						
						break;
					}
				}
				
				String direccion = super.getDireccion();
				boolean editado = false;
				
				// Mover al wumpus de casilla u orientarlo en la direcci�n m�s �ptima
				if (numeroCasillaCazador < numeroCasillaWumpus && !tienePozo(listaCasillas, filaAdyacen, colAdyacen)) {
					if (filaCazador < filaWumpus) {
						String direccionArriba = Constantes.direccion.ARRIBA.toString();
						
						if (direccion.equals(direccionArriba)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							super.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionArriba, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					} else if (colCazador < colWumpus) {
						String direccionIzquierda = Constantes.direccion.IZQUIERDA.toString();
						
						if (direccion.equals(direccionIzquierda)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							super.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionIzquierda, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					}
				} else if (numeroCasillaCazador > numeroCasillaWumpus && !tienePozo(listaCasillas, filaAdyacen, colAdyacen)) {
					if (colCazador > colWumpus) {
						String direccionDerecha = Constantes.direccion.DERECHA.toString();
						
						if (direccion.equals(direccionDerecha)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							super.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionDerecha, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					} else if (filaCazador > filaWumpus) {
						String direccionAbajo = Constantes.direccion.ABAJO.toString();
						
						if (direccion.equals(direccionAbajo)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							super.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionAbajo, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					}
				}
				
				listaCasillas.put(numeroPosicion, casilla);
				
				// Salir del bucle una vez se ha indicado el movimiento al wumpus
				if (editado) {
					break;
				}
			}
		}
	}
	
	/**
	 * M�todo para comprobar si la casilla tiene pozo
	 * 
	 * @param listaCasillasLista que determina las casillas de las que dispone el tablero
	 * @param filaAdyacen N�mero que determina fila que se debe comprobar si tiene pozo
	 * @param colAdyacen N�mero que determina columna que se debe comprobar si tiene pozo
	 * @return Devuelve un valor (verdadero o falso) que determina si la casilla tiene pozo
	 */
	private boolean tienePozo(Map<Posicion, Casilla> listaCasillas, int filaAdyacen, int colAdyacen) {
		Casilla casillaAdyacente = null;
		
		// Obtener la posici�n y la casilla si ya existen
		for (Posicion posicionLista : listaCasillas.keySet()) {
			if (filaAdyacen == posicionLista.getX() && colAdyacen == posicionLista.getY()) {
				casillaAdyacente = listaCasillas.get(posicionLista);
				
				break;
			}
		}
		
		return casillaAdyacente.isTienePozo();
	}
	
	/**
	 * M�todo para determinar cuales son los movimientos que debe realizar el wumpus
	 * 
	 * @param direccionActual Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) nueva de la acci�n
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	private void movimientosWumpus(String direccionActual, String direccionNueva, int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		// Limpiamos las casillas que tienen el atributo hedor
		for (Posicion posicionLista : listaCasillas.keySet()) {
			Casilla casilla = listaCasillas.get(posicionLista);
			
			if (casilla.isTieneHedor()) {
				casilla.setTieneHedor(false);
			}
		}
		
		super.acciones(Constantes.acciones.MOVER.toString(), direccionActual, direccionNueva, numeroFilas, numeroColumnas);
		
		Casilla casilla = null;
		Posicion posicion = null;
		
		// Aignar la posici�n y la casilla nueva del wumpus
		for (Posicion posicionLista : listaCasillas.keySet()) {
			if (super.getX() == posicionLista.getX() && super.getY() == posicionLista.getY()) {
				posicion = posicionLista;
				
				casilla = listaCasillas.get(posicion);
				casilla.setEstaWumpus(true);
				
				break;
			}
		}
		
		listaCasillas.put(posicion, casilla);
		
		// Asignamos el atributo hedor a las casillas adyacentes
		asignarHedor(numeroFilas, numeroColumnas, listaCasillas);
	}
	
	/**
	 * M�todo para asignar la hedor a las casillas adyacentes
	 * 
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	private void asignarHedor(int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = super.getX();
		int numeroCol = super.getY();
		
		// Obtener los n�meros de las casillas adyacentes
		Posicion posCasillaArriba = numeroFila > 0 ? new Posicion((numeroFila - 1), numeroCol) : null;
		Posicion posCasillaIzquierda = numeroCol > 0 ? new Posicion(numeroFila, (numeroCol - 1)) : null;
		Posicion posCasillaDerecha = numeroCol < (numeroColumnas - 1) ? new Posicion(numeroFila, (numeroCol + 1)) : null;
		Posicion posCasillaAbajo = numeroFila < (numeroFilas - 1) ? new Posicion((numeroFila + 1), numeroCol) : null;
		
		List<Posicion> listaPosiciones = new ArrayList<Posicion>();
		listaPosiciones.add(posCasillaArriba);
		listaPosiciones.add(posCasillaIzquierda);
		listaPosiciones.add(posCasillaDerecha);
		listaPosiciones.add(posCasillaAbajo);
		
		// Recorrer las posiciones adyacentes a la casilla actual
		for (Posicion posicion : listaPosiciones) {
			if (posicion != null) {
				int fila = posicion.getX();
				int columna = posicion.getY();
				
				// Casilla actual del bucle
				Casilla casilla = new Casilla(fila, columna);
				
				// Obtener la posici�n y la casilla si ya existen
				for (Posicion posicionLista : listaCasillas.keySet()) {
					if (fila == posicionLista.getX() && columna == posicionLista.getY()) {
						posicion = posicionLista;
						casilla = listaCasillas.get(posicion);
						
						break;
					}
				}
				
				if (casilla != null && !casilla.isTienePozo() && !casilla.isTieneHedor()) {
					// Si la casilla tiene pozo o ya tiene el hedor no se le asigna
					casilla.setTieneHedor(true);
				}
				
				listaCasillas.put(posicion, casilla);
			}
		}
	}
	
}