package de.htw.berlin.PHNX.impl;

import net.sharkfw.knowledgeBase.ContextCoordinates;
import net.sharkfw.knowledgeBase.ContextPoint;
import net.sharkfw.knowledgeBase.Information;
import net.sharkfw.knowledgeBase.PeerSemanticTag;
import net.sharkfw.knowledgeBase.STSet;
import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkCS;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import net.sharkfw.knowledgeBase.TXSemanticTag;
import net.sharkfw.knowledgeBase.Taxonomy;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

/*Als Peer Semantic Tag speichern*/
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
	public PHNXResourceImpl(PHNXSharkEngine phnxEngine, SharkKB kB, PHNXResource.RessourceType ressourceTypeP, String resourceNameP, String ownerIdentifierP,
			String contactPersonP, String amountP, PHNXPicture pictureP) throws SharkKBException {

		this.kb = kb;
		this.phnxEngine = phnxEngine;

		// was wenn contactPersonP null ist?
		if (kB != null && resourceNameP != null && ownerIdentifierP != null) {

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
				// panic - please handle that case here!
			}

			// ownerPST is not null here
			PeerSemanticTag contactPST = phnxEngine.getOwnerPST(this.kb, contactPersonP);
			if (contactPersonP == null) {
				// panic - please handle that case here!
			}

			// contactPST is not null here

			// let's create our context point that represents the resource
			ContextCoordinates cc = kb.createContextCoordinates(resourceTag, ownerPST, contactPST, null, null, null, SharkCS.DIRECTION_NOTHING);
			ContextPoint cp = kB.createContextPoint(cc);

			cp.addInformation(amountP);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public PHNXResourceImpl(PHNXSharkEngine phnxEngine, ContextPoint pointP) throws SharkKBException {
		this.phnxEngine = phnxEngine;
		this.cp = cp;
	}

	@Override
	public String getResourceType() {
		return resourceType;
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public String getOwnerSI() {
		return ownerSI;
	}

	@Override
	public String getContactPersonSI() {
		// resourceType = pointP.getContextCoordinates().getTopic().getSI()[0];
		// resourceName = pointP.getContextCoordinates().getTopic().getName();
		// ownerSI = pointP.getContextCoordinates().getOriginator().getSI()[0];
		// contactPersonSI = pointP
		// amount = pointP.getInformation().next().getContentAsString();
		// picture = null;

		return this.cp.getContextCoordinates().getPeer().getSI()[0];
	}

	@Override
	public String getAmount() {
		return amount;
	}

	@Override
	public PHNXPicture getPicture() {
		return picture;
	}

	@Override
	public void setOwner(PHNXSharkEngine engine, String ownerIdentifierP) {
		if (ownerIdentifierP != null) {
			ownerSI = ownerIdentifierP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setContactPerson(PHNXSharkEngine engine, String emailAddressP) {
		if (emailAddressP != null) {
			contactPersonSI = emailAddressP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setAmount(PHNXSharkEngine engine, String value) {
		if (value != null) {
			amount = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPicture(PHNXSharkEngine engine, PHNXPicture value) {
		if (value != null) {
			picture = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

}