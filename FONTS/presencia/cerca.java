package presencia;
import controladors.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * (Interfície) Classe per mostrar els diferents tipus de cerca
 * 
 * @author prop23-subgrup5
 *
 */

public class cerca extends JFrame implements ActionListener{

	JButton cerca1 = new JButton ("CERCA PER TITOL I AUTOR");
	JButton cerca2 = new JButton ("CERCA PER TITOLS D'UN AUTOR");
	JButton cerca3 = new JButton ("CERCA AUTOR");
	JButton cerca4 = new JButton ("CERCA BOOLEANA");
	JButton cerca5 = new JButton ("CERCA DOCUMENTS SEMBLANTS");
	JButton cerca6 = new JButton ("CERCA DOCUMENTS MES RELLEVANTS");
	JButton retroceder = new JButton ("ENRERE");
	
	/**
	 * Constructora, crea la interfície
	 */
	public cerca() {
		setSize(450,500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));

        cerca1.setBounds(40,50,350,30);
        cerca1.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca1.setBackground(Color.WHITE);
        add(cerca1);
        cerca1.addActionListener(this);
        
        cerca2.setBounds(40,100,350,30);
        cerca2.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca2.setBackground(Color.WHITE);
        add(cerca2);
        cerca2.addActionListener(this);
        
        cerca3.setBounds(40,150,350,30);
        cerca3.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca3.setBackground(Color.WHITE);
        add(cerca3);
        cerca3.addActionListener(this);
        
        cerca4.setBounds(40,200,350,30);
        cerca4.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca4.setBackground(Color.WHITE);
        add(cerca4);
        cerca4.addActionListener(this);
        
        cerca5.setBounds(40,250,350,30);
        cerca5.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca5.setBackground(Color.WHITE);
        add(cerca5);
        cerca5.addActionListener(this);
            
        cerca6.setBounds(40,300,350,30);
        cerca6.setFont(new Font("Dialog", Font.PLAIN, 14));
        cerca6.setBackground(Color.WHITE);
        add(cerca6);
        cerca6.addActionListener(this);
        
        retroceder.setBounds(40,350,350,30);
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
    	if (e.getSource()==cerca1) {
    		controladorPresencia.mostraCercaTitolAutor();	
    		setVisible(false);
        }
    	if (e.getSource()==cerca2) {
    		controladorPresencia.mostraCercaTitolsUnAutor();	
    		setVisible(false);
        }
    	if (e.getSource()==cerca3) {
    		controladorPresencia.mostraCercaAutors();	
    		setVisible(false);
        }
    	if (e.getSource()==cerca4) {
    		controlador.mostraCercaBooleanaInit();	
    		setVisible(false);
        }
    	if (e.getSource()==cerca5) {
    		controladorPresencia.mostraCercaSimilars();	
    		setVisible(false);
        }
    	if (e.getSource()==cerca6) {
    		controladorPresencia.mostraKRellevants();	
    		setVisible(false);
        }
    	if (e.getSource()==retroceder) {
    		controladorPresencia.mostraMenu();	
    		setVisible(false);
        }
    	
    }

}

