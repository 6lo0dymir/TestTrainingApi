package services.authentication.steps;

import io.qameta.allure.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import services.authentication.base.*;
import services.authentication.models.*;
import services.utils.*;

public class ContactsBodyDeserialize extends ContactsBaseTest {

    @Step("Десериализация тела ответа при создании контакта")
    public static NewContactResponse createContact(NewContactBody requestBody, String token) {
        MethodSelector method = new MethodSelector();

        RequestSpecification spec = getContactsSpec()
                .header("Authorization", "Bearer " + token)
                .body(requestBody);

        Response rs = method.executeMethod(spec, "", MethodSelector.Methods.POST);

        return rs
                .then()
                .statusCode(201)
                .extract()
                .as(NewContactResponse.class);
    }
}
