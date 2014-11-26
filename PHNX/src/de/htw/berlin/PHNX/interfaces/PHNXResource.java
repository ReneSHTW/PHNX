package de.htw.berlin.PHNX.interfaces;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {

	String getResourceType();

	String getResourceName();

	PHNXOrganization getOwnerOrganization();

	PHNXBusinessCard getContactPerson();

	String getAmount();

	PHNXPicture getPicture();
	
//	PHNXLocation getPHNXCoordinates();
}
