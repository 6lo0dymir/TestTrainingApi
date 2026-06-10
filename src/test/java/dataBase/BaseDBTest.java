package dataBase;

import io.qameta.allure.restassured.*;
import io.restassured.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

public class BaseDBTest {
    @BeforeAll
    protected static void setup() {
            RestAssured.filters(new AllureRestAssured());
        }
}
