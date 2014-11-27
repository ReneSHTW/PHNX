package de.htw.berlin.PHNX.interfaces;

import java.text.ParseException;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNX {

	public PHNXBusinessCard getBusinessCard(String emailAddress) throws PHNXException, SharkKBException, ParseException;

	public Iterator<PHNXResource> getPHNXResource(String type, String name) throws PHNXException, SharkKBException;

	public PHNXOrganization getOrganization(String wwwAddress) throws PHNXException, SharkKBException;

	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;

	public void setPHNXResource(PHNXResource value) throws PHNXException, SharkKBException;

	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;

	public void removePHNXBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;

	public void removePHNXResource(PHNXResource value) throws PHNXException, SharkKBException;

	public void removePHNXOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;

	// Create Methoden einfügen

	// PHNXMap getPHNXMap();
}
