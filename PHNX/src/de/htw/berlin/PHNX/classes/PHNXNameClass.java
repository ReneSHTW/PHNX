package de.htw.berlin.PHNX.classes;

import java.util.ArrayList;
import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXName;

public class PHNXNameClass implements PHNXName {

	private String firstName;
	private String lastName;
	private Iterator<String> middleNames;

	public PHNXNameClass(String firstNameP, String lastNameP, Iterator<String> middleNamesP) {
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

	@Override
	public String getPrintableFullName() {
		return buildFullName();
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
