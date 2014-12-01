package de.htw.berlin.PHNX.impl;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXPicture;
import de.htw.berlin.PHNX.interfaces.PHNXResource;

public class PHNXBusinessCardImpl implements PHNXBusinessCard {

	private PHNXName name;
	private PHNXContact contact;
	private PHNXOrganization organization; // Es wird in der KB unter der
											// BusinessCard nur der SI zur
											// Organisation gespeichert
	private PHNXResource profession; // der SI zur profession-Resource ist die
										// email-Addresse der BusinessCard
										// Die Resource wird zeitgleich also
										// angelegt passiv.
	private String printableProfessionalDegree;
	private Iterator<PHNXResource> skills; // genau wie beim Profession-Resource
	private Date arrival;
	private Date departure;
	private PHNXPicture picture;
	

	public PHNXBusinessCardImpl(PHNXName nameP, PHNXContact contactP, PHNXOrganization organizationP, PHNXResource professionP, String degreeP,
			Iterator<PHNXResource> skillsP, Date departureP, Date arrivalP, PHNXPicture pictureP) {
		if (nameP != null && arrivalP != null && departureP != null) {
			name = nameP;
			arrival = arrivalP;
			departure = departureP;
			contact = contactP;
			organization = organizationP;
			profession = professionP;
			printableProfessionalDegree = degreeP;
			skills = skillsP;
			picture = pictureP;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public PHNXName getName() {
		return name;
	}

	@Override
	public PHNXContact getContact() {
		return contact;
	}

	@Override
	public PHNXOrganization getOrganization() {
		return organization;
	}

	@Override
	public PHNXResource getProfession() {
		return profession;
	}

	@Override
	public String getPrintableProfessionalDegree() {
		return printableProfessionalDegree;
	}

	@Override
	public Iterator<PHNXResource> getSkills() {
		return skills;
	}

	@Override
	public Date getArrival() {
		return arrival;
	}

	@Override
	public Date getDeparture() {
		return departure;
	}

	@Override
	public PHNXPicture getPicture() {
		return picture;
	}

	@Override
	public void setPHNXName(PHNXName value) {
		if (value != null) {
			name = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPHNXContact(PHNXContact value) {
		if (value != null) {
			contact = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPHNXOrganization(PHNXOrganization value) {
		if (value != null) {
			organization = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPHNXResource(PHNXResource value) {
		if (value != null) {
			profession = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPrintableProfessionalDegree(String value) {
		if (value != null) {
			printableProfessionalDegree = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addSkill(PHNXResource value) {
		if (value != null) {
			// dem iterator hinzufügen
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeSkill(String value) {
		boolean noMatch = true;
		while (skills.hasNext()) {
			if (skills.next().getResourceName().equals(value)) {
				skills.remove();
				noMatch = false;
			}
		}
		if (noMatch) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setArrival(Date value) {
		if (value != null) {
			arrival = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setDeparture(Date value) {
		if (value != null) {
			departure = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setPicture(PHNXPicture value) {
		if (value != null) {
			picture = value;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
