package de.htw.berlin.PHNX.impl;

import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;

/*Als Peer Semantic Tag speichern*/
public class PHNXResourceImpl implements PHNXResource {

	private String resourceType;
	private String resourceName;
	private String owner;
	private String contactPerson;
	private String amount;
	private PHNXPicture picture;

	public PHNXResourceImpl(String resourceTypeP, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP, PHNXPicture pictureP) {
		if (resourceTypeP != null && resourceNameP != null && ownerIdentifierP != null) {
			resourceName = resourceNameP;
			resourceType = resourceTypeP;
			owner = ownerIdentifierP;
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
	public String getOwner() {
		return owner;
	}

	@Override
	public String getContactPerson() {
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
	public void setOwner(String ownerIdentifierP) {
		if (ownerIdentifierP != null) {
			owner = ownerIdentifierP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setContactPerson(String emailAddressP) {
		if (emailAddressP != null) {
			contactPerson = emailAddressP;
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
