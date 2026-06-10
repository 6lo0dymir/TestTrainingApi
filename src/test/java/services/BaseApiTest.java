package services;

import io.qameta.allure.restassured.*;
import io.restassured.*;
import io.restassured.filter.log.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseApiTest {
    private static final String BASE_API_URL = System.getenv()
            .getOrDefault("BASE_API_URL","https://thinking-tester-contact-list.herokuapp.com");

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_API_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }
    public static RequestSpecification getRequestSpec() {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }


}
