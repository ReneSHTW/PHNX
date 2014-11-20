package de.htw.berlin.PHNX.impl;

import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXName;

public class PHNXNameImpl implements PHNXName {

	private String printableFullName;
	private String firstName;
	private String lastName;
	private Iterator<String> middleNames;

	public PHNXNameImpl(String firstNameP, String lastNameP, Iterator<String> middleNamesP) {
		if (firstNameP != null && lastNameP != null && middleNamesP != null) {
			firstName = firstNameP;
			lastName = lastNameP;
			middleNames = middleNamesP;
			printableFullName = "This should never been shown.";
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void buildFullName() {
		printableFullName = firstName;
		while (middleNames.hasNext()) {
			printableFullName += " " + middleNames.next();
		}
		printableFullName += " " + lastName;
	}

	@Override
	public String getPrintableFullName() {
		buildFullName();
		return printableFullName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public Iterator<String> getMiddleNames() {
		return middleNames;
	}

	@Override
	public void setFirstName(String value) {
		if (value != null) {
			firstName = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setLastName(String value) {
		if (value != null) {
			lastName = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addMiddleName(String value) {
		if (value != null) {
			// dem iterator hinzufügen
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
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
