package de.htw.berlin.phoenix;

import java.util.Iterator;

public interface PHNX {

	PHNXBusinessCard getBusinessCard();

	Iterator<PHNXSearchResult> getSearchResults(boolean buttonPerson,
			boolean buttonOrganization, boolean buttonResource,
			boolean buttonMap, String searchInput);
	
	PHNXResource getPHNXResource();
	
	PHNXOrganization getOrganziation();
	
//	PHNXMap getPHNXMap();
}
