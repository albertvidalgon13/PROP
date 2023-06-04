package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;


import javax.swing.*;

import controladors.controlador;
import controladors.controladorPresencia;

import java.util.*;

/**
 * (Interfície) Classe que ens mostra el resultat de la cerca booleana
 * 
 * @author prop23-subgrup5
 *
 */
public class resultatCercaBooleana extends JFrame implements ActionListener {
	JTextArea label;
	JLabel cabecera;
	JLabel cabecera2;
	JButton botonRetroceder = new JButton("ENRERE");
	//JButton ordreAlfAut = new JButton("ALF. AUTORS");
	//JButton ordreAlfTit = new JButton("ALF. TITOLS");
	JComboBox<String> combobox;
	String operacio2;
	Set<String> result2;
    JButton ordena = new JButton("ORDENA");

	
	/**
	 * Constructora, crea la interfície
	 * @param operacio
	 * Operacio intorduïda a la cerca
	 * @param result
	 * Llista amb el resultat de la cerca
	 */
	public resultatCercaBooleana(String operacio, Set<String> result) {
	    
	    
		
			setSize(800, 550);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        setResizable(false);
	        
	        JPanel pan = new JPanel();
	        
	        //setContentPane(pan);
	        getContentPane().setBackground(new Color(44,53,59));
	        
	        cabecera = new JLabel("Aquests son els documents (autor-titol) que compleixen la expressio booleana:");
	        cabecera.setBounds(50,35,700,20);
	        cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
	        cabecera.setForeground(Color.WHITE);
	        add(cabecera);
	       
	        cabecera2 = new JLabel(operacio);
	        cabecera2.setBounds(120,60,500,20);
	        cabecera2.setFont(new Font("Dialog", Font.PLAIN, 18));
	        cabecera2.setForeground(Color.WHITE);
	        add(cabecera2);
	        
			combobox = new JComboBox();
			combobox.addItem("ORDRE ALFABETIC AUTORS");
			combobox.addItem("ORDRE ALFABETIC TITOLS");

			
			combobox.setBounds(120,110,300,25);
			
			combobox.setBackground(Color.WHITE);
			
			add(combobox);
			
			
		    ordena.setBounds(450,110,150,25);
		    ordena.setFont(new Font("Dialog", Font.PLAIN, 14));
		    ordena.setBackground(Color.WHITE);
		    add(ordena);
		    ordena.addActionListener(this);
	        
	        
	 /*       ordreAlfAut.setBounds(600,175,150,30);
		    ordreAlfAut.setFont(new Font("Dialog", Font.PLAIN, 14));
		    ordreAlfAut.setBackground(Color.WHITE);
		    add(ordreAlfAut);
		    ordreAlfAut.addActionListener(this);
		    
		    ordreAlfTit.setBounds(600,225,150,30);
		    ordreAlfTit.setFont(new Font("Dialog", Font.PLAIN, 14));
		    ordreAlfTit.setBackground(Color.WHITE);
		    add(ordreAlfTit);
		    ordreAlfTit.addActionListener(this);*/
	        
	        label = new JTextArea (15,40);

		    label.setLineWrap(true);
		    label.setWrapStyleWord(true);
		    label.setEditable(false);
		    
		    String total = "";
		    for (String x : result) {
		    	total += x + "\n";
		    }
		    
		    label.setText(total);
		    
		    pan.add(label);        
	        JScrollPane sp = new JScrollPane(label);

	        pan.add(sp);
	        pan.setBackground(new Color(44,53,59));
	        pan.setBounds(125,150,475,265);
	        add(pan);
	        
	        botonRetroceder.setBounds(275,425,200,30);
	        botonRetroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonRetroceder.setBackground(Color.WHITE);
	        add(botonRetroceder);
	        botonRetroceder.addActionListener(this);
	        
	        operacio2 = operacio;
	        result2 = result;
	        
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
	    	
	    	 if (e.getSource()==ordena) {
	 	    	String ordermode = (String)combobox.getSelectedItem();
	 	    	if (ordermode.equals("ORDRE ALFABETIC AUTORS")) {
		    		controladorPresencia.mostraResultCercaBooleanaOrdreAlfAutPresencia(operacio2, result2);
			    	setVisible(false);
	 	    	}
	 	    	else if (ordermode.equals("ORDRE ALFABETIC TITOLS")) {
			    	controladorPresencia.mostraResultCercaBooleanaOrdreAlfTitPresencia(operacio2, result2);
			    	setVisible(false);
	 	    	}
	    	 }
	    	
	    /*	if (e.getSource()==ordreAlfAut) {
	    		controladorPresencia.mostraResultCercaBooleanaOrdreAlfAutPresencia(operacio2, result2);
		    	setVisible(false);
		    }
		    if (e.getSource()==ordreAlfTit) {
		    	controladorPresencia.mostraResultCercaBooleanaOrdreAlfTitPresencia(operacio2, result2);
		    	setVisible(false);
		    }*/
	    }
}

