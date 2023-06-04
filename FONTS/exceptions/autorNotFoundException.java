package exceptions;

/**
 * (Exception)
 * 
 * Classe per una excepció personalitzada, que es dispara quan l'autor no existeix en el sistema
 * 
 * @author prop23-subgrup5
 */

public class autorNotFoundException  extends Exception  
{  
    public autorNotFoundException(String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}  