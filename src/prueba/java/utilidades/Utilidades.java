package prueba.java.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para crear métodos útiles a poder ser usados por el resto de clases
 * 
 * @author Josep Puertas
 */
public class Utilidades {
	
	/**
	 * Método para generar números aleatorios que no se repitan y devolver una lista de ellos.
	 * El número cero queda excluido ya que es la casilla de salida y por eso se suma uno
	 * 
	 * @param numeroValores Número que determina la cantidad de valores únicos
	 * @param rango Número que determina el rango (valor máximo) a generar
	 * @return Devuelve una lista con números aleatorios que no se repiten
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