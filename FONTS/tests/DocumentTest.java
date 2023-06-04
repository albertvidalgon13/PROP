package tests;


import domini.Document;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class DocumentTest {
	
	private String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s\n";
	private String loremIpsum2 = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.\n";
	private String loremIpsum3 = "Lorem Ipsum is therefore always free from repetition, injected humour\n";
	
	@Test
	public void viewContingut() {
		Document document = new Document("Lorem","Autos",loremIpsum,"txt","pepe");
		String result = document.viewContingut();
		assertEquals(loremIpsum, result);
	}
	
	@Test
	public void getTypesInDoc(){
		Document document = new Document("Autor","Lorem",loremIpsum3,"txt","pepe");
		ArrayList <String> result = document.getTypesInDoc();
		ArrayList <String> arrayRes = new ArrayList<String>();
		arrayRes.add("lorem");arrayRes.add("ipsum");
		arrayRes.add("free");arrayRes.add("repetition");
		arrayRes.add("injected");arrayRes.add("humour");
		
		assertEquals(arrayRes,result);
	}
	
	@Test
	public void modificaContingut() {
		Document document = new Document("Lorem","Autos",loremIpsum,"txt","pepe");
		document.modificaContingut(loremIpsum2);
		String result = document.viewContingut();
		assertEquals(loremIpsum2, result);
	}
	

}
