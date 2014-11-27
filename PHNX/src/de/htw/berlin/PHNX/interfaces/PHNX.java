package de.htw.berlin.PHNX.interfaces;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNX {

	public PHNXBusinessCard getBusinessCard(String emailAddress);

	public PHNXResource getPHNXResource(String type); //Verschiedene keys möglich machen

	public PHNXOrganization getOrganization(String wwwAddress);

	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;

	public void setPHNXResource(PHNXResource value) throws PHNXException, SharkKBException;

	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;
	
	public void removePHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;
	
	public void removePHNXResource(PHNXResource value) throws PHNXException, SharkKBException;
	
	public void removePHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;
		
	//Create Methoden einfügen

	// PHNXMap getPHNXMap();
}
