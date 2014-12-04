package de.htw.berlin.PHNX.impl;

import java.util.ArrayList;
import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;

public class PHNXOrganizationImpl implements PHNXOrganization {

	private String name;
	private String wwwAddress;
	private PHNXPicture logo;
	private Iterator<PHNXResource> resources;

	public PHNXOrganizationImpl(String nameP, String wwwAddressP, PHNXPicture logoP, Iterator<PHNXResource> resourcesP) {
		if (nameP != null && wwwAddressP != null) {
			name = nameP;
			wwwAddress = wwwAddressP;
			logo = logoP;
			resources = resourcesP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getWwwAddress() {
		return wwwAddress;
	}

	@Override
	public PHNXPicture getLogo() {
		return logo;
	}

	@Override
	public Iterator<PHNXResource> getResources() {
		return resources;
	}

	@Override
	public void setName(String value) {
		if (value != null) {
			name = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setWwwAddress(String value) {
		if (value != null) {
			wwwAddress = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setLogo(PHNXPicture value) {
		if (value != null) {
			logo = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addResource(PHNXResource value) {
		if (value != null) {
			ArrayList<PHNXResource> tempList = new ArrayList<PHNXResource>();
			while (resources.hasNext()) {
				tempList.add(resources.next());
			}
			tempList.add(value);
			resources = tempList.iterator();
		} else {
			throw new IllegalArgumentException();
		}
	}
}
