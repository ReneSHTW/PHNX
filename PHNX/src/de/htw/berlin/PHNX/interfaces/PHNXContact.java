package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

/* Sollte als Peer Semantik Tag gespeichert werden.
 * PHNX_Contact_Firmenaddresse*/

public interface PHNXContact {
	
	public String getMobileNumber();
	
	public String getLandLineNumber();

	public String getEmailAddress();
	
	public String getHomeAddress();

	public Iterator<String> getWwwAddress();
	
	public void setMobileNumber(String aMobileNumber);
	
	public void setLandLineNumber(String aLandLineNumber);
	
	public void setEmailAddress(String anEmailAddress);
	
	public void setHomeAddress(String aHomeAddress);
	
	public void addWwwAddress(String aWwwAddress);
	
	
	
//	PHNXLocation getCurrentLocation();
}
