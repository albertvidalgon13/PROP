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

public class cercaSimilars extends JFrame implements ActionListener{
	JSpinner spinner;
	JLabel cabecera = new JLabel("CERCA DOCUMENTS SEMBLANTS");
	JTextField titol = new JTextField(25);
	JTextField autor = new JTextField(25);
	JTextField num = new JTextField(25);
    JButton submit = new JButton("CERCA");
    JButton retroceder = new JButton("ENRERE");
	JLabel t = new JLabel("Titol:");
	JLabel a = new JLabel("Autor:");
	JLabel n = new JLabel("Numero de documents semblants:");
    
	/**
	 * Constructora, crea la interfície
	 */
    public cercaSimilars() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(new Color(44,53,59));
		   
		cabecera.setBounds(150,30,420,20);
		cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
		cabecera.setForeground(Color.WHITE);
		add(cabecera);

		t.setBounds(85,65,100,100);
		t.setFont(new Font("Dialog", Font.PLAIN, 18));
		t.setForeground(Color.WHITE);
		add(t);
		
		titol.setBounds(150,100,350,30);
		add(titol);
		
		a.setBounds(85,115,100,100);
		a.setFont(new Font("Dialog", Font.PLAIN, 18));
		a.setForeground(Color.WHITE);
		add(a);
		
		autor.setBounds(150,150,350,30);
		add(autor);
		
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
	    	String tit = titol.getText();
	    	String aut = autor.getText();
	    	int number = (Integer) spinner.getValue();
	    	controladorPresencia.cercaSemblantsPresencia(tit,aut,number);
	    	setVisible(false);
	    }
	    if (e.getSource()==retroceder) {
	    	controladorPresencia.mostraCerca();
	    	setVisible(false);
	    }
	}
}
