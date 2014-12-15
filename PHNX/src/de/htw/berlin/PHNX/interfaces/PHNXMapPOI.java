package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.Map.PHNXPoint;

public interface PHNXMapPOI {

	public String getPOIIdentifier();

	public String getwktString();

	public String getPointName();

	public void setPointName(String value);

	public String getPointDescription();

	public void setPointDescription(String value);

	public String getPointCategorie();

	public void setPointCategorie(String value);

	public Date getTimestamp();

}
