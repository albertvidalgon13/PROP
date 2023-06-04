package presencia;
import controladors.*;
import exceptions.fileNotFoundException;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * (Interfície) Classe que ens permet escollir entre les funcionalitats de l'aplicació (alta document, modificació document o cerca)
 * 
 * @author prop23-subgrup5
 *
 */
public class menu extends JFrame implements ActionListener{
	    
	    JLabel label = new JLabel("MENU");
	    
	    JButton botonAltaDoc;
	    JButton botonElDoc;
	    JButton botonModDoc;
	    JButton botonCerca;
	    JButton botonSalir;
	    JButton botonRestuaracio;
	    JButton botonLogOut;
	    /**
	     * Constructora, crea la interfície
	     */
	    public menu() {
	    	setSize(400,450);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        getContentPane().setBackground(new Color(44,53,59));
	        
	        label.setBounds(170,25,100,30);
	        label.setFont(new Font("Dialog", Font.PLAIN, 18));
	        label.setForeground(Color.WHITE);
	        add(label);
	        
	        botonAltaDoc = new JButton("ALTA DOCUMENT");
		    botonElDoc = new JButton("ELIMINAR DOCUMENT");
		    botonModDoc = new JButton("GESTIO DE DOCUMENTS");
		    botonCerca = new JButton("FER UNA CERCA");
		    botonRestuaracio = new JButton("GESTIONS ADMINISTRATIVES");
		    botonLogOut = new JButton("LOG OUT");
		    botonSalir = new JButton("SORTIR");
	        
	        botonAltaDoc.setBounds(80,75,230,30);
	        botonAltaDoc.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonAltaDoc.setBackground(Color.WHITE);
	        botonAltaDoc.setBorder(BorderFactory.createBevelBorder(0));
	        add(botonAltaDoc);
	        botonAltaDoc.addActionListener(this);
	        
	        botonModDoc.setBounds(80,125,230,30);
	        botonModDoc.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonModDoc.setBackground(Color.WHITE);
	        botonModDoc.setBorder(BorderFactory.createBevelBorder(0));
	        add(botonModDoc);
	        botonModDoc.addActionListener(this);
	        
	        botonCerca.setBounds(80,175,230,30);
	        botonCerca.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonCerca.setBackground(Color.WHITE);
	        botonCerca.setBorder(BorderFactory.createBevelBorder(0));
	        add(botonCerca); 
	        botonCerca.addActionListener(this);
	        
	        botonRestuaracio.setBounds(80,225,230,30);
	        botonRestuaracio.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonRestuaracio.setBackground(Color.WHITE);
	        botonRestuaracio.setBorder(BorderFactory.createBevelBorder(0));
	        add(botonRestuaracio); 
	        botonRestuaracio.addActionListener(this);
	        
	        botonLogOut.setBounds(80,275,230,30);
	        botonLogOut.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonLogOut.setBackground(Color.WHITE);
	        botonLogOut.setBorder(BorderFactory.createBevelBorder(0));
	        add(botonLogOut);
	        botonLogOut.addActionListener(this);
	        
	        botonSalir.setBounds(80,325,230,30);
	        botonSalir.setFont(new Font("Dialog", Font.PLAIN, 14));
	        botonSalir.setBackground(Color.WHITE);
	        botonSalir.setBorder(BorderFactory.createBevelBorder(0));
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
	    	if (e.getSource()==botonAltaDoc) {
	        	controladorPresencia.mostraAltaDoc();
	    		setVisible(false);	
	        }
	    	if (e.getSource()==botonSalir) {
	    		controladorPresencia.mostraConfirmacioSortida();
	    		setVisible(false);
	    	}
	    	if (e.getSource()==botonCerca) {
	    		controladorPresencia.mostraCerca();
	    		setVisible(false);
	    	}
	    	if (e.getSource()==botonModDoc) {
	    		try {
					controladorPresencia.mostraModDoc();
				} catch (fileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		setVisible(false);
	    	}
	    	if (e.getSource()==botonLogOut) {
	    		controladorPresencia.guardaSessio();
	    		controladorPresencia.mostraLogIn();
	    		setVisible(false);
	    	}
	    	if (e.getSource()==botonRestuaracio) {
	    		if (controladorPresencia.usuariIniciatIsAdmin()) {
		    		controladorPresencia.mostraGestorRestauracions();
	    			setVisible(false);
	    		}
	    		else {
	    			controladorPresencia.mostraNomesAdmin();
	    			setVisible(false);
	    		}

	    	}
	    	//if (e.getSource()==botonElDoc) {
	    		//controladorPresencia.mostraElDoc();
	    		//setVisible(false);
	    	//}
	    }

}

