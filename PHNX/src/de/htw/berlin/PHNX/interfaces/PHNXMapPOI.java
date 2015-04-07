/**PHNXMapPOI interface represents methods to access map-related information for the PHNX app.
 * A map is an optional but very useful part of PHNX application. Using the map, the user/owner can mark the following:
 * - points representing resources (e.g. machines, trucks, hospitals, dead bodies);
 * - lines representing blocked streets, new streets etc.;
 * - polygons representing areas (e.g. with contaminated water/ground).
 * The map will have topical layers (e.g. a layer with medical resources marked; a layer with dead bodies marked) so that 
 * the user can choose only 1 layer to show or only the layers relevant in a certain situation (e.g. only streets).
 */
package de.htw.berlin.PHNX.interfaces;

public interface PHNXMapPOI {

	public String getPOIIdentifier();

	public String getwktString();

	public String getPointName();

	public String getPointDescription();

	public String getPointCategorie();
}
