package domini;
import java.util.*;
import java.io.Serializable;

/**
 * 
 * (domini) Classe on s'emmagatzema per cada paraula una parella del tipus <String, List<integer>> que representa
 * el document i la llista de posicions on la paraula apareix en el document
 * 
 * @author prop23-subgrup5
 */

public class wordData implements Serializable {
	
		// ATRIBUTS ----------------------------------------------------------------	
		
		// positionsInDoc => <autor+"-"+titol, positions>>
		private HashMap<String, List<Integer>> positionsInDoc;

		
		
		// CONSTRUCTORA ------------------------------------------------------------
		
		public wordData() {
			this.positionsInDoc = new HashMap<String, List<Integer>>();
		}
		
		public wordData(String docName, List <Integer> Positions) {
			this.positionsInDoc = new HashMap<String, List<Integer>>();
			this.positionsInDoc.put(docName, Positions);
		}
		
		// MÈTODES PUBLICS ---------------------------------------------------------
			
		/**
		 * Operaci� per afegir totes les aparicions a un document d'un token
		 * @param docName
		 * Nom del document
		 * @param posl
		 * Llista d'enters amb totes les posicions del token
	
		 */
		public void addToken(String docName, List <Integer> posl) {
				positionsInDoc.put(docName, posl);		
		}
		
		/**
		 * Operaci� per obrenit el nombre de documents on apareix la paraula
		 * @returns nombre de documents on apareix la paraula
	
		 */
		public int numDocsAppearances() {
			return positionsInDoc.size();
		}
		
		/**
		 * Operaci� per eliminar totes les aparicions d'un token a un document
		 * @param docName
		 * Nom del document
	
		 */
		public void eraseEntryDoc(String docName) {
			this.positionsInDoc.remove(docName);	
		}
		/**
		 * Operaci� per obtenir el conjunt de documents on apareix una paraula determinada
		 * @returns
		 * Retorna una Llista amb els documents (Autor-Titol) on apareix el token
	
		 */
		public ArrayList<String> getDocsContainingWord(){
			return new ArrayList<String>(positionsInDoc.keySet());
		}
		/**
		 * Comprova si existeix una praula determinada en un document determinat
		 * @param doc
		 * Nom del document
		 * @returns
		 * Retorna true si existeix el token dins un documetn concret(doc)
	
		 */
		public boolean existWordInsideDoc(String doc) {
			return this.positionsInDoc.containsKey(doc);
		}
		/**
		 * Obt� el conjunt de poisicions on apareix una paraula determinada en un document
		 * @param doc
		 * Nom del document
		 * @returns
		 * Retorna una Llista d'enters amb les posicions del token al document doc.
	
		 */
		public List<Integer> getPositions(String doc) {
			return this.positionsInDoc.get(doc);
		}
	}