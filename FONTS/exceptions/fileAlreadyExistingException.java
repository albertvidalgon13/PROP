package exceptions;

/**
 * (Exception) 
 * 
 * Classe per una excepció personalitzada, que es dispara quan un document en qüestió ja existeix al sistema.
 * 
 * @author prop23-subgrup5
 */

public class fileAlreadyExistingException  extends Exception  
{  
    public fileAlreadyExistingException (String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}  