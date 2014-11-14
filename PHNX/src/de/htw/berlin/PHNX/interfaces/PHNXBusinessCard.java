package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;

public interface PHNXBusinessCard {
	PHNXName getName();

	PHNXContact getContact();

	PHNXOrganization getOrganization();

	PHNXResource getProfession();

	String getPrintableProfessionalDegree();

	Iterator<PHNXResource> getSkills();

	Date getArrival();

	Date getDeparture();
}
