package services.authentication.steps;

import io.qameta.allure.*;
import services.authentication.models.*;

public class LoginStep {
    @Step("Логинимся с телом запроса и получаем тело ответа")
    public static UserLoginResponse loginAndGetResponseBody(UserLoginRequest requestBody) {
        return BodyDeserialize.deserializeResponseLoginBodyToJavaObject(requestBody);
    }
}
