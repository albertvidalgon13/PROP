package tests;

import domini.Contingut;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * (Test)
 * @author prop23-subgrup5
 */
public class ContingutTest {
	
	private String loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500\n";

	@Test
	public void view() {
		Contingut contingut1 = new Contingut(loremIpsum);
		Contingut contingut2 = new Contingut();
		String result = contingut1.view();
		String result2 = contingut2.view();
		assertEquals(loremIpsum, result);
		assertEquals("",result2);
	}

}
