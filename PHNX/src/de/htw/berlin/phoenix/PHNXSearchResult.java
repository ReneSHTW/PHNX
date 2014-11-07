package de.htw.berlin.phoenix;

import java.util.Iterator;

public interface PHNXSearchResult {
	Iterator<PHNXBusinessCard> getBusinessCard(String searchInput);
	Iterator<PHNXOrganization> getOrganizations();
	Iterator<PHNXResource> getResources();
//	Iterator<PHNXCoordinates> getCoordinates();
}
