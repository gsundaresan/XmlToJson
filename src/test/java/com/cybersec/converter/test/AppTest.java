package com.cybersec.converter.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

import com.cybersec.converter.factory.ConverterFactory;
import com.cybersec.converter.utils.ConversionContext;
import com.cybersec.converter.utils.FileReaderUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigorous Test :-)
     * @throws IOException 
     * @throws SAXException 
     */
    public void testApp() throws SAXException, IOException
    {
    	ConversionContext conversionContext;
    	ConverterFactory converterFactory = new ConverterFactory();
    	
    	String absPath = new File("").getAbsolutePath(); // Bad work around in the interest of time ideal way should be to use classloader to get the actual location of the file
    	File input = new File(absPath+"/src/test/resources/input/");
    	File output = new File(absPath+"/src/test/resources/output/");
    	
    	for(File inpfile : input.listFiles()){
    		File dataXml = new File(absPath+"/src/test/resources/output/"+inpfile.getName());
        	conversionContext = converterFactory.conversionContextPicker(inpfile);
        	conversionContext.convertData(inpfile, dataXml);
        	
//        	XMLUnit.setIgnoreWhitespace(true);
//        	XMLUnit.setIgnoreAttributeOrder(true);
//        	Diff differences = XMLUnit.compareXML(FileReaderUtils.fileToStringConverter(dataXml), FileReaderUtils.fileToStringConverter(new File(absPath+"/src/test/resources/expectedOutput/"+dataXml.getName())));
//        	assertTrue( differences.identical());
    	}
    }
}
