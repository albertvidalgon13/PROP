package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.controlador;
import controladors.controladorPresencia;


/**
 * (Interfície) Classe que ens permet cercar el contingut d'un document mitjançant el seu títol i autor
 * 
 * @author prop23-subgrup5
 *
 */
public class cercaTitAut extends JFrame implements ActionListener {

    JTextField textField1 = new JTextField(25);
    JTextField textField2 = new JTextField(25);
    
    JLabel label1 = new JLabel("Titol");
    JLabel label2 = new JLabel("Autor");
    
    JButton submit = new JButton("CERCA");
    JButton retroceder = new JButton("ENRERE");

    /**
     * Constructora, crea la interfície
     */
	public cercaTitAut() {		 
		setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        
        getContentPane().setBackground(new Color(44,53,59));
        
		label1.setBounds(75,75,100,30);
        label1.setFont(new Font("Dialog", Font.PLAIN, 18));
        label1.setForeground(Color.WHITE);
	    add(label1);
	        
	    textField1.setBounds(75,100,150,30);
	    add(textField1);
	    textField1.addActionListener(this);
	        
	    label2.setBounds(75,135,100,30);
        label2.setFont(new Font("Dialog", Font.PLAIN, 18));
        label2.setForeground(Color.WHITE);
	    add(label2);
	        
	    textField2.setBounds(75,160,150,30);
	    add(textField2);
	    textField2.addActionListener(this);
	    
	    submit.setBounds(75,210,150,30);
	    submit.setFont(new Font("Dialog", Font.PLAIN, 14));
	    submit.setBackground(Color.WHITE);
	    add(submit);
	    submit.addActionListener(this);
	    
	    retroceder.setBounds(75,260,150,30);
	    retroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
        retroceder.setBackground(Color.WHITE);
	    add(retroceder);
	    retroceder.addActionListener(this);
	    
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
	    if (e.getSource()==submit) {
	    	String titol = textField1.getText();
	    	String autor = textField2.getText();
	    	if (!titol.isEmpty() && !autor.isEmpty()) {
	    		controladorPresencia.viewContingutDocumentPresencia(titol, autor);
		    	setVisible(false);
	    	}
	    }
	    if (e.getSource()==retroceder) {
	    	controladorPresencia.mostraCerca();
	    	setVisible(false);
	    }
	}
}
