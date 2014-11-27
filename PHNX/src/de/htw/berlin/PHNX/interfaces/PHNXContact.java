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
	
	public void setMobileNumber();
	
	public void setLandLineNumber();
	
	public void setEmailAddress();
	
	public void setHomeAddress();
	
	public void setWwwAddress();
	
	
	
//	PHNXLocation getCurrentLocation();
}
