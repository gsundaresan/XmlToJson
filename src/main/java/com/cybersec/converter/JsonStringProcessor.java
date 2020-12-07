/**
 * 
 */
package com.cybersec.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
public class JsonStringProcessor implements XMLJSONConverterI {

	public JsonStringProcessor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * XMLJSONConvertorI implementation to handle a valid json file with proper
	 * formating (i.e) a standard json file enclosed within braces {}
	 */
	@Override
	public void transformToXml(File jsonFile, File xmlFile) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(xmlFile);
			JSONObject jsonObject = new JSONObject(FileReaderUtils.fileToStringConverter(jsonFile));
			// Root Element
			Element root = new Element(DataType.OBJECT.getDataType());

			recursiveRead(jsonObject, root, false);

			Document doc = new Document();
			doc.setRootElement(root);
			// Create the XML
			XMLOutputter outter = new XMLOutputter();
			outter.setFormat(Format.getPrettyFormat());
			outter.output(doc, fileWriter);
			fileWriter.close();
		} catch (JSONException exception) {
			// TODO: handle exception
			System.out.println(exception.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Failed to close writer");
			}
		}

	}

	/**
	 * This method recursively iterates and reads through the
	 * org.codehaus.jettison.json.JSONObject I have specifically used the
	 * jettison JSONObject since it preserves the order of the JSON when parsing
	 * from a String or file due its use of LinkedHashmap for storing objects
	 * inside a key as opposed to a simple map used by org.json that doesn't
	 * preserve order
	 *
	 */
	private static Element recursiveRead(JSONObject json, Element element, boolean isNested) throws JSONException {
		Iterator<String> keys = json.keys();
		Element childElement = null;
		while (keys.hasNext()) {
			String key = keys.next();
			if (json.get(key) instanceof JSONObject) {
				childElement = new Element(DataType.OBJECT.getDataType());
				if (!isNested)
					childElement.setAttribute(Constants.DATA_ATTRIBUTE_NAME, key);
				recursiveRead(json.getJSONObject(key), childElement, isNested);
				element.addContent(childElement);
			} else if (json.get(key) instanceof JSONArray) {
				JSONArray jsArray = json.getJSONArray(key);
				recursiveJsonArrayRead(element, childElement, jsArray, key, isNested);
			} else {
				if (json.get(key) instanceof Number) {
					childElement = new Element(DataType.NUMBER.getDataType());
					childElement.setAttribute(Constants.DATA_ATTRIBUTE_NAME, key);
					childElement.setText(json.getString(key));
					element.addContent(childElement);
				} else {
					childElement = new Element(json.get(key).getClass().getSimpleName().toLowerCase());
					childElement.setAttribute(Constants.DATA_ATTRIBUTE_NAME, key);
					childElement.setText(json.getString(key));
					element.addContent(childElement);
				}
			}
		}

		return element;
	}

	/**
	 * This nested call iterates repeatedly and in a nested fashion over the
	 * various scenarios of JSON arrays to be handled as part of the problem
	 * statement The JSON arrays are then converted into the required xml
	 * formatting
	 *
	 */
	private static void recursiveJsonArrayRead(Element element, Element childElement, JSONArray jsArray, String key,
			boolean isNested) throws JSONException {
		Element arrayElement = new Element(DataType.ARRAY.getDataType());
		if (!isNested)
			arrayElement.setAttribute(Constants.DATA_ATTRIBUTE_NAME, key);

		for (int arrCount = 0; arrCount < jsArray.length(); arrCount++) {

			if (jsArray.get(arrCount) instanceof JSONObject) {
				childElement = new Element(DataType.OBJECT.getDataType());
				recursiveRead(jsArray.getJSONObject(arrCount), childElement, isNested);
				arrayElement.addContent(childElement);
			} else if (jsArray.get(arrCount) instanceof JSONArray) {
				recursiveJsonArrayRead(arrayElement, childElement, jsArray.getJSONArray(arrCount), key, true);
			} else if (jsArray.get(arrCount) instanceof Number) {
				childElement = new Element(DataType.NUMBER.getDataType());
				childElement.setText(jsArray.getString(arrCount));
				arrayElement.addContent(childElement);
			} else {
				childElement = new Element(jsArray.get(arrCount).getClass().getSimpleName().toLowerCase());
				childElement.setText(jsArray.getString(arrCount));
				arrayElement.addContent(childElement);
			}
		}
		element.addContent(arrayElement);
	}

}
