package de.htw.berlin.PHNX.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import android.text.format.DateFormat;
import de.htw.berlin.PHNX.interfaces.PHNX;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;

public class PHNXEngine implements PHNX {

	private static PHNXEngine phnxEngine = null;
	private SharkKB kB;

	private PHNXEngine(/* eventuell Folder Name */) throws PHNXException {

		String folderName = "keineAhnung";
		try {
			this.kB = new FSSharkKB(folderName);
		} catch (SharkKBException e) {
			throw new PHNXException(e);
		}
	}

	public static PHNXEngine getPHNXEngine() throws PHNXException {
		if (phnxEngine == null) {
			phnxEngine = new PHNXEngine();
		}
		return phnxEngine;
	}

	@Override
	public PHNXBusinessCard getBusinessCard(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PHNXResource getPHNXResource(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PHNXOrganization getOrganization(String wwwAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		if (value != null) {
			kB.createPeerSemanticTag(null, value.getName().getPrintableFullName(), value.getContact().getEmailAddress());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_firstName", value.getName().getFirstName());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_lastName", value.getName().getLastName());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_middleNames",
					concatenateStringiteratorToString(value.getName().getMiddleNames()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_homeAddress", value.getContact().getHomeAddress());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_wwwAddress",
					concatenateStringiteratorToString(value.getContact().getWwwAddress()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_privateMobileNumber", value.getContact().getMobileNumber());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_privateLandLineNumber",
					value.getContact().getLandLineNumber());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Organization_SI", value.getOrganization().getWwwAddress());
			// setPHNXResource aufrufen um Profession-Ressource anzulegen
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_printableProfessionalDegree", value.getPrintableProfessionalDegree());
			// setPHNXResource aufrufen für jeden zu erstellenden String und am
			// ende als Iterator verpacken
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_arrival", formatter.format(value.getArrival()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_departure", formatter.format(value.getDeparture()));

			/*
			 * try { Date date = new SimpleDateFormat("yyyy-MM-dd",
			 * Locale.ENGLISH
			 * ).parse(kB.getPeerSemanticTag(value.getContact().getEmailAddress
			 * ()).getProperty( "PHNX_arrival")); } catch (ParseException e) {
			 * e.printStackTrace(); }
			 */
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void setPHNXResource(PHNXResource value) throws PHNXException, SharkKBException {
		ContextCoordinates cc = InMemoSharkKB.createInMemoContextCoordinates(
				InMemoSharkKB.createInMemoSemanticTag(value.getResourceName(), value.getResourceType()),
				kB.getPeerSemanticTag(value.getOwnerOrganization().getWwwAddress()),
				kB.getPeerSemanticTag(value.getContactPerson().getContact().getEmailAddress()), null, null, null, 0);
		kB.createContextPoint(cc);
		kB.getContextPoint(cc).addInformation(value.getAmount());
		// Picture wird als eigener ContextPunkt erstellt
	}

	@Override
	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException {
		kB.createPeerSemanticTag(null, value.getName(), value.getWwwAddress());
		//kB.getPeerSemanticTag(value.getWwwAddress()).setProperty(arg0, arg1)
	}

	@Override
	public void removePHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub

	}

	private String concatenateStringiteratorToString(Iterator<String> iterator) {
		String concatenation = "";
		while (iterator.hasNext()) {
			concatenation += iterator.next() + " ";
		}
		return concatenation;
	}

	private Iterator<String> concatenateStringsToIterator(String strings) {
		String[] temp = strings.split(" ");
		ArrayList<String> tempList = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			tempList.add(temp[i]);
		}
		return tempList.iterator();
	}

}
