package services.authentication.base;

import io.restassured.specification.*;
import services.*;

public class ContactsBaseTest extends BaseApiTest {
    private static final String CONTACTS_BASE_PATH = "/contacts";

    public static RequestSpecification getContactsSpec() {
        return getRequestSpec()
                .basePath(CONTACTS_BASE_PATH);
    }
    public static RequestSpecification getContactsSpecWithToken(String token) {
        return getContactsSpec()
                .header("Authorization", "Bearer " + token);
    }
}
