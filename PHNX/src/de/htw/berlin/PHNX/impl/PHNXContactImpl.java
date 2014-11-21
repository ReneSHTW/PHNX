package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXContact;

public class PHNXContactImpl implements PHNXContact{

	private String emailAddress;
	private String homeAddress;
	private String wwwAddress;
	private String privateMobileNumber;
	private String privateLandLineNumber;
	private String publicMobileNumber;
	private String publicLandLineNumber;
	
	public PHNXContactImpl(String anEmailAddress, String aHomeAddress, String aWwwAddress, String aPrivateMobileNumber, String aPrivateLandLineNumber, String aPublicMobileNumber, String aPublicLandLineNumber) {
		if (anEmailAddress != null){
			emailAddress = anEmailAddress;
			homeAddress = aHomeAddress;
			wwwAddress = aWwwAddress;
			privateMobileNumber = aPrivateMobileNumber;
			privateLandLineNumber = aPrivateLandLineNumber;
			publicMobileNumber = aPublicMobileNumber;
			publicLandLineNumber = aPublicLandLineNumber;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/*address*/
	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	/*Property*/
	@Override
	public String getHomeAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	/*Subject Identifier*/
	@Override
	public Iterator<String> getWwwAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPrivateMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPrivateLandLineNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPublicMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPublicLandLineNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	//DO ALL THE NECESSARY SETTERS!!!
	/*address*/
	public String setEmailAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	/*Property*/
	public String setHomeAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	/*Subject Identifier*/
	public Iterator<String> setWwwAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public String setPrivateMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String setPrivateLandLineNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String setPublicMobileNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String setPublicLandLineNumber() {
		// TODO Auto-generated method stub
		return null;
	}


}
