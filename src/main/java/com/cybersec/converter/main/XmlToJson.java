package com.cybersec.converter.main;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.cybersec.converter.factory.ConverterFactory;
import com.cybersec.converter.utils.ConversionContext;

/**
 * Hello world!
 *
 */
public class XmlToJson 
{
    public static void main( String[] args )
    {
    	ConversionContext conversionContext;
    	ConverterFactory converterFactory = new ConverterFactory();
    	File inputFile = FileUtils.getFile(args[0]);
    	File outputFile = FileUtils.getFile(args[1]);
    	if(inputFile.exists()){
    		conversionContext = converterFactory.conversionContextPicker(inputFile);
    		conversionContext.convertData(inputFile, outputFile);
    	}
    }
}
