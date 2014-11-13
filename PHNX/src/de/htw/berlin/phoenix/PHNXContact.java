package de.htw.berlin.phoenix;

import java.util.Iterator;

public interface PHNXContact {

	Iterator<String> getPhoneNumbers();

	Iterator<String> getEmailAddresses();

	Iterator<String> getHomeAddress();

	Iterator<String> getWwwAddress();
	
	String getUsualMeetingPlace();
}
