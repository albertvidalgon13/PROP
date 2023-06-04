package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import controladors.controladorPresencia;

/**
 * 
 * (Interfície) Classe per mostrar les diferents funcionalitats per pujar un document al sistema
 * 
 * @author prop23-subgrup5
 *
 */


public class altaDoc extends JFrame  implements ActionListener{
	
	JButton botonCargar = new JButton("CARREGAR DOCUMENT");
    JButton botonCrearTXT = new JButton("CREAR TXT");
    JButton botonCrearXML = new JButton("CREAR XML");
    JButton botonRetroceder = new JButton("ENRERE");
    JButton botonSalir = new JButton("SORTIR");
	
    /**
     * Constructora, crea la interfície
     */
    public altaDoc() {
		setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
        
        botonCargar.setBounds(80,50,230,30);
        botonCargar.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonCargar.setBackground(Color.WHITE);
        add(botonCargar);
        botonCargar.addActionListener(this);

        botonCrearTXT.setBounds(80,100,230,30);
        botonCrearTXT.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonCrearTXT.setBackground(Color.WHITE);
        add(botonCrearTXT);
        botonCrearTXT.addActionListener(this);
        
        botonCrearXML.setBounds(80,150,230,30);
        botonCrearXML.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonCrearXML.setBackground(Color.WHITE);
        add(botonCrearXML);
        botonCrearXML.addActionListener(this);
        
        botonRetroceder.setBounds(80,200,230,30);
        botonRetroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonRetroceder.setBackground(Color.WHITE);
        add(botonRetroceder);
        botonRetroceder.addActionListener(this);
       
        botonSalir.setBounds(80,250,230,30);
        botonSalir.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonSalir.setBackground(Color.WHITE);
        add(botonSalir);
        botonSalir.addActionListener(this);
        
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
    	if (e.getSource()==botonCrearTXT) {
    		controladorPresencia.mostraCreaDoc();
    		setVisible(false);	
        }
    	if (e.getSource()==botonCrearXML) {
    		controladorPresencia.mostraCreaDocXml();
    		setVisible(false);	
        }
    	if (e.getSource()==botonRetroceder) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);	
        }
    	if (e.getSource()==botonSalir) {
    		controladorPresencia.mostraConfirmacioSortida();
    		setVisible(false);
        }
    	if (e.getSource()==botonCargar) {
    		controladorPresencia.mostraCarregaDoc();
    		setVisible(false);
    	}
    }
}
