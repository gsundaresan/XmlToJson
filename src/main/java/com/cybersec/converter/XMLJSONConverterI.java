/**
 * 
 */
package com.cybersec.converter;

import java.io.File;

/**
 * @author guhans
 * Base interface with preserved method signature as mentioned in problem statement limitations
 */
public interface XMLJSONConverterI {
	
	public void transformToXml(File jsonFile, File xmlFile);

}
