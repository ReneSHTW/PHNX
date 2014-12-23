package de.htw.berlin.PHNX.interfaces;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {

	public enum RessourceType {
		PHNX_EQUIPMENT, PHNX_MEDICINE, PHNX_PROFESSION, PHNX_SKILL
	}

	public static final String PHNX_EQUIPMENT_SI = "http:/phnx.berlin/shark/equipment.html";
	public static final String PHNX_MEDICINE_SI = "http:/phnx.berlin/shark/medicine.html";
	public static final String PHNX_PROFESSION_SI = "http:/phnx.berlin/shark/profession.html";
	public static final String PHNX_SKILL_SI = "http:/phnx.berlin/shark/skill.html";

	public String getResourceType();

	public String getResourceName();

	public String getOwnerSI();

	public String getContactPersonSI();

	public String getAmount() throws SharkKBException;

	public PHNXPicture getPicture();

	// public PHNXLocation getPHNXCoordinates();
}