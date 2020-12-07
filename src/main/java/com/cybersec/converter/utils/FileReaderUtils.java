package com.cybersec.converter.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FileReaderUtils {

	public FileReaderUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static String fileToStringConverter(File file){
		String stringData = "";
		try {
			stringData = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringData.trim();
	}
	
	public static boolean isNumeric(String str) {
  	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number [0-9] with optional '-' and decimal.
  	}
	
	public static boolean isValidJson(File dataFile){
		try{
			JSONObject json = new JSONObject(fileToStringConverter(dataFile));
			return json.keys().hasNext();
		}catch(JSONException jsonException){
			System.out.println("Not a valid JSON:"+jsonException.getMessage());
			return false;
		}
	}

}
