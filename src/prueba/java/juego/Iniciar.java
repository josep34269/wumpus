package prueba.java.juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import prueba.java.personajes.Cazador;
import prueba.java.personajes.Wumpus;
import prueba.java.tablero.Casilla;
import prueba.java.tablero.Tablero;
import prueba.java.utilidades.Constantes;
import prueba.java.utilidades.Constantes.direccion;
import prueba.java.utilidades.Posicion;

/**
 * Clase para iniciar el juego indicando el tama�o del tablero y el n�mero pozos y de flechas del cazador
 * 
 * @author Josep Puertas
 */
public class Iniciar {
	
	// Variable para almacernar/determinar los valores que el usuario escribe por consola
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * M�todo para iniciar el juego indicando el tama�o del tablero y el n�mero pozos y de flechas del cazador
	 * 
	 * @param args Colecci�n de argumentos que se le pueden pasar al m�todo
	 */
	public static void main(String[] args) {
		try {
			// Men� de opciones
			System.out.println("0) Salir");
			System.out.println("1) Jugar");
			
			System.out.print("Opci�n seleccionada: ");
			int numeroOpcion = Integer.parseInt(br.readLine());
			
			// Repetir si no se introduce un valor num�rico v�lido
			while (numeroOpcion != 0 || numeroOpcion != 1) {
				if (numeroOpcion == 0) {
					paraJuego();
				} else if (numeroOpcion == 1) {
					break;
				}
				
				System.out.println("Error: N�mero no v�lido");
				
				System.out.print("Opci�n seleccionada: ");
				numeroOpcion = Integer.parseInt(br.readLine());
			}
			
			// Datos a introducir por el usuario
			System.out.print("N�mero de filas: ");
			int numeroFilas = Integer.parseInt(br.readLine());
			
			System.out.print("N�mero de columnas: ");
			int numeroColumnas = Integer.parseInt(br.readLine());
			
			System.out.print("N�mero de pozos: ");
			int numeroPozos = Integer.parseInt(br.readLine());
			
			/*
			 * Crear el tablero a partir de las filas y columnas indicadas
			 * Se a�aden tambi�n los pozos indicados y dos elementos adicionales (el oro y el wumpus)
			 */
			Tablero tablero = new Tablero(numeroFilas, numeroColumnas, numeroPozos, 2);
			Map<Posicion, Casilla> listaCasillas = tablero.getListaCasillas();
			
			System.out.print("N�mero de flechas: ");
			int numeroFlechas = Integer.parseInt(br.readLine());
			
			// Crear el cazador con el n�mero de flechas indicadas
			Cazador cazador = new Cazador(0, 0, numeroFlechas);
			
			// Crear el wumpus a partir de la posici�n aleatoria asignada en el tablero
			Wumpus wumpus = obtenerWumpus(listaCasillas);
			
			// Determinar las percepciones del cazador
			percepcionesCazador(cazador, wumpus, listaCasillas);
			
			do {
				// Determinar que acci�n va a realizar el cazador
				accionCazador(cazador, wumpus, numeroFilas, numeroColumnas);
				
				// Determinar que acci�n va a realizar el wumpus si est� vivo
				if (wumpus.isEstaVivo()) {
					accionWumpus(wumpus, cazador, numeroFilas, numeroColumnas, listaCasillas);
				}
				
				// Determinar las percepciones del cazador
				percepcionesCazador(cazador, wumpus, listaCasillas);
			} while ((cazador.getX() == 0 && cazador.getY() == 0 && cazador.isEstaVivo() && !cazador.isTieneOro()) ||
					((cazador.getX() != 0 || cazador.getY() != 0) && cazador.isEstaVivo() && !cazador.isTieneOro()) ||
					((cazador.getX() != 0 || cazador.getY() != 0) && cazador.isEstaVivo() && cazador.isTieneOro()));
			
			if (cazador.isEstaVivo() && cazador.isTieneOro()) {
				System.out.println("*** El cazador ha vuelto vivo a la casilla de salida con el oro ***");
			}
		} catch (IOException excepcion) {
			System.out.println("Error: El valor introducido debe ser num�rico");
		} catch (NumberFormatException excepcion) {
			System.out.println("Error: El valor introducido debe ser num�rico");
		} finally {
			paraJuego();
		}
	}
	
