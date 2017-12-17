package prueba.java.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prueba.java.utilidades.Posicion;
import prueba.java.utilidades.Utilidades;

/**
 * Clase para determinar el tamaño del tablero con sus casillas correspondientes
 * 
 * @author Josep Puertas
 */
public class Tablero {
	
	// Variable para almacenar/determinar el número de filas (casillas) del tablero
	private int numeroFilas;
	
	// Variable para almacenar/determinar el número de columnas (casillas) del tablero
	private int numeroColumnas;
	
	// Variable para almacenar/determinar el número de pozos de los que dispone el tablero
	private int numeroPozos;
	
	// Variable para almacenar/determinar el número de elementos adicionales a asignar una casilla aleatoria
	private int numeroAdicionales;
	
	// Variable para almacenar/determinar la lista de casillas de las que dispone el tablero
	private Map<Posicion, Casilla> listaCasillas = new HashMap<Posicion, Casilla>();
	
	/**
	 * Enumerar los diferentes atributos (Brisa y/o Hedor) asignables a las celdas adaycentes a pozos y al wumpus
	 */
	private enum casillasAdyacen {BRISA, HEDOR};
	
	/**
	 * Constructor para inicializar el tablero con el número de filas (casillas) y columnas (casillas) indicadas
	 * 
	 * @param numeroFilas Número que determina la cantidad de filas (casillas) del tablero
	 * @param numeroColumnas Número que determina la cantidad de columnas (casillas) del tablero
	 * @param numeroPozos Número que determina la cantidad de pozos de los que dispone el tablero
	 * @param numeroAdicionales Número que determina la cantidad de elementos adicionales a asignar una casilla aleatoria
	 */
	public Tablero(int numeroFilas, int numeroColumnas, int numeroPozos, int numeroAdicionales) {
		super();
		
		this.numeroFilas = numeroFilas;
		this.numeroColumnas = numeroColumnas;
		this.numeroPozos = numeroPozos;
		this.numeroAdicionales = numeroAdicionales;
		
		generarTablero();
	}
	
	/**
	 * Método para generar todo el tablero con sus casillas correspondientes
	 */
	private void generarTablero() {
		Utilidades utilidades = new Utilidades();
		
		int totalCasillas = numeroFilas * numeroColumnas;
		
		/*
		 * El rango de números es desde el uno hasta la el total de casillas menos uno
		 * La razón para descontar un número es porque la casilla inicial es la cero, aunque es la de salida
		 */
		List<Integer> listaPozos = utilidades.numerosAleatorios((numeroPozos + numeroAdicionales), (totalCasillas - 1));
		
		// Crear las casillas correspondientes
		crearCasillas(listaPozos);
	}
	
	/**
	 * Método para crear las casillas correspondientes al tablero
	 * 
	 * @param listaPozos Lista que determina los números totales de casilla en el que están los pozos
	 */
	private void crearCasillas(List<Integer> listaPozos) {
		for (int numeroFila = 0; numeroFila < numeroFilas; numeroFila++) {
			for (int numeroCol = 0; numeroCol < numeroColumnas; numeroCol++) {
				// Contador total de casillas
				int numeroCasilla = numeroFila + numeroCol + (numeroFila * (numeroColumnas - 1));
				
				// Posición actual del bucle
				Posicion posicion = new Posicion(numeroFila, numeroCol);
				
				// Casilla actual del bucle
				Casilla casilla = new Casilla(numeroFila, numeroCol);
				
				// Obtener la posición y la casilla si ya existen
				for (Posicion posicionLista : listaCasillas.keySet()) {
					if (numeroFila == posicionLista.getX() && numeroCol == posicionLista.getY()) {
						posicion = posicionLista;
						casilla = listaCasillas.get(posicion);
						
						break;
					}
				}
				
				// Valor (verdadero o falso) que indica si la casilla tiene muro
				boolean tieneMuro = numeroFila == 0 || numeroCol == 0 || numeroFila == (numeroFilas - 1) || numeroCol == (numeroColumnas - 1);
				
				if (listaPozos.contains(numeroCasilla)) {
					if (listaPozos.size() > numeroAdicionales) {
						// Esta casilla contiene un pozo
						casilla.setTienePozo(true);
						
						// En la propia casilla del pozo no tiene brisa
						if (casilla.isTieneBrisa()) {
							casilla.setTieneBrisa(false);
						}
						
						// Asignar la brisa a las casillas adyacentes
						asignarCasillasAdyacen(casillasAdyacen.BRISA.toString(), numeroFila, numeroCol);
					} else {
						if (listaPozos.size() == numeroAdicionales) {
							// Esta casilla es la que contiene el oro
							casilla.setTieneOro(true);
						} else {
							// Esta casilla es la que contiene al wumpus ya que es el último elemento (adicional) de la lista
							casilla.setEstaWumpus(true);
							
							// Asignar el hedor a las casillas adyacentes
							asignarCasillasAdyacen(casillasAdyacen.HEDOR.toString(), numeroFila, numeroCol);
						}
						
						if (tieneMuro) {
							// Esta casilla tiene muro
							casilla.setTieneMuro(true);
						}
					}
					
					// Eliminar el elemento de la lista
					listaPozos.remove(listaPozos.indexOf(numeroCasilla));
				} else if (tieneMuro) {
					// Esta casilla tiene muro
					casilla.setTieneMuro(true);
				}
				
				listaCasillas.put(posicion, casilla);
			}
		}
	}
	
