package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSearchResult;

public class PHNXSearchResultImpl implements PHNXSearchResult{

	@Override
	public Iterator<PHNXSearchResult> getSearchResults(boolean buttonPerson,
			boolean buttonOrganization, boolean buttonResource,
			boolean buttonMap, String searchInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<PHNXBusinessCard> getBusinessCard(String searchInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<PHNXOrganization> getOrganizations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<PHNXResource> getResources() {
		// TODO Auto-generated method stub
		return null;
	}

}
