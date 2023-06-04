package presencia;

import controladors.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;


/**
 * (Interfície) Classe que ens permet crear un document mitjançant un títol, autor i contingut
 * 
 * @author prop23-subgrup5
 *
 */
public class creaDoc extends JFrame implements ActionListener{
	
	
	JLabel titolLabel = new JLabel("TITOL:");
	JLabel autorLabel = new JLabel("AUTOR:");
	JLabel contingutLabel = new JLabel("CONTINGUT:");
	
	
    JTextField textTitol = new JTextField(95);
    JTextField textAutor = new JTextField(95);
    JTextArea textContingut = new JTextArea(30,75);
    
    JButton submit = new JButton("PUJAR DOCUMENT");
    JButton botonRetroceder = new JButton("ENRERE");
    
    /**
     * Constructora, crea la interfície
     */
	public creaDoc() {
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
        setResizable(false);
      
        titolLabel.setBounds(100,25,100,30);
        titolLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        titolLabel.setForeground(Color.WHITE);
        add(titolLabel);
        textTitol.setBounds(100,50,830,30);
        textTitol.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(textTitol);
       
        autorLabel.setBounds(100,80,100,30);
        autorLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        autorLabel.setForeground(Color.WHITE);
        add(autorLabel);
        textAutor.setBounds(100,105,830,30);
        textAutor.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(textAutor);
        
        contingutLabel.setBounds(100,135,250,30);
        contingutLabel.setForeground(Color.WHITE);
        contingutLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(contingutLabel);
        
        JPanel pan = new JPanel();
        pan.setBounds(65,155,900,475);
        pan.setBackground(new Color(44,53,59));
        textContingut.setLineWrap(true);
        textContingut.setWrapStyleWord(true);
        textContingut.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        pan.add(textContingut);
        
          
        JScrollPane sp = new JScrollPane(textContingut);
        pan.add(sp);
        add(pan);
      
        submit.setBounds(425,650,200,30);
        submit.setFont(new Font("Dialog", Font.PLAIN, 14));
        submit.setBackground(Color.WHITE);
        add(submit);
        submit.addActionListener(this);
        
        botonRetroceder.setBounds(425,690,200,30);
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
    	if (e.getSource()==submit) {
        	String titol = textTitol.getText();	
        	String autor = textAutor.getText();
        	String contingut = textContingut.getText();
        	
        	if (!titol.isEmpty() && !autor.isEmpty()) {
        		if (!contingut.isEmpty()) {
        			controladorPresencia.CreaNouDocumentPresencia(titol,autor,contingut);
        		
        			setVisible(false);
        		}
        		//textTitol.setText("");textAutor.setText("");textContingut.setText("");
        	}
        }
    	if (e.getSource()==botonRetroceder) {
    		controladorPresencia.mostraAltaDoc();
    		setVisible(false);
    	}
    }
}
