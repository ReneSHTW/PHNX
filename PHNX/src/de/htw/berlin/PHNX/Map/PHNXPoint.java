package de.htw.berlin.PHNX.Map;

public class PHNXPoint {

	private double longitude;

	private double latitude;

	public PHNXPoint(double longitudeP, double latitudeP) {
		longitude = longitudeP;
		latitude = latitudeP;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitudeP) {
		longitude = longitudeP;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitudeP) {
		latitude = latitudeP;
	}

}
