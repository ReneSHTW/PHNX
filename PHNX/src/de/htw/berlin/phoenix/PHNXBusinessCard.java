package de.htw.berlin.phoenix;

import java.util.Date;

public interface PHNXBusinessCard {
	PHNXName getName();
	PHNXContact getContact();
	PHNXOrganization getOrganization();
	PHNXProfession getProfessionalDegree();
	PHNXSkill getPHNXSkills();
	Date getArrival();
	Date getDeparture();
}
