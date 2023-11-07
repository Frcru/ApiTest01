package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ServicesTest {

    @Test
    public void getExample(){
        Response response;
        response = given().get("https://65443c285a0b4b04436c2fe6.mockapi.io/api/v1/users/6");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @org.junit.Test

    public void postExample(){
        Response response;
        Map<String,String> user = new HashMap<>();
        user.put("alias","Silvina");
        user.put("userName","Silviili");
        user.put("age","18");
        user.put("workStatus","true");

        response = given().contentType("application/json").body(user).when().post(
                "https://65443c285a0b4b04436c2fe6.mockapi.io/api/v1/users/");
        Assert.assertEquals(response.getStatusCode(),201);
    }
}
