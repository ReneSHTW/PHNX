package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXOrganization {
	public String getName();

	public String getWwwAddress();

	public String getContactPersonEmailAddress();

	public PHNXPicture getLogo();

	public Iterator<PHNXResource> getResources();

	// public PHNXLocation getPHNXLocation();
}
