package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXOrganizationImpl implements PHNXOrganization {

	private SharkKB kb;
	private PeerSemanticTag pst;
	private PHNXSharkEngine phnxEngine;

	public PHNXOrganizationImpl(PHNXSharkEngine phnxEngineP, SharkKB kBP, String nameP, String wwwAddressP, PHNXPicture logoP, Iterator<PHNXResource> resourcesP)
			throws SharkKBException {
		if (nameP != null && wwwAddressP != null) {
			phnxEngine = phnxEngineP;
			kb = kBP;
			pst = kb.createPeerSemanticTag("PHNX_Organization_SI", wwwAddressP, "null");

		} else {
			throw new IllegalArgumentException();
		}
	}

	public PHNXOrganizationImpl(SemanticTag organizationP) {

	}

	@Override
	public String getName() {
		// return name;
		return null;
	}

	@Override
	public String getWwwAddress() {
		// return wwwAddress;
		return null;
	}

	@Override
	public PHNXPicture getLogo() {
		// return logo;
		return null;
	}

	@Override
	public Iterator<PHNXResource> getResources() {
		// return resources;
		return null;
	}

	@Override
	public String getContactPersonEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}
