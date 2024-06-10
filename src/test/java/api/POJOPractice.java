package api;

import antitis.CustomResponse;
import antitis.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

public class POJOPractice {
    @Test
    public void postCategory(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("StudyMate");
        requestBody.setCategory_description("CodeWise academy");
        requestBody.setFlag(true);
        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(status,201);
    }

    @Test
    public void testCustom() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("Cars");
        requestBody.setCategory_description("Mercedes CLS 63");
        requestBody.setFlag(true);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201,status);
        String categoryTitle = response.jsonPath().getString("category_title");
        Assert.assertEquals(requestBody.getCategory_title(),categoryTitle);


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        String expectedTitle = customResponse.getCategory_title();
        String expectedDescription = customResponse.getCategory_description();
        Assert.assertEquals(expectedTitle,"Cars");
        Assert.assertEquals(expectedDescription,"Mercedes CLS 63");
    }

    @Test
    public void postClient() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String urlPost = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("Tesla");
        requestBody.setSeller_name("Elon Mask");
        requestBody.setEmail("Mask@gmail.com");
        requestBody.setPhone_number("7739961217");
        requestBody.setAddress("200 N Dearborn");

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(urlPost);
        int statusCode = response.statusCode();
        Assert.assertEquals(201,statusCode);

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(),CustomResponse.class);
        int id = customResponse.getSeller_id();
        System.out.println(id);

        String urlGet = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers/" + id;
        response = RestAssured.given().auth().oauth2(token).get(urlGet);
        int status = response.statusCode();
        String companyName = customResponse.getCompany_name();
        String sellerName = customResponse.getSeller_name();
        String email = customResponse.getEmail();
        String phoneNumber = customResponse.getPhone_number();
        String address = customResponse.getAddress();
        Assert.assertEquals(200,status);
        Assert.assertEquals(companyName,requestBody.getCompany_name());
        Assert.assertEquals(sellerName,requestBody.getSeller_name());
        Assert.assertEquals(email,requestBody.getEmail());
        Assert.assertEquals(phoneNumber,requestBody.getPhone_number());
        Assert.assertEquals(address,requestBody.getAddress());
    }

}
