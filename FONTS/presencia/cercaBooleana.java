package presencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.controlador;
import controladors.controladorPresencia;

import java.util.*;

/**
 * (Interfície) Classe que ens permet fer la cerca booleana
 * 
 * @author prop23-subgrup5
 *
 */

public class cercaBooleana extends JFrame implements ActionListener {
	
	JLabel cabecera = new JLabel("CERCA BOOLEANA");
	JLabel cabecera2 = new JLabel("HISTORIC:");
	//JTextArea funcionament = new JTextArea(20,20);
	JTextField booleanexp = new JTextField(25);
    JButton submit = new JButton("CERCA");
    JButton ordena = new JButton("ORDENA");
    JButton retroceder = new JButton("ENRERE");
   /* JButton ordreAlf = new JButton("ALFABETIC");
    JButton ordreRec = new JButton("MES RECENT");
    JButton ordreAnt = new JButton("MES ANTIC");
    JButton ordreGran = new JButton("MES GRAN");
    JButton ordrePetit = new JButton("MES PETIT");
    JButton ordreNormal = new JButton("NORMAL"); */
	JTextArea label;
	
	JComboBox<String> combobox;
	
    /**
     * Constructora, crea la interfície
     * @param historic
     * Llista amb totes els operaions booleanes fetes servir per l'usuari
     */
    public cercaBooleana(Vector <String> historic) {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(new Color(44,53,59));
		   
		cabecera.setBounds(305,50,300,20);
		cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
		cabecera.setForeground(Color.WHITE);
		add(cabecera);
		cabecera2.setBounds(80,90,300,20);
		cabecera2.setFont(new Font("Dialog", Font.PLAIN, 18));
		cabecera2.setForeground(Color.WHITE);
		add(cabecera2);
		
		
		combobox = new JComboBox();
		combobox.addItem("ORDRE ALFABETIC");
		combobox.addItem("ORDRE MES RECIENT");
		combobox.addItem("ORDRE MES GRAN");
		combobox.addItem("ORDRE MES PETIT");
		
		combobox.setBounds(120,120,300,25);
		
		combobox.setBackground(Color.WHITE);
		
		add(combobox);
		
		
	    ordena.setBounds(450,120,150,25);
	    ordena.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordena.setBackground(Color.WHITE);
	    add(ordena);
	    ordena.addActionListener(this);
		
        JPanel pan = new JPanel();
        
       
        label = new JTextArea (18,55);;
	    label.setLineWrap(true);
	    label.setWrapStyleWord(true);
	    label.setEditable(false);
	    String total = "";
	    if (historic.isEmpty()) {
	    	total += "Encara no has fet ninguna Operacio Boolena";
	    }
	    else {
		    for(String x : historic) {
		    	total+=x+"\n";
		    }
	    }
	    label.setText(total);
	    pan.add(label);        
        JScrollPane sp = new JScrollPane(label);
       
        pan.add(sp);
        pan.setBackground(new Color(44,53,59));
        pan.setBounds(60,150,650,300);
        add(pan);
		
		booleanexp.setBounds(75,450,625,30);
		booleanexp.setHorizontalAlignment(JTextField.CENTER);
		add(booleanexp);
		booleanexp.addActionListener(this);
		
	    submit.setBounds(230,500,150,30);
	    submit.setFont(new Font("Dialog", Font.PLAIN, 14));
	    submit.setBackground(Color.WHITE);
	    add(submit);
	    submit.addActionListener(this);
	    
	    retroceder.setBounds(400,500,150,30);
	    retroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
        retroceder.setBackground(Color.WHITE);
	    add(retroceder);
	    retroceder.addActionListener(this);
	    
	  /*  ordreAlf.setBounds(600,125,150,30);
	    ordreAlf.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreAlf.setBackground(Color.WHITE);
	    add(ordreAlf);
	    ordreAlf.addActionListener(this);
	    
	    ordreRec.setBounds(600,175,150,30);
	    ordreRec.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreRec.setBackground(Color.WHITE);
	    add(ordreRec);
	    ordreRec.addActionListener(this);
	    
	    ordreAnt.setBounds(600,225,150,30);
	    ordreAnt.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreAnt.setBackground(Color.WHITE);
	    add(ordreAnt);
	    ordreAnt.addActionListener(this);
	    
	    ordreGran.setBounds(600,275,150,30);
	    ordreGran.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreGran.setBackground(Color.WHITE);
	    add(ordreGran);
	    ordreGran.addActionListener(this);
	    
	    ordrePetit.setBounds(600,325,150,30);
	    ordrePetit.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordrePetit.setBackground(Color.WHITE);
	    add(ordrePetit);
	    ordrePetit.addActionListener(this);
	    
	    ordreNormal.setBounds(600,375,150,30);
	    ordreNormal.setFont(new Font("Dialog", Font.PLAIN, 14));
	    ordreNormal.setBackground(Color.WHITE);
	    add(ordreNormal);
	    ordreNormal.addActionListener(this);*/
	    
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
	    	String evalString = booleanexp.getText();
	    	String evalString2 = evalString;
	    	evalString2 = evalString2.replaceAll(" ", "");
	    	if (!evalString.isEmpty() && !evalString2.isEmpty()) {
	    		controladorPresencia.evalPresencia(evalString);	
		    	setVisible(false);
	    	}
	    	booleanexp.setText("");
	    	
	    }
	    if (e.getSource()==retroceder) {
	    	controladorPresencia.mostraCerca();
	    	setVisible(false);
	    }
	    
