package de.htw.berlin.PHNX.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXSharkEngineImpl implements PHNXSharkEngine {

	private static PHNXSharkEngine phnxSharkEngine = null;
	private SharkKB kB;

	private PHNXSharkEngineImpl(/* eventuell Folder Name */) throws PHNXException {

		String folderName = "keineAhnung";
		try {
			this.kB = new FSSharkKB(folderName);
		} catch (SharkKBException e) {
			throw new PHNXException(e);
		}
	}

	public static PHNXSharkEngine getPHNXSharkEngine() throws PHNXException {
		if (phnxSharkEngine == null) {
			phnxSharkEngine = new PHNXSharkEngineImpl();
		}
		return phnxSharkEngine;
	}

	public Iterator<PHNXResource> getPHNXResource(String nameP, String TypP, String ownerP) throws SharkKBException {
		Iterator<PHNXResource> resources = null;
		if (TypP == null) {
			throw new IllegalArgumentException();
		} else if (ownerP == null && nameP != null) {
			resources = getPHNXResourceByTypeAndName(TypP, nameP);
		} else if (nameP == null && ownerP != null) {
			resources = getPHNXResourceByTypeAndOwner(TypP, ownerP);
		} else if (ownerP == null && nameP == null) {
			resources = getPHNXResourceByType(TypP);
		} else {
			resources = getSpecificPHNXResource(nameP, TypP, ownerP);
		}
		return resources;

	}

	private Iterator<PHNXResource> getSpecificPHNXResource(String nameP, String TypP, String ownerP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempList = new ArrayList<ContextPoint>();
		ContextPoint tempPoint = null;
		String[] tempStrings;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStrings = tempPoint.getContextCoordinates().getTopic().getSI();
			if (tempStrings[0].equals(TypP)) {
				tempList.add(tempPoint);
			}
			for (int i = 0; i < tempList.size(); i++) {
				// baue Resource zusammen
			}
		}
		return null;
	}

	private Iterator<PHNXResource> getPHNXResourceByType(String TypP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempList = new ArrayList<ContextPoint>();
		ContextPoint tempPoint = null;
		String[] tempStrings;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStrings = tempPoint.getContextCoordinates().getTopic().getSI();
			if (tempStrings[0].equals(TypP)) {
				tempList.add(tempPoint);
			}
			for (int i = 0; i < tempList.size(); i++) {
				// baue Resource zusammen
			}
		}

		return null;
	}

	private Iterator<PHNXResource> getPHNXResourceByTypeAndOwner(String TypP, String ownerP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempList = new ArrayList<ContextPoint>();
		ContextPoint tempPoint = null;
		String[] tempStrings;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStrings = tempPoint.getContextCoordinates().getTopic().getSI();
			if (tempStrings[0].equals(TypP)) {
				tempList.add(tempPoint);
			}
			for (int i = 0; i < tempList.size(); i++) {
				// baue Resource zusammen
			}
		}

		return null;
	}

	private Iterator<PHNXResource> getPHNXResourceByTypeAndName(String TypP, String nameP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempList = new ArrayList<ContextPoint>();
		ContextPoint tempPoint = null;
		String[] tempStrings;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStrings = tempPoint.getContextCoordinates().getTopic().getSI();
			if (tempStrings[0].equals(TypP)) {
				tempList.add(tempPoint);
			}
			for (int i = 0; i < tempList.size(); i++) {
				// baue Resource zusammen
			}
		}

		return null;
	}

	@Override
	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, PHNXResource professionP, String degreeP,
			Iterator<PHNXResource> skillsP, Date departureP, Date arrivalP, PHNXPicture pictureP) throws PHNXException, SharkKBException, ParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePHNXResource(String nameP, String TypP, String owner) {
		// TODO Auto-generated method stub

	}

}
