package de.htw.berlin.PHNX.impl;

import net.sharkfw.knowledgeBase.SemanticTag;
import net.sharkfw.knowledgeBase.SharkKB;
import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXMapPOIImpl implements PHNXMapPOI {

	private SharkKB kb;
	private SemanticTag st;

	public PHNXMapPOIImpl(SharkKB kBP, String wktStringP, String pointNameP, String pointDescriptionP, String pointCategorieP, String pointIdentifierP)
			throws SharkKBException {
		if (wktStringP != null && pointNameP != null && pointCategorieP != null && pointIdentifierP != null) {
			kb = kBP;
			st = kb.createSemanticTag("PHNX_MapPOI_SI", pointIdentifierP);
			st.setProperty("PHNX_MapPOI_wktString", wktStringP);
			st.setProperty("PHNX_MapPOI_name", pointNameP);
			st.setProperty("PHNX_MapPOI_categorie", pointCategorieP);
			if (pointDescriptionP != null) {
				st.setProperty("PHNX_MapPOI_description", pointDescriptionP);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public PHNXMapPOIImpl(SharkKB kBP, SemanticTag MapPOIP) {
		kb = kBP;
		st = MapPOIP;
	}

	@Override
	public String getwktString() {
		return st.getProperty("PHNX_MapPOI_wktString");
	}

	@Override
	public String getPointName() {
		return st.getProperty("PHNX_MapPOI_name");
	}

	@Override
	public String getPointDescription() {
		return st.getProperty("PHNX_MapPOI_description");
	}

	@Override
	public String getPointCategorie() {
		return st.getProperty("PHNX_MapPOI_categorie");
	}

	@Override
	public String getPOIIdentifier() {
		return st.getSI()[0];
	}

}
