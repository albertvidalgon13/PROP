package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controladors.controlador;
import controladors.controladorPresencia;
import domini.ConjuntDocuments.CustomPair;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que permet a l'usuari escollir a quin punt de restauracio tornar
 * 
 * @author prop23-subgrup5
 *
 */

public class tornarPuntRestauracio extends JFrame implements ActionListener {
	JButton modifica;
	JButton esborra;
	JButton exporta;
	JButton exportatots;
	JButton eliminartots;
	JButton retroceder;
	JTextField titulo = new JTextField(15);
   	JTextField autor = new JTextField(15);
   	JTextArea texto = new JTextArea(20,45);
   	
   	/**
   	 * Constructora, crea la interfície
   	 * @param list
   	 * Llista de tots els documents
   	 * @throws fileNotFoundException 
   	 */
	public tornarPuntRestauracio() throws fileNotFoundException {
	   setSize(800, 700);
	   setLocationRelativeTo(null);
	   setLayout(null);
	   setResizable(false);
	   getContentPane().setBackground(new Color(44,53,59));
	   
	   JLabel cabecera = new JLabel("PUNTS DE RESTAURACIO:");
	   cabecera.setBounds(125,55,500,30);
	   cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
       cabecera.setForeground(Color.WHITE);
	   add(cabecera);
	   
	   
	   JPanel pan = new JPanel();
	   pan.setBackground(new Color(44,53,59));
	   
	   texto.setLineWrap(true);
	   texto.setWrapStyleWord(true);
	   texto.setEditable(false);
		String pathactual = System.getProperty("user.dir");
		File directori;
		if (pathactual.charAt(pathactual.length()-1) == 'S') {
			directori = new File((System.getProperty("user.dir")+"/metaData"));
		}
		else {
			File x = new File((System.getProperty("user.dir")));
			directori = new File((x.getParent() + "/FONTS/metaData"));
		}
	
	   
	   
	   File[] lista = directori.listFiles();
	   
	   if (lista != null){
		   for (File arxiu : lista) {
			   String path = arxiu.getAbsolutePath();
			   if (path.charAt(path.length()-1) == 'r') {
				   texto.append(arxiu.getName() + "\n");	   
			   }
		   }
	   }
	   
	   	pan.setBounds(125,135,510,330);
	   	
	   	pan.add(texto);
	   	
        JScrollPane sp = new JScrollPane(texto);
        pan.add(sp);
        add(pan);
	   	JLabel t = new JLabel("Punt de restauracio:");
	   	JLabel a = new JLabel("Autor:");   	
	   	
	   	t.setBounds(130,435,300,100);
	   	t.setForeground(Color.WHITE);
	   	add(t);
	   	titulo.setBounds(250,475,375,20);
	   	add(titulo);
	   	titulo.addActionListener(this);
	   	
	   	modifica = new JButton("TORNAR AL PUNT");
	   	modifica.setBounds(125,520,250,30);
	   	modifica.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	modifica.setBackground(Color.WHITE);
	   	add(modifica);
	   	modifica.addActionListener(this);
	   	
	   	retroceder = new JButton("ENRERE");
	   	retroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	retroceder.setBackground(Color.WHITE);
	   	retroceder.setBounds(400,520,250,30);
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
    	if (e.getSource()==modifica) {
    		controladorPresencia.carregaPuntRestauracioPres(titulo.getText());
    		setVisible(false);
    		controladorPresencia.confirmaTornarPunt();
        }
    	    	
    	if (e.getSource()==retroceder) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);	
    	}
    }
}
