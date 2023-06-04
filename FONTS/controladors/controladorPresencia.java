package controladors;
import exceptions.fileNotFoundException;
import presencia.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 
 * (controlador) Classe que ens serveix per a controlar la presencia del nostra interficie
 * 
 * @author prop23-subgrup5
 */
@SuppressWarnings("unused")
public class controladorPresencia {
	
	/**
	 * Crida a la interficie per mostrar Alta de documents
	 */
	public static void mostraAltaDoc() {
		altaDoc a = new altaDoc();
	}
	/**
	 * Crida a la interficie per mostrar Creacio de documents
	 */
	public static void mostraCreaDoc() {
		creaDoc a = new creaDoc();
	}
	
	/**
	 * Crida a la interficie per mostrar Creacio de document Xml
	 */
	public static void mostraCreaDocXml() {
		creaDocXml a = new creaDocXml();
	}
	/**
	 * Crida a la interficie per mostrar Menu
	 */
	public static void mostraMenu() {
		menu a = new menu();
	}
	/**
	 * Crida a la interficie per mostrar Cerca de Similars
	 */
	public static void mostraCercaSimilars() {
		cercaSimilars a = new cercaSimilars();
	}
	/**
	 * Crida a la interficie per mostrar Confirmacio de modifficacio
	 */
	public static void mostraConfirmMod() {
		mostraConfirmMod a = new mostraConfirmMod();
	}
	/**
	 * Crida a la interficie per donar l'OK de creacio
	 */
	public static void mostraOkCreat() {
		mostraOkCreat a = new mostraOkCreat();
	}
	/**
	 * Crida a la interficie per mostrar que ja existeix un Document
	 */
	public static void mostraJaExisteix() {
		mostraJaExisteix a = new mostraJaExisteix();
	}
	/**
	 * Crida a la interficie per mostrar Carrega documents
	 */
	public static void mostraCarregaDoc() {
		carregaDoc a = new carregaDoc();
	}
	/**
	 * Crida a la interficie per mostrar Menu de cerca
	 */
	public static void mostraCerca() {
		cerca a = new cerca();
	}
	/**
	 * Crida a la interficie per mostrar Cerca de titols-autor
	 */
	public static void mostraCercaTitolAutor() {
		cercaTitAut a = new cercaTitAut();
	}
	/**
	 * Crida a la interficie per mostrar Cerca dels titols d'un autor
	 */
	public static void mostraCercaTitolsUnAutor() {
		cercaTitUnAut a = new cercaTitUnAut();
	}
	/**
	 * Crida a la interficie per mostrar Cerca d'un autor per prefix
	 */
	public static void mostraCercaAutors() {
		cercaAutors a = new cercaAutors();
	}
	/**
	 * Crida a la interficie per mostrar Confirmacio de sortida de la APP
	 */
	public static void mostraConfirmacioSortida() {
		confirmacionSalida a = new confirmacionSalida();
	}
	/**
	 * Crida a la interficie per mostrar Confirmacio de sortida de usuari
	 */
	public static void mostraConfirmacioSortidaToLogin() {
		confirmacioSortidaToLogin a = new confirmacioSortidaToLogin();
	}
	/**
	 * Crida a la interficie per mostrar Confirmacio esborrat
	 */
	public static void mostraConfirmacioEsborrat() {
		confirmacionEsborrat a = new confirmacionEsborrat();
	}
	/**
	 * Crida a la interficie per mostrar Modificacio de document
	 * @throws fileNotFoundException 
	 */
	public static void mostraModDoc() throws fileNotFoundException {
		controlador.mostraModDoc();
	}
	/**
	 * Crida a la interficie per mostrar el LOG-IN
	 */
	public static void mostraLogIn() {
		login a = new login();
	}
	/**
	 * Crida a la interficie per mostrar la confirmacio d'esborrat d'un document
	 */
	public static void mostraSeguroEliminarDoc(String titol, String autor) {
		
		EliminaDoc e = new EliminaDoc(titol,autor);
	}
	/**
	 * Crida a la interficie per mostrar errors
	 */
	public static void mostraError(String err) {
		error a = new error(err);
	}
	/**
	 * Crida a la interficie per mostrar el registre d'usuaris
	 */
	public static void mostraRegistreUsuari() {
		registrarUsuari x = new registrarUsuari();
	}
	/**
	 * Crida a la interficie per mostrar la cerca de k documents mes rellevants
	 */
	public static void mostraKRellevants() {
		cercaKRellevants x = new cercaKRellevants();
	}
	/**
	 * 
	 * Crida a la interficie per mostrar exportar Documents
	 * @param t
	 * Titol del document a exportar
	 * @param a
	 * Autor del document a exportar
	 */

