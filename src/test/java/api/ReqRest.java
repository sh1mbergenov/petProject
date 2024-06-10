package api;

import antitis.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ReqRest {
    @Test
    public void FirstTest(){
        Response response = RestAssured.get("https://reqres.in/api/users/2");
   //   response.prettyPrint();
        String email = response.jsonPath().getString("data.email");
        System.out.println(email);
        Assert.assertTrue(email.endsWith("@reqres.in"));
        int status = response.statusCode();
        Assert.assertEquals(status,200);
    }
    @Test
    public void SecondTest(){
        Response response = RestAssured.get("https://reqres.in/api/users/2");
     // response.prettyPrint();
        String name = response.jsonPath().getString("data.first_name");
        System.out.println(name);
        Assert.assertTrue(name.equals("Janet"));
        int status = response.statusCode();
        Assert.assertEquals(status,200);
    }
    @Test
    public void text(){
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        String text = response.jsonPath().getString("support.text");
        System.out.println(text);
    }
    @Test
    public void emailsOfAllUsers(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
    }
    @Test
    public void Post(){
        RequestBody requestBody = new RequestBody();
        requestBody.setEmail("yryskeldi@gmail.com");
        requestBody.setPassword("yryskeldi");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody)
                .post("https://backend.cashwise.us/api/myaccount/auth/login");
        System.out.println(response.statusCode());
        String token = response.jsonPath().getString("jwt_token");
        System.out.println(token);
    }
}
