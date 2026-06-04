package services.authentication.steps;

import io.qameta.allure.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import services.authentication.base.*;
import services.authentication.models.*;
import services.utils.*;

public class GetUserDataWithToken extends UsersBaseTest {
    @Step("Получение данных юзера по токену")
    public static UserLoginResponse.User getCurrentUser(String token){
        MethodSelector method = new MethodSelector();

        RequestSpecification spec = getAuthSpec().header("Authorization","Bearer " + token);

        Response response = method.executeMethod(spec,"me",MethodSelector.Methods.GET);

        return response
                .then()
                .statusCode(200)
                .extract()
                .as(UserLoginResponse.User.class);
    }
}
