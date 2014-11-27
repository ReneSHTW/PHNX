package de.htw.berlin.PHNX.interfaces;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {

	public String getResourceType();

	public String getResourceName();

	public PHNXOrganization getOwnerOrganization();

	public void setOwnerOrgianization(PHNXOrganization value);

	public PHNXBusinessCard getContactPerson();

	public void setContactPerson(PHNXBusinessCard value);

	public String getAmount();

	public void setAmount(String value);

	public PHNXPicture getPicture();

	public void setPicture(PHNXPicture value);

	// public PHNXLocation getPHNXCoordinates();
}
