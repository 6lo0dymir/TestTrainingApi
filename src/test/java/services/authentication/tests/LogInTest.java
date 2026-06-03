package services.authentication.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import resourses.*;
import services.authentication.base.*;
import services.authentication.models.*;
import services.authentication.steps.*;
import services.utils.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Authentication Service")
@Feature("Login")
@Owner("Терехин Владимир")
@DisplayName("Тест аутентификации")
public class LogInTest extends UsersBaseTest {
   String email = Credentials.email;
   String password = Credentials.password;

   @DisplayName("Проверка логина и получения токена")
   @Test
   public void loginSmokeTest(){
      UserLoginRequest requestBody = RequestBodyCreator.createUserLoginBodyRequest(email, password);
      UserLoginResponse responseBody = BodyDeserialize.deserializeResponseLoginBodyToJavaObject(requestBody);
      assertAll(
              ()->assertNotNull(responseBody.getAccsessToken())
      );



   }



}
