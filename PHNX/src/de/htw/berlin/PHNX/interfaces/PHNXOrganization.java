/** Interface PHNXOrganization represents methods used to access information about a PHNX organization.
 * A PHNX organization is the owner of any resources. In order to utilize a resource in a catastrophe relief area,
 * one has to go through an organization who owns the resource (be it machinery, medication or a specialist with a certain
 * set of skills. Thus organization is a central entity of PHNX application.
 * An organization has the following obligatory attributes:
 * - A name.
 * - A www address - which is used to identify an organization and compare the names (since
 * the names could be written differently (e.g. different spelling, different languages) but the www link
 * usually stays the same.
 * - a contact person's email address. As told by Customer, usually the organizations active in a certain
 * catastrophe relief area have small groups in the area, which normally have one contact person. 
 * The contact person is the responsible person you need to go through to get to a resource you need (e.g. ask for
 * medication, equipment, a specialist's help).
 * - resources - the equipment, medication, food supplies, skills of skilled workers the organization
 * owns
 * The following attributes would be optional:
 * - a logo - a picture of the organization. Helps to quickly identify the organization.
 * - a location - where the organization is currently located in the affected area.
 */

package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;

public interface PHNXOrganization {
	
	/**Allows access to the name of the organization.
	 * 
	 * @return the name of the organization in String format.
	 */
	public String getName();

	/**Allows access to the www address of the organization. The address of the organization
	 * is used as a unique identifier to compare organizations and check if any organizations are 
	 * semantically the same.
	 * 
	 * @return URI address of the organization
	 */
	public String getWwwAddress();

	/**Allows access to the email address of the contact person for this particular organization.
	 * The email address serves as a unique identifier so this is an obligatory attribute 
	 * as opposed to a person's name.
	 * 
	 * @return the email address in String format
	 */
	public String getContactPersonEmailAddress();

	/**Allows access to the organization's logo/picture.
	 * 
	 * @return the organization's logo in PHNXPicture format
	 */
	public PHNXPicture getLogo();

	/**Allows access to the resources offered by the organization (equipment, medical 
	 * supplies, food supplies, employees' skills etc.)
	 * 
	 * @return an iterator containing all resources of this organization
	 * @throws SharkKBException
	 */
	public Iterator<PHNXResource> getResources() throws SharkKBException;

	/**Allows access to the organization location locally, in the catastrophe relief area.
	 * 
	 * @return the location of the organization in PHNXLocation format
	 */
	// public PHNXLocation getPHNXLocation();
}
