package tests;

import domini.fileTermData;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class fileTermDataTest {
	
	private Set<String> setWords= new HashSet<String>() {{add("hola");add("adeu");}};

	@Test
	public void addWord_containsWord() {
		fileTermData filetermdata = new fileTermData();
		filetermdata.addWord("hola",15,7);
		filetermdata.addWord("adeu",15,7);
		Set<String> result = filetermdata.getWordSet();
		boolean result2 = filetermdata.containsWord("hola");
		boolean result3 = filetermdata.containsWord("nen");
		assertEquals(setWords,result);
		assertEquals(true,result2);
		assertEquals(false,result3);
	}

}
