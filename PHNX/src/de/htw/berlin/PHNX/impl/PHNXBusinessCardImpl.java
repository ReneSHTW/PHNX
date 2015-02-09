package de.htw.berlin.PHNX.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXBusinessCardImpl implements PHNXBusinessCard {

	private SharkKB kb;
	private PeerSemanticTag pst;
	private PHNXSharkEngine phnxEngine;

	public PHNXBusinessCardImpl(PHNXSharkEngine phnxEngineP, SharkKB kBP, PeerSemanticTag bCP) throws SharkKBException {
		phnxEngine = phnxEngineP;
		kb = kBP;
		pst = bCP;
	}

	public PHNXBusinessCardImpl(PHNXSharkEngine phnxEngineP, SharkKB kBP, PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP,
			String degreeP, Date departureP, Date arrivalP, PHNXPicture pictureP) throws SharkKBException {
		if (kBP != null && nameP != null && arrivalP != null && departureP != null && contactP != null && contactP.getEmailAddress() != null) {
			phnxEngine = phnxEngineP;
			kb = kBP;
			pst = kb.createPeerSemanticTag("PHNX_BC_SI", contactP.getEmailAddress(), "null");
			pst.setProperty("PHNX_Name_firstName", nameP.getFirstName());
			pst.setProperty("PHNX_Name_lastName", nameP.getLastName());
			if (nameP.getMiddleNames() != null) {
				pst.setProperty("PHNX_Name_middleNames", concatenateStringiteratorToString(nameP.getMiddleNames()));
			}
			pst.setProperty("PHNX_Contact_wwwAddress", contactP.getWwwAddress());
			pst.setProperty("PHNX_Contact_privateMobileNumber", contactP.getMobileNumber());
			pst.setProperty("PHNX_Contact_privateLandLineNumber", contactP.getLandLineNumber());
			if (organizationSubjectIdentifierP != null) {
				pst.setProperty("PHNX_Organization_SI", organizationSubjectIdentifierP);
			}
			if (degreeP != null) {
				pst.setProperty("PHNX_printableProfessionalDegree", degreeP);
			}
			pst.setProperty("PHNX_arrival", String.valueOf(arrivalP.getTime()));
			pst.setProperty("PHNX_departure", String.valueOf(departureP.getTime()));
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public PHNXName getName() {
		return new PHNXName(pst.getProperty("PHNX_Name_firstName"), pst.getProperty("PHNX_Name_lastName"),
				concatenateStringsToIterator(pst.getProperty("PHNX_Name_middleNames")));
	}

	@Override
	public PHNXContact getContact() {
		return new PHNXContact(pst.getSI()[0], pst.getProperty("PHNX_Contact_wwwAddress"), pst.getProperty("PHNX_Contact_privateMobileNumber"),
				pst.getProperty("PHNX_Contact_privateLandLineNumber"), null /*current Location*/);
	}



	@Override
	public PHNXOrganization getOrganization() throws SharkKBException {
		return phnxEngine.getPHNXOrganization(pst.getProperty("PHNX_Organization_SI"));
	}

	@Override
	public PHNXResource getProfession() throws SharkKBException {
		return phnxEngine.getPHNXResource(null, PHNXResource.RessourceType.PHNX_PROFESSION, pst.getSI()[0]).next();
	}

	@Override
	public String getPrintableProfessionalDegree() {
		return pst.getProperty("PHNX_printableProfessionalDegree");
	}

	@Override
	public Iterator<PHNXResource> getSkills() throws SharkKBException {
		return phnxEngine.getPHNXResource(null, PHNXResource.RessourceType.PHNX_SKILL, pst.getSI()[0]);
	}

	@Override
	public Date getArrival() {
		return new Date(Long.valueOf(pst.getProperty("PHNX_arrival")).longValue());
	}

	@Override
	public Date getDeparture() {
		return new Date(Long.valueOf(pst.getProperty("PHNX_departure")).longValue());
	}

	@Override
	public PHNXPicture getPicture() {
		return null;
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
