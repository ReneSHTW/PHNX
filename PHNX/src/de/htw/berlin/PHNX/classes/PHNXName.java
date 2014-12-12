package de.htw.berlin.PHNX.classes;

import java.util.ArrayList;
import java.util.Iterator;

public class PHNXName {

	private String firstName;
	private String lastName;
	private Iterator<String> middleNames;

	public PHNXName(String firstNameP, String lastNameP, Iterator<String> middleNamesP) {
		if (firstNameP != null && lastNameP != null) {
			firstName = firstNameP;
			lastName = lastNameP;
			middleNames = middleNamesP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private String buildFullName() {
		String printableFullName = firstName;
		while (middleNames.hasNext()) {
			printableFullName += " " + middleNames.next();
		}
		printableFullName += " " + lastName;
		return printableFullName;
	}

	public String getPrintableFullName() {
		return buildFullName();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Iterator<String> getMiddleNames() {
		return middleNames;
	}

	public void setFirstName(String value) {
		if (value != null) {
			firstName = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void setLastName(String value) {
		if (value != null) {
			lastName = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void addMiddleName(String value) {
		if (value != null) {
			ArrayList<String> tempList = new ArrayList<String>();
			while (middleNames.hasNext()) {
				tempList.add(middleNames.next());
			}
			tempList.add(value);
			middleNames = tempList.iterator();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void removeMiddleName(String value) {
		boolean noMatch = true;
		while (middleNames.hasNext()) {
			if (middleNames.next().equals(value)) {
				middleNames.remove();
				noMatch = false;
			}
		}
		if (noMatch) {
			throw new IllegalArgumentException();
		}
	}
}
