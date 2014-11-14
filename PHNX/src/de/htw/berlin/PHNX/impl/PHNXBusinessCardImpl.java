package de.htw.berlin.PHNX.impl;

import java.util.Date;
import java.util.Iterator;

import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXContact;
import de.htw.berlin.PHNX.interfaces.PHNXName;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXResource;

public class PHNXBusinessCardImpl implements PHNXBusinessCard{
	
	PHNXName name;
	PHNXContact contact;
	PHNXOrganization organization;
	PHNXResource profession;
	String printableProffesionalDegree;
	Iterator<PHNXResource> skills;
	Date Arrival;
	Date Departure;
	
	public PHNXBusinessCardImpl(){
		
	}

	@Override
	public PHNXName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PHNXContact getContact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PHNXOrganization getOrganization() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PHNXResource getProfession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrintableProfessionalDegree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<PHNXResource> getSkills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getArrival() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDeparture() {
		// TODO Auto-generated method stub
		return null;
	}

}
