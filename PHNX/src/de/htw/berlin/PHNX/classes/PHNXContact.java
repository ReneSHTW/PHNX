package de.htw.berlin.PHNX.classes;

import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXMapPOI;

public class PHNXContact {

	private String emailAddress;
	private String wwwAddress;
	private String mobileNumber;
	private String landLineNumber;
	private PHNXMapPOI currentLocation;

	public PHNXContact(String anEmailAddress, String aWwwAddress, String aMobileNumber, String aLandLineNumber, PHNXMapPOI aCurrentLocation) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
			wwwAddress = aWwwAddress;
			mobileNumber = aMobileNumber;
			landLineNumber = aLandLineNumber;
			currentLocation = aCurrentLocation;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getLandLineNumber() {
		return landLineNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getWwwAddress() {
		return wwwAddress;
	}

	public void setMobileNumber(String aMobileNumber) {
		if (aMobileNumber != null) {
			mobileNumber = aMobileNumber;
		}
	}

	public void setLandLineNumber(String aLandLineNumber) {
		if (aLandLineNumber != null) {
			landLineNumber = aLandLineNumber;
		}
	}

	public void setEmailAddress(String anEmailAddress) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
		}
	}

	public void setWwwAddress(String aWwwAddress) {
		if (aWwwAddress != null) {
			wwwAddress = aWwwAddress;
		}
	}

	public PHNXMapPOI getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(PHNXMapPOI aCurrentLocation) {
		if (aCurrentLocation != null) {
			currentLocation = aCurrentLocation;
		}
	}
}
