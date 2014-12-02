package de.htw.berlin.PHNX.interfaces;

import java.text.ParseException;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNX {

	public PHNXBusinessCard getBusinessCard(String emailAddress) throws PHNXException, SharkKBException, ParseException;

	public Iterator<PHNXResource> getResource(String type, String name) throws PHNXException, SharkKBException;

	public PHNXOrganization getOrganization(String wwwAddress) throws PHNXException, SharkKBException;

	public PHNXMapPOI getPointOfInterest(String pointIdentifier) throws PHNXException, SharkKBException;

	public void setBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;

	public void setResource(PHNXResource value) throws PHNXException, SharkKBException;

	public void setOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;

	public void setPointOfInterest(PHNXMapPOI value) throws PHNXException, SharkKBException;

	public void removeBusinessCard(PHNXBusinessCard value) throws PHNXException, SharkKBException;

	public void removeResource(PHNXResource value) throws PHNXException, SharkKBException;

	public void removeOrganization(PHNXOrganization value) throws PHNXException, SharkKBException;

	public void removePointOfInterest(PHNXMapPOI value) throws PHNXException, SharkKBException;
}
