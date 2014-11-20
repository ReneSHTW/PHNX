package de.htw.berlin.PHNX.impl;

import de.htw.berlin.PHNX.interfaces.PHNX;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htwberlin.phnx.BusinessCardActivity;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.filesystem.FSSharkKB;

public class PHNXEngine implements PHNX {

	private static PHNXEngine phnxEngine = null;
	private SharkKB sharkKB;

	private PHNXEngine(/* eventuell Folder Name */) throws PHNXException {

		String folderName = "keineAhnung";
		try {
			this.sharkKB = new FSSharkKB(folderName);
		} catch (SharkKBException e) {
			throw new PHNXException(e);
		}
	}

	public static PHNXEngine getPHNXEngine() throws PHNXException {
		if (phnxEngine == null) {
			phnxEngine = new PHNXEngine();
		}
		return phnxEngine;
	}

	@Override
	public PHNXBusinessCard getBusinessCard(String key) {
		// sharkKB nach key durchsuchen
		// gefundene BuisnessCard returnen

		return null;
	}

	@Override
	public PHNXResource getPHNXResource() {

		return null;
	}

	@Override
	public PHNXOrganization getOrganization() {

		return null;
	}

	@Override
	public void setPHNXBusinessCard(PHNXBusinessCard value) throws PHNXException {
		//speichere value in KB
		
	}

	@Override
	public void setPHNXResource(PHNXResource value) throws PHNXException {

	}

	@Override
	public void setPHNXOrganization(PHNXOrganization value) throws PHNXException {

	}

}
