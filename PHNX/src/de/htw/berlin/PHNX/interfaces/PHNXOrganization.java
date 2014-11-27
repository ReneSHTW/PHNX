package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXOrganization {
	public String getName();

	public void setName(String value);

	public String getWwwAddress();

	public void setWwwAddress(String value);

	public PHNXPicture getLogo();

	public void setLogo(PHNXPicture value);

	public Iterator<PHNXResource> getResources();

	public void addResource(PHNXResource value);

	// public PHNXLocation getPHNXLocation();
}
