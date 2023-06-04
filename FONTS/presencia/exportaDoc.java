package presencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import controladors.controlador;
import controladors.controladorPresencia;
import exceptions.fileNotFoundException;

/**
 * (Interfície) CLasse que ens permet exportar un document
 * @author  prop23-subgrup5
 *
 */
public class exportaDoc extends JFrame implements ActionListener{
	JLabel text = new JLabel("Selecciona el nom del fitxer");
	JButton d = new JButton("SELECCIONAR DIRECTORI DE GUARDAT");
	JButton enrrere = new JButton("ENRERE");
	JLabel t;
	JLabel a;
	
	String titol;
	String autor;
	String filename;
	
	JTextField nomFitxer = new JTextField(10);
	
	/**
	 * 	
	 * Creadora que mostra la interficie
	 * @param tit
	 * Títol del document a exportar
	 * @param aut
	 * Autor del document a exportar
	 */
	public exportaDoc(String tit, String aut) {
		titol = tit;
		autor = aut;
		setSize(600, 400);
	    setLocationRelativeTo(null);
	    setLayout(null);
	    getContentPane().setBackground(new Color(44,53,59));
	    
	    t = new JLabel ("Titol: "+ tit);
	    t.setBounds(65,25,400,30);
	    t.setFont(new Font("Dialog", Font.PLAIN, 18));
	    t.setForeground(Color.WHITE);
		add(t);
		
		a = new JLabel ("Autor: "+aut);
	    a.setBounds(65,65,400,30);
	    a.setFont(new Font("Dialog", Font.PLAIN, 18));
	    a.setForeground(Color.WHITE);
		add(a);
	    
	    text.setBounds(65,125,400,30);
	    text.setFont(new Font("Dialog", Font.PLAIN, 18));
        text.setForeground(Color.WHITE);
		add(text);
	    nomFitxer.setBounds(300,125,200,30);
	    nomFitxer.setFont(new Font("Dialog", Font.PLAIN, 18));
	   // nomFitxer.setForeground(Color.WHITE);
		add(nomFitxer);

		d.setBounds(50,200,450,30);
		d.setFont(new Font("Dialog", Font.PLAIN, 14));
	    d.setBackground(Color.WHITE);
		add(d);
		d.addActionListener(this);
		
		   enrrere.setBounds(175,250,200,30);
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
			
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==d) {
			if (!nomFitxer.getText().isEmpty()) {
				JFileChooser filechooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				filechooser.setDialogTitle("Selecciona la carpeta que conté el set de dades");
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
	            filename = nomFitxer.getText();
				int result = filechooser.showOpenDialog(null);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = filechooser.getSelectedFile();
					//System.out.print(f.getAbsolutePath());
					try {
						controladorPresencia.exportaDocumentPresencia(f,titol,autor,filename);
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}	
			}

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
