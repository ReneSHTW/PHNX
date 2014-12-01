package de.htw.berlin.PHNX.impl;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.Map.PHNXPoint;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXMapPOIImpl implements PHNXMapPOI {

	private Iterator<PHNXPoint> PHNXPoints;
	private String pointName;
	private String pointDescription;
	private String pointCategorie;
	private Date timestamp;

	public PHNXMapPOIImpl(String subjctIdentifierP, Iterator<PHNXPoint> PHNXPointsP, String pointNameP, String pointDescriptionP, String pointCategorieP,
			Date timestampP) {

	}

	@Override
	public Iterator<PHNXPoint> getPHNXPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPHNXPoint(PHNXPoint value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointName(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPointDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointDescription() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPointCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointCategorie() {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePHNXMapPOI(PHNXMapPOI value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSubjectIdentifier() {
		return null;
	}

}
