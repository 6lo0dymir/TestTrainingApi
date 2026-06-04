package services.authentication.steps;

import io.qameta.allure.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import services.authentication.base.*;
import services.authentication.models.*;
import services.utils.*;

public class BodyDeserialize extends UsersBaseTest {

    @Step("Десериализация тела ответа login в Java объект")
    public static UserLoginResponse deserializeResponseLoginBodyToJavaObject(UserLoginRequest requestBody) {
        MethodSelector method = new MethodSelector();
        RequestSpecification spec = getAuthSpec().body(requestBody);
        Response rs = method.executeMethod(spec, "login", MethodSelector.Methods.POST);

        return rs
                .then()
                .extract()
                .as(UserLoginResponse.class);
    }
}
