package de.htw.berlin.PHNX.interfaces;

import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNX {

	PHNXBusinessCard getBusinessCard(String key);

	PHNXResource getPHNXResource();

	PHNXOrganization getOrganization();

	void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException;

	void setPHNXResource(PHNXResource value) throws PHNXException;

	void setPHNXOrganization(PHNXOrganization value) throws PHNXException;

	// PHNXMap getPHNXMap();
}
