package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXName {

	String getPrintableFullName();

	String getFirstName();

	String getLastName();

	Iterator<String> getMiddleNames();

	String getNickname();
}
