package presencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.*;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que ens confirma l'esborrat d'un document
 * 
 * @author prop23-subgrup5
 *
 */
public class confirmacionEsborrat extends JFrame implements ActionListener{
	
	JLabel text = new JLabel("Estas segur que vols eliminar el document?");
	JButton esborrar = new JButton("ESBORRAR");
	JButton enrrere = new JButton("ENRERE");
	
	/**
	 * Constructora, crea la interfície
	 */
	public confirmacionEsborrat() {
		setSize(500, 200);
       setLocationRelativeTo(null);
       setLayout(null);
		
       text.setBounds(120,25,400,30);
	   add(text);
  
	   esborrar.setBounds(25,100,200,30);
	   add(esborrar);
	   esborrar.addActionListener(this);

	   enrrere.setBounds(250,100,200,30);
	   add(enrrere);
	   enrrere.addActionListener(this);
	  
	   setVisible(true);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }
   
   @Override
   /**
    * Metode per tancar la APP
    */
   public void dispose() {
		controladorPresencia.mostraConfirmacioSortida();
		setVisible(false);
   }
	
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==esborrar) {
    		//controlador.setEliminaDocument(t,a);
        }
    	if (e.getSource()==enrrere) {
    		try {
				controladorPresencia.mostraModDoc();
			} catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		setVisible(false);
        }
    }

}
