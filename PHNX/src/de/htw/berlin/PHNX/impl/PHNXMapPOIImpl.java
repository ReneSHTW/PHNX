package de.htw.berlin.PHNX.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.Map.PHNXPoint;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXMapPOIImpl implements PHNXMapPOI {

	private Iterator<PHNXPoint> PHNXPoints;
	private String pointName;
	private String pointDescription;
	private String pointCategorie;
	private String pOIIdentifier;
	private Date timestamp;

	public PHNXMapPOIImpl(Iterator<PHNXPoint> PHNXPointsP, String pointNameP, String pointDescriptionP, String pointCategorieP) {
		if (PHNXPointsP != null && pointNameP != null && pointCategorieP != null) {
			PHNXPoints = PHNXPointsP;
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
	public Iterator<PHNXPoint> getPHNXPoints() {
		return PHNXPoints;
	}

	@Override
	public void addPHNXPoint(PHNXPoint value) {
		if (value != null) {
			ArrayList<PHNXPoint> tempList = new ArrayList<PHNXPoint>();
			while (PHNXPoints.hasNext()) {
				tempList.add(PHNXPoints.next());
			}
			tempList.add(value);
			PHNXPoints = tempList.iterator();
		} else {
			throw new IllegalArgumentException();
		}
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
	public void removePHNXPoint(PHNXPoint value) {
		if (value != null) {
			while (PHNXPoints.hasNext()) {
				if ((PHNXPoints.next().getLattitude() == value.getLattitude()) && (PHNXPoints.next().getLongitude() == value.getLongitude())) {
					PHNXPoints.remove();
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getPOIIdentifier() {
		return pOIIdentifier;
	}

}
