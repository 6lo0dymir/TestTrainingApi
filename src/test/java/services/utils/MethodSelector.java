package services.utils;

import io.restassured.response.*;
import io.restassured.specification.*;

public class MethodSelector {
    public enum Methods {
        POST,
        GET,
        PUT,
        DELETE
    }

    /*выполнить метод (добавить в спецификацию)*/

    public Response executeMethod(RequestSpecification requestSpecification, String endpoint, Methods method) {
        return switch (method) {
            case POST -> requestSpecification.post(endpoint);
            case GET -> requestSpecification.get(endpoint);
            case PUT -> requestSpecification.put(endpoint);
            case DELETE -> requestSpecification.delete(endpoint);
            default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
        };
    }

}
