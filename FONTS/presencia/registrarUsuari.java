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

import controladors.controladorPresencia;
import controladors.controladorUsuaris;

/**
 * (Interfície) Classe que ens permet registrar un usuari nou
 * @author prop23-subgrup5
 *
 */
public class registrarUsuari extends JFrame implements ActionListener{
	JButton boton1;
	JButton botonSalir;
	
	
    JTextField textField1 = new JTextField(25);
    JTextField textField2 = new JTextField(25);
    
    JLabel label1 = new JLabel("Nom");
    JLabel label2 = new JLabel("Password");
    
    //para cambiar el color y la fuente del boton (son de prueba los que he puesto)
    Color c = new Color(44,53,59);
    Font  f1  = new Font(Font.SANS_SERIF, Font.PLAIN,  15);
    
    /**
     *  Constructora, crea la interfície
     */
    public registrarUsuari() {

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
        
        boton1=new JButton("Registrar");
        boton1.setBounds(100,250,100,30);
        
        boton1.setBackground(Color.WHITE);
        boton1.setFont(new Font("Dialog", Font.PLAIN, 14));
        boton1.setBorder(BorderFactory.createBevelBorder(0));
        add(boton1);
        boton1.addActionListener(this);

        
        botonSalir=new JButton("Retrocedir");
        botonSalir.setBounds(100,300,100,30);
        
        botonSalir.setBackground(Color.WHITE);
        botonSalir.setFont(new Font("Dialog", Font.PLAIN, 14));
        botonSalir.setBorder(BorderFactory.createBevelBorder(0));
        add(botonSalir);
        botonSalir.addActionListener(this);
        
        
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==boton1) {
			String name = textField1.getText();
			String pas = textField2.getText();
        		if(controladorPresencia.newUsuariPresencia(name,pas)) {
        			login x = new login();
        			setVisible(false);
        			controladorUsuaris.serializeUsers();
        		}
    	}
		if (e.getSource()==botonSalir) {
			controladorPresencia.mostraLogIn();
			setVisible(false);
		}
    }
		
}