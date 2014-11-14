package de.htw.berlin.PHNX.interfaces;

public interface PHNXResource {

	String getResourceType();

	String getResourceName();

	PHNXOrganization getOwnerOrganization();

	PHNXContact getContactPerson();

	int getAmount();

	PHNXPicture getPicture();
	
//	PHNXLocation getPHNXCoordinates();
}
