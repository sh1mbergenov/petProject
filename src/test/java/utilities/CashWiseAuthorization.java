package utilities;

import api.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CashWiseAuthorization {

    public static String getToken(){
        RequestBody requestBody = new RequestBody();
        String url = Config.getProperty("cashWiseApiURL")+"api/myaccount/auth/login";
        requestBody.setEmail("yryskeldi0920@gmail.com");
        requestBody.setPassword("2001yryskeldi");
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody).post(url);
        return response.jsonPath().getString("jwt_token");




    }
}
