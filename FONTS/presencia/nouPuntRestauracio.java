package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controladors.controlador;
import controladors.controladorPresencia;

/**
 * (Interfície) Classe que ens permet crear un nou punt de restauracio
 * 
 * @author prop23-subgrup5
 *
 */

public class nouPuntRestauracio extends JFrame implements ActionListener{

    JTextField textField1 = new JTextField(25);
    
    JLabel label1 = new JLabel("CREAR NOU PUNT DE RESTAURACIO");
    JLabel label2 = new JLabel("Nom:");
    
    JButton submit = new JButton("CREAR");
    JButton retroceder = new JButton("ENRERE");
    
    /**
     * Constructora, crea la interfície
     */
	public nouPuntRestauracio() {		 
		setSize(425, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
	    
	    label1.setBounds(35,50,400,30);
	    label1.setFont(new Font("Dialog", Font.PLAIN, 18));
	    label1.setForeground(Color.WHITE);
        add(label1);
	    
	    label2.setBounds(125,100,100,30);
	    label2.setFont(new Font("Dialog", Font.PLAIN, 18));
	    label2.setForeground(Color.WHITE);
        add(label2);
        textField1.setBounds(125,125,150,30);
	    textField1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
	    add(textField1);
	    //textField1.addActionListener(this);

	    submit.setBounds(125,175,150,30);
	    submit.setFont(new Font("Dialog", Font.PLAIN, 14));
	    submit.setBackground(Color.WHITE);
	    add(submit);
	    submit.addActionListener(this);
	    
	    retroceder.setBounds(125,225,150,30);
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
	    if (e.getSource()==submit) {
	    	controlador.creaPuntRestauracio(textField1.getText());
	    }
	    if (e.getSource()==retroceder) {
	    	controladorPresencia.mostraMenu();
	    	setVisible(false);
	    }
	}
}
