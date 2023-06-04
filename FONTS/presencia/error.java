package presencia;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.*;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que ens permet mostrar un error a l'usuari
 * 
 * @author prop23-subgrup5
 *
 */
public class error extends JFrame implements ActionListener{
	String err;
	JLabel text = new JLabel("ERROR");
	JLabel msg;
	JButton volverMenu = new JButton("ENRERE");
	
	/**
	 * Constructora, crea la interfície
	 */
	public error(String error) {
		err = error;
		String missatge ="";
		if(err =="errorRegistreEx") {
			missatge = "L'usuari ja existeix";
		}
		else if(err =="errorRegistrePas") {
			missatge = "La contrasenya no es valida (almenys 4 caracters)";
		}
		else if(err =="errorLogin") {
			missatge = "L'usuari i la contrasenya no coincideixen";
		}
		else if(err =="errorPermisos") {
			missatge = "No tens permisos per modificar o eliminar aquest document";
		}
		else if(err =="errorUsari") {
			missatge = "No existeix aquest usuari";
		}
		
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
    		
    		if(err =="errorRegistreEx") {
    			setVisible(false);
    		}
    		else if(err =="errorRegistrePas") {
    			setVisible(false);
    		}
    		
    		else {
    			controladorPresencia.mostraMenu();
    		}
        	setVisible(false);
        }
    }

}
