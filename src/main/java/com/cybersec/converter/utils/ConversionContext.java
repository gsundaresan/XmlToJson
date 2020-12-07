/**
 * 
 */
package com.cybersec.converter.utils;

import java.io.File;

import com.cybersec.converter.XMLJSONConverterI;

/**
 * @author guhans
 *Strategy pattern to create dependencies only during runtime.
 */
public class ConversionContext {
	
	private XMLJSONConverterI processor;

	/**
	 * 
	 */
	public ConversionContext(XMLJSONConverterI processor) {
		this.processor = processor;
	}
	
	public void convertData(File jsonData, File xmlData){
		processor.transformToXml(jsonData, xmlData);
	}

}
