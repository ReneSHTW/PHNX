package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;
/*Peer Dimension
 * Organisation als Root
 * jede einzelnde Organisation als instanz davon*/
public interface PHNXOrganization {
	String getName();

	String getWwwAddress();

	void setName(String name);

	PHNXPicture getLogo();

	void setLogo(PHNXPicture picture);

	Iterator<PHNXResource> getResources();
	
	void setResource(PHNXResource resource);
	
//	PHNXLocation getPHNXLocation();
}