	public static void mostraExportarDoc(String t, String a) {
		exportaDoc x = new exportaDoc(t,a);
	}
	/**
	 * 
	 * Funcio que mostra el resultat ordenat alfabeticament ascendent de la cerca titols d'un autor
	 * @param result
	 * Titols del autor ordeants alfabeticament ascendentment
	 * @param autor
	 * Autor dels titols
	 */

	public static void mostraResultOrdreAlfTitA(String result, String autor) {
		String[] lines = result.split("\n");
		Arrays.sort(lines);		
		String result2 = "";
		for(String y: lines) {;	  
		  result2 += y +"\n";
		  
		}
		resultatCercaTitolsUnAutor rctua = new resultatCercaTitolsUnAutor(result2,autor);
	}
	/**
	 * Funcio que mostra el resultat ordenat alfabeticament ascendent de la cerca titols d'un autor
	 * 
	 * @param result
	 * Titols del autor ordeants alfabeticament ascendentment
	 * @param autor
	 *  Autor dels titols
	 */

	public static void mostraResultOrdreAlfTitD(String result, String autor) {
		String[] lines = result.split("\n");
		Arrays.sort(lines, Collections.reverseOrder());   
		String result3 = "";
		for (String x : lines) {
			result3 += x + "\n";
		}
		resultatCercaTitolsUnAutor rctua = new resultatCercaTitolsUnAutor(result3,autor);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana en ordre alfabetic
	 */

	public static void mostraCercaBooleanaOrdreAlfPresencia() {
		Vector <String> tot = controlador.mostraCercaBooleanaOrdreAlf();
    	Collections.sort(tot);
    	cercaBooleana cb = new cercaBooleana(tot);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana en ordre invers
	 */

	public static void mostraCercaBooleanaOrdreAntPresencia() {
		Vector <String> tot = controlador.mostraCercaBooleanaOrdreAnt();
		Vector <String> totrev = new Vector<String>();
    	for (int i = tot.size() - 1; i >= 0; i--) {
    		totrev.add(tot.get(i));
    	}
    	cercaBooleana cb = new cercaBooleana(totrev);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana
	 */

	public static void mostraCercaBooleanaPresencia() {
		Vector <String> tot = controlador.mostraCercaBooleana();
		cercaBooleana cb = new cercaBooleana(tot);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana ordenat per longitud de major a menor
	 */

	public static void mostraCercaBooleanaOrdreGranPresencia() {
		Vector <String> tot = controlador.mostraCercaBooleanaOrdreGran();
		String[] arr = tot.toArray(new String[tot.size()]); //vector to array
		int n = tot.size();
		Arrays.sort(arr, Comparator.comparing(s->s.length()));
		Vector<String> vector = new Vector<String>(Arrays.asList(arr));
		
		Vector <String> totrev = new Vector<String>();
    	for (int i = tot.size() - 1; i >= 0; i--) {
    		totrev.add(vector.get(i));
    	}
		
		cercaBooleana cb = new cercaBooleana(totrev);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana ordenat per logitud de menor a major
	 */

	public static void mostraCercaBooleanaOrdrePetitPresencia() {
		Vector <String> tot = controlador.mostraCercaBooleanaOrdrePetit();
		String[] arr = tot.toArray(new String[tot.size()]); //vector to array
		int n = tot.size();
		Arrays.sort(arr, Comparator.comparing(s->s.length()));
		Vector<String> vector = new Vector<String>(Arrays.asList(arr));
		cercaBooleana cb = new cercaBooleana(vector);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana ordenada alfabeticament per autors
	 * @param operacio2
	 * String que conte la query
	 * @param result2
	 * Resultat ordenat
	 */

	public static void mostraResultCercaBooleanaOrdreAlfAutPresencia(String operacio2, Set<String> result2) {
		SortedSet<String> resultord = new TreeSet <String> ();
		resultord.addAll(result2);
		resultatCercaBooleana rcb = new resultatCercaBooleana(operacio2,resultord);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca booleana ordenada alfabeticament per titols
	 * @param operacio2
	 * String que conte la query
	 * @param result2
	 * Resultat ordenat
	 */

	public static void mostraResultCercaBooleanaOrdreAlfTitPresencia(String operacio2, Set<String> result2) {
		SortedSet<String> resultord = new TreeSet <String> ();
		for(String x : result2) {
			String[] resOfStr = x.split("-",-2);
			String aux = resOfStr[1]+"-"+resOfStr[0];
			resultord.add(aux);
	    }
		
		Set<String> result3 = new LinkedHashSet<String> ();
		for (String y : resultord) {
			String[] resOfStr = y.split("-",-2);
			String aux = resOfStr[1]+"-"+resOfStr[0];
			result3.add(aux);
		}
		
		resultatCercaBooleana rcb = new resultatCercaBooleana(operacio2,result3);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca d'autors
	 * @param autor
	 * Nom del autor a buscar
	 */

	public static void buscaAutorsPresencia(String autor) {
		String resultat = controlador.buscaAutors(autor);
		resultatCercaAutors r = new resultatCercaAutors(resultat,autor);
	}
	/**
	 * 
	 * Funcio que mostra el resultat de la cerca k Rellevants
	 * @param par
	 * Conjunt de paraules	
	 * @param number
	 * Numero de documents rellevants que es desitja buscar
	 */

	public static void cercaRellevantsPresencia(String par, int number) {
		TreeMap <String, Float> resultat2 = controlador.cercaRellevants(par, number);
		resultatCercaRellevants c = new resultatCercaRellevants(par,number,resultat2);
	}
	/**
	 * Funcio que mostra el resultat en ordre alfabetic ascendent de la cerca per prefix d'autors
	 * 
	 * @param result
	 * Resultat de la cerca original
	 * @param autor
	 * Prefix que s'ha buscat en la cerca original
	 */

	public static void mostraResultOrdreAlfPrefA(String result, String autor) {
		String[] lines = result.split("\n");

		Arrays.sort(lines);		
		String result2 = "";
		for(String y: lines) {  
		  result2 += y +"\n";
		  
		}
		
		resultatCercaAutors rca = new resultatCercaAutors(result2,autor);
	}
	/**
	 * 
	 * Funcio que mostra el resultat en ordre alfabetic descendent de la cerca per prefix d'autors
	 * @param result
	 * Resultat de la cerca original
	 * @param autor
	 * Prefix que s'ha buscat en la cerca original
	 */

	public static void mostraResultOrdreAlfPrefD(String result, String autor) {
		String[] lines = result.split("\n");
		
		Arrays.sort(lines, Collections.reverseOrder());   
		String result3 = "";
		for (String x : lines) {
			result3 += x + "\n";
		}
		
		resultatCercaAutors rca = new resultatCercaAutors(result3,autor);
	}
	/**
	 * 
	 * Funcio que ens permet evaluar l'expressio booleana introduida per l'usuari
	 * @param eval
	 */

	public static void evalPresencia(String eval) {
		
		Set<String> result = new HashSet<String>();
		
		result = controlador.eval(eval);
		
		resultatCercaBooleana rcb = new resultatCercaBooleana(eval,result);
	}
	/**
	 * 
	 * @param f
	 * @throws Exception
	 */
	public static void carregaDocumentsPresencia(File f) throws Exception {
		controlador.carregaDocuments(f);
	}
	/**
	 * 
	 * Funcio que ens permet fer la serca de documents semblants a traves del controlador de domini
	 * @param tit
	 * Titol del document
	 * @param aut
	 * Autor del document
	 * @param number
	 * Numero de documents semblants al titol i autor passats per parametre
	 */

	public static void cercaSemblantsPresencia(String tit, String aut, int number) {
		controlador.cercaSemblants(tit,aut,number);
	}
	/**
	 * 
	 * Funcio que ens permet veure el contingut d'un document a traves del controlador de domini
	 * @param titol
	 * Titol del document a veure el seu contingut
	 * @param autor
	 * Autor del document a veure el seu contingut
	 */

	public static void viewContingutDocumentPresencia(String titol, String autor) {
		controlador.viewContingutDocument(titol, autor);
	}
	/**
	 * 
	 * Funcio que ens permet buscar titols d'un autor a traves del controlador de domini
	 * @param autor
	 */

	public static void buscaTitolsAutorPresencia(String autor) {
		controlador.buscaTitolsAutor(autor);
	}
	/**
	 * 
	 * Funcio que ens permet carregar sessions a traves del controlador de domini
	 */

	public static void carregaSessioDocuements() {
		controlador.controlador();
	}
	/**
	 * 
	 * Funcio que ens permet guardar sessio a traves del controlador de domini
	 */

	public static void guardaSessio() {
		controlador.serialize();	
	}
	/**
	 * 
	 * Funcio que ens permet carregar sessio d'usuaris a traves del controlador d'usuaris
	 */

	public static void carregaSessioUsuaris() {
		controladorUsuaris.deSerializeUsers();
	}
	/**
	 * 
	 * Funcio que ens permet guardar usuaris a traves del controlador d'usuaris
	 */

	public static void guardaUsuaris() {
		controladorUsuaris.serializeUsers();
	}
	/**
	 * 
	 * Funcio que ens permet crear un nou document a traves del controlador de domini
	 * @param titol
	 * Titol del nou document a crear
	 * @param autor
	 * Autor del nou document a crear
	 * @param contingut
	 * Contingut del nou document a crear
	 */

	public static void CreaNouDocumentPresencia(String titol, String autor, String contingut) {
		controlador.CreaNouDocument(titol,autor,contingut);
	}
	/**
	 * 
	 * Funcio que ens permet crear un nou document en format Xml a traves del controlador de domini
	 * @param contingut
	 * Contingut del nou document a crear
	 * @return
	 */

	public static boolean CreaNouDocumentXMLPresencia(String contingut) {
		return controlador.CreaNouDocumentXML(contingut);
	}
	/**
	 * 
	 * Funcio que ens permet eliminar un document
	 * @param t
	 * Titol del document a eliminar
	 * @param a
	 * Autor del document a eliminar
	 * @return
	 * Retorna cert si es crear el document, altrament false
	 */

	public static boolean setEliminaDocumentPresencia(String t, String a) {
		return controlador.setEliminaDocument(t,a);
	}
	/**
	 * 
	 * Funcio que ens permet exportar un document a traves del controlador de domini
	 * @param f
	 * 
	 * @param titol
	 * Titol del document a exportar
	 * @param autor
	 * Autor del document a exportar
	 * @param filename
	 * 
	 * @throws IOException
	 * @throws fileNotFoundException
	 */

	public static void exportaDocumentPresencia(File f, String titol, String autor, String filename) throws IOException, fileNotFoundException {
		controlador.exportaDocument(f,titol,autor,filename);
		mostraOKexportat okex = new mostraOKexportat();
	}
	/**
	 * 
	 * Funcio que ens permet controlar el login d'usuaris a traves del controlador de domini
	 * @param name
	 * Nom del usuari
	 * @param pas
	 * Contrasenya del usuari
	 * @return
	 * Retorna true si les credencials son correctes, altrament false
	 */

	public static boolean controlaLoginPresencia(String name, String pas) {
		return controlador.controlaLoginDomini(name, pas);
	}
	/**
	 * 
	 * Funcio que ens permet modificar un document a traves del controlador de domini
	 * @param t
	 * Titol del document a modificar
	 * @param a
	 * Autor del document a modificar
	 * @param text
	 * Nou contingut del document
	 */

	public static void modificaDocumentPresencia(String t, String a, String text) {
		controlador.modificaDocument(t,a,text);
	}
	/**
	 * 
	 * Funcio que ens permet comprovar si existeix un document donat un titol i un autor
	 * @param t
	 * Titol del document
	 * @param a
	 * Autor del document
	 * @return
	 * Retorna cert si existeix, altrament false
	 */

	public static void setModificaTextPresencia(String t, String a) {
		controlador.setModificaText(t,a);
	}
	/**
	 * 
	 * Funcio que ens permet modificar doc creador a traves del controlador de domini
	 * @throws fileNotFoundException
	 */

	public static boolean existeixPresencia(String t, String a) {
		return controlador.existeix(t,a);
	}
	/**
	 * 
	 * @throws fileNotFoundException
	 */
	public static void mostraModificarDocCreadorPresencia() throws fileNotFoundException {
		controlador.mostraModificarDocCreador();
	}
	/**
	 * 
	 * Funcio que ens permet crear un nou usuari a traves del controlador de domini
	 * @param name
	 * Nom del nou usuari
	 * @param pas
	 * Contrasenya del nou usuari
	 * @return
	 * Retorna cert si es crea, altrament false
	 */

	public static boolean newUsuariPresencia(String name, String pas) {
		return controlador.newUsuariDomini(name,pas);
	}
	/**
	 * 
	 * Funcio que ens permet fer login a l'aplicacio
	 */

	public static void log() {
		login log = new login();
	}
	/**
	 * 
	 * Funcio que ens permet exportar tots els documents de l'aplicacio
	 */

	public static void mostraExportaTots() {
		exportaTots et = new exportaTots();
	}
	/**
	 * 
	 * Funcio que ens permet exportar tots els documents de l'aplicacio a traves del controlador de domini
	 * @param f
	 */

	public static void exportaTotsDocumentsPresencia(File f) {
		try {
			controlador.exportaTotsDocs(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Funcio que mostra la confirmacio de exportar tots els documents de l'aplicacio
	 */

	public static void totsOKexportats() {
		totsOKexportats toe = new totsOKexportats();
	}
	/**
	 * 
	 */
	public static void mostraNomesAdmin() {
		mostraNomesAdmin toe = new mostraNomesAdmin();
	}
	/**
	 * 
	 * Funcio que ens permet comprovar si l'usuari iniciat es l'admin a traves del controlador de domini
	 * @return
	 * Retorna cert si l'usuari iniciat es admin, altrament fals
	 */

	public static boolean usuariIniciatIsAdmin() {
		return controlador.usuariIniciatAdmin();
	}
	/**
	 * 
	 * Funcio que mostra la confirmacio de que s'ha esborrat tot el conjunt de documents de l'aplicacio
	 */

	public static void confirmacioEsborrarTot() {
		confirmacioEsborrarTot et = new confirmacioEsborrarTot();
	}
	/**
	 * 
	 * Funcio que ens permet esborrar tot el conjunt de documents de l'aplicacio a traves del controlador de domini
	 */

	public static void esborrarTot() {
		controlador.esborrarTot();
	}
	/**
	 * 
	 * Funcio que ens motra l'interficie per gestionar les restauracions
	 */

	public static void mostraGestorRestauracions() {
		gestorRestauracions gr = new gestorRestauracions();
	}
	/**
	 * 
	 * Funcio que ens motra l'interficie per fer un nou punt de restauracio
	 */

	public static void mostraFerPuntRes() {
		nouPuntRestauracio npr = new nouPuntRestauracio();
	}
	/**
	 * 
	 * Funcio que ens motra l'interficie de tots els punts de restauracio actuals del sistema
	 * @throws fileNotFoundException
	 */

	public static void mostraTornarPuntRes() throws fileNotFoundException {
		tornarPuntRestauracio tpr = new tornarPuntRestauracio();
	}
	/**
	 * 
	 * Funcio que ens mostra la confirmacio de tornar a un punt de restauracio
	 */

	public static void confirmaTornarPunt() {
		confirmaTornarPunt ctp = new confirmaTornarPunt();
	}
	/**
	 * 
	 * Funcio que ens permet carregar un punt de restauracio a traves del controlador de domini
	 * @param x
	 * Identificacio del punt de restauracio
	 */

	public static void carregaPuntRestauracioPres(String x) {
		controlador.carregaPuntRestauracio(x);
	}
	/**
	 * 
	 * Funcio que mostra l'interficie per eliminar usuaris
	 */

	public static void mostraEliminarUsuaris() {
		eliminarUsuaris eU = new eliminarUsuaris();
	}
	/**
	 * 
	 * Funcio que mostra l'error a l'usuari quan les credencials del login son incorrectes
	 */

	public static void mostraErrorLoginPres() {
		mostraErrorLogin me = new mostraErrorLogin();
	}
	/**
	 * 
	 * @param u
	 */
	public static void eliminaUsuari(String u) {
		if(controladorUsuaris.delete_usuari(u)) {
			mostrarOKeliminatUsuari moke = new mostrarOKeliminatUsuari();
		}
		else {
			error e = new error("errorUsari");
		}
	}
	
}
