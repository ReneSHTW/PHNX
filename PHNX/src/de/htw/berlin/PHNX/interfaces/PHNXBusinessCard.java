package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;

/*Als Peer Semantik Tag dem PHNXContact zuordnen*/
public interface PHNXBusinessCard {
	public PHNXName getName();

	public void setPHNXName(PHNXName value);

	public PHNXContact getContact();

	public void setPHNXContact(PHNXContact value);

	public PHNXOrganization getOrganization();

	public void setPHNXOrganization(PHNXOrganization value);

	public PHNXResource getProfession();

	public void setPHNXResource(PHNXResource value);

	public String getPrintableProfessionalDegree();

	public void setPrintableProfessionalDegree(String value);

	public Iterator<PHNXResource> getSkills();

	public void addSkill(PHNXResource value);

	public void removeSkill(String value);

	public Date getArrival();

	public void setArrival(Date value);

	public Date getDeparture();

	public void setDeparture(Date value);

	public PHNXPicture getPicture();

	public void setPicture(PHNXPicture value);
}
