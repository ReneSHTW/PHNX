package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.Map.PHNXPoint;

public interface PHNXMapPOI {
	
	public String getPOIIdentifier();

	public Iterator<PHNXPoint> getPHNXPoints();

	public void addPHNXPoint(PHNXPoint value);

	public String getPointName();

	public void setPointName(String value);

	public String getPointDescription();

	public void setPointDescription(String value);

	public String getPointCategorie();

	public void setPointCategorie(String value);

	public Date getTimestamp();

	public void removePHNXPoint(PHNXPoint value);
}
