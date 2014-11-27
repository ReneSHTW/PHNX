package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXContact;

public class PHNXContactImpl implements PHNXContact {

	private String emailAddress;
	private String homeAddress;
	private Iterator<String> wwwAddress;
	private String mobileNumber;
	private String landLineNumber;

	public PHNXContactImpl(String anEmailAddress, String aHomeAddress, String aWwwAddress, String aMobileNumber, String aLandLineNumber) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
			homeAddress = aHomeAddress;
			///wwwAddress = aWwwAddress;
			mobileNumber = aMobileNumber;
			landLineNumber = aLandLineNumber;

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
	public Iterator<String> getWwwAddress() {
		return wwwAddress;
	}

	@Override
	public void setMobileNumber() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLandLineNumber() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmailAddress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHomeAddress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWwwAddress() {
		// TODO Auto-generated method stub
		
	}
	


}
