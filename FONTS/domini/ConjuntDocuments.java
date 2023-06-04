package domini;
import java.util.*;
import java.util.Map.Entry;
import java.io.Serializable;
import exceptions.*;
//import java.lang.*;

/** 
 * 
 * (domini) Classe principal del sistema, conté les representacions dels documents i les funcionalitats principals.
 * 
 * @author prop23-subgrup5
 */


public class ConjuntDocuments implements Serializable {
	
	// ATRIBUTS PRIVATS -----------------------------------------------------------
	
	private Set<String> all;
	private HashMap <String,HashMap <String, Document>> cjtDocuments;
	private int k;
	private InvertedFile IF;
	
	/**
	 * Operació que revisa si un prefix és igual al nom d'algun autor
	 * @param prefix
	 * TPrefix a comprovar
	 * @param autor
	 * Autor pel que es revisa si el prefix és igual
	 * @returns cert si el prefix és de l'autor, fals en cas contrari
	 */
	private boolean checkEqString(String prefix, String autor) {
		int size = Math.min(prefix.length(),autor.length());
		for(int i = 1; i < size; i++) {
			if(prefix.charAt(i) != autor.charAt(i)) return false;
		}
		return true;
	}
	
	public class CustomPair {
	    public String fst;
	    public String snd;

	    public CustomPair(String f, String s){
	    	fst = f;
	    	snd = s;
	    } 	    
	}
	
	public class CustomBoolean{
		public int interaccion;
		HashMap<String,String> resultats;
		
		public CustomBoolean(){
			interaccion = 0;
			resultats = null;
		}
	}
	
	/**
	 * Operació que comprova si un cert Document existeix al sistema
	 * @param autorD
	 * autor del document a comprovar
	 * @param titolD
	 * titol del document a comprovar
	 * @returns cert si document identificat per <titol,autor> existeix al sistema.
	 */
	public boolean existsFile(String autorD, String titolD) {
		if(cjtDocuments.containsKey(autorD)) {
			HashMap <String, Document> tmp = cjtDocuments.get(autorD);
			if(tmp.containsKey(titolD)) return true;
			else return false;
		}
		return false;
	}
	
	// CONSTRUCTORA -------------------------------------------------------------------
	
	
	/**
	 * Operació constructora
	 * @returns un objecte de tipus ConjuntDocuments buit
	 */
	public ConjuntDocuments () {
		cjtDocuments = new HashMap <String,HashMap<String, Document>>();
		k = 0;
		this.IF = new InvertedFile();
		all = new HashSet<String>();
	}
	
	// METODES PUBLICS -----------------------------------------------------------------
	
