package de.htw.berlin.PHNX.impl;

import java.util.ArrayList;
import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXContact;

public class PHNXContactImpl implements PHNXContact {

	private String emailAddress;
	private String homeAddress;
	private Iterator<String> wwwAddress;
	private String mobileNumber;
	private String landLineNumber;

	public PHNXContactImpl(String anEmailAddress, String aHomeAddress, Iterator<String> aWwwAddress, String aMobileNumber, String aLandLineNumber) {
		if (anEmailAddress != null) {
			emailAddress = anEmailAddress;
			homeAddress = aHomeAddress;
			wwwAddress = aWwwAddress;
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
	public void setMobileNumber(String aMobileNumber) {
		this.mobileNumber = aMobileNumber;

	}

	@Override
	public void setLandLineNumber(String aLandLineNumber) {
		this.landLineNumber = aLandLineNumber;

	}

	@Override
	public void setEmailAddress(String anEmailAddress) {
		this.emailAddress = anEmailAddress;

	}

	@Override
	public void setHomeAddress(String aHomeAddress) {
		this.homeAddress = aHomeAddress;
	}

	@Override
	public void addWwwAddress(String aWwwAddress) {
		if (aWwwAddress != null) {
			ArrayList<String> tempList = new ArrayList<String>();
			if (wwwAddress != null) {
				while (wwwAddress.hasNext()) {
					tempList.add(wwwAddress.next());
				}
			} else {
				tempList.add(aWwwAddress);
				wwwAddress = tempList.iterator();
			}
		}

	}

}
