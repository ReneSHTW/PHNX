package de.htw.berlin.PHNX.interfaces;

import de.htw.berlin.PHNX.impl.PHNXException;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {
	
	public enum RessourceType {
	    PHNX_EQUIPMENT, PHNX_MEDICINE 
	}
	
	public static final String PHNX_EQUIPMENT_SI = "http:/phnx.berlin/shark/equipment.html";
	public static final String PHNX_MEDICINE_SI = "http:/phnx.berlin/shark/equipment.html";

	public String getResourceType();

	public String getResourceName();

	public String getOwnerSI();

	public String getContactPersonSI();

	public String getAmount();

	public PHNXPicture getPicture();

	public void setOwner(PHNXSharkEngine engine, String ownerIdentifierP) throws PHNXException;

	public void setContactPerson(PHNXSharkEngine engine, String emailAddressP) throws PHNXException;

	public void setAmount(PHNXSharkEngine engine, String value) throws PHNXException;

	public void setPicture(PHNXSharkEngine engine, PHNXPicture value) throws PHNXException;

	// public PHNXLocation getPHNXCoordinates();
}