package de.htw.berlin.PHNX.interfaces;

import java.util.Iterator;

public interface PHNXName {

	public String getPrintableFullName();

	public String getFirstName();

	public void setFirstName(String value);

	public String getLastName();

	public void setLastName(String value);

	public Iterator<String> getMiddleNames();

	public void addMiddleName(String value);

	public void removeMiddleName(String value);
}
