package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;

public interface PHNXOrganization {
	public String getName();

	public String getWwwAddress();

	public String getContactPersonEmailAddress();

	public PHNXPicture getLogo();

	public Iterator<PHNXResource> getResources() throws SharkKBException;

	// public PHNXLocation getPHNXLocation();
}
