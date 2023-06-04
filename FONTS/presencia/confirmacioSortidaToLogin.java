package presencia;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.*;

/**
 * (Interfície) Classe que ens permet decidir si volem tancar o no la aplicació
 * 
 * @author prop23-subgrup5
 *
 */
public class confirmacioSortidaToLogin extends JFrame implements ActionListener{
	
	JLabel text = new JLabel("Estas segur que vols tancar la aplicacio?");
	JButton cerrar = new JButton("TANCAR");
	JButton volverMenu = new JButton("TORNAR AL LOGIN");
	
	/**
	 * Constructora, crea la interfície
	 */
	public confirmacioSortidaToLogin() {
		setSize(500, 200);
       setLocationRelativeTo(null);
       setLayout(null);
       getContentPane().setBackground(new Color(44,53,59));
		
       text.setBounds(125,25,400,30);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text);
  
	   cerrar.setBounds(40,70,200,30);
	   cerrar.setFont(new Font("Dialog", Font.PLAIN, 14));
	   cerrar.setBackground(Color.WHITE);
	   add(cerrar);
	   cerrar.addActionListener(this);

	   volverMenu.setBounds(270,70,200,30);
	   volverMenu.setFont(new Font("Dialog", Font.PLAIN, 14));
	   volverMenu.setBackground(Color.WHITE);
	   add(volverMenu);
	   volverMenu.addActionListener(this);
	  
	   setVisible(true);
	   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   }
   

	
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==cerrar) {
    		//controlador.serialize();
        	System.exit(0);
        	
        }
    	if (e.getSource()==volverMenu) {
    		controladorPresencia.mostraLogIn();
    		setVisible(false);
        }
    }

}