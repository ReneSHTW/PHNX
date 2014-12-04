package de.htw.berlin.PHNX.impl;

import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXContactImpl implements PHNXContact {

	private String emailAddress;
	private String homeAddress;
	private String wwwAddress;
	private String mobileNumber;
	private String landLineNumber;
	private PHNXMapPOI currentLocation;

	public PHNXContactImpl(String anEmailAddress, String aHomeAddress, String aWwwAddress, String aMobileNumber, String aLandLineNumber,
			PHNXMapPOI aCurrentLocation) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
			homeAddress = aHomeAddress;
			wwwAddress = aWwwAddress;
			mobileNumber = aMobileNumber;
			landLineNumber = aLandLineNumber;
			currentLocation = aCurrentLocation;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getMobileNumber() {
		return mobileNumber;
	}

	@Override
	public String getLandLineNumber() {
		return landLineNumber;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String getHomeAddress() {
		return homeAddress;
	}

	@Override
	public String getWwwAddress() {
		return wwwAddress;
	}

	@Override
	public void setMobileNumber(String aMobileNumber) {
		if (aMobileNumber != null) {
			mobileNumber = aMobileNumber;
		}
	}

	@Override
	public void setLandLineNumber(String aLandLineNumber) {
		if (aLandLineNumber != null) {
			landLineNumber = aLandLineNumber;
		}
	}

	@Override
	public void setEmailAddress(String anEmailAddress) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
		}
	}

	@Override
	public void setHomeAddress(String aHomeAddress) {
		if (aHomeAddress != null) {
			homeAddress = aHomeAddress;
		}
	}

	@Override
	public void setWwwAddress(String aWwwAddress) {
		if (aWwwAddress != null) {
			wwwAddress = aWwwAddress;
		}
	}

	@Override
	public PHNXMapPOI getCurrentLocation() {
		return currentLocation;
	}

	@Override
	public void setCurrentLocation(PHNXMapPOI aCurrentLocation) {
		if (aCurrentLocation != null) {
			currentLocation = aCurrentLocation;
		}
	}
}
