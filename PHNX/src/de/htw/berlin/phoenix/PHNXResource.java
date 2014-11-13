package de.htw.berlin.phoenix;

public interface PHNXResource {

	String getResourceType();

	String getResourceName();

	String getOwnerOrganization();

	String getContactPerson();

	int getAmount();

	PHNXPicture getPicture();
}
