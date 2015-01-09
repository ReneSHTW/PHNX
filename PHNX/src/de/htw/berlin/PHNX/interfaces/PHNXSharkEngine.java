package de.htw.berlin.PHNX.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import de.htw.berlin.PHNX.impl.PHNXException;

public interface PHNXSharkEngine {

	public void createPHNXBusinessCard(PHNXName nameP, PHNXContact contactP, String organizationSubjectIdentifierP, String degreeP, Date departureP,
			Date arrivalP, PHNXPicture pictureP) throws PHNXException, SharkKBException, ParseException; // fertig

	public PHNXBusinessCard getPHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException; //fertig
	
	public void editPHNXBusinessCard();

	public void removePHNXBusinessCard(String emailAddressP) throws PHNXException, SharkKBException; //fertig

	public void createPHNXResource(PHNXResource.RessourceType type, String resourceNameP, String ownerIdentifierP, String contactPersonP, String amountP,
			PHNXPicture pictureP) throws PHNXException, SharkKBException; //fertig

	public Iterator<PHNXResource> getPHNXResource(String nameP, PHNXResource.RessourceType TypP, String ownerP) throws SharkKBException; //fertig

	public void editPHNXResource(String resourceNameP, PHNXResource.RessourceType resourceTypeP, String ownerIdentifierP,
			PHNXResource.RessourceType changeResourceTypeP, String changeResourceNameP, String changeOwnerIdentifierP, String changeContactPersonP,
			String changeAmountP, PHNXPicture changePictureP) throws SharkKBException; //fertig

	public void removePHNXResource(String nameP, PHNXResource.RessourceType TypP, String ownerP) throws SharkKBException; //fertig
	
	public void createPHNXOrganization()throws SharkKBException;

	public PHNXOrganization getPHNXOrganization(String wWWAddressP) throws SharkKBException;
	
	public void editPHNXOrganization();
	
	public void removePHNXOrganization();

	public TXSemanticTag getRessourceTag(Taxonomy topicsTX, PHNXResource.RessourceType type) throws SharkKBException; //fertig

	public String getResourceTypeSI(PHNXResource.RessourceType type); //fertig

	public PeerSemanticTag getOwnerPST(SharkKB kb, String si) throws SharkKBException; //fertig
}