	/**
	 * M�todo parar obtener el wumpus a partir de la posici�n aleatoria asignada en el tablero
	 * 
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 * @return Devuelve el wumpus a partir de la posici�n aleatoria asignada en el tablero 
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
	 * M�todo para indicar las percepciones de la casilla el cazador
	 * 
	 * @param cazador Objeto que determina los atributos del cazador dentro del tablero
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param listaCasillas Determina la lista de casillas de las que dispone el tablero
	 */
	private static void percepcionesCazador(Cazador cazador, Wumpus wumpus, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = cazador.getX();
		int numeroCol = cazador.getY();
		
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
			cazador.setEstaVivo(false);
			
			System.out.println("*** El cazador ha caido a un pozo y ha muerto ***");
		} else if (casilla.isEstaWumpus() && wumpus.isEstaVivo()) {
			cazador.setEstaVivo(false);
			
			System.out.println("*** El wumpus ha encontrado al cazador y el jugador ha muerto ***");
		} else {
			if (casilla.isTieneMuro()) {
				System.out.println("*** Se percibe que hay alg�n muro ***");
			}
			
			if (casilla.isTieneBrisa()) {
				System.out.println("*** Se percibe una brisa, por lo tanto habr� un pozo cerca ***");
			}
			
			if (casilla.isTieneHedor() && wumpus.isEstaVivo()) {
				System.out.println("*** Se percibe un hedor, por lo tanto estar� el wumpus cerca ***");
			}
			
			if (casilla.isTieneOro()) {
				cazador.setTieneOro(true);
				casilla.setTieneOro(false);
				
				System.out.println("*** Se ha encontrado el oro. Vuelve a la casilla de salida para ganar ***");
			}
		}
	}
	
	/**
	 * M�todo para determinar que acci�n va a realizar el cazador
	 * 
	 * @param cazador Objeto que determina los atributos del cazador dentro del tablero
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @throws IOException Excepci�n debida a que el valor introducido no es num�rico
	 */
	private static void accionCazador(Cazador cazador, Wumpus wumpus, int numeroFilas, int numeroColumnas) throws IOException {
		String direccionActual = cazador.getDireccion();
		
		System.out.println("*** Direcci�n del cazador: " + direccionActual.toLowerCase() + " ***");
		
		System.out.println("1) Avanzar");
		System.out.println("2) Girar 90 grados a la derecha");
		System.out.println("3) Girar 90 grados a la izquierda");
		System.out.println("4) Disparar");
		
		System.out.print("Acci�n a realizar: ");
		int accionRealizar = Integer.parseInt(br.readLine());
		
		// Repetir si no se introduce un valor num�rico v�lido
		while (accionRealizar != 0 || accionRealizar != 1) {
			if (accionRealizar == 1 || accionRealizar == 2 || accionRealizar == 3 || accionRealizar == 4) {
				break;
			}
			
			System.out.println("Error: N�mero no v�lido");
			
			System.out.print("Acci�n a realizar: ");
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
			
			// Mover al cazador de casilla u orientarlo en la direcci�n indicada
			cazador.acciones(Constantes.acciones.MOVER.toString(), direccionActual, direccionNueva, numeroFilas, numeroColumnas);
		} else {
			// Disparar una flecha en la direcci�n indicada
			disparar(cazador, wumpus);
		}
	}
	
	/**
	 * M�todo para que el cazador dispare una flecha en la direcci�n (arriba, derecha, abajo o izquierda) en la que se encuentra
	 * 
	 * @param cazador Objeto que determina los atributos del cazador dentro del tablero
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 */
	private static void disparar(Cazador cazador, Wumpus wumpus) {
		int flechasCazador = cazador.getNumeroFlechas();
		
		if (flechasCazador > 0) {
			int numeroFilaCazador = cazador.getX();
			int numeroColCazador = cazador.getY();
			
			int numeroFilaWumpus = wumpus.getX();
			int numeroColWumpus = wumpus.getY();
			
			// Comprobar la direcci�n del disparo y si el wumpus se encuenra en ella
			switch (Constantes.direccion.valueOf(cazador.getDireccion())) {
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
			
			flechasCazador = cazador.getNumeroFlechas() - 1;
			cazador.setNumeroFlechas(flechasCazador);
			
			System.out.println("*** N�mero de flechas actuales: " + flechasCazador + " ***");
		} else {
			System.out.println("*** El cazador no dispone de m�s flechas ***");
		}
	}
	
	/**
	 * M�todo para determinar que acci�n va a realizar el wumpus
	 * 
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param cazador Objeto que determina los atributos del cazador dentro del tablero
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	private static void accionWumpus(Wumpus wumpus, Cazador cazador, int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = wumpus.getX();
		int numeroCol = wumpus.getY();
		
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
				
				int filaWumpus = wumpus.getX();
				int colWumpus = wumpus.getY();
				
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
				
				String direccion = wumpus.getDireccion();
				boolean editado = false;
				
				// Mover al wumpus de casilla u orientarlo en la direcci�n m�s �ptima
				if (numeroCasillaCazador < numeroCasillaWumpus && !tienePozo(listaCasillas, filaAdyacen, colAdyacen)) {
					if (filaCazador < filaWumpus) {
						String direccionArriba = Constantes.direccion.ARRIBA.toString();
						
						if (direccion.equals(direccionArriba)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(wumpus, direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							wumpus.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionArriba, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					} else if (colCazador < colWumpus) {
						String direccionIzquierda = Constantes.direccion.IZQUIERDA.toString();
						
						if (direccion.equals(direccionIzquierda)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(wumpus, direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							wumpus.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionIzquierda, numeroFilas, numeroColumnas);
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
							movimientosWumpus(wumpus, direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							wumpus.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionDerecha, numeroFilas, numeroColumnas);
						}
						
						editado = true;
					} else if (filaCazador > filaWumpus) {
						String direccionAbajo = Constantes.direccion.ABAJO.toString();
						
						if (direccion.equals(direccionAbajo)) {
							// Indicar que en esta casilla ya no estar� el wumpus
							casilla.setEstaWumpus(false);
							
							// Mover al wumpus
							movimientosWumpus(wumpus, direccion, direccion, numeroFilas, numeroColumnas, listaCasillas);
						} else {
							// Aginar nueva direcci�n
							wumpus.acciones(Constantes.acciones.MOVER.toString(), direccion, direccionAbajo, numeroFilas, numeroColumnas);
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
	private static boolean tienePozo(Map<Posicion, Casilla> listaCasillas, int filaAdyacen, int colAdyacen) {
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
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param direccionActual Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param direccionNueva Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) nueva de la acci�n
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	private static void movimientosWumpus(Wumpus wumpus, String direccionActual, String direccionNueva, int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		// Limpiamos las casillas que tienen el atributo hedor
		for (Posicion posicionLista : listaCasillas.keySet()) {
			Casilla casilla = listaCasillas.get(posicionLista);
			
			if (casilla.isTieneHedor()) {
				casilla.setTieneHedor(false);
			}
		}
		
		wumpus.acciones(Constantes.acciones.MOVER.toString(), direccionActual, direccionNueva, numeroFilas, numeroColumnas);
		
		Casilla casilla = null;
		Posicion posicion = null;
		
		// Aignar la posici�n y la casilla nueva del wumpus
		for (Posicion posicionLista : listaCasillas.keySet()) {
			if (wumpus.getX() == posicionLista.getX() && wumpus.getY() == posicionLista.getY()) {
				posicion = posicionLista;
				
				casilla = listaCasillas.get(posicion);
				casilla.setEstaWumpus(true);
				
				break;
			}
		}
		
		listaCasillas.put(posicion, casilla);
		
		// Asignamos el atributo hedor a las casillas adyacentes
		asignarHedor(wumpus, numeroFilas, numeroColumnas, listaCasillas);
	}
	
	/**
	 * M�todo para asignar la hedor a las casillas adyacentes
	 * 
	 * @param wumpus Objeto que determina los atributos del wumpus dentro del tablero
	 * @param numeroFilas N�mero que determina la cantidad de filas (casillas) de las que dispone el tablero
	 * @param numeroColumnas N�mero que determina la cantidad de columnas (casillas) de las que dispone el tablero
	 * @param listaCasillas Lista que determina las casillas de las que dispone el tablero
	 */
	private static void asignarHedor(Wumpus wumpus, int numeroFilas, int numeroColumnas, Map<Posicion, Casilla> listaCasillas) {
		int numeroFila = wumpus.getX();
		int numeroCol = wumpus.getY();
		
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
	
	/**
	 * M�todo para obtener la nueva direcci�n del personaje
	 * 
	 * @param direccionActual Cadena que determina la direcci�n (arriba, derecha, abajo o izquierda) actual del personaje
	 * @param accionRealizar Cadena que determina la direcci�n (2 - derecha o 3 - izquierda) en la que se mover� el personaje noventa grados
	 * @return Devuelve una cadeba con la nueva direcci�n del personaje
	 */
	private static String definirNuevaDirecccion(String direccionActual, int accionRealizar) {
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
				
				// Obtener la nueva direcci�n a partir de la actual y de la direcci�n del giro
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
	 * M�todo para parar la ejecuci�n del juego
	 */
	private static void paraJuego() {
		System.out.println("El juego ha finalizado");
		
		// Parar la ejecuci�n del juego
		System.exit(1);
	}
	
}