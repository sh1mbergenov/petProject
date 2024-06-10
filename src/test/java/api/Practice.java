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
import utilities.ApiRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class Practice {
    @Test
    public void getSeller() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers/4175";
        Response response = RestAssured.given().auth().oauth2(token)
                .get(url);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        String email = customResponse.getEmail();
        Assert.assertFalse(email.isEmpty());
    }
    @Test
    public void createSeller() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("Comed");
        requestBody.setSeller_name("Ivan");
        requestBody.setEmail("Ivan123@gmail.com");
        requestBody.setPhone_number("7739961778");
        requestBody.setAddress("3109 Lake Shore Drive");
        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(requestBody).post(url);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        String email = customResponse.getEmail();
        Assert.assertFalse(email.isEmpty());
        int id = customResponse.getSeller_id();

        String url1 = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers/" + id;
        Response response1 = RestAssured.given().auth().oauth2(token).get(url);
        String companyName = customResponse.getCompany_name();
        Assert.assertEquals(companyName,"Comed");


    }
    @Test
    public void getAllSellers() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers";
        HashMap<String,Object> params = new HashMap<>();
        params.put("isArchived",false);
        params.put("size",1);
        params.put("page",10);
        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).params(params).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200,status);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        int size = customResponse.getResponses().size();
        for (int i = 0; i < size; i++) {
            String email = customResponse.getResponses().get(i).getEmail();
            Assert.assertFalse(email.isEmpty());
        }
    }
    @Test
    public void listOfProducts() throws JsonProcessingException {
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/products";
        String token = CashWiseAuthorization.getToken();
        HashMap<String,Object> params = new HashMap<>();
        params.put("page",1);
        params.put("size",10);
        Response response = RestAssured.given().auth().oauth2(token)
                .params(params).get(url);
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        int size = customResponse.getResponses().size();
        for (int i = 0; i < size; i++) {
         int id = customResponse.getResponses().get(i).getCategory_id();
          Assert.assertFalse(id != 0);
            System.out.println(id);
        }
    }
    @Test
    public void TestGet(){
        ApiRunner.runGet("api/myaccount/sellers/4103");
        String email =  ApiRunner.getCustomResponse().getEmail();
        System.out.println(email);
    }
    @Test
    public void testAllSellers(){
        Map<String,Object> params = new HashMap<>();
        String url = "api/myaccount/sellers";
        params.put("isArchived",false);
        params.put("page",1);
        params.put("size",60);
        ApiRunner.runGet(url,params);
        int size = ApiRunner.getCustomResponse().getResponses().size();
        System.out.println(size);

    }
    @Test
    public void postSeller(){
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("People's Gas");
        requestBody.setSeller_name("Mike");
        requestBody.setEmail("mike555@gmail.com");
        requestBody.setPhone_number("7739961778");
        requestBody.setAddress("3109 Lake Shore Drive");
        String url = "my/account/sellers";
        ApiRunner.runPost(url,requestBody);
    }
    @Test
    public void testDelete(){
        int id = 1924;
        String url = "my/account/products/";
        ApiRunner.runDelete(url+id);
    }
}
