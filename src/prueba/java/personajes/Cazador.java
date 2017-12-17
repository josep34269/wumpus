package prueba.java.personajes;

import prueba.java.utilidades.Constantes;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) del cazador y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Cazador extends Personaje {
	
	// Variable para almacenar/determinar el n�mero de flechas de las que dispone el cazador
	private int numeroFlechas;
	
	// Variable para almacenar/determinar si el cazador tiene el oro
	private boolean tieneOro;
	
	/**
	 * Constructor para inicializar la posici�n (fila y columna) que ocupa el cazador
	 * 
	 * @param x N�mero que determina la posici�n de la fila del cazador
	 * @param y N�mero que determina la posici�n de la columna del cazador
	 * @param numeroFlechas N�mero que determina la cantidad de flechas de las que dispone el cazador
	 */
	public Cazador(int x, int y, int numeroFlechas) {
		// Direcci�n por defecto del cazador hacia abajo
		super(x, y, Constantes.direccion.ABAJO.toString(), true);
		
		this.numeroFlechas = numeroFlechas;
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener el n�mero de flechas de las que dispone el cazador
	 * 
	 * @return Devuelve el n�mero de flechas de las que dispone el cazador
	 */
	public int getNumeroFlechas() {
		return numeroFlechas;
	}
	
	/**
	 * Asignar el n�mero de flechas de las que dispone el cazador
	 * 
	 * @param numeroFlechas N�mero que determina la cantidad de flechas de las que dispone el cazador
	 */
	public void setNumeroFlechas(int numeroFlechas) {
		this.numeroFlechas = numeroFlechas;
	}
	
	/**
	 * Obtener si el cazador tiene el oro
	 * 
	 * @return Devuelve un valor (verdadero o falso) que indica si el cazador tiene el oro
	 */
	public boolean isTieneOro() {
		return tieneOro;
	}
	
	/**
	 * Asignar si el cazador tiene el oro
	 * 
	 * @param estaVivo Valor (verdadero o falso) que determina si el cazador tiene el oro
	 */
	public void setTieneOro(boolean tieneOro) {
		this.tieneOro = tieneOro;
	}
	
}