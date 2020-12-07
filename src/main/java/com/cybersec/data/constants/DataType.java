/**
 * 
 */
package com.cybersec.data.constants;

/**
 * @author guhans
 *
 */
public enum DataType {

	OBJECT("object"),
	NUMBER("number"),
	STRING("string"),
	BOOLEAN("boolean"),
	ARRAY("array");
	
	private String dataType;
	
	public String getDataType(){
		return this.dataType;
	}
	
	private DataType(String dataType){
		this.dataType = dataType;
	}
}
