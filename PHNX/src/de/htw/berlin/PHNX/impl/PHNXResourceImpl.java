package de.htw.berlin.PHNX.impl;

import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;

/*Als Peer Semantic Tag speichern*/
public class PHNXResourceImpl implements PHNXResource {

	private String resourceType;
	private String resourceName;
	private PHNXOrganization ownerOrganization;
	private PHNXBusinessCard contactPerson;
	private String amount;
	private PHNXPicture picture;

	public PHNXResourceImpl(String resourceTypeP, String resourceNameP, PHNXOrganization ownerOrganizationP, PHNXBusinessCard contactPersonP, String amountP,
			PHNXPicture pictureP) {
		if (resourceTypeP != null && resourceNameP != null && ownerOrganizationP != null) {
			resourceName = resourceNameP;
			resourceType = resourceTypeP;
			ownerOrganization = ownerOrganizationP;
			contactPerson = contactPersonP;
			amount = amountP;
			picture = pictureP;
		} else {
			throw new IllegalArgumentException();
		}

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
	public PHNXOrganization getOwnerOrganization() {
		return ownerOrganization;
	}

	@Override
	public PHNXBusinessCard getContactPerson() {
		return contactPerson;
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
	public void setOwnerOrgianization(PHNXOrganization value) {
		if (value != null) {
			ownerOrganization = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setContactPerson(PHNXBusinessCard value) {
		if (value != null) {
			contactPerson = value;
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
