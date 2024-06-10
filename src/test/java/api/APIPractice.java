package api;

import antitis.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class APIPractice {
    @Test
    public void testEmail() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);
        Response response = RestAssured.given().auth().oauth2(token).params(params)
                .get(url);
        response.prettyPrint();
        String email1 = response.jsonPath().getString("responses[0].email");
        String email2 = response.jsonPath().getString("responses[1].email");
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        Assert.assertFalse(email1.isEmpty());
        Assert.assertFalse(email2.isEmpty());
    }

    @Test
    public void improvedAllSellers() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 100);
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        int size = response.jsonPath().getList("responses").size();
        for (int i = 0; i < size; i++) {
            String email = response.jsonPath().getString("responses[" + i + "].email");
            Assert.assertFalse(email.isEmpty());
        }
    }

    @Test
    public void getAllBankAccounts() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        response.prettyPrint();
        int status = response.statusCode();
        Assert.assertEquals(200, status);
    }

    @Test
    public void createBankAccount() {
        RequestBody requestBody = new RequestBody();
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/bankaccount";
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name("samsung");
        requestBody.setDescription("samsung bank account");
        requestBody.setBalance(1231525040);
        Response response = RestAssured.given().contentType(ContentType.JSON).auth()
                .oauth2(token).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201, status);
    }
    @Test
    public void verifyAccountName(){
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiURL") + "api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200,status);
        int size = response.jsonPath().getList("JSON").size();
        String expectedBankAccountName = "samsung";
        boolean isPresent = false;
        for (int i = 0; i < size; i++) {
           String bankName = response.jsonPath().getString("["+ i +"].bank_account_name");
          if(bankName.equalsIgnoreCase(expectedBankAccountName)){
              isPresent = true;
          }
        }
        Assert.assertTrue(isPresent);
    }
}