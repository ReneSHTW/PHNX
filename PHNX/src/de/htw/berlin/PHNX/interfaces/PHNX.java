package de.htw.berlin.PHNX.interfaces;

import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNX {

	public PHNXBusinessCard getBusinessCard(String emailAddress);

	public PHNXResource getPHNXResource(String type); //Verschiedene keys m�glich machen

	public PHNXOrganization getOrganization(String wwwAddress);

	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException;

	public void setPHNXResource(PHNXResource value) throws PHNXException;

	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException;
	
	public void removePHNXBusinessCard(PHNXBusinessCard value) throws PHNXException;
		
	//Create Methoden einf�gen

	// PHNXMap getPHNXMap();
}
