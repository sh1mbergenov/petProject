package step_definitions;

import antitis.RequestBody;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utilities.ApiRunner;

import java.util.HashMap;

public class SellerApiSteps {
    int size;
    String email;
    int id;

    @Given("user hits get all seller api with {string}")
    public void user_hits_get_all_seller_api_with(String endpoint) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("isArchived",false);
        params.put("page",1);
        params.put("size",10);
        ApiRunner.runGet(endpoint,params);
        size = ApiRunner.getCustomResponse().getResponses().size();
    }
    @Then("verify email is not empty")
    public void verify_email_is_not_empty() {
        for (int i = 0; i < size; i++) {
            String email = ApiRunner.getCustomResponse().getResponses().get(i).getEmail();
            Assert.assertFalse(email.isEmpty());
        }
    }
    @Given("user hits get single seller api with {string}")
    public void user_hits_get_single_seller_api_with(String singleSellerEndpoint) {
         id = 4348;
      ApiRunner.runGet(singleSellerEndpoint + id);
    }
    @Then("verify single seller email is not empty")
    public void verify_single_seller_email_is_not_empty() {
        String email = ApiRunner.getCustomResponse().getEmail();
        Assert.assertFalse(email.isEmpty());
    }
    @Then("hit put api with {string} and change email to {string}")
    public void hit_put_api_with_and_change_email_to(String putEndPoint, String changedEmail) {
        Faker faker = new Faker();
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(changedEmail);
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());
        email = changedEmail;
        ApiRunner.runPut(putEndPoint + id,requestBody);
    }
    @Then("verify email was changed")
    public void verify_email_was_changed() {
        String expectedEmail = email;
     Assert.assertEquals(ApiRunner.getCustomResponse().getEmail(),expectedEmail);
    }

}
