package domini;

import java.util.*;

/**
 * 
 * (domini) Classe que ens serveix per a enmagatcemar l'historic de les operacions boleanes fetes per l'usuari
 * 
 * @author prop23-subgrup5
 */

public class OpBoleana{
	
	private static Vector<String> historic_operacions = new Vector <String>();
	
	
	public OpBoleana(){}
	
	/**
	 * Serveix per afegir una operació booleana a la llista
	 * @param o
	 * Operació nova
	 */
	public static void putOperacio(String o) {
		historic_operacions.add(o);
	}
	/**
	 * Retorna el vector de totes les operacions fetes fins al moment
	 * @return
	 */
	public static Vector<String> getOps(){
		return historic_operacions;
	}
	
}
