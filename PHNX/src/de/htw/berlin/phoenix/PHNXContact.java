package de.htw.berlin.phoenix;

import java.util.Iterator;

/**email, phone number, home address, www address
 * 
 * @author olga, Rene
 *
 */
public interface PHNXContact {
	
	Iterator<String> getPhoneNumbers(); 
	Iterator<String> getEmailAddresses(); 
	Iterator<String> getHomeAddress(); 
	Iterator<String> getWwwAddress(); //!!! discuss what it's gonna be called (the ST)
}
