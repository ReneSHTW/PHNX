package de.htw.berlin.PHNX.Map;

public class PHNXPoint {

	private double longitude;

	private double lattitude;

	public PHNXPoint(double longitudeP, double lattitudeP) {
		longitude = longitudeP;
		lattitude = lattitudeP;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitudeP) {
		longitude = longitudeP;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitudeP) {
		lattitude = lattitudeP;
	}

}
