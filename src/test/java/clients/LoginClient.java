package clients;

import static helpers.factories.SpecificationFactory.baseRequestSpecification;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import pojos.requests.login.PostLoginRequest;

public class LoginClient {

    public static final String PATH_LOGIN = "/api/login";

    public Response postLoginRequest(PostLoginRequest body) {
        return given()
            .spec(baseRequestSpecification())
            .body(body)
            .post(PATH_LOGIN);
    }
}
