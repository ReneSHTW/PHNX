package de.htw.berlin.PHNX.interfaces;

import net.sharkfw.knowledgeBase.SharkKBException;
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

	public String getAmount() throws SharkKBException;

	public PHNXPicture getPicture();

	// public PHNXLocation getPHNXCoordinates();
}