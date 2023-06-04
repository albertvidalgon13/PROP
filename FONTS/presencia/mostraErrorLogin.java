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
 * (Interfície) Classe que ens permet mostrar un error en el login de l'aplicacio
 * 
 * @author prop23-subgrup5
 *
 */

public class mostraErrorLogin extends JFrame implements ActionListener {
	JLabel text = new JLabel("ERROR");
	JLabel msg;
	JButton volverMenu = new JButton("ENRERE");
	
	/**
	 * Constructora, crea la interfície
	 */
	public mostraErrorLogin() {
		String missatge = "No existeix aquest usuari";

		msg = new JLabel(missatge);
		
		setSize(500, 200);
       setLocationRelativeTo(null);
       setLayout(null);
       getContentPane().setBackground(new Color(44,53,59));
		
       text.setBounds(200,25,400,30);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text);
	   
	   msg.setBounds(50,65,400,30);
       msg.setForeground(Color.WHITE);
       msg.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(msg);

	   volverMenu.setBounds(125,110,200,30);
	   volverMenu.setFont(new Font("Dialog", Font.PLAIN, 14));
	   volverMenu.setBackground(Color.WHITE);
	   add(volverMenu);
	   volverMenu.addActionListener(this);
	  
	   setVisible(true);
       setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   }
   
	
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==volverMenu) {
    		controladorPresencia.log();
        	setVisible(false);
        }
    }
}
