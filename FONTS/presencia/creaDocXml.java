package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.controlador;
import controladors.controladorPresencia;

/**
 * (Interfície) Classe que ens permet crear un XML
 * @author  prop23-subgrup5
 *
 */
public class creaDocXml extends JFrame implements ActionListener{

	
	JLabel titolLabel = new JLabel("TITOL:");
	JLabel autorLabel = new JLabel("AUTOR:");
	JLabel contingutLabel = new JLabel("CONTINGUT:");
	JLabel formatLabel = new JLabel("Format XML: etiquetes <titol>,<autor> i <contingut> en aquest ordre (Tanqueu els tags amb el corresponent </>)");
	
    JTextField textTitol = new JTextField(95);
    JTextField textAutor = new JTextField(95);
    JTextArea textContingut = new JTextArea(32,75);
    
    JButton submit = new JButton("PUJAR DOCUMENT");
    JButton botonRetroceder = new JButton("ENRERE");
    
    /**
     * Constructora, crea la interfície
     */
	public creaDocXml() {
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
        setResizable(false);
        
        formatLabel.setBounds(175,50,800,30);
        formatLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        formatLabel.setForeground(Color.WHITE);
        add(formatLabel);
        
        JPanel pan = new JPanel();
        pan.setBounds(100,100,900,550);
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

	@Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==submit) {
        	String contingut = textContingut.getText();
        	
        	if (!contingut.isEmpty()) {
        		if (controladorPresencia.CreaNouDocumentXMLPresencia(contingut)) {
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
