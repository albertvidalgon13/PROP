package domini;
import java.io.File;

import java.io.Serializable;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


/**
 *(domini) Clase la qual guarda totes les paraules que apareixen al conjunt de tots els documents, a més per cada paraula guarda a quin document
 *apareix i en quines posicions, també s'encarrega de fer les busquedes més complexes com booleana o els k documents més semblants. 
 * @author prop23-subgrup5
 */
public class InvertedFile implements Serializable{
	
	/**
	 * 	ED pel fitxer invertit
		*- cada entrada de HashMap és una paraula
		*- per cada paraula es te un objecte wordData amb la posting list de la paraula
	 */
	private HashMap <String, wordData> index;	
	

	/**
	 * 	ED pels term vectors dels documents
		*- cada entrada del HashMap representa un document, on el seu nom és autor+"-"+nom
		*- per cada document és té un objecte fileTermData, que conté les dades del
		*	termvector del document
	 */
	private HashMap <String, fileTermData> docTerms;
	
	private int numDocs;
	
	/**
	 *Clase per calcular la similitud entre dos documents
	 */
	public class termSimilarity{
		float sim;
		
		public termSimilarity(fileTermData f1, fileTermData f2) {
			float magnitudef1 = f1.getMagnitude();
			float magnitudef2 = f2.getMagnitude();
			
			float escalarProduct = 0;
			
			if(f1.size() < f2.size()) {
				Set<String> keys = f1.getWordSet();
		        for(String key: keys){
		            if(f2.containsWord(key)) {
		            	escalarProduct+= f1.getWeight(key) * f2.getWeight(key);
		            }
		        }
			}
			else {
				Set<String> keys = f2.getWordSet();
		        for(String key: keys){
		            if(f1.containsWord(key)) {
		            	escalarProduct+= f1.getWeight(key) * f2.getWeight(key);
		            }
		        }
			}
			if(magnitudef1 * magnitudef2 == 0) this.sim=0;
			else this.sim = escalarProduct/(magnitudef1 * magnitudef2);
		}
		public float getSim() {
			return this.sim;
		}
	}
	/**
	 * 	Pair<Float,String>, Pair per representar la similitud d'un document amb un altre document
		*- fst és la similitud del document snd amb el un document determinat
		*- snd és el document per el qual es comprova la similitud
	 */
	public class CustomPairSim{
	    private float fst;
	    private String snd;

	    public CustomPairSim(float f, String s){
	    	fst = f;
	    	snd = s;
	    } 	    
	    
	    public float getSim() {
	    	return fst;
	    }
	    
	    public String getDocName() {
	    	return snd;
	    }
	}
	
	public class CustomComparator implements Comparator <CustomPairSim> {
	    public int compare(CustomPairSim object1, CustomPairSim object2) {
	        return  (int)(object2.getSim()*10000) - (int)(object1.getSim()*10000);
	    }
	}
	
	// CONSTRUCTORA -------------------------------------------------------------------------------------
	
	public InvertedFile() {
		this.index = new HashMap<String, wordData> ();
		this.numDocs = 0;
		this.docTerms = new HashMap <String, fileTermData>();
	}
	
	// METODES PRIVATS
	
	
	// METODES PUBLICS ----------------------------------------------------------------------------------
	
