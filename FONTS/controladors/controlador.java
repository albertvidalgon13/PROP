package controladors;
import exceptions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import presencia.*;

import domini.*;
import domini.ConjuntDocuments.CustomPair;


/**
 * 
 * (controlador) Classe que ens serveix per a controlar el nostre sistema, el domini
 * 
 * @author prop23-subgrup5
 */
@SuppressWarnings("unused")
public class controlador {

	/**
	 * Conjunt documents per accedir-hi a la classe
	 */
	private static ConjuntDocuments conjuntDocuments;
	private static String metaDataPath;
	private static String changeLogPath;
	private static String usuariIniciat;
	private static FileWriter myWriter;
	
	public static void changeMetadataPath() {
		String pathactual = System.getProperty("user.dir");
		File f;
		if (pathactual.charAt(pathactual.length()-1) == 'S') {
			f = new File((System.getProperty("user.dir")+"/metaData/metadata.ser"));
		}
		else {
			File x = new File((System.getProperty("user.dir")));
			f = new File((x.getParent() + "/FONTS/metaData/metadata.ser"));
		}
		metaDataPath = f.getAbsolutePath();
	}
	public static void changeLogPath() {
		String pathactual = System.getProperty("user.dir");
		File f;
		if (pathactual.charAt(pathactual.length()-1) == 'S') {
			f = new File((System.getProperty("user.dir")+"/metaData/changelog.txt"));
		}
		else {
			File x = new File((System.getProperty("user.dir")));
			f = new File((x.getParent() + "/FONTS/metaData/changelog.txt"));
		}
		changeLogPath = f.getAbsolutePath();
	}
	
