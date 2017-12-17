package prueba.java.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para crear m�todos �tiles a poder ser usados por el resto de clases
 * 
 * @author Josep Puertas
 */
public class Utilidades {
	
	/**
	 * M�todo para generar n�meros aleatorios que no se repitan y devolver una lista de ellos.
	 * El n�mero cero queda excluido ya que es la casilla de salida y por eso se suma uno
	 * 
	 * @param numeroValores N�mero que determina la cantidad de valores �nicos
	 * @param rango N�mero que determina el rango (valor m�ximo) a generar
	 * @return Devuelve una lista con n�meros aleatorios que no se repiten
	 */
	public List<Integer> numerosAleatorios(int numeroValores, int rango) {
		List<Integer> listaNumeros = new ArrayList<Integer>();
		
		do {
			int numeroAleatorio = (int) (Math.random() * rango + 1);
			
			if (!listaNumeros.contains(numeroAleatorio)) {
				listaNumeros.add(numeroAleatorio);
			}
		} while(listaNumeros.size() < numeroValores);
		
		return listaNumeros;
	}
	
}