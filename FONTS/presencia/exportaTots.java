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

import controladors.controladorPresencia;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que ens permet exportar tots els documents
 * @author  prop23-subgrup5
 *
 */
public class exportaTots extends JFrame implements ActionListener{
	JButton d = new JButton("SELECCIONAR DIRECTORI DE GUARDAT");
	JLabel txt = new JLabel("Tots els documents es guardaran en el directori seleccionat");
	JButton enrrere = new JButton("ENRERE");
	
	/**
	 * Creadora que mostra la interficie
	 */
	public exportaTots() {

		setSize(600, 250);
	    setLocationRelativeTo(null);
	    setLayout(null);
	    getContentPane().setBackground(new Color(44,53,59));
	    
		txt.setBounds(50,50,450,30);
		txt.setFont(new Font("Dialog", Font.PLAIN, 14));
		txt.setForeground(Color.WHITE);
		add(txt);
	    
		d.setBounds(50,100,450,30);
		d.setFont(new Font("Dialog", Font.PLAIN, 14));
	    d.setBackground(Color.WHITE);
		add(d);
		d.addActionListener(this);
		
		   enrrere.setBounds(175,150,200,30);
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
				JFileChooser filechooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				filechooser.setDialogTitle("Selecciona la carpeta que conté el set de dades");
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = filechooser.showOpenDialog(null);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = filechooser.getSelectedFile();
					//System.out.print(f.getAbsolutePath());
					try {
						controladorPresencia.exportaTotsDocumentsPresencia(f);
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
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
