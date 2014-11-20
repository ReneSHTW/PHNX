package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXContact {

	String privateMobileNumber();
	
	String privateLandLineNumber();
	
	String publicMobileNumber();
	
	String publicLandLineNumber();

	String getEmailAddress();
	
	String getHomeAddress();

	Iterator<String> getWwwAddress();
	
//	PHNXLocation getCurrentLocation();
}