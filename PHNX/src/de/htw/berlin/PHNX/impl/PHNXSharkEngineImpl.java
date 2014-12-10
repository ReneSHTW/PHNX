package de.htw.berlin.PHNX.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.SemanticTag;
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
		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		ContextPoint tempPoint = null;
		String tempStringTyp;
		String tempStringOwner;
		String tempStringName;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
			tempStringName = tempPoint.getContextCoordinates().getTopic().getName();
			tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
			if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP) && tempStringName.equals(nameP)) {
				tempListCP.add(tempPoint);
			}
			for (int i = 0; i < tempListCP.size(); i++) {
				tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
			}
		}
		return tempListResource.iterator();
	}

	private Iterator<PHNXResource> getPHNXResourceByType(String TypP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		ContextPoint tempPoint = null;
		String tempStringTyp;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
			if (tempStringTyp.equals(TypP)) {
				tempListCP.add(tempPoint);
			}
			for (int i = 0; i < tempListCP.size(); i++) {
				tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
			}
		}
		return tempListResource.iterator();
	}

	private Iterator<PHNXResource> getPHNXResourceByTypeAndOwner(String TypP, String ownerP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		ContextPoint tempPoint = null;
		String tempStringTyp;
		String tempStringOwner;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
			tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
			if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP)) {
				tempListCP.add(tempPoint);
			}
			for (int i = 0; i < tempListCP.size(); i++) {
				tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
			}
		}
		return tempListResource.iterator();
	}

	private Iterator<PHNXResource> getPHNXResourceByTypeAndName(String TypP, String nameP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		ContextPoint tempPoint = null;
		String tempStringTyp;
		String tempStringName;
		while (tempEnum.hasMoreElements()) {
			tempPoint = kB.getAllContextPoints().nextElement();
			tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
			tempStringName = tempPoint.getContextCoordinates().getTopic().getName();
			if (tempStringTyp.equals(TypP) && tempStringName.equals(nameP)) {
				tempListCP.add(tempPoint);
			}
			for (int i = 0; i < tempListCP.size(); i++) {
				tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
			}
		}
		return tempListResource.iterator();
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
		// Keine verschachtelten Create aufrufe
		// die Skills bekommt man wieder raus anhand von getPHNXResource (null, skill, SI der BusinessCard)
		// dito für die profession (null, profession, SI der BusinessCard)

	}

	@Override
	public void removePHNXResource(String nameP, String TypP, String owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public PHNXResource createPHNXResource() throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub
		return null;
	}

}
