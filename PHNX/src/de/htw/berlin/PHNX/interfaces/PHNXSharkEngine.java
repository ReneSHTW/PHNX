package de.htw.berlin.PHNX.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import net.sharkfw.knowledgeBase.SharkKBException;

import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNXSharkEngine {

	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, PHNXResource professionP, String degreeP,
			Iterator<PHNXResource> skillsP, Date departureP, Date arrivalP, PHNXPicture pictureP) throws PHNXException, SharkKBException, ParseException;

	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException;

	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException;

	public PHNXResource createPHNXResource() throws PHNXException, SharkKBException;

	public void removePHNXResource(String nameP, String TypP, String owner);

}