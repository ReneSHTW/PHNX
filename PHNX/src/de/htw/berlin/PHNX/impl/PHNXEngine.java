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
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.Knowledge;
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
	public PHNXBusinessCard getBusinessCard(String emailAddress) throws SharkKBException, ParseException {
		// Bedingung einfügen -> PeerSemanticTag mit dieser emailaddresse muss
		// vorhanden sein
		if (emailAddress != null) {
			PHNXName name = new PHNXNameImpl(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Name_firstName"), kB.getPeerSemanticTag(emailAddress)
					.getProperty("PHNX_Name_lastName"), concatenateStringsToIterator(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Name_middleName")));
			PHNXContact contact = new PHNXContactImpl(emailAddress, kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_homeAddress"), kB
					.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_wwwAddress"), kB.getPeerSemanticTag(emailAddress).getProperty(
					"PHNX_Contact_privateMobileNumber"), kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_privateLandLineNumber"));
			PHNXBusinessCard card = new PHNXBusinessCardImpl(name, contact, getOrganization(kB.getPeerSemanticTag(emailAddress).getProperty(
					"PHNX_Organization_SI")), null, kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_printableProfessionalDegree"), null,
					new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_departure")),
					new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_arrival")));
			kB.getPeerSemanticTag(emailAddress);
			return card;
		} else {
			throw new IllegalArgumentException();
		}

		/* null #1 -> getPHNXResource fuer Profession */
		/* null #2 -> getPHNXResource fuer Skills */
	}

	@Override
	public Iterator<PHNXResource> getPHNXResource(String type, String name) {
		/*
		 * kB.getContextPoint(arg0)
		 * 
		 * PHNXResource resource = null;
		 */
		return null;
	}

	@Override
	public PHNXOrganization getOrganization(String wwwAddress) {
		return null;
	}

	@Override
	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		if (value != null) {
			PHNXResource resource = null;
			kB.createPeerSemanticTag(value.getName().getPrintableFullName(), value.getContact().getEmailAddress(), "null");
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
			resource = new PHNXResourceImpl(value.getProfession().getResourceType(), value.getProfession().getResourceName(), value.getProfession()
					.getOwnerOrganization(), value.getProfession().getContactPerson(), value.getProfession().getAmount(), value.getProfession().getPicture());
			setPHNXResource(resource);
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_printableProfessionalDegree", value.getPrintableProfessionalDegree());
			while (value.getSkills().hasNext()) {
				resource = new PHNXResourceImpl(value.getSkills().next().getResourceType(), value.getSkills().next().getResourceName(), value.getSkills()
						.next().getOwnerOrganization(), value.getSkills().next().getContactPerson(), value.getSkills().next().getAmount(), value.getSkills()
						.next().getPicture());
				setPHNXResource(resource);
			}
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_arrival", formatter.format(value.getArrival()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_departure", formatter.format(value.getDeparture()));
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void setPHNXResource(PHNXResource value) throws PHNXException, SharkKBException {
		if (value != null) {
			ContextCoordinates cc = InMemoSharkKB.createInMemoContextCoordinates(
					InMemoSharkKB.createInMemoSemanticTag(value.getResourceName(), value.getResourceType()),
					kB.getPeerSemanticTag(value.getOwnerOrganization().getWwwAddress()),
					kB.getPeerSemanticTag(value.getContactPerson().getContact().getEmailAddress()), null, null, null, 0);
			kB.createContextPoint(cc);
			kB.getContextPoint(cc).addInformation(value.getAmount());
			// Picture wird als eigener ContextPunkt erstellt
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException {
		if (value != null) {
			PHNXResource resource = null;
			kB.createPeerSemanticTag(value.getName(), value.getWwwAddress(), "null");
			while (value.getResources().hasNext()) {
				resource = new PHNXResourceImpl(value.getResources().next().getResourceType(), value.getResources().next().getResourceName(), value
						.getResources().next().getOwnerOrganization(), value.getResources().next().getContactPerson(), value.getResources().next().getAmount(),
						value.getResources().next().getPicture());
				setPHNXResource(resource);
			}
			// Logo wird als eigener ContextPunkt erstellt
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removePHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		if (value != null) {
			// kB.removeSemanticTag(value.getContact().getEmailAddress());
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removePHNXResource(PHNXResource value) throws PHNXException, SharkKBException {
		if (value != null) {
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removePHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException {
		if (value != null) {
		} else {
			throw new IllegalArgumentException();
		}
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
