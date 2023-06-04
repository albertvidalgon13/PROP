package presencia;
import controladors.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * (Interfície) Classe que ens permet fer log-in al sistema
 * 
 * @author prop23-subgrup5
 *
 */
public class login extends JFrame implements ActionListener{
	JButton boton1;
	JButton boton2;
	
    JTextField textField1 = new JTextField(25);
    JPasswordField textField2 = new JPasswordField();
    
    JLabel label1 = new JLabel("Nom");
    JLabel label2 = new JLabel("Password");
    
    //para cambiar el color y la fuente del boton (son de prueba los que he puesto)
    Color c = new Color(44,53,59);
    Font  f1  = new Font(Font.SANS_SERIF, Font.PLAIN,  15);
    
    /**
     *  Constructora, crea la interfície
     */
    public login() {

        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44,53,59));
        
        label1.setBounds(100,75,100,30);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Dialog", Font.PLAIN, 14));
        add(label1);
        textField1.setBounds(100,100,100,30);
        add(textField1);
        textField1.addActionListener(this);
        
        label2.setBounds(100,145,100,30);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Dialog", Font.PLAIN, 14));
        add(label2);
        
        textField2.setBounds(100,170,100,30);
        add(textField2);
        textField2.addActionListener(this);
        
        boton1=new JButton("Log-In");
        boton1.setBounds(100,250,100,30);
        
        boton1.setBackground(Color.WHITE);
        boton1.setFont(new Font("Dialog", Font.PLAIN, 14));
        boton1.setBorder(BorderFactory.createBevelBorder(0));
        add(boton1);
        boton1.addActionListener(this);
        
        boton2=new JButton("Nou Usuari");
        boton2.setBounds(100,300,100,30);
        
        boton2.setBackground(Color.WHITE);
        boton2.setFont(new Font("Dialog", Font.PLAIN, 14));
        boton2.setBorder(BorderFactory.createBevelBorder(0));
        add(boton2);
        boton2.addActionListener(this);
        
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    /**
     * Metode per tancar la APP
     */
    public void dispose() {
		controladorPresencia.mostraConfirmacioSortidaToLogin();
		setVisible(false);
    }

    
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource()==boton1) {
    		String name = textField1.getText();
			String pas = textField2.getText();
			System.out.print(pas);
        	if (controladorPresencia.controlaLoginPresencia(name,pas)) {
        		controladorPresencia.carregaSessioDocuements();
        		setVisible(false);	
        	}
        	setVisible(false);
        }
    	if (e.getSource()==boton2) {
    		controladorPresencia.mostraRegistreUsuari();
        	setVisible(false);	
        }
    }
    
    public static void start() {
        login formulario1=new login();
        formulario1.setBounds(0,0,450,350);
        formulario1.setVisible(true);
        formulario1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}