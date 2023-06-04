package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controladors.controladorPresencia;

/**
 * (Interfície) Classe que ens permet informar l'usuari que ha tornat a un punt de restauracio
 * 
 * @author prop23-subgrup5
 *
 */

public class confirmaTornarPunt extends JFrame implements ActionListener{
	JLabel text = new JLabel("S'ha tornat al punt de restauracio");
	JButton enrrere = new JButton("ENRERE");
	
	/**
	 * Creadora que mostra la interficie
	 */
	public confirmaTornarPunt() {
			setSize(500, 200);
	       setLocationRelativeTo(null);
	       setLayout(null);
	       getContentPane().setBackground(new Color(44,53,59));
	       text.setBounds(110,25,400,30);
	       text.setFont(new Font("Dialog", Font.PLAIN, 18));
	       text.setForeground(Color.WHITE);
		   add(text);

		   enrrere.setBounds(140,100,200,30);
		   enrrere.setFont(new Font("Dialog", Font.PLAIN, 14));
	       enrrere.setBackground(Color.WHITE);
		   add(enrrere);
		   enrrere.addActionListener(this);
		  
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

	    	if (e.getSource()==enrrere) {
	    		controladorPresencia.mostraMenu();
	    		setVisible(false);
	        }
	    }
}