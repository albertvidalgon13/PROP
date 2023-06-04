package tests;

import domini.wordData;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class wordDataTest {
	
	private List<Integer> pos = new ArrayList<Integer>() {{
        add(1);add(17);
        add(3);add(22);
        add(15);add(29);
        } };
    private String titShek ="Romeo y Julieta-Sheakspear";
	
	
	/*@Test
	public void printPostingList() {
	
	}*/
	
	@Test
	public void numDocsAppearances() {
		wordData worddata = new wordData(titShek,pos);
		worddata.addToken("titol2", pos);
		worddata.addToken("titol3", pos);
		int result = worddata.numDocsAppearances();
		assertEquals(3,result);
	}
	
	
	@Test
	public void eraseEntryDoc() {
		wordData worddata = new wordData(titShek,pos);
		worddata.eraseEntryDoc(titShek);
		boolean result  = worddata.existWordInsideDoc(titShek);
		assertEquals(result,false);
		
	}
	
	@Test
	public void getDocsContainingWord(){
		wordData worddata = new wordData(titShek,pos);
		ArrayList<String> arrayTit = new ArrayList<String>() {{add("titol2");add("titol3");add(titShek);}};
		worddata.addToken("titol2", pos);
		worddata.addToken("titol3", pos);
		ArrayList<String> result = worddata.getDocsContainingWord();
		assertEquals(result,arrayTit);
	}
	
	@Test
	public void existWordInsideDoc() {
		wordData worddata = new wordData(titShek,pos);
		boolean result = worddata.existWordInsideDoc(titShek);
		boolean result2 = worddata.existWordInsideDoc("noExisteix");
		assertEquals(result,true);
		assertEquals(result2,false);
	}
	
	@Test
	public void getPositions() {
		wordData worddata = new wordData(titShek,pos);
		List<Integer> result = worddata.getPositions(titShek);
		assertEquals(result,pos);
	}
	
	public void addToken() {
		wordData worddata = new wordData();
		worddata.addToken(titShek, pos);
		boolean result = worddata.existWordInsideDoc(titShek);
		assertEquals(result,true);
			
}
}
