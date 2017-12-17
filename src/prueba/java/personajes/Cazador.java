package prueba.java.personajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import prueba.java.tablero.Casilla;
import prueba.java.utilidades.Constantes;
import prueba.java.utilidades.Constantes.direccion;
import prueba.java.utilidades.Posicion;

/**
 * Clase para almacenar/determinar las posiciones (fila y columna) del cazador y realizar las acciones
 * 
 * @author Josep Puertas
 */
public class Cazador extends Personaje {
	
	// Variable para almacenar/determinar el número de flechas de las que dispone el cazador
	private int numeroFlechas;
	
	// Variable para almacenar/determinar si el cazador tiene el oro
	private boolean tieneOro;
	
	// Variable para almacernar/determinar los valores que el usuario escribe por consola
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Constructor para inicializar la posición (fila y columna) que ocupa el cazador
	 * 
	 * @param x Número que determina la posición de la fila del cazador
	 * @param y Número que determina la posición de la columna del cazador
	 * @param numeroFlechas Número que determina la cantidad de flechas de las que dispone el cazador
	 */
	public Cazador(int x, int y, int numeroFlechas) {
		// Dirección por defecto del cazador hacia abajo
		super(x, y, Constantes.direccion.ABAJO.toString(), true);
		
		this.numeroFlechas = numeroFlechas;
	}
	
	/**
	 * Método para determinar que acción va a realizar el cazador
	 * 
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param numeroFilas Número que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas Número que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @throws IOException Excepción debida a que el valor introducido no es numérico
	 */
	public void accionCazador(Wumpus wumpus, int numeroFilas, int numeroColumnas) throws IOException {
		String direccionActual = super.getDireccion();
		
		System.out.println("*** Dirección del cazador: " + direccionActual.toLowerCase() + " ***");
		
		System.out.println("1) Avanzar");
		System.out.println("2) Girar 90 grados a la derecha");
		System.out.println("3) Girar 90 grados a la izquierda");
		System.out.println("4) Disparar");
		
		System.out.print("Acción a realizar: ");
		int accionRealizar = Integer.parseInt(br.readLine());
		
		// Repetir si no se introduce un valor numérico válido
		while (accionRealizar != 0 || accionRealizar != 1) {
			if (accionRealizar == 1 || accionRealizar == 2 || accionRealizar == 3 || accionRealizar == 4) {
				break;
			}
			
			System.out.println("Error: Número no válido");
			
			System.out.print("Acción a realizar: ");
			accionRealizar = Integer.parseInt(br.readLine());
		}
		
		String direccionNueva = null;
		
		if (accionRealizar == 1 || accionRealizar == 2 || accionRealizar == 3) {
			direccionNueva = direccionActual;
			
			if (accionRealizar == 2) {
				direccionNueva = definirNuevaDirecccion(direccionActual, accionRealizar);
			} if (accionRealizar == 3) {
				direccionNueva = definirNuevaDirecccion(direccionActual, accionRealizar);
			}
			
			// Mover al cazador de casilla u orientarlo en la dirección indicada
			super.acciones(Constantes.acciones.MOVER.toString(), direccionActual, direccionNueva, numeroFilas, numeroColumnas);
		} else {
			// Disparar una flecha en la dirección indicada
			disparar(wumpus);
		}
	}
	
	/**
	 * Método para obtener la nueva dirección del personaje
	 * 
	 * @param direccionActual Cadena que determina la dirección (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param accionRealizar Cadena que determina la dirección (2 - derecha o 3 - izquierda) en la que se moverá el personaje noventa grados
	 * @return Devuelve una cadeba con la nueva dirección del personaje
	 */
	private String definirNuevaDirecccion(String direccionActual, int accionRealizar) {
		String direccionNueva = null;
		
		for (direccion valorDireccion : Constantes.direccion.values()) {
			if (direccionActual.equals(valorDireccion.toString())) {
				/*
				 * 0: Arriba
				 * 1: Derecha
				 * 2: Abajo
				 * 3: Izquierda 
				 */
				int numeroDireccion = valorDireccion.ordinal();
				
				// Obtener la nueva dirección a partir de la actual y de la dirección del giro
				if ((numeroDireccion == 0 && accionRealizar == 2) || (numeroDireccion == 2 && accionRealizar == 3)) {
					direccionNueva = Constantes.direccion.DERECHA.toString();
				} else if ((numeroDireccion == 1 && accionRealizar == 2) || (numeroDireccion == 3 && accionRealizar == 3)) {
					direccionNueva = Constantes.direccion.ABAJO.toString();
				} else if ((numeroDireccion == 2 && accionRealizar == 2) || (numeroDireccion == 0 && accionRealizar == 3)) {
					direccionNueva = Constantes.direccion.IZQUIERDA.toString();
				} else if ((numeroDireccion == 3 && accionRealizar == 2) || (numeroDireccion == 1 && accionRealizar == 3)) {
					direccionNueva = Constantes.direccion.ARRIBA.toString();
				}
				
				break;
			}
		}
		
		return direccionNueva;
	}
	
