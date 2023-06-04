package domini;

import java.util.*;
import java.io.Serializable;
import java.lang.Math;

/**
 * 
 * (domini) Classe que ens serveix per a calcular els pessos de les paraules i documents
 * 
 * @author prop23-subgrup5
 */

public class fileTermData implements Serializable{
	
	/**
	 * Constructora
	 * @author prop23-subgrup
	 *
	 */
	public class weight implements Serializable{
		
		private float tf;
		private float idf;
		
		// CONSTRUCTORA
		
		public weight() {
			this.tf = this.idf = 0;
		}
		public weight(float tf, float idf) {
			this.tf = tf;
			this.idf = idf;
		}
		
		// METODES PUBLICS
		public void updateidf(float idf) {
			this.idf = idf;
		}
		public void updatetf(float tf) {
			this.tf = tf;
		}
		public float getWeight() {
			return Math.abs(tf*idf);
		}
		public void normalizeWeight(float norm) {
			// divideix cada terme per l'arrel de norm, per a que quan es calculi el pes sigui efectiva la normalitzaciï¿½
			this.tf *= Math.sqrt(norm);
			this.idf *= Math.sqrt(norm);
		}
		public float gettf() {
			return this.tf;
		}
		
	}
	
	private HashMap <String, weight> terms;
	
	// CONSTRUCTORA -----------------------------------------------
	/**
	 * Inicialitza HashMap
	 */
	public fileTermData() {
		this.terms = new HashMap<String, weight>();
	}
	
	// METODES PUBLICS --------------------------------------------
	
	/**
	 * Afegeix una nova paraula
	 * @param word
	 * Paraula a afegir
	 * @param tf
	 * TF data
	 * @param idf
	 * IDF data
	 */
	public void addWord(String word, float tf, float idf) {
		this.terms.put(word, new weight(tf,idf));
	}
	/**
	 * Busca la posiciÃ³ d'una paraula
	 * @param word
	 * Paraula a buscar
	 * @return
	 */
	public float getTfWord(String word) {
		return this.terms.get(word).gettf();
	}
	/**
	 * Actualitza el pes 
	 * @param word
	 * Nova paraula
	 * @param idf
	 * Idf data
	 */
	public void updateWeight(String word, float idf) {
		weight w = this.terms.get(word);
		w.updateidf(idf);
		this.terms.put(word, w);
		
		// despres d'actualitzar un pes, normalitza de nou
		//this.normalize();
	}
	
	/**
	 * Afegeix un pes 
	 * @param word
	 * Nova paraula
	 * @param idf
	 * Idf data
	 * @param tf
	 * term frequency
	 */
	public void newWeight(String word, float newidf,float tf) {
		weight w = new weight(newidf,tf);
		this.terms.put(word, w);
		
		// despres d'actualitzar un pes, normalitza de nou
		//this.normalize();
	}
	/**
	 * Retorna el pes d'una paraula
	 * @param word
	 * Paraula a buscar
	 * @return
	 */
	public float getWeight(String word) {
		return this.terms.get(word).getWeight();
	}
	/**
	 * Ens serveix per normalitzar 
	 */
	public void normalize() {
		float magnitude = 0;
		for(Map.Entry<String, weight> entry : terms.entrySet()) {
			  magnitude += Math.pow(entry.getValue().getWeight(), 2);
		}
		magnitude = (float) (1.0/Math.sqrt(magnitude));
		
		for(Map.Entry<String, weight> entry : terms.entrySet()) {
			  entry.getValue().normalizeWeight(magnitude);
		}
	}
	/**
	 * Retorna la magnitud de la estructura
	 * @return
	 */
	public float getMagnitude() {
		float magnitude = 0;
		
		for(Map.Entry<String, weight> entry : terms.entrySet()) {
			  magnitude += Math.pow(entry.getValue().getWeight(), 2);
		}
		magnitude = (float)Math.sqrt(magnitude);
		return magnitude;
	}
	
	/**
	 * Printa tots els termes com a un vector
	 */
	public void printDocTermVector() {
		System.out.print("[");
		for(Map.Entry<String, weight> entry : terms.entrySet()) {
			  System.out.print(" " + entry.getValue().getWeight());
		}
		System.out.println(" ]");
	}
	/**
	 * Operació que retorna la quantitat de termes que conté la representació
	 * @return
	 * Quantitat de termes que conté la representació
	 */
		public int size() {
		return this.terms.size();
	}
	/**
	 * Busca si contÃ© una praula
	 * @param word
	 * Paraula a buscar
	 * @return
	 * True si contÃ© paraula
	 */
	public boolean containsWord(String word) {		
		return this.terms.containsKey(word);
	}
	/**
	 * Retorna el set de paraules al sistema
	 * @return
	 */
	public Set<String> getWordSet(){
		return this.terms.keySet();
	}
	
	
}
