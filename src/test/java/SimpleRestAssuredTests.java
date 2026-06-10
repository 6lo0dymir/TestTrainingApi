import groovyjarjarantlr4.v4.runtime.misc.*;
import io.qameta.allure.*;
import io.restassured.http.*;
import org.checkerframework.checker.units.qual.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;


@Epic("Базовые тесты RESTassured")
@Owner("Терехин Владимир")
@DisplayName("Прямые тесты RESTassured")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleRestAssuredTests {
    private static String baseUserPath;
    private static String baseContactPath;
    private static Map<String, String> credentials;
    private static String token;
    private static String contactId;


    @BeforeAll
    public static void setUp() {

        String baseUrl = "https://thinking-tester-contact-list.herokuapp.com";
        baseUserPath = "/users";
        baseContactPath = "/contacts";
        String targetEmail = "john.doe@example.com";
        io.restassured.RestAssured.baseURI = baseUrl;
        /** Создаем тело запроса JSON */
        Map<String, String> credentials = Map.of(
                "email", "kennymc666@gmail.com",
                "password", "12345678"
        );
        /** Получаем токен */
        token = given()
                .basePath(baseUserPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
        /** Ищем ID контакта по указанному Email */
        contactId = given()
                .basePath(baseContactPath)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path("find {it.email == '" + targetEmail + "'}._id");

    }
    @AfterEach
    public void cleanUp() {
        if (contactId != null) {
            deleteContact(contactId);
            contactId = null;
        }
    }

    @Step("Создание нового тела контакта")
    private Map<String,String> createContactBody(){
        Map<String, String> newContact = new HashMap<>();
        newContact.put("firstName", "John");
        newContact.put("lastName", "Doe");
        newContact.put("birthdate", "1990-01-01");
        newContact.put("email", "john.doe@example.com");
        newContact.put("phone", "88005553535");
        newContact.put("street1", "123 Main St");
        newContact.put("street2", "Apt 4B");
        newContact.put("city", "New York");
        newContact.put("stateProvince", "NY");
        newContact.put("postalCode", "10001");
        newContact.put("country", "USA");
        return newContact;
    }
    @Step("Создание нового контакта с извлечением id")
    private String createTestContact(){
        Map<String,String> contact = createContactBody();
        return given()
                .basePath(baseContactPath)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(contact)
                .when()
                .post()
                .then()
                .extract()
                .path("_id");
    }
    @Step("Удаление контакта по ID")
    private void deleteContact(String contactId) {
        if (contactId != null) {
            try {
                given()
                        .basePath(baseContactPath)
                        .header("Authorization", "Bearer " + token)
                        .delete("/" + contactId);
            } catch (Exception e) {
                System.err.println("Cleanup failed for " + contactId + ": " + e.getMessage());
            }
        }
    }


    @DisplayName("Базовая аутентификация")
    @Test
    @Order(1)
    public void authBaseTest() {
        given()
                .basePath(baseUserPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("user", notNullValue())
                .body("user.email", equalTo("kennymc666@gmail.com"));


    }
    @DisplayName("Получение данных пользователя с токеном авторизации")
    @Test
    @Order(2)
    public void getUserDataWithToken() {
        given()
                .basePath(baseUserPath)
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .when()
                .get("/me")
                .then()
                .statusCode(200)
                .body("_id", notNullValue())
                .body("email", equalTo("kennymc666@gmail.com"))
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
        ;
    }

    @DisplayName("Добавление нового контакта")
    @Test
    @Order(3)
    public void addNewContact() {
        Map<String,String> contact = createContactBody();
        String contactID = createTestContact();
        given()
                .basePath(baseContactPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(contact)
                .when()
                .post("")
                .then()
                .statusCode(201)
                .body("_id", notNullValue())
                .body("firstName", equalTo(contact.get("firstName")))
                .body("lastName", equalTo(contact.get("lastName")))
                .body("email", equalTo(contact.get("email")))
                .body("phone", equalTo(contact.get("phone")))
                .extract()
                .path("_id");

    }

    @DisplayName("Находим id контакта по email")
    @Test
    @Order(4)
    public void getContactIdByEmail() {
        createTestContact();
        String targetEmail = "john.doe@example.com";
        String contactId = given()
                .basePath(baseContactPath)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path("find {it.email == '" + targetEmail + "'}._id");
        System.out.println("ID контакта " + contactId);
        given()
                .basePath(baseContactPath)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/" + contactId)
                .then()
                .statusCode(200)
                .body("email", equalTo(targetEmail));
    }

    @DisplayName("Изменяем данные контакта метод PATCH")
    @Test
    @Order(5)
    public void changeContactData() {
        String contactId = createTestContact();
        Map<String, String> newContactData = Map.of(
                "firstName", "Жека",
                "lastName", "Неизвестный"
        );
        given()
                .basePath(baseContactPath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(newContactData)
                .when()
                .patch("/" + contactId)
                .then()
                .statusCode(200)
                .body("firstName", equalTo(newContactData.get("firstName")))
                .body("lastName", equalTo(newContactData.get("lastName")))
        ;
    }

    @DisplayName("Удаление контакта по ID")
    @Test
    @Order(6)
    public void deleteContactById() {
        given()
                .basePath(baseContactPath)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/" + contactId)
                .then()
                .log().all()
                .statusCode(200);
    }
}

