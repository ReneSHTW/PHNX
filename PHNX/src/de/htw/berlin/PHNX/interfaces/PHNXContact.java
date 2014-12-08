package de.htw.berlin.PHNX.interfaces;


public interface PHNXContact {

	public String getMobileNumber();

	public String getLandLineNumber();

	public String getEmailAddress();

	public String getWwwAddress();
	
	public PHNXMapPOI getCurrentLocation();

	public void setMobileNumber(String aMobileNumber);

	public void setLandLineNumber(String aLandLineNumber);

	public void setEmailAddress(String anEmailAddress);

	public void setWwwAddress(String aWwwAddress);

	public void setCurrentLocation(PHNXMapPOI aCurrentLocation);
}
