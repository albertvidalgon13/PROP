package presencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import controladors.controlador;
import controladors.controladorPresencia;
import exceptions.fileNotFoundException;
/**
 * (Interfície) Classe que ens confirma que em modificat un document
 * 
 * @author prop23-subgrup5
 *
 */
public class mostraConfirmMod extends JFrame implements ActionListener{
	JLabel text = new JLabel("Documento modificat correctament");
	JButton enrrere = new JButton("ENRERE");
	
	/**
	 * creadora de la interficie
	 */
	public mostraConfirmMod() {
			setSize(500, 200);
	       setLocationRelativeTo(null);
	       setLayout(null);
	       getContentPane().setBackground(new Color(44,53,59));
	       text.setBounds(65,25,400,30);
	       text.setFont(new Font("Dialog", Font.PLAIN, 18));
	       text.setForeground(Color.WHITE);
		   add(text);

		   enrrere.setBounds(140,100,200,30);
		   enrrere.setFont(new Font("Dialog", Font.PLAIN, 14));
	       enrrere.setBackground(Color.WHITE);
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
