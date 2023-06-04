package presencia;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controladors.*;
import domini.ConjuntDocuments.CustomPair;
import exceptions.fileNotFoundException;

/**
 * (Interfície) Classe que ens permet modificar el document seleccionat
 * 
 * @author prop23-subgrup5
 *
 */
public class modificarDoc extends JFrame implements ActionListener{
	
	JButton mostraDocsCreador;
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
	public modificarDoc(List<CustomPair> list) throws fileNotFoundException {
	   setSize(800, 700);
	   setLocationRelativeTo(null);
	   setLayout(null);
	   setResizable(false);
	   getContentPane().setBackground(new Color(44,53,59));
	   
	   JLabel cabecera = new JLabel("TITOLS I AUTORS:");
	   cabecera.setBounds(125,55,500,30);
	   cabecera.setFont(new Font("Dialog", Font.PLAIN, 18));
       cabecera.setForeground(Color.WHITE);
	   add(cabecera);
	   
	   
	   mostraDocsCreador = new JButton("MOSTRA ELS MEUS DOCUMENTS");
	   mostraDocsCreador.setBounds(225,100,300,30);
	   mostraDocsCreador.setFont(new Font("Dialog", Font.PLAIN, 14));
	   mostraDocsCreador.setBackground(Color.WHITE);
	   add(mostraDocsCreador);
	   mostraDocsCreador.addActionListener(this);
	   
	   JPanel pan = new JPanel();
	   pan.setBackground(new Color(44,53,59));
	   
	   texto.setLineWrap(true);
	   texto.setWrapStyleWord(true);
	   texto.setEditable(false);
	   
	   for (int i = 0; i < list.size(); i++) {
		   	CustomPair x = list.get(i);
		   
		    String autor = x.snd;
		    String titol = x.fst;
		    String creador =  controlador.getCreador(autor,titol);
		    texto.append("Titol: " + autor + "\n");
		    texto.append("Autor: " + titol + "\n");
		    texto.append("Creador: " + creador + "\n");
		    
		    texto.append("--------------------------------------------------------------------------------------------------------------\n");
	   }


	   	pan.setBounds(125,135,510,330);
	   	 	
	   	pan.add(texto);
	   	
        JScrollPane sp = new JScrollPane(texto);
        pan.add(sp);
	   
	   	JLabel t = new JLabel("Titol:");
	   	JLabel a = new JLabel("Autor:");   	
	   	
	   	t.setBounds(130,435,300,100);
	   	t.setForeground(Color.WHITE);
	   	add(t);
	   	titulo.setBounds(180,475,445,20);
	   	add(titulo);
	   	titulo.addActionListener(this);
	   	
	   	a.setBounds(130,460,300,100);
	   	a.setForeground(Color.WHITE);
	   	add(a);
	   	add(autor);
	   	autor.setBounds(180,500,445,20);
	   	autor.addActionListener(this);
	   	
	   	modifica = new JButton("MODIFICAR");
	   	modifica.setBounds(125,550,125,30);
	   	modifica.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	modifica.setBackground(Color.WHITE);
	   	add(modifica);
	   	modifica.addActionListener(this);
	   	
	   	esborra = new JButton("BORRAR");
	   	esborra.setBounds(260,550,125,30);
	   	esborra.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	esborra.setBackground(Color.WHITE);
	   	add(esborra);
	   	esborra.addActionListener(this);
	   	
	   	exporta = new JButton("EXPORTAR");
	   	exporta.setBounds(395,550,125,30);
	   	exporta.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	exporta.setBackground(Color.WHITE);
	   	add(exporta);
	   	exporta.addActionListener(this);
	   	
	   	
	   	add(pan);
	   	
	   	eliminartots = new JButton("ELIMINAR TOTS");
	   	eliminartots.setBounds(125,600,260,30);
	   	eliminartots.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	eliminartots.setBackground(Color.WHITE);
	   	add(eliminartots);
	   	eliminartots.addActionListener(this);
	   	
	   	
	   	
	   	exportatots = new JButton("EXPORTAR TOTS");
	   	exportatots.setBounds(395,600,260,30);
	   	exportatots.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	exportatots.setBackground(Color.WHITE);
	   	add(exportatots);
	   	exportatots.addActionListener(this);
	   	
	   	
	   	retroceder = new JButton("ENRERE");
	   	retroceder.setFont(new Font("Dialog", Font.PLAIN, 14));
	   	retroceder.setBackground(Color.WHITE);
	   	retroceder.setBounds(530,550,125,30);
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
    		String t = titulo.getText();
    		String a = autor.getText();
    		
    		controladorPresencia.setModificaTextPresencia(t,a);
    		
    		setVisible(false);	
        }
    	if (e.getSource()==esborra) {
    		String t = titulo.getText();
    		String a = autor.getText();

	    	if (controladorPresencia.existeixPresencia(t,a)) {
	    		controladorPresencia.mostraSeguroEliminarDoc(t,a);
	    		setVisible(false);
	    	}
        }
    	if (e.getSource()==exporta) {
    		String t = titulo.getText();
    		String a = autor.getText();
	    	if (controladorPresencia.existeixPresencia(t,a)) {
	    		controladorPresencia.mostraExportarDoc(t,a);
	    		setVisible(false);
	    	}
        }
    	    	
    	if (e.getSource()==retroceder) {
    		controladorPresencia.mostraMenu();
    		setVisible(false);	
    	}
    	if (e.getSource()==exportatots) {
    		controladorPresencia.mostraExportaTots();
    		setVisible(false);	
    	}
    	if (e.getSource()==mostraDocsCreador) {
    		try {
				controladorPresencia.mostraModificarDocCreadorPresencia();
			} catch (fileNotFoundException e1) {
			
				e1.printStackTrace();
			}
    		setVisible(false);	
    	}
    	if (e.getSource()== eliminartots) {
    		
    		if (controladorPresencia.usuariIniciatIsAdmin()) {
    			controladorPresencia.confirmacioEsborrarTot();
    			setVisible(false);
    		}
    		else {
    			controladorPresencia.mostraNomesAdmin();
    			setVisible(false);
    		}
    		
    	}
    }
}
