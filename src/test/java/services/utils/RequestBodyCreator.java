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
    @Step("Создание JSON объекта с параметрами нового контакта")
    public static NewContactBody createContactBody (String firstName, String lastName, String birthdate, String email,
                                                    String phone, String street1, String street2, String city,
                                                    String stateProvince, String postalCode, String country){
        NewContactBody contactBody = new NewContactBody();
        contactBody.setFirstName(firstName);
        contactBody.setLastName(lastName);
        contactBody.setBirthdate(birthdate);
        contactBody.setEmail(email);
        contactBody.setPhone(phone);
        contactBody.setStreet1(street1);
        contactBody.setStreet2(street2);
        contactBody.setCity(city);
        contactBody.setStateProvince(stateProvince);
        contactBody.setPostalCode(postalCode);
        contactBody.setCountry(country);
        return contactBody;

    }
}
