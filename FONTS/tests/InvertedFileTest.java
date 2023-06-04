package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import domini.InvertedFile;

/**
 * (Test)
 * @author prop23-subgrup5
 *
 */
public class InvertedFileTest {

	private InvertedFile invertedfile = new InvertedFile();
	@SuppressWarnings("serial")
	private ArrayList <String> oldTokens = new ArrayList<String>() {{add("lorem");add("text");add("joan");add("ipsum");}};
	@SuppressWarnings("serial")
	private List<String> words = new ArrayList<String>() {{
		add("text");add("joan");
	}};
	private List<String> words2 = new ArrayList<String>() {{
		add("lluis");add("i");add("he");add("escrit");
	}};
	private String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500\n";
	
	@Before
	public void initialize() {
		this.invertedfile.docAdded("desconegut", "Lorem Ipsum", loremIpsum);
		this.invertedfile.docAdded("Lluis", "Text Lluis", "hola soc el Lluis i he escrit aquest text");
		this.invertedfile.docAdded("Joan", "Text Joan", "Lorem text joan Ipsum");
	}
	
	@Test
	public void docAdded() {
		Set<String> resultAux = this.invertedfile.getConjTitAutor();
		String result = resultAux.toString();
		assertEquals(result,"[desconegut-Lorem Ipsum, Joan-Text Joan, Lluis-Text Lluis]");
	}
	
	@Test
	public void docRemoved() {
		this.invertedfile.docRemoved("Joan","Text Joan",oldTokens);
		Set<String> resultAux = this.invertedfile.getConjTitAutor();
		String result = resultAux.toString();
		assertEquals(result,"[desconegut-Lorem Ipsum, Lluis-Text Lluis]");
	}
	
	@Test
	public void docModified() {
		this.invertedfile.docModified("Joan","Text Joan",oldTokens,loremIpsum);
		Set<String> resultAux = this.invertedfile.getConjTitAutor();
		String result = resultAux.toString();
		assertEquals(result,"[desconegut-Lorem Ipsum, Joan-Text Joan, Lluis-Text Lluis]");
	}
	
	@Test
	public void getDocsWord() {
		Set<String> resultAux =this.invertedfile.getDocsWord(words);
		String result = resultAux.toString();
		assertEquals(result,"[Joan-Text Joan]");
		
		resultAux =this.invertedfile.getDocsWord(words2);
		result = resultAux.toString();
		assertEquals(result,"[Lluis-Text Lluis]");
	}
	
	@Test
	public void getDocsNOTWord() {
		Set<String> resultAux = this.invertedfile.getDocsNOTWord(words);
		String result = resultAux.toString();
		assertEquals(result,"[desconegut-Lorem Ipsum, Lluis-Text Lluis]");
		
		resultAux = this.invertedfile.getDocsNOTWord(words2);
		result = resultAux.toString();
		assertEquals(result,"[desconegut-Lorem Ipsum]");
	}
	@Test
	public void getDocsSeqWord() {
		Set<String> resultAux = this.invertedfile.getDocsSeqWord(words);
		String result = resultAux.toString();
		assertEquals(result, "[Joan-Text Joan]");
		//Prova amb lowercase i stopwords
		resultAux = this.invertedfile.getDocsSeqWord(words2);
		result = resultAux.toString();
		assertEquals(result, "[Lluis-Text Lluis]");
	}
	
	@Test
	public void trobaKSimilars() {
		TreeMap<String,Float> resultAux = this.invertedfile.trobaKSimilars("desconegut","Lorem Ipsum",2);
		String result = resultAux.toString();
		assertEquals(result, "{Lluis-Text Lluis=0.0, Joan-Text Joan=0.12867996}");
		//Afegim un altre document on el contingut és el mateix, per tant la similitud hauria de ser 1, a més la siilitud dels altres documents més "diferents" hauria de disminuir
		this.invertedfile.docAdded("desconegut", "Lorem Ipsum Copia", loremIpsum);
		resultAux = this.invertedfile.trobaKSimilars("desconegut","Lorem Ipsum",2);
		result = resultAux.toString();
		assertEquals(result,"{desconegut-Lorem Ipsum Copia=1.0, Joan-Text Joan=0.08717948}");
	}
	
}