	/**
	 * Método para que el cazador dispare una flecha en la dirección (arriba, derecha, abajo o izquierda) en la que se encuentra
	 * 
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 */
	public void disparar(Wumpus wumpus) {
		if (numeroFlechas > 0) {
			int numeroFilaCazador = super.getX();
			int numeroColCazador = super.getY();
			
			int numeroFilaWumpus = wumpus.getX();
			int numeroColWumpus = wumpus.getY();
			
			// Comprobar la dirección del disparo y si el wumpus se encuenra en ella
			switch (Constantes.direccion.valueOf(super.getDireccion())) {
				case ARRIBA:
					if (numeroColCazador == numeroColWumpus && numeroFilaCazador > numeroFilaWumpus) {
						wumpus.setEstaVivo(false);
						
						System.out.println("*** El cazador ha matado al wumpus ***");
					} else {
						System.out.println("*** La flecha ha acabado contra un muro ***");
					}
					
					break;
				case DERECHA:
					if (numeroFilaCazador == numeroFilaWumpus && numeroColCazador < numeroColWumpus) {
						wumpus.setEstaVivo(false);
						
						System.out.println("*** El cazador ha matado al wumpus ***");
					} else {
						System.out.println("*** La flecha ha acabado contra un muro ***");
					}
					
					break;
				case ABAJO:
					if (numeroColCazador == numeroColWumpus && numeroFilaCazador < numeroFilaWumpus) {
						wumpus.setEstaVivo(false);
						
						System.out.println("*** El cazador ha matado al wumpus ***");
					} else {
						System.out.println("*** La flecha ha acabado contra un muro ***");
					}
					
					break;
				case IZQUIERDA:
					if (numeroFilaCazador == numeroFilaWumpus && numeroColCazador > numeroColWumpus) {
						wumpus.setEstaVivo(false);
						
						System.out.println("*** El cazador ha matado al wumpus ***");
					} else {
						System.out.println("*** La flecha ha acabado contra un muro ***");
					}
					
					break;
				default:
					
					break;
			}
			
			numeroFlechas = numeroFlechas - 1;
			
			System.out.println("*** Número de flechas actuales: " + numeroFlechas + " ***");
		} else {
			System.out.println("*** El cazador no dispone de más flechas ***");
		}
	}
	
	/**
	 * Método para indicar las percepciones de la casilla el cazador
	 * 
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param listaCasillas Determina la lista de casillas de las que dispone el tablero
	 */
	public void percepcionesCazador(Wumpus wumpus, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = super.getX();
		int numeroCol = super.getY();
		
		Casilla casilla = null;
		
		// Obtener la casilla
		for (Posicion posicionLista : listaCasillas.keySet()) {
			if (numeroFila == posicionLista.getX() && numeroCol == posicionLista.getY()) {
				casilla = listaCasillas.get(posicionLista);
				
				break;
			}
		}
		
		// Comprobar los atribibutos (actualizarlos si es necesario) y mostrar un mensaje
		if (casilla.isTienePozo()) {
			super.setEstaVivo(false);
			
			System.out.println("*** El cazador ha caido a un pozo y ha muerto ***");
		} else if (casilla.isEstaWumpus() && wumpus.isEstaVivo()) {
			super.setEstaVivo(false);
			
			System.out.println("*** El wumpus ha encontrado al cazador y el jugador ha muerto ***");
		} else {
			if (casilla.isTieneMuro()) {
				System.out.println("*** Se percibe que hay algún muro ***");
			}
			
			if (casilla.isTieneBrisa()) {
				System.out.println("*** Se percibe una brisa, por lo tanto habrá un pozo cerca ***");
			}
			
			if (casilla.isTieneHedor() && wumpus.isEstaVivo()) {
				System.out.println("*** Se percibe un hedor, por lo tanto estará el wumpus cerca ***");
			}
			
			if (casilla.isTieneOro()) {
				tieneOro = true;
				casilla.setTieneOro(false);
				
				System.out.println("*** Se ha encontrado el oro. Vuelve a la casilla de salida para ganar ***");
			}
		}
	}
	
	/* GETTERS y SETTERS */
	
	/**
	 * Obtener el número de flechas de las que dispone el cazador
	 * 
	 * @return Devuelve el número de flechas de las que dispone el cazador
	 */
	public int getNumeroFlechas() {
		return numeroFlechas;
	}
	
	/**
	 * Asignar el número de flechas de las que dispone el cazador
	 * 
	 * @param numeroFlechas Número que determina la cantidad de flechas de las que dispone el cazador
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