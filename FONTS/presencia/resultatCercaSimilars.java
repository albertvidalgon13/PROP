package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

import controladors.controladorPresencia;


/**
 * (Interfície) Classe que ens mostra el resultat de la cerca de documents similars
 * 
 * @author prop23-subgrup5
 *
 */
public class resultatCercaSimilars extends JFrame implements ActionListener{
	
	JLabel cabecera1;
	JLabel cabecera2;
	JLabel cabecera3;
	JButton botonRetroceder;
	
	/**
	 * Constructora, crea la interfície
	 * @param titol
	 * Titol del document buscat
	 * @param autor
	 * Autor del document buscat
	 * @param n
	 * Número de documents a buscar
	 * @param resultat
	 * Lluista amb els resultats de la cerca
	 */
	public resultatCercaSimilars(String titol, String autor, int n, TreeMap <String, Float> resultat) {
		
		setSize(800, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(44,53,59));
        cabecera1 = new JLabel("Aquests son els "+n+" documents mes semblants al document amb:");
        cabecera2 = new JLabel("Titol: "+titol);
        cabecera3 = new JLabel("Autor: "+autor);
        
        cabecera1.setBounds(50,50,650,30);
        cabecera2.setBounds(50,75,650,30);
        cabecera3.setBounds(50,100,650,30);
        cabecera1.setFont(new Font("Dialog", Font.PLAIN, 18));
        cabecera1.setForeground(Color.WHITE);
        cabecera2.setFont(new Font("Dialog", Font.PLAIN, 18));
        cabecera2.setForeground(Color.WHITE);
        cabecera3.setFont(new Font("Dialog", Font.PLAIN, 18));
        cabecera3.setForeground(Color.WHITE);
        add(cabecera1);
        add(cabecera2);
        add(cabecera3);
        
       	JTextArea texto = new JTextArea(20,45);
       	JPanel pan = new JPanel();
       	pan.setBackground(new Color(44,53,59));
 	   
       	texto.setLineWrap(true);
       	texto.setWrapStyleWord(true);
       	texto.setEditable(false);
        
       	int i = 0;
        
       	if (resultat.size() == 0) {
       		texto.append("No existeix cap document per comparar");
       	}
       	else {
	        for (Map.Entry<String, Float> entry : resultat.entrySet()){	
	        		
	        	Float similitud = entry.getValue();
		        String semblant = entry.getKey();
		        String display = (i+1)+". Similitud: "+similitud+", document: "+semblant+ ".\n"; 
		        texto.append(display);
	        	++i;
	        }
       		
       	}
        pan.add(texto);
        pan.setBounds(75,130,500,250);
        JScrollPane sp = new JScrollPane(texto);
        pan.add(sp);
        add(pan);
        
        botonRetroceder = new JButton("ENRERE");
        
	    botonRetroceder.setBounds(275,400,230,30);
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

	@Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==botonRetroceder) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);	
    	}
	}	
	
}
