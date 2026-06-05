package services.authentication.steps;

import io.qameta.allure.*;
import services.authentication.models.*;

public class NewContactStep {

    @Step("Создание контакта")
    public static NewContactResponse createContact(NewContactBody contactBody, String token) {
        return ContactsBodyDeserialize.createContact(contactBody, token);
    }
}
