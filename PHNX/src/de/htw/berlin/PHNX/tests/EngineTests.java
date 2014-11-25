package de.htw.berlin.PHNX.tests;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import de.htw.berlin.PHNX.impl.PHNXEngine;

public class EngineTests {

	public EngineTests() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void engineTest() {
		// PHNXEngine engine = new PHNX();
	}

	@Test
	public void concatenateStringiteratorToStringTest() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("eins");
		arrayList.add("zwei");
		arrayList.add("drei");
		Iterator<String> iterator = arrayList.iterator();
		String concatenation = "";
		while (iterator.hasNext()) {
			concatenation += iterator.next() + " ";
		}
		Assert.assertTrue(concatenation.equals("eins zwei drei "));
	}

	@Test
	public void concatenateStringsToIteratorTest() {
		String strings = "eins zwei drei";
		String[] temp = strings.split(" ");
		Assert.assertTrue(temp[0].equals("eins"));
		ArrayList<String> tempList = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			tempList.add(temp[i]);
		}
		Iterator<String> iterator = tempList.iterator();
		Assert.assertTrue(iterator.next().equals("eins"));
	}

}
