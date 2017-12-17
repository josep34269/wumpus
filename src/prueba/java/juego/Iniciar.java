package prueba.java.juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import prueba.java.personajes.Cazador;
import prueba.java.personajes.Wumpus;
import prueba.java.tablero.Casilla;
import prueba.java.tablero.Tablero;
import prueba.java.utilidades.Posicion;

/**
 * Clase para iniciar el juego indicando el tamaño del tablero y el número pozos y de flechas del cazador
 * 
 * @author Josep Puertas
 */
public class Iniciar {
	
	// Variable para almacernar/determinar los valores que el usuario escribe por consola
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Método para iniciar el juego indicando el tamaño del tablero y el número pozos y de flechas del cazador
	 * 
	 * @param args Colección de argumentos que se le pueden pasar al método
	 */
	public static void main(String[] args) {
		try {
			// Menú de opciones
			System.out.println("0) Salir");
			System.out.println("1) Jugar");
			
			System.out.print("Opción seleccionada: ");
			int numeroOpcion = Integer.parseInt(br.readLine());
			
			// Repetir si no se introduce un valor numérico válido
			while (numeroOpcion != 0 || numeroOpcion != 1) {
				if (numeroOpcion == 0) {
					pararJuego();
				} else if (numeroOpcion == 1) {
					break;
				}
				
				System.out.println("Error: Número no válido");
				
				System.out.print("Opción seleccionada: ");
				numeroOpcion = Integer.parseInt(br.readLine());
			}
			
			// Datos a introducir por el usuario
			System.out.print("Número de filas: ");
			int numeroFilas = Integer.parseInt(br.readLine());
			
			System.out.print("Número de columnas: ");
			int numeroColumnas = Integer.parseInt(br.readLine());
			
			System.out.print("Número de pozos: ");
			int numeroPozos = Integer.parseInt(br.readLine());
			
			/*
			 * Crear el tablero a partir de las filas y columnas indicadas
			 * Se añaden también los pozos indicados y dos elementos adicionales (el oro y el wumpus)
			 */
			Tablero tablero = new Tablero(numeroFilas, numeroColumnas, numeroPozos, 2);
			Map<Posicion, Casilla> listaCasillas = tablero.getListaCasillas();
			
			System.out.print("Número de flechas: ");
			int numeroFlechas = Integer.parseInt(br.readLine());
			
			// Crear el cazador con el número de flechas indicadas
			Cazador cazador = new Cazador(0, 0, numeroFlechas);
			
			// Crear el wumpus a partir de la posición aleatoria asignada en el tablero
			Wumpus wumpus = obtenerWumpus(listaCasillas);
			
			// Determinar las percepciones del cazador
			cazador.percepcionesCazador(wumpus, listaCasillas);
			
			do {
				// Determinar que acción va a realizar el cazador
				cazador.accionCazador(wumpus, numeroFilas, numeroColumnas);
				
				// Determinar que acción va a realizar el wumpus si está vivo
				if (wumpus.isEstaVivo()) {
					wumpus.accionWumpus(cazador, numeroFilas, numeroColumnas, listaCasillas);
				}
				
				// Determinar las percepciones del cazador
				cazador.percepcionesCazador(wumpus, listaCasillas);
			} while ((cazador.getX() == 0 && cazador.getY() == 0 && cazador.isEstaVivo() && !cazador.isTieneOro()) ||
					((cazador.getX() != 0 || cazador.getY() != 0) && cazador.isEstaVivo() && !cazador.isTieneOro()) ||
					((cazador.getX() != 0 || cazador.getY() != 0) && cazador.isEstaVivo() && cazador.isTieneOro()));
			
			if (cazador.isEstaVivo() && cazador.isTieneOro()) {
				System.out.println("*** El cazador ha vuelto vivo a la casilla de salida con el oro ***");
			}
		} catch (IOException excepcion) {
			System.out.println("Error: El valor introducido debe ser numérico");
		} catch (NumberFormatException excepcion) {
			System.out.println("Error: El valor introducido debe ser numérico");
		} finally {
			pararJuego();
		}
	}
	
	/**
	 * Método parar obtener el wumpus a partir de la posición aleatoria asignada en el tablero
	 * 
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 * @return Devuelve el wumpus a partir de la posición aleatoria asignada en el tablero 
	 */
	private static Wumpus obtenerWumpus(Map<Posicion, Casilla> listaCasillas) {
		Wumpus wumpus = null;
		
		// Recorrer la lista de casillas hasta encontrar la que corresponde al wumpus
		for (Posicion clave : listaCasillas.keySet()) {
			Casilla casilla = listaCasillas.get(clave);
			
			if (casilla.isEstaWumpus()) {
				wumpus = new Wumpus(casilla.getX(), casilla.getY());
				
				break;
			}
		}
		
		return wumpus;
	}
	
	/**
	 * Método para parar la ejecución del juego
	 */
	private static void pararJuego() {
		System.out.println("El juego ha finalizado");
		
		// Parar la ejecución del juego
		System.exit(1);
	}
	
}