package presencia;

import controladors.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * (Interfície) Classe que ens permet inserir documents al sistema des del sistema de fitxers del nostre PC
 * 
 * @author prop23-subgrup5
 *
 */

public class carregaDoc extends JFrame implements ActionListener{
	
	JButton carregarDoc = new JButton("SELECCIONAR DOCUMENT");
	JButton retroceder = new JButton("ENRERE");
	JButton carregarDocs = new JButton("SELECCIONAR DOCUMENTS");
	
	/**
	 * Constructora, crea la interfície
	 */
	public carregaDoc() {
		setSize(400,400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(44,53,59));
        setLayout(null);
        
        carregarDoc.setBounds(70,100,250,30);
        carregarDoc.setFont(new Font("Dialog", Font.PLAIN, 14));
        carregarDoc.setBackground(Color.WHITE);
        add(carregarDoc);
        carregarDoc.addActionListener(this);
        
        carregarDocs.setBounds(70,150,250,30);
        carregarDocs.setFont(new Font("Dialog", Font.PLAIN, 14));
        carregarDocs.setBackground(Color.WHITE);
        add(carregarDocs);
        carregarDocs.addActionListener(this);
        
        retroceder.setBounds(70,200,250,30);
        retroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
        retroceder.setBackground(Color.WHITE);
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
		if (e.getSource()==carregarDoc) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = filechooser.showOpenDialog(null);
			
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = filechooser.getSelectedFile();
				//System.out.print(f.getAbsolutePath());
				try {
					controlador.carregaDocument(f);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}
		
		if (e.getSource()==carregarDocs) {
			JFileChooser filechooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			filechooser.setDialogTitle("Selecciona la carpeta que conté el set de dades");
			filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			
			int result = filechooser.showOpenDialog(null);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = filechooser.getSelectedFile();
				//System.out.print(f.getAbsolutePath());
				try {
					controladorPresencia.carregaDocumentsPresencia(f);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}
		
		
		if (e.getSource()==retroceder) {
			controladorPresencia.mostraAltaDoc();
			setVisible(false);
		}	
    }
}
