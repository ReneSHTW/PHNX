package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXSearchResult {

	Iterator<PHNXSearchResult> getSearchResults(boolean buttonPerson,
			boolean buttonOrganization, boolean buttonResource,
			boolean buttonMap, String searchInput);

	Iterator<PHNXBusinessCard> getBusinessCard(String searchInput);

	Iterator<PHNXOrganization> getOrganizations();

	Iterator<PHNXResource> getResources();

	// Iterator<PHNXLocation> getCoordinates();
}
