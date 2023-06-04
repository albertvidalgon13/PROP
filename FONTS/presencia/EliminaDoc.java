package presencia;
import controladors.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import controladors.controladorPresencia;
import exceptions.fileNotFoundException;


/**
 * (Interfície) Classe que ens permet decidir si volem esborrar o no un document
 * 
 * @author prop23-subgrup5
 *
 */
public class EliminaDoc extends JFrame  implements ActionListener{

	JLabel text;
	JButton eliminar = new JButton("ELIMINAR");
	JButton volverMenu = new JButton("TORNAR AL MENU");
	
	String t;
	String a;
	/**
	 * Constructora, crea la interfície
	 * 
	 * @param titol
	 * títol del document a borrar
	 * 
	 * @param autor
	 * autor del document a borrar
	 */
	public EliminaDoc(String titol, String autor) {
		t = titol;
		a = autor;
		setSize(500, 200);
       setLocationRelativeTo(null);
       setLayout(null);
       getContentPane().setBackground(new Color(44,53,59));
       
       JLabel text = new JLabel("Estas segur que vols eliminar aquest document?"); 
       text.setBounds(80,25,400,30);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text);
       JLabel text2 = new JLabel("Titol: "+ titol); 
       text2.setBounds(80,45,400,30);
       text2.setForeground(Color.WHITE);
       text2.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text2);
       JLabel text3 = new JLabel("Autor: "+ autor); 
       text3.setBounds(80,65,400,30);
       text3.setForeground(Color.WHITE);
       text3.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text3);
  
	   eliminar.setBounds(40,100,200,30);
	   eliminar.setFont(new Font("Dialog", Font.PLAIN, 14));
	   eliminar.setBackground(Color.WHITE);
	   add(eliminar);
	   eliminar.addActionListener(this);

	   volverMenu.setBounds(270,100,200,30);
	   volverMenu.setFont(new Font("Dialog", Font.PLAIN, 14));
	    volverMenu.setBackground(Color.WHITE);
	   add(volverMenu);
	   volverMenu.addActionListener(this);
	  
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
    	if (e.getSource()==eliminar) {
    		if (controladorPresencia.setEliminaDocumentPresencia(t,a)) {
    			try {
    				controladorPresencia.mostraModDoc();
    			} 
    			catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
    			}
    		}

	    	setVisible(false);
    		
        }
    	if (e.getSource()==volverMenu) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);
        }
    }
}
