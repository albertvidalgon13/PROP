package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import controladors.*;

/**
 * 
 * (Interfície) Classe que ens permet cercar Documents similars a un altre mitjançant el seu títol i autor
 * 
 * @author prop23-subgrup5
 *
 */

public class cercaKRellevants extends JFrame implements ActionListener{
	JSpinner spinner;
	JLabel cabecera = new JLabel("CERCA DOCUMENTS MES RELLEVANTS");
	JTextField paraules = new JTextField(25);
	JTextField num = new JTextField(25);
    JButton submit = new JButton("CERCA");
    JButton retroceder = new JButton("ENRERE");
	JLabel t = new JLabel("Paraules:");
	JLabel n = new JLabel("Numero de documents mes rellevants:");
    
	/**
	 * Constructora, crea la interfície
	 */
    public cercaKRellevants() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(new Color(44,53,59));
		   
		cabecera.setBounds(100,30,420,20);
		cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
		cabecera.setForeground(Color.WHITE);
		add(cabecera);

		t.setBounds(85,65,100,100);
		t.setFont(new Font("Dialog", Font.PLAIN, 18));
		t.setForeground(Color.WHITE);
		add(t);
		
		paraules.setBounds(175,100,350,30);
		add(paraules);
		
		
		n.setBounds(100,175,350,100);
		n.setFont(new Font("Dialog", Font.PLAIN, 18));
		n.setForeground(Color.WHITE);
		add(n);
		
		SpinnerModel sm = new SpinnerNumberModel(1,1,20,1);
		spinner = new JSpinner(sm);
		
		spinner.setBounds(400,210,50,30);
		add(spinner);
		
		
	    submit.setBounds(120,275,150,30);
	    submit.setFont(new Font("Dialog", Font.PLAIN, 14));
	    submit.setBackground(Color.WHITE);
	    add(submit);
	    submit.addActionListener(this);
	    
	    retroceder.setBounds(320,275,150,30);
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
	    	String par = paraules.getText();
	    	int number = (Integer) spinner.getValue();
	    	controladorPresencia.cercaRellevantsPresencia(par,number);
	    	setVisible(false);
	    }
	    if (e.getSource()==retroceder) {
	    	controladorPresencia.mostraCerca();
	    	setVisible(false);
	    }
	}
}
