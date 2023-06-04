package exceptions;

/**
 * (Exception)
 * 
 * Classe per una excepció personalitzada, que es dispara quan un document no existeix al sistema.
 * 
 * @author prop23-subgrup5
 */
public class fileNotFoundException  extends Exception  
{  
    public fileNotFoundException  (String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}  