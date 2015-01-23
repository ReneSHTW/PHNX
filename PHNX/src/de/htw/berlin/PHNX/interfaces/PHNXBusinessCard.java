package de.htw.berlin.PHNX.interfaces;

/** Public interface PHNXBusinessCard represents methods to provide access to the information about persons saved in  
 * in the Phoenix app. Each person, including the user/owner of the app, has a PHNXBusinessCard.
 */

import java.util.Date;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;

/*Als Peer Semantik Tag dem PHNXContact zuordnen*/
public interface PHNXBusinessCard {
	
	/**getName() provides access to the name of a person (the app user/owner or any other person whose information is saved in the app. 
	 * The name will consist of first name, last name, an optional middle name, and an optional nickname.
	 * 
	 * @return the name of the person according to the standard defined in PHNXName interface
	 */
	public PHNXName getName();
	
	/**getContact() provides access to a stored person's contact information.
	 * The contact will consist of email address, optional phone number, optional post address, optional link to a personal website.
	 * 
	 * @return the contact information of the person according to the standard defined in PHNX interface
	 */
	public PHNXContact getContact();

	/**getOrganization() provides access to the organization information of the person.
	 * The organization will consist of organization name, optional logo picture, organization Internet address, organization email address, and contact person.
	 * 
	 * @return the organization information of the person according to the standard defined in PHNXContact interface
	 * @throws SharkKBException if the organization doesn't exist
	 */
	public PHNXOrganization getOrganization() throws SharkKBException;

	/**getProfession() provides access to the person's profession.
	 * The profession is the job they are performing currently in the NGO on this particular mission.
	 * 
	 * @return the person's profession according to the standard defined in PHNXOrganization interface
	 * @throws SharkKBException if profession doesn't exist
	 */
	public PHNXResource getProfession() throws SharkKBException;

	/**getPrintableProfessionalDegree() provides access to the information on the person's professional degree and/or qualifications.
	 * The professional degree here means the learned skills which qualify the person to do a certain job, since the person's education may not reflect in their current job position (e.g. medical nurse, a rocket scientist, a brain surgeon).
	 * 
	 * @return String containing the person's professional degree/qualifications in printable form
	 */
	public String getPrintableProfessionalDegree();

	/**getSkills() provides access to the person's skills.
	 * Skills are what the person can offer while being on the current mission (e.g. perform surgeries, translate between German and Russian, etc).
	 * Skills might differ from the professional degree and don't have to only contain professional skills.
	 * @return an Iterator with PHNXResourse since skills are considered a resource (resources are skills, medicine, food, tools, all kinds of equipment, etc).
	 * @throws SharkKBException if there are no skills saved
	 */
	public Iterator<PHNXResource> getSkills() throws SharkKBException;

	/**getArrival()provides access to the arrival date (the date when the owner of PHNX app arrives to the catastrophe territory).
	 * The date is important because information validity can be checked based on the date.
	 * @return the arrival date in Date format
	 */
	public Date getArrival();
	
	/**getDeparture() provides access to the departure date (the date when the owner of PHNX app is scheduled to leave the catastrophe area).
	 * The date is important because information validity can be checked based on this date.
	 * The application could be set up so that all information saved in the app is deleted after this date.
	 * @return the departure date in Date format
	 */
	public Date getDeparture();

	/**getPicture() provides access to the photo/ picture of the app owner. 
	 * The format of the picture is to be decided during the implementation.
	 * 
	 * @return the picture or the path to the picture, depending on implementation.
	 */
	public PHNXPicture getPicture();
}
