package tests;

import static org.junit.Assert.*;

import java.util.Vector;
import domini.OpBoleana;
import org.junit.Test;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class OpBoleanaTest {
	
	private String op1 = "hola & adeu";
	private String op2 = "hola | (adeu & 'EL nen')";
	private String op3 = "{nen nena}";
	
	
	private Vector<String> vectExp = new Vector<String>() {{
		add(op1); add(op2); add(op3);
	}};
	
	@Test
	public void putOperacio() {
		OpBoleana.putOperacio(op1);
		OpBoleana.putOperacio(op2);
		OpBoleana.putOperacio(op3);
		Vector<String> result = OpBoleana.getOps();
		assertEquals(result,vectExp);
	}

}
