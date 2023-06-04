package domini;

import java.util.*;

import java.io.Serializable;


/** 
 * 
 * (domini) Classe que ens serveix per a emmagatzemar el contingut d'un document
 * 
 * @author prop23-subgrup5
 */

public class Contingut implements Serializable{
	private List<String> words;
	private int numParagraphs;
	
	// MÈTODES PRIVATS
	
	/**
	 * Operació que separa reconstrueix en un únic String el contingut del document
	 * @param paragraphs
	 * llista de Strings, on cadascun és un paràgraf del document original
	 * @param numP
	 * número de paràgrafs del document
	 * @returns un String amb tot el contingut del document
	 */
	private void processaContingut(String[] paragraphs, int numP) {
		for(int i = 0; i < numP; i++) {
			words.add(paragraphs[i]);
		}
	}
	
	// CONSTRUCTORES
	
	/**
	 * Operació constructora
	 * @returns un objecte buit de tipus Contingut 
	 */
	public Contingut() {
		this.numParagraphs = 0;
		this.words =  new ArrayList<String> ();
	}
	
	
	/**
	 * Operació constructora parametritzada
	 * @param contingut
	 * contingut del nou fitxer a construir
	 * @returns un objecte buit de tipus Contingut amb el contingut donat
	 */
	public Contingut(String contingut) {
		
		String[] paragraphs = contingut.split("\n");
		this.numParagraphs = paragraphs.length;
		
		this.words = new ArrayList<String> (this.numParagraphs);		
		processaContingut(paragraphs, this.numParagraphs);
	}
	
	// MÈTODES PÚBLICS
	
	/**
	 * Operació que retorna el contingut del document
	 * @returns un String amb tot el contingut del document
	 */
	public String view() {
		String contingut = "";
		for(int i = 0; i < this.numParagraphs; i++) {	
			contingut += words.get(i);
			contingut += "\n";
		}
		return contingut;
	}
	

	
}
