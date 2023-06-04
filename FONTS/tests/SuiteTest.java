package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * (Test) Execució de tots els tests
 * @author prop23-subgrup5
 */
@RunWith(Suite.class)
@SuiteClasses({ DocumentTest.class, ContingutTest.class, ConjuntDocumentTests.class, 
	InvertedFileTest.class, wordDataTest.class,fileTermDataTest.class })
public class SuiteTest {
}