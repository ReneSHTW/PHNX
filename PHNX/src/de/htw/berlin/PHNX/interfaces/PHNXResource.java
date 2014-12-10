package de.htw.berlin.PHNX.interfaces;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {

	public String getResourceType();

	public String getResourceName();

	public String getOwnerSI();

	public void setOwner(String ownerIdentifierP);

	public String getContactPersonSI();

	public void setContactPerson(String emailAddressP);

	public String getAmount();

	public void setAmount(String value);

	public PHNXPicture getPicture();

	public void setPicture(PHNXPicture value);

	// public PHNXLocation getPHNXCoordinates();
}