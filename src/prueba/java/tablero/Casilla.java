package prueba.java.tablero;

import prueba.java.utilidades.Posicion;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) y atributos (pozo, brisa, hedor, muro y/o oro) que pueden tener cada casilla
 * 
 * @author Josep Puertas
 */
public class Casilla extends Posicion {
	
	// Variable para almacenar/determinar si la casilla tiene un pozo
	private boolean tienePozo;
	
	// Variable para almacenar/determinar si la casilla tiene brisa
	private boolean tieneBrisa;
	
	// Variable para almacenar/determinar si la casilla tiene hedor
	private boolean tieneHedor;
	
	// Variable para almacenar/determinar si la casilla tiene muro
	private boolean tieneMuro;
	
	// Variable para almacenar/determinar si la casilla tiene el oro
	private boolean tieneOro;
	
	// Variable para almacenar/determinar si en la casilla está el wumpus
	private boolean estaWumpus;
	
	/**
	 * Constructor para inicilizar la posición (fila y columna) de la casilla
	 * 
	 * @param x Número que determina la posición de la fila dentro del tablero
	 * @param y Número que determina la posición de la columna dentro del tablero
	 */
	public Casilla(int x, int y) {
		super(x, y);
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener si la casilla tiene un pozo
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si la casilla tiene un pozo
	 */
	public boolean isTienePozo() {
		return tienePozo;
	}
	
	/**
	 * Asignar si la casilla tiene un pozo
	 * 
	 * @param tienePozo Valor (verdadero o falso) que determina si la casilla tiene un pozo
	 */
	public void setTienePozo(boolean tienePozo) {
		this.tienePozo = tienePozo;
	}
	
	/**
	 * Obtener si la casilla tiene brisa
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si la casilla tiene brisa
	 */
	public boolean isTieneBrisa() {
		return tieneBrisa;
	}
	
	/**
	 * Asignar si la casilla tiene brisa
	 * 
	 * @param tieneBrisa Valor (verdadero o falso) que determina si la casilla tiene brisa
	 */
	public void setTieneBrisa(boolean tieneBrisa) {
		this.tieneBrisa = tieneBrisa;
	}
	
	/**
	 * Obtener si la casilla tiene hedor
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si la casilla tiene hedor
	 */
	public boolean isTieneHedor() {
		return tieneHedor;
	}
	
	/**
	 * Asignar si la casilla tiene hedor
	 * 
	 * @param tieneHedor Valor (verdadero o falso) que determina si la casilla tiene hedor
	 */
	public void setTieneHedor(boolean tieneHedor) {
		this.tieneHedor = tieneHedor;
	}
	
	/**
	 * Obtener si la casilla tiene muro
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si la casilla tiene muro
	 */
	public boolean isTieneMuro() {
		return tieneMuro;
	}
	
	/**
	 * Asignar si la casilla tiene muro
	 * 
	 * @param tieneMuro Valor (verdadero o falso) que determina si la casilla tiene muro
	 */
	public void setTieneMuro(boolean tieneMuro) {
		this.tieneMuro = tieneMuro;
	}
	
	/**
	 * Obtener si la casilla tiene el oro
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si la casilla tiene el oro
	 */
	public boolean isTieneOro() {
		return tieneOro;
	}
	
	/**
	 * Asignar si la casilla tiene el oro
	 * 
	 * @param tieneOro Valor (verdadero o falso) que determina si la casilla tiene el oro
	 */
	public void setTieneOro(boolean tieneOro) {
		this.tieneOro = tieneOro;
	}
	
	/**
	 * Obtener si en la casilla está el wumpus
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si en la casilla está el wumpus
	 */
	public boolean isEstaWumpus() {
		return estaWumpus;
	}
	
	/**
	 * Asignar si en la casilla está el wumpus
	 * 
	 * @param estaWumpus Valor (verdadero o falso) que determina si en la casilla está el wumpus
	 */
	public void setEstaWumpus(boolean estaWumpus) {
		this.estaWumpus = estaWumpus;
	}
	
}