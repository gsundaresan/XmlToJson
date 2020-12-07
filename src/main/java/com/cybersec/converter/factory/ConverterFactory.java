/**
 * 
 */
package com.cybersec.converter.factory;

import java.io.File;

import com.cybersec.converter.JsonStringProcessor;
import com.cybersec.converter.StringProcessor;
import com.cybersec.converter.utils.ConversionContext;
import com.cybersec.converter.utils.FileReaderUtils;

/**
 * @author guhans
 *Factory implementation which invokes the processor/converter context based on the contents of the JSON File
 */
public class ConverterFactory {

	
	public ConverterFactory() {
		// TODO Auto-generated constructor stub
	}
	ConversionContext context;
	
	/**
	 * 
	 *we can limit the number of times the File is being read,
	 *but since the pre-requisite was mentioned to not change interface method signature, we have use Files as arguments
	 *a better way would be to handle the string conversion at the Factory or context picker level and pick the child implementation accordingly 
	 */
	public ConversionContext conversionContextPicker(File jsonFile){
		
		if(FileReaderUtils.isValidJson(jsonFile)){
			context = new ConversionContext(new JsonStringProcessor());
		}else{
			context = new ConversionContext(new StringProcessor());
		}
		
		return context;
	}
}
