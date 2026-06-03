package services.utils;

import io.qameta.allure.*;
import services.authentication.models.*;

public class RequestBodyCreator {

    @Step("Создание JSON обьекта с параметрами \"email\" и \"password\"")
    public static UserLoginRequest createUserLoginBodyRequest (String email, String password){
        UserLoginRequest user = new UserLoginRequest();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