	/**
	 * Lectura de les stopwords en un document amb totes elles
	 * @return Una Llista amb totes les stopwords, les quals estan a stopwords8.txt
	 * @throws IOException
	 */
	public static ArrayList<String> readLinesStopwords() throws IOException {
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
	 * Operació que ens permet afegir un nou document a l'aplicació
	 * @param autorD
	 * autor del document a afegir
	 * @param titolD
	 * titol del document a afegir
	 * @param contingut
	 * contingut del document a afegir
	 */
	public void docAdded(String autorD, String titolD, String contingut){
			
		/*
		NETEJA DEL CONTINGUT DEL DOCUMENT
		S'elimina del contingut tota mena de caràcters especials.Després, es
		separa el contingut per espais, de forma que només queda un vector de paraules, i d'aquestes
		paraules s'eliminen les stopwords
		 */
		
		this.numDocs++;
		String docName = autorD+"-"+titolD;
		
	
		ArrayList<String> stopwords = null;
		try {
			stopwords = readLinesStopwords();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String cleanWords = contingut.replaceAll("[^a-zA-Z0-9Ã¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“ÃšÃ Ã¨Ã¬Ã²Ã¹Ã€ÃˆÃŒÃ’Ã™Ã„Ã‹Ã�Ã–ÃœÃ¤Ã«Ã¯Ã¶Ãœ ]", " ");
	    cleanWords = cleanWords.toLowerCase();
	    ArrayList<String> cleanWordsList = new ArrayList<String>(Arrays.asList(cleanWords.split(" ")));
	    HashMap <String, List<Integer>> tokensPositions = new HashMap <String, List<Integer>> ();
	    cleanWordsList.removeAll(stopwords);
	    
	    /*
	    Amb el contingut del nou document es fan dues coses
	    	1. Afegir les dades dels seus tokens a l'inverted file
	    	2. Construir el seu termvector
	    
	    Posteriorment, s'han d'actualitzar els pesos tdf-idf d'aquelles
	    paraules que apareixen al nou document
	     */
	    
	    // primer es construeix un HashMap<Strings, <Llista Posicions>>
	    
	    for(int i = 0; i < cleanWordsList.size(); i++) {
	    	// comprova si Ã©s una paraula
	    	
	    	if(!cleanWordsList.get(i).isEmpty()) {
	    		if (tokensPositions.containsKey(cleanWordsList.get(i))) {
	    			List <Integer> l = tokensPositions.get(cleanWordsList.get(i));
	    			l.add(i);
	    			tokensPositions.put(cleanWordsList.get(i), l);
	    		}
	        	else {
	        		List <Integer> l = new ArrayList <Integer> ();
	        		l.add(i);
	        		tokensPositions.put(cleanWordsList.get(i), l);
	        	}
	    	}
	    }
	        
        // Es recorre aquesta estructura i s'afegeix a l'IF
        // s'aprofita també per obtenir la frequencia de la paraula més freqüent en el document, de cara al tdf-idf
        
        int mostFreqWord = 0;
        
        for(Map.Entry<String,List<Integer>> entry : tokensPositions.entrySet()) {
			  String key = entry.getKey();
			  List <Integer> value = entry.getValue();
			  
			  mostFreqWord = Math.max(mostFreqWord, value.size());
			  
			  // comprova si la paraula existeix al IF
			  
			  	// si no existeix, crea una nova entrada pel token
			  if(!index.containsKey(key)) {
				  wordData tmp = new wordData(docName, value);
				  index.put(key, tmp);
			  }
			  	// si existeix, afegeix una entrada perl wordData d'aquell token
			  else {
				  wordData tmp = index.get(key);
				  tmp.addToken(docName, value);
				  index.put(key, tmp);
			  }
        }
	        
	        
	        
        /*
        Un cop introduits els tokens del nou fitxer al fitxer invertit, es crea el term vector
        del document. Per cada paraula es necessitara
        	- term frequency -> frequencia_paraula/frequencia_mes_frecuent
        	- inverse document frequency -> numero_documents/numero_aparicions_paraula_en_altres_docs
        
        on el numero d'aparicions de la paraula en altres docs s'obtindra del IF
         */
        
        fileTermData t = new fileTermData();
        
        for(Map.Entry<String,List<Integer>> entry : tokensPositions.entrySet()) {
			  String key = entry.getKey();
			  float tf = (float)entry.getValue().size()/(float)mostFreqWord;
			  // en aquest punt únicament calculem el tf, doncs el idf es recalculara per tot el sistema en acabar aquesta operacio
			  t.addWord(key, tf, 0);
        }
        //t.normalize();
        this.docTerms.put(docName, t);   
        this.updateIdf();
        
	}
	
	/**
	 * Operació que ens permet mantenir la consistencia de l'aplicació desprès d'eliminar un document. Es realitzen un seguit d'operacions;
	 * 		- esborrar totes les entrades a l'inverted file per cadascuna de les paraules que contenia -> oldTokens
	 *		- esborrar l'entrada al vector space model del document
	 *		- actualitzar tots els pesos idf de totes les paraules del conjunt
	 * @param autorD
	 * autor del document a eliminar
	 * @param titolD
	 * titol del document a eliminar
	 * @param oldTokens
	 * 
	 */
	public void docRemoved(String autorD, String titolD, ArrayList <String> oldTokens) {
		String docName = autorD+"-"+titolD;
		for(int i = 0; i < oldTokens.size(); i++) {
			String wordToErase = oldTokens.get(i);
			if(index.containsKey(wordToErase)) {
				index.get(wordToErase).eraseEntryDoc(docName);
				// comprova si aquella paraula no té més entrades a la posting list, i en aquest cas l'esborra
				if(index.get(wordToErase).numDocsAppearances() == 0) {
					index.remove(wordToErase);
				}
			}
	
		}
		this.numDocs--;
		this.docTerms.remove(docName);
		this.updateIdf();
	}
	
	/**
	 * Operació per modificar un document
	 * @param autorD
	 * autor del document modificat
	 * @param titolD
	 * titol del document modificat
	 * @param oldTokens
	 * Tokens que hi havia anteriorment al document abans de ser modificat
	 * @param contingut
	 * nou contingut del document a modificat
	 */
	public void docModified(String autorD, String titolD, ArrayList <String> oldTokens, String contingut) {
		this.docRemoved(autorD, titolD, oldTokens);
		this.docAdded(autorD, titolD, contingut);
	}
	
	/**
	 * Operació que troba els k documents més similars al document identificat per autorD i titolD
	 * @param autorD
	 * autor del document modificat
	 * @param titolD
	 * titol del document modificat
	 * @param k
	 * enter indicant quants documents volem retornar
	 * @return
	 * Un TreeMap amb el titol+autor del document i la seva similitud amb el document de referència
	 */
	public TreeMap <String, Float> trobaKSimilars(String autorD, String titolD, int k){
		ArrayList <CustomPairSim> pairs = new ArrayList <CustomPairSim> ();
		String docName = autorD+"-"+titolD;
		fileTermData ft = docTerms.get(docName);
		
		for(Map.Entry<String,fileTermData> entry : docTerms.entrySet()) {
				if(entry.getValue() != ft) {
					termSimilarity tsim = new termSimilarity (ft, entry.getValue());
					pairs.add(new CustomPairSim(tsim.getSim(), entry.getKey()));
				}
		}
		Collections.sort(pairs, new CustomComparator());
		TreeMap <String, Float> sortedResult = new TreeMap<String, Float>(Collections.reverseOrder());
		
		// agafa els k més relevants
		if(k >= pairs.size()) {
			for(int i = 0; i < pairs.size(); i++) {
				CustomPairSim temp = pairs.get(i);			
				sortedResult.put(temp.getDocName(), temp.getSim());
			}
			
			return sortedResult;
		}
		else {
			for(int i = 0; i < k; i++) {
				CustomPairSim temp = pairs.get(i);			
				sortedResult.put(temp.getDocName(), temp.getSim());
			}
			return sortedResult;
		}
	}
	
	/**
	 * Cerca els k documents més rellevant en funció d'un conjunt de paraules
	 * 
	 * @param llistPar
	 * Llista de les paraules les quals serviran com a query per la cerca
	 * @param k
	 * nombre de documents que volem retornar
	 * @return
	 * retorna un TreeMap amb els documents més rellevants resècte la query

	 */
	public TreeMap<String,Float> trobaKRellevants(String[] llistPar,int k) {
		ArrayList <CustomPairSim> pairs = new ArrayList <CustomPairSim> ();
		
		fileTermData ft = new fileTermData();
	        
		HashMap<String,List<Integer>> tokensPositions = new HashMap<String,List<Integer>>();
		
		for(int i =0; i<llistPar.length; i++) {
			List<Integer> l = new ArrayList<Integer>();
			l.add(i+1);
			tokensPositions.put(llistPar[i], l);
		}
		
        for(Map.Entry<String,List<Integer>> entry : tokensPositions.entrySet()) {
			  String key = entry.getKey();
			  //float tf = (float)entry.getValue().size()/1.0;
			  float tf = 1;
			  ft.addWord(key, tf, 0);
        }
        
        for(Map.Entry<String,wordData> entry : index.entrySet()) {
			String word = entry.getKey();			
			wordData w = entry.getValue();
			ArrayList <String> docsContainingWord = w.getDocsContainingWord();
			int numDocsContainingWord = docsContainingWord.size();
			float newIdf = (float)Math.log(((float)this.numDocs)/((float)numDocsContainingWord));
			
			for(int j = 0; j < docsContainingWord.size(); j++) {
				ft.newWeight(word,newIdf,1);
			}
		} 
        
        //Buscam els mes similars a la query
    	for(Map.Entry<String,fileTermData> entry : docTerms.entrySet()) {
			if(entry.getValue() != ft) {
				termSimilarity tsim = new termSimilarity (ft, entry.getValue());
				pairs.add(new CustomPairSim(tsim.getSim(), entry.getKey()));
			}
			
		}
		
		Collections.sort(pairs, new CustomComparator());
		TreeMap <String, Float> sortedResult = new TreeMap<String, Float>(Collections.reverseOrder());
		// agafa els k més relevants
		if(k >= pairs.size()) {
			for(int i = 0; i < pairs.size(); i++) {
				CustomPairSim temp = pairs.get(i);			
				sortedResult.put(temp.getDocName(), temp.getSim());
			}
			return sortedResult;
		}
		else {
			for(int i = 0; i < k; i++) {
				CustomPairSim temp = pairs.get(i);			
				sortedResult.put(temp.getDocName(), temp.getSim());
			}
			return sortedResult;
		}
	}
	
	private boolean canUpdate() {
		if(numDocs < 10) return true;
		if(numDocs < 30 && numDocs % 3 == 0) return true;
		if(numDocs > 30 && numDocs % 5 == 0) return true;
		return false;
	}
	/**
	 * Operació que ens permet actualitzar Idf
	 */
	public void updateIdf() { 
		if(canUpdate()) {
			for(Map.Entry<String,wordData> entry : index.entrySet()) {
				String word = entry.getKey();			
				wordData w = entry.getValue();
				ArrayList <String> docsContainingWord = w.getDocsContainingWord();
				int numDocsContainingWord = docsContainingWord.size();
				float newIdf = (float)Math.log(((float)this.numDocs)/((float)numDocsContainingWord));
				
				for(int j = 0; j < docsContainingWord.size(); j++) {
					docTerms.get(docsContainingWord.get(j)).updateWeight(word,newIdf);
				}
			}   
		}
    }
		
	    
	/**
	 * OPeració que retorna una llista amb tots els documents i els seus respectius autors
	 * @return un Set<String> amb tots els documents de l'aplicació i els seus autors
	 */
	public Set<String> getConjTitAutor() {
		return this.docTerms.keySet();
	}  
	    
	    
	/**
	 * Operació que retorna una llista de docuemtns que conte un conjunt de paraules
	 * @param words
	 * Llista de paraules que volem comprovar a quins documents apareix
	 * @return
	 * Retorna una Set<String> amb el conjunt de documents de forma: Ator-Titol on apareixen totes les paraules de 'words'
	 */
	public Set<String> getDocsWord(List<String> words) {
		List<String> stopwords = null;
		try {
			stopwords = readLinesStopwords();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> list = new ArrayList<String>();
		words.removeAll(stopwords);
		//Aqui hay que hacer try catch, si la lista words esta vacia hay que devolver un error tipo:"no has introducido busquedas o solo son stopwords"
		if(words.isEmpty()) return this.docTerms.keySet(); 

		wordData paraula = this.index.get(words.get(0));
		if(paraula == null); //error
		else {
			list = paraula.getDocsContainingWord();
		}
		for(int i =1; i< words.size(); i++) {
			for(int j = 0; j < list.size() && !list.isEmpty();j++) {
				if(!this.index.get(words.get(i)).existWordInsideDoc(list.get(j))) {
					list.remove(j);
				}
			}	
		}
		Set<String> setDocs = new HashSet<String>(list);
		return setDocs;
	}
	
	/**
	 * Operació que retorna una llista amb tots els documents on no apareixen les paraules words
	 * @param words
	 * llista de paraules
	 * @return un Set<String> amb tots els documents on les paraules words no hi apareixen
	 */
	public Set<String> getDocsNOTWord(List<String> words) {
		
		Set<String> list= getDocsWord(words);
		Set<String> listNotWords = this.docTerms.keySet();
		listNotWords.removeAll(list);
		return listNotWords;
	}
	
	/**
	 * Operació que retorna una llista amb tots els documents on apareix la seqüència de paraules words
	 * @param words
	 * seqüència de paraules
	 * @return un Set<String> amb tots els documents on la seqüència de paraules words hi apareix
	 */
	public Set<String> getDocsSeqWord(List<String> words) {
		
		Set<String> listIntersec = getDocsWord(words);
		for(String doc: listIntersec) {
			if(!areSeq(doc,words)) listIntersec.remove(doc); 
		}
		return listIntersec;
	}
	
	/**
	 * Operació que comprova si apareixen les paraules words de manera sequüencial en el document doc
	 * @param doc
	 * document on comprovam si hi apareixen les paraules words de forma seqüencial
	 * @param words
	 * Paraules que volem veure si apareixen en ordre
	 * @return true si apareixen les paraules de forma seqüencial, false en cas contrari
	 */
	private boolean areSeq(String doc, List<String> words) {
		for(int i = 1; i < words.size(); i++) {
			List<Integer> posAnt = this.index.get(words.get(i-1)).getPositions(doc);
			List<Integer> posAct =this.index.get(words.get(i)).getPositions(doc);
			
			boolean found = false;
			for(int k = 0; k<posAnt.size() && !found; k++) {
				int pos = posAnt.get(k);
				for(int j = 0; j< posAct.size(); j++) {
					if( pos== posAct.get(j)-1) found = true;
					if(!found) return false;
				}
			}
		}
		
		return true;
	}	
		
}
