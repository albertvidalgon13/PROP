package domini;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 * (domini) Classe que ens serveix per a representar un document amb els seus respectius parametres
 * 
 * @author prop23-subgrup5
 */

public class Document implements Serializable{
	
    // ATRIBUTS PRIVATS -------------------------------------------------------
	
	private Contingut contingut;
	private String format;
	// representacio interna breu del contingut -> paraules sense repeticions, ni stopwords ni caracters especials
    private ArrayList <String> paraulesDoc;
    private String permis;
	
	// OPERACIONS PRIVADES -----------------------------------------------------
	
    /**
	 * Operació que volca el fitxer amb les stopWords en un Array
	 * @param f
	 * És el document que conté la llista de stopwords
	 * @returns una llista de Strings amb totes les stopwords
	 */
	public static ArrayList<String> readLines(File f) throws IOException {
		ArrayList<String> stopwords = new ArrayList<String>();
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line=br.readLine()) != null) {
			stopwords.add(line);
		}
		br.close();
		fr.close();
		return stopwords;
	}
	
	/**
	 * Operació que actualitza la representació interna del document
	 * @param contingut
	 * contingut del document un cop modificat
	 */
	private void updateTokens(String contingut) {
		// processa El contingut del document per construir la representació interna
		
		String pathactual = System.getProperty("user.dir");
		File f;
		if (pathactual.charAt(pathactual.length()-1) == 'S') {
			f = new File((System.getProperty("user.dir")+"/domini/stopwords8.txt"));
		}
		else {
			File x = new File((System.getProperty("user.dir")));
			f = new File((x.getParent() + "/FONTS/domini/stopwords8.txt"));
		}
		
		ArrayList<String> stopwords = new ArrayList<String>();
		try {
			stopwords = readLines(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String cleanWords = contingut.replaceAll(",","");
		cleanWords = cleanWords.replaceAll("[^a-zA-Z0-9Ã¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“ÃšÃ Ã¨Ã¬Ã²Ã¹Ã€ÃˆÃŒÃ’Ã™Ã„Ã‹Ã�Ã–ÃœÃ¤Ã«Ã¯Ã¶Ãœ ]", " ");
		
		cleanWords = cleanWords.toLowerCase();
        this.paraulesDoc = new ArrayList<String>(Arrays.asList(cleanWords.split(" ")));
        this.paraulesDoc.removeAll(stopwords);
	}
	
	// CONSTRUCTORA -------------------------------------------------------------
	
	/**
	 * Operació constructora parametritzada
	 * @param autor
	 * nom de l'autor del document a construir
	 * @param titol
	 * titol del document a construir
	 * @param contingut
	 * contingut del document a construir
	 * @returns un objecte de titol i contingut donat
	 */
    public Document(String autor, String titol, String contingut, String f,String userCreador) {
		this.contingut  = new Contingut(contingut);
		this.updateTokens(contingut);
		this.format = f;
		this.permis = userCreador;
	}	
	
	
	// MÈTODES PÚBLICS
	
    /**
	 * Operació per veure el contingut del document
	 * @returns el contingut del document
	 */
	public String viewContingut() {
		return this.contingut.view();		
	}
	/**
	 * Operació per veure el format del document
	 * @returns el format del document
	 */
	public String getFormat() {
		return this.format;		
	}
	/**
	 * Operació per veure l'usuari creador del document
	 * @returns l'usuari creador del document
	 */
	public String getCreador() {
		return this.permis;		
	}
	
	/**
	 * Operació per obtenir tots els types del document. S'utilitza al conjunt de documents per actualitzar la seva representació interna
	 * @returns la llista amb els types del document
	 */
	public ArrayList <String> getTypesInDoc(){
		return this.paraulesDoc;
	}
	/**
	 * Operacio que retorna l'usuari que te permisos sobre aquest document
	 * @returns l'usuari que te els permisos
	 */
	public String getUserCreador(){
		return this.permis;
	}
	
	/**
	 * Operació per modificar el contingut del fitxer. A més, s'actualitza la seva representació interna pel nou contingut
	 * @param contingut
	 * contingut del document un cop modificat
	 */
	public void modificaContingut(String contingut) {
		this.contingut = new Contingut(contingut);
		this.updateTokens(contingut);
	}
	
}
