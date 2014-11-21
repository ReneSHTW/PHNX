package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXContact;

public class PHNXContactImpl implements PHNXContact {

	private String emailAddress;
	private String homeAddress;
	private String wwwAddress;
	private String MobileNumber;
	private String LandLineNumber;

	public PHNXContactImpl(String anEmailAddress, String aHomeAddress, String aWwwAddress, String aMobileNumber, String aLandLineNumber) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
			homeAddress = aHomeAddress;
			wwwAddress = aWwwAddress;
			MobileNumber = aMobileNumber;
			LandLineNumber = aLandLineNumber;

		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLandLineNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHomeAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<String> getWwwAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
