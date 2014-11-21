package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

/* Sollte als Peer Semantik Tag gespeichert werden.
 * PHNX_Contact_Firmenaddresse*/

public interface PHNXContact {

	String getPrivateMobileNumber();
	
	String getPrivateLandLineNumber();
	
	String getPublicMobileNumber();
	
	String getPublicLandLineNumber();

	String getEmailAddress();
	
	String getHomeAddress();

	Iterator<String> getWwwAddress();
	
//	PHNXLocation getCurrentLocation();
}
