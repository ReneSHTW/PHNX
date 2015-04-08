/**The interface PHNXPicture contains methods allowing access to pictures in PHNX app.
 * Since a picture is not uniquely defined for all possible different implementations,
 * this interface is used as a wrapper for possible picture formats and implementations.
 */
package de.htw.berlin.PHNX.interfaces;

/*es gibt ein Topic welches Picture heiﬂt*/
public interface PHNXPicture {
	/**Allows access to the picture/photo
	 * 
	 * @return path to the picture/photo
	 */
	String getPicture();
	
	/**Sets the path to where the picture/phone is found for this implementation
	 * 
	 * @param picturePath the path to the picture
	 */
	 void setPicture(String picturePath);
}
