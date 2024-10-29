package org.nagra.testScripts;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class APIRequest {
    public static void main(String[] args) {
        // Define the base URI
        RestAssured.baseURI = "https://api.entelinteg.opentv.com";
        
     // Manually encode the filter parameter
        String filter = "{\"locale\":\"es_LA\"}";


        // Define the endpoint with the pre-encoded filter parameter
        String endpoint = "/metadata/delivery/GLOBAL/btv/services";

        // Define the headers
        String authToken = "Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6IjM5OTczMCIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOiJTVEIwMDAxIiwib25OZXR3b3JrIjp0cnVlLCJjdXJyZW50VXNlclByb2ZpbGUiOnsiX2lkIjoiNjZhMTZjMmQzZjRhYjIzNzU4MTBkYmZhIiwiY2hpbGRQcm9maWxlIjpmYWxzZSwibWF4QWdlUmF0aW5nIjo3fSwiYmxvY2tpbmdJbmZvcm1hdGlvbiI6e30sInBhcmVudGFsQ29udHJvbCI6eyJyYXRpbmdUaHJlc2hvbGQiOnsiTVBBQSI6IlBHIn19LCJmaXhlZF9leHAiOjE3MzE4NTcyNzQsImNhY2hlS2V5IjoiUTJocGJHVmZSbFJVU0RvM09rSnBaeUJUWTNKbFpXNCIsImZpbHRlcmluZ0luZm9ybWF0aW9uIjp7ImZpbHRlci1zdHJpbmciOiJDaGlsZV9GVFRIIn0sImFjY291bnRQcm9maWxlSWQiOiJGVFRIX0NMIiwidHlwIjoiRGV2QXV0aE4iLCJleHAiOjE3MjkyNjg4NzQsInZlciI6IjEuMCIsImRldmljZVByb2ZpbGVJZCI6IkJpZyBTY3JlZW4iLCJzZXNzaW9uQ29udHJvbCI6eyJzZXNzaW9uQ29udHJvbEVuYWJsZWQiOnRydWUsIm1heFNlc3Npb25zIjoyfSwidGVuYW50SWQiOiJuYWdyYSIsImJpbGxpbmdDb3VudHJ5Q29kZSI6IkNMIiwiZGV2aWNlSWQiOiIyNjIzMTU3MDIxMDg4NzciLCJnZW9CbG9ja0V4ZW1wdCI6ZmFsc2V9.e0FbnQr_hiJnEAgJXfsEG1cvzY45dBwQ1Gor94BYxhI";

        String uriValue = "https://api.entelinteg.opentv.com/metadata/delivery/GLOBAL/btv/services";
        
        
        // Make the GET request with logging
        Response response = given()
            .header("Accept", "application/json, text/plain, */*")
            .header("Authorization", authToken)
            .queryParam("filter", filter)
            .log().all()  // Log the request details
            .when()
            .get(uriValue);
            //.get(endpoint)
            //.then()
            //.log().all()  // Log the response details
            //.extract()
            //.response();

        // Print the response
        System.out.println("Status code: " + response.getStatusCode());
        //System.out.println("Response body: " + response.getBody().asString());
    }
}