	    if (e.getSource()==ordena) {
	    	String ordermode = (String)combobox.getSelectedItem();
	    	if (ordermode.equals("ORDRE ALFABETIC")) {
		    	controladorPresencia.mostraCercaBooleanaOrdreAlfPresencia();
		    	setVisible(false);
	    	}
	    	else if (ordermode.equals("ORDRE MES RECIENT")) {
		    	controladorPresencia.mostraCercaBooleanaOrdreAntPresencia();
		    	setVisible(false);
	    	}
	  /*  	else if (ordermode.equals("ORDRE MES ANTIC")) {
		    	controladorPresencia.mostraCercaBooleanaPresencia();
		    	setVisible(false);
	    	}*/
	    	else if (ordermode.equals("ORDRE MES GRAN")) {
		    	controladorPresencia.mostraCercaBooleanaOrdreGranPresencia();
		    	setVisible(false);
	    	}
	    	else if (ordermode.equals("ORDRE MES PETIT")) {
		    	controladorPresencia.mostraCercaBooleanaOrdrePetitPresencia();
		    	setVisible(false);
	    	}
	 /*   	else if (ordermode.equals("ORDRE MES NORMAL")) {
		    	controladorPresencia.mostraCercaBooleanaOrdreAntPresencia();
		    	setVisible(false);
	    	}*/
	    	
	    }
	    
	/*    if (e.getSource()==ordreAlf) {
	    	controladorPresencia.mostraCercaBooleanaOrdreAlfPresencia();
	    	setVisible(false);
	    }
	    if (e.getSource()==ordreRec) {
	    	controladorPresencia.mostraCercaBooleanaOrdreAntPresencia();
	    	setVisible(false);
	    }
	    if (e.getSource()==ordreAnt) {
	    	controladorPresencia.mostraCercaBooleanaPresencia();
	    	setVisible(false);
	    }
	    if (e.getSource()==ordreGran) {
	    	controladorPresencia.mostraCercaBooleanaOrdreGranPresencia();
	    	setVisible(false);
	    }
	    if (e.getSource()==ordrePetit) {
	    	controladorPresencia.mostraCercaBooleanaOrdrePetitPresencia();
	    	setVisible(false);
	    }
	    if (e.getSource()==ordreNormal) {
	    	controladorPresencia.mostraCercaBooleanaOrdreAntPresencia();
	    	setVisible(false);
	    }*/
	}
	
}
