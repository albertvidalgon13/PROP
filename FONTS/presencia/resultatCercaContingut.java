package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.controladorPresencia;

/**
 * (Interfície) Classe que ens mostra el resultat de la cerca del contingut d'un document
 * 
 * @author prop23-subgrup5
 *
 */

public class resultatCercaContingut extends JFrame implements ActionListener{

	JTextArea label;
	JLabel cabecera;
	JLabel cabecera2;
	JLabel cabecera3;
	JButton botonRetroceder = new JButton("ENRERE");
	/**
	 * Constructora, crea la interfície
	 * @param c
	 * Contingut, resultat de la cerca
	 * @param autor
	 * Autor del document buscat
	 * @param titol
	 * Títol del document buscat
	 */
	public resultatCercaContingut(String c, String autor, String titol) {

			setSize(800, 650);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        setResizable(false);
	        
	        JPanel pan = new JPanel();
	        
	        //setContentPane(pan);
	        getContentPane().setBackground(new Color(44,53,59));
	        
	        cabecera = new JLabel("Aquest es el contingut del document amb: ");
	        cabecera.setBounds(50,50,500,20);
	        cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
	        cabecera.setForeground(Color.WHITE);
	        add(cabecera);
	        cabecera2 = new JLabel("Titol: " + titol);
	        cabecera2.setBounds(50,75,500,20);
	        cabecera2.setFont(new Font("Dialog", Font.PLAIN, 18));
	        cabecera2.setForeground(Color.WHITE);
	        add(cabecera2);
	        cabecera3 = new JLabel("Autor: " + autor);
	        cabecera3.setBounds(50,100,500,20);
	        cabecera3.setFont(new Font("Dialog", Font.PLAIN, 18));
	        cabecera3.setForeground(Color.WHITE);
	        add(cabecera3);
	        
	       
	        label = new JTextArea (25,63);
	        label.setText(c);
		    label.setLineWrap(true);
		    label.setWrapStyleWord(true);
		    label.setEditable(false);

		    pan.add(label);        
	        JScrollPane sp = new JScrollPane(label);

	        pan.add(sp);
	        pan.setBackground(new Color(44,53,59));
	        pan.setBounds(40,150,720,400);
	        add(pan);
	        
	        botonRetroceder.setBounds(300,550,200,30);
	        botonRetroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonRetroceder.setBackground(Color.WHITE);
	        add(botonRetroceder);
	        botonRetroceder.addActionListener(this); 
	        
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
	    	if (e.getSource()==botonRetroceder) {
	    		controladorPresencia.mostraMenu();
	    		setVisible(false);	
	        }
	    }
}
