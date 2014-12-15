package de.htw.berlin.PHNX.impl;

import java.util.Date;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXMapPOIImpl implements PHNXMapPOI {

	private String pointName;
	private String pointDescription;
	private String pointCategorie;
	private String pOIIdentifier;
	private String wktString;
	private Date timestamp;

	public PHNXMapPOIImpl(String wktStringP, String pointNameP, String pointDescriptionP, String pointCategorieP) {
		if (wktStringP != null && pointNameP != null && pointCategorieP != null) {
			wktString = wktStringP;
			pointName = pointNameP;
			pointDescription = pointDescriptionP;
			pointCategorie = pointCategorieP;
			timestamp = new Date(System.currentTimeMillis());
			pOIIdentifier = pointName + "_" + timestamp.getTime();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getwktString() {
		return wktString;
	}

	@Override
	public String getPointName() {
		return pointName;
	}

	@Override
	public void setPointName(String value) {
		if (value != null) {
			pointName = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getPointDescription() {
		return pointDescription;
	}

	@Override
	public void setPointDescription(String value) {
		if (value != null) {
			pointDescription = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getPointCategorie() {
		return pointCategorie;
	}

	@Override
	public void setPointCategorie(String value) {
		if (value != null) {
			pointCategorie = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String getPOIIdentifier() {
		return pOIIdentifier;
	}

}
