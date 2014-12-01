package de.htw.berlin.PHNX.interfaces;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.Map.PHNXPoint;

public interface PHNXMapPOI {
	
	public String getSubjectIdentifier();

	public Iterator<PHNXPoint> getPHNXPoints();

	public void addPHNXPoint(PHNXPoint value);

	public String getPointName();

	public void setPointName(String value);

	public String getPointDescription();

	public void setPointDescription();

	public String getPointCategorie();

	public void setPointCategorie();

	public Date getTimestamp();

	public void removePHNXMapPOI(PHNXMapPOI value);
}
