package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SwapiTest {
    private Response response;

    @Test
    public void getSwApi() {

        response = given().get("https://swapi.dev/api/people/1");
        String height = response.jsonPath().getString("height");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(height, "172");
    }

    @Test
    //1. Test the endpoint people/2/ and check the success response,
    // the skin color to be gold, and the amount of films
    // it appears on (should be 6).
    public void isBlond() {
        response = RestAssured.when().get("https://swapi.dev/api/people/2");

        String hairColor = response.jsonPath().getString("skin_color");

        Assert.assertEquals(hairColor, "gold");

        List<String> movieList = response.jsonPath().getList("films");
        List<String> expected = new ArrayList<>();

        expected.add("https://swapi.dev/api/films/1/");
        expected.add("https://swapi.dev/api/films/2/");
        expected.add("https://swapi.dev/api/films/3/");
        expected.add("https://swapi.dev/api/films/4/");
        expected.add("https://swapi.dev/api/films/5/");
        expected.add("https://swapi.dev/api/films/6/");

        for (int i = 0; i < 5; i++) {

            Assert.assertEquals(movieList.get(i), expected.get(i));
        }


    }
//2. Request the endpoint of the second movie in which people/2/ was present
// (using the response from people/2/). Check the release date to be in the
// correct date format, and the response to include characters, planets,
// starships, vehicles and species, each element including more than 1 element.
    @Test
    public void checkRealeseDate(){
        response = given().get("https://swapi.dev/api/films/2");
       // response =RestAssured.when().get("https://swapi.dev/api/films/2");
        String releaseDate = response.jsonPath().getString("release_date");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //year validation
        try {
            formatter.parse(releaseDate);
            Assert.assertTrue(true);

        } catch(ParseException e){
            Assert.assertTrue(false);
        }
    }

    // 3. Request the endpoint of the first planet present in the last
    // film's response (using the previous response). Check the gravity and
    // the terrains matching the exact same values returned by the request
    // (Use fixtures to store and compare the data of terrains and gravity).
    @Test

    public void isFilmFound() {
        response = given().get("https://swapi.dev/api/film/7");
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
