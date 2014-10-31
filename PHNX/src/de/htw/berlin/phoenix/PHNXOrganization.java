package de.htw.berlin.phoenix;

import java.util.Iterator;

public interface PHNXOrganization {
	String getName();
	Iterator<String> getWwwAddress();
	void setName(String name);
	PHNXPicture getLogo();
	//void setLogo(PHNXPicture);
}
