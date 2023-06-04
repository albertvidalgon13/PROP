package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controladors.controladorPresencia;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que ens permet gestionar els punts de restauracio de la l'aplicacio
 * @author  prop23-subgrup5
 *
 */

public class gestorRestauracions extends JFrame implements ActionListener {
    
    JLabel label = new JLabel("GESTOR DE RESTAURACIONS");
    
    JButton botonFerRes;
    JButton botonTornar;
    JButton botonEliminarUsuaris;
    JButton botonSalir;
    /**
     * Constructora, crea la interfície
     */
    public gestorRestauracions() {
    	setSize(450,325);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
        
        label.setBounds(80,25,300,30);
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        add(label);
        
        botonFerRes = new JButton("CREAR PUNT DE RESTAURACIO");
	    botonTornar = new JButton("TORNAR A PUNT DE RESTAURACIO");
	    botonEliminarUsuaris = new JButton("BORRAR USUARI");
	    botonSalir = new JButton("ENRERE");
        
	    botonFerRes.setBounds(82,75,255,30);
	    botonFerRes.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonFerRes.setBackground(Color.WHITE);
        botonFerRes.setBorder(BorderFactory.createBevelBorder(0));
        add(botonFerRes);
        botonFerRes.addActionListener(this);
        
        botonTornar.setBounds(82,125,255,30);
        botonTornar.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonTornar.setBackground(Color.WHITE);
        botonTornar.setBorder(BorderFactory.createBevelBorder(0));
        add(botonTornar);
        botonTornar.addActionListener(this);
        
        botonEliminarUsuaris.setBounds(82,175,255,30);
        botonEliminarUsuaris.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonEliminarUsuaris.setBackground(Color.WHITE);
        botonEliminarUsuaris.setBorder(BorderFactory.createBevelBorder(0));
        add(botonEliminarUsuaris);
        botonEliminarUsuaris.addActionListener(this);
        
        botonSalir.setBounds(82,225,255,30);
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
	@Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==botonSalir) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);
        }
    	if (e.getSource()==botonTornar) {
    		try {
				controladorPresencia.mostraTornarPuntRes();
			} catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		setVisible(false);
        }
    	if (e.getSource()==botonFerRes) {
    		controladorPresencia.mostraFerPuntRes();
    		setVisible(false);
        }
    	if (e.getSource()==botonEliminarUsuaris) {
    		controladorPresencia.mostraEliminarUsuaris();
    		setVisible(false);
        }
		
	}
}
