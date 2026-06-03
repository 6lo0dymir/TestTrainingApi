package services.authentication.base;

import io.restassured.specification.*;
import services.BaseApiTest;

public class UsersBaseTest extends BaseApiTest {
    private static final String USERS_BASE_PATH = "/users/";

    public static RequestSpecification getAuthSpec() {
        return getRequestSpec()
                .basePath(USERS_BASE_PATH);
    }
}
