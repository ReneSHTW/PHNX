package de.htw.berlin.PHNX.impl;

import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class PHNXResourceImpl implements PHNXResource {
	private SharkKB kb = null;
	private ContextPoint cp;
	private PHNXSharkEngine phnxEngine;

	/**
	 * That constructor is only used when a new (really new) resource is to be created.
	 * 
	 * @param kB
	 * @param ressourceTypeP
	 * @param resourceNameP
	 * @param ownerIdentifierP
	 * @param contactPersonP
	 * @param amountP
	 * @param pictureP
	 * @throws SharkKBException
	 */
	public PHNXResourceImpl(PHNXSharkEngine PHNXEngineP, SharkKB kBP, PHNXResource.RessourceType ressourceTypeP, String resourceNameP, String ownerIdentifierP,
			String contactPersonP, String amountP, PHNXPicture pictureP) throws SharkKBException {
		if (kBP != null && resourceNameP != null && ownerIdentifierP != null) {
			this.kb = kBP;
			this.phnxEngine = PHNXEngineP;

			Taxonomy topicTX = kb.getTopicsAsTaxonomy();

			// get (or create) tag representing resource type (not actual resource!)
			TXSemanticTag typeTag = phnxEngine.getRessourceTag(topicTX, ressourceTypeP);

			if (resourceNameP == null || resourceNameP.length() == 0) {
				resourceNameP = "unknown"; // TODO: wichtig: hier muss irgendwie ein eindeutiger Name erzeugt werden: Mit aktuellen Datum/Uhrzeit???!
			}

			String si = phnxEngine.getResourceTypeSI(ressourceTypeP) + "/" + resourceNameP;

			// create tag representing actual resource with unique (!) si
			TXSemanticTag resourceTag = topicTX.createTXSemanticTag(resourceNameP, si);

			// now tell taxonomy that this newly created tag is a sub concept of type tag
			resourceTag.move(typeTag);

			// get peer semantic tag that represents our owner
			PeerSemanticTag ownerPST = phnxEngine.getOwnerPST(this.kb, ownerIdentifierP);
			if (ownerPST == null) {
				throw new SharkKBException(); // error Text hinzufuegen
			}

			if (contactPersonP != null) {
				PeerSemanticTag contactPST = phnxEngine.getOwnerPST(this.kb, contactPersonP);
				if (contactPST == null) {
					throw new SharkKBException(); // error Text hinzufuegen
				}
				// let's create our context point that represents the resource
				ContextCoordinates cc = kb.createContextCoordinates(resourceTag, ownerPST, contactPST, null, null, null, SharkCS.DIRECTION_NOTHING);
				ContextPoint cp = kb.createContextPoint(cc);
				cp.addInformation(amountP);
			} else {
				// let's create our context point that represents the resource without contactPST
				ContextCoordinates cc = kb.createContextCoordinates(resourceTag, ownerPST, null, null, null, null, SharkCS.DIRECTION_NOTHING);
				ContextPoint cp = kb.createContextPoint(cc);
				cp.addInformation(amountP);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public PHNXResourceImpl(PHNXSharkEngine phnxEngineP, ContextPoint cPointP) throws SharkKBException {
		this.phnxEngine = phnxEngineP;
		this.cp = cPointP;
	}

	@Override
	public String getResourceType() {
		return cp.getContextCoordinates().getTopic().getSI()[0];
	}

	@Override
	public String getResourceName() {
		return cp.getContextCoordinates().getTopic().getName();
	}

	@Override
	public String getOwnerSI() {
		return cp.getContextCoordinates().getOriginator().getSI()[0];
	}

	@Override
	public String getContactPersonSI() {
		return this.cp.getContextCoordinates().getPeer().getSI()[0];
	}

	@Override
	public String getAmount() throws SharkKBException {
		return cp.getInformation().next().getContentAsString();
	}

	@Override
	public PHNXPicture getPicture() {
		return null;
	}
}