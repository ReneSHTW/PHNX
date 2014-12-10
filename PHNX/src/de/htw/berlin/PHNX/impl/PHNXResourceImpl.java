package de.htw.berlin.PHNX.impl;

import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

/*Als Peer Semantic Tag speichern*/
public class PHNXResourceImpl implements PHNXResource {

	private String resourceType;
	private String resourceName;
	private String ownerSI;
	private String contactPersonSI;
	private String amount;
	private PHNXPicture picture;

	public PHNXResourceImpl(String resourceTypeP, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP, PHNXPicture pictureP) {
		if (resourceTypeP != null && resourceNameP != null && ownerIdentifierP != null) {
			resourceName = resourceNameP;
			resourceType = resourceTypeP;
			ownerSI = ownerIdentifierP;
			contactPersonSI = contactPersonP;
			amount = amountP;
			picture = pictureP;
		} else {
			throw new IllegalArgumentException();
		}

	}

	public PHNXResourceImpl(ContextPoint pointP) throws SharkKBException {
		resourceType = pointP.getContextCoordinates().getTopic().getSI()[0];
		resourceName = pointP.getContextCoordinates().getTopic().getName();
		ownerSI = pointP.getContextCoordinates().getOriginator().getSI()[0];
		contactPersonSI = pointP.getContextCoordinates().getPeer().getSI()[0];
		amount = pointP.getInformation().next().getContentAsString();
		picture = null;
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public String getOwnerSI() {
		return ownerSI;
	}

	@Override
	public String getContactPersonSI() {
		return contactPersonSI;
	}

	@Override
	public String getAmount() {
		return amount;
	}

	@Override
	public PHNXPicture getPicture() {
		return picture;
	}

	@Override
	public void setOwner(String ownerIdentifierP) {
		if (ownerIdentifierP != null) {
			ownerSI = ownerIdentifierP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setContactPerson(String emailAddressP) {
		if (emailAddressP != null) {
			contactPersonSI = emailAddressP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setAmount(String value) {
		if (value != null) {
			amount = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPicture(PHNXPicture value) {
		if (value != null) {
			picture = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

}