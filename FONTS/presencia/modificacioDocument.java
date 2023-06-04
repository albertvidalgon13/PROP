package presencia;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controladors.controladorPresencia;
import exceptions.fileNotFoundException;

import javax.swing.*;

import controladors.controlador;

/**
 * (Interfície) Classe que ens llista tots els documents i ens permet modificar o esborrar un document donat el seu títol i autor
 * 
 * @author prop23-subgrup5
 *
 */
public class modificacioDocument extends JFrame implements ActionListener{
	
	JTextArea label;
	JButton submit;
	JButton retro;
	
	String t;
	String a;
	
	/**
	 * Constructora, crea la interfície
	 * @param contenido
	 * Contingut actual del document a modificar
	 * @param titol
	 * Titol del document a modificar
	 * @param autor
	 * Autor del document a modificar
	 */
	public modificacioDocument(String contenido, String titol, String autor){	
			a = autor;
			t = titol;
		 	setSize(1000, 800);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        setResizable(false);
	        getContentPane().setBackground(new Color(44,53,59));
	        	        
	        JLabel labeltitol = new JLabel("Titol: " + titol);
	        JLabel labelautor = new JLabel("Autor: " + autor);

	        labeltitol.setBounds(120,0,700,200);
	        labeltitol.setFont(new Font("Dialog", Font.PLAIN, 18));
	        labeltitol.setForeground(Color.WHITE);
	        add(labeltitol);
	        
	        labelautor.setBounds(120,30,700,200);
	        labelautor.setFont(new Font("Dialog", Font.PLAIN, 18));
	        labelautor.setForeground(Color.WHITE);
	        add(labelautor);
	        
	        JPanel pan = new JPanel();
	        pan.setBackground(new Color(44,53,59));
			label = new JTextArea (30,65);
		    label.setBounds(100,200,500,250);
		    label.setLineWrap(true);
		    label.setWrapStyleWord(true);

		    label.setText(contenido);
		    
		    pan.add(label);

	        JScrollPane sp = new JScrollPane(label);
	        pan.add(sp);
	        
	        
	        pan.setBounds(100,150,750,470);
		    add(pan);

		    submit = new JButton("GUARDAR");
		    submit.setBounds(335,635,300,30);
	        submit.setFont(new Font("Dialog", Font.PLAIN, 14));
	        submit.setBackground(Color.WHITE);
		    add(submit);
	        submit.addActionListener(this);

		    retro = new JButton("ENRERE");
		    retro.setBounds(335,675,300,30);
	        retro.setFont(new Font("Dialog", Font.PLAIN, 14));
	        retro.setBackground(Color.WHITE);
		    add(retro);
	        retro.addActionListener(this);

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
    		String text = label.getText();
    		//System.out.print(text);
    		controladorPresencia.modificaDocumentPresencia(t,a,text);
    		controladorPresencia.mostraConfirmMod();
    		setVisible(false);
    		
    	}
		if (e.getSource()==retro) {
    		try {
				controladorPresencia.mostraModDoc();
			} catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		setVisible(false);
    	}
    }
	
	
}
