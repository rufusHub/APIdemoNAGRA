package org.nagra.utilities;

import java.util.regex.Pattern;

// input parameter----complete requestBody, variableName, variableValue
// purpose? to assign the value to the variable of the requestbody.
// output parameter ----after replacement requestbody it will return.
public class JsonReplacement {
	
	public static String assignJsonValue(String requestBody, String variableName, String variableValue) {
		requestBody = requestBody.replaceAll(Pattern.quote("{{"+variableName+"}}"),variableValue);
		return requestBody;
	}
}
