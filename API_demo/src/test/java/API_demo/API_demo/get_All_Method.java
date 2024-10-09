package API_demo.API_demo;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class get_All_Method {
	public static void main(String[] args) {
		Response res =
				given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/APIBatchStudents");
		
		System.out.println("Status code is "+res.statusCode());
		System.out.println("Response code is "+ res.asString());
	}
}
