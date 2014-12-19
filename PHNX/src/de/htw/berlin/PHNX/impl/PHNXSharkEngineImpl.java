package de.htw.berlin.PHNX.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.FragmentationParameter;
import net.sharkfw.knowledgeBase.Information;
import net.sharkfw.knowledgeBase.Knowledge;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkCSAlgebra;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXSharkEngineImpl implements PHNXSharkEngine {

	private static PHNXSharkEngine phnxSharkEngine = null;
	private SharkKB kB;
	
	public TXSemanticTag getRessourceTag(Taxonomy topicsTX, PHNXResource.RessourceType type) throws SharkKBException {
		
		String si = this.getResourceTypeSI(type);
		String name = null;
		
		switch(type) {
		case PHNX_EQUIPMENT: name = "Equipment"; break;
		case PHNX_MEDICINE: name = "Medicine"; break;
		}
		
		return topicsTX.createTXSemanticTag(name, si);
	}
	
	public String getResourceTypeSI(PHNXResource.RessourceType type) {
		String si = null;
		
		switch(type) {
		case PHNX_EQUIPMENT: si = PHNXResource.PHNX_EQUIPMENT_SI; break;
		case PHNX_MEDICINE: si = PHNXResource.PHNX_MEDICINE_SI; break;
		}
		
		return si;
	}
	
	public PeerSemanticTag getOwnerPST(SharkKB kb, String si) throws SharkKBException {
		return kb.getPeerSTSet().getSemanticTag(si);
	}


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

	/**
	 * @param nameP not necessarily unique (can be null)
	 * @param typeP unique (must not be null)
	 * @param ownerP unique SI of owner peer (can be null)
	 * 
	 */
	public Iterator<PHNXResource> getPHNXResource(String nameP, PHNXResource.RessourceType typeR, String ownerP) throws SharkKBException {
		// let's create a coordinate to extract knowledge later.
		
		// tag representing resource type (not the actual resource!)
		SemanticTag resourceTypeTag = this.getRessourceTag(this.kB.getTopicsAsTaxonomy(), typeR);

		// owner tag
		PeerSemanticTag ownerPST = this.getOwnerPST(this.kB, ownerP);
		
		// catch exception?
		
		ContextCoordinates cc = this.kB.createContextCoordinates(resourceTypeTag, ownerPST, null, null, null, null, SharkCS.DIRECTION_INOUT);
		
		FragmentationParameter[] fps = new FragmentationParameter[SharkCS.MAXDIMENSIONS];
		
		/* define parameter to find resources of give type
		 * we have to look for concepts which are sub concepts of given type in topic dimension
		 */
		FragmentationParameter fpTopic = new FragmentationParameter(false, true, 1);
		fps[SharkCS.DIM_TOPIC] = fpTopic;
		
		// lets find all context point representing resources of given type
		Knowledge k = SharkCSAlgebra.extract(this.kB, cc, fps);
		
		Enumeration<ContextPoint> tempEnum = k.contextPoints();
		
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		if(tempEnum != null) {
			while(tempEnum.hasMoreElements()) {
				ContextPoint cp = tempEnum.nextElement();
				
				if(nameP == null) {
					tempListResource.add(new PHNXResourceImpl(this, cp));
				} else {
					if(cp.getContextCoordinates().getTopic().getName().equals(nameP)) {
						tempListResource.add(new PHNXResourceImpl(this, cp));
					}
				}
			}
		}
		
//		Iterator<PHNXResource> resources = null;
//		
//		ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
//		ContextPoint tempPoint = null;
//		String tempStringTyp;
//		String tempStringOwner;
//		String tempStringName;
//		if (TypP == null) {
//			throw new IllegalArgumentException();
//		} else if (ownerP == null && nameP != null) {
//			while (tempEnum.hasMoreElements()) {
//				tempPoint = kB.getAllContextPoints().nextElement();
//				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
//				tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
//				if (tempStringTyp.equals(TypP) && tempStringName.equals(nameP)) {
//					tempListCP.add(tempPoint);
//				}
//				for (int i = 0; i < tempListCP.size(); i++) {
//					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
//				}
//			}
//			tempListResource.iterator();
//		} else if (nameP == null && ownerP != null) {
//			while (tempEnum.hasMoreElements()) {
//				tempPoint = kB.getAllContextPoints().nextElement();
//				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
//				tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
//				if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP)) {
//					tempListCP.add(tempPoint);
//				}
//				for (int i = 0; i < tempListCP.size(); i++) {
//					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
//				}
//			}
//			tempListResource.iterator();
//		} else if (ownerP == null && nameP == null) {
//			while (tempEnum.hasMoreElements()) {
//				tempPoint = kB.getAllContextPoints().nextElement();
//				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
//				if (tempStringTyp.equals(TypP)) {
//					tempListCP.add(tempPoint);
//				}
//				for (int i = 0; i < tempListCP.size(); i++) {
//					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
//				}
//			}
//			tempListResource.iterator();
//		} else {
//			while (tempEnum.hasMoreElements()) {
//				tempPoint = kB.getAllContextPoints().nextElement();
//				tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
//				tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
//				tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
//				if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP) && tempStringName.equals(nameP)) {
//					tempListCP.add(tempPoint);
//				}
//				for (int i = 0; i < tempListCP.size(); i++) {
//					tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
//				}
//			}
//			resources = tempListResource.iterator();
//		}
				
		return tempListResource.iterator();
	}

	@Override
	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		if (emailAddressP != null && (kB.getPeerSemanticTag(emailAddressP) != null)) {
			PHNXBusinessCard card = new PHNXBusinessCardImpl(phnxSharkEngine, kB.getPeerSemanticTag(emailAddressP));
			return card;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, String degreeP, Date departureP,
			Date arrivalP, PHNXPicture pictureP) throws SharkKBException {
		new PHNXBusinessCardImpl(kB, nameP, contactP, organizationSubjectIdentifierP, degreeP, departureP, arrivalP, pictureP);
		// Keine verschachtelten Create aufrufe
		// die Skills bekommt man wieder raus anhand von getPHNXResource (null, skill, SI der BusinessCard[0])
		// dito für die profession (null, profession, SI der BusinessCard[0])
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

	@Override
	public PHNXOrganization getPHNXOrganization(String wWWAddressP) throws SharkKBException {
		// TODO Auto-generated method stub
		return null;
	}
}