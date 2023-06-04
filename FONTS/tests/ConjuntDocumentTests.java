package tests;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;
import domini.ConjuntDocuments;
import exceptions.autorNotFoundException;
import exceptions.fileAlreadyExistingException;
import exceptions.fileNotFoundException;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class ConjuntDocumentTests {
	
	private String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s\n";
	private String loremIpsum2 = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.\n";
	private ConjuntDocuments conjuntdocuments = new ConjuntDocuments();
	
	@Before
	public void initialize() throws fileAlreadyExistingException {
		this.conjuntdocuments.CreaDocument("Lorem Ipsum","desconegut",loremIpsum,"txt","pepe");
		this.conjuntdocuments.CreaDocument("Lorem Ipsum2","desconegut",loremIpsum2,"txt","pepe");
		this.conjuntdocuments.CreaDocument("text Joan","Joan","El text es den Joan","txt","pepe");
		this.conjuntdocuments.CreaDocument("text Jordi","Jordi","Aixo es el text escrit per en Jordi","txt","pepe");
	}
	
	
	
	@Test
	public void CreaDocument_eliminaDocument() throws fileAlreadyExistingException, autorNotFoundException, fileNotFoundException {
		
		
		
		String result = conjuntdocuments.ConsultaTitolsAutor("desconegut");
		String result2 = conjuntdocuments.ConsultaTitolsAutor("Joan");
		assertEquals(result,"Lorem Ipsum\nLorem Ipsum2\n");
		assertEquals(result2,"text Joan\n");
		this.conjuntdocuments.eliminaDocument("Lorem Ipsum", "desconegut");
		result = conjuntdocuments.ConsultaTitolsAutor("desconegut");
		assertEquals(result,"Lorem Ipsum2\n");
	}
	
	@Test
	public void viewContingutDocument_modifiaContingut() throws fileAlreadyExistingException, fileNotFoundException {
		String result = conjuntdocuments.viewContingutDocument("Lorem Ipsum","desconegut");
		assertEquals(result,loremIpsum);
		this.conjuntdocuments.modificaDocument("Lorem Ipsum", "desconegut", loremIpsum2,"admin");
		result = conjuntdocuments.viewContingutDocument("Lorem Ipsum","desconegut");
		assertEquals(result,loremIpsum2);
	}
	
	@Test
	public void consultaAutorsPrefix() throws fileAlreadyExistingException {
		String result = this.conjuntdocuments.consultaAutorsPrefix("Jo");
		assertEquals(result,"Joan\nJordi\n");
	}
	@Test
	public void getbrackets() {
		String result = ConjuntDocuments.getbrackets("(hola|adeu)&({Ei}&!Jordi)");
		assertEquals(result,"()({})");
		result = ConjuntDocuments.getbrackets("!hola");
		assertEquals(result,"");
	}
	@Test
	public void checkOpBool() {
		boolean result = ConjuntDocuments.checkOpBool("(hola|adeu)&({Ei}&!Jordi)");
		assertEquals(result,true);
		result = ConjuntDocuments.checkOpBool("(hola|adeu}&[{Ei}&!Jordi)");
		assertEquals(result,false);
	}
	
	@Test
	public void exchangeVals() {
		Vector<Set<String>> resultAux = this.conjuntdocuments.exchangeVals("{lorem dummy} | Ipsum & !typesetting");
		String result = resultAux.toString();
		String exp = "[[desconegut-Lorem Ipsum], [|], [], [&], [!], [desconegut-Lorem Ipsum]]";
		assertEquals(result,exp);
		resultAux = this.conjuntdocuments.exchangeVals("{lorem dummy} & ((!{sun moon} | jordi) & month)");
		result = resultAux.toString();
		exp = "[[desconegut-Lorem Ipsum], [&], [(], [(], [!], [], [|], [Jordi-text Jordi], [)], [&], [], [)]]";
		assertEquals(result,exp);
	}
	
	@Test
	public void eval() {
		Set<String> resultAux = this.conjuntdocuments.eval("!typesetting");
		String result = resultAux.toString();
		assertEquals(result,"[Jordi-text Jordi, desconegut-Lorem Ipsum2, Joan-text Joan]");
		resultAux = this.conjuntdocuments.eval("Gracies");
		result = resultAux.toString();
		assertEquals(result,"[No hi ha solucions]");
	}
}