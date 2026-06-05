package services.authentication.tests;

import org.junit.jupiter.api.*;
import resourses.*;
import services.authentication.base.*;
import services.authentication.models.*;
import services.authentication.steps.*;
import services.utils.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест на создание нового контакта")
public class CreateContactTest extends ContactsBaseTest {

    private static String token;

    @BeforeAll
    public static void Login(){
        UserLoginRequest loginRequest = RequestBodyCreator.createUserLoginBodyRequest(
                Credentials.email,Credentials.password);
        UserLoginResponse loginResponse = LoginStep.loginAndGetResponseBody(loginRequest);
        token = loginResponse.getAccessToken();
    }
    @DisplayName("Создание нового контакта")
    @Test
    public void createNewContact(){
        NewContactBody contactBody = RequestBodyCreator.createContactBody(
                NewContactData.firstName,NewContactData.lastName,NewContactData.birthdate, NewContactData.email,
                NewContactData.phone,NewContactData.street1,NewContactData.street2,NewContactData.city,
                NewContactData.stateProvince,NewContactData.postalCode,NewContactData.country
        );
        NewContactResponse createdContact = NewContactStep.createContact(contactBody, token);
        assertEquals(NewContactData.firstName, createdContact.getFirstName());
        assertEquals(NewContactData.lastName, createdContact.getLastName());
        assertEquals(NewContactData.email, createdContact.getEmail());
    }
}
