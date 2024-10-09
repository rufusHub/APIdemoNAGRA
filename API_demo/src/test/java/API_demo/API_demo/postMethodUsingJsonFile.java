package API_demo.API_demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class postMethodUsingJsonFile {
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("../API_demo/requestBody_array.json");
		FileInputStream fi = new FileInputStream(f);
		JSONTokener js = new JSONTokener(fi);
		JSONObject j = new JSONObject(js);
		
	
		Response res =
				given()
				.contentType(ContentType.JSON)
				.body(j.toString())
				.when()
				.post("http://localhost:3000/APIBatchStudents");
		
		System.out.println("Status code is "+res.statusCode());
		System.out.println("Response data is "+ res.asString());
	}
}
