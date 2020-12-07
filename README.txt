Introduction

The given use case can be briefly described as a customized XML to JSON converter which converts the provided JSON to an arbitrary XML format by accomodating all of the following cases.

1) The JSON data has to be converted to its XML counterpart with the dataType as the tagName,the name as XML name attribute of the tag and value as the value of the tag
2) The JSON arrays have to be enclosed within an array tag (including nested arrays) and then the elements as mentioned in point 1.
3) Special case files with just null and datatype,value entries also have to be handled with their appropriate xml tagNames and values

#########################################################################################################################################################################################
Building and Running the provided code:

The case has been developed as a maven project so the same can be packaged as a runnable jar using the package maven command: mvn package
The above command would generate a runnable jar with all downloaded dependencies from mvn central repository into the path: \target from root folder.
The name of the created artifact will be XmlToJson-1.0.jar

Post packaging, the executable jar can be run as instructed in the documentation: java -jar XmlToJson-1.0.jar example.json example1.json
where example.json is the input file availabe in the same folder.

The code without compilation data(as instructed) has been packaged as a ZIP file of name XmlToJson

Note: If at all there seems to be a dependency related issue, a simple dependency tree update command should handle it: mvn dependency:tree (Usual maven multi-dependency issue)

#########################################################################################################################################################################################
Approach and Design:

Design Patterns:
As instructed, I have used Factory pattern to select the type of basic XMLTOJSONConverter Implementation. Additionally I have added an
enclosure of strategy pattern to establish dependencies during run time.
This composite pattern uses polymorphism ensures that one one class(ConverterContext) is initated and and returned based on requirement during invocation.

Recursive Model converter:
I have implemented a recursive methods to handle the JSON and JSON Array read for nested cases since the level of nesting here doesn't look complex, that being said the case has been
tested rigorously to check for breakage.
I have made use of org.codehaus.jettison library to parse the JSON input. I have used this specific library since it preserves the order of data from the file being read.
I have used jdom to construct the XML as per requirement.
########################################################################################################################################################################################
Libraries/Frameworks Used:

Java 7 (as provided in limitations)
Apache Maven 3.3.9
org.codehaus.jettison,jdom - for parsing
org.apache.commons:commons-lang3, commons-io - for various in-built utilities.
junit:3.8.1, xmlunit:1.4 for testing

#########################################################################################################################################################################################

Unit Tests & Integration Tests:

A bsic and thorough testing template is provided by the singular test class implementation.
The test picks files from the directory:/src/test/resources/input/ , converts them to XML, stores in the directory:/src/test/resources/output/ , and compares the final output against 
standard expected output present in the directory: /src/test/resources/expectedOutput/

Note: While testing I was able to identify that the XML and JSON examples provided with the names example.json and example.xml were differing in value at the boolean field:security_related which has been altered.