	/**
	 * Operació que afegeix un nou document al sistema. A més d'afegir-lo al conjunt de documents, l'afegeix a la seva representació
	 * intera al fitxer invertit.
	 * @param titolD
	 * titol del document a crear
	 * @param autorD
	 * autor del document a crear
	 * @param contingut
	 * tcontingut del document a crear
	 * @throws fileAlreadyExistingException si el fitxer ja existeix al sistema.
	 */
	public void CreaDocument(String titolD, String autorD, String contingut, String format, String userCreador) throws fileAlreadyExistingException{
		
		if(this.existsFile(autorD, titolD)) {
			throw new fileAlreadyExistingException("Impossible crear nou fitxer, el document identificar per " + "<" + autorD + "," + titolD+"> ja existeix al sistema.");
		}
		else {
			Document tmpDoc = new Document(titolD, autorD, contingut, format, userCreador);
			HashMap<String,Document> titols = cjtDocuments.get(autorD);
			if(titols == null) titols = new HashMap<String,Document>();
			titols.put(titolD, tmpDoc);
			cjtDocuments.put(autorD, titols);
			String x =autorD+"-"+titolD;
			all.add(x);
			IF.docAdded(autorD, titolD, contingut);
		}
	}
	/**
	 * Operació que ens retorna l'usuari que hac reat un document
	 * @param autorD
	 * autor del que es consulten els títolsdocumentl nom de l'usuari que ha creat el document .
	 * @throws fileNotFoundException 
	 * @param titolD
	 * Titol del document
	 * @return String
	 * Retorna l'usuari que hac reat el document (autorD,titolD)
	 */
	public String getUsuariCreadorDoc(String titolD,String autorD) throws fileNotFoundException {
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			HashMap <String,Document> titols = cjtDocuments.get(autorD);
			Document tmpDoc = titols.get(titolD);
			String c = tmpDoc.getUserCreador();
			return c;	
		}
	}
	
	/**
	 * Operació que consulta la llista de titols d'un cert autor
	 * @param autorD
	 * autor del que es consulten els títols
	 * @returns una llista amb el nom del titols dels documents d'aquell autor.
	 * @throws autorNotFoundException l'autor no té cap document al seu nom al sistema.
	 */
	public String ConsultaTitolsAutor(String autorD) throws autorNotFoundException{
		HashMap <String,Document> titols = cjtDocuments.get(autorD);
		if(titols == null) {
			throw new autorNotFoundException("L'autor <" + autorD + "> no existeix al sistema.");
		}
		else {
			String autors ="";
			Set<String> keys = titols.keySet();
			for(String key: keys) {;	  
				  autors += key +"\n";
				  System.out.print(key);
			}
			return autors;
		}
		
	}	
	
	/**
	 * Operació que retorna el contingut d'un cert document.
	 * @param titolD
	 * titol del document a visualitzar
	 * @param autorD
	 * autor del document a visualitzar
	 * @returns el contingut del document identificat per <titol, autor>
	 * @throws fileNotFoundException si el document identificat per <titol, autor> no existeix al sistema.
	 */
	public String viewContingutDocument(String titolD, String autorD) throws fileNotFoundException {
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			HashMap <String,Document> titols = cjtDocuments.get(autorD);
			Document tmpDoc = titols.get(titolD);
			String c = tmpDoc.viewContingut();
			return c;	
		}
		
	}
	/**
	 * Operació que retorna el creador d'un cert document.
	 * @param titolD
	 * titol del document a comprovar el seu creador
	 * @param autorD
	 * autor del document a comprovar el seu creador
	 * @returns l'usuari que ha creat el document identificat per titolD i autorD
	 * @throws fileNotFoundException si el document identificat per <titol, autor> no existeix al sistema.
	 */
	public String viewPermis(String titolD, String autorD) throws fileNotFoundException {
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			HashMap <String,Document> titols = cjtDocuments.get(autorD);
			Document tmpDoc = titols.get(titolD);
			String c = tmpDoc.getCreador();
			return c;	
		}
		
	}
	
	public String getFormatDoc(String titol, String autor) throws fileNotFoundException {
			HashMap <String,Document> titols = cjtDocuments.get(autor);
			Document tmpDoc = titols.get(titol);
			String c = tmpDoc.getFormat();
			return c;	
	}
	
	
	/**
	 * Consulta la llista d'autors que es corresponen amb un prefix donat
	 * @param prefix
	 * prefix pel qual es comprova si existeix cap autor.
	 * @returns euna llista amb els autors que es corresponen amb el prefix
	 */
	public String consultaAutorsPrefix(String prefix) {
		String autors ="";
		Set<String> keys = new HashSet<String>();
		keys= cjtDocuments.keySet(); //convertim HashMap de autos amb set per iterar
		//System.out.print(keys);
		for(String key: keys) {
			boolean t = true;
			for (int j = 0; j < prefix.length() && t == true;++j) {
				if(key.length() < prefix.length()) t = false;
				else if (key.charAt(j) != prefix.charAt(j)) t = false;
			}
			if (t == true) {
				autors+= key+"\n";
			}
		}
		return autors;
	}
	
	/**
	 * Operació que modifica el contingut d'un cert document. A més, també actualitza la representació interna del conjunt de documents pel nou contingut del document.
	 * @param titolD
	 * titol del document a modificar
	 * @param autorD
	 * autor del document a modificar
	 * @param contingut
	 * @throws fileNotFoundException si el document identificat per <titolD, autorD> no existeix al sistema
	 */
	public void modificaDocument(String titolD, String autorD, String contingut,String usuariIniciat) throws fileNotFoundException{
		
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			HashMap <String,Document> titols = cjtDocuments.get(autorD);
			// busca el titol de l'obra per l'autor
			Document tmpDoc = titols.get(titolD);
			// s'obté la representació interna del document per actualitzar la representació interna del conjunt
			ArrayList <String> oldTokens = tmpDoc.getTypesInDoc();
			IF.docModified(autorD,titolD, oldTokens, contingut);
			tmpDoc.modificaContingut(contingut);
			titols.put(titolD, tmpDoc);			
		}
		
	}
	
	/**
	 * Operació per obtenir el titol i autor de tots els documents del sistema
	 * @returns Una llista amb l'autor i titol de cadascun dels documents carregats al sistema.
	 */
	public List<CustomPair> getTitolsAutors() {
		List<CustomPair> LisTitAut = new ArrayList<CustomPair>();
		
		Set<Map.Entry<String, HashMap<String, Document>> > entries = cjtDocuments.entrySet();
		
		 for (Map.Entry<String,HashMap< String,Document>> entry : entries) {
	            Set<String> keys = entry.getValue().keySet();
	    		for(String key: keys) {
	    			CustomPair autTit = new CustomPair(entry.getKey(),key);
	    			LisTitAut.add(autTit);
	    		}
	    }
		return LisTitAut;
	}
	
	/**
	 * Operació per obtenir el titol i autor de tots els documents creats per un usuari
	 * @param user
	 * Usuari
	 * @returns Una llista amb l'autor i titol de cadascun dels documents creat per l'usuari user.
	 */
	public List<CustomPair> getDocsCreatsUser(String usuari) {
		List<CustomPair> LisTitAut = new ArrayList<CustomPair>();
		
		Set<Map.Entry<String, HashMap<String, Document>>> entries = cjtDocuments.entrySet();
		
		 for (Map.Entry<String,HashMap< String,Document>> entry : entries) {
			 Set<Entry<String, Document>> docs = entry.getValue().entrySet();
			 for(Entry<String, Document> doc : docs) {
	    			if(doc.getValue().getCreador().equals(usuari)) {
	    				CustomPair autTit = new CustomPair(entry.getKey(),doc.getKey());
		    			LisTitAut.add(autTit);
	    			}
	    			
	    		}
	    }
		
		return LisTitAut;
	}
	
	/**
	 * Operació que elimina un document del sistema. A més d'eliminar-lo, elimina tots els types que conté de la representació interna del conjunt de documents, actualitzant les estructures del fitxer invertit i del model d'espai vectorial
	 * @param titolD
	 * titol del document a eliminar
	 * @param autorD
	 * autor del document a eliminar
	 * @throws fileNotFoundException si el document identificat per <titolD, autorD> no existeix al sistema
	 */
	public void eliminaDocument(String titolD, String autorD) throws fileNotFoundException{
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			// primer obte la representacio interna del document a esborrar
			ArrayList <String> oldTokens = this.cjtDocuments.get(autorD).get(titolD).getTypesInDoc();
			// actualitza la representacio interna del conjunt de documents
			IF.docRemoved(autorD, titolD, oldTokens);
			this.cjtDocuments.get(autorD).remove(titolD);
			
			// en cas de que l'autor del document esborrat no tingui més documents, s'esborra també l'entrada de l'autor
			if(this.cjtDocuments.get(autorD) == null) {
				this.cjtDocuments.remove(autorD);
			}
		}
		
	}
	
	/**
	 * Operació que retorna una llista amb els documents on hi apareixen les paraules words
	 * @param words
	 * paraules a consultar en el conjunt de documents
	 * @return un Set<String> amb els documents on hi apareixen totes les paraules del parametre words
	 */
	public Set<String> conjDocConteWords(List<String> words) {
		Set<String> docs = this.IF.getDocsWord(words);
		return docs;
	}
	
	/**
	 * Operació que retorna una llista amb els documents on no hi apareixen les paraules words
	 * @param words
	 * paraules a consultar en el conjunt de documents
	 * @return un Set<String> amb els documents on no hi apareixen totes les paraules del parametre words
	 */
	public Set<String> conjDocConteNOWords(List<String> words) {
		Set<String> docs = this.IF.getDocsNOTWord(words);
		return docs;
		
	}
	/**
	 * Operació que retorna una llista amb els documents on hi apareix la seqüència de paraules words
	 * @param words
	 * seqüència de paraules a consultar en el conjunt de documents
	 * @return un Set<String> amb els documents on hi apareix la seqüència de paraules del parametre word
	 */
	public Set<String> conjDocsConteSeqWords(List<String> words) {
		Set<String> docs = this.IF.getDocsSeqWord(words);
		return docs;
	}
	
	/**
	 * Operació que retorna la llista dels k documents similars a un cert document.
	 * @param titolD
	 * titol del document pel que es llisten els seus k similars
	 * @param autorD
	 * autor del document pel que es llisten els seus k similars
	 * @param k
	 * Un enter que determina quans documents es llisten
	 * @returns un HashMap amb el pes i el respectiu titol i autor del document que és similar al que es proporciona.
	 * @throws fileNotFoundException si el document identificat per <titolD, autorD> no existeix al sistema
	 */
	public TreeMap <String, Float>trobaKSimilarsCjt(String autorD, String titolD, int k) throws fileNotFoundException{
		if(!this.existsFile(autorD, titolD)) {
			throw new fileNotFoundException("El document identificat per " + "<" + autorD + "," + titolD+"> no existeix al sistema.");
		}
		else {
			return IF.trobaKSimilars(autorD, titolD, k);
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
	public TreeMap<String, Float> trobaKRellevantsCjt (String[] llistPar,int k) {
		return IF.trobaKRellevants(llistPar,k);
	}
	
	/**
	 * Operació que retorna nomes els brackets () {} de la boolexpresion
	 * @param boolexpresion
	 * expressió booleana a evaluar
	 * @return un String on nomès apareixen els brackets del String boolexpresion
	 */
	public static String getbrackets(String boolexpresion) {
		String result = boolexpresion;
		result = result.replaceAll("[^(){}]", "");
		return result;
	}
	
	/**
	 * Aux op check parentesis recursive 3 funcions
	 */
	
	public static boolean is_open_br(Character c) {
		if (c == '(' || c == '{' || c == '[') return true;
		else return false;
	}
	
	public static Character match(Character c) {
		if (c == ')') return '(';
		else if (c == '}') return '{';
		else return '[';
	}
	
	public static boolean bracketsRec(String s) {
		
		Vector<Character> v1 = new Vector<Character>();
		
		for (int i = 0; i < v1.size(); ++i) {
			Character current = s.charAt(i);
			
			if (is_open_br(current)) v1.add(current);
			else if (v1.size() == 0) return false;
			else if (match(current) != v1.elementAt(v1.size()-1)) return false;
			else v1.remove(v1.size()-1);
		}
		
		return v1.size() == 0;
	}
	
	/**
	 * Operació que comprova que la boolexpresion estigui ben construida en termes de brackets, implementada amb stack. ALtrament es podria fer recursiu
	 * @param boolexpresion
	 * expressió booleana a evaluar
	 * @return cert si l'expressió està ben construida en termes de brackets, altrament fals
	 */
	public static boolean checkOpBool(String boolexpresion) {
		String clean = getbrackets(boolexpresion);
		if (clean.length() % 2 != 0) return false;
		
		//if (bracketsRec(clean)) System.out.print("aaa\n"); //recursiu
		
		Stack<Character> stack = new Stack<Character>();
		for (char c : clean.toCharArray()) {
			if (c == '(' || c == '{') {stack.push(c);}
			else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {stack.pop();}
			else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {stack.pop();}
		}
		return stack.isEmpty();
	}

	
	/**
	 * Operació que ens crea una estructura de dades a partir dels documents que compleixen les necessitas de l'expressió booleana
	 * @param boolexp
	 * expressió booleana a evaluar
	 * @return una estrucuta de dades amb els documents que compleixen les necessitats de l'expressió a evaluar amb parentesis i negacions
	 */
	public Vector<Set<String>> exchangeVals (String boolexp) {
		Vector<Set<String>> output = new Vector<Set<String>>();
		
		for (int i = 0; i < boolexp.length(); i++) {
			if (boolexp.charAt(i) == '&') {
				Set<String> res = new HashSet<String>();
				res.add("&");
				output.add(res);
			}
			else if (boolexp.charAt(i) == '|') {
				Set<String> res = new HashSet<String>();
				res.add("|");
				output.add(res);
			}
			else if (boolexp.charAt(i) == '(') {
				Set<String> res = new HashSet<String>();
				res.add("(");
				output.add(res);
			}
			else if (boolexp.charAt(i) == ')') {
				Set<String> res = new HashSet<String>();
				res.add(")");
				output.add(res);
			}
			else if (boolexp.charAt(i) == '!') {
				Set<String> res = new HashSet<String>();
				res.add("!");
				output.add(res);
			}
			else if (boolexp.charAt(i) == '"') { //sequencia paraules
				String seqWords = "";
				
				int j; i+=1;
				for (j = i; j < boolexp.length() && boolexp.charAt(j) != '"'; j++) {seqWords += boolexp.charAt(j);}
				
				List<String> param = new ArrayList<String>(Arrays.asList(seqWords.split(" ")));
				Set<String> res = new HashSet<String>();
				res = conjDocsConteSeqWords(param);
				output.add(res);
				i = j;				
			} 
			else if (boolexp.charAt(i) == '{') { // x paraules
				String words = "";
				
				int j;
				for (j = i; j < boolexp.length() && boolexp.charAt(j) != '}'; j++) {if (j != i) words += boolexp.charAt(j);}
				
				Set<String> res = new HashSet<String>();
				List<String> param = new ArrayList<String>(Arrays.asList(words.split(" ")));
				res = conjDocConteWords(param);
				output.add(res);
				i = j;
			}
			else if (boolexp.charAt(i) != ' ') { //paraula sola "hola adeu" & joan
				String word = "";
				
				int j;
				for (j = i; j < boolexp.length() && boolexp.charAt(j) != ' ' && boolexp.charAt(j) != ')'; j++) {word += boolexp.charAt(j);}
				
				Set<String> res = new HashSet<String>();
				List<String> param = new ArrayList<String> ();
				param.add(word);
				res = conjDocConteWords(param);
				output.add(res);
				i = j-1;
			}
		}
		return output;
	}
	
	
	/**
	 * Operació que ens retorna el resultat de la operació booleana introduida per parametre
	 * @param expbool
	 * expressió booleana a evaluar
	 * @return el resultat de l'expressió booleana un cop realitzades les operacions & | ! i amb les seves respectives prioritats dels parentesis
	 */
	public Set<String> S(Vector<Set<String>> expbool) {
	    Set<String> result = P(expbool);
	    
	    if (!expbool.get(k).isEmpty() && expbool.get(k).iterator().next() == "|") { //if el primer elem del set es una OR
	    	if (k < expbool.size()-1) {
	    		k++;
        	}
	        Set<String> b = P(expbool);
	        result.addAll(b); // OR amb sets
	    } 
	    else if (!expbool.get(k).isEmpty() && expbool.get(k).iterator().next() == "&") { //if el primer elem del set es una AND
	    		if (k < expbool.size()-1) {
	    			k++;
	    		}
	            Set<String> b = P(expbool);
		        result.retainAll(b); // AND amb sets
	        }
	    return result;
	}
	
	/**
	 * Operació que ens permet retornar el resultat de l'operació booleana si aquest està negat o no
	 * @param expbool
	 * expressió booleana a evaluar
	 * @return el resultat de l'expressió booleana en els casos on aquest ha de ser negat o no
	 */
	public Set<String> P(Vector<Set<String>> expbool) {
		
		if (!expbool.get(k).isEmpty() && expbool.get(k).iterator().next() == "!") {
			
			if (k < expbool.size()-1) {
				k++;
			}
			
			Set<String> todos = new HashSet<String>(all);
			
			Set<String> todos2 = new HashSet<String>();
			todos2 = A(expbool);
			if (!todos2.isEmpty()) {
				todos.removeAll(todos2);
			}
			return todos;
		}
	    else
	    {   
	        return A(expbool); 
	    }
	}

	/**
	 * Operació que ens permet retornar el resultat de l'operació booleana si aquest està prioritzar per un parentesis o no
	 * @param expbool
	 * expressió booleana a evaluar
	 * @return el resultat de l'expressió booleana en el casos on aquest es precedit per una prioritat de parentesis o no
	 */
	public Set<String> A(Vector<Set<String>> expbool) {
		Set<String> result = new HashSet<String>();
		
	    if (!expbool.get(k).isEmpty() && expbool.get(k).iterator().next() == "(") {
	    	if (k < expbool.size()-1) {
        		k++;
        	}
	        result = S(expbool);
	        if (expbool.get(k).iterator().next() == ")") { 
	        	if (k < expbool.size()-1) {
	        		k++;
	        	}
	        }
	    } 
	    else {
	    	
	    	result = expbool.get(k);
	    	if (k < expbool.size()-1) {
        		k++;
        	}
	    }

	    return result;
	}
	
	/**
	 * Operació que ens retorna el resultat final de la expressió booleana que es vol consular si aquesta està ben construida
	 * @param expbool
	 * expressió booleana a evaluar
	 * @return un Set<String> amb els documents que compleixen les condicions de l'expressió booleana si aquesta ha estat ben construida, altrament un missatge
	 * indicant que l'expressió ha estat mal construida
	 */
	public Set<String> eval(String expbool) {
		
		Set<String> output = new HashSet<String>();
		boolean correct = false;
		correct = checkOpBool(expbool);
		
		Vector<Set<String>> res = new Vector<Set<String>>();
		res = exchangeVals(expbool);
	
		if (correct) {
			Set<String> result = new HashSet<String>();
			OpBoleana.putOperacio(expbool);
			
		    if (res.size() > 1) {result = S(res);k = 0;}
		    else {result = res.get(0);k=0;}
		    
		    if (result.isEmpty()) output.add("No hi ha solucions"); 
		    else output = result;
		}
		else {
			output.add("Expressio incorrecte");
		}
		return output;
	}
	
	/**
	 * Operació que retorna totes les opreacions booleanes ben construides introduides per l'usuari
	 * @return un Vector<String> on cada string és una expressió booleana ben construida
	 */
	public static Vector<String> getBooleanes(){
		return OpBoleana.getOps();
	}
	
	/**
	 * Operació que borra tots els documents del conjunt de documents
	 */
	public void clearAll() {
		cjtDocuments.clear();
	}
}


