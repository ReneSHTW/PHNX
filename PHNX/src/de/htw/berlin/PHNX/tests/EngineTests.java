package de.htw.berlin.PHNX.tests;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.Taxonomy;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

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
	public void engineTest() throws PHNXException {
		PHNXSharkEngine engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
		Assert.assertNotNull(engine);
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

	@Test
	public void BusinessCardDateTest() throws ParseException {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN); // Formatierer erzeugen
		Calendar datum = Calendar.getInstance(); // Calender Datum erzeugen
		datum.set(1988, 8 - 1, 21, 0, 0); // Calender Datum festlegen, Monate beginnen bei Januar mit 0
		Assert.assertTrue(formatter.format(datum.getTime()).equals("1988-08-21")); // formatiere Calendar-Typ zu String und vergleiche
		datum.setTime((Date) formatter.parseObject("1988-08-21")); // Ein Datum aus einem String erzeugen (setTime erwartet den Datentyp Date)
		Assert.assertTrue(formatter.format(datum.getTime()).equals("1988-08-21")); // aus String erzeugtes Datum testen
	}

	@Test
	public void STSetTest() throws SharkKBException {
		STSet plainSet = InMemoSharkKB.createInMemoSTSet();
		SemanticTag one = plainSet.createSemanticTag("eins", "Nummer");
		SemanticTag two = plainSet.createSemanticTag("zwei", "Nummer");

		plainSet.getSemanticTag("Nummer").getName();
		System.out.println(plainSet.getSemanticTag("Nummer").getName());
	}

	@Test
	public void TaxonomyTest() throws SharkKBException {
		SharkKB kb = new InMemoSharkKB();
		Taxonomy topicTX = kb.getTopicsAsTaxonomy();
	}

}
