/**PHNXResource interface allows access to the resources saved in the PHNX app.
 * The resources are employees' skills and abilities, equipment, supplies etc. which belong to
 * and are distributed by the organizations in the catastrophe relief areas.  
 * 
 */
package de.htw.berlin.PHNX.interfaces;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

/*Als Topic speichern
 * Subject Identifier finden*/
public interface PHNXResource {

	public enum RessourceType {
		PHNX_EQUIPMENT, PHNX_MEDICINE, PHNX_PROFESSION, PHNX_SKILL
	}

	/**The URIs are not the real links to the resources. They are meant to be used as unique
	 * Subject Identifiers (SI). 
	 */
	public static final String PHNX_EQUIPMENT_SI = "http:/phnx.berlin/shark/equipment.html";
	public static final String PHNX_MEDICINE_SI = "http:/phnx.berlin/shark/medicine.html";
	public static final String PHNX_PROFESSION_SI = "http:/phnx.berlin/shark/profession.html";
	public static final String PHNX_SKILL_SI = "http:/phnx.berlin/shark/skill.html";

	/**Allows access to the type of the resource (whether the resource is a piece of equipment,
	 * a medicine, a skill, a profession etc). 
	 * 
	 * @return the resource type in String format
	 */
	public String getResourceType();

	/**Allows access to the name of the resource (e.g. Bagger L05er) 
	 * 	
	 * @return the name of the resource in String format
	 */
	public String getResourceName();

	/**Allows access to the resource owner's Subject Identifier. The resource owner is an
	 * organization.
	 * 
	 * @return the resource owner's Subject Identifier (SI) in String format
	 */
	public String getOwnerSI();

	/**Allows access to the Subject Identifier of the contact person for this resource. 
	 * The contact person is the person responsible for a given resource at the organization
	 * this resource belongs to.
	 * 
	 * @return contact person's Subject Identifier in String format
	 */
	public String getContactPersonSI();

	/**Returns the available amount of the resource for the given organization.
	 * Note: the available amount means the amount the organization has agreed to share 
	 * with external organizations. It also happens that certain organizations own
	 * certain resources which they are not willing to share.
	 * 
	 * @return the amount of the resource in String(!) format
	 * @throws SharkKBException
	 */
	public String getAmount() throws SharkKBException;

	/**Allows access to the resources picture. 
	 * Optional. For some resources it doesn't make sense to have pictures.
	 * Some other resources, like machinery, are nice to get a picture of, for 
	 * the purpose of unique identification.
	 * 
	 * @return picture in PNHXPicture format
	 */
	public PHNXPicture getPicture();

	// public PHNXLocation getPHNXCoordinates();
}