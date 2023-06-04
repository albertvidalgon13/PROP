package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controladors.controladorPresencia;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe per mostrar la confirmacio per esborrar tot el conjunt de documents
 * 
 * @author  prop23-subgrup5
 *
 */
public class confirmacioEsborrarTot extends JFrame implements ActionListener {
	JLabel text = new JLabel("Estas segur que vols esborrar tots els documents?");
	JButton esborrar = new JButton("ESBORRAR");
	JButton enrrere = new JButton("ENRERE");
	
	/**
	 * Constructora, crea la interfície
	 */
	public confirmacioEsborrarTot() {
		setSize(500, 200);
       setLocationRelativeTo(null);
       setLayout(null);
       getContentPane().setBackground(new Color(44,53,59));
		
       text.setBounds(120,25,400,30);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("Dialog", Font.PLAIN, 14));
	   add(text);
  
	   esborrar.setBounds(25,100,200,30);
	   esborrar.setFont(new Font("Dialog", Font.PLAIN, 14));
	   esborrar.setBackground(Color.WHITE);
	   add(esborrar);
	   esborrar.addActionListener(this);

	   enrrere.setBounds(250,100,200,30);
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
    	if (e.getSource()==esborrar) {
    		controladorPresencia.esborrarTot();
    		try {
				controladorPresencia.mostraModDoc();
			} catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		setVisible(false);
        }
    	if (e.getSource()==enrrere) {
    		try {
				controladorPresencia.mostraModDoc();
			} catch (fileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		setVisible(false);
        }
    }
}
