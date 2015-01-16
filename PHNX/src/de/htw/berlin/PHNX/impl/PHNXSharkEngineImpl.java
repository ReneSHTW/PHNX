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
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXSharkEngineImpl implements PHNXSharkEngine {

	private static PHNXSharkEngine phnxSharkEngine = null;
	private SharkKB kB;
	private static String foldername = "PHNXSharkKB";

	public TXSemanticTag getRessourceTag(Taxonomy topicsTX, PHNXResource.RessourceType type) throws SharkKBException {

		String si = this.getResourceTypeSI(type);
		String name = null;

		switch (type) {
		case PHNX_EQUIPMENT:
			name = "Equipment";
			break;
		case PHNX_MEDICINE:
			name = "Medicine";
			break;
		case PHNX_PROFESSION:
			name = "Profession";
			break;
		case PHNX_SKILL:
			name = "Skill";
			break;
		}
		return topicsTX.createTXSemanticTag(name, si);
	}

	public String getResourceTypeSI(PHNXResource.RessourceType type) {
		String si = null;

		switch (type) {
		case PHNX_EQUIPMENT:
			si = PHNXResource.PHNX_EQUIPMENT_SI;
			break;
		case PHNX_MEDICINE:
			si = PHNXResource.PHNX_MEDICINE_SI;
			break;
		case PHNX_PROFESSION:
			si = PHNXResource.PHNX_PROFESSION_SI;
			break;
		case PHNX_SKILL:
			si = PHNXResource.PHNX_SKILL_SI;
			break;
		}
		return si;
	}

	public PeerSemanticTag getOwnerPST(SharkKB kb, String si) throws SharkKBException {
		return kb.getPeerSTSet().getSemanticTag(si);
	}

	private PHNXSharkEngineImpl() throws PHNXException {
		try {
			this.kB = new FSSharkKB(foldername);
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

	private Enumeration<ContextPoint> getPHNXResourceAsCP(String nameP, PHNXResource.RessourceType typeP, String ownerP) throws SharkKBException {
		// let's create a coordinate to extract knowledge later.
		// tag representing resource type (not the actual resource!)
		SemanticTag resourceTypeTag = this.getRessourceTag(this.kB.getTopicsAsTaxonomy(), typeP);

		// owner tag
		PeerSemanticTag ownerPST = this.getOwnerPST(this.kB, ownerP);

		// catch exception?

		ContextCoordinates cc = this.kB.createContextCoordinates(resourceTypeTag, ownerPST, null, null, null, null, SharkCS.DIRECTION_INOUT);

		FragmentationParameter[] fps = new FragmentationParameter[SharkCS.MAXDIMENSIONS];

		/* define parameter to find resources of give type we have to look for concepts which are sub concepts of given type in topic dimension */
		FragmentationParameter fpTopic = new FragmentationParameter(false, true, 1);
		fps[SharkCS.DIM_TOPIC] = fpTopic;

		// lets find all context point representing resources of given type
		Knowledge k = SharkCSAlgebra.extract(this.kB, cc, fps);

		Enumeration<ContextPoint> tempEnum = k.contextPoints();

		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		if (tempEnum != null) {
			while (tempEnum.hasMoreElements()) {
				ContextPoint cp = tempEnum.nextElement();

				if (nameP == null) {
					tempListResource.add(new PHNXResourceImpl(this, cp));
				} else {
					if (cp.getContextCoordinates().getTopic().getName().equals(nameP)) {
						tempListResource.add(new PHNXResourceImpl(this, cp));
					}
				}
			}
		}
		return tempEnum;
	}

	/**
	 * @param nameP
	 *            not necessarily unique (can be null)
	 * @param typeP
	 *            unique (must not be null)
	 * @param ownerP
	 *            unique SI of owner peer (can be null)
	 * 
	 */
	public Iterator<PHNXResource> getPHNXResource(String nameP, PHNXResource.RessourceType typeP, String ownerP) throws SharkKBException {
		Enumeration<ContextPoint> tempEnum = getPHNXResourceAsCP(nameP, typeP, ownerP);
		ArrayList<PHNXResource> tempListResource = new ArrayList<PHNXResource>();
		if (tempEnum != null) {
			while (tempEnum.hasMoreElements()) {
				ContextPoint cp = tempEnum.nextElement();

				if (nameP == null) {
					tempListResource.add(new PHNXResourceImpl(this, cp));
				} else {
					if (cp.getContextCoordinates().getTopic().getName().equals(nameP)) {
						tempListResource.add(new PHNXResourceImpl(this, cp));
					}
				}
			}
		}
		return tempListResource.iterator();
		// Iterator<PHNXResource> resources = null;
		//
		// ArrayList<ContextPoint> tempListCP = new ArrayList<ContextPoint>();
		// ContextPoint tempPoint = null;
		// String tempStringTyp;
		// String tempStringOwner;
		// String tempStringName;
		// if (TypP == null) {
		// throw new IllegalArgumentException();
		// } else if (ownerP == null && nameP != null) {
		// while (tempEnum.hasMoreElements()) {
		// tempPoint = kB.getAllContextPoints().nextElement();
		// tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
		// tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
		// if (tempStringTyp.equals(TypP) && tempStringName.equals(nameP)) {
		// tempListCP.add(tempPoint);
		// }
		// for (int i = 0; i < tempListCP.size(); i++) {
		// tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
		// }
		// }
		// tempListResource.iterator();
		// } else if (nameP == null && ownerP != null) {
		// while (tempEnum.hasMoreElements()) {
		// tempPoint = kB.getAllContextPoints().nextElement();
		// tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
		// tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
		// if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP)) {
		// tempListCP.add(tempPoint);
		// }
		// for (int i = 0; i < tempListCP.size(); i++) {
		// tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
		// }
		// }
		// tempListResource.iterator();
		// } else if (ownerP == null && nameP == null) {
		// while (tempEnum.hasMoreElements()) {
		// tempPoint = kB.getAllContextPoints().nextElement();
		// tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
		// if (tempStringTyp.equals(TypP)) {
		// tempListCP.add(tempPoint);
		// }
		// for (int i = 0; i < tempListCP.size(); i++) {
		// tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
		// }
		// }
		// tempListResource.iterator();
		// } else {
		// while (tempEnum.hasMoreElements()) {
		// tempPoint = kB.getAllContextPoints().nextElement();
		// tempStringTyp = tempPoint.getContextCoordinates().getTopic().getSI()[0];
		// tempStringName = tempPoint.getContextCoordinates().getTopic().getSI()[1];
		// tempStringOwner = tempPoint.getContextCoordinates().getOriginator().getSI()[0];
		// if (tempStringTyp.equals(TypP) && tempStringOwner.equals(ownerP) && tempStringName.equals(nameP)) {
		// tempListCP.add(tempPoint);
		// }
		// for (int i = 0; i < tempListCP.size(); i++) {
		// tempListResource.add(new PHNXResourceImpl(tempListCP.get(i)));
		// }
		// }
		// resources = tempListResource.iterator();
		// }
	}

	@Override
	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		if (emailAddressP != null && (kB.getPeerSemanticTag(emailAddressP) != null)) {
			PHNXBusinessCard card = new PHNXBusinessCardImpl(this, kB, kB.getPeerSemanticTag(emailAddressP));
			return card;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException {
		kB.getPeerSTSet().removeSemanticTag(kB.getPeerSemanticTag(emailAddressP));
	}
//sdfsd
	@Override
	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, String degreeP, Date departureP,
			Date arrivalP, PHNXPicture pictureP) throws SharkKBException {
		new PHNXBusinessCardImpl(this, kB, nameP, contactP, organizationSubjectIdentifierP, degreeP, departureP, arrivalP, pictureP);
		// die Skills bekommt man wieder raus anhand von getPHNXResource (null, PHNX_SKILL, SI der BusinessCard[0])
		// dito für die profession (null, PHNX_PROFESSION, SI der BusinessCard[0])
	}

	@Override
	public void removePHNXResource(String nameP, PHNXResource.RessourceType typP, String ownerP) throws SharkKBException {
		if (nameP != null && typP != null && ownerP != null) {
			ContextPoint cp = getPHNXResourceAsCP(nameP, typP, ownerP).nextElement();
			kB.removeContextPoint(cp.getContextCoordinates());
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void createPHNXResource(PHNXResource.RessourceType type, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP,
			PHNXPicture pictureP) throws PHNXException, SharkKBException {
		new PHNXResourceImpl(this, kB, type, resourceNameP, ownerIdentifierP, contactPersonP, amountP, pictureP);
	}

	@Override
	public void editPHNXResource(String resourceNameP, PHNXResource.RessourceType resourceTypeP, String ownerIdentifierP,
			PHNXResource.RessourceType changeResourceTypeP, String changeResourceNameP, String changeOwnerIdentifierP, String changeContactPersonP,
			String changeAmountP, PHNXPicture changePictureP) throws SharkKBException {
		if (resourceTypeP == null && resourceNameP != null && ownerIdentifierP != null) {
			ContextPoint resource = getPHNXResourceAsCP(resourceNameP, resourceTypeP, ownerIdentifierP).nextElement();
			if (changeResourceTypeP != null) {
				resource.getContextCoordinates().getTopic().addSI(getResourceTypeSI(changeResourceTypeP));
				resource.getContextCoordinates().getTopic().removeSI(getResourceTypeSI(resourceTypeP));
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
		if (wWWAddressP != null && (kB.getPeerSemanticTag(wWWAddressP) != null)) {
			PHNXOrganization organization = new PHNXOrganizationImpl(this, kB, kB.getPeerSemanticTag(wWWAddressP));
			return organization;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void editPHNXBusinessCard(String subjectIdentifierP, String changeSubjectIdentifierP, PHNXName changeNameP, PHNXContact changeContactP,
			String changeOrganizationSubjectIdentifierP, String changeDegreeP, Date changeDepartureP, Date changeArrivalP, PHNXPicture changePictureP)
			throws SharkKBException {
		PeerSemanticTag pst = kB.getPeerSemanticTag(subjectIdentifierP);
		if (changeSubjectIdentifierP != null) {
			pst.addSI(changeSubjectIdentifierP);
			pst.removeSI(subjectIdentifierP);
		}
		if (changeNameP != null) {
			if (changeNameP.getFirstName() != null) {
				pst.setProperty("PHNX_Name_firstName", changeNameP.getFirstName());
			}
			if (changeNameP.getLastName() != null) {
				pst.setProperty("PHNX_Name_lastName", changeNameP.getLastName());
			}
			if (changeNameP.getMiddleNames() != null) {
				pst.setProperty("PHNX_Name_middleNames", concatenateStringiteratorToString(changeNameP.getMiddleNames()));
			}
		}
		if (changeContactP != null) {
			if (changeContactP.getWwwAddress() != null) {
				pst.setProperty("PHNX_Contact_wwwAddress", changeContactP.getWwwAddress());
			}
			if (changeContactP.getMobileNumber() != null) {
				pst.setProperty("PHNX_Contact_privateMobileNumber", changeContactP.getMobileNumber());
			}
			if (changeContactP.getLandLineNumber() != null) {
				pst.setProperty("PHNX_Contact_privateLandLineNumber", changeContactP.getLandLineNumber());
			}
		}
		if (changeOrganizationSubjectIdentifierP != null) {
			pst.setProperty("PHNX_Organization_SI", changeOrganizationSubjectIdentifierP);
		}
		if (changeDegreeP != null) {
			pst.setProperty("PHNX_printableProfessionalDegree", changeDegreeP);
		}
		if (changeDepartureP != null) {
			pst.setProperty("PHNX_departure", String.valueOf(changeDepartureP.getTime()));
		}
		if (changeArrivalP != null) {
			pst.setProperty("PHNX_arrival", String.valueOf(changeArrivalP.getTime()));
		}
		if (changePictureP != null) {
			// todo
		}
	}

	@Override
	public void createPHNXOrganization(String nameP, String wwwAddressP, String contactPersonEmailP, PHNXPicture logoP) throws SharkKBException {
		new PHNXOrganizationImpl(this, kB, nameP, wwwAddressP, contactPersonEmailP, logoP);
	}

	@Override
	public void editPHNXOrganization(String wwwAddressP, String changeWwwAddressP, String changeNameP, String changeContactPersonEmailP, PHNXPicture changeLogoP)
			throws SharkKBException {
		PeerSemanticTag pst = kB.getPeerSemanticTag(wwwAddressP);
		if (changeWwwAddressP != null) {
			pst.addSI(changeWwwAddressP);
			pst.removeSI(wwwAddressP);
		}
		if (changeNameP != null) {
			pst.setProperty("PHNX_Organization_Name", changeNameP);
		}
		if (changeContactPersonEmailP != null) {
			pst.setProperty("PHNX_Organization_contactPersonEmail", changeContactPersonEmailP);
		}
		if (changeLogoP != null) {
			// todo
		}
	}

	@Override
	public void removePHNXOrganization(String wwwAddressP) throws SharkKBException {
		kB.getPeerSTSet().removeSemanticTag(kB.getPeerSemanticTag(wwwAddressP));
	}

	private String concatenateStringiteratorToString(Iterator<String> iterator) {
		String concatenation = "";
		while (iterator.hasNext()) {
			concatenation += iterator.next() + " ";
		}
		return concatenation;
	}

	@Override
	public void createPHNXMapPOI(String wktStringP, String pointNameP, String pointDescriptionP, String pointCategorieP, String pointIdentifierP)
			throws SharkKBException {
		new PHNXMapPOIImpl(kB, wktStringP, pointNameP, pointDescriptionP, pointCategorieP, pointIdentifierP);
	}

	@Override
	public PHNXMapPOI getPHNXMapPOI(String pointIdentifierP) throws SharkKBException {
		if (pointIdentifierP != null && (kB.getSemanticTag(pointIdentifierP) != null)) {
			PHNXMapPOI MapPOI = new PHNXMapPOIImpl(kB, kB.getSemanticTag(pointIdentifierP));
			return MapPOI;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void editPHNXMapPOI(String pointIdentifierP, String changeWktStringP, String changePointNameP, String changePointDescriptionP,
			String changePointCategorieP, String changePointIdentifierP) throws SharkKBException {
		SemanticTag st = kB.getSemanticTag(pointIdentifierP);
		if (changePointIdentifierP != null) {
			st.addSI(changePointIdentifierP);
			st.removeSI(pointIdentifierP);
		}
		if (changeWktStringP != null) {
			st.setProperty("PHNX_MapPOI_wktString", changeWktStringP);
		}
		if (changePointNameP != null) {
			st.setProperty("PHNX_MapPOI_name", changePointNameP);
		}
		if (changePointDescriptionP != null) {
			st.setProperty("PHNX_MapPOI_description", changePointDescriptionP);
		}
		if (changePointCategorieP != null) {
			st.setProperty("PHNX_MapPOI_categorie", changePointCategorieP);
		}
	}

	@Override
	public void removePHNXMapPOI(String pointIdentifierP) throws SharkKBException {
		String[] tempArray = new String[1];
		tempArray[0] = pointIdentifierP;
		kB.removeSemanticTag(tempArray);
	}
}