	/**
	 * Operacio constructora del controlador, crea el conjunt de documents en el cas de ser la primera
	 * vegada que es crida, o be el des-serialitza del fitxer de metadata, carregant aixi l'estat
	 * de la plataforma en la darrera sessio.
	 */
	public static void controlador() {
		// des-serialitzacio del conjunt de documents
		try
		{
			changeMetadataPath();
			FileInputStream file = new FileInputStream(metaDataPath);
			ObjectInputStream in = new ObjectInputStream(file);
			
			conjuntDocuments = (ConjuntDocuments)in.readObject();
			
			in.close();
			file.close();
			
			System.out.println("Object has been deserialized.. ");
		}
		
		catch(IOException ex)
		{
			System.out.println("Res a serialitzar.");
			conjuntDocuments = new ConjuntDocuments();
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}
		
		// buida el fitxer de changeLog
		try {
				changeLogPath();
		      File file = new File(changeLogPath);
		      if (file.createNewFile()) {
		    	  System.out.println("File created: " + file.getName());
		      } 
		      else {
		    	  myWriter = new FileWriter(file);
		    	  myWriter.close();
		    	  // el deixa obert
		    	  myWriter =  new FileWriter(file);
		      }
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	/**
	 * Funcio que actualitza l'usuari que te la sessio iniciada
	 * @param newUsuari 
	 * Nou usuari que ha iniciat sessio
	 */
	public static void updateUsuariIniciat(String newUsuari) {
		usuariIniciat = newUsuari;
	}
	
	
	/**
	 * Funcio que serialitza l'objecte conjunt documents. 
	 * Guardant tant els documents com les seves representacions internes per una futura sessio 
	 */
	public static void serialize() {
		// tanca el fitxer de changeLog
		try {
			myWriter.close();
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		// serialitza el conjunt de documents
		try
		{
			changeMetadataPath();
			FileOutputStream file = new FileOutputStream(metaDataPath);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(conjuntDocuments);
			
			out.close();
			file.close();
			System.out.println("Object has been serialized");

		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	/**
	 * Funcio que crea un nou punt de restauracio de l'aplicacio
	 * @param newPrFileName 
	 * Nom del nou punt de restauracio que es vol crear
	 */
	public static void creaPuntRestauracio(String newPrFileName){
		// serialitza l'estat actual de la plataforma en el fitxer amb nom newPrFileName
		if(newPrFileName.contains(" ") || newPrFileName.contains(".")) {
			System.out.println("ABORTANT: El nom del punt de restauracio no pot contenir ni espais ni punts.");
			return;
		}
		
		try
		{	
			
			String pathactual = System.getProperty("user.dir");
			File f;
			if (pathactual.charAt(pathactual.length()-1) == 'S') {
				f = new File((System.getProperty("user.dir")+"/metaData/" + newPrFileName + ".ser"));
			}
			else {
				File x = new File((System.getProperty("user.dir")));
				f = new File((x.getParent() + "/FONTS/metaData/" + newPrFileName + ".ser"));
			}
			
			
			String dst = f.getAbsolutePath();
			// comprova si ja hi ha algun fitxer amb aquest nom
			File tmpDir = new File(dst);
			if(tmpDir.exists()) {
				System.out.println("ABORTANT: no s'ha creat el punt de restauracio, ja existeix un punt creat amb el mateix nom");
			}
			else {
				// Guarda l'objecte en un fitxer
				
				FileOutputStream file = new FileOutputStream(dst);
				ObjectOutputStream out = new ObjectOutputStream(file);

				out.writeObject(conjuntDocuments);
				
				out.close();
				file.close();
				System.out.println("Punt de restauracio creat");
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	
	/**
	 * Funcio que carrega un punt de restauracio existent del sistema
	 * @param prFileName 
	 * Nom del punt de restauracio que es desitja restaurar
	 */
	public static void carregaPuntRestauracio(String prFileName) {
		// des-serializtacio del punt de restauracio
		
		String pathactual = System.getProperty("user.dir");
		File f;
		if (pathactual.charAt(pathactual.length()-1) == 'S') {
			f = new File((System.getProperty("user.dir")+"/metaData/" + prFileName + ".ser"));
		}
		else {
			File x = new File((System.getProperty("user.dir")));
			f = new File((x.getParent() + "/FONTS/metaData/" + prFileName + ".ser"));
		}
		
		String src = f.getAbsolutePath();
		try
		{
			FileInputStream file = new FileInputStream(src);
			ObjectInputStream in = new ObjectInputStream(file);
			
			conjuntDocuments = (ConjuntDocuments)in.readObject();
			
			in.close();
			file.close();
			
			System.out.println("Backup point restored");
		}
		
		catch(IOException ex)
		{
			System.out.println("No s'ha trobat cap punt de restauracio amb PATH = " + src);
			conjuntDocuments = new ConjuntDocuments();
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}
	}
	
	/**
	 * Funcio que crea un nou document amb els seus parametres adients
	 * 
	 * @param titolD
	 * Titol del nou document a crear
	 * @param autorD
	 * Autor del nou document a crear
	 * @param Contingut
	 * Contingut del nou document a crear
	 */
	public static void CreaNouDocument(String titolD, String autorD, String Contingut) {
		try {
			conjuntDocuments.CreaDocument(titolD,autorD,Contingut,"txt",usuariIniciat);
			controladorPresencia.mostraOkCreat();
			
			// excriu al changeLog
			try {
			      myWriter.write("##NEW" + '\n');
			      myWriter.write(autorD+'\n');
			      myWriter.write(titolD + '\n');
			} 
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		catch(fileAlreadyExistingException e){
			controladorPresencia.mostraJaExisteix();

			System.out.println("Exception capturada: " + e);
		}
	}
	
	/**
	 * Funcio que crea un nou document del tipus Xml
	 * 
	 * @param 
	 * Contingut El contingut del document d'incloure el titol, l'autor i el seu contingut
	 * @return 
	 * Retorna true si el contingut del Xml esta estructurat adecuadament i aquest no existeix actualment en el sistema
	 * altrament saltara error amb la seva corresponent explicacio
	 */
	public static boolean CreaNouDocumentXML(String Contingut) {
			String[] separado = Contingut.split(">");
		
	     String t1 = separado[1];
	     String a1 = separado[3];
	     String c1 = separado[5];
	    
	     String[] septit = t1.split("<");
	    
	     String titol = septit[0];
	    
	     String[] sepaut = a1.split("<");
	    
	     String autor = sepaut[0];        
	    
	     String[] sepcont = c1.split("</cont");
	    
	     String c = sepcont[0];
	    
	     System.out.print("titol -> "+ titol + "\n");
	     System.out.print("autor -> "+ autor+"\n");
	     System.out.print("contendio -> "+ c +"\n");
		
		try {
			conjuntDocuments.CreaDocument(titol,autor,c,"xml",usuariIniciat);
			controladorPresencia.mostraOkCreat();
			
			// excriu al changeLog
			try {
			      myWriter.write("##NEW" + '\n');
			      myWriter.write(autor+'\n');
			      myWriter.write(titol + '\n');
			} 
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		catch(fileAlreadyExistingException e){
			controladorPresencia.mostraJaExisteix();

			System.out.println("Exception capturada: " + e);
		}
		return true;
	}
	

	/**
	 * Funcio que realitza una lecctura d'un document del FileSystem
	 * 
	 * @param document 
	 * Document amb el path relatiu introduït
	 * @throws Exception
	 */
	public static void carregaTxt (File document) throws Exception{
		String st;
		BufferedReader br = new BufferedReader(new FileReader(document));
		
		String Autor = br.readLine();
		String Titol =  br.readLine();
		
		String salto = "\n";
		String Contingut = "";
		
		while ((st = br.readLine()) != null) {
			 Contingut += st;
			 Contingut += salto;
			 
		}
		br.close();
		
		try {
			conjuntDocuments.CreaDocument(Titol,Autor,Contingut,"txt",usuariIniciat);
			try {
			      myWriter.write("##NEW" + '\n');
			      myWriter.write(Autor+'\n');
			      myWriter.write(Titol + '\n');
			} 
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		catch(fileAlreadyExistingException e){
			System.out.println("Exception capturada: " + e);
		}
		
	}
	
	/**
	 * Funcio que realitza una lecctura d'un document del tipus Xml del FileSystem
	 * 
	 * @param document
	 * Document amb el path relatiu introduït
	 * @throws Exception
	 */
	public static void carregaXml (File document) throws Exception{
		
		Path path = Path.of(document.getAbsolutePath());
		String str = Files.readString(path);
		
		String[] separado = str.split(">");
		
		String t1 = separado[1];
		String a1 = separado[3];
		String c1 = separado[5];
		
		String[] septit = t1.split("<");
		
		String titol = septit[0];
		
		String[] sepaut = a1.split("<");
		
		String autor = sepaut[0];        
		
		String[] sepcont = c1.split("</cont");
		
		String contingut = sepcont[0];
		
		System.out.print("titol -> "+ titol + "\n");
		System.out.print("autor -> "+ autor+"\n");
		System.out.print("contendio -> "+ contingut+"\n");
		
		try {
			conjuntDocuments.CreaDocument(titol,autor,contingut,"xml",usuariIniciat);
			try {
			      myWriter.write("##NEW" + '\n');
			      myWriter.write(autor+'\n');
			      myWriter.write(titol + '\n');
			} 
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		catch(fileAlreadyExistingException e){
			System.out.println("Exception capturada: " + e);
		}
		
	}
	
	/**
	 * Funcio que distingeix entre .txt o .xml
	 * 
	 * @param document
	 * Document amb el path relatiu introduït
	 * @throws Exception
	 */
	public static void carregaDocument (File document) throws Exception {
		
		String path = document.getAbsolutePath();
		if (path.charAt(path.length()-3) == 'x' && path.charAt(path.length()-2) == 'm' && path.charAt(path.length()-1) == 'l') {
			carregaXml(document);
			
		}
		else if (path.charAt(path.length()-3) == 't' && path.charAt(path.length()-2) == 'x' && path.charAt(path.length()-1) == 't') {
			carregaTxt(document);
		}	
	}
	/**
	 * Funcio que llegeix una carpeta sencera i agafa tots els documents que la composen
	 * 
	 * @param carpeta
	 * Carpeta amb el seu path 
	 * @throws Exception
	 */
	public static void carregaDocuments (File carpeta) throws Exception {
		String[] lista = carpeta.list();
		
		String path = carpeta.getAbsolutePath();
		
		for (int i = 0; i < lista.length; i++) {
			File document = new File(path + "/" + lista[i]);	
			carregaDocument(document);
		}
	}
	/**
	 * Funcio que ens mostra el contingut d'un document donat el seu titol i autor
	 * 
	 * @param titolD
	 * Titol del document a mostrar
	 * @param autorD
	 * Autor del document a mostrar
	 */
	public static void viewContingutDocument(String titolD, String autorD) {
		
		try{
			String c = conjuntDocuments.viewContingutDocument(titolD, autorD);
			resultatCercaContingut res = new resultatCercaContingut(c,autorD,titolD);
		}
		catch (fileNotFoundException e) {
			System.out.println("Exception capturada. Impossible veure el contingut del fitxer. " + e);
		}
	}
	
	public static String getCreador(String titol,String autor) throws fileNotFoundException {
		return conjuntDocuments.viewPermis(titol,autor);
	}
	
	/**
	 * Funcio que ens mostra tots els titols d'un autor passat per parametre
	 * 
	 * @param autor
	 * Autor de la cerca
	 */
	public static void buscaTitolsAutor(String autor) {
		try{
			String resultat = conjuntDocuments.ConsultaTitolsAutor(autor);
			resultatCercaTitolsUnAutor r = new resultatCercaTitolsUnAutor(resultat,autor);	
		}
		catch (autorNotFoundException e) {
			noExisteixAutor au = new noExisteixAutor();
			System.out.println("Exception capturada: " + e);
		}
	}
	/**
	 * Funcio que ens mostra els autors que començen per un prefix passat per parametre
	 * 
	 * @param prefix
	 * Prefix de la cerca
	 */
	public static String buscaAutors(String prefix) {
		String resultat = conjuntDocuments.consultaAutorsPrefix(prefix);
		return resultat;
		//resultatCercaAutors r = new resultatCercaAutors(resultat,prefix);	
	}
	
	/**
	 * Funcio que ens mostra el conjunt de tots els documents (es demana la llista de tots al domini)
	 * 
	 * @throws fileNotFoundException 
	 */
	public static void mostraModDoc() throws fileNotFoundException {	
		List<CustomPair> l = conjuntDocuments.getTitolsAutors();
		modificarDoc a = new modificarDoc(l);
	}
	/**
	 * Funcio que ens mostra el modificar contingut (es demana el contingut al domini)
	 * 
	 * @param titol
	 * Titol del document a modificar
	 * @param autor
	 * Autor del document a modificar
	 */
	public static void setModificaText(String titol, String autor) {
		System.out.println("L'usuari carregat es "+usuariIniciat);
		try{
			String contenido = conjuntDocuments.viewContingutDocument(titol,autor);	
			String usuariCreador = conjuntDocuments.getUsuariCreadorDoc(titol,autor);

			if(usuariIniciat.equals(usuariCreador) || usuariIniciat.equals("admin")) {
				System.out.print("es compleix");
				if(conjuntDocuments.getFormatDoc(titol,autor).equals("txt")) {
					modificacioDocument m = new modificacioDocument(contenido,titol,autor);		
				}
				else {
					String c = "<contingut>"+contenido+"</contingut>";
					modificacioDocument m = new modificacioDocument(c,titol,autor);	
				}
			}
			else {
				error er = new error("errorPermisos");
			}
		}
		catch (fileNotFoundException e) {
			noExisteixDoc m = new noExisteixDoc(titol,autor);
			System.out.println("Exception capturada: " + e);
		}
	}
	/**
	 * Funcio que elimina el document amb titol-autor passat per parametre
	 * 
	 * @param titolD
	 * Titol del document a eliminar
	 * @param autorD
	 * Autor del document a eliminar
	 */
	public static boolean setEliminaDocument(String titolD, String autorD) {
		
		try {
			String usuariCreador = conjuntDocuments.getUsuariCreadorDoc(titolD,autorD);
			if(usuariIniciat.equals(usuariCreador) || usuariIniciat.equals("admin")) {
				conjuntDocuments.eliminaDocument(titolD,autorD);
				try {
				      myWriter.write("##ERASED" + '\n');
				      myWriter.write(autorD+'\n');
				      myWriter.write(titolD + '\n');
				} 
				catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				}
				return true;
			}
			else {
				error err = new error("errorPermisos");
			}
		}
		catch(fileNotFoundException e){
			System.out.println("Exception capturada: " + e);
		}
		return false;
		
	}
	/**
	 * Es modifica el document amb titol-autor amb el contingut passat per parametre
	 * 
	 * @param titol
	 * Titol del document
	 * @param autor
	 * Autor del document
	 * @param contingutNou
	 * Contingut del document modificat
	 */
	public static void modificaDocument(String titol, String autor, String contingutNou) {
		try {
			conjuntDocuments.modificaDocument(titol, autor, contingutNou,usuariIniciat);
			try {
			      
			      myWriter.write("##MODIFIED" + '\n');
			      myWriter.write(autor+'\n');
			      myWriter.write(titol + '\n');
			} 
			catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		catch(fileNotFoundException e){
			System.out.println("Exception capturada: " + e);
		}
		
	}
	/**
	 * Funcio que evalua una expressió booleana
	 * 
	 * @param opbool
	 * Operació booleana
	 */
	public static Set<String> eval(String opbool) {
		Set<String> result = new HashSet<String>();
		
		result = conjuntDocuments.eval(opbool);
		return result;
	}
	/**
	 * Funcio que cerca els documents mes semblants a un document (titol-autor) passat per parametre
	 * 
	 * @param titol
	 * Títol del document
	 * @param autor
	 * Autor del document
	 * @param numero
	 * Numero de documents a buscar
	 */
	public static void cercaSemblants(String titol, String autor, int numero) {
		try {
			TreeMap <String, Float> resultat = conjuntDocuments.trobaKSimilarsCjt(autor,titol,numero);
			resultatCercaSimilars c = new resultatCercaSimilars(titol,autor,numero,resultat);
		}
		catch(fileNotFoundException e){
			noExisteixDoc nc = new noExisteixDoc(titol,autor);
			System.out.println("Exception capturada: " + e);
		}
		
	}
	
	/**
	 * Funcio que cerca els documents mes rellevants respecte un conjunt de paraules
	 * 
	 * @param paraules
	 * String amb el conjunt de paraules a buscar
	 * @param numero
	 * Numero de documents a buscar
	 */
	public static TreeMap <String, Float> cercaRellevants(String paraules, int numero) {
		//try {
			String[] llistPar = paraules.split(" ");
			TreeMap <String, Float> resultat = conjuntDocuments.trobaKRellevantsCjt(llistPar,numero);
			return resultat;
		//}
		/*catch(fileNotFoundException e){
			//noExisteixDoc nc = new noExisteixDoc(titol,autor);
			System.out.println("Exception capturada: " + e);
		}*/
		
	}
	/**
	 * Funcio que mostra el historic de cerques boleanes fetes per l'usuari
	 */
	public static Vector <String> mostraCercaBooleana() {
		Vector <String> tot = conjuntDocuments.getBooleanes();
		return tot;
		//cercaBooleana cb = new cercaBooleana(tot);
	}
	
	/**
	 * Funcio que mostra el resultat de la cerca booleana
	 */
	public static void mostraCercaBooleanaInit() {
		controladorPresencia.mostraCercaBooleanaPresencia();
	}
	
	/**
	 * Funcio que mostra el historic de cerques booleanes ordenades alfabeticament 
	 * @return
	 * Resultat de la cerca booleana ordenat alfabeticament
	 */

	public static Vector <String> mostraCercaBooleanaOrdreAlf() {
    	
    	Vector <String> tot = conjuntDocuments.getBooleanes();
    	return tot;
    	//Collections.sort(tot);
    	//cercaBooleana cb = new cercaBooleana(tot);
	}
	
	/**
	 * Funcio que mostra el historic de cerques booleanes ordenades per antiguitat
	 * @return
	 * Resultat de la cerca booleana ordenat de manera inversa, el mes antic amunt
	 */

	public static Vector <String> mostraCercaBooleanaOrdreAnt() {

    	Vector <String> tot = conjuntDocuments.getBooleanes();
    	return tot;
    	/*Vector <String> totrev = new Vector<String>();
    	for (int i = tot.size() - 1; i >= 0; i--) {
    		totrev.add(tot.get(i));
    	}
    	cercaBooleana cb = new cercaBooleana(totrev);*/
	}
	
	/**
	 * Funcio que mostra el historic de cerques booleanes ordenades per longitud petita a gran
	 * @return
	 * Resultat de la cerca booleana ordenat per longitud
	 */

	public static Vector<String> mostraCercaBooleanaOrdrePetit() {
		Vector <String> tot = conjuntDocuments.getBooleanes();
		return tot;
		/*String[] arr = tot.toArray(new String[tot.size()]); //vector to array
		int n = tot.size();
		Arrays.sort(arr, Comparator.comparing(s->s.length()));
		Vector<String> vector = new Vector<String>(Arrays.asList(arr));
		cercaBooleana cb = new cercaBooleana(vector);*/
		
		//sortS(tot,n);
	}
	
	/**
	 * Funcio que mostra el historic de cerques booleanes ordenades per longitud gran a petita
	 * @return
	 */
	public static Vector<String> mostraCercaBooleanaOrdreGran() {
		Vector <String> tot = conjuntDocuments.getBooleanes();
		return tot;
		/*String[] arr = tot.toArray(new String[tot.size()]); //vector to array
		int n = tot.size();
		Arrays.sort(arr, Comparator.comparing(s->s.length()));
		Vector<String> vector = new Vector<String>(Arrays.asList(arr));
		
		Vector <String> totrev = new Vector<String>();
    	for (int i = tot.size() - 1; i >= 0; i--) {
    		totrev.add(vector.get(i));
    	}
		
		cercaBooleana cb = new cercaBooleana(totrev);*/
		
	}
	
	public static void mostraResultCercaBooleanaOrdreAlfTit(String opbool, Set<String> result) {
		/*
		SortedSet<String> resultord = new TreeSet <String> ();
		for(String x : result) {
			String[] resOfStr = x.split("-",-2);
			String aux = resOfStr[1]+"-"+resOfStr[0];
			resultord.add(aux);
	    }
		
		Set<String> result2 = new LinkedHashSet<String> ();
		for (String y : resultord) {
			String[] resOfStr = y.split("-",-2);
			String aux = resOfStr[1]+"-"+resOfStr[0];
			result2.add(aux);
		}
		
		resultatCercaBooleana rcb = new resultatCercaBooleana(opbool,result2);*/
	}
	
	public static void mostraResultCercaBooleanaOrdreAlfAut(String opbool, Set<String> result) {
		/*SortedSet<String> resultord = new TreeSet <String> ();
		resultord.addAll(result);
		resultatCercaBooleana rcb = new resultatCercaBooleana(opbool,resultord);*/
	}
	
	/**
	 * Funcio que mostra el resultat de la cerca booleana de la seva manera original
	 * @param opbool
	 * String que conte la operacio booleana de la cerca
	 */

	public static void mostraResultatCercaBooleanaNormal(String opbool) {
		controladorPresencia.evalPresencia(opbool);
	}
	
	/**
	 * Funcio relacio interficie->domini. Cerca si existeix un document amb titol-autor determinat
	 * 
	 * @param t
	 * Titol del document
	 * @param a
	 * Autor del document
	 * @return
	 * True si existeix el document amb els atributs passat per parametre
	 */
	public static Boolean existeix(String t, String a) {
		return conjuntDocuments.existsFile(a, t);
	}
	
	/**
	 * Funcio que ens exporta un document
	 * 
	 * @param f
	 * 
	 * @param titol
	 * Titol del documents que es vol exportar
	 * @param autor
	 * Autor del documeent que es vol exportar
	 * @param Filename
	 * 
	 * @throws IOException
	 * 
	 * @throws fileNotFoundException
	 * 
	 */

	public static void exportaDocument(File f, String titol, String autor, String Filename) throws IOException, fileNotFoundException {
		String path = f.getAbsolutePath();
		if (conjuntDocuments.getFormatDoc(titol,autor).equals("txt")) {
			String pathExport = path+"/"+Filename+".txt";
			File fileExport = new File(pathExport);
			fileExport.createNewFile();
	        FileWriter fw = new FileWriter(fileExport);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(titol+"\n");
	        bw.write(autor+"\n");
	        String contingut = conjuntDocuments.viewContingutDocument(titol,autor);
	        bw.write(contingut);
	        bw.close();
		}
		else {
			String pathExport = path+"/"+Filename+".xml";
			File fileExport = new File(pathExport);
			fileExport.createNewFile();
	        FileWriter fw = new FileWriter(fileExport);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("<titol>"+titol+"</titol>\n");
	        bw.write("<autor>"+autor+"</autor>\n");
	        String contingut = conjuntDocuments.viewContingutDocument(titol,autor);
	        bw.write("<contingut>"+contingut+"</contingut>");
	        bw.close();
		}

	}
	
	/**
	 * 
	 * @throws fileNotFoundException
	 */
	public static void mostraModificarDocCreador() throws fileNotFoundException {
		List<CustomPair> l = conjuntDocuments.getDocsCreatsUser(usuariIniciat);
		modificarDocCreador a = new modificarDocCreador(l);
	}
	
	/**
	 * Funcio que ens permet controlar el login a traves del controlador d'usuaris
	 * 
	 * @param name
	 * Nom del usuari a comprovar les seves credencials
	 * @param pas
	 * Contrasenya del usuari a comprovar les seves credecnials
	 * @return
	 */

	public static boolean controlaLoginDomini(String name, String pas) {
		return controladorUsuaris.controlaLogin(name, pas);
	}
	
	/**
	 * Funcio que ens permet crear un nou usuari a traves del controlador d'usuaris
	 * 
	 * @param name
	 * Nom del nous usuari a registrar en el sistema
	 * @param pas
	 * Contrasenya del nou usuari a regstirar en el sistema
	 * @return
	 * Retorna fals si no es possible crear el nou usuari, altrament cert
	 */

	public static boolean newUsuariDomini(String name, String pas) {
		return controladorUsuaris.newUsuari(name,pas);
	}
	
	/**
	 * 
	 */
	public static void log() {
		controladorPresencia.carregaSessioUsuaris();
		controladorPresencia.log();
	}
	
	/**
	 * Funcio que ens permet exportat tot el conjunt de documents del sistema
	 * 
	 * @param f
	 * Tot el conjunt de documents del sistema
	 * @throws IOException
	 * 
	 */

	public static void exportaTotsDocs (File f) throws IOException {
		List<CustomPair> l = conjuntDocuments.getTitolsAutors();
		
		String placetosave = f.getAbsolutePath() + "/documents";
		
		Path path = Paths.get(placetosave);
		Files.createDirectories(path);
		
		File fn = new File(placetosave);
		
		for (int i = 0; i < l.size(); i++) {
		   	CustomPair x = l.get(i);
		    String titol = x.snd;
		    String autor = x.fst;
		    try {
				exportaDocument(fn,titol,autor,titol);
			} catch (IOException | fileNotFoundException e) {
				e.printStackTrace();
			}
		    
	   }
		controladorPresencia.totsOKexportats();
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean usuariIniciatAdmin() {
		if (usuariIniciat.equals("admin")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Funcio que ens permet borrar tot el conjunt de documents
	 */
	public static void esborrarTot() {
		conjuntDocuments.clearAll();
	}
	
	/**
	 * 
	 */
	public static void eliminaAquestUsuari() {
		
	}
}

