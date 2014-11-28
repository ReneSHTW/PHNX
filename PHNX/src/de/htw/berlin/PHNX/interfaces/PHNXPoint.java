package de.htw.berlin.PHNX.interfaces;

public interface PHNXPoint {
	
	public Double getLatitude(String value);
	
	public Double getLongitude(String value);
	
	public String getColorOfPoint(String value);
	
	public void setColorOfPoint(String value);
	
	public String getNameOfPoint(String value);
	
	public void setNameOfPoint(String value);
	
	public String getDescriptionOfPoint(String value);
	
	public void setDescriptionOfPoint(String value);
	
	public PHNXPicture getViewOfLocation(String value);


}
