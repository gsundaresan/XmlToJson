/**
 * 
 */
package com.cybersec.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.cybersec.converter.utils.FileReaderUtils;
import com.cybersec.data.constants.Constants;
import com.cybersec.data.constants.DataType;

/**
 * @author guhans
 *
 */
public class StringProcessor implements XMLJSONConverterI {

	/**
	 * 
	 */
	public StringProcessor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *XMLJSONConvertorI implementation to handle a simple String case json file (i.e data without braces enclosure) 
	 */
	@Override
	public void transformToXml(File stringFile, File xmlFile) {
		try{
			String trimmedString = FileReaderUtils.fileToStringConverter(stringFile);
			Element element = null;
	    	if(!trimmedString.startsWith(Constants.ARRAY_BRACE_OPEN) && !trimmedString.endsWith(Constants.ARRAY_BRACE_CLOSE)){
	    		if(FileReaderUtils.isNumeric(trimmedString)){
	    			element = new Element(DataType.NUMBER.getDataType());
	    			element.setText(trimmedString);
	    		}else if(Constants.DATA_TYPE_STRING_TRUE.equalsIgnoreCase(trimmedString) || Constants.DATA_TYPE_STRING_FALSE.equalsIgnoreCase(trimmedString)){
	    			element = new Element(DataType.BOOLEAN.getDataType());
	    			element.setText(trimmedString);
	    		}else{
	    			element = new Element(DataType.STRING.getDataType());
	    			element.setText(trimmedString);
	    		}
	    	}else{
	    		trimmedString = trimmedString.substring(1, trimmedString.length()-1);
	    		element = new Element(DataType.ARRAY.getDataType());
	    		Element arrElement = null;
	    		for(String val : trimmedString.split(Constants.DELEMITER)){
	    			if(FileReaderUtils.isNumeric(val)){
	    				arrElement = new Element(DataType.NUMBER.getDataType());
	    				arrElement.setText(val);
	    				element.addContent(arrElement);
	        		}else if(Constants.DATA_TYPE_STRING_TRUE.equalsIgnoreCase(val) || Constants.DATA_TYPE_STRING_FALSE.equalsIgnoreCase(val)){
	        			arrElement = new Element(DataType.BOOLEAN.getDataType());
	        			arrElement.setText(val);
	        			element.addContent(arrElement);
	        		}else{
	        			arrElement = new Element(DataType.STRING.getDataType());
	        			arrElement.setText(val);
	        			element.addContent(arrElement);
	        		}
	    		}
	    		
	    	}
	    	Document doc=new Document();
			doc.setRootElement(element);
			//Create the XML
			XMLOutputter outter=new XMLOutputter();
			outter.setFormat(Format.getPrettyFormat());
			outter.output(doc, new FileWriter(xmlFile));
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