	/**
	 * Método para asignar la brisa o hedor a las casillas adyacentes
	 * 
	 * @param atributo Cadena que determina los diferentes atributos (Brisa y/o Hedor) asignables a las celdas adaycentes a pozos y al wumpus
	 * @param numeroFila Número que indica la fila de la casilla actual
	 * @param numeroCol Número que indica la columna de la casilla actual
	 */
	private void asignarCasillasAdyacen(String atributo, int numeroFila, int numeroCol) {
		// Obtener los números de las casillas adyacentes
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
				
				// Obtener la posición y la casilla si ya existen
				for (Posicion posicionLista : listaCasillas.keySet()) {
					if (fila == posicionLista.getX() && columna == posicionLista.getY()) {
						posicion = posicionLista;
						casilla = listaCasillas.get(posicion);
						
						break;
					}
				}
				
				if (casillasAdyacen.BRISA.toString().equals(atributo) && casilla != null && !casilla.isTienePozo() && !casilla.isTieneBrisa()) {
					// Si la casilla tiene pozo o ya tiene la brisa no se le asigna
					casilla.setTieneBrisa(true);
				} else if (casillasAdyacen.HEDOR.toString().equals(atributo) && casilla != null && !casilla.isTienePozo() && !casilla.isTieneHedor()) {
					// Si la casilla tiene pozo o ya tiene el hedor no se le asigna
					casilla.setTieneHedor(true);
				}
				
				listaCasillas.put(posicion, casilla);
			}
		}
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener el número de filas (casillas) del tablero
	 * 
	 * @return Devuelve el número de filas (casillas) del tablero
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}
	
	/**
	 * Asignar el número de filas (casillas) del tablero
	 * 
	 * @param numeroFilas Número que determina la cantidad de filas (casillas) del tablero
	 */
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
	
	/**
	 * Obtener el número de columnas (casillas) del tablero
	 * 
	 * @return Devuelve el número de columnas (casillas) del tablero
	 */
	public int getNumeroColumnas() {
		return numeroColumnas;
	}
	
	/**
	 * Asignar el número de columnas (casillas) del tablero
	 * 
	 * @param numeroColumnas Número que determina la cantidad de columnas (casillas) del tablero
	 */
	public void setNumeroColumnas(int numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}
	
	/**
	 * Obtener el número de pozos de los que dispone el tablero
	 * 
	 * @return Devuelve el número de pozos de los que dispone el tablero
	 */
	public int getNumeroPozos() {
		return numeroPozos;
	}
	
	/**
	 * Asignar el número de pozos de los que dispone el tablero
	 * 
	 * @param numeroPozos Número que determina la cantidad de pozos de los que dispone el tablero
	 */
	public void setNumeroPozos(int numeroPozos) {
		this.numeroPozos = numeroPozos;
	}
	
	/**
	 * Obtener el número de elementos adicionales a asignar una casilla aleatoria
	 * 
	 * @return Devuelve el número de elementos adicionales a asignar una casilla aleatoria
	 */
	public int getNumeroAdicionales() {
		return numeroAdicionales;
	}
	
	/**
	 * Asignar el número de elementos adicionales a asignar una casilla aleatoria
	 * 
	 * @param numeroAdicionales Número que determina la cantidad de elementos adicionales a asignar una casilla aleatoria
	 */
	public void setNumeroAdicionales(int numeroAdicionales) {
		this.numeroAdicionales = numeroAdicionales;
	}
	
	/**
	 * Obtener la lista de casillas de las que dispone el tablero
	 * 
	 * @return Devuelve la lista de casillas de las que dispone el tablero
	 */
	public Map<Posicion, Casilla> getListaCasillas() {
		return listaCasillas;
	}
	
	/**
	 * Asignar la lista de casillas de las que dispone el tablero
	 * 
	 * @param listaCasillas Mapa que determina la lista de casillas de las que dispone el tablero
	 */
	public void setListaCasillas(Map<Posicion, Casilla> listaCasillas) {
		this.listaCasillas = listaCasillas;
	}
	
}