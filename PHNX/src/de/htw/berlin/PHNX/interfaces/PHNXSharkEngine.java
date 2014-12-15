package de.htw.berlin.PHNX.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;
import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNXSharkEngine {

	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, String degreeP, Date departureP,
			Date arrivalP, PHNXPicture pictureP) throws PHNXException, SharkKBException, ParseException;

	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException;

	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException;

	public void createPHNXResource(String resourceTypeP, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP,
			PHNXPicture pictureP) throws PHNXException, SharkKBException;

	public Iterator<PHNXResource> getPHNXResource(String nameP, String TypP, String ownerP) throws SharkKBException;

	public void editPHNXResource(String resourceNameP, String resourceTypeP, String ownerIdentifierP, String changeResourceTypeP, String changeResourceNameP,
			String changeOwnerIdentifierP, String changeContactPersonP, String changeAmountP, PHNXPicture changePictureP) throws SharkKBException;

	public void removePHNXResource(String nameP, String TypP, String ownerP) throws SharkKBException;

	public PHNXOrganization getPHNXOrganization(String wWWAddressP) throws SharkKBException;

}