package org.nagra.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

// input parameter ----- jsonfilePath
// purpose of this method ----- to read the data  of json file
// output parameter ----json data in String format
public class JsonHandle {
	public static String readJsonData(String path) throws FileNotFoundException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		JSONTokener js = new JSONTokener(fr);
		JSONObject j = new JSONObject(js);
		return j.toString();
	}
}
