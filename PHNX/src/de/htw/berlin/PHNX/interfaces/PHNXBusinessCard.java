package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;

/*Als Peer Semantik Tag dem PHNXContact zuordnen*/
public interface PHNXBusinessCard {
	public PHNXName getName();

	public PHNXContact getContact();

	public PHNXOrganization getOrganization() throws SharkKBException;

	public PHNXResource getProfession() throws SharkKBException;

	public String getPrintableProfessionalDegree();

	public Iterator<PHNXResource> getSkills() throws SharkKBException;

	public Date getArrival();

	public Date getDeparture();

	public PHNXPicture getPicture();
}
