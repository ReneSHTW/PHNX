package de.htw.berlin.PHNX.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import de.htw.berlin.PHNX.interfaces.PHNX;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;
import de.htw.berlin.PHNX.interfaces.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;

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
		if (emailAddress != null && (kB.getPeerSemanticTag(emailAddress) != null)) {
			PHNXName name = new PHNXNameImpl(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Name_firstName"), kB.getPeerSemanticTag(emailAddress)
					.getProperty("PHNX_Name_lastName"), concatenateStringsToIterator(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Name_middleName")));
			PHNXContact contact = new PHNXContactImpl(emailAddress, kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_homeAddress"), kB
					.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_wwwAddress"), kB.getPeerSemanticTag(emailAddress).getProperty(
					"PHNX_Contact_privateMobileNumber"), kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_Contact_privateLandLineNumber"), null);
			PHNXBusinessCard card = new PHNXBusinessCardImpl(name, contact, getOrganization(kB.getPeerSemanticTag(emailAddress).getProperty(
					"PHNX_Organization_SI")), null, kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_printableProfessionalDegree"), null, new Date(
					Long.parseLong(kB.getPeerSemanticTag(emailAddress).getProperty("PHNX_departure"))), new Date(Long.parseLong(kB.getPeerSemanticTag(
					emailAddress).getProperty("PHNX_arrival"))), null);
			kB.getPeerSemanticTag(emailAddress);
			return card;
		} else {
			throw new IllegalArgumentException();
		}

		/* null #1 -> getPHNXResource fuer Profession */
		/* null #2 -> getPHNXResource fuer Skills */
		/* null #3 -> Bild Holen */
	}

	@Override
	public Iterator<PHNXResource> getResource(String type, String name) {
		// kB.createContextCoordinates(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
		// SharkCSAlgebra.extract(kB, arg1);
		return null;
	}

	@Override
	public PHNXOrganization getOrganization(String wwwAddress) throws SharkKBException {
		if (wwwAddress != null && (kB.getPeerSemanticTag(wwwAddress) != null)) {
			PHNXOrganization organization = new PHNXOrganizationImpl(kB.getPeerSemanticTag(wwwAddress).getName(), kB.getPeerSemanticTag(wwwAddress).getSI()[0],
					null, null);
			return organization;
		} else {
			throw new IllegalArgumentException();
		}

		/* null #1 -> Bild Holen */
		/* null #2 -> getPHNXResource fuer resources */
	}

	@Override
	public void setBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		if (value != null) {
			PHNXResource resource = null;
			kB.createPeerSemanticTag(value.getName().getPrintableFullName(), value.getContact().getEmailAddress(), "null");
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_firstName", value.getName().getFirstName());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_lastName", value.getName().getLastName());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Name_middleNames",
					concatenateStringiteratorToString(value.getName().getMiddleNames()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_homeAddress", value.getContact().getHomeAddress());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_wwwAddress", value.getContact().getWwwAddress());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_privateMobileNumber", value.getContact().getMobileNumber());
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Contact_privateLandLineNumber",
					value.getContact().getLandLineNumber());
			// PHNXMapPOI speichern von PHNXContact fehlt noch
			if (value.getOrganization() != null) {
				kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_Organization_SI", value.getOrganization().getWwwAddress());
				resource = new PHNXResourceImpl(value.getProfession().getResourceType(), value.getProfession().getResourceName(), value.getProfession()
						.getOwnerOrganization(), value.getProfession().getContactPerson(), value.getProfession().getAmount(), value.getProfession()
						.getPicture());
				setResource(resource);
			}
			if (value.getPrintableProfessionalDegree() != null) {
				kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_printableProfessionalDegree",
						value.getPrintableProfessionalDegree());
			}
			if (value.getSkills() != null) {
				while (value.getSkills().hasNext()) {
					resource = new PHNXResourceImpl(value.getSkills().next().getResourceType(), value.getSkills().next().getResourceName(), value.getSkills()
							.next().getOwnerOrganization(), value.getSkills().next().getContactPerson(), value.getSkills().next().getAmount(), value
							.getSkills().next().getPicture());
					setResource(resource);
				}
			}
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_arrival", String.valueOf(value.getArrival().getTime()));
			kB.getPeerSemanticTag(value.getContact().getEmailAddress()).setProperty("PHNX_departure", String.valueOf(value.getDeparture()));
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void setResource(PHNXResource value) throws PHNXException, SharkKBException {
		if (value != null) {
			ContextCoordinates cc = kB.createContextCoordinates(kB.createSemanticTag(value.getResourceName(), value.getResourceType()),
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
	public void setOrganization(PHNXOrganization value) throws PHNXException, SharkKBException {
		if (value != null && value.getWwwAddress() != null) {
			PHNXResource resource = null;
			kB.createPeerSemanticTag(value.getName(), value.getWwwAddress(), "null");
			while (value.getResources().hasNext()) {
				resource = new PHNXResourceImpl(value.getResources().next().getResourceType(), value.getResources().next().getResourceName(), value
						.getResources().next().getOwnerOrganization(), value.getResources().next().getContactPerson(), value.getResources().next().getAmount(),
						value.getResources().next().getPicture());
				setResource(resource);
			}
			// Logo wird als eigener ContextPunkt erstellt
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException {
		if (value != null) {
			kB.getPeersAsSemanticNet().removeSemanticTag(kB.getPeerSemanticTag(value.getContact().getEmailAddress()));
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeResource(PHNXResource value) throws PHNXException, SharkKBException {
		if (value != null) {
			// kB.removeContextPoint(kB.get);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeOrganization(PHNXOrganization value) throws PHNXException, SharkKBException {
		if (value != null) {
			kB.getPeersAsSemanticNet().removeSemanticTag(kB.getPeerSemanticTag(value.getWwwAddress()));
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

	@Override
	public PHNXMapPOI getPointOfInterest(String pointIdentifier) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointOfInterest(PHNXMapPOI value) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePointOfInterest(PHNXMapPOI value) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub

	}
}
