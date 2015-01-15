package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import net.sharkfw.knowledgeBase.PeerSemanticTag;
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

	public PHNXOrganizationImpl(PHNXSharkEngine phnxEngineP, SharkKB kBP, String nameP, String wwwAddressP, String contactPersonEmailP, PHNXPicture logoP)
			throws SharkKBException {
		if (nameP != null && wwwAddressP != null) {
			phnxEngine = phnxEngineP;
			kb = kBP;
			pst = kb.createPeerSemanticTag("PHNX_Organization_SI", wwwAddressP, "null");
			pst.setProperty("PHNX_Organization_Name", nameP);
			if (contactPersonEmailP != null) {
				pst.setProperty("PHNX_Organization_contactPersonEmail", contactPersonEmailP);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public PHNXOrganizationImpl(PHNXSharkEngine phnxEngineP, SharkKB kBP, PeerSemanticTag organizationP) {
		kb = kBP;
		phnxEngine = phnxEngineP;
		pst = organizationP;
	}

	@Override
	public String getName() {
		return pst.getProperty("PHNX_Organization_Name");
	}

	@Override
	public String getWwwAddress() {
		return pst.getSI()[0];
	}

	@Override
	public PHNXPicture getLogo() {
		// todo
		return null;
	}

	@Override
	public Iterator<PHNXResource> getResources() throws SharkKBException {
		return phnxEngine.getPHNXResource(null, null, pst.getSI()[0]);
	}

	@Override
	public String getContactPersonEmailAddress() {
		return pst.getProperty("PHNX_Organization_contactPersonEmail");
	}
}
