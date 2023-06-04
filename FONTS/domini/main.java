package domini;


import java.io.File;
import java.io.IOException;

import controladors.controladorUsuaris;
import controladors.controlador;
import presencia.login;

/** 
 * 
 * (main) Classe principal, inici del projecte i de la interfÃ­cie
 * 
 * 
 * @author prop23-grup5
 *
 */

public class main {
	@SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
    	
    	//ConjuntDocuments.exchangeVals("(â€œhola adÃ©uâ€�)");
    	//ConjuntDocuments.cercaBooleana("{p1 p2 p3} & (â€œhola adÃ©uâ€� | pep) & !joan");
    	//login log = new login();
    	//controladorUsuaris.carregarUsuaris();
		
		//File f = new File(System.getProperty("user.dir"));
			
		
		//System.out.print(f.getAbsolutePath());
		//System.out.print(f.getParent());
    	controlador.log();
    }
}
