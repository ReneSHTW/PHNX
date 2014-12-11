package de.htw.berlin.PHNX.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.Information;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;
import net.sharkfw.knowledgeBase.inmemory.InMemoSharkKB;
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
		Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		ContextPoint tempPoint = null;
		String tempStringTyp;
		String tempStringOwner;
		String tempStringName;
		if (TypP == null) {
			throw new IllegalArgumentException();
		} else if (ownerP == null && nameP != null) {
			while (tempEnum.hasMoreElements()) {
				tempPoint = kB.getAllContextPoints().nextElement();
				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
				tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
				if (tempStringTyp.equals(TypP) && tempStringName.equals(nameP)) {
					tempListCP.add(tempPoint);
				}
				for (int i = 0; i < tempListCP.size(); i++) {
					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
				}
			}
			tempListResource.iterator();
		} else if (nameP == null && ownerP != null) {
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
			tempListResource.iterator();
		} else if (ownerP == null && nameP == null) {
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
			tempListResource.iterator();
		} else {
			while (tempEnum.hasMoreElements()) {
				tempPoint = kB.getAllContextPoints().nextElement();
				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
				tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
				tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
				if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP) && tempStringName.equals(nameP)) {
					tempListCP.add(tempPoint);
				}
				for (int i = 0; i < tempListCP.size(); i++) {
					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
				}
			}
			resources = tempListResource.iterator();
		}
		return resources;
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
	public void removePHNXResource(String nameP, String TypP, String ownerP) throws SharkKBException {
		String[] tempStringArray = new String[2];
		tempStringArray[0] = TypP;
		tempStringArray[1] = nameP;
		kB.removeContextPoint(kB.createContextCoordinates(kB.getSemanticTag(tempStringArray), kB.getPeerSemanticTag(ownerP), null, null, null, null,
				SharkCS.DIRECTION_NOTHING));
	}

	@Override
	public void createPHNXResource(String resourceTypeP, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP,
			PHNXPicture pictureP) throws PHNXException, SharkKBException {
		new PHNXResourceImpl(kB, resourceTypeP, resourceNameP, ownerIdentifierP, contactPersonP, amountP, pictureP);
	}

	@Override
	public void editPHNXResource(String resourceNameP, String resourceTypeP, String ownerIdentifierP, String changeResourceTypeP, String changeResourceNameP,
			String changeOwnerIdentifierP, String changeContactPersonP, String changeAmountP, PHNXPicture changePictureP) throws SharkKBException {
		if (resourceTypeP == null && resourceNameP != null && ownerIdentifierP != null) {
			Enumeration<ContextPoint> tempEnum = kB.getAllContextPoints();
			ContextPoint resource = null;
			ContextPoint tempPoint = null;
			String tempStringTyp;
			String tempStringOwner;
			String tempStringName;
			boolean stopWhileLoop = false;
			while (tempEnum.hasMoreElements() && stopWhileLoop == false) {
				tempPoint = kB.getAllContextPoints().nextElement();
				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
				tempStringName = tempPoint.getContextCoordinates().getTopic().getName();
				tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
				if (tempStringTyp.equals(resourceTypeP) && tempStringOwner.equals(ownerIdentifierP) && tempStringName.equals(resourceNameP)) {
					resource = tempPoint;
					stopWhileLoop = true;
				}
			}
			if (changeResourceTypeP != null) {
				resource.getContextCoordinates().getTopic().addSI(changeResourceTypeP);
				resource.getContextCoordinates().getTopic().removeSI(resourceTypeP);
			}
			if (changeResourceNameP != null) {
				resource.getContextCoordinates().getTopic().setName(changeResourceNameP);
			}
			if (changeOwnerIdentifierP != null) {
				resource.getContextCoordinates().getOriginator().addSI(changeOwnerIdentifierP);
				resource.getContextCoordinates().getOriginator().removeSI(ownerIdentifierP);
			}
			if (changeContactPersonP != null) {
				String tempContactPerson = resource.getContextCoordinates().getPeer().getSI()[0];
				resource.getContextCoordinates().getPeer().addSI(changeContactPersonP);
				resource.getContextCoordinates().getPeer().removeSI(tempContactPerson);
			}
			if (changeAmountP != null) {
				Information tempInformation = resource.getInformation().next();
				resource.addInformation(changeAmountP);
				resource.removeInformation(tempInformation);
			}
			if (changePictureP != null) {
				// todo
			}
		}
	}
}
