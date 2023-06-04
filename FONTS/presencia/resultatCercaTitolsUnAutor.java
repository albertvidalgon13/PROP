package presencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.controladorPresencia;

import java.awt.*;

/**
 *  (Interfície) Classe que ens mostra el resultat de la cerca de tots els titols d'un autor donat el seu nom
 *  
 * @author prop23-subgrup5
 *
 */
public class resultatCercaTitolsUnAutor extends JFrame implements ActionListener{

    JTextArea label;
    JLabel cabecera;
    JButton botonRetroceder = new JButton("ENRERE");
   // JButton ordreAlfTitA = new JButton("ALF. ASC");
//    JButton ordreAlfTitD = new JButton("ALF. DESC");
    JButton ordena = new JButton("ORDENA");

    String resultat2;
    String autor2;
	JComboBox<String> combobox;

    /**
     * Constructora, crea la interfície
     * @param resultat
     * Resultat de la cerca
     * @param autor
     * Autor cercat
     */
	public resultatCercaTitolsUnAutor(String resultat, String autor) {
		setSize(800, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        
        JPanel pan = new JPanel();
        
        //setContentPane(pan);
        getContentPane().setBackground(new Color(44,53,59));
        
        cabecera = new JLabel("Aquests son els titols del autor: '" + autor + "'");
        cabecera.setBounds(120,50,500,20);
        cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
        cabecera.setForeground(Color.WHITE);
        add(cabecera);
        
		combobox = new JComboBox();
		combobox.addItem("ORDRE ALFABETIC ASCENDENT");
		combobox.addItem("ORDRE ALFABETIC DESCENDENT");

		
		combobox.setBounds(120,110,300,25);
		
		combobox.setBackground(Color.WHITE);
		
		add(combobox);
	    ordena.setBounds(450,110,150,25);
	    ordena.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordena.setBackground(Color.WHITE);
	    add(ordena);
	    ordena.addActionListener(this);
        
        
 /*       ordreAlfTitA.setBounds(600,225,150,30);
	    ordreAlfTitA.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreAlfTitA.setBackground(Color.WHITE);
	    add(ordreAlfTitA);
	    ordreAlfTitA.addActionListener(this);
        
	    ordreAlfTitD.setBounds(600,275,150,30);
	    ordreAlfTitD.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreAlfTitD.setBackground(Color.WHITE);
	    add(ordreAlfTitD);
	    ordreAlfTitD.addActionListener(this);*/
        
        label = new JTextArea (15,40);
        label.setText(resultat);
	    label.setLineWrap(true);
	    label.setWrapStyleWord(true);
	    label.setEditable(false);

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
        
        resultat2 = resultat;
        autor2 = autor;
        
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
	    	if (ordermode.equals("ORDRE ALFABETIC ASCENDENT")) {
	    		controladorPresencia.mostraResultOrdreAlfTitA(resultat2, autor2);
		    	setVisible(false);
	    	}
	    	else if (ordermode.equals("ORDRE ALFABETIC DESCENDENT")) {
	    		controladorPresencia.mostraResultOrdreAlfTitD(resultat2, autor2);
		    	setVisible(false);
	    	}
 	 }
    }
}
